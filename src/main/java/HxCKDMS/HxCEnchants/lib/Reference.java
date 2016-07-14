package HxCKDMS.HxCEnchants.lib;

import net.minecraft.enchantment.Enchantment;

import java.util.*;

import static HxCKDMS.HxCEnchants.Configurations.Configurations.EnchantIDs;

public class Reference {
    public static final String MOD_ID = "HxCEnchants";
    public static final String MOD_NAME = "HxC Enchants";
    public static final String VERSION = "2.3.4";
    public static final String CHANNEL_NAME = MOD_ID;
    public static final String DEPENDENCIES = "required-after:HxCCore@[1.12.0,)";
    public static final String CLIENT_PROXY_CLASS = "HxCKDMS.HxCEnchants.Proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "HxCKDMS.HxCEnchants.Proxy.ServerProxy";
    public static final HashMap<String, List<Short>> ENCH_INCOMPATS = new HashMap<>();
    public static final HashMap<String, Byte> ENCH_TYPE = new HashMap<>();

    static {
        ENCH_INCOMPATS.put("AuraDeadly", Arrays.asList(EnchantIDs.get("GaiaAura"), EnchantIDs.get("HealingAura")));
        ENCH_INCOMPATS.put("AuraFiery", Arrays.asList(EnchantIDs.get("GaiaAura"), EnchantIDs.get("HealingAura"), EnchantIDs.get("IcyAura")));
        ENCH_INCOMPATS.put("Regen", Collections.singletonList(EnchantIDs.get("BattleHealing")));
        ENCH_INCOMPATS.put("ArrowZeus", Collections.singletonList(EnchantIDs.get("LightningArrow")));
        ENCH_INCOMPATS.put("BattleHealing", Collections.singletonList(EnchantIDs.get("Regen")));
        ENCH_INCOMPATS.put("Examine", Collections.singletonList(EnchantIDs.get("SoulTear")));
        ENCH_INCOMPATS.put("SoulTear", Collections.singletonList(EnchantIDs.get("Examine")));
        ENCH_INCOMPATS.put("FlameTouch", Arrays.asList(EnchantIDs.get("VoidTouch"), (short) Enchantment.silkTouch.effectId));
        ENCH_INCOMPATS.put("Fly", Collections.singletonList(EnchantIDs.get("JumpBoost")));
        ENCH_INCOMPATS.put("JumpBoost", Collections.singletonList(EnchantIDs.get("Fly")));
        ENCH_INCOMPATS.put("MeteorFall", Collections.singletonList(EnchantIDs.get("BattleHealing")));
        ENCH_INCOMPATS.put("FeatherFall", Collections.singletonList(EnchantIDs.get("BattleHealing")));
        ENCH_INCOMPATS.put("VoidTouch", Arrays.asList(EnchantIDs.get("FlameTouch"), (short) Enchantment.fortune.effectId, (short) Enchantment.silkTouch.effectId));
        ENCH_INCOMPATS.put("GaiaAura", Collections.singletonList(EnchantIDs.get("BattleHealing")));
        ENCH_INCOMPATS.put("HealingAura", Arrays.asList(EnchantIDs.get("AuraToxic"), EnchantIDs.get("AuraDeadly"), EnchantIDs.get("AuraFiery")));
        ENCH_INCOMPATS.put("RepulsiveAura", Collections.singletonList(EnchantIDs.get("AuraMagnetic")));
        ENCH_INCOMPATS.put("IcyAura", Collections.singletonList(EnchantIDs.get("AuraFiery")));
        ENCH_INCOMPATS.put("MagneticAura", Collections.singletonList(EnchantIDs.get("RepulsiveAura")));
        ENCH_INCOMPATS.put("LightningArrow", Collections.singletonList(EnchantIDs.get("BattleHealing")));
// 0     1        2           3            4            5        6       7          8          9       10     11
//ALL, ARMOR, ARMOR_FEET, ARMOR_LEGS, ARMOR_TORSO, ARMOR_HEAD, WEAPON, DIGGER, FISHING_ROD, BREAKABLE, BOW, SWORD
        ENCH_TYPE.put("Bound", (byte) 0);
        ENCH_TYPE.put("FlameTouch", (byte) 7);
        ENCH_TYPE.put("Repair", (byte) 9);
        ENCH_TYPE.put("EarthEater", (byte) 7);
        ENCH_TYPE.put("SpeedMine", (byte) 7);
        ENCH_TYPE.put("VoidTouch", (byte) 9);
        ENCH_TYPE.put("AdrenalineBoost", (byte) 5);
        ENCH_TYPE.put("AuraFiery", (byte) 1);
        ENCH_TYPE.put("AuraDeadly", (byte) 1);
        ENCH_TYPE.put("AuraDark", (byte) 1);
        ENCH_TYPE.put("AuraThick", (byte) 1);
        ENCH_TYPE.put("AuraToxic", (byte) 1);
        ENCH_TYPE.put("BattleHealing", (byte) 4);
        ENCH_TYPE.put("DivineIntervention", (byte) 4);
        ENCH_TYPE.put("Fly", (byte) 2);
        ENCH_TYPE.put("JumpBoost", (byte) 3);
        ENCH_TYPE.put("Regen", (byte) 1);
        ENCH_TYPE.put("Shroud", (byte) 1);
        ENCH_TYPE.put("Stealth", (byte) 2);
        ENCH_TYPE.put("Swiftness", (byte) 3);
        ENCH_TYPE.put("Vitality", (byte) 1);
        ENCH_TYPE.put("WitherProtection", (byte) 5);
        ENCH_TYPE.put("MeteorFall", (byte) 2);
        ENCH_TYPE.put("FeatherFall", (byte) 2);
        ENCH_TYPE.put("FlashStep", (byte) 2);
        ENCH_TYPE.put("HealingAura", (byte) 1);
        ENCH_TYPE.put("RepulsiveAura", (byte) 1);
        ENCH_TYPE.put("AuraMagnetic", (byte) 1);
        ENCH_TYPE.put("GaiaAura", (byte) 1);
        ENCH_TYPE.put("IcyAura", (byte) 1);
        ENCH_TYPE.put("Gluttony", (byte) 5);
        ENCH_TYPE.put("ExplosiveDischarge", (byte) 4);
        ENCH_TYPE.put("ChargedAura", (byte) 1);
        ENCH_TYPE.put("Nightvision", (byte) 5);
        ENCH_TYPE.put("LightningArrow", (byte) 10);
        ENCH_TYPE.put("ArrowExplosive", (byte) 10);
        ENCH_TYPE.put("ArrowZeus", (byte) 10);
        ENCH_TYPE.put("ArrowSeeking", (byte) 10);
        ENCH_TYPE.put("LifeSteal", (byte) 11);
        ENCH_TYPE.put("Piercing", (byte) 6);
        ENCH_TYPE.put("Poison", (byte) 6);
        ENCH_TYPE.put("SoulTear", (byte) 11);
        ENCH_TYPE.put("SCurse", (byte) 11);
        ENCH_TYPE.put("Vampirism", (byte) 11);
        ENCH_TYPE.put("Vorpal", (byte) 11);
        ENCH_TYPE.put("OverCharge", (byte) 11);
        ENCH_TYPE.put("EnchLeech", (byte) 11);
        ENCH_TYPE.put("Examine", (byte) 11);
        ENCH_TYPE.put("FlamingArrow", (byte) 10);
    }

    public static UUID HealthUUID = UUID.fromString("fe15f490-62d7-11e4-b116-123b93f75cba"),
            SpeedUUID = UUID.fromString("fe15f828-62d7-11e4-b116-123b93f75cba");
}
