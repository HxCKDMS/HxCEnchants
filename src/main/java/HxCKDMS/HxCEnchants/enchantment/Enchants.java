package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.HxCEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Level;

public class Enchants {

    private static int Enchs = 0;

    public static Enchantment AdrenalineBoost,
            AuraDark, AuraDeadly, AuraFiery, AuraThick, AuraToxic,
            ArmorRegen, ArrowExplosive, ArrowLightning, ArrowSeeking, Bound,
            BattleHealing, DivineIntervention, Examine, FlameTouch, Fly, JumpBoost,
            LeadFooted, LifeSteal, Poison, Piercing, Penetrating, Repair, Shroud,
            SoulTear, Swiftness, Stealth, Vampirism, Venom, Vitality, WitherProtection;

    public static void load () {
        if (Config.enchAdrenalineBoostEnable) {
            AdrenalineBoost = new HxCEnchantment(Config.enchAdrenalineBoostID, "adrenalineBoost",Config.enchAdrenalineBoostWeight, EnumEnchantmentType.armor_head, Config.enchAdrenalineBoostLVL);
            MinecraftForge.EVENT_BUS.register(AdrenalineBoost);
            Enchs++;
        }
        if (Config.enchAuraDarkEnable) {
            AuraDark = new HxCEnchantment(Config.enchAuraDarkID, "darkAura", Config.enchAuraDarkWeight, EnumEnchantmentType.armor, Config.enchAuraDarkLVL);
            MinecraftForge.EVENT_BUS.register(AuraDark);
            Enchs++;
        }
        if (Config.enchAuraDeadlyEnable) {
            AuraDeadly = new HxCEnchantment(Config.enchAuraDeadlyID, "deadlyAura", Config.enchAuraDeadlyWeight, EnumEnchantmentType.armor, Config.enchAuraDeadlyLVL);
            MinecraftForge.EVENT_BUS.register(AuraDeadly);
            Enchs++;
        }
        if (Config.enchAuraFieryEnable) {
            AuraFiery = new HxCEnchantment(Config.enchAuraFieryID, "flameAura", Config.enchAuraFieryWeight, EnumEnchantmentType.armor, Config.enchAuraFieryLVL);
            MinecraftForge.EVENT_BUS.register(AuraFiery);
            Enchs++;
        }
        if (Config.enchAuraThickEnable) {
            AuraThick = new HxCEnchantment(Config.enchAuraThickID, "thickAura", Config.enchAuraThickWeight, EnumEnchantmentType.armor, Config.enchAuraThickLVL);
            MinecraftForge.EVENT_BUS.register(AuraThick);
            Enchs++;
        }
        if (Config.enchAuraToxicEnable) {
            AuraToxic = new HxCEnchantment(Config.enchAuraToxicID, "toxicAura", Config.enchAuraToxicWeight, EnumEnchantmentType.armor, Config.enchAuraToxicLVL);
            MinecraftForge.EVENT_BUS.register(AuraToxic);
            Enchs++;
        }
        if (Config.enchRegenEnable) {
            ArmorRegen = new HxCEnchantment(Config.enchRegenID, "regen", Config.enchRegenWeight, EnumEnchantmentType.armor, Config.enchRegenLVL);
            MinecraftForge.EVENT_BUS.register(ArmorRegen);
            Enchs++;
        }
        if (Config.enchArrowExplosiveEnable) {
            ArrowExplosive = new HxCEnchantment(Config.enchArrowExplosiveID, "arrowExplosive", Config.enchArrowExplosiveWeight, EnumEnchantmentType.bow, Config.enchArrowExplosiveLVL);
            MinecraftForge.EVENT_BUS.register(ArrowExplosive);
            Enchs++;
        }
        if (Config.enchArrowLightningEnable) {
            ArrowLightning = new HxCEnchantment(Config.enchArrowLightningID, "arrowZeus", Config.enchArrowLightningWeight, EnumEnchantmentType.bow, Config.enchArrowLightningLVL);
            MinecraftForge.EVENT_BUS.register(ArrowLightning);
            Enchs++;
        }
        if (Config.enchArrowSeekingEnable) {
            ArrowSeeking = new HxCEnchantment(Config.enchArrowSeekingID, "arrowSeeking", Config.enchArrowSeekingWeight, EnumEnchantmentType.bow, Config.enchArrowSeekingLVL);
            MinecraftForge.EVENT_BUS.register(ArrowSeeking);
            Enchs++;
        }
        if (Config.enchBattleHealingEnable) {
            BattleHealing = new HxCEnchantment(Config.enchBattleHealingID, "battleHeal", Config.enchBattleHealingWeight, EnumEnchantmentType.armor_torso, Config.enchBattleHealingLVL);
            MinecraftForge.EVENT_BUS.register(BattleHealing);
            Enchs++;
        }
        if(Config.enchBoundEnable){
            Bound = new HxCEnchantment(Config.enchBoundID, "bound", Config.enchBoundWeight, EnumEnchantmentType.all, Config.enchBoundLVL);
            MinecraftForge.EVENT_BUS.register(Bound);
            Enchs++;
        }
        if (Config.enchDivineInterventionEnable) {
            DivineIntervention = new HxCEnchantment(Config.enchDivineInterventionID, "divineIntervention", Config.enchDivineInterventionWeight, EnumEnchantmentType.armor_torso, Config.enchDivineInterventionLVL);
            MinecraftForge.EVENT_BUS.register(DivineIntervention);
            Enchs++;
        }
        if (Config.enchExamineEnable) {
            Examine = new HxCEnchantment(Config.enchExamineID, "examine", Config.enchExamineWeight, EnumEnchantmentType.weapon, Config.enchExamineLVL);
            MinecraftForge.EVENT_BUS.register(Examine);
            Enchs++;
        }
        if (Config.enchFlameTouchEnable) {
            FlameTouch = new HxCEnchantment(Config.enchFlameTouchID, "flameTouch", Config.enchFlameTouchWeight, EnumEnchantmentType.digger, Config.enchFlameTouchLVL);
            MinecraftForge.EVENT_BUS.register(FlameTouch);
            Enchs++;
        }
        if(Config.enchFlyEnable){
            Fly = new HxCEnchantment(Config.enchFlyID, "fly", Config.enchFlyWeight, EnumEnchantmentType.armor_feet, 10);
            MinecraftForge.EVENT_BUS.register(Fly);
            Enchs++;
        }
        if(Config.enchJumpBoostEnable) {
            JumpBoost = new HxCEnchantment(Config.enchJumpBoostID, "jumpBoost", Config.enchJumpBoostWeight, EnumEnchantmentType.armor_legs, Config.enchJumpBoostLVL);
            MinecraftForge.EVENT_BUS.register(JumpBoost);
            Enchs++;
        }
        if(Config.enchLifeStealEnable) {
            LifeSteal = new HxCEnchantment(Config.enchLifeStealID, "lifeSteal", Config.enchLifeStealWeight, EnumEnchantmentType.weapon, Config.enchLifeStealLVL);
            MinecraftForge.EVENT_BUS.register(LifeSteal);
            Enchs++;
        }
        if(Config.enchLeadFootedEnable) {
            LeadFooted = new HxCEnchantment(Config.enchLeadFootedID, "leadFooted", Config.enchLeadFootedWeight, EnumEnchantmentType.armor_feet, Config.enchLeadFootedLVL);
            MinecraftForge.EVENT_BUS.register(LeadFooted);
            Enchs++;
        }
        if(Config.enchPoisonEnable) {
            Poison = new HxCEnchantment(Config.enchPoisonID, "poison", Config.enchPoisonWeight, EnumEnchantmentType.bow, Config.enchPoisonLVL);
            Venom = new HxCEnchantment(Config.enchVenomID, "venom", Config.enchPoisonWeight, EnumEnchantmentType.weapon, Config.enchPoisonLVL);
            MinecraftForge.EVENT_BUS.register(Poison);
            Enchs++;
            MinecraftForge.EVENT_BUS.register(Venom);
            Enchs++;
        }
        if(Config.enchPiercingEnable) {
            Piercing = new HxCEnchantment(Config.enchPiercingID, "piercing", Config.enchPiercingWeight, EnumEnchantmentType.weapon, Config.enchPiercingLVL);
            Penetrating = new HxCEnchantment(Config.enchArrowPiercingID, "penetrating", Config.enchPiercingWeight, EnumEnchantmentType.bow, Config.enchPiercingLVL);
            MinecraftForge.EVENT_BUS.register(Piercing);
            Enchs++;
            MinecraftForge.EVENT_BUS.register(Penetrating);
            Enchs++;
        }
        if(Config.enchRepairEnable){
            Repair = new HxCEnchantment(Config.enchRepairID, "repair", Config.enchRepairWeight, EnumEnchantmentType.weapon, Config.enchRepairLVL);
            MinecraftForge.EVENT_BUS.register(Repair);
            Enchs++;
        }
        if(Config.enchShroudEnable){
            Shroud = new HxCEnchantment(Config.enchShroudID, "shroud", Config.enchShroudWeight, EnumEnchantmentType.armor, Config.enchShroudLVL);
            MinecraftForge.EVENT_BUS.register(Shroud);
            Enchs++;
        }
        if(Config.enchSoulTearEnable){
            SoulTear = new HxCEnchantment(Config.enchSoulTearID, "soulTear", Config.enchSoulTearWeight, EnumEnchantmentType.weapon, Config.enchSoulTearLVL);
            MinecraftForge.EVENT_BUS.register(SoulTear);
            Enchs++;
        }
        if(Config.enchSwiftnessEnable){
            Swiftness = new HxCEnchantment(Config.enchSwiftnessID, "swiftness", Config.enchSwiftnessWeight, EnumEnchantmentType.armor_legs, Config.enchSwiftnessLVL);
            MinecraftForge.EVENT_BUS.register(Swiftness);
            Enchs++;
        }
        if(Config.enchStealthEnable){
            Stealth = new HxCEnchantment(Config.enchStealthID, "stealth", Config.enchStealthWeight, EnumEnchantmentType.armor_feet, Config.enchStealthLVL);
            MinecraftForge.EVENT_BUS.register(Stealth);
            Enchs++;
        }
        if(Config.enchVampirismEnable) {
            Vampirism = new HxCEnchantment(Config.enchVampirismID, "vampirism", Config.enchVampirismWeight, EnumEnchantmentType.weapon, Config.enchVampirismLVL);
            MinecraftForge.EVENT_BUS.register(Vampirism);
            Enchs++;
        }
        if(Config.enchVitalityEnable) {
            Vitality = new HxCEnchantment(Config.enchVitalityID, "vitality", Config.enchVitalityWeight, EnumEnchantmentType.armor_torso, Config.enchVitalityLVL);
            MinecraftForge.EVENT_BUS.register(Vitality);
            Enchs++;
        }
        if(Config.enchWitherProtectionEnable) {
            WitherProtection = new HxCEnchantment(Config.enchWitherProtectionID, "witherProt", Config.enchWitherProtectionWeight, EnumEnchantmentType.armor_head, Config.enchWitherProtectionLVL);
            MinecraftForge.EVENT_BUS.register(WitherProtection);
            Enchs++;
        }
        if (HxCKDMS.HxCEnchants.Config.DebugMode) LogHelper.debug(Level.DEBUG, Enchs + "Enchantments Have Been Registered");
    }
}
