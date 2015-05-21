package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCCore.Utils.LogHelper;
import HxCKDMS.HxCEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Level;

public class Enchants {

    private static int Enchs = 0;

    public static Enchantment AdrenalineBoost;
    public static Enchantment AuraDark;
    public static Enchantment AuraDeadly;
    public static Enchantment AuraFiery;
    public static Enchantment AuraThick;
    public static Enchantment AuraToxic;
    public static Enchantment ArmorRegen;
    public static Enchantment ArrowExplosive;
    public static Enchantment ArrowLightning;
    public static Enchantment ArrowSeeking;
    public static Enchantment Bound;
    public static Enchantment BattleHealing;
    public static Enchantment DivineIntervention;
    public static Enchantment Examine;
    public static Enchantment FlameTouch;
    public static Enchantment Fly;
    public static Enchantment JumpBoost;
    public static Enchantment LeadFooted;
    public static Enchantment LifeSteal;
    public static Enchantment Poison;
    public static Enchantment Piercing;
    public static Enchantment Penetrating;
    public static Enchantment Repair;
    public static Enchantment Shroud;
    public static Enchantment SoulTear;
    public static Enchantment Swiftness;
    public static Enchantment Stealth;
    public static Enchantment Vampirism;
    public static Enchantment Venom;
    public static Enchantment Vitality;
    public static Enchantment WitherProtection;

    public static void load () {
        if (Config.enchAdrenalineBoostEnable) {
            AdrenalineBoost = new EnchantmentAdrenalineBoost(Config.enchAdrenalineBoostID, Config.enchAdrenalineBoostWeight);
            MinecraftForge.EVENT_BUS.register(AdrenalineBoost);
            Enchs++;
        }
        if (Config.enchAuraDarkEnable) {
            AuraDark = new EnchantmentDarkAura(Config.enchAuraDarkID, Config.enchAuraDarkWeight);
            MinecraftForge.EVENT_BUS.register(AuraDark);
            Enchs++;
        }
        if (Config.enchAuraDeadlyEnable) {
            AuraDeadly = new EnchantmentDeadlyAura(Config.enchAuraDeadlyID, Config.enchAuraDeadlyWeight);
            MinecraftForge.EVENT_BUS.register(AuraDeadly);
            Enchs++;
        }
        if (Config.enchAuraFieryEnable) {
            AuraFiery = new EnchantmentFieryAura(Config.enchAuraFieryID, Config.enchAuraFieryWeight);
            MinecraftForge.EVENT_BUS.register(AuraFiery);
            Enchs++;
        }
        if (Config.enchAuraThickEnable) {
            AuraThick = new EnchantmentThickAura(Config.enchAuraThickID, Config.enchAuraThickWeight);
            MinecraftForge.EVENT_BUS.register(AuraThick);
            Enchs++;
        }
        if (Config.enchAuraToxicEnable) {
            AuraToxic = new EnchantmentToxicAura(Config.enchAuraToxicID, Config.enchAuraToxicWeight);
            MinecraftForge.EVENT_BUS.register(AuraToxic);
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
        if(Config.enchBoundEnable){
            Bound = new EnchantmentBound(Config.enchBoundID, Config.enchBoundWeight);
            MinecraftForge.EVENT_BUS.register(Bound);
            Enchs++;
        }
        if (Config.enchDivineInterventionEnable) {
            DivineIntervention = new EnchantmentDivineIntervention(Config.enchDivineInterventionID, Config.enchDivineInterventionWeight);
            MinecraftForge.EVENT_BUS.register(DivineIntervention);
            Enchs++;
        }
        if (Config.enchExamineEnable) {
            Examine = new EnchantmentExtraXP(Config.enchExamineID, Config.enchExamineWeight);
            MinecraftForge.EVENT_BUS.register(Examine);
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
            LeadFooted = new EnchantmentLeadFooted(Config.enchLeadFootedID, Config.enchLeadFootedWeight);
            MinecraftForge.EVENT_BUS.register(LeadFooted);
            Enchs++;
        }
        if(Config.enchPoisonEnable) {
            Poison = new EnchantmentPoison(Config.enchPoisonID, Config.enchPoisonWeight);
            Venom = new EnchantmentVenom(Config.enchVenomID, Config.enchPoisonWeight);
            MinecraftForge.EVENT_BUS.register(Poison);
            Enchs++;
            MinecraftForge.EVENT_BUS.register(Venom);
            Enchs++;
        }
        if(Config.enchPiercingEnable) {
            Piercing = new EnchantmentPiercing(Config.enchPiercingID, Config.enchPiercingWeight);
            Penetrating = new EnchantmentPenetrating(Config.enchArrowPiercingID, Config.enchPiercingWeight);
            MinecraftForge.EVENT_BUS.register(Piercing);
            Enchs++;
            MinecraftForge.EVENT_BUS.register(Penetrating);
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
        if(Config.enchSoulTearEnable){
            SoulTear = new EnchantmentSoulTear(Config.enchSoulTearID, Config.enchSoulTearWeight);
            MinecraftForge.EVENT_BUS.register(SoulTear);
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
