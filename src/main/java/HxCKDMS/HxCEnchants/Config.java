package HxCKDMS.HxCEnchants;

import net.minecraftforge.common.config.Configuration;

public class Config
{
    public static boolean DebugMode = HxCKDMS.HxCCore.Configs.Config.DebugMode;
    public static boolean EDT;
    public static float PiercingPercent;

    public static int[] enchAdrenalineBoostVals;
    public static boolean enchAdrenalineBoostEnable;

    public static int[] enchAuraFieryVals;
    public static boolean enchAuraFieryEnable;

    public static int[] enchAuraDeadlyVals;
    public static boolean enchAuraDeadlyEnable;

    public static int[] enchAuraDarkVals;
    public static boolean enchAuraDarkEnable;
    
    public static int[] enchAuraThickVals;
    public static boolean enchAuraThickEnable;

    public static int[] enchAuraToxicVals;
    public static boolean enchAuraToxicEnable;

    public static int[] enchArrowExplosiveVals;
    public static boolean enchArrowExplosiveEnable;

    public static int[] enchArrowLightningVals;
    public static boolean enchArrowLightningEnable;

    public static int[] enchArrowSeekingVals;
    public static boolean enchArrowSeekingEnable;

    public static int[] enchBattleHealingVals;
    public static boolean enchBattleHealingEnable;

    public static int[] enchBoundVals;
    public static boolean enchBoundEnable;

    public static int[] enchDivineInterventionVals;
    public static boolean enchDivineInterventionEnable;

    public static int[] enchExamineVals;
    public static boolean enchExamineEnable;

    public static int[] enchFlameTouchVals;
    public static boolean enchFlameTouchEnable;

    public static int[] enchFlyVals = new int[3];
    public static boolean enchFlyEnable;

    public static int[] enchJumpBoostVals;
    public static boolean enchJumpBoostEnable;

    public static int[] enchLeadFootedVals;
    public static boolean enchLeadFootedEnable;

    public static int[] enchLifeStealVals;
    public static boolean enchLifeStealEnable;

    public static int[] enchPiercingVals;
    public static int[] enchArrowPiercingVals;
    public static boolean enchPiercingEnable;

    public static int[] enchPoisonVals;
    public static int[] enchVenomVals;
    public static boolean enchPoisonEnable;

    public static int[] enchRegenVals;
    public static boolean enchRegenEnable;

    public static int[] enchRepairVals;
    public static boolean enchRepairEnable;

    public static int[] enchShroudVals;
    public static boolean enchShroudEnable;

    public static int[] enchSoulTearVals;
    public static boolean enchSoulTearEnable;

    public static int[] enchSCurseVals;
    public static boolean enchSCurseEnable;

    public static int[] enchStealthVals;
    public static boolean enchStealthEnable;

    public static int[] enchSwiftnessVals;
    public static boolean enchSwiftnessEnable;

    public static int[] enchVampirismVals;
    public static boolean enchVampirismEnable;

    public static int[] enchVitalityVals;
    public static boolean enchVitalityEnable;

    public static int[] enchVorpalVals;
    public static boolean enchVorpalEnable;

    public static int[] enchWitherProtectionVals;
    public static boolean enchWitherProtectionEnable;

    public Config(Configuration config)
    {
        config.load();
        /** Config Group Descriptions **/
        config.setCategoryComment("Features", "This allows you to tweak anything we add.");
        config.setCategoryComment("Armor Enchants", "This allows you to disable any enchants.");
        config.setCategoryComment("Arrow Enchants", "This allows you to disable any enchants.");
        config.setCategoryComment("Tool/Weapon Enchants", "This allows you to disable any enchants.");
        
        config.setCategoryComment("Armor Enchant Values", "ID, Max Level, Weight, Cost, Special");
        config.setCategoryComment("Arrow Enchant Values", "ID, Max Level, Weight, Cost, Special");
        config.setCategoryComment("Tool/Weapon Enchant Values", "ID, Max Level, Weight, Cost, Special");

        /** Features **/
        EDT = config.getBoolean("ExplosionDestroysTerrain", "Features", false, "");
        PiercingPercent = config.getFloat("PiercingPercent", "Features", 0.1f, 0.01f, 10000.0f, "set this value as high as you want");

        /** Armor Enchants **/
        enchAdrenalineBoostEnable = config.getBoolean("AdrenalineBoost", "Armor Enchants", true, "Enable Enchant Adrenaline Boost?");
        enchBattleHealingEnable = config.getBoolean("BattleHealing", "Armor Enchants", true, "Enable Enchant Battle Healing?");
        enchFlyEnable = config.getBoolean("Fly", "Armor Enchants", true, "Enable Enchant Fly?");
        enchJumpBoostEnable = config.getBoolean("JumpBoost", "Armor Enchants", true, "Enable Enchant Jump Boost");
        enchRegenEnable = config.getBoolean("Regen", "Armor Enchants", true, "Enable Enchant Regen?");
        enchSwiftnessEnable = config.getBoolean("Swiftness", "Armor Enchants", true, "Enable Enchant Swiftness?");
        enchVitalityEnable = config.getBoolean("Vitality", "Armor Enchants", true, "Enable Enchant Vitality?");
        enchWitherProtectionEnable = config.getBoolean("WitherProtection", "Armor Enchants", true, "Enable Enchant Wither Protection?");
        enchRepairEnable = config.getBoolean("Repair", "Armor Enchants", true, "Enable Enchant Repair?");
        enchBoundEnable = config.getBoolean("Bound", "Armor Enchants", false, "Enable Enchant Bound? DISABLED IT IS BROKEN");
        enchLeadFootedEnable = config.getBoolean("LeadFooted", "Armor Enchants", false, "Enable Enchant Lead Footed? DISABLED IT IS BROKEN");
        enchShroudEnable = config.getBoolean("Shroud", "Armor Enchants", true, "Enable Enchant Shroud?");
        enchStealthEnable = config.getBoolean("Stealth", "Armor Enchants", true, "Enable Enchant Stealth?");
        enchAuraDarkEnable = config.getBoolean("DarkAura", "Armor Enchants", true, "Enable Enchant Dark Aura?");
        enchAuraDeadlyEnable = config.getBoolean("DeadlyAura", "Armor Enchants", true, "Enable Enchant Deadly Aura?");
        enchAuraFieryEnable = config.getBoolean("FieryAura", "Armor Enchants", true, "Enable Enchant Fiery Aura?");
        enchAuraThickEnable = config.getBoolean("ThickAura", "Armor Enchants", true, "Enable Enchant Thick Aura?");
        enchAuraToxicEnable = config.getBoolean("ToxicAura", "Armor Enchants", true, "Enable Enchant Toxic Aura?");
        enchDivineInterventionEnable = config.getBoolean("DivineIntervention", "Armor Enchants", true, "Enable Enchant Divine Intervention?");

        enchAdrenalineBoostVals = config.get("Armor Enchant Vals", "AdrenalineBoostVals", new int[]{400, 4, 10, 30}).getIntList();
        enchBattleHealingVals = config.get("Armor Enchant Vals", "BattleHealingVals", new int[]{402, 4, 3, 40}).getIntList();
        enchFlyVals = config.get("Armor Enchant Vals", "FlyVals", new int[]{403, 1, 1, 55}).getIntList();
        enchJumpBoostVals = config.get("Armor Enchant Vals", "JumpBoostVals", new int[]{404, 4, 10, 20}).getIntList();
        enchRepairVals = config.get("Armor Enchant Vals", "RepairVals", new int[]{405, 4, 1, 15, 35}).getIntList();
        enchRegenVals = config.get("Armor Enchant Vals", "RegenVals", new int[]{406, 4, 1, 15, 50}).getIntList();
        enchSwiftnessVals = config.get("Armor Enchant Vals", "SwiftnessVals", new int[]{407, 4, 10, 20}).getIntList();
        enchVitalityVals = config.get("Armor Enchant Vals", "VitalityVals", new int[]{408, 4, 4, 30}).getIntList();
        enchWitherProtectionVals = config.get("Armor Enchant Vals", "WitherProtectionVals", new int[]{409, 4, 8, 40}).getIntList();
        enchBoundVals = config.get("Armor Enchant Vals", "BoundVals", new int[]{410, 3, 5, 45}).getIntList();
        enchLeadFootedVals = config.get("Armor Enchant Vals", "LeadFootedVals", new int[]{411, 1, 10, 20}).getIntList();
        enchShroudVals = config.get("Armor Enchant Vals", "ShroudVals", new int[]{412, 1, 2, 45}).getIntList();
        enchStealthVals = config.get("Armor Enchant Vals", "StealthVals", new int[]{413, 2, 6, 40}).getIntList();
        enchAuraDeadlyVals = config.get("Armor Enchant Vals", "DeadlyAuraVals", new int[]{414, 10, 1, 50}).getIntList();
        enchAuraFieryVals = config.get("Armor Enchant Vals", "FieryAuraVals", new int[]{415, 10, 5, 30}).getIntList();
        enchAuraThickVals = config.get("Armor Enchant Vals", "ThickAuraVals", new int[]{416, 10, 10, 20}).getIntList();
        enchAuraToxicVals = config.get("Armor Enchant Vals", "ToxicAuraVals", new int[]{417, 10, 5, 35}).getIntList();
        enchAuraDarkVals = config.get("Armor Enchant Vals", "DarkAuraVals", new int[]{418, 10, 15, 20}).getIntList();
        enchDivineInterventionVals = config.get("Armor Enchant Vals", "DivineInterventionVals", new int[]{419, 5, 1, 45}).getIntList();

        /** Arrow Enchants **/
        enchArrowExplosiveEnable = config.getBoolean("ArrowExplosive", "Arrow Enchants", true, "Enable Enchant Arrow Explosive?");
        enchArrowLightningEnable = config.getBoolean("ArrowLightning", "Arrow Enchants", true, "Enable Enchant Arrow Lightning?");
        enchArrowSeekingEnable = config.getBoolean("ArrowSeeking", "Arrow Enchants", true, "Enable Enchant Arrow Seeking?");

        enchArrowExplosiveVals = config.get("Arrow Enchant Vals", "ArrowExplosiveVals", new int[]{421, 4, 2, 40}).getIntList();
        enchArrowLightningVals = config.get("Arrow Enchant Vals", "ArrowLightningVals", new int[]{422, 1, 5, 30}).getIntList();
        enchArrowSeekingVals = config.get("Arrow Enchant Vals", "ArrowSeekingVals", new int[]{423, 4, 1, 45}).getIntList();
        enchArrowPiercingVals = config.get("Arrow Enchant Vals", "ArrowPiercingVals", new int[]{424, 5, 1, 40}).getIntList();

        /** Tool/Weapon Enchants **/
        enchFlameTouchEnable = config.getBoolean("FlameTouch", "Tool/Weapon Enchants", true, "Enable Enchant Auto-Smelt?");
        enchLifeStealEnable = config.getBoolean("LifeSteal", "Tool/Weapon Enchants", true, "Enable Enchant Life Steal?");
        enchPoisonEnable = config.getBoolean("Poison", "Tool/Weapon Enchants", true, "Enable Enchant Poison?");
        enchVampirismEnable = config.getBoolean("Vampirism", "Tool/Weapon Enchants", true, "Enable Enchant Vampirism?");
        enchExamineEnable = config.getBoolean("Examine", "Tool/Weapon Enchants", true, "Enable Enchant Examine?");
        enchPiercingEnable = config.getBoolean("Piercing", "Tool/Weapon Enchants", true, "Enable Enchant Piercing?");
        enchVorpalEnable = config.getBoolean("Vorpal", "Tool/Weapon Enchants", true, "Enable Enchant Vorpal?");
        enchSCurseEnable = config.getBoolean("SCurse", "Tool/Weapon Enchants", true, "Enable Enchant SCurse?");

        enchFlameTouchVals = config.get("Tool/Weapon Enchant Vals", "FlameTouchVals", new int[]{431, 4, 2, 30}).getIntList();
        enchLifeStealVals = config.get("Tool/Weapon Enchant Vals", "LifeStealVals", new int[]{432, 5, 7, 35}).getIntList();
        enchPoisonVals = config.get("Tool/Weapon Enchant Vals", "PoisonVals", new int[]{433, 4, 10, 25}).getIntList();
        enchVampirismVals = config.get("Tool/Weapon Enchant Vals", "VampirismVals", new int[]{434, 5, 3, 45}).getIntList();
        enchExamineVals = config.get("Tool/Weapon Enchant Vals", "ExamineVals", new int[]{435, 10, 1, 55}).getIntList();
        enchPiercingVals = config.get("Tool/Weapon Enchant Vals", "PiercingVals", new int[]{436, 10, 1, 50}).getIntList();
        enchVenomVals = config.get("Tool/Weapon Enchant Vals", "VenomVals", new int[]{437, 4, 1, 25}).getIntList();
        enchVorpalVals = config.get("Tool/Weapon Enchant Vals", "VorpalVals", new int[]{438, 10, 1, 55, 1}).getIntList();
        enchSCurseVals = config.get("Tool/Weapon Enchant Vals", "SCurseVals", new int[]{439, 10, 1, 55, 3}).getIntList();

        if(config.hasChanged())
        {
            config.save();
        }
    }
}