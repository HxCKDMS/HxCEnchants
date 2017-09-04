package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCEnchants.Configurations.Configurations;
import hxckdms.hxccore.utilities.HxCPlayerInfoHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.LinkedHashMap;

import static HxCKDMS.HxCEnchants.Configurations.Configurations.enchantments;
import static HxCKDMS.HxCEnchants.lib.Reference.HealthUUID;
import static HxCKDMS.HxCEnchants.lib.Reference.SpeedUUID;

@SuppressWarnings("all")
public class EnchantHandlers {
    private int repairTimer = 60, regenTimer = 60, vitTimer = 600;
    private FurnaceRecipes furnaceRecipes = FurnaceRecipes.smelting();

    public void playerTickEvent(EntityPlayerMP player) {
        if (player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).hasTagCompound() && player.inventory.armorItemInSlot(0).isItemEnchanted() && player.motionY < -0.8 && !player.isSneaking()) {
            int tmp = 0, tmp2 = 0;
            if (isEnabled("FeatherFall"))
                tmp = EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("FeatherFall").id, player.inventory.armorItemInSlot(0));
            if (isEnabled("MeteorFall"))
                tmp2 = EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("MeteorFall").id, player.inventory.armorItemInSlot(0));

            if (tmp > 0)
                player.addVelocity(0, 0.01f * (float) tmp, 0);

            if (tmp2 > 0)
                player.addVelocity(0, 0.02f * (float) -tmp2, 0);;
        }

        if (player.inventory.armorItemInSlot(1) != null && isEnabled("Swiftness")) {
            if (player.inventory.armorItemInSlot(1) == null) {
                IAttributeInstance ps = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
                AttributeModifier SpeedBuff = new AttributeModifier(SpeedUUID, "SpeedBuffedPants", 0, 0);
                ps.removeModifier(SpeedBuff);
            }
        }

        if (player.inventory.armorItemInSlot(0) != null && isEnabled("Vitality")) {
            if (player.inventory.armorItemInSlot(2) == null) {
                IAttributeInstance ph = player.getEntityAttribute(SharedMonsterAttributes.maxHealth);
                AttributeModifier VitBuff = new AttributeModifier(HealthUUID, "HealthBuffedChestplate", 0, 0);
                ph.removeModifier(VitBuff);
            }
        }

        if (isEnabled("Fly")) {
            NBTTagCompound c = HxCPlayerInfoHandler.getTagCompound(player, "backLocation");
            if (player.inventory.armorItemInSlot(0) == null && c != null && c.hasKey("flightEnc") && c.getBoolean("flightEnc")) {
                c.setBoolean("fly", false);
                c.setBoolean("flightEnc", false);
            }
        }
        player.sendPlayerAbilities();
    }
    private boolean isEnabled(String n) {
        return false;
    }

    public void delayedPlayerTickEvent(EntityPlayerMP player) {
        repairTimer--; regenTimer--;
        if (isEnabled("Repair") && repairTimer <= 0) {
//            RepairItems(player);
            repairTimer = Configurations.repairTimer;
        }

        if (isEnabled("Regen") && regenTimer <= 0) {
            short H = 0, C = 0, L = 0, B = 0, rid = (short) enchantments.get("Regen").id;
            byte Regen = 0;
            ItemStack ArmourHelm = player.inventory.armorItemInSlot(3),
                    ArmourChest = player.inventory.armorItemInSlot(2),
                    ArmourLegs = player.inventory.armorItemInSlot(1),
                    ArmourBoots = player.inventory.armorItemInSlot(0);

            if (ArmourHelm != null)
                H = (short) EnchantmentHelper.getEnchantmentLevel((int)rid, player.inventory.armorItemInSlot(3));
            if (ArmourChest != null)
                C = (short) EnchantmentHelper.getEnchantmentLevel((int)rid, player.inventory.armorItemInSlot(2));
            if (ArmourLegs != null)
                L = (short) EnchantmentHelper.getEnchantmentLevel((int)rid, player.inventory.armorItemInSlot(1));
            if (ArmourBoots != null)
                B = (short) EnchantmentHelper.getEnchantmentLevel((int)rid, player.inventory.armorItemInSlot(0));

            if (H > 0) Regen += 1;
            if (B > 0) Regen += 1;
            if (C > 0) Regen += 1;
            if (L > 0) Regen += 1;

            if (player.getHealth() < player.getMaxHealth() && Regen > 0) {
                float hp = player.getMaxHealth() - player.getHealth();
                regenTimer = Configurations.regenTimer;
                player.heal(Regen);
            }
        }
        player.sendPlayerAbilities();
    }

    public void handleAttackEvent(EntityPlayerMP player, EntityLivingBase victim, ItemStack weapon, float damage, LivingHurtEvent event, LinkedHashMap<Enchantment, Integer> enchants) {
    }

    public void playerHurtEvent(EntityPlayerMP player, DamageSource source, float ammount, LivingHurtEvent event) {
    }

}
