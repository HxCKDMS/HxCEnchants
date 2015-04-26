package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCEnchants.Config;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

import java.util.List;


public class ArrowEventHandler
{
	int Explosive = 0;
	int Homing = 0;
	int Zeus = 0;
    int Poison = 0;

	@SubscribeEvent
	public void ArrowLooseEvent(ArrowLooseEvent event) {
        ItemStack stack = event.bow;

        Explosive = HxCEnchantHelper.getEnchantLevel(stack,8);
        Homing = HxCEnchantHelper.getEnchantLevel(stack,9);
        Zeus = HxCEnchantHelper.getEnchantLevel(stack,10);
        Poison = HxCEnchantHelper.getEnchantLevel(stack,18);

        degrade(stack,(Explosive + Homing + Zeus + Poison));
    }


	@SubscribeEvent
	public void entityAttacked(LivingAttackEvent event) {
        if(event.entityLiving instanceof EntityLiving){
            EntityLivingBase ent = event.entityLiving;
            if (event.source.isProjectile() && Explosive > 0) {
                ent.worldObj.createExplosion(ent, ent.posX, ent.posY, ent.posZ, 2.0F * Explosive, Config.EDT);
            } else if (event.source.isProjectile() && Homing > 0) {
                float damage = 6;
                ent.attackEntityFrom(DamageSource.generic, damage);
            } else if (event.source.isProjectile() && Zeus > 0) {
                ent.worldObj.spawnEntityInWorld(new EntityLightningBolt(ent.worldObj, ent.posX, ent.posY+1, ent.posZ));
            } else if (event.source.isProjectile() && Poison > 0) {
                ent.addPotionEffect(new PotionEffect(Potion.poison.getId(), Poison * 120, Poison));
            }
        }
	} 


	@SubscribeEvent
    @SuppressWarnings("unchecked")
	public void arrowInAir(EntityEvent event) {
        if (event.entity instanceof EntityArrow){
			EntityArrow arrow = (EntityArrow) event.entity;
            if(Homing > 0) {
                AxisAlignedBB box = arrow.boundingBox;
                double size = 8 * Homing;
                List<EntityLiving> possibleTargets = (List<EntityLiving>) event.entity.worldObj.getEntitiesWithinAABB(EntityLiving.class, box.expand(size, size, size));
                double distance = 100000;
                EntityLiving target = null;

                for(EntityLiving entityLiving : possibleTargets){
                    double distanceToEntity = distanceTo(arrow, entityLiving);
                    if(distance > distanceToEntity){
                        distance = distanceToEntity;
                        target = entityLiving;
                    }
                }
                if(target == null)
                    return;

                double motionX = target.posX - arrow.posX;
                double motionY = target.boundingBox.minY + target.height - arrow.posY;
                double motionZ = target.posZ - arrow.posZ;
                arrow.setThrowableHeading(motionX, motionY, motionZ, 2.0F, 0.0F);
            }
        }
	}

    private double distanceTo(EntityArrow entityArrow, Entity entity){
        double[] doubles = new double[]{
                entityArrow.posX - entity.posX,
                entityArrow.posY - entity.posY,
                entityArrow.posZ - entity.posZ
        };

        double distance = 0D;
        for (double aDouble : doubles) distance += Math.pow(aDouble, 2);

        return Math.sqrt(distance);
    }

    public void degrade(ItemStack stack, int Power){
        int newPow = (stack.getTagCompound().getInteger("HxCEnchantPower") - (Power * Config.baseDrain));
        stack.getTagCompound().setInteger("HxCEnchantPower",newPow);
    }
}