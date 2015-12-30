package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCCore.Handlers.NBTFileIO;
import HxCKDMS.HxCCore.HxCCore;
import HxCKDMS.HxCCore.api.Utils.AABBUtils;
import HxCKDMS.HxCCore.api.Utils.Teleporter;
import HxCKDMS.HxCEnchants.Configurations;
import HxCKDMS.HxCEnchants.EnchantConfigHandler;
import HxCKDMS.HxCEnchants.api.HxCEnchantment;
import HxCKDMS.HxCEnchants.api.IEnchantHandler;
import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;

import java.io.File;
import java.util.*;

import static HxCKDMS.HxCEnchants.Configurations.*;
import static HxCKDMS.HxCEnchants.EnchantConfigHandler.getData;
import static HxCKDMS.HxCEnchants.EnchantConfigHandler.isEnabled;
import static HxCKDMS.HxCEnchants.lib.Reference.*;

@SuppressWarnings({"unchecked", "ConstantConditions"})
public class EnchantHandlers implements IEnchantHandler {
    private int repairTimer = 60, regenTimer = 60, flyTimer = 1200, swiftTimer = 600, vitTimer = 600, stealthTimer = 600;
    FurnaceRecipes furnaceRecipes = FurnaceRecipes.instance();

    public static boolean OverCharge = false, FlashButton = false;
    @Override
    public void handleHelmetEnchant(EntityPlayerMP player, ItemStack helmet, LinkedHashMap<Enchantment, Integer> enchants) {
        if (enchants.containsKey(Enchantment.enchantmentsList[getData("Gluttony", "armor")[0]]) && !Loader.isModLoaded("HungerOverhaul")) {
            short gluttony = (short)EnchantmentHelper.getEnchantmentLevel((int) EnchantConfigHandler.getData("Gluttony", "armor")[0], helmet);
            LinkedHashMap<Boolean, Item> tmp = hasFood(player);
            if (gluttony > 0 && !tmp.isEmpty() && player.getFoodStats().getFoodLevel() <= (gluttony / 2) + 5 && tmp.containsKey(true) && tmp.get(true) != null) {
                player.getFoodStats().addStats(((ItemFood) Items.apple).getHealAmount(new ItemStack(tmp.get(true))), ((ItemFood) Items.apple).getSaturationModifier(new ItemStack(tmp.get(true))));
                for (short slot = 0; slot < player.inventory.mainInventory.length; slot++) {
                    if (player.inventory.mainInventory[slot] != null && player.inventory.mainInventory[slot].getItem() instanceof ItemFood && player.inventory.mainInventory[slot].getItem() == tmp.get(true)) {
                        player.inventory.decrStackSize(slot, 1);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void handleChestplateEnchant(EntityPlayerMP player, ItemStack chestplate, LinkedHashMap<Enchantment, Integer> enchants) {
        if (enchants.containsKey(Enchantment.enchantmentsList[getData("Vitality", "armor")[0]])) {
            long charge = chestplate.getTagCompound().getInteger("HxCEnchantCharge");
            vitTimer--;
            IAttributeInstance ph = player.getEntityAttribute(SharedMonsterAttributes.maxHealth);
            short vitalityLevel = (short) EnchantmentHelper.getEnchantmentLevel((int)EnchantConfigHandler.getData("Vitality", "armor")[0], chestplate);
            double vitality = vitalityLevel * 0.5F;
            AttributeModifier HealthBuff = new AttributeModifier(HealthUUID, "HealthBuffedChestplate", vitality, 0);
            if (!ph.func_111122_c().contains(HealthBuff) && vitalityLevel != 0 && (charge > getData("Vitality", "armor")[4] || !enableChargesSystem))
                ph.applyModifier(HealthBuff);
            if (ph.func_111122_c().contains(HealthBuff) && vitalityLevel == 0)
                ph.removeModifier(HealthBuff);

            if (vitTimer <= 0 && enableChargesSystem && vitalityLevel > 0) {
                chestplate.getTagCompound().setLong("HxCEnchantCharge", charge - getData("Vitality", "armor")[4]);
                vitTimer = 600;
            }
        }
    }

    @Override
    public void handleLeggingEnchant(EntityPlayerMP player, ItemStack leggings, LinkedHashMap<Enchantment, Integer> enchants) {
        if (enchants.containsKey(Enchantment.enchantmentsList[getData("Swiftness", "armor")[0]])) {
            IAttributeInstance ps = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
            short speedLevel = (short) EnchantmentHelper.getEnchantmentLevel((int)EnchantConfigHandler.getData("Swiftness", "armor")[0], leggings);
            double speedBoost = speedLevel * 0.2;
            AttributeModifier SpeedBuff = new AttributeModifier(SpeedUUID, "SpeedBuffedPants", speedBoost, 0);
            if (!ps.func_111122_c().contains(SpeedBuff) && speedLevel != 0)
                ps.applyModifier(SpeedBuff);
            if (ps.func_111122_c().contains(SpeedBuff) && speedLevel == 0)
                ps.removeModifier(SpeedBuff);
        }
    }

    @Override
    public void handleBootEnchant(EntityPlayerMP player, ItemStack boots, LinkedHashMap<Enchantment, Integer> enchants) {
        String UUID = player.getUniqueID().toString();
        File CustomPlayerData = new File(HxCCore.HxCCoreDir, "HxC-" + UUID + ".dat");
        if (enchants.containsKey(Enchantment.enchantmentsList[getData("Fly", "armor")[0]]) &! NBTFileIO.getBoolean(CustomPlayerData, "flightEnc")) {
            NBTFileIO.setBoolean(CustomPlayerData, "fly", true);
            NBTFileIO.setBoolean(CustomPlayerData, "flightEnc", true);
        } else if (NBTFileIO.getBoolean(CustomPlayerData, "flightEnc") &! enchants.containsKey(Enchantment.enchantmentsList[getData("Fly", "armor")[0]])) {
            NBTFileIO.setBoolean(CustomPlayerData, "fly", false);
            NBTFileIO.setBoolean(CustomPlayerData, "flightEnc", false);
        }

        if (enchants.containsKey(Enchantment.enchantmentsList[getData("Stealth", "armor")[0]])) {
            Stealth(player, enchants.get(Enchantment.enchantmentsList[getData("Stealth", "armor")[0]]));
        }

        if (FlashButton && enchants.containsKey(Enchantment.enchantmentsList[getData("FlashStep", "armor")[0]])) {
            int FlashLevel = EnchantmentHelper.getEnchantmentLevel((int)EnchantConfigHandler.getData("FlashStep", "armor")[0], boots);
            if (FlashLevel > 0) {
                World world = player.worldObj;
                double vx, vy, vz, x, y, z;
                x = player.posX;
                y = player.posY;
                z = player.posZ;
                vx = player.getLookVec().xCoord;
                vy = player.getLookVec().yCoord;
                vz = player.getLookVec().zCoord;
                for (int i = 10; i < 10 + (3 * FlashLevel); i++) {
                    if (world.getBlock((int) Math.round(x + vx * i), (int) Math.round(y + vy * i), (int) Math.round(z + vz * i)) != Blocks.air && world.getBlock((int) Math.round(x + vx * i), (int) Math.round(y + vy * i) + 2, (int) Math.round(z + vz * i)) == Blocks.air)
                        player.playerNetServerHandler.setPlayerLocation((int) Math.round(x + vx * i), (int) Math.round(y + vy * i) + 2, (int) Math.round(z + vz * i), player.cameraYaw, player.cameraPitch);
                    else if (world.getBlock((int) Math.round(x + vx * i), (int) Math.round(y + i), (int) Math.round(z + vz * i)) != Blocks.air && world.getBlock((int) Math.round(x + vx * i), (int) Math.round(y + i) + 2, (int) Math.round(z + vz * i)) == Blocks.air)
                      player.playerNetServerHandler.setPlayerLocation((int) Math.round(x + vx * i), (int) Math.round(y + i) + 2, (int) Math.round(z + vz * i), player.cameraYaw, player.cameraPitch);
                    else if (world.getBlock((int) Math.round(x + vx * i), (int) Math.round(y + -i), (int) Math.round(z + vz * i)) != Blocks.air && world.getBlock((int) Math.round(x + vx * i), (int) Math.round(y + -i) + 2, (int) Math.round(z + vz * i)) == Blocks.air)
                      player.playerNetServerHandler.setPlayerLocation((int) Math.round(x + vx * i), (int) Math.round(y + -i) + 2, (int) Math.round(z + vz * i), player.cameraYaw, player.cameraPitch);
                }
            }
            FlashButton = false;
        }
        player.sendPlayerAbilities();
    }

    @Override
    public void handleDeathEvent(EntityPlayerMP player, EntityLivingBase victim, ItemStack stack) {
        short vampireLevel = (short) EnchantmentHelper.getEnchantmentLevel((int)EnchantConfigHandler.getData("Vampirism", "weapon")[0], stack);
        short examineLevel = (short) EnchantmentHelper.getEnchantmentLevel((int)EnchantConfigHandler.getData("Examine", "weapon")[0], stack);
        if (examineLevel > 0)
            if (victim instanceof EntityLiving) {
                victim.worldObj.spawnEntityInWorld(new EntityXPOrb(victim.worldObj, victim.posX, victim.posY + 1, victim.posZ, examineLevel * getData("Examine", "weapon")[4]));
            }

        if (vampireLevel > 0 && !Loader.isModLoaded("HungerOverhaul")) {
            if (victim instanceof EntityAnimal)
                player.getFoodStats().addStats(1, 0.3F);
            else if (victim instanceof EntityPlayerMP)
                player.getFoodStats().addStats(10, 0.5F);
            else if (victim instanceof EntityVillager)
                player.getFoodStats().addStats(5, 0.5F);
            else if (victim.isEntityUndead())
                player.getFoodStats().addStats(0, 0);
            else if (victim instanceof EntitySlime)
                player.getFoodStats().addStats(1, 0.1F);
            else if (victim instanceof EntityEnderman)
                player.getFoodStats().addStats(2, 0.2F);
            else if (victim instanceof EntityMob)
                player.getFoodStats().addStats(3, 0.2F);

            else player.getFoodStats().addStats(1, 0.1F);
        }
    }

    @Override
    public void playerTickEvent(EntityPlayerMP player) {
        if (isEnabled("OverCharge", "weapon") && player.getHeldItem() != null && (player.getHeldItem().getItem() instanceof ItemSword || player.getHeldItem().getItem() instanceof ItemAxe) && player.getHeldItem().isItemEnchanted() && player.getHeldItem().getTagCompound() != null) {
            long HeldCharges = 0;
            if (enableChargesSystem) {
                HeldCharges = player.getHeldItem().getTagCompound().getLong("HxCEnchantCharge");
            }
            boolean stored = player.getHeldItem().getTagCompound().getBoolean("StoredCharge");
            int temp = EnchantmentHelper.getEnchantmentLevel((int)EnchantConfigHandler.getData("OverCharge", "weapon")[0], player.getHeldItem()),
                    RequiredCharge = getData("OverCharge", "weapon")[4];
            if (temp > 0 && (HeldCharges >= RequiredCharge || !enableChargesSystem) && !stored) {
                if (OverCharge && player.getHeldItem().getTagCompound().getInteger("HxCOverCharge") != 0) {
                    player.addChatComponentMessage(new ChatComponentText("You have just stored a charge of " + player.getHeldItem().getTagCompound().getInteger("HxCOverCharge") + "!"));
                    player.getHeldItem().getTagCompound().setBoolean("StoredCharge", true);
                    OverCharge = false;
                    if (enableChargesSystem)
                        player.getHeldItem().getTagCompound().setLong("HxCEnchantCharge", HeldCharges - RequiredCharge);
                }

                if (!OverCharge && player.getHeldItem().getTagCompound().getInteger("HxCOverCharge") != 0 && HeldCharges >= RequiredCharge * 2) {
                    player.getHeldItem().getTagCompound().setInteger("HxCOverCharge", player.getHeldItem().getTagCompound().getInteger("HxCOverCharge") + 1);
                    if (enableChargesSystem)
                        player.getHeldItem().getTagCompound().setLong("HxCEnchantCharge", HeldCharges - RequiredCharge);
                }

                if (OverCharge && player.getHeldItem().getTagCompound().getInteger("HxCOverCharge") == 0 && HeldCharges >= RequiredCharge * 2) {
                    OverCharge = false;
                    player.getHeldItem().getTagCompound().setInteger("HxCOverCharge", player.getHeldItem().getTagCompound().getInteger("HxCOverCharge") + 1);
                    if (enableChargesSystem)
                        player.getHeldItem().getTagCompound().setLong("HxCEnchantCharge", HeldCharges - RequiredCharge);
                }
            }
        }

        if (player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).hasTagCompound() && player.inventory.armorItemInSlot(0).isItemEnchanted() && player.motionY < -0.8 && !player.isSneaking()) {
            int tmp = 0, tmp2 = 0;
            if (isEnabled("FeatherFall", "armor") && player.inventory.armorItemInSlot(0).getTagCompound().getLong("HxCEnchantCharge") > getData("FeatherFall", "armor")[4])
                tmp = EnchantmentHelper.getEnchantmentLevel((int)EnchantConfigHandler.getData("FeatherFall", "armor")[0], player.inventory.armorItemInSlot(0));
            if (isEnabled("MeteorFall", "armor") && player.inventory.armorItemInSlot(0).getTagCompound().getLong("HxCEnchantCharge") > getData("MeteorFall", "armor")[4])
                tmp2 = EnchantmentHelper.getEnchantmentLevel((int)EnchantConfigHandler.getData("MeteorFall", "armor")[0], player.inventory.armorItemInSlot(0));

            if (tmp > 0) {
                player.motionY += (0.01f * (tmp / 2));
                if (enableChargesSystem)
                    player.inventory.armorItemInSlot(0).getTagCompound().setLong("HxCEnchantCharge", player.inventory.armorItemInSlot(0).getTagCompound().getLong("HxCEnchantCharge") - getData("FeatherFall", "armor")[4]);
            }
            if (tmp2 > 0) {
                player.motionY -= (0.02f * tmp2);
                if (enableChargesSystem)
                    player.inventory.armorItemInSlot(0).getTagCompound().setLong("HxCEnchantCharge", player.inventory.armorItemInSlot(0).getTagCompound().getLong("HxCEnchantCharge") - getData("MeteorFall", "armor")[4]);
            }
        }

        if (player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(1).hasTagCompound() && player.inventory.armorItemInSlot(1).isItemEnchanted()) {
            int tmp = 0;
            if (isEnabled("Swiftness", "armor")) {
                tmp = EnchantmentHelper.getEnchantmentLevel((int) EnchantConfigHandler.getData("Swiftness", "armor")[0], player.inventory.armorItemInSlot(1));

                if (tmp == 0) {
                    IAttributeInstance ps = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
                    AttributeModifier SpeedBuff = new AttributeModifier(SpeedUUID, "SpeedBuffedPants", 0, 0);
                    ps.removeModifier(SpeedBuff);
                }
            }
        }
    }

    @Override
    public void delayedPlayerTickEvent(EntityPlayerMP player) {
        repairTimer--; regenTimer--;
        if (isEnabled("Repair", "other") && repairTimer <= 0) {
            RepairItems(player);
            repairTimer = Configurations.repairTimer;
        }

        if (isEnabled("Regen", "armor") && regenTimer <= 0) {
            short H = 0, C = 0, L = 0, B = 0, rid = EnchantConfigHandler.getData("Regen", "armor")[0];
            byte Regen = 0;
            if (player.inventory.armorItemInSlot(3) != null)
                H = (short) EnchantmentHelper.getEnchantmentLevel((int)rid, player.inventory.armorItemInSlot(3));
            if (player.inventory.armorItemInSlot(2) != null)
                C = (short) EnchantmentHelper.getEnchantmentLevel((int)rid, player.inventory.armorItemInSlot(2));
            if (player.inventory.armorItemInSlot(1) != null)
                L = (short) EnchantmentHelper.getEnchantmentLevel((int)rid, player.inventory.armorItemInSlot(1));
            if (player.inventory.armorItemInSlot(0) != null)
                B = (short) EnchantmentHelper.getEnchantmentLevel((int)rid, player.inventory.armorItemInSlot(0));

            if (H > 0) Regen += 1;
            if (B > 0) Regen += 1;
            if (C > 0) Regen += 1;
            if (L > 0) Regen += 1;

            ItemStack ArmourHelm = player.inventory.armorItemInSlot(3),
                    ArmourChest = player.inventory.armorItemInSlot(2),
                    ArmourLegs = player.inventory.armorItemInSlot(1),
                    ArmourBoots = player.inventory.armorItemInSlot(0);

            long HChrg = -1, CChrg = -1, LChrg = -1, BChrg = -1;
            if (enableChargesSystem) {
                if (ArmourHelm != null && ArmourHelm.getTagCompound() != null)
                    HChrg = ArmourHelm.getTagCompound().getLong("HxCEnchantCharge");
                if (ArmourChest != null && ArmourChest.getTagCompound() != null)
                    CChrg = ArmourChest.getTagCompound().getLong("HxCEnchantCharge");
                if (ArmourLegs != null && ArmourLegs.getTagCompound() != null)
                    LChrg = ArmourLegs.getTagCompound().getLong("HxCEnchantCharge");
                if (ArmourBoots != null && ArmourBoots.getTagCompound() != null)
                    BChrg = ArmourBoots.getTagCompound().getLong("HxCEnchantCharge");
            }

            if (player.getHealth() < player.getMaxHealth() && Regen > 0) {
                float hp = player.getMaxHealth() - player.getHealth();
                regenTimer = Configurations.regenTimer;
                if (H > 0 && (HChrg > (hp * 2) / Regen || !enableChargesSystem)) {
                    if (enableChargesSystem)
                        ArmourHelm.getTagCompound().setLong("HxCEnchantCharge", HChrg - H * getData("Regen", "armor")[4]);
                    player.heal(H / 2);
                }
                if (C > 0 && (CChrg > (hp * 2) / Regen || !enableChargesSystem)) {
                    if (enableChargesSystem)
                        ArmourChest.getTagCompound().setLong("HxCEnchantCharge", CChrg - C * getData("Regen", "armor")[4]);
                    player.heal(C / 2);
                }
                if (L > 0 && (LChrg > (hp * 2) / Regen || !enableChargesSystem)) {
                    if (enableChargesSystem)
                        ArmourLegs.getTagCompound().setLong("HxCEnchantCharge", LChrg - L * getData("Regen", "armor")[4]);
                    player.heal(L / 2);
                }
                if (B > 0 && (BChrg > (hp * 2) / Regen || !enableChargesSystem)) {
                    if (enableChargesSystem)
                        ArmourBoots.getTagCompound().setLong("HxCEnchantCharge", BChrg - B * getData("Regen", "armor")[4]);
                    player.heal(B / 2);
                }
            }
        }
    }

    @Override
    public void handleAuraEvent(EntityPlayerMP player, List<Entity> ents, LinkedHashMap<Enchantment, Integer> sharedEnchants) {
        if (enableChargesSystem) {
            final int[] tcr = {0};
            sharedEnchants.forEach((enchantment, level) -> {
                if (enchantment instanceof HxCEnchantment)
                    tcr[0] += (((HxCEnchantment)enchantment).getChargeRequirement() * level);
            });
            for (int i = 0; i < 4; i++) {
                long c = player.getCurrentArmor(i).getTagCompound().getLong("HxCCharge");
                if (tcr[0] > c) return;
                else player.getCurrentArmor(i).getTagCompound().setLong("HxCCharge", c - tcr[0]);
            }
        }

        World world = player.getEntityWorld();
        for (Entity entity : ents) {
            if (entity instanceof EntityLivingBase && entity != player && !entity.isDead) {
                if (sharedEnchants.keySet().contains(Enchantment.enchantmentsList[getData("AuraDeadly", "armor")[0]])) {
                    if ((PlayerAuraDeadly || !(entity instanceof EntityPlayer)) && !(entity instanceof EntityGolem) && !(entity instanceof EntityAnimal) && !((EntityLivingBase) entity).isPotionActive(Potion.wither)) {
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.wither.getId(), 100, 1, true));
                    }
                }

                if (sharedEnchants.keySet().contains(Enchantment.enchantmentsList[getData("AuraDark", "armor")[0]])) {
                    if ((PlayerAuraDark || !(entity instanceof EntityPlayer)) && !(entity instanceof EntityGolem) && !(entity instanceof EntityAnimal) && !((EntityLivingBase) entity).isPotionActive(Potion.blindness)) {
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.blindness.getId(), 100, 1, true));
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.confusion.getId(), 100, 1, true));
                    }
                }

                if (sharedEnchants.keySet().contains(Enchantment.enchantmentsList[getData("AuraFiery", "armor")[0]])) {
                    if ((PlayerAuraFiery || !(entity instanceof EntityPlayer)) && !(entity instanceof EntityGolem) && !(entity instanceof EntityAnimal) && !entity.isBurning()) {
                        entity.setFire(100);
                    }
                }

                if (sharedEnchants.keySet().contains(Enchantment.enchantmentsList[getData("AuraThick", "armor")[0]])) {
                    if ((PlayerAuraThick || !(entity instanceof EntityPlayer)) && !(entity instanceof EntityGolem) && !(entity instanceof EntityAnimal) && !((EntityLivingBase) entity).isPotionActive(Potion.moveSlowdown)) {
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 100, 1, true));
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 100, 1, true));
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.weakness.getId(), 100, 1, true));
                    }
                }

                if (sharedEnchants.keySet().contains(Enchantment.enchantmentsList[getData("AuraToxic", "armor")[0]])) {
                    if (entity instanceof EntityLivingBase && (PlayerAuraToxic || !(entity instanceof EntityPlayer)) && !(entity instanceof EntityGolem) && !(entity instanceof EntityAnimal) && !((EntityLivingBase) entity).isPotionActive(Potion.poison)) {
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.getId(), 500, 1, true));
                    }
                }

                if (sharedEnchants.keySet().contains(Enchantment.enchantmentsList[getData("HealingAura", "armor")[0]])) {
                    if ((PlayerHealingAura || !(entity instanceof EntityPlayer)) && !(entity instanceof EntityMob) && !((EntityLivingBase) entity).isPotionActive(Potion.regeneration)) {
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 500, Math.round(sharedEnchants.get(Enchantment.enchantmentsList[getData("GaiaAura", "armor")[0]])/4/3), true));
                    }
                }

                if (sharedEnchants.keySet().contains(Enchantment.enchantmentsList[getData("IcyAura", "armor")[0]])) {
                    if ((PlayerIcyAura || !(entity instanceof EntityPlayer)) && !(entity instanceof EntityGolem) && !(entity instanceof EntityAnimal) && !((EntityLivingBase) entity).isPotionActive(Potion.moveSlowdown)) {
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 500, 1, true));
                    }
                }

                if (sharedEnchants.keySet().contains(Enchantment.enchantmentsList[getData("ChargedAura", "armor")[0]])) {
                    if (!(entity instanceof EntityPlayer) && !(entity instanceof EntityGolem) && !(entity instanceof EntityAnimal)) {
                        world.addWeatherEffect(new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ));
                    }
                }

                if (sharedEnchants.keySet().contains(Enchantment.enchantmentsList[getData("RepulsiveAura", "armor")[0]])) {
                    if (!(entity instanceof EntityAnimal || entity instanceof EntityVillager || entity instanceof EntityGolem || entity instanceof EntityPlayer)) {
                        double motionX = player.posX - entity.posX;
                        double motionY = player.boundingBox.minY + player.height - entity.posY;
                        double motionZ = player.posZ - entity.posZ;
                        entity.setVelocity(-motionX / 8, -motionY / 8, -motionZ / 8);
                    }
                }
            }

            if (sharedEnchants.keySet().contains(Enchantment.enchantmentsList[getData("GaiaAura", "armor")[0]])) {
                int ran = world.rand.nextInt(Math.round(100 / (GaiasAuraSpeed * sharedEnchants.get(Enchantment.enchantmentsList[getData("GaiaAura", "armor")[0]]))));
                if (ran == 0) {
                    List<ChunkPosition> crops = getCropsWithinAABB(player.worldObj, AABBUtils.getAreaBoundingBox((short) Math.round(player.posX), (short) Math.round(player.posY), (short) Math.round(player.posZ), sharedEnchants.get(Enchantment.enchantmentsList[getData("GaiaAura", "armor")[0]])/4));
                    for (ChunkPosition pos : crops) {
                        player.worldObj.getBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ).updateTick(player.worldObj, pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ, new Random());
                    }
                }
            }

            if (sharedEnchants.keySet().contains(Enchantment.enchantmentsList[getData("IcyAura", "armor")[0]])) {
                List<ChunkPosition> blocks = getFreezablesWithinAABB(player.worldObj, AABBUtils.getAreaBoundingBox((short) Math.round(player.posX), (short) Math.round(player.posY), (short) Math.round(player.posZ), sharedEnchants.get(Enchantment.enchantmentsList[getData("GaiaAura", "armor")[0]])/4));
                for (ChunkPosition pos : blocks) {
                    if (world.getBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ) == Blocks.lava)
                        world.setBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ, Blocks.obsidian, 0, 3);
                    if (world.getBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ) == Blocks.flowing_lava)
                        world.setBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ, Blocks.stone, 0, 3);
                    if (world.getBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ) == Blocks.water)
                        world.setBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ, Blocks.ice, 0, 3);
                    if (world.getBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ) == Blocks.fire)
                        world.setBlockToAir(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ);
                }
            }

            if (sharedEnchants.keySet().contains(Enchantment.enchantmentsList[getData("AuraMagnetic", "armor")[0]])) {
                if (entity instanceof EntityXPOrb || entity instanceof EntityItem) {
                    double motionX = player.posX - entity.posX;
                    double motionY = player.boundingBox.minY + player.height - entity.posY;
                    double motionZ = player.posZ - entity.posZ;
                    entity.setVelocity(motionX / 4, motionY / 4, motionZ / 4);
                }
            }
        }
    }

    @Override
    public void handleAttackEvent(EntityPlayerMP player, EntityLivingBase victim, ItemStack weapon, float damage, LivingHurtEvent event, LinkedHashMap<Enchantment, Integer> enchants) {
        if (enableChargesSystem) {
            final long[] cr = new long[]{0, weapon.getTagCompound().getLong("HxCCharge")};
            enchants.forEach((enchant, level) -> {
                if (enchant instanceof HxCEnchantment) {
                    cr[0] += (((HxCEnchantment)enchant).getChargeRequirement() * level);
                }
            });
            if (cr[0] > cr[1]) return;
            else weapon.getTagCompound().setLong("HxCCharge", cr[1] - cr[0]);
        }

        if (enchants.containsKey(Enchantment.enchantmentsList[getData("LifeSteal", "weapon")[0]])) {
            player.heal(damage/10 * enchants.get(Enchantment.enchantmentsList[getData("LifeSteal", "weapon")[0]]));
        }

        if (enchants.containsKey(Enchantment.enchantmentsList[getData("Piercing", "weapon")[0]])) {
                victim.attackEntityFrom(new DamageSource("Piercing").setDamageBypassesArmor().setDamageAllowedInCreativeMode()
                        .setDamageIsAbsolute(), damage * (PiercingPercent * enchants.get(Enchantment.enchantmentsList[getData("Piercing", "weapon")[0]])));
        }

        if (enchants.containsKey(Enchantment.enchantmentsList[getData("Vorpal", "weapon")[0]])) {
            victim.attackEntityFrom(new DamageSource("Vorpal").setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute(),
                    enchants.get(Enchantment.enchantmentsList[getData("Vorpal", "weapon")[0]]) * 2);
        }

        if (enchants.containsKey(Enchantment.enchantmentsList[getData("SCurse", "weapon")[0]])) {
            int SCurseLevel = enchants.get(Enchantment.enchantmentsList[getData("SCurse", "weapon")[0]]);
            victim.attackEntityFrom(new DamageSource("scurse").setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute(), SCurseLevel*2);
            player.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 120 * SCurseLevel, SCurseLevel, true));
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 120, Math.round(SCurseLevel /3), true));
            player.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 120 * SCurseLevel, SCurseLevel, true));
        }

        if (enchants.containsKey(Enchantment.enchantmentsList[getData("OverCharge", "weapon")[0]]) && weapon.getTagCompound().getBoolean("StoredCharge")) {
            int OverChage = enchants.get(Enchantment.enchantmentsList[getData("OverCharge", "weapon")[0]]);
            int storedCharge = weapon.getTagCompound().getInteger("HxCOverCharge");
            if (OverChage > 0) {
                List<EntityLivingBase> list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox((int)Math.round(player.posX), (int)Math.round(player.posY), (int)Math.round(player.posZ), OverChage*5));
                int ndamage = storedCharge/list.size();
                list.stream().filter(liv -> liv != player).forEach(liv -> liv.attackEntityFrom(new DamageSource("OverCharge").setDamageIsAbsolute().setDamageAllowedInCreativeMode(), ndamage));
                weapon.getTagCompound().setInteger("HxCOverCharge", 0);
                weapon.getTagCompound().setBoolean("StoredCharge", false);
                Map<Integer, Integer> enchs = EnchantmentHelper.getEnchantments(weapon);
                enchs.remove((int)getData("OverCharge", "weapon")[0]);
                if (OverChage > 1) enchs.put((int) getData("OverCharge", "weapon")[0], OverChage - 1);
                EnchantmentHelper.setEnchantments(enchs, weapon);
            }
        }
    }

    @Override
    public void playerMineBlockEvent(EntityPlayer player, ItemStack tool, BlockEvent.HarvestDropsEvent event, LinkedHashMap<Enchantment, Integer> enchants) {
        if (isEnabled("FlameTouch", "other")) {
            int AutoSmeltLevel = (short)EnchantmentHelper.getEnchantmentLevel(getData("FlameTouch", "other")[0], tool);
            if (AutoSmeltLevel > 0 && (tool.getTagCompound().getLong("HxCEnchantCharge") > getData("FlameTouch", "other")[4] || !enableChargesSystem)) {
                for (int i = 0; i < event.drops.size(); i++) {
                    ItemStack smelted = furnaceRecipes.getSmeltingResult(event.drops.get(i));

                    if (smelted != null) {
                        ItemStack drop = smelted.copy();
                        drop.stackSize *= AutoSmeltLevel;
                        event.drops.set(i, drop);
                        if (enableChargesSystem)
                            tool.getTagCompound().setLong("HxCEnchantCharge", tool.getTagCompound().getLong("HxCEnchantCharge") - (getData("FlameTouch", "other")[4] * AutoSmeltLevel));
                    }
                }
            }
        }

        if (isEnabled("VoidTouch", "other")) {
            short voidLevel = (short) EnchantmentHelper.getEnchantmentLevel(getData("VoidTouch", "other")[0], tool);
            if (voidLevel > 0 && (tool.getTagCompound().getLong("HxCEnchantCharge") > getData("VoidTouch", "other")[4] || !enableChargesSystem)) {
                for(String block : VoidedItems)
                    event.drops.remove(new ItemStack(Block.getBlockFromName(block)));
                if (enableChargesSystem)
                    tool.getTagCompound().setLong("HxCEnchantCharge", tool.getTagCompound().getLong("HxCEnchantCharge") - (getData("VoidTouch", "other")[4] * voidLevel));
            }
        }
    }

    @Override
    public void playerHurtEvent(EntityPlayerMP player, DamageSource source, float ammount, LivingHurtEvent event) {
        boolean allowEffect = !(source.damageType.equalsIgnoreCase("wither") ||
                source.damageType.equalsIgnoreCase("starve") ||
                source.damageType.equalsIgnoreCase("fall") ||
                source.damageType.equalsIgnoreCase("explosion.player") ||
                source.damageType.equalsIgnoreCase("explosion") ||
                source.damageType.equalsIgnoreCase("inWall") ||
                source.damageType.equalsIgnoreCase("poison"));

        ItemStack ArmourHelm = player.inventory.armorItemInSlot(3),
                ArmourChest = player.inventory.armorItemInSlot(2);

        int AdrenalineBoostLevel = 0, BattleHealingLevel = 0, WitherProt = 0, DivineInterventionLevel = 0, ExplosiveDischarge = 0;

        if (ArmourChest != null && ArmourChest.hasTagCompound() && ArmourChest.isItemEnchanted()) {
            if (isEnabled("BattleHealing", "armor"))
                BattleHealingLevel = EnchantmentHelper.getEnchantmentLevel(getData("BattleHealing", "armor")[0], ArmourChest);

            if (isEnabled("DivineIntervention", "armor"))
                DivineInterventionLevel = EnchantmentHelper.getEnchantmentLevel(getData("DivineIntervention", "armor")[0], ArmourChest);

            if (isEnabled("ExplosiveDischarge", "armor") && allowEffect)
                ExplosiveDischarge = EnchantmentHelper.getEnchantmentLevel(getData("ExplosiveDischarge", "armor")[0], ArmourChest);

            if (BattleHealingLevel > 0 && source.damageType.equalsIgnoreCase("generic") && (ArmourChest.getTagCompound().getLong("HxCEnchantCharge") > getData("BattleHealing", "armor")[4] || !enableChargesSystem)) {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), BattleHealingLevel * 60, BattleHealingLevel));
                if (enableChargesSystem)
                    ArmourChest.getTagCompound().setLong("HxCEnchantCharge", ArmourHelm.getTagCompound().getLong("HxCEnchantCharge") - getData("BattleHealing", "armor")[4]);
            }


            if (DivineInterventionLevel > 0 && player.prevHealth - ammount <= 1) {
                player.heal(5);
                int x, y, z;
                if (player.getBedLocation(0) != null) {
                    x = player.getBedLocation(0).posX;
                    y = player.getBedLocation(0).posY;
                    z = player.getBedLocation(0).posZ;
                } else {
                    ChunkCoordinates coords = HxCCore.server.worldServerForDimension(0).getSpawnPoint();
                    x = coords.posX;
                    y = coords.posY;
                    z = coords.posZ;
                }
                if (player.dimension != 0) Teleporter.transferPlayerToDimension(player, 0, x, y, z);
                else player.playerNetServerHandler.setPlayerLocation(x, y, z, 90, 0);
                Map<Integer, Integer> enchs = EnchantmentHelper.getEnchantments(ArmourChest);
                enchs.remove((int)getData("DivineIntervention", "armor")[0]);
                if (DivineInterventionLevel > 1) enchs.put((int)getData("DivineIntervention", "armor")[0], DivineInterventionLevel - 1);
                EnchantmentHelper.setEnchantments(enchs, ArmourChest);
            }

            if (ExplosiveDischarge > 0 && (ArmourChest.getTagCompound().getLong("HxCEnchantCharge") > getData("ExplosiveDischarge", "armor")[4] || !enableChargesSystem)) {
                player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, 2f * ExplosiveDischarge, false);
                if (enableChargesSystem)
                    ArmourChest.getTagCompound().setLong("HxCEnchantCharge", ArmourChest.getTagCompound().getLong("HxCEnchantCharge") - getData("ExplosiveDischarge", "armor")[4]);
            }
        }

        if (ArmourHelm != null && ArmourHelm.hasTagCompound() && ArmourHelm.isItemEnchanted()) {
            if (isEnabled("AdrenalineBoost", "armor"))
                AdrenalineBoostLevel = EnchantmentHelper.getEnchantmentLevel(getData("AdrenalineBoost", "armor")[0], ArmourHelm);

            if (isEnabled("WitherProtection", "armor"))
                WitherProt = EnchantmentHelper.getEnchantmentLevel(getData("WitherProtection", "armor")[0], ArmourHelm);

            if (WitherProt > 0 && source.damageType.equalsIgnoreCase("wither") && (ArmourHelm.getTagCompound().getLong("HxCEnchantCharge") > getData("WitherProtection", "armor")[4] || !enableChargesSystem)) {
                event.setCanceled(true);
                if (enableChargesSystem)
                    ArmourHelm.getTagCompound().setLong("HxCEnchantCharge", ArmourHelm.getTagCompound().getLong("HxCEnchantCharge") - getData("WitherProtection", "armor")[4]);
            }

            if(AdrenalineBoostLevel > 0 && allowEffect && (ArmourHelm.getTagCompound().getLong("HxCEnchantCharge") > getData("AdrenalineBoost", "armor")[4] || !enableChargesSystem)) {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 60, AdrenalineBoostLevel));
                if (enableChargesSystem)
                    ArmourHelm.getTagCompound().setLong("HxCEnchantCharge", ArmourHelm.getTagCompound().getLong("HxCEnchantCharge") - getData("AdrenalineBoost", "armor")[4]);
            }
        }
    }

    private LinkedHashMap<Boolean, Item> hasFood(EntityPlayerMP player) {
        LinkedHashMap<Boolean, Item> meh = new LinkedHashMap<>();
        for (ItemStack item : player.inventory.mainInventory)
            if (item != null && item.getItem() instanceof ItemFood)
                meh.put(true, item.getItem());
        return meh;
    }

    private void RepairItems(EntityPlayerMP player){
        ItemStack Inv;
        ItemStack Armor;
        long tmp = 0;
        for(int j = 0; j < 36; j++){
            Inv = player.inventory.getStackInSlot(j);
            if (Inv != null && Inv.isItemStackDamageable() && Inv.hasTagCompound() && Inv.isItemEnchanted() && Inv.getMaxDurability() != Inv.getCurrentDurability()){
                if (enableChargesSystem)
                    tmp = Inv.getTagCompound().getLong("HxCEnchantCharge");
                int a = EnchantmentHelper.getEnchantmentLevel(getData("Repair", "other")[0], Inv);
                int b = Inv.getCurrentDurability() - a;
                if (Inv.getCurrentDurability() > 0 && (tmp >= Inv.getCurrentDurability() || !enableChargesSystem)) {
                    Inv.setMetadata(b);
                    if (enableChargesSystem)
                        Inv.getTagCompound().setLong("HxCEnchantCharge", tmp - a * getData("Repair", "other")[4]);
                }
            }
        }
        for(int j = 0; j < 4; j++){
            Armor = player.getCurrentArmor(j);
            if (Armor != null && Armor.isItemStackDamageable() && Armor.hasTagCompound() && Armor.isItemEnchanted()){
                if (enableChargesSystem)
                    tmp = Armor.getTagCompound().getLong("HxCEnchantCharge");
                int c = EnchantmentHelper.getEnchantmentLevel(getData("Repair", "other")[0], Armor);
                int d = Armor.getCurrentDurability() - c;
                if (Armor.getCurrentDurability() > 0 && (tmp >= Armor.getCurrentDurability() || !enableChargesSystem)) {
                    Armor.setMetadata(d);
                    if (enableChargesSystem)
                        Armor.getTagCompound().setLong("HxCEnchantCharge", tmp - c * getData("Repair", "other")[4]);
                }
            }
        }
    }
    
    private void Stealth (EntityPlayerMP player, int StealthLevel) {
        int px = Math.round((float)player.posX); int py = Math.round((float)player.posY); int pz = Math.round((float) player.posZ);
        List list  = player.worldObj.getEntitiesWithinAABB(EntityMob.class, AABBUtils.getAreaBoundingBox(px, py, pz, 24));
        for (EntityMob entity : (List<EntityMob>) list) {
            IAttributeInstance fr = entity.getEntityAttribute(SharedMonsterAttributes.followRange);
            AttributeModifier StealthBuff = new AttributeModifier(StealthUUID, "StealthDeBuff", (fr.getBaseValue()-StealthLevel), 1);
            fr.removeModifier(StealthBuff);
            fr.applyModifier(StealthBuff);
        }
    }

    private static ArrayList<ChunkPosition> getCropsWithinAABB(World world, AxisAlignedBB box) {
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

    private static ArrayList<ChunkPosition> getFreezablesWithinAABB(World world, AxisAlignedBB box) {
        ArrayList<ChunkPosition> blocks = new ArrayList();

        for(int x = (int)box.minX; (double)x <= box.maxX; ++x) {
            for(int y = (int)box.minY; (double)y <= box.maxY; ++y) {
                for(int z = (int)box.minZ; (double)z <= box.maxZ; ++z) {
                    Block block = world.getBlock(x, y, z);
                    if(block != null && (block == Blocks.lava || block == Blocks.flowing_lava || block == Blocks.water || block == Blocks.fire))
                        blocks.add(new ChunkPosition(x, y, z));
                }
            }
        }
        return blocks;
    }
}
