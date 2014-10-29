package HxCKDMS.XEnchants.common;

import net.minecraftforge.common.config.*;

public class Config
{
    public boolean DebugMode;

    private int defaultEnchantID = 120;

    public int enchAdrenalineBoostID;
    public boolean enchAdrenalineBoostEnable;
    public int enchAdrenalineBoostLVL;
    public int enchAdrenalineBoostWeight;

    public int enchAirStriderID;
    public boolean enchAirStriderEnable;
    public int enchAirStriderLVL;
    public int enchAirStriderWeight;

    public int enchArmorRegenID;
    public boolean enchArmorRegenEnable;
    public int enchArmorRegenLVL;
    public int enchArmorRegenWeight;

    public int enchArrowExplosiveID;
    public boolean enchArrowExplosiveEnable;
    public int enchArrowExplosiveLVL;
    public int enchArrowExplosiveWeight;

    public int enchArrowLightningID;
    public boolean enchArrowLightningEnable;
    public int enchArrowLightningLVL;
    public int enchArrowLightningWeight;

    public int enchArrowSeekingID;
    public boolean enchArrowSeekingEnable;
    public int enchArrowSeekingLVL;
    public int enchArrowSeekingWeight;

    public int enchBattleHealingID;
    public boolean enchBattleHealingEnable;
    public int enchBattleHealingLVL;
    public int enchBattleHealingWeight;

    public int enchBoundID;
    public boolean enchBoundEnable;
    public int enchBoundLVL;
    public int enchBoundWeight;

    public int enchCriticalID;
    public boolean enchCriticalEnable;
    public int enchCriticalLVL;
    public int enchCriticalWeight;

    public int enchFlameTouchID;
    public boolean enchFlameTouchEnable;
    public int enchFlameTouchLVL;
    public int enchFlameTouchWeight;

    public int enchFlyID;
    public boolean enchFlyEnable;
    public int enchFlyWeight;

    public int enchJumpBoostID;
    public boolean enchJumpBoostEnable;
    public int enchJumpBoostLVL;
    public int enchJumpBoostWeight;

    public int enchLeadFootedID;
    public boolean enchLeadFootedEnable;
    public int enchLeadFootedLVL;
    public int enchLeadFootedWeight;

    public int enchPoisonID;
    public boolean enchPoisonEnable;
    public int enchPoisonLVL;
    public int enchPoisonWeight;

    public int enchSwiftnessID;
    public boolean enchSwiftnessEnable;
    public int enchSwiftnessLVL;
    public int enchSwiftnessWeight;

    public int enchVampirismID;
    public boolean enchVampirismEnable;
    public int enchVampirismLVL;
    public int enchVampirismWeight;

    public int enchVitalityID;
    public boolean enchVitalityEnable;
    public int enchVitalityLVL;
    public int enchVitalityWeight;

    public int enchWitherProtectionID;
    public boolean enchWitherProtectionEnable;
    public int enchWitherProtectionLVL;
    public int enchWitherProtectionWeight;

    public boolean Tooltip;

    public Config(Configuration config)
    {
        config.load();


        DebugMode = config.get("DEBUG", "Debug Mode Enable?", false).getBoolean(false);

        enchAdrenalineBoostID = config.get("Armor", "Adrenaline Boost ID", defaultEnchantID).getInt();
        enchAdrenalineBoostEnable = config.get("Armor", "Adrenaline Boost Enable", true).getBoolean(true);
        enchAdrenalineBoostLVL = config.get("Armor", "Adrenaline Boost Max Level", 4).getInt();
        enchAdrenalineBoostWeight = config.get("Armor", "Adrenaline Boost Weight", 2).getInt();

        enchAirStriderID = config.get("Armor", "AirStriderID", defaultEnchantID+17).getInt();
        enchAirStriderEnable = config.get("Armor", "Air Strider Enable", true).getBoolean(true);
        enchAirStriderLVL = config.get("Armor", "Air Strider MaxLevel", 4).getInt();
        enchAirStriderWeight = config.get("Armor", "Air Strider Weight", 2).getInt();

        enchArmorRegenID = config.get("Armor", "ArmorRegenID", defaultEnchantID+1).getInt();
        enchArmorRegenEnable = config.get("Armor", "ArmorRegenEnable", true).getBoolean(true);
        enchArmorRegenLVL = config.get("Armor", "ArmorRegenMaxLevel", 2).getInt();
        enchArmorRegenWeight = config.get("Armor", "ArmorRegenWeight", 2).getInt();

        enchArrowExplosiveID = config.get("Armor", "ArrowExplosiveID", defaultEnchantID+2).getInt();
        enchArrowExplosiveEnable = config.get("Armor", "ArrowExplosiveEnable", true).getBoolean(true);
        enchArrowExplosiveLVL = config.get("Armor", "ArrowExplosiveMaxLevel", 4).getInt();
        enchArrowExplosiveWeight = config.get("Armor", "ArrowExplosiveWeight", 2).getInt();

        enchArrowLightningID = config.get("Armor", "ArrowLightningID", defaultEnchantID+3).getInt();
        enchArrowLightningEnable = config.get("Armor", "ArrowLightningEnable", true).getBoolean(true);
        enchArrowLightningLVL = config.get("Armor", "ArrowLightningMaxLevel", 1).getInt();
        enchArrowLightningWeight = config.get("Armor", "ArrowLightningWeight", 2).getInt();

        enchArrowSeekingID = config.get("Armor", "ArrowSeekingID", defaultEnchantID+4).getInt();
        enchArrowSeekingEnable = config.get("Armor", "ArrowSeekingEnable", true).getBoolean(true);
        enchArrowSeekingLVL = config.get("Armor", "ArrowSeekingMaxLevel", 1).getInt();
        enchArrowSeekingWeight = config.get("Armor", "ArrowSeekingWeight", 2).getInt();

        enchBattleHealingID = config.get("Armor", "BattleHealingID", defaultEnchantID+5).getInt();
        enchBattleHealingEnable = config.get("Armor", "BattleHealingEnable", true).getBoolean(true);
        enchBattleHealingLVL = config.get("Armor", "BattleHealingMaxLevel", 4).getInt();
        enchBattleHealingWeight = config.get("Armor", "BattleHealingWeight", 2).getInt();

        enchBoundID = config.get("Armor", "BoundID", defaultEnchantID+6).getInt();
        enchBoundEnable = config.get("Armor", "BoundEnable", true).getBoolean(true);
        enchBoundLVL = config.get("Armor", "BoundMaxLevel", 1).getInt();
        enchBoundWeight = config.get("Armor", "BoundWeight", 2).getInt();

        enchCriticalID = config.get("Armor", "CriticalID", defaultEnchantID+7).getInt();
        enchCriticalEnable = config.get("Armor", "CriticalEnable", true).getBoolean(true);
        enchCriticalLVL = config.get("Armor", "CriticalMaxLevel", 5).getInt();
        enchCriticalWeight = config.get("Armor", "CriticalWeight", 2).getInt();

        enchFlameTouchID = config.get("Armor", "FlameTouchID", defaultEnchantID+8).getInt();
        enchFlameTouchEnable = config.get("Armor", "FlameTouchEnable", true).getBoolean(true);
        enchFlameTouchLVL = config.get("Armor", "FlameTouchMaxLevel", 1).getInt();
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
        enchLeadFootedLVL = config.get("Armor", "LeadFootedMaxLevel", 1).getInt();
        enchLeadFootedWeight = config.get("Armor", "LeadFootedWeight", 2).getInt();

        enchPoisonID = config.get("Armor", "PoisonID", defaultEnchantID+12).getInt();
        enchPoisonEnable = config.get("Armor", "PoisonEnable", true).getBoolean(true);
        enchPoisonLVL = config.get("Armor", "PoisonMaxLevel", 5).getInt();
        enchPoisonWeight = config.get("Armor", "PoisonWeight", 2).getInt();

        enchSwiftnessID = config.get("Armor", "SwiftnessID", defaultEnchantID+13).getInt();
        enchSwiftnessEnable = config.get("Armor", "SwiftnessEnable", true).getBoolean(true);
        enchSwiftnessLVL = config.get("Armor", "SwiftnessMaxLevel", 4).getInt();
        enchSwiftnessWeight = config.get("Armor", "SwiftnessWeight", 2).getInt();

        enchVampirismID = config.get("Armor", "VampirismID", defaultEnchantID+14).getInt();
        enchVampirismEnable = config.get("Armor", "VampirismEnable", true).getBoolean(true);
        enchVampirismLVL = config.get("Armor", "VampirismMaxLevel", 5).getInt();
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