package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.HxCEnchants.EnchantConfigHandler;
import HxCKDMS.HxCEnchants.api.HxCEnchant;
import HxCKDMS.HxCEnchants.lib.Reference;
import net.minecraft.enchantment.Enchantment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("all")
public class Enchants implements HxCEnchant {
    public static short Enchs = 0;
    
    private static short[] data;

    public static HxCEnchant enchant;
    
    public static List<HxCEnchantment> hxcEnchants = new ArrayList<>();
    public static void load () {
        if (EnchantConfigHandler.isEnabled("AdrenalineBoost", "armor")) {
            data = EnchantConfigHandler.getData("AdrenalineBoost", "armor");
            enchant.registerEnchant("adrenalineBoost", EnumHxCEnchantType.ARMOR_HEAD, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("AuraDark", "armor")) {
            data = EnchantConfigHandler.getData("AuraDark", "armor");
            enchant.registerEnchant("darkAura", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("AuraDeadly", "armor")) {
            data = EnchantConfigHandler.getData("AuraDeadly", "armor");
            enchant.registerEnchant("deadlyAura", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("GaiaAura", "armor")[0], (int) EnchantConfigHandler.getData("HealingAura", "armor")[0]));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("AuraFiery", "armor")) {
            data = EnchantConfigHandler.getData("AuraFiery", "armor");
            enchant.registerEnchant("flameAura", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("HealingAura", "armor")[0], (int) EnchantConfigHandler.getData("GaiaAura", "armor")[0], (int) EnchantConfigHandler.getData("IcyAura", "armor")[0]));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("AuraThick", "armor")) {
            data = EnchantConfigHandler.getData("AuraThick", "armor");
            enchant.registerEnchant("thickAura", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("AuraToxic", "armor")) {
            data = EnchantConfigHandler.getData("AuraToxic", "armor");
            enchant.registerEnchant("toxicAura", EnumHxCEnchantType.ARMOR, data[0], data[2], data[1], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("GaiaAura", "armor")[0], (int) EnchantConfigHandler.getData("HealingAura", "armor")[0]));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("Regen", "armor")) {
            data = EnchantConfigHandler.getData("Regen", "armor");
            enchant.registerEnchant("regen", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("BattleHealing", "armor")[0]));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("ArrowExplosive", "weapon")) {
            data = EnchantConfigHandler.getData("ArrowExplosive", "weapon");
            enchant.registerEnchant("arrowExplosive", EnumHxCEnchantType.BOW, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("Zeus", "weapon")) {
            data = EnchantConfigHandler.getData("Zeus", "weapon");
            enchant.registerEnchant("arrowZeus", EnumHxCEnchantType.BOW, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("LightningArrow", "weapon")[0]));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("ArrowSeeking", "weapon")) {
            data = EnchantConfigHandler.getData("ArrowSeeking", "weapon");
            enchant.registerEnchant("arrowSeeking", EnumHxCEnchantType.BOW, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("BattleHealing", "armor")) {
            data = EnchantConfigHandler.getData("BattleHealing", "armor");
            enchant.registerEnchant("battleHeal", EnumHxCEnchantType.ARMOR_TORSO, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("Regen", "armor")[0]));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("Bound", "other")) {
            data = EnchantConfigHandler.getData("Bound", "other");
            enchant.registerEnchant("bound", EnumHxCEnchantType.ALL, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("DivineIntervention", "armor")) {
            data = EnchantConfigHandler.getData("DivineIntervention", "armor");
            enchant.registerEnchant("divineIntervention", EnumHxCEnchantType.ARMOR_TORSO, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("Examine", "weapon")) {
            data = EnchantConfigHandler.getData("Examine", "weapon");
            enchant.registerEnchant("examine", EnumHxCEnchantType.SWORD, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("SoulTear", "weapon")[0]));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("FlameTouch", "other")) {
            data = EnchantConfigHandler.getData("FlameTouch", "other");
            enchant.registerEnchant("flameTouch", EnumHxCEnchantType.DIGGER, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("VoidTouch", "other")[0]));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("Fly" , "armor")) {
            data = EnchantConfigHandler.getData("Fly" , "armor");
            enchant.registerEnchant("fly", EnumHxCEnchantType.ARMOR_FEET, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("LeadFooted", "armor")[0], (int) EnchantConfigHandler.getData("JumpBoost", "armor")[0]));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("JumpBoost", "armor")) {
            data = EnchantConfigHandler.getData("JumpBoost", "armor");
            enchant.registerEnchant("jumpBoost", EnumHxCEnchantType.ARMOR_LEGS, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("LeadFooted", "armor")[0], (int) EnchantConfigHandler.getData("Fly", "armor")[0]));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("LifeSteal", "weapon")) {
            data = EnchantConfigHandler.getData("LifeSteal", "weapon");
            enchant.registerEnchant("lifeSteal", EnumHxCEnchantType.SWORD, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("LeadFooted", "armor")) {
            data = EnchantConfigHandler.getData("LeadFooted", "armor");
            enchant.registerEnchant("leadFooted", EnumHxCEnchantType.ARMOR_FEET, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("Swiftness", "armor")[0], (int) EnchantConfigHandler.getData("Fly", "armor")[0], (int) EnchantConfigHandler.getData("JumpBoost", "armor")[0], (int) EnchantConfigHandler.getData("FeatherFall", "armor")[0], Enchantment.featherFalling.effectId));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("Poison", "weapon")) {
            data = EnchantConfigHandler.getData("Poison", "weapon");
            enchant.registerEnchant("poison", EnumHxCEnchantType.WEAPON, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("Piercing", "weapon")) {
            data = EnchantConfigHandler.getData("Piercing", "weapon");
            enchant.registerEnchant("piercing", EnumHxCEnchantType.WEAPON, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("Repair", "other")) {
            data = EnchantConfigHandler.getData("Repair", "other");
            enchant.registerEnchant("repair", EnumHxCEnchantType.BREAKABLE, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("Shroud" , "armor")){
            data = EnchantConfigHandler.getData("Shroud" , "armor");
            enchant.registerEnchant("shroud", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("SoulTear", "weapon")){
            data = EnchantConfigHandler.getData("SoulTear", "weapon");
            enchant.registerEnchant("soulTear", EnumHxCEnchantType.SWORD, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("SCurse", "weapon")){
            data = EnchantConfigHandler.getData("SCurse", "weapon");
            enchant.registerEnchant("slayersCurse", EnumHxCEnchantType.SWORD, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("Stealth", "armor")){
            data = EnchantConfigHandler.getData("Stealth", "armor");
            enchant.registerEnchant("stealth", EnumHxCEnchantType.ARMOR_FEET, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("Swiftness", "armor")){
            data = EnchantConfigHandler.getData("Swiftness", "armor");
            enchant.registerEnchant("swiftness", EnumHxCEnchantType.ARMOR_LEGS, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("LeadFooted", "armor")[0]));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("Vampirism", "weapon")) {
            data = EnchantConfigHandler.getData("Vampirism", "weapon");
            enchant.registerEnchant("vampirism", EnumHxCEnchantType.SWORD, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("Vitality", "armor")) {
            data = EnchantConfigHandler.getData("Vitality", "armor");
            enchant.registerEnchant("vitality", EnumHxCEnchantType.ARMOR_TORSO, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("Vorpal", "weapon")) {
            data = EnchantConfigHandler.getData("Vorpal", "weapon");
            enchant.registerEnchant("vorpal", EnumHxCEnchantType.SWORD, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("WitherProtection", "armor")) {
            data = EnchantConfigHandler.getData("WitherProtection", "armor");
            enchant.registerEnchant("witherProt", EnumHxCEnchantType.ARMOR_HEAD, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("SpeedMine", "tool")) {
            data = EnchantConfigHandler.getData("SpeedMine", "tool");
            enchant.registerEnchant("speedMine", EnumHxCEnchantType.DIGGER, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("VoidTouch", "tool")) {
            data = EnchantConfigHandler.getData("VoidTouch", "tool");
            enchant.registerEnchant("voidTouch", EnumHxCEnchantType.DIGGER, data[0], data[1], data[2], data[3], data[4], Arrays.asList(Enchantment.fortune.effectId, Enchantment.silkTouch.effectId));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("EnchLeech", "weapon")) {
            data = EnchantConfigHandler.getData("EnchLeech", "weapon");
            enchant.registerEnchant("enchLeech", EnumHxCEnchantType.SWORD, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("PipeMine", "tool")) {
            data = EnchantConfigHandler.getData("PipeMine", "tool");
            enchant.registerEnchant("pipeMine", EnumHxCEnchantType.DIGGER, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("FeatherFall", "armor")) {
            data = EnchantConfigHandler.getData("FeatherFall", "armor");
            enchant.registerEnchant("featherFall", EnumHxCEnchantType.ARMOR_FEET, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("MeteorFall", "armor")[0]));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("MeteorFall", "armor")) {
            data = EnchantConfigHandler.getData("MeteorFall", "armor");
            enchant.registerEnchant("meteorFall", EnumHxCEnchantType.ARMOR_FEET, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("FeatherFall", "armor")[0], Enchantment.featherFalling.effectId, (int) EnchantConfigHandler.getData("Fly", "armor")[0]));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("OverCharge", "weapon")) {
            data = EnchantConfigHandler.getData("OverCharge", "weapon");
            enchant.registerEnchant("overcharge", EnumHxCEnchantType.SWORD, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("ExplosiveDischarge", "armor")) {
            data = EnchantConfigHandler.getData("ExplosiveDischarge", "armor");
            enchant.registerEnchant("explosiveDischarge", EnumHxCEnchantType.ARMOR_TORSO, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("GaiaAura", "armor")) {
            data = EnchantConfigHandler.getData("GaiaAura", "armor");
            enchant.registerEnchant("gaiaAura", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("AuraToxic", "armor")[0], (int) EnchantConfigHandler.getData("AuraFiery", "armor")[0], (int) EnchantConfigHandler.getData("IcyAura", "armor")[0], (int) EnchantConfigHandler.getData("AuraDeadly", "armor")[0]));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("FlashStep", "armor")) {
            data = EnchantConfigHandler.getData("FlashStep", "armor");
            enchant.registerEnchant("flashStep", EnumHxCEnchantType.ARMOR_FEET, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("LeadFooted", "armor")[0]));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("FlamingArrow", "weapon")) {
            data = EnchantConfigHandler.getData("FlamingArrow", "weapon");
            enchant.registerEnchant("flamingArrow", EnumHxCEnchantType.BOW, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("HealingAura", "armor")) {
            data = EnchantConfigHandler.getData("HealingAura", "armor");
            enchant.registerEnchant("healingAura", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("AuraToxic", "armor")[0], (int) EnchantConfigHandler.getData("AuraDeadly", "armor")[0], (int) EnchantConfigHandler.getData("AuraFiery", "armor")[0]));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("Gluttony", "armor")) {
            data = EnchantConfigHandler.getData("Gluttony", "armor");
            enchant.registerEnchant("gluttony", EnumHxCEnchantType.ARMOR_HEAD, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("RepulsiveAura", "armor")) {
            data = EnchantConfigHandler.getData("RepulsiveAura", "armor");
            enchant.registerEnchant("repulsiveAura", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("AuraMagnetic", "armor")[0]));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("AuraMagnetic", "armor")) {
            data = EnchantConfigHandler.getData("AuraMagnetic", "armor");
            enchant.registerEnchant("auraMagnetic", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("AuraRepulsive", "armor")[0]));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("IcyAura", "armor")) {
            data = EnchantConfigHandler.getData("IcyAura", "armor");
            enchant.registerEnchant("icyAura", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("AuraFiery", "armor")[0]));
            Enchs++;
        }
        if (EnchantConfigHandler.isEnabled("LightningArrow", "weapon")) {
            data = EnchantConfigHandler.getData("LightningArrow", "weapon");
            enchant.registerEnchant("lightningArrow", EnumHxCEnchantType.BOW, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("Zeus", "weapon")[0]));
            Enchs++;
        }
        if (HxCKDMS.HxCCore.Configs.Configurations.DebugMode)
            LogHelper.warn(Enchs + " enchantments have been registered.", Reference.MOD_NAME);
    }

    @Override
    public void registerEnchant(String name, EnumHxCEnchantType type, int defaultID, int maxLevel, int rarity, int cost, long requiredCharge, List<Integer> bannedEnchantIds) {

    }

    @Override
    public long getChargeRequirement() {
        return 0;
    }
}
