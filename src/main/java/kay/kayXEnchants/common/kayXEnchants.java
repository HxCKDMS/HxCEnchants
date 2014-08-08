package kay.kayXEnchants.common;

import kay.kayXEnchants.enchantment.*;
import kay.kayXEnchants.lib.Reference;
import net.minecraft.enchantment.*;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)

public class kayXEnchants
{
    @Instance(Reference.MOD_ID)
    public static kayXEnchants instance;
    
    public static Config Config;
    
	public static Enchantment Fly;
	public static Enchantment Swiftness;
	public static Enchantment Vitality;
	public static Enchantment WitherProtection;
	public static Enchantment BattleHealing;
	public static Enchantment AdrenalineBoost;
	public static Enchantment JumpBoost;
    
    public static Events events;
    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
        Config = new Config(new Configuration(event.getSuggestedConfigurationFile()));
        events = new Events();
        FMLCommonHandler.instance().bus().register(new Events());
    }
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        event.getModState();
        //MinecraftServer server = MinecraftServer.getServer();
        //ICommandManager command = server.getCommandManager();
        //ServerCommandManager manager = (ServerCommandManager) command;
        //manager.registerCommand(new CommandWhatChanged());
        //manager.registerCommand(new CommandListBetaTesters());

	    if(kay.kayXEnchants.common.Config.enchFlyEnable)
	    	Fly = new EnchantmentFly(kay.kayXEnchants.common.Config.enchFlyID, 1);
	    if(kay.kayXEnchants.common.Config.enchSwiftnessEnable)
	    	Swiftness = new EnchantmentSwiftness(kay.kayXEnchants.common.Config.enchSwiftnessID, 4);
	    if(kay.kayXEnchants.common.Config.enchVitalityEnable)
	    	Vitality = new EnchantmentVitality(kay.kayXEnchants.common.Config.enchVitalityID, 2);
	    if(kay.kayXEnchants.common.Config.enchWitherProtectionEnable)
	    	WitherProtection = new EnchantmentWitherProtection(kay.kayXEnchants.common.Config.enchWitherProtectionID, 3);
	    if(kay.kayXEnchants.common.Config.enchBattleHealingEnable)
	    	BattleHealing = new EnchantmentBattleHealing(kay.kayXEnchants.common.Config.enchBattleHealingID, 1);
	    if(kay.kayXEnchants.common.Config.enchAdrenalineBoostEnable)
	    	AdrenalineBoost = new EnchantmentAdrenalineBoost(kay.kayXEnchants.common.Config.enchAdrenalineBoostID, 1);
	    if(kay.kayXEnchants.common.Config.enchJumpBoostEnable)
	    	JumpBoost = new EnchantmentJumpBoost(kay.kayXEnchants.common.Config.enchJumpBoostID, 3);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
	    if(kay.kayXEnchants.common.Config.enchFlyEnable)
	    	FMLCommonHandler.instance().bus().register(Fly);
	    if(kay.kayXEnchants.common.Config.enchSwiftnessEnable)
	    	FMLCommonHandler.instance().bus().register(Swiftness);
	    if(kay.kayXEnchants.common.Config.enchVitalityEnable)
	    	FMLCommonHandler.instance().bus().register(Vitality);  
	    if(kay.kayXEnchants.common.Config.enchWitherProtectionEnable)
	    	FMLCommonHandler.instance().bus().register(WitherProtection);       
	    if(kay.kayXEnchants.common.Config.enchBattleHealingEnable)
	    	FMLCommonHandler.instance().bus().register(BattleHealing);  
	    if(kay.kayXEnchants.common.Config.enchAdrenalineBoostEnable)
	    	FMLCommonHandler.instance().bus().register(AdrenalineBoost);  
	    if(kay.kayXEnchants.common.Config.enchJumpBoostEnable)
	        FMLCommonHandler.instance().bus().register(JumpBoost);
        event.getModState();
    }

    public static boolean containsEnchant(ItemStack stack, int id)
    {
        return (stack != null) && EnchantmentHelper.getEnchantments(stack).containsKey(id);
    }
}
