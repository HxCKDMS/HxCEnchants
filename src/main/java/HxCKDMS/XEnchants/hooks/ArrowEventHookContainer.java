package HxCKDMS.XEnchants.hooks;

import java.util.List;

import HxCKDMS.XEnchants.common.XEnchants;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;


public class ArrowEventHookContainer 
{	
	// Booleans, my boys
	boolean isExplosive = false;
	boolean isPoison = false;
	boolean isHoming = false;
	boolean isCritical = false;
	boolean isGodly = false;

	// Integers, ya idiot
	int explosiveAmount;
	int poisonAmount;
	int homingAmount;
	int criticalAmount;
	int godlyAmount;

	EntityLiving target;

	@SubscribeEvent
	public void arrowLooseEvent(ArrowLooseEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		ItemStack stack = player.inventory.getCurrentItem();

		explosiveAmount = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArrowExplosive.effectId, stack);

		if(explosiveAmount > 0)
		{
			isExplosive = true;
		}

		poisonAmount = EnchantmentHelper.getEnchantmentLevel(XEnchants.Poison.effectId, stack);

		if(poisonAmount > 0)
		{
			isPoison = true;
		}

		homingAmount = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArrowSeeking.effectId, stack);

		if(homingAmount > 0)
		{
			isHoming = true;
		}

		criticalAmount = EnchantmentHelper.getEnchantmentLevel(XEnchants.Critical.effectId, stack);

		if(criticalAmount > 0)
		{
			isCritical = true;
		}

		godlyAmount = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArrowLightning.effectId, stack);

		if(godlyAmount > 0)
		{
			isGodly = true;
		}
	}

	@SubscribeEvent
	public void entityAttacked(LivingAttackEvent event)
	{
		EntityPlayerMP ent = (EntityPlayerMP) event.entityLiving;

		if(event.source.isProjectile() && isExplosive)
		{
			this.createExplosionOnEntityWithModifier(null, ent.worldObj, explosiveAmount, ent);
		} else if(event.source.isProjectile() && isPoison)
		{
			int duration = poisonAmount * 20;
			int amplifier = poisonAmount + 8;
			float damage = poisonAmount + 4;
			ent.addPotionEffect(new PotionEffect(Potion.poison.id, duration, amplifier));
			ent.attackEntityFrom(DamageSource.generic, damage);
		} else if(event.source.isProjectile() && isHoming)
		{
			float damage = 6;
			ent.attackEntityFrom(DamageSource.generic, damage);
		} else if(event.source.isProjectile() && isCritical)
		{
			float damage = criticalAmount + 4;
			ent.attackEntityFrom(DamageSource.generic, damage);
		} else if(event.source.isProjectile() && isGodly)
		{
			this.spawnLightningOnEntityWithModifier(null, ent.worldObj, godlyAmount, ent);
		}
	} 

	@SubscribeEvent
	public void arrowInAir(EntityEvent event)
	{
		if(event.entity instanceof EntityArrow)
		{
			EntityArrow arrow = (EntityArrow) event.entity;

			// To whomever reads this, other than myself, I am terribly sorry for the mess of code below...ugh...
			if(isHoming)// && (arrow.shootingEntity instanceof EntityPlayer) && arrow.getDistanceToEntitySq(arrow.shootingEntity) > (float) (7 - homingAmount))
			{	
				if(target == null || target.velocityChanged || !target.canEntityBeSeen(arrow))
				{
					double posX = arrow.posX;
					double posY = arrow.posY;
					double posZ = arrow.posZ;
					double size = 6 * homingAmount;
					double d = -1D;
					EntityLiving entityliving = null;
					List list = arrow.worldObj.getEntitiesWithinAABB(net.minecraft.entity.EntityLiving.class, arrow.boundingBox.expand(size, size, size));

                    for (Object aList : list) {
                        EntityLiving tempEnt = (EntityLiving) aList;

                        if (tempEnt == arrow.shootingEntity) {
                            continue;
                        }

                        double distance = tempEnt.getDistance(posX, posY, posZ);

                        if ((size < 0.0D || distance < size * size) && (d == -1D || distance < d) && tempEnt.canEntityBeSeen(arrow)) {
                            d = distance;
                            entityliving = tempEnt;
                        }
                    }

					target = entityliving;
				}

				if(target != null)
				{
					// All these fancy calculations guarantee that it will hit an entity dead on
					double dirX = target.posX - arrow.posX;
					double dirY = target.boundingBox.minY + (double) (target.height / 2.0F) - (arrow.posY + (double) (arrow.height / 2.0F));
		            double dirZ = target.posZ - arrow.posZ;
					arrow.setThrowableHeading(dirX, dirY, dirZ, 1.5F, 0.0F);
				}
			} else if(isCritical)
			{
				arrow.setIsCritical(true);
			}
		}
	}

/*
	@SubscribeEvent
	// We declared the EntityLiving, but it was null, so every 
	// living update, we set it equal to the EntityLivingBase class
	public void onLivingUpdate(LivingUpdateEvent event)
	{
		EntityLivingBase living = event.entityLiving;
		living = target;
	}
*/

	public void createExplosionOnEntityWithModifier(EntityArrow arrow, World world, int modifierAmount, EntityPlayerMP player)
	{
		if((arrow.shootingEntity instanceof EntityPlayer))
		{
            ItemStack bow = player.getHeldItem();
            int level = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArrowExplosive.effectId, bow);
			world.createExplosion(arrow, arrow.posX, arrow.posY, arrow.posZ, 2.0F * level, true);
		}
	}

	public void spawnLightningOnEntityWithModifier(EntityArrow arrow, World world, int modifierAmount, EntityPlayerMP entity)
	{
		if(!arrow.isInWater())
		{
			if(entity == null)
			{
				if((arrow.shootingEntity instanceof EntityPlayer) && arrow.getDistanceToEntity(arrow.shootingEntity) > 5F + (float) (modifierAmount * 2))
				{
					for(int j = 0; j < modifierAmount; j++)
					{
						int l = world.rand.nextInt(4);
						world.spawnEntityInWorld(new EntityLightningBolt(world, arrow.posX + (double)l, arrow.posY, arrow.posZ + (double)l));
					}
					if(modifierAmount > 1)
					{
						this.createExplosionOnEntityWithModifier(arrow, world, modifierAmount - 1, null);
					}
				}
			} else if(entity.getDistance(arrow.posX, arrow.posY, arrow.posZ) > (double) (5F + (float) ((modifierAmount + 1) * 2)))
			{
				for(int k = 0; k < modifierAmount; k++)
				{
					int i1 = world.rand.nextInt(4);
					world.spawnEntityInWorld(new EntityLightningBolt(world, arrow.posX + (double)i1, arrow.posY, arrow.posZ + (double)i1));
				}
				if(modifierAmount > 1)
				{
					this.createExplosionOnEntityWithModifier(arrow, world, modifierAmount - 1, entity);
				}
			}
		}
	}
}