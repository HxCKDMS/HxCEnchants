package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCCore.Utils.LogHelper;
import HxCKDMS.HxCEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Level;

public class Enchants {

    private static int Enchs = 0;

    public static Enchantment AdrenalineBoost;
    public static Enchantment AirStrider;
    public static Enchantment ArmorRegen;
    public static Enchantment ArrowExplosive;
    public static Enchantment ArrowLightning;
    public static Enchantment ArrowSeeking;
    public static Enchantment Bound;
    public static Enchantment BattleHealing;
    public static Enchantment FlameTouch;
    public static Enchantment Fly;
    public static Enchantment JumpBoost;
    public static Enchantment LeadFooted;
    public static Enchantment LifeSteal;
    public static Enchantment Poison;
    public static Enchantment Repair;
    public static Enchantment Shroud;
    public static Enchantment Swiftness;
    public static Enchantment Stealth;
    public static Enchantment Vampirism;
    public static Enchantment Vitality;
    public static Enchantment WitherProtection;
    public static Enchantment Examine;

    public static void load () {
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
        }
        if (Config.enchExamineEnable) {
            Examine = new EnchantmentExtraXP(Config.enchExamineID, Config.enchExamineWeight);
            MinecraftForge.EVENT_BUS.register(Examine);
            Enchs++;
        }
        if(Config.enchBoundEnable){
            Bound = new EnchantmentBound(Config.enchBoundID, Config.enchBoundWeight);
            MinecraftForge.EVENT_BUS.register(Bound);
            Enchs++;
        }
        if (Config.enchFlameTouchEnable) {
            FlameTouch = new EnchantmentFlameTouch(Config.enchFlameTouchID, Config.enchFlameTouchWeight);
            MinecraftForge.EVENT_BUS.register(FlameTouch);
            Enchs++;
        }
        if(Config.enchFlyEnable){
            Fly = new EnchantmentFly(Config.enchFlyID, Config.enchFlyWeight);
            MinecraftForge.EVENT_BUS.register(Fly);
            Enchs++;
        }
        if(Config.enchJumpBoostEnable) {
            JumpBoost = new EnchantmentJumpBoost(Config.enchJumpBoostID, Config.enchJumpBoostWeight);
            MinecraftForge.EVENT_BUS.register(JumpBoost);
            Enchs++;
        }
        if(Config.enchLifeStealEnable) {
            LifeSteal = new EnchantmentLifeSteal(Config.enchLifeStealID, Config.enchLifeStealWeight);
            MinecraftForge.EVENT_BUS.register(LifeSteal);
            Enchs++;
        }
        if(Config.enchLeadFootedEnable) {
            LeadFooted = new EnchantmentLifeSteal(Config.enchLeadFootedID, Config.enchLeadFootedWeight);
            MinecraftForge.EVENT_BUS.register(LeadFooted);
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
        if(Config.enchShroudEnable){
            Shroud = new EnchantmentShroud(Config.enchShroudID, Config.enchShroudWeight);
            MinecraftForge.EVENT_BUS.register(Shroud);
            Enchs++;
        }
        if(Config.enchSwiftnessEnable){
            Swiftness = new EnchantmentSwiftness(Config.enchSwiftnessID, Config.enchSwiftnessWeight);
            MinecraftForge.EVENT_BUS.register(Swiftness);
            Enchs++;
        }
        if(Config.enchStealthEnable){
            Stealth = new EnchantmentStealth(Config.enchStealthID, Config.enchStealthWeight);
            MinecraftForge.EVENT_BUS.register(Stealth);
            Enchs++;
        }
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
        if (HxCKDMS.HxCEnchants.Config.DebugMode) LogHelper.debug(Level.DEBUG, Enchs + "Enchantments Have Been Registered");
    }
}
