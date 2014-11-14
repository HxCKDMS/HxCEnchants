package HxCKDMS.XEnchants;

import HxCKDMS.XEnchants.enchantment.*;
import HxCKDMS.XEnchants.Handlers.ArmorEventHandler;
import HxCKDMS.XEnchants.Handlers.ArrowEventHandler;
import HxCKDMS.XEnchants.Handlers.ToolEventHandler;
import HxCKDMS.XEnchants.lib.Reference;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)

public class XEnchants
{
    @Instance(Reference.MOD_ID)
    public static XEnchants instance;

    boolean DevMode = true;

    public static Config Config;

    private int Enchs = 0;

	public static Enchantment AdrenalineBoost;
    public static Enchantment AirStrider;
	public static Enchantment ArmorRegen;
	public static Enchantment ArrowExplosive;
	public static Enchantment ArrowLightning;
	public static Enchantment ArrowSeeking;
	public static Enchantment BattleHealing;
    public static Enchantment FlameTouch;
    public static Enchantment Fly;
    public static Enchantment JumpBoost;
//    public static Enchantment LeadFooted;
    public static Enchantment LifeSteal;
    public static Enchantment Poison;
    public static Enchantment Repair;
//    public static Enchantment Shroud;
    public static Enchantment Swiftness;
//    public static Enchantment Stealth;
    public static Enchantment Vampirism;
    public static Enchantment Vitality;
    public static Enchantment WitherProtection;

    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
        Config = new Config(new Configuration(event.getSuggestedConfigurationFile()));try {
        Class.forName( "HxCKDMS.HxCCore.HxCCore" );
        } catch( ClassNotFoundException e ) {
            if (HxCKDMS.XEnchants.Config.DebugMode || DevMode){
                FMLCommonHandler.instance().getFMLLogger().log(Level.ERROR, "[XEnchants] HxCCore not found! Please Grab it from our site!");
            }else{
                throw new NullPointerException("HxCCore Not Found!!! Please Download it from our site! or Enable DebugMode and set all IDs to < 256 =/");
            }
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        event.getModState();
        if (Config.enchAdrenalineBoostEnable) {
            AdrenalineBoost = new EnchantmentAdrenalineBoost(Config.enchAdrenalineBoostID, Config.enchAdrenalineBoostWeight);
            MinecraftForge.EVENT_BUS.register(AdrenalineBoost);
            Enchs++;
        }
        if(Config.enchAirStriderEnable){
            AirStrider = new EnchantmentFasterFlight(Config.enchAirStriderID, Config.enchAirStriderWeight);
            MinecraftForge.EVENT_BUS.register(AirStrider);
            Enchs++;
        }

        if (Config.enchRegenEnable) {
            ArmorRegen = new EnchantmentRegen(Config.enchRegenID, Config.enchRegenWeight);
            MinecraftForge.EVENT_BUS.register(ArmorRegen);
            Enchs++;
        }
        if (Config.enchArrowExplosiveEnable) {
            ArrowExplosive = new EnchantmentArrowExplosive(Config.enchArrowExplosiveID, Config.enchArrowExplosiveWeight);
            MinecraftForge.EVENT_BUS.register(ArrowExplosive);
            Enchs++;
        }
        if (Config.enchArrowLightningEnable) {
            ArrowLightning = new EnchantmentArrowLightning(Config.enchArrowLightningID, Config.enchArrowLightningWeight);
            MinecraftForge.EVENT_BUS.register(ArrowLightning);
            Enchs++;
        }
        if (Config.enchArrowSeekingEnable) {
            ArrowSeeking = new EnchantmentArrowSeeking(Config.enchArrowSeekingID, Config.enchArrowSeekingWeight);
            MinecraftForge.EVENT_BUS.register(ArrowSeeking);
            Enchs++;
        }
        if (Config.enchBattleHealingEnable) {
            BattleHealing = new EnchantmentBattleHealing(Config.enchBattleHealingID, Config.enchBattleHealingWeight);
            MinecraftForge.EVENT_BUS.register(BattleHealing);
            Enchs++;
        }/*
        if(Config.enchBoundEnable){
            Bound = new EnchantmentBound(Config.enchBoundID, Config.enchBoundWeight);
            MinecraftForge.EVENT_BUS.register(Bound);
            Enchs++;
}*/

        if (Config.enchFlameTouchEnable) {
            FlameTouch = new EnchantmentFlameTouch(Config.enchFlameTouchID, Config.enchFlameTouchWeight);
            MinecraftForge.EVENT_BUS.register(FlameTouch);
            Enchs++;
        }
        if(HxCKDMS.XEnchants.Config.enchFlyEnable){
            Fly = new EnchantmentFly(Config.enchFlyID, Config.enchFlyWeight);
            MinecraftForge.EVENT_BUS.register(Fly);
            Enchs++;
        }
        if(Config.enchJumpBoostEnable) {
            JumpBoost = new EnchantmentJumpBoost(Config.enchJumpBoostID, Config.enchJumpBoostWeight);
            MinecraftForge.EVENT_BUS.register(JumpBoost);
            Enchs++;
        }/*

        if(Config.enchLeadFootedEnable){
            LeadFooted = new EnchantmentLeadFooted(Config.enchLeadFootedID, Config.enchLeadFootedWeight);
            MinecraftForge.EVENT_BUS.register(LeadFooted);
            Enchs++;
            }
*/      if(Config.enchLifeStealEnable) {
            LifeSteal = new EnchantmentLifeSteal(Config.enchLifeStealID, Config.enchLifeStealWeight);
            MinecraftForge.EVENT_BUS.register(LifeSteal);
            Enchs++;
        }
        if(Config.enchPoisonEnable) {
            Poison = new EnchantmentPoison(Config.enchPoisonID, Config.enchPoisonWeight);
            MinecraftForge.EVENT_BUS.register(Poison);
            Enchs++;
        }
        if(Config.enchRepairEnable){
            Repair = new EnchantmentRepair(Config.enchRepairID, Config.enchRepairWeight);
        MinecraftForge.EVENT_BUS.register(Repair);
            Enchs++;
        }
/*

        if(Config.enchShroudEnable){
            Shroud = new EnchantmentShroud(Config.enchShroudID, Config.enchShroudWeight);
        MinecraftForge.EVENT_BUS.register(Shroud);
            Enchs++;
        }
*/

        if(Config.enchSwiftnessEnable){
            Swiftness = new EnchantmentSwiftness(Config.enchSwiftnessID, Config.enchSwiftnessWeight);
            MinecraftForge.EVENT_BUS.register(Swiftness);
            Enchs++;
        }
/*

        if(Config.enchStealthEnable){
            Stealth = new EnchantmentStealth(Config.enchStealthID, Config.enchStealthWeight);
            MinecraftForge.EVENT_BUS.register(Stealth);
            Enchs++;
            }
*/
        if(Config.enchVampirismEnable) {
            Vampirism = new EnchantmentVampirism(Config.enchVampirismID, Config.enchVampirismWeight);
            MinecraftForge.EVENT_BUS.register(Vampirism);
            Enchs++;
        }
        if(Config.enchVitalityEnable) {
            Vitality = new EnchantmentVitality(Config.enchVitalityID, Config.enchVitalityWeight);
            MinecraftForge.EVENT_BUS.register(Vitality);
            Enchs++;
        }
        if(Config.enchWitherProtectionEnable) {
            WitherProtection = new EnchantmentWitherProtection(Config.enchWitherProtectionID, Config.enchWitherProtectionWeight);
            MinecraftForge.EVENT_BUS.register(WitherProtection);
            Enchs++;
        }
        MinecraftForge.EVENT_BUS.register(new ArrowEventHandler());
        MinecraftForge.EVENT_BUS.register(new ArmorEventHandler());
        MinecraftForge.EVENT_BUS.register(new ToolEventHandler());

        if (HxCKDMS.XEnchants.Config.DebugMode)
            FMLCommonHandler.instance().getFMLLogger().log(Level.DEBUG, "[XEnchants] " + Enchs + "Enchantments Have Been Registered");
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        event.getModState();
    }	
}
