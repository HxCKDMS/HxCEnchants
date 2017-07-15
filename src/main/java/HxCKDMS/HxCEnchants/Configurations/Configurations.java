package HxCKDMS.HxCEnchants.Configurations;

import hxckdms.hxcconfig.Config;
import net.minecraft.enchantment.Enchantment;

import java.util.*;

@Config
@SuppressWarnings("all")
public class Configurations {
    public static boolean ExplosionDestroysTerrain = false, AurasAffectPlayers = true,  enableChargesSystem = true, enableCustomBlocks;
    public static float PiercingPercent = 0.15f, GaiasAuraSpeed = 2.5f, SpeedTweak = 0.0387f;
    public static int updateTime = 10, guiVersion = 1, repairTimer = 120, regenTimer = 45, tableRange = 3;
    @Config.comment(value = "Enchant Level / this (Level/2 default)")
    public static float EarthEaterDepthModifier = 2.0f, EarthEaterHeightModifier = 1.75f, EarthEaterWidthModifier = 1.5f;
    public static short StartingID = 400;
    public static boolean usecustomenum = true, blacklistEnchantsFromEnchantingPlus = false, EnableKeybinds = true, EnableCoordinatesInGUIs = false;

    public static List<String> VoidedItems = Arrays.asList(new String[]{"minecraft:cobblestone", "minecraft:dirt", "minecraft:gravel"});

    public static LinkedHashMap<String, DummyEnchant> enchantments = new LinkedHashMap<>();

    @Config.ignore
    public static LinkedHashMap<String, Boolean> EnabledEnchants = new LinkedHashMap<>();
    @Config.ignore
    public static LinkedHashMap<String, Short> EnchantIDs = new LinkedHashMap<>();
    @Config.ignore
    public static LinkedHashMap<String, Byte> EnchantLevels = new LinkedHashMap<>();
    @Config.ignore
    public static LinkedHashMap<String, Byte> EnchantWeight = new LinkedHashMap<>();
    @Config.ignore
    public static LinkedHashMap<String, Byte> EnchantCost = new LinkedHashMap<>();
    @Config.ignore
    public static LinkedHashMap<String, Long> EnchantChargeNeeded = new LinkedHashMap<>();

    //Enabled, ID, Level, Weight, Cost, Charge Cost
// 0     1        2           3            4            5        6       7          8          9       10     11
//ALL, ARMOR, ARMOR_FEET, ARMOR_LEGS, ARMOR_TORSO, ARMOR_HEAD, WEAPON, DIGGER, FISHING_ROD, BREAKABLE, BOW, SWORD
    public boolean init() {
        if (enchantments.size() > 0) return true;

        enchantments.put("Bound", new DummyEnchant(false, StartingID + enchantments.size(),  (byte) 3, (byte) 5, (byte) 45,  0l,  (byte) 0));
        enchantments.put("FlameTouch", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 4, (byte) 2, (byte) 30, 30l,  (byte) 7, Arrays.asList(EnchantIDs.get("VoidTouch"), (short) Enchantment.silkTouch.effectId)));
        enchantments.put("Repair", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 4, (byte) 1, (byte) 15, 25l,  (byte) 9));
        enchantments.put("EarthEater", new DummyEnchant(true, StartingID + enchantments.size(), (byte) 10, (byte) 2, (byte) 30, 30l,  (byte) 7));
        enchantments.put("SpeedMine", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 4, (byte) 2, (byte) 30, 30l,  (byte) 7));
        enchantments.put("VoidTouch", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 1, (byte) 2, (byte) 30, 30l,  (byte) 9, Arrays.asList(EnchantIDs.get("FlameTouch"), (short) Enchantment.fortune.effectId, (short) Enchantment.silkTouch.effectId)));
        enchantments.put("AdrenalineBoost", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 4, (byte) 10, (byte) 30, 30l,  (byte) 5));
        enchantments.put("AuraFiery", new DummyEnchant(true, StartingID + enchantments.size(), (byte) 10, (byte) 5, (byte) 30, 30l,  (byte) 1, Arrays.asList(EnchantIDs.get("GaiaAura"), EnchantIDs.get("HealingAura"), EnchantIDs.get("IcyAura"))));
        enchantments.put("AuraDeadly", new DummyEnchant(true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 50, 50l,  (byte) 1, Arrays.asList(EnchantIDs.get("GaiaAura"), EnchantIDs.get("HealingAura"))));
        enchantments.put("AuraDark", new DummyEnchant(true, StartingID + enchantments.size(), (byte) 10, (byte) 15, (byte) 20, 20l,  (byte) 1));
        enchantments.put("AuraThick", new DummyEnchant(true, StartingID + enchantments.size(), (byte) 10, (byte) 10, (byte) 20, 20l,  (byte) 1));
        enchantments.put("AuraToxic", new DummyEnchant(true, StartingID + enchantments.size(), (byte) 10, (byte) 5, (byte) 35, 35l,  (byte) 1));
        enchantments.put("BattleHealing", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 4, (byte) 3, (byte) 40, 40l,  (byte) 4, Collections.singletonList(EnchantIDs.get("Regen"))));
        enchantments.put("DivineIntervention", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 5, (byte) 1, (byte) 45, 45l,  (byte) 4));
        enchantments.put("Fly", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 1, (byte) 1, (byte) 55, 50l,  (byte) 2, Collections.singletonList(EnchantIDs.get("JumpBoost"))));
        enchantments.put("JumpBoost", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 4, (byte) 10, (byte) 10, 10l,  (byte) 3, Collections.singletonList(EnchantIDs.get("Fly"))));
        enchantments.put("Regen", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 4, (byte) 1, (byte) 15, 15l,  (byte) 1, Collections.singletonList(EnchantIDs.get("BattleHealing"))));
        enchantments.put("Shroud", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 1, (byte) 2, (byte) 45, 45l,  (byte) 1));
        enchantments.put("Stealth", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 2, (byte) 6, (byte) 40, 40l,  (byte) 2));
        enchantments.put("Swiftness", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 4, (byte) 10, (byte) 20, 20l,  (byte) 3));
        enchantments.put("Vitality", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 4, (byte) 4, (byte) 30, 30l,  (byte) 1));
        enchantments.put("WitherProtection", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 4, (byte) 8, (byte) 40, 40l,  (byte) 5));
        enchantments.put("MeteorFall", new DummyEnchant(true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, 40l,  (byte) 2, Collections.singletonList(EnchantIDs.get("BattleHealing"))));
        enchantments.put("FeatherFall", new DummyEnchant(true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, 40l,  (byte) 2, Collections.singletonList(EnchantIDs.get("BattleHealing"))));
        enchantments.put("FlashStep", new DummyEnchant(true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, 40l,  (byte) 2));
        enchantments.put("HealingAura", new DummyEnchant(true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, 40l,  (byte) 1, Arrays.asList(EnchantIDs.get("AuraToxic"), EnchantIDs.get("AuraDeadly"), EnchantIDs.get("AuraFiery"))));
        enchantments.put("RepulsiveAura", new DummyEnchant(true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, 40l,  (byte) 1, Collections.singletonList(EnchantIDs.get("AuraMagnetic"))));
        enchantments.put("AuraMagnetic", new DummyEnchant(true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, 40l,  (byte) 1, Collections.singletonList(EnchantIDs.get("RepulsiveAura"))));
        enchantments.put("GaiaAura", new DummyEnchant(true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, 40l,  (byte) 1, Collections.singletonList(EnchantIDs.get("BattleHealing"))));
        enchantments.put("IcyAura", new DummyEnchant(true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, 40l,  (byte) 1, Collections.singletonList(EnchantIDs.get("AuraFiery"))));
        enchantments.put("Gluttony", new DummyEnchant(true, StartingID + enchantments.size(), (byte) 20, (byte) 1, (byte) 40, 40l,  (byte) 5));
        enchantments.put("ExplosiveDischarge", new DummyEnchant(true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, 40l,  (byte) 4));
        enchantments.put("ChargedAura", new DummyEnchant(true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 40, 40l,  (byte) 1));
        enchantments.put("Nightvision", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 1, (byte) 1, (byte) 30,  0l,  (byte) 5));
        enchantments.put("LightningArrow", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 5, (byte) 1, (byte) 40, 40l, (byte) 10, Collections.singletonList(EnchantIDs.get("BattleHealing"))));
        enchantments.put("ArrowExplosive", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 4, (byte) 2, (byte) 40, 40l, (byte) 10));
        enchantments.put("ArrowZeus", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 1, (byte) 5, (byte) 30, 30l, (byte) 10, Collections.singletonList(EnchantIDs.get("LightningArrow"))));
        enchantments.put("ArrowSeeking", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 4, (byte) 1, (byte) 45, 45l, (byte) 10));
        enchantments.put("LifeSteal", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 5, (byte) 7, (byte) 35, 35l, (byte) 11));
        enchantments.put("Piercing", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 5, (byte) 1, (byte) 40, 40l,  (byte) 6));
        enchantments.put("Poison", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 4, (byte) 10, (byte) 25, 25l,  (byte) 6));
        enchantments.put("SoulTear", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 4, (byte) 2, (byte) 30, 30l, (byte) 11, Collections.singletonList(EnchantIDs.get("Examine"))));
        enchantments.put("SCurse", new DummyEnchant(true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 55, 55l, (byte) 11));
        enchantments.put("Vampirism", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 5, (byte) 3, (byte) 45, 45l, (byte) 11));
        enchantments.put("Vorpal", new DummyEnchant(true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 55, 55l, (byte) 11));
        enchantments.put("OverCharge", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 4, (byte) 2, (byte) 30, 30l, (byte) 11));
        enchantments.put("EnchLeech", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 4, (byte) 2, (byte) 30, 30l, (byte) 11));
        enchantments.put("Examine", new DummyEnchant(true, StartingID + enchantments.size(), (byte) 10, (byte) 1, (byte) 55,  3l, (byte) 11, Collections.singletonList(EnchantIDs.get("SoulTear"))));
        enchantments.put("FlamingArrow", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 3, (byte) 7, (byte) 20, 20l, (byte) 10));
        enchantments.put("BloodRazor", new DummyEnchant(true, StartingID + enchantments.size(),  (byte) 5, (byte) 2, (byte) 30, 50l,  (byte) 6));
        return false;
    }
/*
    public static HashMap<String, HxCEnchantmentDummy> enchants = new HashMap<String, HxCEnchantmentDummy>(){{
        EnabledEnchants.keySet().forEach(enc -> {
            putIfAbsent(enc, new HxCEnchantmentDummy(StartingID + size(), StringUtils.uncapitalize(enc), EnchantWeight.get(enc), Reference.ENCH_TYPE.get(enc), EnchantLevels.get(enc), EnchantCost.get(enc), EnchantChargeNeeded.get(enc), (Reference.ENCH_INCOMPATS.containsKey(enc) ? Reference.ENCH_INCOMPATS.get(enc) : Collections.emptyList())));
        });
    }};*/

    public class DummyEnchant {
        boolean enabled;
        public int id, maxLevel, minLevel, weight, cost;
        public long charge;
        public byte type;
        public List<Short> incompatable_enchants = new ArrayList<>();

        public DummyEnchant() {}

        public DummyEnchant(boolean enabled, int id, byte maxlvl, byte weight, byte cost, long chrg, byte type) {
            this.enabled = enabled;
            this.id = id;
            this.maxLevel = maxlvl;
            this.minLevel = 1;
            this.weight = weight;
            this.cost = cost;
            this.type = type;
            this.charge = chrg;
        }

        public DummyEnchant(boolean enabled, int id, byte maxlvl, byte weight, byte cost, long chrg, byte type, List<Short> incompatEnchs) {
            this.enabled = enabled;
            this.id = id;
            this.maxLevel = maxlvl;
            this.minLevel = 1;
            this.weight = weight;
            this.cost = cost;
            this.type = type;
            this.charge = chrg;
            this.incompatable_enchants = incompatEnchs;
        }
    }
}
