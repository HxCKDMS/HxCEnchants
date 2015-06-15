package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCEnchants.Config;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.EnchantmentHelper;
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
	boolean isExplosive, isHoming, isZeus, isPoison, isPiercing;
	int ExplosionLevel, PoisonLevel, HomingLevel, ZeusLevel, PiercingLevel;

	@SubscribeEvent
	public void ArrowLooseEvent(ArrowLooseEvent event) {
        ItemStack stack = event.bow;
        if (Config.enchArrowLightningEnable) ZeusLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.ArrowLightning.effectId, stack);
        if (Config.enchArrowSeekingEnable) HomingLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.ArrowSeeking.effectId, stack);
        if (Config.enchArrowExplosiveEnable) ExplosionLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.ArrowExplosive.effectId, stack);
        if (Config.enchPoisonEnable) PoisonLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.Poison.effectId, stack);
        if (Config.enchPiercingEnable) PiercingLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.Penetrating.effectId, stack);

        isExplosive = ExplosionLevel > 0 && Config.enchArrowExplosiveEnable;
        isHoming = HomingLevel > 0 && Config.enchArrowSeekingEnable;
        isZeus = ZeusLevel > 0 && Config.enchArrowLightningEnable;
        isPoison = PoisonLevel > 0 && Config.enchPoisonEnable;
        isPiercing = PiercingLevel > 0 && Config.enchPiercingEnable;
    }


	@SubscribeEvent
	public void entityAttacked(LivingAttackEvent event) {
        if(event.entityLiving instanceof EntityLiving){
            EntityLivingBase ent = event.entityLiving;
            if (event.source.isProjectile() && isExplosive) ent.worldObj.createExplosion(ent, ent.posX, ent.posY, ent.posZ, 2.0F * ExplosionLevel, Config.EDT);
            if (event.source.isProjectile() && isHoming) ent.attackEntityFrom(DamageSource.generic, 6);
            if (event.source.isProjectile() && isZeus) ent.worldObj.addWeatherEffect(new EntityLightningBolt(ent.worldObj, ent.posX, ent.posY+1, ent.posZ));
            if (event.source.isProjectile() && isPoison) ent.addPotionEffect(new PotionEffect(Potion.poison.getId(), PoisonLevel * 120, PoisonLevel));
            if (event.source.isProjectile() && isPiercing) ent.attackEntityFrom(new DamageSource("Piercing").setDamageBypassesArmor(), Config.PiercingDamage);
        }
	} 


	@SubscribeEvent
    @SuppressWarnings("unchecked")
	public void arrowInAir(EntityEvent event) {
        if (event.entity instanceof EntityArrow){
			EntityArrow arrow = (EntityArrow) event.entity;
            if(isHoming) {
                AxisAlignedBB box = arrow.getBoundingBox();
                double size = 8 * HomingLevel;
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
                double motionY = target.getBoundingBox().minY + target.height - arrow.posY;
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
}