package HxCKDMS.XEnchants;

import net.minecraftforge.common.config.Configuration;

public class Config
{
    public static boolean DebugMode;
    public static boolean Feedback;

    public int enchAdrenalineBoostID;
    public boolean enchAdrenalineBoostEnable;
    public static int enchAdrenalineBoostLVL;
    public int enchAdrenalineBoostWeight;

    public int enchAirStriderID;
    public boolean enchAirStriderEnable;
    public static int enchAirStriderLVL;
    public int enchAirStriderWeight;

    public int enchArrowExplosiveID;
    public boolean enchArrowExplosiveEnable;
    public static int enchArrowExplosiveLVL;
    public int enchArrowExplosiveWeight;

    public int enchArrowLightningID;
    public boolean enchArrowLightningEnable;
    public static int enchArrowLightningLVL;
    public int enchArrowLightningWeight;

    public int enchArrowSeekingID;
    public boolean enchArrowSeekingEnable;
    public static int enchArrowSeekingLVL;
    public int enchArrowSeekingWeight;

    public int enchBattleHealingID;
    public boolean enchBattleHealingEnable;
    public static int enchBattleHealingLVL;
    public int enchBattleHealingWeight;

//    public int enchBoundID;
//    public boolean enchBoundEnable;
//    public static int enchBoundLVL;
//    public int enchBoundWeight;


    public int enchFlameTouchID;
    public boolean enchFlameTouchEnable;
    public static int enchFlameTouchLVL;
    public int enchFlameTouchWeight;

    public int enchFlyID;
    public boolean enchFlyEnable;
    public int enchFlyWeight;

    public int enchJumpBoostID;
    public boolean enchJumpBoostEnable;
    public static int enchJumpBoostLVL;
    public int enchJumpBoostWeight;

    public int enchLeadFootedID;
    public boolean enchLeadFootedEnable;
    public static int enchLeadFootedLVL;
    public int enchLeadFootedWeight;

    public int enchLifeStealID;
    public boolean enchLifeStealEnable;
    public static int enchLifeStealLVL;
    public int enchLifeStealWeight;

    public int enchPoisonID;
    public boolean enchPoisonEnable;
    public static int enchPoisonLVL;
    public int enchPoisonWeight;

    public int enchRegenID;
    public boolean enchRegenEnable;
    public static int enchRegenLVL;
    public int enchRegenWeight;
    public static int enchRegenRate;

    public int enchRepairID;
    public boolean enchRepairEnable;
    public static int enchRepairLVL;
    public int enchRepairWeight;
    public static int enchRepairRate;

    public int enchShroudID;
    public boolean enchShroudEnable;
    public static int enchShroudLVL;
    public int enchShroudWeight;

    public int enchSwiftnessID;
    public boolean enchSwiftnessEnable;
    public static int enchSwiftnessLVL;
    public int enchSwiftnessWeight;

    public int enchStealthID;
    public boolean enchStealthEnable;
    public static int enchStealthLVL;
    public int enchStealthWeight;

    public int enchVampirismID;
    public boolean enchVampirismEnable;
    public static int enchVampirismLVL;
    public int enchVampirismWeight;

    public int enchVitalityID;
    public boolean enchVitalityEnable;
    public static int enchVitalityLVL;
    public int enchVitalityWeight;

    public int enchWitherProtectionID;
    public boolean enchWitherProtectionEnable;
    public static int enchWitherProtectionLVL;
    public int enchWitherProtectionWeight;

    int defaultEnchantID = 320;

    public Config(Configuration config)
    {
        config.load();

        DebugMode = config.get("DEBUG", "Debug Mode Enable?", false).getBoolean();

        DebugMode = config.get("Preferences", "Enable Feedback (ChatMessages and such from enchants)", false).getBoolean();

        enchAdrenalineBoostID = config.get("Armor", "Adrenaline Boost ID", defaultEnchantID).getInt();
        enchAdrenalineBoostEnable = config.get("Armor", "Adrenaline Boost Enable", true).getBoolean();
        enchAdrenalineBoostLVL = config.get("Armor", "Adrenaline Boost Max Level", 4).getInt();
        enchAdrenalineBoostWeight = config.get("Armor", "Adrenaline Boost Weight", 2).getInt();

        enchAirStriderID = config.get("Armor", "AirStriderID", defaultEnchantID +1).getInt();
        enchAirStriderEnable = config.get("Armor", "Air Strider Enable", true).getBoolean();
        enchAirStriderLVL = config.get("Armor", "Air Strider MaxLevel", 4).getInt();
        enchAirStriderWeight = config.get("Armor", "Air Strider Weight", 2).getInt();

        enchArrowExplosiveID = config.get("Armor", "Arrow Explosive ID", defaultEnchantID +2).getInt();
        enchArrowExplosiveEnable = config.get("Armor", "Arrow Explosive Enable", true).getBoolean();
        enchArrowExplosiveLVL = config.get("Armor", "Arrow ExplosiveMax Level", 4).getInt();
        enchArrowExplosiveWeight = config.get("Armor", "Arrow Explosive Weight", 2).getInt();

        enchArrowLightningID = config.get("Armor", "Arrow Lightning ID", defaultEnchantID +3).getInt();
        enchArrowLightningEnable = config.get("Armor", "Arrow Lightning Enable", true).getBoolean();
        enchArrowLightningLVL = config.get("Armor", "Arrow Lightning Max Level", 1).getInt();
        enchArrowLightningWeight = config.get("Armor", "Arrow Lightning Weight", 2).getInt();

        enchArrowSeekingID = config.get("Armor", "Arrow Seeking ID", defaultEnchantID +4).getInt();
        enchArrowSeekingEnable = config.get("Armor", "Arrow Seeking Enable", true).getBoolean();
        enchArrowSeekingLVL = config.get("Armor", "Arrow Seeking Max Level", 4).getInt();
        enchArrowSeekingWeight = config.get("Armor", "Arrow Seeking Weight", 2).getInt();

        enchBattleHealingID = config.get("Armor", "Battle Healing ID", defaultEnchantID +5).getInt();
        enchBattleHealingEnable = config.get("Armor", "Battle Healing Enable", true).getBoolean();
        enchBattleHealingLVL = config.get("Armor", "Battle Healing Max Level", 4).getInt();
        enchBattleHealingWeight = config.get("Armor", "Battle Healing Weight", 2).getInt();

//        enchBoundID = config.get("Armor", "Bound ID", defaultEnchantID+6).getInt();
//        enchBoundEnable = config.get("Armor", "Bound Enable", true).getBoolean();
//        enchBoundLVL = config.get("Armor", "Bound Max Level", 1).getInt();
//        enchBoundWeight = config.get("Armor", "Bound Weight", 2).getInt();

        enchFlameTouchID = config.get("Armor", "Flame Touch ID", defaultEnchantID +7).getInt();
        enchFlameTouchEnable = config.get("Armor", "Flame Touch Enable", true).getBoolean();
        enchFlameTouchLVL = config.get("Armor", "Flame Touch Max Level", 4).getInt();
        enchFlameTouchWeight = config.get("Armor", "Flame Touch Weight", 2).getInt();

        enchFlyID = config.get("Armor", "Fly ID", defaultEnchantID +8).getInt();
        enchFlyEnable = config.get("Armor", "Fly Enable", true).getBoolean();
        enchFlyWeight = config.get("Armor", "Fly Weight", 2).getInt();

        enchJumpBoostID = config.get("Armor", "Jump Boost ID", defaultEnchantID +9).getInt();
        enchJumpBoostEnable = config.get("Armor", "Jump Boost Enable", true).getBoolean();
        enchJumpBoostLVL = config.get("Armor", "Jump Boost Max Level", 4).getInt();
        enchJumpBoostWeight = config.get("Armor", "Jump Boost eWeight", 2).getInt();

        enchLeadFootedID = config.get("Armor", "Lead Footed ID", defaultEnchantID +10).getInt();
        enchLeadFootedEnable = config.get("Armor", "Lead Footed Enable", true).getBoolean();
        enchLeadFootedLVL = config.get("Armor", "Lead Footed Max Level", 1).getInt();
        enchLeadFootedWeight = config.get("Armor", "Lead Footed Weight", 2).getInt();

        enchLifeStealID = config.get("Armor", "Life Steal ID", defaultEnchantID +20).getInt();
        enchLifeStealEnable = config.get("Armor", "Life Steal Enable", true).getBoolean();
        enchLifeStealLVL = config.get("Armor", "Life Steal Max Level", 1).getInt();
        enchLifeStealWeight = config.get("Armor", "Life Steal Weight", 2).getInt();

        enchPoisonID = config.get("Armor", "Poison ID", defaultEnchantID +11).getInt();
        enchPoisonEnable = config.get("Armor", "Poison Enable", true).getBoolean();
        enchPoisonLVL = config.get("Armor", "Poison Max Level", 5).getInt();
        enchPoisonWeight = config.get("Armor", "Poison Weight", 2).getInt();

        enchRegenID = config.get("Armor", "Regen ID", defaultEnchantID +12).getInt();
        enchRegenEnable = config.get("Armor", "Regen Enable", true).getBoolean();
        enchRegenLVL = config.get("Armor", "Regen Max Level", 2).getInt();
        enchRegenWeight = config.get("Armor", "Regen Weight", 2).getInt();
        enchRegenRate = config.get("Armor", "Regen Rate", 3).getInt();

        enchRepairID = config.get("All", "Repair ID", defaultEnchantID +13).getInt();
        enchRepairEnable = config.get("All", "Repair Enable", true).getBoolean();
        enchRepairLVL = config.get("All", "Repair Max Level", 5).getInt();
        enchRepairWeight = config.get("All", "Repair Weight", 1).getInt();
        enchRepairRate = config.get("All", "Repair Speed Default = 1 Max = 999999 DON'T EXCEED THE MAX (Java will crash)", 1).getInt();

        enchShroudID = config.get("Armor", "Shroud ID", defaultEnchantID +14).getInt();
        enchShroudEnable = config.get("Armor", "Shroud Enable", true).getBoolean();
        enchShroudLVL = config.get("Armor", "Shroud Max Level", 4).getInt();
        enchShroudWeight = config.get("Armor", "Shroud Weight", 2).getInt();

        enchSwiftnessID = config.get("Armor", "Swiftness ID", defaultEnchantID +15).getInt();
        enchSwiftnessEnable = config.get("Armor", "Swiftness Enable", true).getBoolean();
        enchSwiftnessLVL = config.get("Armor", "Swiftness Max Level", 4).getInt();
        enchSwiftnessWeight = config.get("Armor", "Swiftness Weight", 2).getInt();

        enchStealthID = config.get("Armor", "Stealth ID", defaultEnchantID +16).getInt();
        enchStealthEnable = config.get("Armor", "Stealth Enable", true).getBoolean();
        enchStealthLVL = config.get("Armor", "Stealth Max Level", 4).getInt();
        enchStealthWeight = config.get("Armor", "Stealth Weight", 2).getInt();

        enchVampirismID = config.get("Armor", "Vampirism ID", defaultEnchantID +17).getInt();
        enchVampirismEnable = config.get("Armor", "Vampirism Enable", true).getBoolean();
        enchVampirismLVL = config.get("Armor", "Vampirism Max Level", 5).getInt();
        enchVampirismWeight = config.get("Armor", "Vampirism Weight", 2).getInt();

        enchVitalityID = config.get("Armor", "Vitality ID", defaultEnchantID +18).getInt();
        enchVitalityEnable = config.get("Armor", "Vitality Enable", true).getBoolean();
        enchVitalityLVL = config.get("Armor", "Vitality Max Level", 4).getInt();
        enchVitalityWeight = config.get("Armor", "Vitality Weight", 2).getInt();

        enchWitherProtectionID = config.get("Armor", "Wither Protection ID", defaultEnchantID +19).getInt();
        enchWitherProtectionEnable = config.get("Armor", "Wither Protection Enable", true).getBoolean();
        enchWitherProtectionLVL = config.get("Armor", "Wither Protection Max Level", 4).getInt();
        enchWitherProtectionWeight = config.get("Armor", "Wither Protection Weight", 2).getInt();

        if(config.hasChanged())
        {
            config.save();
        }
    }
}