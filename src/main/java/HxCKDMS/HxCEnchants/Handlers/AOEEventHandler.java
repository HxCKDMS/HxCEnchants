package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCCore.api.Utils.AABBUtils;
import HxCKDMS.HxCEnchants.Config;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

public class AOEEventHandler {
    int[] DeadlyAura = new int[4], FieryAura = new int[4],
            ToxicAura = new int[4], ThickAura = new int[4],
            DarkAura = new int[4];

    private int tickTimer = Config.updateTime;
    @SubscribeEvent
    @SuppressWarnings({"unused", "ConstantConditions", "unchecked"})
    public void playerTickEvent(TickEvent.PlayerTickEvent event) {
        tickTimer--;
        if (tickTimer <= 0) {
            tickTimer = Config.updateTime;
            EntityPlayer player = event.player;
            World world = player.getEntityWorld();

            for (int i = 0; i < 4; i++) {
                if (player.inventory.armorItemInSlot(i) != null) {
                    if (Config.enchAuraDeadlyEnable)
                        DeadlyAura[i] = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraDeadly.effectId, player.inventory.armorItemInSlot(i));
                    if (Config.enchAuraDarkEnable)
                        DarkAura[i] = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraDark.effectId, player.inventory.armorItemInSlot(i));
                    if (Config.enchAuraFieryEnable)
                        FieryAura[i] = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraFiery.effectId, player.inventory.armorItemInSlot(i));
                    if (Config.enchAuraThickEnable)
                        ThickAura[i] = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraThick.effectId, player.inventory.armorItemInSlot(i));
                    if (Config.enchAuraToxicEnable)
                        ToxicAura[i] = EnchantmentHelper.getEnchantmentLevel(Enchants.AuraToxic.effectId, player.inventory.armorItemInSlot(i));
                }
            }

            if (DeadlyAura[0] > 0 && DeadlyAura[1] > 0 && DeadlyAura[2] > 0 && DeadlyAura[3] > 0) {
                int level = (DeadlyAura[0] + DeadlyAura[1] + DeadlyAura[2] + DeadlyAura[3]) / 4;
                double motionY = world.rand.nextGaussian() + 0.02D;
                List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox(player.serverPosX, player.serverPosY, player.serverPosZ, level));
                for (EntityLivingBase entity : (List<EntityLivingBase>) list) {
                    if (entity != player && !entity.isDead && !(entity instanceof EntityAnimal))
                        entity.addPotionEffect(new PotionEffect(Potion.wither.getId(), 100, 1, true, false));
                }
            }
            if (DarkAura[0] > 0 && DarkAura[1] > 0 && DarkAura[2] > 0 && DarkAura[3] > 0) {
                int level = (DarkAura[0] + DarkAura[1] + DarkAura[2] + DarkAura[3]) / 4;
                double motionY = world.rand.nextGaussian() + 0.02D;
                List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox(player.serverPosX, player.serverPosY, player.serverPosZ, level));
                for (EntityLivingBase entity : (List<EntityLivingBase>) list)
                    if (entity != player && !entity.isDead && !(entity instanceof EntityAnimal)) {
                        entity.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 100, 1, true, false));
                        entity.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 100, 1, true, false));
                    }
            }
            if (FieryAura[0] > 0 && FieryAura[1] > 0 && FieryAura[2] > 0 && FieryAura[3] > 0) {
                int level = (FieryAura[0] + FieryAura[1] + FieryAura[2] + FieryAura[3]) / 4;
                double motionY = world.rand.nextGaussian() + 0.02D;
                List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox(player.serverPosX, player.serverPosY, player.serverPosZ, level));
                for (EntityLivingBase entity : (List<EntityLivingBase>) list)
                    if (entity != player && !entity.isDead && !(entity instanceof EntityAnimal))
                        entity.setFire(100);
            }
            if (ThickAura[0] > 0 && ThickAura[1] > 0 && ThickAura[2] > 0 && ThickAura[3] > 0) {
                int level = (ThickAura[0] + ThickAura[1] + ThickAura[2] + ThickAura[3]) / 4;
                List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox(player.serverPosX, player.serverPosY, player.serverPosZ, level));
                for (EntityLivingBase entity : (List<EntityLivingBase>) list)
                    if (entity != player && !entity.isDead && !(entity instanceof EntityAnimal)) {
                        entity.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 100, 1, true, false));
                        entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 100, 1, true, false));
                        entity.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 100, 1, true, false));
                    }
            }
            if (ToxicAura[0] > 0 && ToxicAura[1] > 0 && ToxicAura[2] > 0 && ToxicAura[3] > 0) {
                int level = (ToxicAura[0] + ToxicAura[1] + ToxicAura[2] + ToxicAura[3]) / 4;
                double motionY = world.rand.nextGaussian() + 0.02D;
                List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox(player.serverPosX, player.serverPosY, player.serverPosZ, level));
                for (EntityLivingBase entity : (List<EntityLivingBase>) list)
                    if (entity != player && !entity.isDead && !(entity instanceof EntityAnimal))
                        entity.addPotionEffect(new PotionEffect(Potion.poison.getId(), 500, 1, true, false));
            }
        }
	}
}
