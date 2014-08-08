package kay.kayXEnchants.common;

import net.minecraftforge.common.config.*;

public class Config
{
    private int defaultEnchantID = 120;
    
    public static int enchFlyID;
    public static boolean enchFlyEnable;
    
    public static int enchSwiftnessID;
    public static boolean enchSwiftnessEnable;
    public static int enchSwiftnessLVL;
    
    public static int enchVitalityID;
    public static boolean enchVitalityEnable;
    public static int enchVitalityLVL;
    
    public static int enchWitherProtectionID;
    public static boolean enchWitherProtectionEnable;
    
    public static int enchBattleHealingID;
    public static boolean enchBattleHealingEnable;
    public static int enchBattleHealingLVL;
    
    public static int enchAdrenalineBoostID;
    public static boolean enchAdrenalineBoostEnable;
    public static int enchAdrenalineBoostLVL;
    
    public static boolean Tooltip;
    public static boolean AllowTrollBitches;
    
    public static int enchJumpBoostID;
    public static boolean enchJumpBoostEnable;
    public static int enchJumpBoostLVL;
 
    public Config(Configuration config)
    {
        config.load();
        
        enchFlyID = config.get("Armor", "FlyID", defaultEnchantID).getInt();
        enchFlyEnable = config.get("Armor", "FlyEnable", true).getBoolean(true);
       
        enchSwiftnessID = config.get("Armor", "SwiftnessID", defaultEnchantID+1).getInt();
        enchSwiftnessEnable = config.get("Armor", "SwiftnessEnable", true).getBoolean(true);
        enchSwiftnessLVL = config.get("Armor", "SwiftnessMaxLevel", 4).getInt();
        
        enchVitalityID = config.get("Armor", "VitalityID", defaultEnchantID+2).getInt();
        enchVitalityEnable = config.get("Armor", "VitalityEnable", true).getBoolean(true);
        enchVitalityLVL = config.get("Armor", "VitalityMaxLevel", 4).getInt();
        
        enchWitherProtectionID = config.get("Armor", "WitherProtectionID", defaultEnchantID+3).getInt();
        enchWitherProtectionEnable = config.get("Armor", "WitherProtectionEnable", true).getBoolean(true);
        
        enchBattleHealingID = config.get("Armor", "BattleHealingID", defaultEnchantID+4).getInt();
        enchBattleHealingEnable = config.get("Armor", "BattleHealingEnable", true).getBoolean(true);
        enchBattleHealingLVL = config.get("Armor", "BattleHealingMaxLevel", 4).getInt();
        
        enchAdrenalineBoostID = config.get("Armor", "AdrenalineBoostID", defaultEnchantID+5).getInt();
        enchAdrenalineBoostEnable = config.get("Armor", "AdrenalineBoostEnable", true).getBoolean(true);
        enchAdrenalineBoostLVL = config.get("Armor", "AdrenalineBoostMaxLevel", 4).getInt();

        enchJumpBoostID = config.get("Armor", "JumpBoostID", defaultEnchantID+6).getInt();
        enchJumpBoostEnable = config.get("Armor", "JumpBoostEnable", true).getBoolean(true);
        enchJumpBoostLVL = config.get("Armor", "JumpBoostMaxLevel", 2).getInt();

        AllowTrollBitches = config.get("Other", "Allow Trolling People Who Ticked Me Off (Teoettan)", true).getBoolean(true);
        Tooltip = config.get("Other", "Show info tooltips on enchanted armor. (Does get annoying sometimes.)", true).getBoolean(true);
        
        if(config.hasChanged())
        {
            config.save();
        }
    }
}