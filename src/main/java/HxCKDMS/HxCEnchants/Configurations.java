package HxCKDMS.HxCEnchants;

import HxCKDMS.HxCCore.api.Configuration.Config;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
@SuppressWarnings("all")
public class Configurations {
    @Config.Boolean
    public static boolean ExplosionDestroysTerrain = false, PlayerAuraDeadly = true, PlayerAuraFiery = true, PlayerAuraThick = true, PlayerAuraDark = true, PlayerAuraToxic = true,  enableChargesSystem = true, PlayerIcyAura = true, PlayerRepulsiveAura = true, PlayerHealingAura = true;
    @Config.Float
    public static float PiercingPercent = 0.15f;
    @Config.Integer
    public static int updateTime = 10, guiVersion = 1;
    @Config.Boolean
    public static boolean notice = true;

    @Config.List
    public static List<String> VoidedItems = Arrays.asList(new String[]{"minecraft:cobblestone", "minecraft:dirt", "minecraft:gravel"});

    @Config.Map(description = "Enabled, ID, Weight, Cost, Charge Cost, Special", category = "Enchants")
    public static LinkedHashMap<String, String> Enchants = new LinkedHashMap<String, String>();
    @Config.Map(description = "Enabled, ID, Weight, Cost, Charge Cost, Special", category = "WeaponEnchants")
    public static LinkedHashMap<String, String> WeaponEnchants = new LinkedHashMap<String, String>();
    @Config.Map(description = "Enabled, ID, Weight, Cost, Charge Cost, Special", category = "ToolEnchants")
    public static LinkedHashMap<String, String> ToolEnchants = new LinkedHashMap<String, String>();
    @Config.Map(description = "Enabled, ID, Weight, Cost, Charge Cost, Special", category = "ArmourEnchants")
    public static LinkedHashMap<String, String> ArmorEnchants = new LinkedHashMap<String, String>();

    static {
        Enchants.put("Bound", "false, 400, 3, 5, 45, 0");
        Enchants.put("FlameTouch", "true, 401, 4, 2, 30, 10");
        Enchants.put("Repair", "true, 402, 4, 1, 15, 5, 60");

        ArmorEnchants.put("AdrenalineBoost", "true, 410, 4, 10, 30, 50");
        ArmorEnchants.put("AuraFiery", "true, 411, 10, 5, 30, 15");
        ArmorEnchants.put("AuraDeadly", "true, 412, 10, 1, 50, 50");
        ArmorEnchants.put("AuraDark", "true, 413, 10, 15, 20, 5");
        ArmorEnchants.put("AuraThick", "true, 414, 10, 10, 20, 5");
        ArmorEnchants.put("AuraToxic", "true, 415, 10, 5, 35, 20");
        ArmorEnchants.put("BattleHealing", "true, 416, 4, 3, 40, 50");
        ArmorEnchants.put("DivineIntervention", "true, 417, 5, 1, 45, 0");
        ArmorEnchants.put("Fly", "true, 418, 1, 1, 55, 50");
        ArmorEnchants.put("JumpBoost", "true, 419, 4, 10, 10, 2");
        ArmorEnchants.put("LeadFooted", "false, 420, 1, 10, 20, 0");
        ArmorEnchants.put("Regen", "true, 421, 4, 1, 15, 5, 60");
        ArmorEnchants.put("Shroud", "false, 422, 1, 2, 45, 0");
        ArmorEnchants.put("Stealth", "true, 423, 2, 6, 40, 25");
        ArmorEnchants.put("Swiftness", "true, 424, 4, 10, 20, 25");
        ArmorEnchants.put("Vitality", "true, 425, 4, 4, 30, 50");
        ArmorEnchants.put("WitherProtection", "true, 426, 4, 8, 40, 25");
        ArmorEnchants.put("MeteorFall", "true, 427, 10, 1, 40, 50");
        ArmorEnchants.put("FeatherFall", "true, 428, 10, 1, 40, 15");
        ArmorEnchants.put("FlashStep", "true, 429, 10, 1, 40, 120");
        ArmorEnchants.put("HealingAura", "true, 430, 10, 1, 40, 15");
        ArmorEnchants.put("RepulsiveAura", "true, 431, 10, 1, 40, 15");
        ArmorEnchants.put("AuraMagnetic", "true, 432, 10, 1, 40, 15");
        ArmorEnchants.put("GaiaAura", "true, 433, 10, 1, 40, 15");
        ArmorEnchants.put("IcyAura", "true, 434, 10, 1, 40, 15");
        ArmorEnchants.put("Gluttony", "true, 435, 20, 1, 40, 20");
        ArmorEnchants.put("ExplosiveDischarge", "true, 436, 10, 1, 40, 120");

//        ToolEnchants.put("PipeMine", "false, 451, 4, 2, 30, 25");
        ToolEnchants.put("SpeedMine", "true, 452, 4, 2, 30, 5");
        ToolEnchants.put("VoidTouch", "true, 453, 4, 2, 30, 5");

        WeaponEnchants.put("LightningArrow", "true, 460, 5, 1, 40, 150");
        WeaponEnchants.put("ArrowExplosive", "true, 461, 4, 2, 40, 250");
        WeaponEnchants.put("Zeus", "true, 462, 1, 5, 30, 250");
        WeaponEnchants.put("ArrowSeeking", "true, 463, 4, 1, 45, 100");
        WeaponEnchants.put("LifeSteal", "true, 464, 5, 7, 35, 50");
        WeaponEnchants.put("Piercing", "true, 465, 5, 1, 40, 50");
        WeaponEnchants.put("Poison", "true, 466, 4, 10, 25, 35");
        WeaponEnchants.put("SoulTear", "false, 467, 4, 2, 30, 150");
        WeaponEnchants.put("SCurse", "true, 468, 10, 1, 55, 20, 3");
        WeaponEnchants.put("Vampirism", "true, 469, 5, 3, 45, 25");
        WeaponEnchants.put("Vorpal", "true, 470, 10, 1, 55, 20, 1");
        WeaponEnchants.put("OverCharge", "true, 471, 4, 2, 30, 500");
        WeaponEnchants.put("EnchLeech", "false, 472, 4, 2, 30, 100");
        WeaponEnchants.put("Examine", "true, 473, 10, 1, 55, 25");
        WeaponEnchants.put("FlamingArrow", "true, 474, 3, 7, 20, 30");
    }
}
