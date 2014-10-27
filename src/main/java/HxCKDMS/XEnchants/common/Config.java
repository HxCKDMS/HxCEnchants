package HxCKDMS.XEnchants.common;

import net.minecraftforge.common.config.*;

public class Config
{
    private int defaultEnchantID = 120;

    public static int enchAdrenalineBoostID;
    public static boolean enchAdrenalineBoostEnable;
    public static int enchAdrenalineBoostLVL;
    public static int enchAdrenalineBoostWeight;

    public static int enchArmorRegenID;
    public static boolean enchArmorRegenEnable;
    public static int enchArmorRegenLVL;
    public static int enchArmorRegenWeight;

    public static int enchArrowExplosiveID;
    public static boolean enchArrowExplosiveEnable;
    public static int enchArrowExplosiveLVL;
    public static int enchArrowExplosiveWeight;

    public static int enchArrowLightningID;
    public static boolean enchArrowLightningEnable;
    public static int enchArrowLightningLVL;
    public static int enchArrowLightningWeight;

    public static int enchArrowSeekingID;
    public static boolean enchArrowSeekingEnable;
    public static int enchArrowSeekingLVL;
    public static int enchArrowSeekingWeight;

    public static int enchBattleHealingID;
    public static boolean enchBattleHealingEnable;
    public static int enchBattleHealingLVL;
    public static int enchBattleHealingWeight;

    public static int enchBoundID;
    public static boolean enchBoundEnable;
    public static int enchBoundLVL;
    public static int enchBoundWeight;

    public static int enchCriticalID;
    public static boolean enchCriticalEnable;
    public static int enchCriticalLVL;
    public static int enchCriticalWeight;

    public static int enchFlameTouchID;
    public static boolean enchFlameTouchEnable;
    public static int enchFlameTouchLVL;
    public static int enchFlameTouchWeight;

    public static int enchFlyID;
    public static boolean enchFlyEnable;
    public static int enchFlyWeight;

    public static int enchJumpBoostID;
    public static boolean enchJumpBoostEnable;
    public static int enchJumpBoostLVL;
    public static int enchJumpBoostWeight;

    public static int enchLeadFootedID;
    public static boolean enchLeadFootedEnable;
    public static int enchLeadFootedLVL;
    public static int enchLeadFootedWeight;

    public static int enchPoisonID;
    public static boolean enchPoisonEnable;
    public static int enchPoisonLVL;
    public static int enchPoisonWeight;

    public static int enchSwiftnessID;
    public static boolean enchSwiftnessEnable;
    public static int enchSwiftnessLVL;
    public static int enchSwiftnessWeight;

    public static int enchVampirismID;
    public static boolean enchVampirismEnable;
    public static int enchVampirismLVL;
    public static int enchVampirismWeight;

    public static int enchVitalityID;
    public static boolean enchVitalityEnable;
    public static int enchVitalityLVL;
    public static int enchVitalityWeight;

    public static int enchWitherProtectionID;
    public static boolean enchWitherProtectionEnable;
    public static int enchWitherProtectionLVL;
    public static int enchWitherProtectionWeight;

    public static boolean Tooltip;

    public Config(Configuration config)
    {
        config.load();

        enchAdrenalineBoostID = config.get("Armor", "AdrenalineBoostID", defaultEnchantID).getInt();
        enchAdrenalineBoostEnable = config.get("Armor", "AdrenalineBoostEnable", true).getBoolean(true);
        enchAdrenalineBoostLVL = config.get("Armor", "AdrenalineBoostMaxLevel", 4).getInt();
        enchAdrenalineBoostWeight = config.get("Armor", "AdrenalineBoostWeight", 2).getInt();

        enchArmorRegenID = config.get("Armor", "ArmorRegenID", defaultEnchantID+1).getInt();
        enchArmorRegenEnable = config.get("Armor", "ArmorRegenEnable", true).getBoolean(true);
        enchArmorRegenLVL = config.get("Armor", "ArmorRegenMaxLevel", 4).getInt();
        enchArmorRegenWeight = config.get("Armor", "ArmorRegenWeight", 2).getInt();

        enchArrowExplosiveID = config.get("Armor", "ArrowExplosiveID", defaultEnchantID+2).getInt();
        enchArrowExplosiveEnable = config.get("Armor", "ArrowExplosiveEnable", true).getBoolean(true);
        enchArrowExplosiveLVL = config.get("Armor", "ArrowExplosiveMaxLevel", 4).getInt();
        enchArrowExplosiveWeight = config.get("Armor", "ArrowExplosiveWeight", 2).getInt();

        enchArrowLightningID = config.get("Armor", "ArrowLightningID", defaultEnchantID+3).getInt();
        enchArrowLightningEnable = config.get("Armor", "ArrowLightningEnable", true).getBoolean(true);
        enchArrowLightningLVL = config.get("Armor", "ArrowLightningMaxLevel", 4).getInt();
        enchArrowLightningWeight = config.get("Armor", "ArrowLightningWeight", 2).getInt();

        enchArrowSeekingID = config.get("Armor", "ArrowSeekingID", defaultEnchantID+4).getInt();
        enchArrowSeekingEnable = config.get("Armor", "ArrowSeekingEnable", true).getBoolean(true);
        enchArrowSeekingLVL = config.get("Armor", "ArrowSeekingMaxLevel", 4).getInt();
        enchArrowSeekingWeight = config.get("Armor", "ArrowSeekingWeight", 2).getInt();

        enchBattleHealingID = config.get("Armor", "BattleHealingID", defaultEnchantID+5).getInt();
        enchBattleHealingEnable = config.get("Armor", "BattleHealingEnable", true).getBoolean(true);
        enchBattleHealingLVL = config.get("Armor", "BattleHealingMaxLevel", 4).getInt();
        enchBattleHealingWeight = config.get("Armor", "BattleHealingWeight", 2).getInt();

        enchBoundID = config.get("Armor", "BoundID", defaultEnchantID+6).getInt();
        enchBoundEnable = config.get("Armor", "BoundEnable", true).getBoolean(true);
        enchBoundLVL = config.get("Armor", "BoundMaxLevel", 4).getInt();
        enchBoundWeight = config.get("Armor", "BoundWeight", 2).getInt();

        enchCriticalID = config.get("Armor", "CriticalID", defaultEnchantID+7).getInt();
        enchCriticalEnable = config.get("Armor", "CriticalEnable", true).getBoolean(true);
        enchCriticalLVL = config.get("Armor", "CriticalMaxLevel", 4).getInt();
        enchCriticalWeight = config.get("Armor", "CriticalWeight", 2).getInt();

        enchFlameTouchID = config.get("Armor", "FlameTouchID", defaultEnchantID+8).getInt();
        enchFlameTouchEnable = config.get("Armor", "FlameTouchEnable", true).getBoolean(true);
        enchFlameTouchLVL = config.get("Armor", "FlameTouchMaxLevel", 4).getInt();
        enchFlameTouchWeight = config.get("Armor", "FlameTouchWeight", 2).getInt();

        enchFlyID = config.get("Armor", "FlyID", defaultEnchantID+9).getInt();
        enchFlyEnable = config.get("Armor", "FlyEnable", true).getBoolean(true);
        enchFlyWeight = config.get("Armor", "FlyWeight", 2).getInt();

        enchJumpBoostID = config.get("Armor", "JumpBoostID", defaultEnchantID+10).getInt();
        enchJumpBoostEnable = config.get("Armor", "JumpBoostEnable", true).getBoolean(true);
        enchJumpBoostLVL = config.get("Armor", "JumpBoostMaxLevel", 4).getInt();
        enchJumpBoostWeight = config.get("Armor", "JumpBoostWeight", 2).getInt();

        enchLeadFootedID = config.get("Armor", "LeadFootedID", defaultEnchantID+11).getInt();
        enchLeadFootedEnable = config.get("Armor", "LeadFootedEnable", true).getBoolean(true);
        enchLeadFootedLVL = config.get("Armor", "LeadFootedMaxLevel", 4).getInt();
        enchLeadFootedWeight = config.get("Armor", "LeadFootedWeight", 2).getInt();

        enchPoisonID = config.get("Armor", "PoisonID", defaultEnchantID+12).getInt();
        enchPoisonEnable = config.get("Armor", "PoisonEnable", true).getBoolean(true);
        enchPoisonLVL = config.get("Armor", "PoisonMaxLevel", 4).getInt();
        enchPoisonWeight = config.get("Armor", "PoisonWeight", 2).getInt();

        enchSwiftnessID = config.get("Armor", "SwiftnessID", defaultEnchantID+13).getInt();
        enchSwiftnessEnable = config.get("Armor", "SwiftnessEnable", true).getBoolean(true);
        enchSwiftnessLVL = config.get("Armor", "SwiftnessMaxLevel", 4).getInt();
        enchSwiftnessWeight = config.get("Armor", "SwiftnessWeight", 2).getInt();

        enchVampirismID = config.get("Armor", "VampirismID", defaultEnchantID+14).getInt();
        enchVampirismEnable = config.get("Armor", "VampirismEnable", true).getBoolean(true);
        enchVampirismLVL = config.get("Armor", "VampirismMaxLevel", 4).getInt();
        enchVampirismWeight = config.get("Armor", "VampirismWeight", 2).getInt();

        enchVitalityID = config.get("Armor", "VitalityID", defaultEnchantID+15).getInt();
        enchVitalityEnable = config.get("Armor", "VitalityEnable", true).getBoolean(true);
        enchVitalityLVL = config.get("Armor", "VitalityMaxLevel", 4).getInt();
        enchVitalityWeight = config.get("Armor", "VitalityWeight", 2).getInt();

        enchWitherProtectionID = config.get("Armor", "WitherProtectionID", defaultEnchantID+16).getInt();
        enchWitherProtectionEnable = config.get("Armor", "WitherProtectionEnable", true).getBoolean(true);
        enchWitherProtectionLVL = config.get("Armor", "WitherProtectionMaxLevel", 4).getInt();
        enchWitherProtectionWeight = config.get("Armor", "WitherProtectionWeight", 2).getInt();

        Tooltip = config.get("Other", "Show info tooltips on enchanted armor. (Does get annoying sometimes.)", true).getBoolean(true);
        
        if(config.hasChanged())
        {
            config.save();
        }
    }
}