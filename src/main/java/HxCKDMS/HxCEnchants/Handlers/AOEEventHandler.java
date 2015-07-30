package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCCore.api.Utils.AABBUtils;
import HxCKDMS.HxCEnchants.Configurations;
import HxCKDMS.HxCEnchants.EnchantConfigHandler;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("all")
public class AOEEventHandler {
    private short[] DeadlyAura = new short[4], FieryAura = new short[4],
            ToxicAura = new short[4], ThickAura = new short[4],
            DarkAura = new short[4], Shroud = new short[4],
            GaiaAura = new short[4], IcyAura = new short[4],
            HealingAura = new short[4], MagneticAura = new short[4],
            RepulsiveAura = new short[4];

    private int tickTimer = Configurations.updateTime;
    @SubscribeEvent
    public void playerTickEvent(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            tickTimer--;
            if (tickTimer <= 0) {
                tickTimer = Configurations.updateTime;
                EntityPlayer player = (EntityPlayer) event.entityLiving;

                if (player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(1) != null &&
                        player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(3) != null &&
                        player.inventory.armorItemInSlot(0).hasTagCompound() && player.inventory.armorItemInSlot(1).hasTagCompound() &&
                        player.inventory.armorItemInSlot(2).hasTagCompound() && player.inventory.armorItemInSlot(3).hasTagCompound() &&
                        player.inventory.armorItemInSlot(0).isItemEnchanted() && player.inventory.armorItemInSlot(1).isItemEnchanted() &&
                        player.inventory.armorItemInSlot(2).isItemEnchanted() && player.inventory.armorItemInSlot(3).isItemEnchanted()) {

                    World world = player.getEntityWorld();
                    long[] chrgs = new long[]{0, 0, 0, 0};

                    for (short i = 0; i < 4; i++) {
                        if (player.inventory.armorItemInSlot(i) != null) {
                            if (EnchantConfigHandler.isEnabled("AuraDeadly", "armor"))
                                DeadlyAura[i] = (short) EnchantmentHelper.getEnchantmentLevel(Enchants.AuraDeadly.effectId, player.inventory.armorItemInSlot(i));
                            if (EnchantConfigHandler.isEnabled("AuraDark", "armor"))
                                DarkAura[i] = (short) EnchantmentHelper.getEnchantmentLevel(Enchants.AuraDark.effectId, player.inventory.armorItemInSlot(i));
                            if (EnchantConfigHandler.isEnabled("AuraFiery", "armor"))
                                FieryAura[i] = (short) EnchantmentHelper.getEnchantmentLevel(Enchants.AuraFiery.effectId, player.inventory.armorItemInSlot(i));
                            if (EnchantConfigHandler.isEnabled("AuraThick", "armor"))
                                ThickAura[i] = (short) EnchantmentHelper.getEnchantmentLevel(Enchants.AuraThick.effectId, player.inventory.armorItemInSlot(i));
                            if (EnchantConfigHandler.isEnabled("AuraToxic", "armor"))
                                ToxicAura[i] = (short) EnchantmentHelper.getEnchantmentLevel(Enchants.AuraToxic.effectId, player.inventory.armorItemInSlot(i));
//                    if (EnchantConfigHandler.isEnabled("Shroud", "armor"))
//                        Shroud[i] = EnchantmentHelper.getEnchantmentLevel(Enchants.Shroud.effectId, player.inventory.armorItemInSlot(i));
                            if (EnchantConfigHandler.isEnabled("GaiaAura", "armor"))
                                GaiaAura[i] = (short) EnchantmentHelper.getEnchantmentLevel(Enchants.GaiaAura.effectId, player.inventory.armorItemInSlot(i));
                            if (EnchantConfigHandler.isEnabled("IcyAura", "armor"))
                                IcyAura[i] = (short) EnchantmentHelper.getEnchantmentLevel(Enchants.IcyAura.effectId, player.inventory.armorItemInSlot(i));
                            if (EnchantConfigHandler.isEnabled("HealingAura", "armor"))
                                HealingAura[i] = (short) EnchantmentHelper.getEnchantmentLevel(Enchants.HealingAura.effectId, player.inventory.armorItemInSlot(i));
                            if (EnchantConfigHandler.isEnabled("AuraMagnetic", "armor"))
                                MagneticAura[i] = (short) EnchantmentHelper.getEnchantmentLevel(Enchants.AuraMagnetic.effectId, player.inventory.armorItemInSlot(i));
                            if (EnchantConfigHandler.isEnabled("RepulsiveAura", "armor"))
                                RepulsiveAura[i] = (short) EnchantmentHelper.getEnchantmentLevel(Enchants.RepulsiveAura.effectId, player.inventory.armorItemInSlot(i));
                            if (player.inventory.armorItemInSlot(i).getTagCompound() != null && Configurations.enableChargesSystem)
                                chrgs[i] = player.inventory.armorItemInSlot(i).getTagCompound().getLong("HxCEnchantCharge");
                        }
                    }

                    if (DeadlyAura[0] > 0 && DeadlyAura[1] > 0 && DeadlyAura[2] > 0 && DeadlyAura[3] > 0 && ((chrgs[0] > EnchantConfigHandler.getData("AuraDeadly", "armor")[4] && chrgs[1] > EnchantConfigHandler.getData("AuraDeadly", "armor")[4] && chrgs[2] > EnchantConfigHandler.getData("AuraDeadly", "armor")[4] && chrgs[3] > EnchantConfigHandler.getData("AuraDeadly", "armor")[4]) || !Configurations.enableChargesSystem)) {
                        short level = (short) ((DeadlyAura[0] + DeadlyAura[1] + DeadlyAura[2] + DeadlyAura[3]) / 4);
                        double motionY = world.rand.nextGaussian() + 0.02D;
//                if (shroud < 1)world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, player.posX + 0.5 + rand.nextFloat(), player.posY + 0.5 + rand.nextFloat(), player.posZ + 0.5 + rand.nextFloat(), 0, motionY, 0);
                        List<EntityLivingBase> list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox((short) Math.round(player.posX), (short) Math.round(player.posY), (short) Math.round(player.posZ), level));
                        for (EntityLivingBase entity : list)
                            if ((Configurations.PlayerAuraDeadly || !(entity instanceof EntityPlayer)) && entity != player && !(entity instanceof EntityGolem) && !entity.isDead && !(entity instanceof EntityAnimal) && !entity.isPotionActive(Potion.wither)) {
                                entity.addPotionEffect(new PotionEffect(Potion.wither.getId(), 100, 1, true));
                                if (Configurations.enableChargesSystem)
                                    for (short i = 0; i < 4; i++)
                                        player.inventory.armorItemInSlot(i).getTagCompound().setLong("HxCEnchantCharge", chrgs[i] - DeadlyAura[i]);
                            }
                    }

                    if (DarkAura[0] > 0 && DarkAura[1] > 0 && DarkAura[2] > 0 && DarkAura[3] > 0 && ((chrgs[0] > EnchantConfigHandler.getData("AuraDark", "armor")[4] && chrgs[1] > EnchantConfigHandler.getData("AuraDark", "armor")[4] && chrgs[2] > EnchantConfigHandler.getData("AuraDark", "armor")[4] && chrgs[3] > EnchantConfigHandler.getData("AuraDark", "armor")[4]) || !Configurations.enableChargesSystem)) {
                        short level = (short) ((DarkAura[0] + DarkAura[1] + DarkAura[2] + DarkAura[3]) / 4);
                        double motionY = world.rand.nextGaussian() + 0.02D;
//                if (shroud < 1)world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX + 0.5 + rand.nextFloat(), player.posY + 0.5 + rand.nextFloat(), player.posZ + 0.5 + rand.nextFloat(), 0, motionY, 0);
                        List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox((short) Math.round(player.posX), (short) Math.round(player.posY), (short) Math.round(player.posZ), level));
                        for (EntityLivingBase entity : (List<EntityLivingBase>) list)
                            if ((Configurations.PlayerAuraDark || !(entity instanceof EntityPlayer)) && entity != player && !(entity instanceof EntityGolem) && !entity.isDead && !(entity instanceof EntityAnimal) && !entity.isPotionActive(Potion.blindness)) {
                                entity.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 100, 1, true));
                                entity.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 100, 1, true));
                                if (Configurations.enableChargesSystem)
                                    for (short i = 0; i < 4; i++)
                                        player.inventory.armorItemInSlot(i).getTagCompound().setLong("HxCEnchantCharge", chrgs[i] - DarkAura[i]);
                            }
                    }

                    if (FieryAura[0] > 0 && FieryAura[1] > 0 && FieryAura[2] > 0 && FieryAura[3] > 0 && ((chrgs[0] > EnchantConfigHandler.getData("AuraFiery", "armor")[4] && chrgs[1] > EnchantConfigHandler.getData("AuraFiery", "armor")[4] && chrgs[2] > EnchantConfigHandler.getData("AuraFiery", "armor")[4] && chrgs[3] > EnchantConfigHandler.getData("AuraFiery", "armor")[4]) || !Configurations.enableChargesSystem)) {
                        short level = (short) ((FieryAura[0] + FieryAura[1] + FieryAura[2] + FieryAura[3]) / 4);
                        double motionY = world.rand.nextGaussian() + 0.02D;
//                if (shroud < 1)world.spawnParticle(EnumParticleTypes.FLAME, player.posX + 0.5 + rand.nextFloat(), player.posY + rand.nextFloat(), player.posZ + 0.5 + rand.nextFloat(), 0, motionY, 0);
                        List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox((short) Math.round(player.posX), (short) Math.round(player.posY), (short) Math.round(player.posZ), level));
                        for (EntityLivingBase entity : (List<EntityLivingBase>) list)
                            if ((Configurations.PlayerAuraFiery || !(entity instanceof EntityPlayer)) && entity != player && !(entity instanceof EntityGolem) && !entity.isDead && !(entity instanceof EntityAnimal) && !entity.isBurning()) {
                                entity.setFire(100);
                                if (Configurations.enableChargesSystem)
                                    for (short i = 0; i < 4; i++)
                                        player.inventory.armorItemInSlot(i).getTagCompound().setLong("HxCEnchantCharge", chrgs[i] - FieryAura[i]);
                            }
                    }

                    if (ThickAura[0] > 0 && ThickAura[1] > 0 && ThickAura[2] > 0 && ThickAura[3] > 0 && ((chrgs[0] > EnchantConfigHandler.getData("AuraThick", "armor")[4] && chrgs[1] > EnchantConfigHandler.getData("AuraThick", "armor")[4] && chrgs[2] > EnchantConfigHandler.getData("AuraThick", "armor")[4] && chrgs[3] > EnchantConfigHandler.getData("AuraThick", "armor")[4]) || !Configurations.enableChargesSystem)) {
                        short level = (short) ((ThickAura[0] + ThickAura[1] + ThickAura[2] + ThickAura[3]) / 4);
                        List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox((short) Math.round(player.posX), (short) Math.round(player.posY), (short) Math.round(player.posZ), level));
                        for (EntityLivingBase entity : (List<EntityLivingBase>) list)
                            if ((Configurations.PlayerAuraThick || !(entity instanceof EntityPlayer)) && entity != player && !(entity instanceof EntityGolem) && !entity.isDead && !(entity instanceof EntityAnimal) && !entity.isPotionActive(Potion.moveSlowdown)) {
                                entity.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 100, 1, true));
                                entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 100, 1, true));
                                entity.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 100, 1, true));
                                if (Configurations.enableChargesSystem)
                                    for (short i = 0; i < 4; i++)
                                        player.inventory.armorItemInSlot(i).getTagCompound().setLong("HxCEnchantCharge", chrgs[i] - ThickAura[i]);
                            }
                    }

                    if (ToxicAura[0] > 0 && ToxicAura[1] > 0 && ToxicAura[2] > 0 && ToxicAura[3] > 0 && ((chrgs[0] > EnchantConfigHandler.getData("AuraToxic", "armor")[4] && chrgs[1] > EnchantConfigHandler.getData("AuraToxic", "armor")[4] && chrgs[2] > EnchantConfigHandler.getData("AuraToxic", "armor")[4] && chrgs[3] > EnchantConfigHandler.getData("AuraToxic", "armor")[4]) || !Configurations.enableChargesSystem)) {
                        short level = (short) ((ToxicAura[0] + ToxicAura[1] + ToxicAura[2] + ToxicAura[3]) / 4);
                        double motionY = world.rand.nextGaussian() + 0.02D;
//                if (shroud < 1)world.spawnParticle(EnumParticleTypes.SLIME, player.posX + 0.5 + rand.nextFloat(), player.posY + 0.5 + rand.nextFloat(), player.posZ + 0.5 + rand.nextFloat(), 0, motionY, 0);
                        List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox((short) Math.round(player.posX), (short) Math.round(player.posY), (short) Math.round(player.posZ), level));
                        for (EntityLivingBase entity : (List<EntityLivingBase>) list)
                            if ((Configurations.PlayerAuraToxic || !(entity instanceof EntityPlayer)) && entity != player && !(entity instanceof EntityGolem) && !entity.isDead && !(entity instanceof EntityAnimal) && !entity.isPotionActive(Potion.poison)) {
                                entity.addPotionEffect(new PotionEffect(Potion.poison.getId(), 500, 1, true));
                                if (Configurations.enableChargesSystem)
                                    for (short i = 0; i < 4; i++)
                                        player.inventory.armorItemInSlot(i).getTagCompound().setLong("HxCEnchantCharge", chrgs[i] - ToxicAura[i]);
                            }
                    }

                    if (GaiaAura[0] > 0 && GaiaAura[1] > 0 && GaiaAura[2] > 0 && GaiaAura[3] > 0 && ((chrgs[0] > EnchantConfigHandler.getData("GaiaAura", "armor")[4] && chrgs[1] > EnchantConfigHandler.getData("GaiaAura", "armor")[4] && chrgs[2] > EnchantConfigHandler.getData("GaiaAura", "armor")[4] && chrgs[3] > EnchantConfigHandler.getData("GaiaAura", "armor")[4]) || !Configurations.enableChargesSystem)) {
                        short level = (short) ((GaiaAura[0] + GaiaAura[1] + GaiaAura[2] + GaiaAura[3]) / 4);
                        int ran = world.rand.nextInt(Math.round(100 / level));
                        if (ran == 0) {
                            List<ChunkPosition> crops = getCropsWithinAABB(player.worldObj, AABBUtils.getAreaBoundingBox((short) Math.round(player.posX), (short) Math.round(player.posY), (short) Math.round(player.posZ), level));
                            for (ChunkPosition pos : crops) {
                                player.worldObj.getBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ).updateTick(player.worldObj, pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ, new Random());
                                if (Configurations.enableChargesSystem)
                                    for (short i = 0; i < 4; i++)
                                        player.inventory.armorItemInSlot(i).getTagCompound().setLong("HxCEnchantCharge", chrgs[i] - EnchantConfigHandler.getData("GaiaAura", "armor")[4]);
                            }
                        }
                    }

                    if (HealingAura[0] > 0 && HealingAura[1] > 0 && HealingAura[2] > 0 && HealingAura[3] > 0 && ((chrgs[0] > EnchantConfigHandler.getData("HealingAura", "armor")[4] && chrgs[1] > EnchantConfigHandler.getData("HealingAura", "armor")[4] && chrgs[2] > EnchantConfigHandler.getData("HealingAura", "armor")[4] && chrgs[3] > EnchantConfigHandler.getData("HealingAura", "armor")[4]) || !Configurations.enableChargesSystem)) {
                        short level = (short) ((HealingAura[0] + HealingAura[1] + HealingAura[2] + HealingAura[3]) / 4);
                        double motionY = world.rand.nextGaussian() + 0.02D;
                        List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox((short) Math.round(player.posX), (short) Math.round(player.posY), (short) Math.round(player.posZ), level));
                        for (EntityLivingBase entity : (List<EntityLivingBase>) list)
                            if ((Configurations.PlayerHealingAura || !(entity instanceof EntityPlayer)) && entity != player && !(entity instanceof EntityMob) && !entity.isDead && !entity.isPotionActive(Potion.regeneration)) {
                                entity.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 500, Math.round(level / 3), true));
                                if (Configurations.enableChargesSystem)
                                    for (short i = 0; i < 4; i++)
                                        player.inventory.armorItemInSlot(i).getTagCompound().setLong("HxCEnchantCharge", chrgs[i] - HealingAura[i]);
                            }
                    }

                    if (IcyAura[0] > 0 && IcyAura[1] > 0 && IcyAura[2] > 0 && IcyAura[3] > 0 && ((chrgs[0] > EnchantConfigHandler.getData("IcyAura", "armor")[4] && chrgs[1] > EnchantConfigHandler.getData("IcyAura", "armor")[4] && chrgs[2] > EnchantConfigHandler.getData("IcyAura", "armor")[4] && chrgs[3] > EnchantConfigHandler.getData("IcyAura", "armor")[4]) || !Configurations.enableChargesSystem)) {
                        short level = (short) ((IcyAura[0] + IcyAura[1] + IcyAura[2] + IcyAura[3]) / 4);
                        List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox((short) Math.round(player.posX), (short) Math.round(player.posY), (short) Math.round(player.posZ), level));
                        List<ChunkPosition> blocks = getFreezablesWithinAABB(player.worldObj, AABBUtils.getAreaBoundingBox((short) Math.round(player.posX), (short) Math.round(player.posY), (short) Math.round(player.posZ), level));
                        for (EntityLivingBase entity : (List<EntityLivingBase>) list)
                            if ((Configurations.PlayerIcyAura || !(entity instanceof EntityPlayer)) && entity != player && !(entity instanceof EntityGolem) && !entity.isDead && !(entity instanceof EntityAnimal) && !entity.isPotionActive(Potion.poison)) {
                                entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 500, 1, true));
                                if (Configurations.enableChargesSystem)
                                    for (short i = 0; i < 4; i++)
                                        player.inventory.armorItemInSlot(i).getTagCompound().setLong("HxCEnchantCharge", chrgs[i] - IcyAura[i]);
                            }
                        for (ChunkPosition pos : blocks) {
                            if (world.getBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ) == Blocks.lava)
                                world.setBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ, Blocks.obsidian);
                            if (world.getBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ) == Blocks.flowing_lava)
                                world.setBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ, Blocks.cobblestone);
                            if (world.getBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ) == Blocks.water)
                                world.setBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ, Blocks.ice);
                        }
                    }

                    if (MagneticAura[0] > 0 && MagneticAura[1] > 0 && MagneticAura[2] > 0 && MagneticAura[3] > 0 && ((chrgs[0] > EnchantConfigHandler.getData("AuraMagnetic", "armor")[4] && chrgs[1] > EnchantConfigHandler.getData("AuraMagnetic", "armor")[4] && chrgs[2] > EnchantConfigHandler.getData("AuraMagnetic", "armor")[4] && chrgs[3] > EnchantConfigHandler.getData("AuraMagnetic", "armor")[4]) || !Configurations.enableChargesSystem)) {
                        short level = (short) ((MagneticAura[0] + MagneticAura[1] + MagneticAura[2] + MagneticAura[3]) / 4);
                        List items = player.worldObj.getEntitiesWithinAABB(EntityItem.class, AABBUtils.getAreaBoundingBox((short) Math.round(player.posX), (short) Math.round(player.posY), (short) Math.round(player.posZ), level));
                        List exp = player.worldObj.getEntitiesWithinAABB(EntityXPOrb.class, AABBUtils.getAreaBoundingBox((short) Math.round(player.posX), (short) Math.round(player.posY), (short) Math.round(player.posZ), level));
                        for (EntityItem item : (List<EntityItem>) items) {
                            double motionX = player.posX - item.posX;
                            double motionY = player.boundingBox.minY + player.height - item.posY;
                            double motionZ = player.posZ - item.posZ;
                            item.setVelocity(motionX / 8, motionY / 8, motionZ / 8);
                            if (Configurations.enableChargesSystem)
                                for (short i = 0; i < 4; i++)
                                    player.inventory.armorItemInSlot(i).getTagCompound().setLong("HxCEnchantCharge", chrgs[i] - MagneticAura[i]);
                        }
                        for (EntityXPOrb xp : (List<EntityXPOrb>) exp) {
                            double motionX = player.posX - xp.posX;
                            double motionY = player.boundingBox.minY + player.height - xp.posY;
                            double motionZ = player.posZ - xp.posZ;
                            xp.setVelocity(motionX / 2, motionY / 2, motionZ / 2);
                            if (Configurations.enableChargesSystem)
                                for (short i = 0; i < 4; i++)
                                    player.inventory.armorItemInSlot(i).getTagCompound().setLong("HxCEnchantCharge", chrgs[i] - MagneticAura[i]);
                        }
                    }

                    if (RepulsiveAura[0] > 0 && RepulsiveAura[1] > 0 && RepulsiveAura[2] > 0 && RepulsiveAura[3] > 0 && ((chrgs[0] > EnchantConfigHandler.getData("RepulsiveAura", "armor")[4] && chrgs[1] > EnchantConfigHandler.getData("RepulsiveAura", "armor")[4] && chrgs[2] > EnchantConfigHandler.getData("RepulsiveAura", "armor")[4] && chrgs[3] > EnchantConfigHandler.getData("RepulsiveAura", "armor")[4]) || !Configurations.enableChargesSystem)) {
                        short level = (short) ((RepulsiveAura[0] + RepulsiveAura[1] + RepulsiveAura[2] + RepulsiveAura[3]) / 4);
                        List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox((short) Math.round(player.posX), (short) Math.round(player.posY), (short) Math.round(player.posZ), level));
                        for (EntityLivingBase ent : (List<EntityLivingBase>) list)
                            if (ent != player && !(ent instanceof EntityAnimal || ent instanceof EntityVillager || ent instanceof EntityGolem || ent instanceof EntityPlayer)) {
                                double motionX = player.posX - ent.posX;
                                double motionY = player.boundingBox.minY + player.height - ent.posY;
                                double motionZ = player.posZ - ent.posZ;
                                ent.setVelocity(-motionX / 8, -motionY / 8, -motionZ / 8);
                                if (Configurations.enableChargesSystem)
                                    for (short i = 0; i < 4; i++)
                                        player.inventory.armorItemInSlot(i).getTagCompound().setLong("HxCEnchantCharge", chrgs[i] - RepulsiveAura[i]);
                            }
                    }
                }
            }
        }
    }


    public static ArrayList<ChunkPosition> getCropsWithinAABB(World world, AxisAlignedBB box) {
        ArrayList<ChunkPosition> crops = new ArrayList();

        for(int x = (int)box.minX; (double)x <= box.maxX; ++x) {
            for(int y = (int)box.minY; (double)y <= box.maxY; ++y) {
                for(int z = (int)box.minZ; (double)z <= box.maxZ; ++z) {
                    Block block = world.getBlock(x, y, z);
                    if(block != null && (block instanceof BlockCrops || block instanceof IGrowable || block == Blocks.cactus || block instanceof IPlantable || block == Blocks.vine))
                        crops.add(new ChunkPosition(x, y, z));
                }
            }
        }
        return crops;
    }
    public static ArrayList<ChunkPosition> getFreezablesWithinAABB(World world, AxisAlignedBB box) {
        ArrayList<ChunkPosition> blocks = new ArrayList();

        for(int x = (int)box.minX; (double)x <= box.maxX; ++x) {
            for(int y = (int)box.minY; (double)y <= box.maxY; ++y) {
                for(int z = (int)box.minZ; (double)z <= box.maxZ; ++z) {
                    Block block = world.getBlock(x, y, z);
                    if(block != null && (block == Blocks.lava || block == Blocks.flowing_lava || block == Blocks.water))
                        blocks.add(new ChunkPosition(x, y, z));
                }
            }
        }
        return blocks;
    }
}
