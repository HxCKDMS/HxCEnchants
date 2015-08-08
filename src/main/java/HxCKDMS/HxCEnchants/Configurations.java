package HxCKDMS.HxCEnchants;

import HxCKDMS.HxCCore.api.Configuration.Config;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class Configurations {
    @Config.Boolean
    public static boolean ExplosionDestroysTerrain = false, PlayerAuraDeadly = true, PlayerAuraFiery = true, PlayerAuraThick = true, PlayerAuraDark = true, PlayerAuraToxic = true,  enableChargesSystem = true, PlayerIcyAura = true, PlayerHealingAura = true;
    @Config.Float
    public static float PiercingPercent = 0.15f;
    @Config.Integer
    public static int updateTime = 10, guiVersion = 1, idShift = 0;
    @Config.Boolean
    public static boolean notice2 = true, blackListFromEPlus, disableKeybinds;

    @Config.List
    public static List<String> VoidedItems = Arrays.asList("minecraft:cobblestone", "minecraft:dirt", "minecraft:gravel");

    @Config.Map(description = "Enabled, ID, Weight, Cost, Charge Cost, Special", category = "Enchants")
    public static LinkedHashMap<String, String> Enchants = new LinkedHashMap<>();
    @Config.Map(description = "Enabled, ID, Weight, Cost, Charge Cost, Special", category = "WeaponEnchants")
    public static LinkedHashMap<String, String> WeaponEnchants = new LinkedHashMap<>();
    @Config.Map(description = "Enabled, ID, Weight, Cost, Charge Cost, Special", category = "ToolEnchants")
    public static LinkedHashMap<String, String> ToolEnchants = new LinkedHashMap<>();
    @Config.Map(description = "Enabled, ID, Weight, Cost, Charge Cost, Special", category = "ArmourEnchants")
    public static LinkedHashMap<String, String> ArmorEnchants = new LinkedHashMap<>();

    static {
        Enchants.put("Bound", "false, 100, 3, 5, 45, 0");
        Enchants.put("FlameTouch", "true, 101, 4, 2, 30, 10");
        Enchants.put("Repair", "true, 102, 4, 1, 15, 5, 60");

        ArmorEnchants.put("AdrenalineBoost", "true, 110, 4, 10, 30, 50");
        ArmorEnchants.put("AuraFiery", "true, 111, 10, 5, 30, 15");
        ArmorEnchants.put("AuraDeadly", "true, 112, 10, 1, 50, 50");
        ArmorEnchants.put("AuraDark", "true, 113, 10, 15, 20, 5");
        ArmorEnchants.put("AuraThick", "true, 114, 10, 10, 20, 5");
        ArmorEnchants.put("AuraToxic", "true, 115, 10, 5, 35, 20");
        ArmorEnchants.put("BattleHealing", "true, 116, 4, 3, 40, 50");
        ArmorEnchants.put("DivineIntervention", "true, 117, 5, 1, 45, 0");
        ArmorEnchants.put("Fly", "true, 118, 1, 1, 55, 50");
        ArmorEnchants.put("JumpBoost", "true, 119, 4, 10, 10, 2");
        ArmorEnchants.put("LeadFooted", "false, 120, 1, 10, 20, 0");
        ArmorEnchants.put("Regen", "true, 121, 4, 1, 15, 5, 60");
        ArmorEnchants.put("Shroud", "false, 122, 1, 2, 45, 0");
        ArmorEnchants.put("Stealth", "true, 123, 2, 6, 40, 25");
        ArmorEnchants.put("Swiftness", "true, 124, 4, 10, 20, 25");
        ArmorEnchants.put("Vitality", "true, 125, 4, 4, 30, 50");
        ArmorEnchants.put("WitherProtection", "true, 126, 4, 8, 40, 25");
        ArmorEnchants.put("MeteorFall", "true, 127, 10, 1, 40, 50");
        ArmorEnchants.put("FeatherFall", "true, 128, 10, 1, 40, 15");
        ArmorEnchants.put("FlashStep", "true, 129, 10, 1, 40, 120");
        ArmorEnchants.put("HealingAura", "true, 130, 10, 1, 40, 15");
        ArmorEnchants.put("RepulsiveAura", "true, 131, 10, 1, 40, 15");
        ArmorEnchants.put("AuraMagnetic", "true, 132, 10, 1, 40, 15");
        ArmorEnchants.put("GaiaAura", "true, 133, 10, 1, 40, 15");
        ArmorEnchants.put("IcyAura", "true, 134, 10, 1, 40, 15");
        ArmorEnchants.put("Gluttony", "true, 135, 20, 1, 40, 20");
        ArmorEnchants.put("ExplosiveDischarge", "true, 136, 10, 1, 40, 120");

//        ToolEnchants.put("PipeMine", "false, 151, 4, 2, 30, 25");
        ToolEnchants.put("SpeedMine", "true, 152, 4, 2, 30, 5");
        ToolEnchants.put("VoidTouch", "true, 153, 4, 2, 30, 5");

        WeaponEnchants.put("LightningArrow", "true, 160, 5, 1, 40, 150");
        WeaponEnchants.put("ArrowExplosive", "true, 161, 4, 2, 40, 250");
        WeaponEnchants.put("Zeus", "true, 162, 1, 5, 30, 250");
        WeaponEnchants.put("ArrowSeeking", "true, 163, 4, 1, 45, 100");
        WeaponEnchants.put("LifeSteal", "true, 164, 5, 7, 35, 50");
        WeaponEnchants.put("Piercing", "true, 165, 5, 1, 40, 50");
        WeaponEnchants.put("Poison", "true, 166, 4, 10, 25, 35");
        WeaponEnchants.put("SoulTear", "false, 167, 4, 2, 30, 150");
        WeaponEnchants.put("SCurse", "true, 168, 10, 1, 55, 20, 3");
        WeaponEnchants.put("Vampirism", "true, 169, 5, 3, 45, 25");
        WeaponEnchants.put("Vorpal", "true, 170, 10, 1, 55, 20, 1");
        WeaponEnchants.put("OverCharge", "true, 171, 4, 2, 30, 500");
        WeaponEnchants.put("EnchLeech", "false, 172, 4, 2, 30, 100");
        WeaponEnchants.put("Examine", "true, 173, 10, 1, 55, 25");
        WeaponEnchants.put("FlamingArrow", "true, 174, 3, 7, 20, 30");
    }
}
