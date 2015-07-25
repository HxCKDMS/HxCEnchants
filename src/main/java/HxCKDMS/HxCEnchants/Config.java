package HxCKDMS.HxCEnchants;

import net.minecraftforge.common.config.Configuration;

public class Config
{
    public static boolean DebugMode = HxCKDMS.HxCCore.Configs.Config.DebugMode;
    public static boolean EDT;
    public static float PiercingPercent;

    public static int[] enchAdrenalineBoostVals, enchAuraFieryVals, enchAuraDeadlyVals, enchAuraDarkVals,
            enchAuraThickVals, enchAuraToxicVals, enchArrowExplosiveVals, enchArrowLightningVals,
            enchArrowSeekingVals, enchBattleHealingVals, enchBoundVals, enchDivineInterventionVals,
            enchExamineVals, enchFlameTouchVals, enchFlyVals, enchJumpBoostVals, enchLeadFootedVals,
            enchLifeStealVals, enchPiercingVals, enchArrowPiercingVals, enchPoisonVals, enchVenomVals,
            enchRegenVals, enchRepairVals, enchShroudVals, enchSoulTearVals, enchSCurseVals, enchStealthVals,
            enchSwiftnessVals, enchVampirismVals, enchVitalityVals, enchVorpalVals, enchWitherProtectionVals;

    public static boolean enchAdrenalineBoostEnable, enchAuraFieryEnable, enchAuraDeadlyEnable,
            enchAuraDarkEnable, enchAuraToxicEnable, enchAuraThickEnable, enchArrowExplosiveEnable, enchArrowLightningEnable,
            enchArrowSeekingEnable, enchBattleHealingEnable, enchBoundEnable, enchDivineInterventionEnable,
            enchExamineEnable, enchFlameTouchEnable, enchFlyEnable, enchJumpBoostEnable, enchLeadFootedEnable,
            enchLifeStealEnable, enchPiercingEnable, enchPoisonEnable, enchRegenEnable, enchRepairEnable,
            enchShroudEnable, enchSoulTearEnable, enchSCurseEnable, enchStealthEnable, enchSwiftnessEnable,
            enchVampirismEnable, enchVitalityEnable, enchVorpalEnable, enchWitherProtectionEnable;

    public Config(Configuration config) {
        config.load();
        /** Config Group Descriptions **/
        config.setCategoryComment("A Features", "This allows you to tweak anything we add.");
        config.setCategoryComment("B Armor Enchants", "This allows you to disable any enchants.");
        config.setCategoryComment("D Arrow Enchants", "This allows you to disable any enchants.");
        config.setCategoryComment("F Tool/Weapon Enchants", "This allows you to disable any enchants.");

        config.setCategoryComment("C Armor Enchant Values", "ID, Max Level, Weight, Cost, Special");
        config.setCategoryComment("E Arrow Enchant Values", "ID, Max Level, Weight, Cost, Special");
        config.setCategoryComment("G Tool/Weapon Enchant Values", "ID, Max Level, Weight, Cost, Special");

        /** Features **/
        EDT = config.getBoolean("ExplosionDestroysTerrain", "A Features", false, "");
        PiercingPercent = config.getFloat("PiercingPercent", "A Features", 0.1f, 0.01f, 10000.0f, "set this value as high as you want");

        /** Armor Enchants **/
        enchAdrenalineBoostEnable = config.getBoolean("AdrenalineBoost", "B Armor Enchants", true, "Enable Enchant Adrenaline Boost?");
        enchBattleHealingEnable = config.getBoolean("BattleHealing", "B Armor Enchants", true, "Enable Enchant Battle Healing?");
        enchFlyEnable = config.getBoolean("Fly", "B Armor Enchants", true, "Enable Enchant Fly?");
        enchJumpBoostEnable = config.getBoolean("JumpBoost", "B Armor Enchants", true, "Enable Enchant Jump Boost");
        enchRegenEnable = config.getBoolean("Regen", "B Armor Enchants", true, "Enable Enchant Regen?");
        enchSwiftnessEnable = config.getBoolean("Swiftness", "B Armor Enchants", true, "Enable Enchant Swiftness?");
        enchVitalityEnable = config.getBoolean("Vitality", "B Armor Enchants", true, "Enable Enchant Vitality?");
        enchWitherProtectionEnable = config.getBoolean("WitherProtection", "B Armor Enchants", true, "Enable Enchant Wither Protection?");
        enchRepairEnable = config.getBoolean("Repair", "B Armor Enchants", true, "Enable Enchant Repair?");
        enchBoundEnable = config.getBoolean("Bound", "B Armor Enchants", false, "Enable Enchant Bound? DISABLED IT IS BROKEN");
        enchLeadFootedEnable = config.getBoolean("LeadFooted", "B Armor Enchants", false, "Enable Enchant Lead Footed? DISABLED IT IS BROKEN");
        enchShroudEnable = config.getBoolean("Shroud", "B Armor Enchants", true, "Enable Enchant Shroud?");
        enchStealthEnable = config.getBoolean("Stealth", "B Armor Enchants", true, "Enable Enchant Stealth?");
        enchAuraDarkEnable = config.getBoolean("DarkAura", "B Armor Enchants", true, "Enable Enchant Dark Aura?");
        enchAuraDeadlyEnable = config.getBoolean("DeadlyAura", "B Armor Enchants", true, "Enable Enchant Deadly Aura?");
        enchAuraFieryEnable = config.getBoolean("FieryAura", "B Armor Enchants", true, "Enable Enchant Fiery Aura?");
        enchAuraThickEnable = config.getBoolean("ThickAura", "B Armor Enchants", true, "Enable Enchant Thick Aura?");
        enchAuraToxicEnable = config.getBoolean("ToxicAura", "B Armor Enchants", true, "Enable Enchant Toxic Aura?");
        enchDivineInterventionEnable = config.getBoolean("DivineIntervention", "B Armor Enchants", true, "Enable Enchant Divine Intervention?");

        enchAdrenalineBoostVals = config.get("C Armor Enchant Values", "AdrenalineBoostVals", new int[]{400, 4, 10, 30}).getIntList();
        enchBattleHealingVals = config.get("C Armor Enchant Values", "BattleHealingVals", new int[]{402, 4, 3, 40}).getIntList();
        enchFlyVals = config.get("C Armor Enchant Values", "FlyVals", new int[]{403, 1, 1, 55}).getIntList();
        enchJumpBoostVals = config.get("C Armor Enchant Values", "JumpBoostVals", new int[]{404, 4, 10, 20}).getIntList();
        enchRepairVals = config.get("C Armor Enchant Values", "RepairVals", new int[]{405, 4, 1, 15, 35, 60}).getIntList();
        enchRegenVals = config.get("C Armor Enchant Values", "RegenVals", new int[]{406, 4, 1, 15, 50, 60}).getIntList();
        enchSwiftnessVals = config.get("C Armor Enchant Values", "SwiftnessVals", new int[]{407, 4, 10, 20}).getIntList();
        enchVitalityVals = config.get("C Armor Enchant Values", "VitalityVals", new int[]{408, 4, 4, 30}).getIntList();
        enchWitherProtectionVals = config.get("C Armor Enchant Values", "WitherProtectionVals", new int[]{409, 4, 8, 40}).getIntList();
        enchBoundVals = config.get("C Armor Enchant Values", "BoundVals", new int[]{410, 3, 5, 45}).getIntList();
        enchLeadFootedVals = config.get("C Armor Enchant Values", "LeadFootedVals", new int[]{411, 1, 10, 20}).getIntList();
        enchShroudVals = config.get("C Armor Enchant Values", "ShroudVals", new int[]{412, 1, 2, 45}).getIntList();
        enchStealthVals = config.get("C Armor Enchant Values", "StealthVals", new int[]{413, 2, 6, 40}).getIntList();
        enchAuraDeadlyVals = config.get("C Armor Enchant Values", "DeadlyAuraVals", new int[]{414, 10, 1, 50}).getIntList();
        enchAuraFieryVals = config.get("C Armor Enchant Values", "FieryAuraVals", new int[]{415, 10, 5, 30}).getIntList();
        enchAuraThickVals = config.get("C Armor Enchant Values", "ThickAuraVals", new int[]{416, 10, 10, 20}).getIntList();
        enchAuraToxicVals = config.get("C Armor Enchant Values", "ToxicAuraVals", new int[]{417, 10, 5, 35}).getIntList();
        enchAuraDarkVals = config.get("C Armor Enchant Values", "DarkAuraVals", new int[]{418, 10, 15, 20}).getIntList();
        enchDivineInterventionVals = config.get("C Armor Enchant Values", "DivineInterventionVals", new int[]{419, 5, 1, 45}).getIntList();

        /** Arrow Enchants **/
        enchArrowExplosiveEnable = config.getBoolean("ArrowExplosive", "D Arrow Enchants", true, "Enable Enchant Arrow Explosive?");
        enchArrowLightningEnable = config.getBoolean("ArrowLightning", "D Arrow Enchants", true, "Enable Enchant Arrow Lightning?");
        enchArrowSeekingEnable = config.getBoolean("ArrowSeeking", "D Arrow Enchants", true, "Enable Enchant Arrow Seeking?");

        enchArrowExplosiveVals = config.get("E Arrow Enchant Values", "ArrowExplosiveVals", new int[]{421, 4, 2, 40}).getIntList();
        enchArrowLightningVals = config.get("E Arrow Enchant Values", "ArrowLightningVals", new int[]{422, 1, 5, 30}).getIntList();
        enchArrowSeekingVals = config.get("E Arrow Enchant Values", "ArrowSeekingVals", new int[]{423, 4, 1, 45}).getIntList();
        enchArrowPiercingVals = config.get("E Arrow Enchant Values", "ArrowPiercingVals", new int[]{424, 5, 1, 40}).getIntList();

        /** Tool/Weapon Enchants **/
        enchFlameTouchEnable = config.getBoolean("FlameTouch", "F Tool/Weapon Enchants", true, "Enable Enchant Auto-Smelt?");
        enchLifeStealEnable = config.getBoolean("LifeSteal", "F Tool/Weapon Enchants", true, "Enable Enchant Life Steal?");
        enchPoisonEnable = config.getBoolean("Poison", "F Tool/Weapon Enchants", true, "Enable Enchant Poison?");
        enchVampirismEnable = config.getBoolean("Vampirism", "F Tool/Weapon Enchants", true, "Enable Enchant Vampirism?");
        enchExamineEnable = config.getBoolean("Examine", "F Tool/Weapon Enchants", true, "Enable Enchant Examine?");
        enchPiercingEnable = config.getBoolean("Piercing", "F Tool/Weapon Enchants", true, "Enable Enchant Piercing?");
        enchVorpalEnable = config.getBoolean("Vorpal", "F Tool/Weapon Enchants", true, "Enable Enchant Vorpal?");
        enchSCurseEnable = config.getBoolean("SCurse", "F Tool/Weapon Enchants", true, "Enable Enchant SCurse?");

        enchFlameTouchVals = config.get("G Tool/Weapon Enchant Values", "FlameTouchVals", new int[]{431, 4, 2, 30}).getIntList();
        enchLifeStealVals = config.get("G Tool/Weapon Enchant Values", "LifeStealVals", new int[]{432, 5, 7, 35}).getIntList();
        enchPoisonVals = config.get("G Tool/Weapon Enchant Values", "PoisonVals", new int[]{433, 4, 10, 25}).getIntList();
        enchVampirismVals = config.get("G Tool/Weapon Enchant Values", "VampirismVals", new int[]{434, 5, 3, 45}).getIntList();
        enchExamineVals = config.get("G Tool/Weapon Enchant Values", "ExamineVals", new int[]{435, 10, 1, 55}).getIntList();
        enchPiercingVals = config.get("G Tool/Weapon Enchant Values", "PiercingVals", new int[]{436, 10, 1, 50}).getIntList();
        enchVenomVals = config.get("G Tool/Weapon Enchant Values", "VenomVals", new int[]{437, 4, 1, 25}).getIntList();
        enchVorpalVals = config.get("G Tool/Weapon Enchant Values", "VorpalVals", new int[]{438, 10, 1, 55, 1}).getIntList();
        enchSCurseVals = config.get("G Tool/Weapon Enchant Values", "SCurseVals", new int[]{439, 10, 1, 55, 3}).getIntList();

        if(config.hasChanged())
            config.save();
    }
}