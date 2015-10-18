package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.HxCEnchants.EnchantConfigHandler;
import HxCKDMS.HxCEnchants.lib.Reference;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;

import java.util.Arrays;

@SuppressWarnings("all")
public class Enchants {
    private static short[] data;
    public static HxCEnch enchant = new HxCEnch();
    public static void load () {
        if (EnchantConfigHandler.isEnabled("AdrenalineBoost", "armor")) {
            data = EnchantConfigHandler.getData("AdrenalineBoost", "armor");
            enchant.registerEnchant("adrenalineBoost", EnumHxCEnchantType.ARMOR_HEAD, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("AuraDark", "armor")) {
            data = EnchantConfigHandler.getData("AuraDark", "armor");
            enchant.registerEnchant("darkAura", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("AuraDeadly", "armor")) {
            data = EnchantConfigHandler.getData("AuraDeadly", "armor");
            enchant.registerEnchant("deadlyAura", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("GaiaAura", "armor")[0], (int) EnchantConfigHandler.getData("HealingAura", "armor")[0]));
        }
        if (EnchantConfigHandler.isEnabled("AuraFiery", "armor")) {
            data = EnchantConfigHandler.getData("AuraFiery", "armor");
            enchant.registerEnchant("flameAura", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("HealingAura", "armor")[0], (int) EnchantConfigHandler.getData("GaiaAura", "armor")[0], (int) EnchantConfigHandler.getData("IcyAura", "armor")[0]));
        }
        if (EnchantConfigHandler.isEnabled("AuraThick", "armor")) {
            data = EnchantConfigHandler.getData("AuraThick", "armor");
            enchant.registerEnchant("thickAura", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("AuraToxic", "armor")) {
            data = EnchantConfigHandler.getData("AuraToxic", "armor");
            enchant.registerEnchant("toxicAura", EnumHxCEnchantType.ARMOR, data[0], data[2], data[1], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("GaiaAura", "armor")[0], (int) EnchantConfigHandler.getData("HealingAura", "armor")[0]));
        }
        if (EnchantConfigHandler.isEnabled("Regen", "armor")) {
            data = EnchantConfigHandler.getData("Regen", "armor");
            enchant.registerEnchant("regen", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("BattleHealing", "armor")[0]));
        }
        if (EnchantConfigHandler.isEnabled("ArrowExplosive", "weapon")) {
            data = EnchantConfigHandler.getData("ArrowExplosive", "weapon");
            enchant.registerEnchant("arrowExplosive", EnumHxCEnchantType.BOW, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("Zeus", "weapon")) {
            data = EnchantConfigHandler.getData("Zeus", "weapon");
            enchant.registerEnchant("arrowZeus", EnumHxCEnchantType.BOW, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("LightningArrow", "weapon")[0]));
        }
        if (EnchantConfigHandler.isEnabled("ArrowSeeking", "weapon")) {
            data = EnchantConfigHandler.getData("ArrowSeeking", "weapon");
            enchant.registerEnchant("arrowSeeking", EnumHxCEnchantType.BOW, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("BattleHealing", "armor")) {
            data = EnchantConfigHandler.getData("BattleHealing", "armor");
            enchant.registerEnchant("battleHeal", EnumHxCEnchantType.ARMOR_TORSO, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("Regen", "armor")[0]));
        }
        if (EnchantConfigHandler.isEnabled("Bound", "other")) {
            data = EnchantConfigHandler.getData("Bound", "other");
            enchant.registerEnchant("bound", EnumHxCEnchantType.ALL, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("DivineIntervention", "armor")) {
            data = EnchantConfigHandler.getData("DivineIntervention", "armor");
            enchant.registerEnchant("divineIntervention", EnumHxCEnchantType.ARMOR_TORSO, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("Examine", "weapon")) {
            data = EnchantConfigHandler.getData("Examine", "weapon");
            enchant.registerEnchant("examine", EnumHxCEnchantType.SWORD, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("SoulTear", "weapon")[0]));
        }
        if (EnchantConfigHandler.isEnabled("FlameTouch", "other")) {
            data = EnchantConfigHandler.getData("FlameTouch", "tool");
            enchant.registerEnchant("flameTouch", EnumHxCEnchantType.DIGGER, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("VoidTouch", "tool")[0]));
        }
        if (EnchantConfigHandler.isEnabled("Fly" , "armor")) {
            data = EnchantConfigHandler.getData("Fly" , "armor");
            enchant.registerEnchant("fly", EnumHxCEnchantType.ARMOR_FEET, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("LeadFooted", "armor")[0], (int) EnchantConfigHandler.getData("JumpBoost", "armor")[0]));
        }
        if (EnchantConfigHandler.isEnabled("JumpBoost", "armor")) {
            data = EnchantConfigHandler.getData("JumpBoost", "armor");
            enchant.registerEnchant("jumpBoost", EnumHxCEnchantType.ARMOR_LEGS, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("LeadFooted", "armor")[0], (int) EnchantConfigHandler.getData("Fly", "armor")[0]));
        }
        if (EnchantConfigHandler.isEnabled("LifeSteal", "weapon")) {
            data = EnchantConfigHandler.getData("LifeSteal", "weapon");
            enchant.registerEnchant("lifeSteal", EnumHxCEnchantType.SWORD, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("LeadFooted", "armor")) {
            data = EnchantConfigHandler.getData("LeadFooted", "armor");
            enchant.registerEnchant("leadFooted", EnumHxCEnchantType.ARMOR_FEET, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("Swiftness", "armor")[0], (int) EnchantConfigHandler.getData("Fly", "armor")[0], (int) EnchantConfigHandler.getData("JumpBoost", "armor")[0], (int) EnchantConfigHandler.getData("FeatherFall", "armor")[0], Enchantment.featherFalling.effectId));
        }
        if (EnchantConfigHandler.isEnabled("Poison", "weapon")) {
            data = EnchantConfigHandler.getData("Poison", "weapon");
            enchant.registerEnchant("poison", EnumHxCEnchantType.WEAPON, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("Piercing", "weapon")) {
            data = EnchantConfigHandler.getData("Piercing", "weapon");
            enchant.registerEnchant("piercing", EnumHxCEnchantType.WEAPON, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("Repair", "other")) {
            data = EnchantConfigHandler.getData("Repair", "other");
            enchant.registerEnchant("repair", EnumHxCEnchantType.BREAKABLE, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("Shroud" , "armor")){
            data = EnchantConfigHandler.getData("Shroud" , "armor");
            enchant.registerEnchant("shroud", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("SoulTear", "weapon")){
            data = EnchantConfigHandler.getData("SoulTear", "weapon");
            enchant.registerEnchant("soulTear", EnumHxCEnchantType.SWORD, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("SCurse", "weapon")){
            data = EnchantConfigHandler.getData("SCurse", "weapon");
            enchant.registerEnchant("slayersCurse", EnumHxCEnchantType.SWORD, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("Stealth", "armor")){
            data = EnchantConfigHandler.getData("Stealth", "armor");
            enchant.registerEnchant("stealth", EnumHxCEnchantType.ARMOR_FEET, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("Swiftness", "armor")){
            data = EnchantConfigHandler.getData("Swiftness", "armor");
            enchant.registerEnchant("swiftness", EnumHxCEnchantType.ARMOR_LEGS, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("LeadFooted", "armor")[0]));
        }
        if (EnchantConfigHandler.isEnabled("Vampirism", "weapon")) {
            data = EnchantConfigHandler.getData("Vampirism", "weapon");
            enchant.registerEnchant("vampirism", EnumHxCEnchantType.SWORD, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("Vitality", "armor")) {
            data = EnchantConfigHandler.getData("Vitality", "armor");
            enchant.registerEnchant("vitality", EnumHxCEnchantType.ARMOR_TORSO, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("Vorpal", "weapon")) {
            data = EnchantConfigHandler.getData("Vorpal", "weapon");
            enchant.registerEnchant("vorpal", EnumHxCEnchantType.SWORD, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("WitherProtection", "armor")) {
            data = EnchantConfigHandler.getData("WitherProtection", "armor");
            enchant.registerEnchant("witherProt", EnumHxCEnchantType.ARMOR_HEAD, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("SpeedMine", "other")) {
            data = EnchantConfigHandler.getData("SpeedMine", "tool");
            enchant.registerEnchant("speedMine", EnumHxCEnchantType.DIGGER, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("VoidTouch", "other")) {
            data = EnchantConfigHandler.getData("VoidTouch", "tool");
            enchant.registerEnchant("voidTouch", EnumHxCEnchantType.DIGGER, data[0], data[1], data[2], data[3], data[4], Arrays.asList(Enchantment.fortune.effectId, Enchantment.silkTouch.effectId));
        }
        if (EnchantConfigHandler.isEnabled("EnchLeech", "weapon")) {
            data = EnchantConfigHandler.getData("EnchLeech", "weapon");
            enchant.registerEnchant("enchLeech", EnumHxCEnchantType.SWORD, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
//        if (EnchantConfigHandler.isEnabled("PipeMine", "other")) {
//            data = EnchantConfigHandler.getData("PipeMine", "tool");
//            enchant.registerEnchant("pipeMine", EnumHxCEnchantType.DIGGER, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
//        }
        if (EnchantConfigHandler.isEnabled("FeatherFall", "armor")) {
            data = EnchantConfigHandler.getData("FeatherFall", "armor");
            enchant.registerEnchant("featherFall", EnumHxCEnchantType.ARMOR_FEET, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("MeteorFall", "armor")[0]));
        }
        if (EnchantConfigHandler.isEnabled("MeteorFall", "armor")) {
            data = EnchantConfigHandler.getData("MeteorFall", "armor");
            enchant.registerEnchant("meteorFall", EnumHxCEnchantType.ARMOR_FEET, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("FeatherFall", "armor")[0], Enchantment.featherFalling.effectId, (int) EnchantConfigHandler.getData("Fly", "armor")[0]));
        }
        if (EnchantConfigHandler.isEnabled("OverCharge", "weapon")) {
            data = EnchantConfigHandler.getData("OverCharge", "weapon");
            enchant.registerEnchant("overcharge", EnumHxCEnchantType.SWORD, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("ExplosiveDischarge", "armor")) {
            data = EnchantConfigHandler.getData("ExplosiveDischarge", "armor");
            enchant.registerEnchant("explosiveDischarge", EnumHxCEnchantType.ARMOR_TORSO, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("GaiaAura", "armor")) {
            data = EnchantConfigHandler.getData("GaiaAura", "armor");
            enchant.registerEnchant("gaiaAura", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("AuraToxic", "armor")[0], (int) EnchantConfigHandler.getData("AuraFiery", "armor")[0], (int) EnchantConfigHandler.getData("IcyAura", "armor")[0], (int) EnchantConfigHandler.getData("AuraDeadly", "armor")[0]));
        }
        if (EnchantConfigHandler.isEnabled("FlashStep", "armor")) {
            data = EnchantConfigHandler.getData("FlashStep", "armor");
            enchant.registerEnchant("flashStep", EnumHxCEnchantType.ARMOR_FEET, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("LeadFooted", "armor")[0]));
        }
        if (EnchantConfigHandler.isEnabled("FlamingArrow", "weapon")) {
            data = EnchantConfigHandler.getData("FlamingArrow", "weapon");
            enchant.registerEnchant("flamingArrow", EnumHxCEnchantType.BOW, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("HealingAura", "armor")) {
            data = EnchantConfigHandler.getData("HealingAura", "armor");
            enchant.registerEnchant("healingAura", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("AuraToxic", "armor")[0], (int) EnchantConfigHandler.getData("AuraDeadly", "armor")[0], (int) EnchantConfigHandler.getData("AuraFiery", "armor")[0]));
        }
        if (EnchantConfigHandler.isEnabled("Gluttony", "armor")) {
            data = EnchantConfigHandler.getData("Gluttony", "armor");
            enchant.registerEnchant("gluttony", EnumHxCEnchantType.ARMOR_HEAD, data[0], data[1], data[2], data[3], data[4], Arrays.asList());
        }
        if (EnchantConfigHandler.isEnabled("RepulsiveAura", "armor")) {
            data = EnchantConfigHandler.getData("RepulsiveAura", "armor");
            enchant.registerEnchant("repulsiveAura", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("AuraMagnetic", "armor")[0]));
        }
        if (EnchantConfigHandler.isEnabled("AuraMagnetic", "armor")) {
            data = EnchantConfigHandler.getData("AuraMagnetic", "armor");
            enchant.registerEnchant("auraMagnetic", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("RepulsiveAura", "armor")[0]));
        }
        if (EnchantConfigHandler.isEnabled("IcyAura", "armor")) {
            data = EnchantConfigHandler.getData("IcyAura", "armor");
            enchant.registerEnchant("icyAura", EnumHxCEnchantType.ARMOR, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("AuraFiery", "armor")[0]));
        }
        if (EnchantConfigHandler.isEnabled("LightningArrow", "weapon")) {
            data = EnchantConfigHandler.getData("LightningArrow", "weapon");
            enchant.registerEnchant("lightningArrow", EnumHxCEnchantType.BOW, data[0], data[1], data[2], data[3], data[4], Arrays.asList((int) EnchantConfigHandler.getData("Zeus", "weapon")[0]));
        }
        HxCEnch.hxcEnchants.forEach(z -> MinecraftForge.EVENT_BUS.register(z));
        LogHelper.info(HxCEnch.hxcEnchants.size() + " enchantments have been registered.", Reference.MOD_NAME);
    }
}
