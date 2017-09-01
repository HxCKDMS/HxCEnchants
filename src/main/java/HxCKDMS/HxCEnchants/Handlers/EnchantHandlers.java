package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCEnchants.Configurations.Configurations;
import HxCKDMS.HxCEnchants.api.AABBUtils;
import hxckdms.hxccore.libraries.GlobalVariables;
import hxckdms.hxccore.utilities.HxCPlayerInfoHandler;
import hxckdms.hxccore.utilities.TeleportHelper;
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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static HxCKDMS.HxCEnchants.Configurations.Configurations.*;
import static HxCKDMS.HxCEnchants.lib.Reference.HealthUUID;
import static HxCKDMS.HxCEnchants.lib.Reference.SpeedUUID;
import static net.minecraft.enchantment.Enchantment.enchantmentsList;

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
        if (enchants.containsKey(enchantmentsList[enchantments.get("LifeSteal").id])) {
            player.heal(damage/10 * enchants.get(enchantmentsList[enchantments.get("LifeSteal").id]));
        }

        if (enchants.containsKey(enchantmentsList[enchantments.get("Piercing").id])) {
                victim.attackEntityFrom(new DamageSource("Piercing").setDamageBypassesArmor().setDamageAllowedInCreativeMode()
                        .setDamageIsAbsolute(), damage * (PiercingPercent * enchants.get(enchantmentsList[enchantments.get("Piercing").id])));
        }

        if (enchants.containsKey(enchantmentsList[enchantments.get("Vorpal").id])) {
            victim.attackEntityFrom(new DamageSource("Vorpal").setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute(),
                    enchants.get(enchantmentsList[enchantments.get("Vorpal").id]) * 0.35f * damage);
        }

        if (enchants.containsKey(enchantmentsList[enchantments.get("BloodRazor").id])) {
            event.ammount += (victim.getMaxHealth() * (0.05f * enchants.get(enchantmentsList[enchantments.get("BloodRazor").id])));
        }

        if (enchants.containsKey(enchantmentsList[enchantments.get("SCurse").id])) {
            int SCurseLevel = enchants.get(enchantmentsList[enchantments.get("SCurse").id]);
            victim.attackEntityFrom(new DamageSource("scurse").setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute(), damage * (0.2f * SCurseLevel));
            player.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 120 * SCurseLevel, SCurseLevel, true));
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 120, Math.round(SCurseLevel /3), true));
            player.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 120 * SCurseLevel, SCurseLevel, true));
        }

        if (enchants.containsKey(enchantmentsList[enchantments.get("VoidTouch").id])) {
            short voidLevel = (short) EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("VoidTouch").id, weapon);
            if (voidLevel > 0) {
                victim.attackEntityFrom(new DamageSource("Void").setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute(), damage * (0.2f * voidLevel));
            }
        }

        if (enchants.containsKey(enchantmentsList[enchantments.get("OverCharge").id]) && weapon.getTagCompound().getInteger("Charge") > 0) {
            int OverCharge = enchants.get(enchantmentsList[enchantments.get("OverCharge").id]);
            int storedCharge = weapon.getTagCompound().getInteger("Charge");
            if (OverCharge > 0) {
                List<EntityLivingBase> list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox((int)Math.round(player.posX), (int)Math.round(player.posY), (int)Math.round(player.posZ), OverCharge*5));
                int ndamage = (int) ((damage * 0.3f * ((list.size()) + (storedCharge/15)) / list.size()));
                list.stream().filter(liv -> liv != player).forEach(liv -> liv.attackEntityFrom(new DamageSource("OverCharge").setDamageIsAbsolute().setDamageAllowedInCreativeMode(), ndamage));
                weapon.getTagCompound().setInteger("Charge", 0);
                Map<Integer, Integer> enchs = EnchantmentHelper.getEnchantments(weapon);
                if (overChargeDecays) enchs.remove((int) enchantments.get("OverCharge").id);
                if (OverCharge > 1 && overChargeDecays) enchs.put((int) enchantments.get("OverCharge").id, OverCharge - 1);
                EnchantmentHelper.setEnchantments(enchs, weapon);
            }
        }
    }

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
            if (isEnabled("BattleHealing"))
                BattleHealingLevel = EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("BattleHealing").id, ArmourChest);

            if (isEnabled("DivineIntervention"))
                DivineInterventionLevel = EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("DivineIntervention").id, ArmourChest);

            if (isEnabled("ExplosiveDischarge") && allowEffect)
                ExplosiveDischarge = EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("ExplosiveDischarge").id, ArmourChest);

            if (BattleHealingLevel > 0 && source.damageType.equalsIgnoreCase("generic")) {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), BattleHealingLevel * 60, BattleHealingLevel));
            }


            if (DivineInterventionLevel > 0 && player.prevHealth - ammount <= 1) {
                player.heal(5);
                int x, y, z;
                if (player.getBedLocation(0) != null) {
                    x = player.getBedLocation(0).posX;
                    y = player.getBedLocation(0).posY;
                    z = player.getBedLocation(0).posZ;
                } else {
                    ChunkCoordinates coords = GlobalVariables.server.worldServerForDimension(0).getSpawnPoint();
                    x = coords.posX;
                    y = coords.posY;
                    z = coords.posZ;
                }
                if (player.dimension != 0) TeleportHelper.teleportEntityToDimension(player, x, y, z, 0);
                else player.playerNetServerHandler.setPlayerLocation(x, y, z, 90, 0);
                Map<Integer, Integer> enchs = EnchantmentHelper.getEnchantments(ArmourChest);
                enchs.remove((int) enchantments.get("DivineIntervention").id);
                if (DivineInterventionLevel > 1) enchs.put((int) enchantments.get("DivineIntervention").id, DivineInterventionLevel - 1);
                EnchantmentHelper.setEnchantments(enchs, ArmourChest);
            }

            if (ExplosiveDischarge > 0) {
                player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, 2f * ExplosiveDischarge, false);
            }
        }

        if (ArmourHelm != null && ArmourHelm.hasTagCompound() && ArmourHelm.isItemEnchanted()) {
            if (isEnabled("AdrenalineBoost"))
                AdrenalineBoostLevel = EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("AdrenalineBoost").id, ArmourHelm);

            if (isEnabled("WitherProtection"))
                WitherProt = EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("WitherProtection").id, ArmourHelm);

            if (WitherProt > 0 && source.damageType.equalsIgnoreCase("wither")) {
                event.setCanceled(true);
            }

            if(AdrenalineBoostLevel > 0 && allowEffect) {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 60, AdrenalineBoostLevel));
            }
        }
    }

}
