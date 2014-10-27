package HxCKDMS.XEnchants.common;

import HxCKDMS.XEnchants.enchantment.*;
import HxCKDMS.XEnchants.hooks.*;
import HxCKDMS.XEnchants.lib.Reference;
import net.minecraft.enchantment.*;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)

public class XEnchants
{
    @Instance(Reference.MOD_ID)
    public static XEnchants instance;

    public static Config Config;

	public static Enchantment AdrenalineBoost;
	public static Enchantment ArmorRegen;
	public static Enchantment ArrowExplosive;
	public static Enchantment ArrowLightning;
	public static Enchantment ArrowSeeking;
	public static Enchantment BattleHealing;
    public static Enchantment Bound;
    public static Enchantment Critical;
    public static Enchantment FlameTouch;
    public static Enchantment Fly;
    public static Enchantment JumpBoost;
    public static Enchantment LeadFooted;
    public static Enchantment Poison;
    public static Enchantment Swiftness;
    public static Enchantment Vampirism;
    public static Enchantment Vitality;
    public static Enchantment WitherProtection;

    public static Events events;

    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
        Config = new Config(new Configuration(event.getSuggestedConfigurationFile()));
        events = new Events();
        MinecraftForge.EVENT_BUS.register(new Events());
    }
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        event.getModState();
	    if(Config.enchAdrenalineBoostEnable)
            AdrenalineBoost = new EnchantmentAdrenalineBoost(Config.enchAdrenalineBoostID, Config.enchAdrenalineBoostWeight);
            FMLCommonHandler.instance().bus().register(AdrenalineBoost);

        if(Config.enchArmorRegenEnable)
            ArmorRegen = new EnchantmentArmorRegen(Config.enchArmorRegenID, Config.enchArmorRegenWeight);
            FMLCommonHandler.instance().bus().register(ArmorRegen);

        if(Config.enchArrowExplosiveEnable)
            ArrowExplosive = new EnchantmentArrowExplosive(Config.enchArrowExplosiveID, Config.enchArrowExplosiveWeight);
            FMLCommonHandler.instance().bus().register(ArrowExplosive);

        if(Config.enchArrowLightningEnable)
            ArrowLightning = new EnchantmentArrowLightning(Config.enchArrowLightningID, Config.enchArrowLightningWeight);
            FMLCommonHandler.instance().bus().register(ArrowLightning);

        if(Config.enchArrowSeekingEnable)
            ArrowSeeking = new EnchantmentArrowSeeking(Config.enchArrowSeekingID, Config.enchArrowSeekingWeight);
            FMLCommonHandler.instance().bus().register(ArrowSeeking);

        if(Config.enchBattleHealingEnable)
            BattleHealing = new EnchantmentBattleHealing(Config.enchBattleHealingID, Config.enchBattleHealingWeight);
            FMLCommonHandler.instance().bus().register(BattleHealing);

        if(Config.enchBoundEnable)
            Bound = new EnchantmentBound(Config.enchBoundID, Config.enchBoundWeight);
            FMLCommonHandler.instance().bus().register(Bound);

        if(Config.enchCriticalEnable)
            Critical = new EnchantmentCritical(Config.enchCriticalID, Config.enchCriticalWeight);
            FMLCommonHandler.instance().bus().register(Critical);

        if(Config.enchFlameTouchEnable)
            FlameTouch = new EnchantmentFlameTouch(Config.enchFlameTouchID, Config.enchFlameTouchWeight);
            FMLCommonHandler.instance().bus().register(FlameTouch);

        if(Config.enchFlyEnable)
            Fly = new EnchantmentFly(Config.enchFlyID, Config.enchFlyWeight);
            FMLCommonHandler.instance().bus().register(Fly);

        if(Config.enchJumpBoostEnable)
            JumpBoost = new EnchantmentJumpBoost(Config.enchJumpBoostID, Config.enchJumpBoostWeight);
            FMLCommonHandler.instance().bus().register(JumpBoost);

        if(Config.enchLeadFootedEnable)
            LeadFooted = new EnchantmentLeadFooted(Config.enchLeadFootedID, Config.enchLeadFootedWeight);
            FMLCommonHandler.instance().bus().register(LeadFooted);

        if(Config.enchPoisonEnable)
            Poison = new EnchantmentPoison(Config.enchPoisonID, Config.enchPoisonWeight);
            FMLCommonHandler.instance().bus().register(Poison);

        if(Config.enchSwiftnessEnable)
            Swiftness = new EnchantmentSwiftness(Config.enchSwiftnessID, Config.enchSwiftnessWeight);
            FMLCommonHandler.instance().bus().register(Swiftness);

        if(Config.enchVampirismEnable)
            Vampirism = new EnchantmentVampirism(Config.enchVampirismID, Config.enchVampirismWeight);
            FMLCommonHandler.instance().bus().register(Vampirism);

        if(Config.enchVitalityEnable)
            Vitality = new EnchantmentVitality(Config.enchVitalityID, Config.enchVitalityWeight);
            FMLCommonHandler.instance().bus().register(Vitality);

        if(Config.enchWitherProtectionEnable)
            WitherProtection = new EnchantmentWitherProtection(Config.enchWitherProtectionID, Config.enchWitherProtectionWeight);
            FMLCommonHandler.instance().bus().register(WitherProtection);


        FMLCommonHandler.instance().bus().register(new ArrowEventHookContainer());
        FMLCommonHandler.instance().bus().register(new ArmorEventHookContainer());
        FMLCommonHandler.instance().bus().register(new ToolEventHookContainer());

    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        event.getModState();
    }

    public static boolean containsEnchant(ItemStack stack, int id)
    {
        return (stack != null) && EnchantmentHelper.getEnchantments(stack).containsKey(id);
    }
}
