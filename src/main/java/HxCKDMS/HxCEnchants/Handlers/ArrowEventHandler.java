package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCEnchants.Configurations;
import HxCKDMS.HxCEnchants.EnchantConfigHandler;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

import java.util.List;
import java.util.Random;

@SuppressWarnings("all")
public class ArrowEventHandler {
	private boolean isExplosive, isHoming, isZeus, isPoison, isPiercing, isLightning, isFlaming;
	private short ExplosionLevel, PoisonLevel, HomingLevel, ZeusLevel, PiercingLevel, LightningLevel, FlamingLevel;

	@SubscribeEvent
	public void ArrowLooseEvent(ArrowLooseEvent event) {
        ItemStack stack = event.bow;
        assert stack != null;
        if (stack.hasTagCompound() && (stack.getTagCompound().getLong("HxCEnchantCharge") > 0 || !Configurations.enableChargesSystem)) {
            if (EnchantConfigHandler.isEnabled("Zeus", "weapon"))
                ZeusLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.Zeus.effectId, stack);
            if (EnchantConfigHandler.isEnabled("ArrowSeeking", "weapon"))
                HomingLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.ArrowSeeking.effectId, stack);
            if (EnchantConfigHandler.isEnabled("ArrowExplosive", "weapon"))
                ExplosionLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.ArrowExplosive.effectId, stack);
            if (EnchantConfigHandler.isEnabled("Poison", "weapon"))
                PoisonLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.Poison.effectId, stack);
            if (EnchantConfigHandler.isEnabled("Piercing", "weapon"))
                PiercingLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.Piercing.effectId, stack);
            if (EnchantConfigHandler.isEnabled("LightningArrow", "weapon"))
                LightningLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.LightningArrow.effectId, stack);
            if (EnchantConfigHandler.isEnabled("FlamingArrow", "weapon"))
                FlamingLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.FlamingArrow.effectId, stack);

            isExplosive = ExplosionLevel > 0;
            isHoming = HomingLevel > 0;
            isZeus = ZeusLevel > 0;
            isPoison = PoisonLevel > 0;
            isPiercing = PiercingLevel > 0;
            isLightning = LightningLevel > 0;
            isFlaming = FlamingLevel > 0;

            if (Configurations.enableChargesSystem) {
                int use = 0;
                if (isExplosive) use += EnchantConfigHandler.getData("ArrowExplosive", "weapon")[4];
                if (isHoming) use += EnchantConfigHandler.getData("ArrowSeeking", "weapon")[4];
                if (isZeus) use += EnchantConfigHandler.getData("Zeus", "weapon")[4];
                if (isPoison) use += EnchantConfigHandler.getData("Poison", "weapon")[4];
                if (isPiercing) use += EnchantConfigHandler.getData("Piercing", "weapon")[4];
                if (isLightning) use += EnchantConfigHandler.getData("LightningArrow", "weapon")[4];
                if (isFlaming) use += EnchantConfigHandler.getData("FlamingArrow", "weapon")[4];

                long tmp = stack.getTagCompound().getLong("HxCEnchantCharge") - use;

                if (tmp >= 0)
                    stack.getTagCompound().setLong("HxCEnchantCharge", tmp);
                else {
                    isExplosive = false; isHoming = false;
                    isZeus = false; isPoison = false;
                    isPiercing = false; isLightning = false;
                    isFlaming = false;
                }
            }
        }
    }


	@SubscribeEvent
	public void entityAttacked(LivingAttackEvent event) {
        if(event.entityLiving instanceof EntityLiving){
            EntityLivingBase ent = event.entityLiving;
            if (!event.source.isProjectile()) return;
            if (isExplosive) {
				ent.worldObj.createExplosion(ent, ent.posX, ent.posY, ent.posZ, 2.0F * ExplosionLevel, Configurations.ExplosionDestroysTerrain);
				isExplosive = false;
			}
            if (isLightning) {
				ent.attackEntityFrom(new DamageSource("LightningArrow"), 7);
				isLightning = false;
			}
            if (isHoming) {
				ent.attackEntityFrom(new DamageSource("HomingArrow"), 5);
				isHoming = false;
			}
			if (isFlaming) {
				ent.attackEntityFrom(new DamageSource("FlamingArrow"), 2);
				isFlaming = false;
			}
			if (isZeus) {
				ent.worldObj.addWeatherEffect(new EntityLightningBolt(ent.worldObj, ent.posX, ent.posY+1, ent.posZ));
				isZeus = false;
			}
            if (isPoison) {
				ent.addPotionEffect(new PotionEffect(Potion.poison.getId(), PoisonLevel * 120, PoisonLevel));
				isPoison = false;
			}
            if (isPiercing) {
				ent.attackEntityFrom(new DamageSource("Piercing").setDamageBypassesArmor().setDamageIsAbsolute().setDamageAllowedInCreativeMode(), event.ammount * Configurations.PiercingPercent);
				isPiercing = false;
			}
        }
	} 


	@SubscribeEvent
    @SuppressWarnings("unchecked")
	public void arrowInAir(EntityEvent event) {
        if (event.entity instanceof EntityArrow){
			EntityArrow arrow = (EntityArrow) event.entity;
            if(isHoming) {
                AxisAlignedBB box = arrow.boundingBox;
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
                double motionY = target.boundingBox.minY + target.height - arrow.posY;
                double motionZ = target.posZ - arrow.posZ;
                arrow.setThrowableHeading(motionX, motionY, motionZ, 2.0F, 0.0F);
            }
            if (isLightning) {
                if (arrow.shootingEntity != null) {
                    arrow.motionX *= LightningLevel;
                    arrow.motionY *= LightningLevel;
                    arrow.motionZ *= LightningLevel;
                    isLightning = false;
                }
            }
            Random ran = new Random();
            if (isFlaming && FlamingLevel > 0 && FlamingLevel < 20) {
                if (FlamingLevel > 5) {FlamingLevel/=3;}
                if (FlamingLevel > 5) {FlamingLevel/=3;}
                int x = (int)Math.round(arrow.posX), y = (int)Math.round(arrow.posY), z = (int)Math.round(arrow.posZ);
                for (int i = x - FlamingLevel; i < x + FlamingLevel; i++) {
                    for (int j = y - FlamingLevel; j < y + FlamingLevel; j++) {
                        for (int k = z - FlamingLevel; k < z + FlamingLevel; k++) {
                            if (ran.nextInt(FlamingLevel) == 0 && arrow.worldObj.isAirBlock(i,j,k))
                                arrow.worldObj.setBlock(i, j, k, Blocks.fire);
                        }
                    }
                }
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