package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCCore.api.Utils.AABBUtils;
import HxCKDMS.HxCEnchants.Config;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.List;
import java.util.Random;

public class AOEEventHandler
{
     /************
     * H = Head  *
     * T = Torso *
     * L = Legs  *
     * F = Feet  *
     ************/

    int DeadlyAuraH, DeadlyAuraT, DeadlyAuraL, DeadlyAuraF,
             FieryAuraH, FieryAuraT, FieryAuraL, FieryAuraF,
             ToxicAuraH, ToxicAuraT, ToxicAuraL, ToxicAuraF,
             ThickAuraH, ThickAuraT, ThickAuraL, ThickAuraF,
             DarkAuraH, DarkAuraT, DarkAuraL, DarkAuraF,
             ShroudH, ShroudT, ShroudL, ShroudF;

    ItemStack ArmourHelm = null, ArmourChest = null, ArmourLegs = null, ArmourBoots = null;

    @SubscribeEvent
    @SuppressWarnings({"unused", "ConstantConditions", "unchecked"})
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
		if(event.entityLiving instanceof EntityPlayerMP && !(((EntityPlayerMP) event.entityLiving).getEntityWorld().isRemote)) {
            EntityPlayerMP player = (EntityPlayerMP) event.entityLiving;
            World world = player.getEntityWorld();
            Random rand = world.rand;
            ArmourHelm = player.inventory.armorItemInSlot(3);
            ArmourChest = player.inventory.armorItemInSlot(2);
            ArmourLegs = player.inventory.armorItemInSlot(1);
            ArmourBoots = player.inventory.armorItemInSlot(0);

            if (Config.enchAuraDeadlyEnable) {
                DeadlyAuraH = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraDeadly.effectId, ArmourHelm);
                DeadlyAuraT = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraDeadly.effectId, ArmourChest);
                DeadlyAuraL = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraDeadly.effectId, ArmourLegs);
                DeadlyAuraF = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraDeadly.effectId, ArmourBoots);
            }
            if (Config.enchAuraDarkEnable) {
                DarkAuraH = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraDark.effectId, ArmourHelm);
                DarkAuraT = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraDark.effectId, ArmourChest);
                DarkAuraL = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraDark.effectId, ArmourLegs);
                DarkAuraF = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraDark.effectId, ArmourBoots);
            }
            if (Config.enchAuraFieryEnable) {
                FieryAuraH = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraFiery.effectId, ArmourHelm);
                FieryAuraT = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraFiery.effectId, ArmourChest);
                FieryAuraL = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraFiery.effectId, ArmourLegs);
                FieryAuraF = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraFiery.effectId, ArmourBoots);
            }
            if (Config.enchAuraThickEnable) {
                ThickAuraH = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraThick.effectId, ArmourHelm);
                ThickAuraT = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraThick.effectId, ArmourChest);
                ThickAuraL = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraThick.effectId, ArmourLegs);
                ThickAuraF = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraThick.effectId, ArmourBoots);
            }
            if (Config.enchAuraToxicEnable) {
                ToxicAuraH = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraToxic.effectId, ArmourHelm);
                ToxicAuraT = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraToxic.effectId, ArmourChest);
                ToxicAuraL = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraToxic.effectId, ArmourLegs);
                ToxicAuraF = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraToxic.effectId, ArmourBoots);
            }
            if (Config.enchShroudEnable) {
                ShroudH = EnchantmentHelper.getEnchantmentLevel(Enchants.Shroud.effectId, ArmourHelm);
                ShroudT = EnchantmentHelper.getEnchantmentLevel(Enchants.Shroud.effectId, ArmourChest);
                ShroudL = EnchantmentHelper.getEnchantmentLevel(Enchants.Shroud.effectId, ArmourLegs);
                ShroudF = EnchantmentHelper.getEnchantmentLevel(Enchants.Shroud.effectId, ArmourBoots);
            }
            int shroud = ShroudF + ShroudH + ShroudL + ShroudT;
            if (DeadlyAuraH > 0 && DeadlyAuraT > 0 && DeadlyAuraL > 0 && DeadlyAuraF > 0){
                int level = (DeadlyAuraH + DeadlyAuraT + DeadlyAuraL + DeadlyAuraF)/4;
                double motionY = rand.nextGaussian() + 0.02D;
                if (shroud < 1)world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, player.posX + 0.5 + rand.nextFloat(), player.posY + 0.5 + rand.nextFloat(), player.posZ + 0.5 + rand.nextFloat(), 0, motionY, 0);
                List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox(player.serverPosX, player.serverPosY, player.serverPosZ, level));
                for (EntityLivingBase entity : (List<EntityLivingBase>)list){
                    if (entity != player && !entity.isDead && !(entity instanceof EntityAnimal))
                        entity.addPotionEffect(new PotionEffect(Potion.wither.getId(), 100, 1, true, false));
                }
            }
            if (DarkAuraH > 0 && DarkAuraT > 0 && DarkAuraL > 0 && DarkAuraF > 0){
                int level = (DarkAuraH + DarkAuraT + DarkAuraL + DarkAuraF)/4;
                double motionY = rand.nextGaussian() + 0.02D;
                if (shroud < 1)world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX + 0.5 + rand.nextFloat(), player.posY + 0.5 + rand.nextFloat(), player.posZ + 0.5 + rand.nextFloat(), 0, motionY, 0);
                List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox(player.serverPosX, player.serverPosY, player.serverPosZ, level));
                for (EntityLivingBase entity : (List<EntityLivingBase>)list){
                    if (entity != player && !entity.isDead && !(entity instanceof EntityAnimal)){
                        entity.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 100, 1, true, false));
                        entity.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 100, 1, true, false));
                    }
                }
            }
            if (FieryAuraH > 0 && FieryAuraT > 0 && FieryAuraL > 0 && FieryAuraF > 0){
                int level = (FieryAuraH + FieryAuraT + FieryAuraL + FieryAuraF)/4;
                double motionY = rand.nextGaussian() + 0.02D;
                if (shroud < 1)world.spawnParticle(EnumParticleTypes.FLAME, player.posX + 0.5 + rand.nextFloat(), player.posY + rand.nextFloat(), player.posZ + 0.5 + rand.nextFloat(), 0, motionY, 0);
                List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox(player.serverPosX, player.serverPosY, player.serverPosZ, level));
                for (EntityLivingBase entity : (List<EntityLivingBase>)list){
                    if (entity != player && !entity.isDead && !(entity instanceof EntityAnimal)){
                        entity.setFire(100);
                    }
                }
            }
            if (ThickAuraH > 0 && ThickAuraT > 0 && ThickAuraL > 0 && ThickAuraF > 0){
                int level = (ThickAuraH + ThickAuraT + ThickAuraL + ThickAuraF)/4;
                List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox(player.serverPosX, player.serverPosY, player.serverPosZ, level));
                for (EntityLivingBase entity : (List<EntityLivingBase>)list){
                    if (entity != player && !entity.isDead && !(entity instanceof EntityAnimal)){
                        entity.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 100, 1, true, false));
                        entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 100, 1, true, false));
                        entity.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 100, 1, true, false));
                    }
                }
            }
            if (ToxicAuraH > 0 && ToxicAuraT > 0 && ToxicAuraL > 0 && ToxicAuraF > 0){
                int level = (ToxicAuraH + ToxicAuraT + ToxicAuraL + ToxicAuraF)/4;
                double motionY = rand.nextGaussian() + 0.02D;
                if (shroud < 1)world.spawnParticle(EnumParticleTypes.SLIME, player.posX + 0.5 + rand.nextFloat(), player.posY + 0.5 + rand.nextFloat(), player.posZ + 0.5 + rand.nextFloat(), 0, motionY, 0);
                List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox(player.serverPosX, player.serverPosY, player.serverPosZ, level));
                for (EntityLivingBase entity : (List<EntityLivingBase>)list){
                    if (entity != player && !entity.isDead && !(entity instanceof EntityAnimal)){
                        entity.addPotionEffect(new PotionEffect(Potion.poison.getId(), 500, 1, true, false));
                    }
                }
            }
        }
	}
}
