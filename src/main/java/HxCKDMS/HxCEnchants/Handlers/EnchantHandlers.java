package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCCore.Handlers.NBTFileIO;
import HxCKDMS.HxCCore.HxCCore;
import HxCKDMS.HxCCore.api.Utils.AABBUtils;
import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.HxCCore.api.Utils.Teleporter;
import HxCKDMS.HxCEnchants.Configurations;
import HxCKDMS.HxCEnchants.EnchantConfigHandler;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import HxCKDMS.HxCEnchants.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
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
import net.minecraftforge.event.world.BlockEvent;

import java.io.File;
import java.util.*;

import static HxCKDMS.HxCEnchants.EnchantConfigHandler.getData;
import static HxCKDMS.HxCEnchants.EnchantConfigHandler.isEnabled;
import static HxCKDMS.HxCEnchants.lib.Reference.*;

@SuppressWarnings("all")
public class EnchantHandlers implements IEnchantHandler {
    private int ShouldRepair = 60, CanRegen = 60, flyTimer = 1200, swiftTimer = 600, vitTimer = 600, stealthTimer = 600;
    private short VampireLevel, ExamineLevel, VoidLevel, AutoSmeltLevel, LifeStealLevel, PiercingLevel, VorpalLevel, SCurseLevel;
    FurnaceRecipes furnaceRecipes = FurnaceRecipes.instance();

    public static boolean OverCharge = false, FlashButton = false;
    private double SpeedBoost, Vitality;
    private short VitalityLevel, FlyLevel, RegenLevel, SpeedLevel, StealthLevel, H = 0, C = 0, L = 0, B = 0;
    private short[] DeadlyAura = new short[4], FieryAura = new short[4],
            ToxicAura = new short[4], ThickAura = new short[4],
            DarkAura = new short[4], Shroud = new short[4],
            GaiaAura = new short[4], IcyAura = new short[4],
            HealingAura = new short[4], MagneticAura = new short[4],
            RepulsiveAura = new short[4];
    
    @Override
    public void handleHelmetEnchant(EntityPlayerMP player, ItemStack helmet, long itemCharge) {
        if (EnchantConfigHandler.isEnabled("Gluttony", "armor")) {
            int gluttony = EnchantmentHelper.getEnchantmentLevel(Enchants.Gluttony.effectId, helmet);
            LinkedHashMap<Boolean, Item> tmp = hasFood(player);
            if (gluttony > 0 && player.getFoodStats().getFoodLevel() <= (gluttony / 2) + 5 && !tmp.isEmpty() && tmp.containsKey(true) && tmp.get(true) != null) {
                player.getFoodStats().addStats(((ItemFood) Items.apple).getHealAmount(new ItemStack(tmp.get(true))), ((ItemFood) Items.apple).getSaturationModifier(new ItemStack(tmp.get(true))));
                for (int i = 0; i < player.inventory.mainInventory.length; i++) {
                    if (player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i].getItem() instanceof ItemFood && player.inventory.mainInventory[i].getItem() == tmp.get(true)) {
                        player.inventory.decrStackSize(i, 1);
                        if (Configurations.enableChargesSystem)
                            helmet.getTagCompound().setLong("HxCEnchantCharge", itemCharge - EnchantConfigHandler.getData("Gluttony", "armor")[4]);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void handleChestplateEnchant(EntityPlayerMP player, ItemStack chestplate, long itemCharge) {
        if (isEnabled("Vitality", "armor")) {
            long charge = chestplate.getTagCompound().getInteger("HxCEnchantCharge");
            vitTimer--;
            IAttributeInstance ph = player.getEntityAttribute(SharedMonsterAttributes.maxHealth);
            VitalityLevel = (short) EnchantmentHelper.getEnchantmentLevel(Enchants.Vitality.effectId, chestplate);
            Vitality = VitalityLevel * 0.5F;
            AttributeModifier HealthBuff = new AttributeModifier(HealthUUID, "HealthBuffedChestplate", Vitality, 1);
            if (!ph.func_111122_c().contains(HealthBuff) && VitalityLevel != 0 && (charge > getData("Vitality", "armor")[4] || !Configurations.enableChargesSystem))
                ph.applyModifier(HealthBuff);
            if (ph.func_111122_c().contains(HealthBuff) && (VitalityLevel == 0 || (charge < getData("Vitality", "armor")[4]) || !Configurations.enableChargesSystem))
                ph.removeModifier(HealthBuff);

            if (vitTimer <= 0 && Configurations.enableChargesSystem) {
                chestplate.getTagCompound().setLong("HxCEnchantCharge", charge - getData("Vitality", "armor")[4]);
                vitTimer = 600;
            }
        }
    }

    @Override
    public void handleLeggingEnchant(EntityPlayerMP player, ItemStack leggings, long itemCharge) {
        if (isEnabled("Swiftness", "armor")) {
            swiftTimer--;
            IAttributeInstance ps = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
            SpeedLevel = (short) EnchantmentHelper.getEnchantmentLevel(Enchants.Swiftness.effectId, leggings);
            SpeedBoost = SpeedLevel * 0.2;
            AttributeModifier SpeedBuff = new AttributeModifier(SpeedUUID, "SpeedBuffedPants", SpeedBoost, 1);
            if (!ps.func_111122_c().contains(SpeedBuff) && SpeedLevel != 0 && (itemCharge > getData("Swiftness", "armor")[4] || !Configurations.enableChargesSystem))
                ps.applyModifier(SpeedBuff);
            if (ps.func_111122_c().contains(SpeedBuff) && (SpeedLevel == 0 || (itemCharge < getData("Swiftness", "armor")[4] || !Configurations.enableChargesSystem)))
                ps.removeModifier(SpeedBuff);

            if (swiftTimer <= 0 && Configurations.enableChargesSystem && itemCharge > getData("Swiftness", "armor")[4]) {
                leggings.getTagCompound().setLong("HxCEnchantCharge", itemCharge - getData("Swiftness", "armor")[4]);
                swiftTimer = 600;
            }
        }
    }

    @Override
    public void handleBootEnchant(EntityPlayerMP player, ItemStack boots, long itemCharges) {
        String UUID = player.getUniqueID().toString();
        File CustomPlayerData = new File(HxCCore.HxCCoreDir, "HxC-" + UUID + ".dat");
        if (EnchantConfigHandler.isEnabled("Fly", "armor")) {
            FlyLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.Fly.effectId, boots);
            if (FlyLevel > 0 && player.capabilities.isFlying && !player.capabilities.isCreativeMode)
                flyTimer--;

            if (flyTimer <= 0 && Configurations.enableChargesSystem && itemCharges > EnchantConfigHandler.getData("Fly", "armor")[4]) {
                flyTimer = 1200;
                boots.getTagCompound().setLong("HxCEnchantCharge", itemCharges - EnchantConfigHandler.getData("Fly", "armor")[4]);
            }
            boolean flyhbt = false;
            try {
                flyhbt = NBTFileIO.getBoolean(CustomPlayerData, "EFlyHasChanged");
            } catch (Exception ignored) {}

            if (FlyLevel > 0 && !player.capabilities.allowFlying && (itemCharges > EnchantConfigHandler.getData("Fly", "armor")[4] * 2 || !Configurations.enableChargesSystem)) {
                player.capabilities.allowFlying = true;
                player.sendPlayerAbilities();
                NBTFileIO.setBoolean(CustomPlayerData, "EFlyHasChanged", true);
                if (Configurations.enableChargesSystem)
                    boots.getTagCompound().setLong("HxCEnchantCharge", itemCharges - EnchantConfigHandler.getData("Fly", "armor")[4]);
            }
            if ((FlyLevel < 1 && flyhbt) || (FlyLevel > 0 && itemCharges < EnchantConfigHandler.getData("Fly", "armor")[4])) {
                player.capabilities.allowFlying = false;
                player.capabilities.isFlying = false;
                player.sendPlayerAbilities();
                NBTFileIO.setBoolean(CustomPlayerData, "EFlyHasChanged", false);
            }
            if (player.capabilities.isFlying && FlyLevel > 0 && !player.capabilities.isCreativeMode)
                player.worldObj.spawnParticle("smoke", player.posX + Math.random() - 0.5d,
                        player.posY - 1.62d, player.posZ + Math.random() - 0.5d, 0.0d, 0.0d, 0.0d);
        }

        if (EnchantConfigHandler.isEnabled("Stealth", "armor")) {
            stealthTimer--;
            StealthLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.Stealth.effectId, boots);

            if (StealthLevel > 0) {
                Stealth(player, StealthLevel);

                if (stealthTimer <= 0 && Configurations.enableChargesSystem) {
                    boots.getTagCompound().setLong("HxCEnchantCharge", itemCharges - EnchantConfigHandler.getData("Stealth", "armor")[4]);
                    stealthTimer = 600;
                }
            }
        }

        if (EnchantConfigHandler.isEnabled("FlashStep", "armor") && FlashButton && itemCharges >= EnchantConfigHandler.getData("FlashStep", "armor")[4]) {
            int FlashLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.FlashStep.effectId, boots);
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
                    if (world.getBlock((int) Math.round(x + vx * i), (int) Math.round(y + vy * i), (int) Math.round(z + vz * i)) != Blocks.air && world.getBlock((int) Math.round(x + vx * i), (int) Math.round(y + vy * i) + 2, (int) Math.round(z + vz * i)) == Blocks.air) {
                        player.playerNetServerHandler.setPlayerLocation((int) Math.round(x + vx * i), (int) Math.round(y + vy * i) + 2, (int) Math.round(z + vz * i), player.cameraYaw, player.cameraPitch);
                        if (Configurations.enableChargesSystem)
                            boots.getTagCompound().setLong("HxCEnchantCharge", itemCharges - EnchantConfigHandler.getData("FlashStep", "armor")[4]);
                    } else if (world.getBlock((int) Math.round(x + vx * i), (int) Math.round(y + i), (int) Math.round(z + vz * i)) != Blocks.air && world.getBlock((int) Math.round(x + vx * i), (int) Math.round(y + i) + 2, (int) Math.round(z + vz * i)) == Blocks.air) {
                        player.playerNetServerHandler.setPlayerLocation((int) Math.round(x + vx * i), (int) Math.round(y + i) + 2, (int) Math.round(z + vz * i), player.cameraYaw, player.cameraPitch);
                        if (Configurations.enableChargesSystem)
                            boots.getTagCompound().setLong("HxCEnchantCharge", itemCharges - EnchantConfigHandler.getData("FlashStep", "armor")[4]);
                    } else if (world.getBlock((int) Math.round(x + vx * i), (int) Math.round(y + -i), (int) Math.round(z + vz * i)) != Blocks.air && world.getBlock((int) Math.round(x + vx * i), (int) Math.round(y + -i) + 2, (int) Math.round(z + vz * i)) == Blocks.air) {
                        player.playerNetServerHandler.setPlayerLocation((int) Math.round(x + vx * i), (int) Math.round(y + -i) + 2, (int) Math.round(z + vz * i), player.cameraYaw, player.cameraPitch);
                        if (Configurations.enableChargesSystem)
                            boots.getTagCompound().setLong("HxCEnchantCharge", itemCharges - EnchantConfigHandler.getData("FlashStep", "armor")[4]);
                    }
                }
            }
            FlashButton = false;
        }

    }


    @Override
    public void handleDeathEvent(EntityPlayerMP player, EntityLivingBase victim, ItemStack stack, long itemCharge) {
        VampireLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.Vampirism.effectId, stack);
        ExamineLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.Examine.effectId, stack);
        if (ExamineLevel > 0 && (itemCharge > EnchantConfigHandler.getData("Examine", "weapon")[4] || !Configurations.enableChargesSystem))
            if (victim instanceof EntityLiving) {
                victim.worldObj.spawnEntityInWorld(new EntityXPOrb(victim.worldObj, victim.posX, victim.posY + 1, victim.posZ, ExamineLevel * EnchantConfigHandler.getData("Examine", "weapon")[4]));
                if (Configurations.enableChargesSystem)
                    stack.getTagCompound().setLong("HxCEnchantCharge", itemCharge - EnchantConfigHandler.getData("Examine", "weapon")[4]);
            }

        if (VampireLevel > 0 && (itemCharge > EnchantConfigHandler.getData("Vampirism", "weapon")[4] || !Configurations.enableChargesSystem)) {
            if (victim instanceof EntityAnimal)
                player.getFoodStats().addStats(1, 0.3F);
            else if (victim instanceof EntityPlayerMP)
                player.getFoodStats().addStats(10, 0.5F);
            else if (victim instanceof EntityVillager)
                player.getFoodStats().addStats(5, 0.5F);
            else if (((EntityLiving) victim).isEntityUndead())
                player.getFoodStats().addStats(0, 0);
            else if (victim instanceof EntitySlime)
                player.getFoodStats().addStats(1, 0.1F);
            else if (victim instanceof EntityEnderman)
                player.getFoodStats().addStats(2, 0.2F);
            else if (victim instanceof EntityMob)
                player.getFoodStats().addStats(3, 0.2F);

            else player.getFoodStats().addStats(1, 0.1F);

            if (HxCKDMS.HxCCore.Configs.Configurations.DebugMode)
                LogHelper.warn(player + "has had their hunger increased by Vampirism.", Reference.MOD_ID);

            if (Configurations.enableChargesSystem)
                stack.getTagCompound().setLong("HxCEnchantCharge", itemCharge - EnchantConfigHandler.getData("Vampirism", "weapon")[4]);
        }
    }

    @Override
    public void playerTickEvent(EntityPlayerMP player) {
//        player.noClip = true;
        if (EnchantConfigHandler.isEnabled("OverCharge", "weapon") && player.getHeldItem() != null && player.getHeldItem().isItemEnchanted() && player.getHeldItem().getTagCompound() != null) {
            long HeldCharges = 0;
            if (Configurations.enableChargesSystem) {
                HeldCharges = player.getHeldItem().getTagCompound().getLong("HxCEnchantCharge");
            }
            boolean stored = player.getHeldItem().getTagCompound().getBoolean("StoredCharge");
            int temp = EnchantmentHelper.getEnchantmentLevel(Enchants.Overcharge.effectId, player.getHeldItem()), RequiredCharge = EnchantConfigHandler.getData("OverCharge", "weapon")[4];
            if (temp > 0 && (HeldCharges >= RequiredCharge || !Configurations.enableChargesSystem) && !stored) {
                if (OverCharge && !stored && player.getHeldItem().getTagCompound().getInteger("HxCOverCharge") != 0) {
                    player.addChatComponentMessage(new ChatComponentText("You have just stored a charge of " + player.getHeldItem().getTagCompound().getInteger("HxCOverCharge") + "!"));
                    player.getHeldItem().getTagCompound().setBoolean("StoredCharge", true);
                    OverCharge = false;
                    if (Configurations.enableChargesSystem)
                        player.getHeldItem().getTagCompound().setLong("HxCEnchantCharge", HeldCharges - RequiredCharge);
                }

                if (!OverCharge && !stored && player.getHeldItem().getTagCompound().getInteger("HxCOverCharge") != 0 && HeldCharges >= RequiredCharge * 2) {
                    player.getHeldItem().getTagCompound().setInteger("HxCOverCharge", player.getHeldItem().getTagCompound().getInteger("HxCOverCharge") + 1);
                    if (Configurations.enableChargesSystem)
                        player.getHeldItem().getTagCompound().setLong("HxCEnchantCharge", HeldCharges - RequiredCharge);
                }

                if (OverCharge && !stored && player.getHeldItem().getTagCompound().getInteger("HxCOverCharge") == 0 && HeldCharges >= RequiredCharge * 2) {
                    OverCharge = false;
                    player.getHeldItem().getTagCompound().setInteger("HxCOverCharge", player.getHeldItem().getTagCompound().getInteger("HxCOverCharge") + 1);
                    if (Configurations.enableChargesSystem)
                        player.getHeldItem().getTagCompound().setLong("HxCEnchantCharge", HeldCharges - RequiredCharge);
                }
            }
        }

        if (player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).hasTagCompound() && player.inventory.armorItemInSlot(0).isItemEnchanted() && player.motionY < -0.8 && !player.isSneaking()) {
            int tmp = 0, tmp2 = 0;
            if (EnchantConfigHandler.isEnabled("FeatherFall", "armor") && player.inventory.armorItemInSlot(0).getTagCompound().getLong("HxCEnchantCharge") > EnchantConfigHandler.getData("FeatherFall", "armor")[4])
                tmp = EnchantmentHelper.getEnchantmentLevel(Enchants.FeatherFall.effectId, player.inventory.armorItemInSlot(0));
            if (EnchantConfigHandler.isEnabled("MeteorFall", "armor") && player.inventory.armorItemInSlot(0).getTagCompound().getLong("HxCEnchantCharge") > EnchantConfigHandler.getData("MeteorFall", "armor")[4])
                tmp2 = EnchantmentHelper.getEnchantmentLevel(Enchants.MeteorFall.effectId, player.inventory.armorItemInSlot(0));

            if (tmp > 0) {
                player.motionY += (0.01f * (tmp / 2));
                if (Configurations.enableChargesSystem)
                    player.inventory.armorItemInSlot(0).getTagCompound().setLong("HxCEnchantCharge", player.inventory.armorItemInSlot(0).getTagCompound().getLong("HxCEnchantCharge") - EnchantConfigHandler.getData("FeatherFall", "armor")[4]);
            }
            if (tmp2 > 0) {
                player.motionY -= (0.02f * tmp2);
                if (Configurations.enableChargesSystem)
                    player.inventory.armorItemInSlot(0).getTagCompound().setLong("HxCEnchantCharge", player.inventory.armorItemInSlot(0).getTagCompound().getLong("HxCEnchantCharge") - EnchantConfigHandler.getData("MeteorFall", "armor")[4]);
            }
        }
    }

    @Override
    public void delayedPlayerTickEvent(EntityPlayerMP player) {
        ShouldRepair--; CanRegen--;
        if (EnchantConfigHandler.isEnabled("Repair", "other") && ShouldRepair <= 0) {
            RepairItems(player);
            ShouldRepair = (EnchantConfigHandler.getData("Repair", "other")[5]);
        }

        if (EnchantConfigHandler.isEnabled("Regen", "armor") && CanRegen <= 0) {
            byte Regen = 0;
            if (player.inventory.armorItemInSlot(3) != null)
                H = (short) EnchantmentHelper.getEnchantmentLevel(Enchants.ArmorRegen.effectId, player.inventory.armorItemInSlot(3));
            if (player.inventory.armorItemInSlot(2) != null)
                B = (short) EnchantmentHelper.getEnchantmentLevel(Enchants.ArmorRegen.effectId, player.inventory.armorItemInSlot(2));
            if (player.inventory.armorItemInSlot(1) != null)
                C = (short) EnchantmentHelper.getEnchantmentLevel(Enchants.ArmorRegen.effectId, player.inventory.armorItemInSlot(1));
            if (player.inventory.armorItemInSlot(0) != null)
                L = (short) EnchantmentHelper.getEnchantmentLevel(Enchants.ArmorRegen.effectId, player.inventory.armorItemInSlot(0));

            if (H > 0) Regen += 1;
            if (B > 0) Regen += 1;
            if (C > 0) Regen += 1;
            if (L > 0) Regen += 1;

            ItemStack ArmourHelm = player.inventory.armorItemInSlot(3),
                    ArmourChest = player.inventory.armorItemInSlot(2),
                    ArmourLegs = player.inventory.armorItemInSlot(1),
                    ArmourBoots = player.inventory.armorItemInSlot(0);

            long HChrg = -1, CChrg = -1, LChrg = -1, BChrg = -1;
            if (Configurations.enableChargesSystem) {
                if (ArmourHelm != null && ArmourHelm.getTagCompound() != null)
                    HChrg = ArmourHelm.getTagCompound().getLong("HxCEnchantCharge");
                if (ArmourChest != null && ArmourChest.getTagCompound() != null)
                    CChrg = ArmourChest.getTagCompound().getLong("HxCEnchantCharge");
                if (ArmourLegs != null && ArmourLegs.getTagCompound() != null)
                    LChrg = ArmourLegs.getTagCompound().getLong("HxCEnchantCharge");
                if (ArmourBoots != null && ArmourBoots.getTagCompound() != null)
                    BChrg = ArmourBoots.getTagCompound().getLong("HxCEnchantCharge");
            }

            if (player.getHealth() < player.getMaxHealth() && RegenLevel > 0) {
                float hp = player.getMaxHealth() - player.getHealth();
                CanRegen = EnchantConfigHandler.getData("Regen", "armor")[4];
                if (H > 0) {
                    if (Configurations.enableChargesSystem)
                        ArmourChest.getTagCompound().setLong("HxCEnchantCharge", HChrg - H * EnchantConfigHandler.getData("Regen", "armor")[4]);
                    player.heal(H / 2);
                }
                if (C > 0 && CChrg > (hp * 2) / RegenLevel) {
                    if (Configurations.enableChargesSystem)
                        ArmourChest.getTagCompound().setLong("HxCEnchantCharge", CChrg - C * EnchantConfigHandler.getData("Regen", "armor")[4]);
                    player.heal(C / 2);
                }
                if (L > 0 && LChrg > (hp * 2) / RegenLevel) {
                    if (Configurations.enableChargesSystem)
                        ArmourLegs.getTagCompound().setLong("HxCEnchantCharge", LChrg - L * EnchantConfigHandler.getData("Regen", "armor")[4]);
                    player.heal(L / 2);
                }
                if (B > 0 && BChrg > (hp * 2) / RegenLevel) {
                    if (Configurations.enableChargesSystem)
                        ArmourBoots.getTagCompound().setLong("HxCEnchantCharge", BChrg - B * EnchantConfigHandler.getData("Regen", "armor")[4]);
                    player.heal(B / 2);
                }
            }
        }

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

    @Override
    public void handleAttackEvent(EntityPlayerMP player, EntityLivingBase victim, ItemStack weapon, float damage, long itemCharge) {
        if (EnchantConfigHandler.isEnabled("LifeSteal", "weapon") && (itemCharge > EnchantConfigHandler.getData("LifeSteal", "weapon")[4] || !Configurations.enableChargesSystem)) {
            LifeStealLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.LifeSteal.effectId, weapon);
            if (LifeStealLevel > 0) {
                player.heal(damage/10 * LifeStealLevel);
                if (Configurations.enableChargesSystem)
                    weapon.getTagCompound().setLong("HxCEnchantCharge", itemCharge - EnchantConfigHandler.getData("LifeSteal", "weapon")[4]);
            }
        }

        if (EnchantConfigHandler.isEnabled("Piercing", "weapon") && (itemCharge > EnchantConfigHandler.getData("Piercing", "weapon")[4] || !Configurations.enableChargesSystem)) {
            PiercingLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.Piercing.effectId, weapon);
            if (PiercingLevel > 0)
                victim.attackEntityFrom(new DamageSource("Piercing").setDamageBypassesArmor().setDamageAllowedInCreativeMode()
                        .setDamageIsAbsolute(), damage * Configurations.PiercingPercent);
            if (Configurations.enableChargesSystem)
                weapon.getTagCompound().setLong("HxCEnchantCharge", itemCharge - EnchantConfigHandler.getData("Piercing", "weapon")[4]);
        }

        if (EnchantConfigHandler.isEnabled("Vorpal", "weapon") && (itemCharge > EnchantConfigHandler.getData("Vorpal", "weapon")[4] || !Configurations.enableChargesSystem)) {
            VorpalLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.Vorpal.effectId, weapon);
            if (VorpalLevel > 0) victim.attackEntityFrom(new DamageSource("Vorpal").setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute(), VorpalLevel * EnchantConfigHandler.getData("Vorpal", "weapon")[4]);
            if (Configurations.enableChargesSystem)
                weapon.getTagCompound().setLong("HxCEnchantCharge", itemCharge - EnchantConfigHandler.getData("Vorpal", "weapon")[4]);
        }

        if (EnchantConfigHandler.isEnabled("SCurse", "weapon") && (itemCharge > EnchantConfigHandler.getData("SCurse", "weapon")[4] || !Configurations.enableChargesSystem)) {
            SCurseLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.SCurse.effectId, weapon);
            if (SCurseLevel > 0) {
                victim.attackEntityFrom(new DamageSource("scurse").setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute(), EnchantConfigHandler.getData("SCurse", "weapon")[5] * SCurseLevel);
                player.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 120 * SCurseLevel, SCurseLevel, true));
                player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 120, Math.round(SCurseLevel/3), true));
                player.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 120 * SCurseLevel, SCurseLevel * EnchantConfigHandler.getData("SCurse", "weapon")[5], true));
                if (Configurations.enableChargesSystem)
                    weapon.getTagCompound().setLong("HxCEnchantCharge", itemCharge - EnchantConfigHandler.getData("SCurse", "weapon")[4]);
            }
        }

        if (EnchantConfigHandler.isEnabled("OverCharge", "weapon") && weapon.getTagCompound().getBoolean("StoredCharge")) {
            int OverChage = EnchantmentHelper.getEnchantmentLevel(Enchants.Overcharge.effectId, weapon);
            int storedCharge = weapon.getTagCompound().getInteger("HxCOverCharge");
            if (OverChage > 0) {
                List<EntityLivingBase> list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox((int)Math.round(player.posX), (int)Math.round(player.posY), (int)Math.round(player.posZ), OverChage*5));
                int ndamage = storedCharge/list.size();
                for (EntityLivingBase liv : list) {
                    if (liv != player) {
                        liv.attackEntityFrom(new DamageSource("OverCharge").setDamageIsAbsolute().setDamageAllowedInCreativeMode(), ndamage);
                    }
                }
                weapon.getTagCompound().setInteger("HxCOverCharge", 0);
                weapon.getTagCompound().setBoolean("StoredCharge", false);
                Map<Integer, Integer> enchs = EnchantmentHelper.getEnchantments(weapon);
                enchs.remove(EnchantConfigHandler.getData("OverCharge", "weapon")[0]);
                if (OverChage > 1) enchs.put((int) EnchantConfigHandler.getData("OverCharge", "weapon")[0], OverChage - 1);
                EnchantmentHelper.setEnchantments(enchs, weapon);
            }
        }
    }

    @Override
    public void playerMineBlockEvent(EntityPlayer player, ItemStack tool, long itemCharge, BlockEvent.HarvestDropsEvent event) {
        if (EnchantConfigHandler.isEnabled("FlameTouch", "tool")) {
            int AutoSmeltLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.FlameTouch.effectId, tool);
            if (AutoSmeltLevel > 0 && (tool.getTagCompound().getLong("HxCEnchantCharge") > EnchantConfigHandler.getData("FlameTouch", "tool")[4] || !Configurations.enableChargesSystem)) {
                for (int i = 0; i < event.drops.size(); i++) {
                    ItemStack smelted = furnaceRecipes.getSmeltingResult(event.drops.get(i));

                    if (smelted != null) {
                        ItemStack drop = smelted.copy();
                        drop.stackSize *= AutoSmeltLevel;
                        event.drops.set(i, drop);
                        if (Configurations.enableChargesSystem)
                            tool.getTagCompound().setLong("HxCEnchantCharge", tool.getTagCompound().getLong("HxCEnchantCharge") - (EnchantConfigHandler.getData("FlameTouch", "tool")[4] * AutoSmeltLevel));
                    }
                }
            }
        }

        if (EnchantConfigHandler.isEnabled("VoidTouch", "tool")) {
            VoidLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.VoidTouch.effectId, tool);
            if (VoidLevel > 0 && (tool.getTagCompound().getLong("HxCEnchantCharge") > EnchantConfigHandler.getData("VoidTouch", "tool")[4] || !Configurations.enableChargesSystem)) {
                for(String block : Configurations.VoidedItems)
                    event.drops.remove(new ItemStack(Block.getBlockFromName(block)));
                if (Configurations.enableChargesSystem)
                    tool.getTagCompound().setLong("HxCEnchantCharge", tool.getTagCompound().getLong("HxCEnchantCharge") - (EnchantConfigHandler.getData("VoidTouch", "tool")[4] * VoidLevel));
            }
        }
    }

    @Override
    public void playerHurtEvent(EntityPlayerMP player, DamageSource source, float ammount) {
        boolean allowABEffect = !(source.damageType.equalsIgnoreCase("wither") ||
                source.damageType.equalsIgnoreCase("starve") ||
                source.damageType.equalsIgnoreCase("fall") ||
                source.damageType.equalsIgnoreCase("explosion.player") ||
                source.damageType.equalsIgnoreCase("explosion") ||
                source.damageType.equalsIgnoreCase("inWall"));

        ItemStack ArmourHelm = player.inventory.armorItemInSlot(3),
                ArmourChest = player.inventory.armorItemInSlot(2);

        int AdrenalineBoostLevel = 0, BattleHealingLevel = 0, WitherProt = 0, DivineInterventionLevel = 0, ExplosiveDischarge = 0;

        if (ArmourChest != null && ArmourChest.hasTagCompound()) {
            if (EnchantConfigHandler.isEnabled("BattleHealing", "armor"))
                BattleHealingLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.BattleHealing.effectId, ArmourChest);

            if (EnchantConfigHandler.isEnabled("DivineIntervention", "armor"))
                DivineInterventionLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.DivineIntervention.effectId, ArmourChest);

            if (EnchantConfigHandler.isEnabled("ExplosiveDischarge", "armor"))
                ExplosiveDischarge = EnchantmentHelper.getEnchantmentLevel(Enchants.ExplosiveDischarge.effectId, ArmourChest);

            if (BattleHealingLevel > 0 && source.damageType.equalsIgnoreCase("generic")) {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), BattleHealingLevel * 60, BattleHealingLevel));
                if (Configurations.enableChargesSystem)
                    ArmourChest.getTagCompound().setLong("HxCEnchantCharge", ArmourHelm.getTagCompound().getLong("HxCEnchantCharge") - EnchantConfigHandler.getData("BattleHealing", "armor")[4]);
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
                enchs.remove(EnchantConfigHandler.getData("DivineIntervention", "armor")[0]);
                if (DivineInterventionLevel > 1) enchs.put((int)EnchantConfigHandler.getData("DivineIntervention", "armor")[0], DivineInterventionLevel - 1);
                EnchantmentHelper.setEnchantments(enchs, ArmourChest);
            }

            if (ExplosiveDischarge > 0 && (ArmourChest.getTagCompound().getLong("HxCEnchantCharge") > EnchantConfigHandler.getData("ExplosiveDischarge", "armor")[4] || !Configurations.enableChargesSystem)) {
                player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, 2f * ExplosiveDischarge, false);
                if (Configurations.enableChargesSystem)
                    ArmourChest.getTagCompound().setLong("HxCEnchantCharge", ArmourChest.getTagCompound().getLong("HxCEnchantCharge") - EnchantConfigHandler.getData("ExplosiveDischarge", "armor")[4]);
            }
        }
        if (ArmourHelm != null && ArmourHelm.hasTagCompound()) {
            if (EnchantConfigHandler.isEnabled("AdrenalineBoost", "armor"))
                AdrenalineBoostLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.AdrenalineBoost.effectId, ArmourHelm);

            if (EnchantConfigHandler.isEnabled("WitherProtection", "armor"))
                WitherProt = EnchantmentHelper.getEnchantmentLevel(Enchants.WitherProtection.effectId, ArmourHelm);

            if (WitherProt > 0 && source.damageType.equalsIgnoreCase("wither") && (ArmourHelm.getTagCompound().getLong("HxCEnchantCharge") > EnchantConfigHandler.getData("WitherProtection", "armor")[4] || !Configurations.enableChargesSystem)) {
                player.removePotionEffect(Potion.wither.getId());
                if (Configurations.enableChargesSystem)
                    ArmourHelm.getTagCompound().setLong("HxCEnchantCharge", ArmourHelm.getTagCompound().getLong("HxCEnchantCharge") - EnchantConfigHandler.getData("WitherProtection", "armor")[4]);
            }

            if(AdrenalineBoostLevel > 0 && allowABEffect && (ArmourHelm.getTagCompound().getLong("HxCEnchantCharge") > EnchantConfigHandler.getData("AdrenalineBoost", "armor")[4] || !Configurations.enableChargesSystem)) {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 60, AdrenalineBoostLevel));
                if (Configurations.enableChargesSystem)
                    ArmourHelm.getTagCompound().setLong("HxCEnchantCharge", ArmourHelm.getTagCompound().getLong("HxCEnchantCharge") - EnchantConfigHandler.getData("AdrenalineBoost", "armor")[4]);
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
                if (Configurations.enableChargesSystem)
                    tmp = Inv.getTagCompound().getLong("HxCEnchantCharge");
                int a = EnchantmentHelper.getEnchantmentLevel(Enchants.Repair.effectId, Inv);
                int b = Inv.getCurrentDurability() - a;
                if (Inv.getCurrentDurability() > 0 && (tmp >= Inv.getCurrentDurability() || !Configurations.enableChargesSystem)) {
                    Inv.setMetadata(b);
                    if (Configurations.enableChargesSystem)
                        Inv.getTagCompound().setLong("HxCEnchantCharge", tmp - a * EnchantConfigHandler.getData("Repair", "other")[4]);
                }
            }
        }
        for(int j = 0; j < 4; j++){
            Armor = player.getCurrentArmor(j);
            if (Armor != null && Armor.isItemStackDamageable() && Armor.hasTagCompound() && Armor.isItemEnchanted()){
                if (Configurations.enableChargesSystem)
                    tmp = Armor.getTagCompound().getLong("HxCEnchantCharge");
                int c = EnchantmentHelper.getEnchantmentLevel(Enchants.Repair.effectId, Armor);
                int d = Armor.getCurrentDurability() - c;
                if (Armor.getCurrentDurability() > 0 && (tmp >= Armor.getCurrentDurability() || !Configurations.enableChargesSystem)) {
                    Armor.setMetadata(d);
                    if (Configurations.enableChargesSystem)
                        Armor.getTagCompound().setLong("HxCEnchantCharge", tmp - c * EnchantConfigHandler.getData("Repair", "other")[4]);
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
