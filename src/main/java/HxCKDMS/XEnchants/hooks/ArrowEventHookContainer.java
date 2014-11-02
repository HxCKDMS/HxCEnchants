package HxCKDMS.XEnchants.hooks;

import java.util.List;

import HxCKDMS.XEnchants.XEnchants;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;


public class ArrowEventHookContainer 
{
	boolean isExplosive = false;
	boolean isHoming = false;
	boolean isGodly = false;

	int explosiveAmount;
	int homingAmount;
	int godlyAmount;

	EntityLiving target;

	@SubscribeEvent
	public void ArrowLooseEvent(ArrowLooseEvent event)
	{
        if (event.entity instanceof EntityArrow)
        {
            EntityPlayer player = event.entityPlayer;
            ItemStack stack = player.inventory.getCurrentItem();

            godlyAmount = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArrowLightning.effectId, stack);
            homingAmount = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArrowSeeking.effectId, stack);
            explosiveAmount = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArrowExplosive.effectId, stack);

            if(explosiveAmount > 0)
            {
                isExplosive = true;
            }
            if(homingAmount > 0)
            {
                isHoming = true;
            }
            if(godlyAmount > 0)
            {
                isGodly = true;
            }
        }
	}

	@SubscribeEvent
	public void entityAttacked(LivingAttackEvent event)
	{
        if(event.entityLiving instanceof EntityPlayerMP)
        {
		    EntityPlayerMP ent = (EntityPlayerMP) event.entityLiving;
            if(event.source.isProjectile() && isExplosive)
            {
                ent.worldObj.createExplosion(target, target.posX, target.posY, target.posZ, 2.0F, true);
            }
            else if(event.source.isProjectile() && isHoming)
            {
                float damage = 6;
                ent.attackEntityFrom(DamageSource.generic, damage);
            }
            else if(event.source.isProjectile() && isGodly)
            {
                target.worldObj.spawnEntityInWorld(new EntityLightningBolt(target.worldObj, target.posX, target.posY, target.posZ));
            }
        }
	} 

	@SubscribeEvent
	public void arrowInAir(EntityEvent event)
	{
        if (event.entity instanceof EntityArrow){
			EntityArrow arrow = (EntityArrow) event.entity;

			// To whomever reads this, other than myself, I am terribly sorry for the mess of code below...ugh...
			// && (arrow.shootingEntity instanceof EntityPlayer) && arrow.getDistanceToEntitySq(arrow.shootingEntity) > (float) (7 - homingAmount))

            if(isHoming)
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
			}
        }
	}
}