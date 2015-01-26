package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCEnchants.Config;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

import java.util.List;


public class ArrowEventHandler
{
	boolean isExplosive;
	boolean isHoming;
	boolean isZeus;
    boolean isPoison;

	int ExplosionLevel;
    int PoisonLevel;
	int HomingLevel;
	int ZeusLevel;

	EntityLiving target;

	@SubscribeEvent
	public void ArrowLooseEvent(ArrowLooseEvent event) {
        ItemStack stack = event.bow;

        isExplosive = false;
        isHoming = false;
        isZeus = false;
        isPoison = false;

        ZeusLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.ArrowLightning.effectId, stack);
        HomingLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.ArrowSeeking.effectId, stack);
        ExplosionLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.ArrowExplosive.effectId, stack);
        PoisonLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.Poison.effectId, stack);

        if(ExplosionLevel > 0){
            isExplosive = true;
        }if(HomingLevel > 0){
            isHoming = true;
        }if(ZeusLevel > 0){
            isZeus = true;
        }if(PoisonLevel > 0){
            isPoison = true;
        }
    }


	@SubscribeEvent
	public void entityAttacked(LivingAttackEvent event) {
        if(event.entityLiving instanceof EntityLiving){
            EntityLivingBase ent = event.entityLiving;
            if (event.source.isProjectile() && isExplosive) {
                ent.worldObj.createExplosion(ent, ent.posX, ent.posY, ent.posZ, 2.0F * ExplosionLevel, Config.EDT);
            } else if (event.source.isProjectile() && isHoming) {
                float damage = 6;
                ent.attackEntityFrom(DamageSource.generic, damage);
            } else if (event.source.isProjectile() && isZeus) {
                ent.worldObj.spawnEntityInWorld(new EntityLightningBolt(ent.worldObj, ent.posX, ent.posY+1, ent.posZ));
            } else if (event.source.isProjectile() && isPoison) {
                ent.addPotionEffect(new PotionEffect(Potion.poison.getId(), PoisonLevel * 120, PoisonLevel));
            }
        }
	} 


	@SubscribeEvent
	public void arrowInAir(EntityEvent event) {
        if (event.entity instanceof EntityArrow){
			EntityArrow arrow = (EntityArrow) event.entity;
            if(isHoming) {
                if(target == null || target.velocityChanged || !target.canEntityBeSeen(arrow)) {
                    double posX = arrow.posX;
                    double posY = arrow.posY;
                    double posZ = arrow.posZ;
                    double size = 6 * HomingLevel;
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
                if(target != null ){
                    if(target instanceof EntityLiving) {
                        double dirX = target.posX - arrow.posX;
                        double dirY = target.boundingBox.minY + (double) (target.height / 2.0F) - (arrow.posY + (double) (arrow.height / 2.0F));
                        double dirZ = target.posZ - arrow.posZ;
                        arrow.setThrowableHeading(dirX, dirY, dirZ, 1.5F, 0.0F);
                        arrow.setVelocity(dirX*10, dirY*10, dirZ*10);
                    }
                }
            }
        }
	}
}