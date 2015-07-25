package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCCore.api.Utils.AABBUtils;
import HxCKDMS.HxCEnchants.Config;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.List;

public class AOEEventHandler {
    int[] DeadlyAura = new int[4], FieryAura = new int[4],
            ToxicAura = new int[4], ThickAura = new int[4],
            DarkAura = new int[4], Chrgs, Shroud = new int[4];

    @SubscribeEvent
    @SuppressWarnings({"unused", "ConstantConditions", "unchecked"})
    public void playerTickEvent(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        World world = player.getEntityWorld();
        Chrgs = new int[]{0,0,0,0};

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
//                    if (Config.enchShroudEnable)
//                        Shroud[i] = EnchantmentHelper.getEnchantmentLevel(Enchants.Shroud.effectId, player.inventory.armorItemInSlot(i));
                if (player.inventory.armorItemInSlot(i).getTagCompound() != null && Config.enableChargesSystem)
                    Chrgs[i] = player.inventory.armorItemInSlot(i).getTagCompound().getInteger("HxCEnchantCharge");
            }
        }

        if (DeadlyAura[0] > 0 && DeadlyAura[1] > 0 && DeadlyAura[2] > 0 && DeadlyAura[3] > 0 && ((Chrgs[0] > Config.enchAuraDeadlyVals[4] && Chrgs[1] > Config.enchAuraDeadlyVals[4] && Chrgs[2] > Config.enchAuraDeadlyVals[4] && Chrgs[3] > Config.enchAuraDeadlyVals[4]) || !Config.enableChargesSystem)) {
            int level = (DeadlyAura[0] + DeadlyAura[1] + DeadlyAura[2] + DeadlyAura[3]) / 4;
            double motionY = world.rand.nextGaussian() + 0.02D;
//                if (shroud < 1)world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, player.posX + 0.5 + rand.nextFloat(), player.posY + 0.5 + rand.nextFloat(), player.posZ + 0.5 + rand.nextFloat(), 0, motionY, 0);
            List<EntityLivingBase> list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox((int)Math.round(player.posX), (int)Math.round(player.posY), (int)Math.round(player.posZ), level));
            for (EntityLivingBase entity : list)
                if ((Config.PAuraDeadly || !(entity instanceof EntityPlayer)) && entity != player && !entity.isDead && !(entity instanceof EntityAnimal)) {
                    entity.addPotionEffect(new PotionEffect(Potion.wither.getId(), 100, 1, true));
                    for (int i = 0; i < 4; i++)
                        player.inventory.armorItemInSlot(i).getTagCompound().setInteger("HxCEnchantCharge", Chrgs[i] - DeadlyAura[i]);
                }
        }

        if (DarkAura[0] > 0 && DarkAura[1] > 0 && DarkAura[2] > 0 && DarkAura[3] > 0 && ((Chrgs[0] > Config.enchAuraDarkVals[4] && Chrgs[1] > Config.enchAuraDarkVals[4] && Chrgs[2] > Config.enchAuraDarkVals[4] && Chrgs[3] > Config.enchAuraDarkVals[4]) || !Config.enableChargesSystem)) {
            int level = (DarkAura[0] + DarkAura[1] + DarkAura[2] + DarkAura[3]) / 4;
            double motionY = world.rand.nextGaussian() + 0.02D;
//                if (shroud < 1)world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX + 0.5 + rand.nextFloat(), player.posY + 0.5 + rand.nextFloat(), player.posZ + 0.5 + rand.nextFloat(), 0, motionY, 0);
            List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox((int)Math.round(player.posX), (int)Math.round(player.posY), (int)Math.round(player.posZ), level));
            for (EntityLivingBase entity : (List<EntityLivingBase>) list)
                if ((Config.PAuraDark || !(entity instanceof EntityPlayer)) && entity != player && !entity.isDead && !(entity instanceof EntityAnimal)) {
                    entity.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 100, 1, true));
                    entity.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 100, 1, true));
                    for (int i = 0; i < 4; i++)
                        player.inventory.armorItemInSlot(i).getTagCompound().setInteger("HxCEnchantCharge", Chrgs[i] - DarkAura[i]);
                }
        }

        if (FieryAura[0] > 0 && FieryAura[1] > 0 && FieryAura[2] > 0 && FieryAura[3] > 0 && ((Chrgs[0] > Config.enchAuraFieryVals[4] && Chrgs[1] > Config.enchAuraFieryVals[4] && Chrgs[2] > Config.enchAuraFieryVals[4] && Chrgs[3] > Config.enchAuraFieryVals[4]) || !Config.enableChargesSystem)) {
            int level = (FieryAura[0] + FieryAura[1] + FieryAura[2] + FieryAura[3]) / 4;
            double motionY = world.rand.nextGaussian() + 0.02D;
//                if (shroud < 1)world.spawnParticle(EnumParticleTypes.FLAME, player.posX + 0.5 + rand.nextFloat(), player.posY + rand.nextFloat(), player.posZ + 0.5 + rand.nextFloat(), 0, motionY, 0);
            List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox((int)Math.round(player.posX), (int)Math.round(player.posY), (int)Math.round(player.posZ), level));
            for (EntityLivingBase entity : (List<EntityLivingBase>) list)
                if ((Config.PAuraFiery || !(entity instanceof EntityPlayer)) && entity != player && !entity.isDead && !(entity instanceof EntityAnimal)) {
                    entity.setFire(100);
                    for (int i = 0; i < 4; i++)
                        player.inventory.armorItemInSlot(i).getTagCompound().setInteger("HxCEnchantCharge", Chrgs[i] - FieryAura[i]);
                }
        }

        if (ThickAura[0] > 0 && ThickAura[1] > 0 && ThickAura[2] > 0 && ThickAura[3] > 0 && ((Chrgs[0] > Config.enchAuraThickVals[4] && Chrgs[1] > Config.enchAuraThickVals[4] && Chrgs[2] > Config.enchAuraThickVals[4] && Chrgs[3] > Config.enchAuraThickVals[4]) || !Config.enableChargesSystem)) {
            int level = (ThickAura[0] + ThickAura[1] + ThickAura[2] + ThickAura[3]) / 4;
            List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox((int)Math.round(player.posX), (int)Math.round(player.posY), (int)Math.round(player.posZ), level));
            for (EntityLivingBase entity : (List<EntityLivingBase>) list)
                if ((Config.PAuraThick || !(entity instanceof EntityPlayer)) && entity != player && !entity.isDead && !(entity instanceof EntityAnimal)) {
                    entity.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 100, 1, true));
                    entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 100, 1, true));
                    entity.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 100, 1, true));
                    for (int i = 0; i < 4; i++)
                        player.inventory.armorItemInSlot(i).getTagCompound().setInteger("HxCEnchantCharge", Chrgs[i] - ThickAura[i]);
                }
        }

        if (ToxicAura[0] > 0 && ToxicAura[1] > 0 && ToxicAura[2] > 0 && ToxicAura[3] > 0 && ((Chrgs[0] > Config.enchAuraToxicVals[4] && Chrgs[1] > Config.enchAuraToxicVals[4] && Chrgs[2] > Config.enchAuraToxicVals[4] && Chrgs[3] > Config.enchAuraToxicVals[4]) || !Config.enableChargesSystem)) {
            int level = (ToxicAura[0] + ToxicAura[1] + ToxicAura[2] + ToxicAura[3]) / 4;
            double motionY = world.rand.nextGaussian() + 0.02D;
//                if (shroud < 1)world.spawnParticle(EnumParticleTypes.SLIME, player.posX + 0.5 + rand.nextFloat(), player.posY + 0.5 + rand.nextFloat(), player.posZ + 0.5 + rand.nextFloat(), 0, motionY, 0);
            List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox((int)Math.round(player.posX), (int)Math.round(player.posY), (int)Math.round(player.posZ), level));
            for (EntityLivingBase entity : (List<EntityLivingBase>) list)
                if ((Config.PAuraToxic || !(entity instanceof EntityPlayer)) && entity != player && !entity.isDead && !(entity instanceof EntityAnimal)) {
                    entity.addPotionEffect(new PotionEffect(Potion.poison.getId(), 500, 1, true));
                    for (int i = 0; i < 4; i++)
                        player.inventory.armorItemInSlot(i).getTagCompound().setInteger("HxCEnchantCharge", Chrgs[i] - ToxicAura[i]);
                }
        }
    }
}
