package HxCKDMS.HxCEnchants;

import HxCKDMS.HxCCore.Utils.LogHelper;
import net.minecraft.enchantment.Enchantment;
import org.apache.logging.log4j.Level;

public class Enchants {

    private static int Enchs = 0;

    public static Enchantment AdrenalineBoost;      /** 1 **/
    public static Enchantment AuraDark;             /** 2 **/
    public static Enchantment AuraDeadly;           /** 3 **/
    public static Enchantment AuraFiery;            /** 4 **/
    public static Enchantment AuraThick;            /** 5 **/
    public static Enchantment AuraToxic;            /** 6 **/
    public static Enchantment AirStrider;           /** 7 **/
    public static Enchantment ArmorRegen;           /** 8 **/
    public static Enchantment ArrowExplosive;       /** 9 **/
    public static Enchantment ArrowLightning;       /** 10 **/
    public static Enchantment ArrowSeeking;         /** 11 **/
    public static Enchantment Bound;                /** 12 **/
    public static Enchantment BattleHealing;        /** 13 **/
    public static Enchantment FlameTouch;           /** 14 **/
    public static Enchantment Fly;                  /** 15 **/
    public static Enchantment JumpBoost;            /** 16 **/
    public static Enchantment LeadFooted;           /** 17 **/
    public static Enchantment LifeSteal;            /** 18 **/
    public static Enchantment Poison;               /** 19 **/
    public static Enchantment Repair;               /** 20 **/
    public static Enchantment Shroud;               /** 21 **/
    public static Enchantment SoulTear;             /** 22 **/
    public static Enchantment Swiftness;            /** 23 **/
    public static Enchantment Stealth;              /** 24 **/
    public static Enchantment Vampirism;            /** 25 **/
    public static Enchantment Vitality;             /** 26 **/
    public static Enchantment WitherProtection;     /** 27 **/
    public static Enchantment Examine;              /** 28 **/

    public static void load () {
        if (Config.enchAdrenalineBoostEnable) {
            Enchs++;
        }
        if (Config.enchAuraDarkEnable) {
            Enchs++;
        }
        if (Config.enchAuraDeadlyEnable) {
            Enchs++;
        }
        if (Config.enchAuraFieryEnable) {
            Enchs++;
        }
        if (Config.enchAuraThickEnable) {
            Enchs++;
        }
        if (Config.enchAuraToxicEnable) {
            Enchs++;
        }
        if (Config.enchAirStriderEnable){
            Enchs++;
        }
        if (Config.enchRegenEnable) {
            Enchs++;
        }
        if (Config.enchArrowExplosiveEnable) {
            Enchs++;
        }
        if (Config.enchArrowLightningEnable) {
            Enchs++;
        }
        if (Config.enchArrowSeekingEnable) {
            Enchs++;
        }
        if (Config.enchBattleHealingEnable) {
            Enchs++;
        }
        if (Config.enchExamineEnable) {
            Enchs++;
        }
        if (Config.enchBoundEnable){
            Enchs++;
        }
        if (Config.enchFlameTouchEnable) {
            Enchs++;
        }
        if (Config.enchFlyEnable){
            Enchs++;
        }
        if (Config.enchJumpBoostEnable) {
            Enchs++;
        }
        if (Config.enchLifeStealEnable) {
            Enchs++;
        }
        if (Config.enchLeadFootedEnable) {
            Enchs++;
        }
        if (Config.enchPoisonEnable) {
            Enchs++;
        }
        if (Config.enchRepairEnable){
            Enchs++;
        }
        if (Config.enchShroudEnable){
            Enchs++;
        }
        if (Config.enchSoulTearEnable){
            Enchs++;
        }
        if (Config.enchSwiftnessEnable){
            Enchs++;
        }
        if (Config.enchStealthEnable){
            Enchs++;
        }
        if (Config.enchVampirismEnable) {
            Enchs++;
        }
        if (Config.enchVitalityEnable) {
            Enchs++;
        }
        if (Config.enchWitherProtectionEnable) {
           Enchs++;
        }
        if (HxCKDMS.HxCEnchants.Config.DebugMode) LogHelper.debug(Level.DEBUG, Enchs + "Enchantments Have Been Registered");
    }
}
