package HxCKDMS.HxCEnchants.Configurations;

import HxCKDMS.HxCEnchants.api.HxCEnchantment;
import hxckdms.hxcconfig.Config;
import net.minecraft.enchantment.Enchantment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Config
@SuppressWarnings("all")
public class Configurations {
    public static boolean ExplosionDestroysTerrain = false, AurasAffectPlayers = true,  enableCustomBlocks, AutosmeltMultipliesWithOres = true, AutosmeltWithFortune = true;
    public static float PiercingPercent = 0.15f, GaiasAuraSpeed = 2.5f, SpeedTweak = 0.0287f, VitalityPerLevel = 5.0f;
    public static int updateTime = 1000, guiVersion = 1, repairTimer = 120, regenTimer = 45, tableRange = 3, AuraUpdateDelay = 3000;
    @Config.comment(value = "Enchant Level / this (Level/2 default)")
    public static float EarthEaterDepthModifier = 2.0f, EarthEaterHeightModifier = 1.75f, EarthEaterWidthModifier = 1.5f;
    public static short StartingID = 400;
    public static boolean usecustomenum = true, blacklistEnchantsFromEnchantingPlus = false, EnableKeybinds = true, EnableCoordinatesInGUIs, overChargeDecays;

    public static List<String> VoidedItems = Arrays.asList(new String[]{"minecraft:cobblestone", "minecraft:dirt", "minecraft:gravel"});

    public static LinkedHashMap<String, DummyEnchant> enchantments = new LinkedHashMap<>();

    //Enabled, ID, Level, Weight, Cost, Charge Cost
// 0     1        2           3            4            5        6       7          8          9       10     11
//ALL, ARMOR, ARMOR_FEET, ARMOR_LEGS, ARMOR_TORSO, ARMOR_HEAD, WEAPON, DIGGER, FISHING_ROD, BREAKABLE, BOW, SWORD
    public boolean init() {
        if (enchantments.size() > 0) return true;
//TODO new planned enchants include Devourer gains damage from kills, Decapitating, Reaping deals killing blows at cost of hunger
//        enchantments.put("Bound", new DummyEnchant("Bound", false, StartingID + enchantments.size(),  (byte) 3, (byte) 5, (byte) 45, (byte) 0));
        enchantments.put("FlameTouch", new DummyEnchant("FlameTouch", true, StartingID + enchantments.size(),  (byte) 2, (byte) 2, (byte) 30, (byte) 7));
        enchantments.put("Repair", new DummyEnchant("Repair", true, StartingID + enchantments.size(),  (byte) 4, (byte) 1, (byte) 15, (byte) 9));
        enchantments.put("EarthEater", new DummyEnchant("EarthEater", true, StartingID + enchantments.size(), (byte) 10, (byte) 2, (byte) 30, (byte) 7));
        enchantments.put("SpeedMine", new DummyEnchant("SpeedMine", true, StartingID + enchantments.size(),  (byte) 4, (byte) 2, (byte) 30, (byte) 7));
        enchantments.put("VoidTouch", new DummyEnchant("VoidTouch", true, StartingID + enchantments.size(),  (byte) 1, (byte) 2, (byte) 30, (byte) 9));
        enchantments.put("AdrenalineBoost", new DummyEnchant("AdrenalineBoost", true, StartingID + enchantments.size(),  (byte) 4, (byte) 10, (byte) 30, (byte) 5));
        enchantments.put("AuraFiery", new DummyEnchant("AuraFiery", true, StartingID + enchantments.size(), (byte) 10, (byte) 5, (byte) 30, (byte) 1));
        enchantments.put("AuraDeadly", new DummyEnchant("AuraDeadly", true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 50, (byte) 1));
        enchantments.put("AuraDark", new DummyEnchant("AuraDark", true, StartingID + enchantments.size(), (byte) 10, (byte) 15, (byte) 20, (byte) 1));
        enchantments.put("AuraThick", new DummyEnchant("AuraThick", true, StartingID + enchantments.size(), (byte) 10, (byte) 10, (byte) 20, (byte) 1));
        enchantments.put("AuraToxic", new DummyEnchant("AuraToxic", true, StartingID + enchantments.size(), (byte) 10, (byte) 5, (byte) 35, (byte) 1));
        enchantments.put("BattleHealing", new DummyEnchant("BattleHealing", true, StartingID + enchantments.size(),  (byte) 4, (byte) 3, (byte) 40, (byte) 4));
        enchantments.put("DivineIntervention", new DummyEnchant("DivineIntervention", true, StartingID + enchantments.size(),  (byte) 5, (byte) 1, (byte) 45, (byte) 4));
        enchantments.put("Fly", new DummyEnchant("Fly", true, StartingID + enchantments.size(),  (byte) 1, (byte) 1, (byte) 55, (byte) 2));
        enchantments.put("JumpBoost", new DummyEnchant("JumpBoost", true, StartingID + enchantments.size(),  (byte) 4, (byte) 10, (byte) 10, (byte) 3));
        enchantments.put("Regen", new DummyEnchant("Regen", true, StartingID + enchantments.size(),  (byte) 4, (byte) 1, (byte) 15, (byte) 1));
//        enchantments.put("Shroud", new DummyEnchant("Shroud", true, StartingID + enchantments.size(),  (byte) 1, (byte) 2, (byte) 45, (byte) 1));
        enchantments.put("Stealth", new DummyEnchant("Stealth", true, StartingID + enchantments.size(),  (byte) 2, (byte) 6, (byte) 40, (byte) 2));
        enchantments.put("Swiftness", new DummyEnchant("Swiftness", true, StartingID + enchantments.size(),  (byte) 4, (byte) 10, (byte) 20, (byte) 3));
        enchantments.put("Vitality", new DummyEnchant("Vitality", true, StartingID + enchantments.size(),  (byte) 4, (byte) 4, (byte) 30, (byte) 1));
        enchantments.put("WitherProtection", new DummyEnchant("WitherProtection", true, StartingID + enchantments.size(),  (byte) 4, (byte) 8, (byte) 40, (byte) 5));
        enchantments.put("MeteorFall", new DummyEnchant("MeteorFall", true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, (byte) 2));
        enchantments.put("FeatherFall", new DummyEnchant("FeatherFall", true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, (byte) 2));
        enchantments.put("FlashStep", new DummyEnchant("FlashStep", true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, (byte) 2));
        enchantments.put("HealingAura", new DummyEnchant("HealingAura", true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, (byte) 1));
        enchantments.put("RepulsiveAura", new DummyEnchant("RepulsiveAura", true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, (byte) 1));
        enchantments.put("AuraMagnetic", new DummyEnchant("AuraMagnetic", true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, (byte) 1));
        enchantments.put("GaiaAura", new DummyEnchant("GaiaAura", true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, (byte) 1));
        enchantments.put("IcyAura", new DummyEnchant("IcyAura", true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, (byte) 1));
        enchantments.put("Gluttony", new DummyEnchant("Gluttony", true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, (byte) 5));
        enchantments.put("ExplosiveDischarge", new DummyEnchant("ExplosiveDischarge", true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, (byte) 4));
        enchantments.put("ChargedAura", new DummyEnchant("ChargedAura", true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, (byte) 1));
        enchantments.put("Nightvision", new DummyEnchant("Nightvision", true, StartingID + enchantments.size(),  (byte) 1, (byte) 1, (byte) 30,  (byte) 5));
        enchantments.put("LightningArrow", new DummyEnchant("LightningArrow", true, StartingID + enchantments.size(),  (byte) 5, (byte) 1, (byte) 40, (byte) 10));
        enchantments.put("ArrowExplosive", new DummyEnchant("ArrowExplosive", true, StartingID + enchantments.size(),  (byte) 4, (byte) 2, (byte) 40, (byte) 10));
        enchantments.put("ArrowZeus", new DummyEnchant("ArrowZeus", true, StartingID + enchantments.size(),  (byte) 1, (byte) 5, (byte) 30, (byte) 10));
        enchantments.put("ArrowSeeking", new DummyEnchant("ArrowSeeking", true, StartingID + enchantments.size(),  (byte) 4, (byte) 1, (byte) 45, (byte) 10));
        enchantments.put("LifeSteal", new DummyEnchant("LifeSteal", true, StartingID + enchantments.size(),  (byte) 5, (byte) 7, (byte) 35, (byte) 11));
        enchantments.put("Piercing", new DummyEnchant("Piercing", true, StartingID + enchantments.size(),  (byte) 5, (byte) 1, (byte) 40, (byte) 6));
        enchantments.put("Poison", new DummyEnchant("Poison", true, StartingID + enchantments.size(),  (byte) 4, (byte) 10, (byte) 25, (byte) 6));
//        enchantments.put("SoulTear", new DummyEnchant("SoulTear", true, StartingID + enchantments.size(),  (byte) 4, (byte) 2, (byte) 30, (byte) 11));
        enchantments.put("SCurse", new DummyEnchant("SCurse", true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 55, (byte) 11));
        enchantments.put("Vampirism", new DummyEnchant("Vampirism", true, StartingID + enchantments.size(),  (byte) 5, (byte) 3, (byte) 45, (byte) 11));
        enchantments.put("Vorpal", new DummyEnchant("Vorpal", true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 55, (byte) 11));
        enchantments.put("OverCharge", new DummyEnchant("OverCharge", true, StartingID + enchantments.size(),  (byte) 4, (byte) 2, (byte) 30, (byte) 11));
        enchantments.put("EnchLeech", new DummyEnchant("EnchLeech", true, StartingID + enchantments.size(),  (byte) 4, (byte) 2, (byte) 30, (byte) 11));
        enchantments.put("Examine", new DummyEnchant("Examine", true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 55,  (byte) 11));
        enchantments.put("FlamingArrow", new DummyEnchant("FlamingArrow", true, StartingID + enchantments.size(),  (byte) 3, (byte) 7, (byte) 20, (byte) 10));
        enchantments.put("BloodRazor", new DummyEnchant("BloodRazor", true, StartingID + enchantments.size(),  (byte) 5, (byte) 2, (byte) 30, (byte) 6));
        enchantments.put("Saturation", new DummyEnchant("Saturation", true, StartingID + enchantments.size(),  (byte) 5, (byte) 2, (byte) 30, (byte) 5));

        enchantments.get("AuraDeadly").setIncompats(Arrays.asList((short) enchantments.get("GaiaAura").id, (short) enchantments.get("HealingAura").id));
        enchantments.get("AuraFiery").setIncompats(Arrays.asList((short) enchantments.get("GaiaAura").id, (short) enchantments.get("HealingAura").id, (short) enchantments.get("IcyAura").id));
        enchantments.get("Regen").setIncompats(Arrays.asList((short) enchantments.get("BattleHealing").id));
        enchantments.get("ArrowZeus").setIncompats(Arrays.asList((short) enchantments.get("LightningArrow").id));
        enchantments.get("BattleHealing").setIncompats(Arrays.asList((short) enchantments.get("Regen").id));
        enchantments.get("Gluttony").setIncompats(Arrays.asList((short) enchantments.get("Saturation").id));
        enchantments.get("Saturation").setIncompats(Arrays.asList((short) enchantments.get("Gluttony").id));
//        enchantments.get("Examine").setIncompats(Arrays.asList((short) enchantments.get("SoulTear").id));
//        enchantments.get("SoulTear").setIncompats(Arrays.asList((short) enchantments.get("Examine").id));
        enchantments.get("FlameTouch").setIncompats(Arrays.asList((short) enchantments.get("VoidTouch").id, (short) Enchantment.silkTouch.effectId));
        enchantments.get("Fly").setIncompats(Arrays.asList((short) enchantments.get("JumpBoost").id));
        enchantments.get("JumpBoost").setIncompats(Arrays.asList((short) enchantments.get("Fly").id));
        enchantments.get("MeteorFall").setIncompats(Arrays.asList((short) enchantments.get("BattleHealing").id));
        enchantments.get("FeatherFall").setIncompats(Arrays.asList((short) enchantments.get("BattleHealing").id));
        enchantments.get("VoidTouch").setIncompats(Arrays.asList((short) enchantments.get("FlameTouch").id, (short) Enchantment.fortune.effectId, (short) Enchantment.silkTouch.effectId));
        enchantments.get("GaiaAura").setIncompats(Arrays.asList((short) enchantments.get("BattleHealing").id));
        enchantments.get("HealingAura").setIncompats(Arrays.asList((short) enchantments.get("AuraToxic").id, (short) enchantments.get("AuraDeadly").id, (short) enchantments.get("AuraFiery").id));
        enchantments.get("RepulsiveAura").setIncompats(Arrays.asList((short) enchantments.get("AuraMagnetic").id));
        enchantments.get("IcyAura").setIncompats(Arrays.asList((short) enchantments.get("AuraFiery").id));
        enchantments.get("AuraMagnetic").setIncompats(Arrays.asList((short) enchantments.get("RepulsiveAura").id));
        enchantments.get("LightningArrow").setIncompats(Arrays.asList((short) enchantments.get("BattleHealing").id));

        return false;
    }

    public class DummyEnchant {
        public boolean enabled;
        public String name = "error";
        public int id, maxLevel, minLevel, weight, cost;
        public byte type;
        public List<Short> incompatable_enchants = new ArrayList<>();

        public DummyEnchant() {}

        public DummyEnchant(String name, boolean enabled, int id, byte maxlvl, byte weight, byte cost, byte type) {
            this.name = name;
            this.enabled = enabled;
            this.id = id;
            this.maxLevel = maxlvl;
            this.minLevel = 1;
            this.weight = weight;
            this.cost = cost;
            this.type = type;
        }

        public void setIncompats(List<Short> incompatEnchs) {
            this.incompatable_enchants = incompatEnchs;
        }

        public void init() {
            String newName = name.substring(0,1).toLowerCase() + name.substring(1);
            if (enabled)
                new HxCEnchantment(this.id, newName, (byte) this.weight, this.type, (byte) this.maxLevel, (byte) this.cost, this.incompatable_enchants);
        }
    }
}
