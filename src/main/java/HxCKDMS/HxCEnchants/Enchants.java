package HxCKDMS.HxCEnchants;

import HxCKDMS.HxCCore.Utils.LogHelper;
import org.apache.logging.log4j.Level;

@SuppressWarnings("unused")
public class Enchants {

    private static int Enchs = 0;

    /** AdrenalineBoost;       [00] **/
    /** AuraDark;              [01] **/
    /** AuraDeadly;            [02] **/
    /** AuraFiery;             [03] **/
    /** AuraThick;             [04] **/
    /** AuraToxic;             [05] **/
    /** AirStrider;            [06] **/ //removed so empty
    /** ArmorRegen;            [07] **/
    /** ArrowExplosive;        [08] **/
    /** ArrowLightning;        [09] **/
    /** ArrowSeeking;          [10] **/
    /** Bound;                 [11] **/
    /** BattleHealing;         [12] **/
    /** FlameTouch;            [13] **/
    /** Fly;                   [14] **/
    /** JumpBoost;             [15] **/
    /** LeadFooted;            [16] **/
    /** LifeSteal;             [17] **/
    /** Poison;                [18] **/
    /** Repair;                [19] **/
    /** Shroud;                [20] **/
    /** SoulTear;              [21] **/
    /** Swiftness;             [22] **/
    /** Stealth;               [23] **/
    /** Vampirism;             [24] **/
    /** Vitality;              [25] **/
    /** WitherProtection;      [26] **/
    /** Examine;               [27] **/

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
//        if (Config.enchAirStriderEnable){
//            Enchs++;
//        }
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
