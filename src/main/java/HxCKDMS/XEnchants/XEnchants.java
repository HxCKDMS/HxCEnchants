package HxCKDMS.XEnchants;

import HxCKDMS.XEnchants.enchantment.*;
import HxCKDMS.XEnchants.hooks.*;
import HxCKDMS.XEnchants.lib.Reference;
import net.minecraft.enchantment.*;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)

public class XEnchants
{
    @Instance(Reference.MOD_ID)
    public static XEnchants instance;

    public static Config Config;

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
    public static Enchantment LeadFooted;
    public static Enchantment Poison;
    public static Enchantment Repair;
    public static Enchantment Swiftness;
    public static Enchantment Vampirism;
    public static Enchantment Vitality;
    public static Enchantment WitherProtection;

    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
        Config = new Config(new Configuration(event.getSuggestedConfigurationFile()));
    }
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        event.getModState();
	    if(Config.enchAdrenalineBoostEnable)
            AdrenalineBoost = new EnchantmentAdrenalineBoost(Config.enchAdrenalineBoostID, Config.enchAdrenalineBoostWeight);
            MinecraftForge.EVENT_BUS.register(AdrenalineBoost);

        if(Config.enchAirStriderEnable)
            AirStrider = new EnchantmentFasterFlight(Config.enchAirStriderID, Config.enchAirStriderWeight);
            MinecraftForge.EVENT_BUS.register(AirStrider);

        if(Config.enchArmorRegenEnable)
            ArmorRegen = new EnchantmentArmorRegen(Config.enchArmorRegenID, Config.enchArmorRegenWeight);
            MinecraftForge.EVENT_BUS.register(ArmorRegen);

        if(Config.enchArrowExplosiveEnable)
            ArrowExplosive = new EnchantmentArrowExplosive(Config.enchArrowExplosiveID, Config.enchArrowExplosiveWeight);
            MinecraftForge.EVENT_BUS.register(ArrowExplosive);

        if(Config.enchArrowLightningEnable)
            ArrowLightning = new EnchantmentArrowLightning(Config.enchArrowLightningID, Config.enchArrowLightningWeight);
            MinecraftForge.EVENT_BUS.register(ArrowLightning);

        if(Config.enchArrowSeekingEnable)
            ArrowSeeking = new EnchantmentArrowSeeking(Config.enchArrowSeekingID, Config.enchArrowSeekingWeight);
            MinecraftForge.EVENT_BUS.register(ArrowSeeking);

        if(Config.enchBattleHealingEnable)
            BattleHealing = new EnchantmentBattleHealing(Config.enchBattleHealingID, Config.enchBattleHealingWeight);
            MinecraftForge.EVENT_BUS.register(BattleHealing);

//        if(Config.enchBoundEnable)
//            Bound = new EnchantmentBound(Config.enchBoundID, Config.enchBoundWeight);
//            MinecraftForge.EVENT_BUS.register(Bound);

        if(Config.enchFlameTouchEnable)
            FlameTouch = new EnchantmentFlameTouch(Config.enchFlameTouchID, Config.enchFlameTouchWeight);
            MinecraftForge.EVENT_BUS.register(FlameTouch);

        if(Config.enchFlyEnable)
            Fly = new EnchantmentFly(Config.enchFlyID, Config.enchFlyWeight);
            MinecraftForge.EVENT_BUS.register(Fly);

        if(Config.enchJumpBoostEnable)
            JumpBoost = new EnchantmentJumpBoost(Config.enchJumpBoostID, Config.enchJumpBoostWeight);
            MinecraftForge.EVENT_BUS.register(JumpBoost);

        if(Config.enchLeadFootedEnable)
            LeadFooted = new EnchantmentLeadFooted(Config.enchLeadFootedID, Config.enchLeadFootedWeight);
            MinecraftForge.EVENT_BUS.register(LeadFooted);

        if(Config.enchPoisonEnable)
            Poison = new EnchantmentPoison(Config.enchPoisonID, Config.enchPoisonWeight);
            MinecraftForge.EVENT_BUS.register(Poison);

        if(Config.enchRepairEnable)
            Repair = new EnchantmentRepair(Config.enchRepairID, Config.enchRepairWeight);
        MinecraftForge.EVENT_BUS.register(Repair);

        if(Config.enchSwiftnessEnable)
            Swiftness = new EnchantmentSwiftness(Config.enchSwiftnessID, Config.enchSwiftnessWeight);
            MinecraftForge.EVENT_BUS.register(Swiftness);

        if(Config.enchVampirismEnable)
            Vampirism = new EnchantmentVampirism(Config.enchVampirismID, Config.enchVampirismWeight);
            MinecraftForge.EVENT_BUS.register(Vampirism);

        if(Config.enchVitalityEnable)
            Vitality = new EnchantmentVitality(Config.enchVitalityID, Config.enchVitalityWeight);
            MinecraftForge.EVENT_BUS.register(Vitality);

        if(Config.enchWitherProtectionEnable)
            WitherProtection = new EnchantmentWitherProtection(Config.enchWitherProtectionID, Config.enchWitherProtectionWeight);
            MinecraftForge.EVENT_BUS.register(WitherProtection);

        MinecraftForge.EVENT_BUS.register(new ArrowEventHookContainer());
        MinecraftForge.EVENT_BUS.register(new ArmorEventHookContainer());
        MinecraftForge.EVENT_BUS.register(new ToolEventHookContainer());

    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        event.getModState();
    }

}
