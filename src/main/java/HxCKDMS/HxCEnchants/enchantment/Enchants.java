package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.HxCEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.ResourceLocation;
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
            AdrenalineBoost = new HxCEnchantment(Config.enchAdrenalineBoostID, new ResourceLocation("adrenalineBoost"),Config.enchAdrenalineBoostWeight, EnumEnchantmentType.WEAPON, Config.enchAdrenalineBoostLVL);
            MinecraftForge.EVENT_BUS.register(AdrenalineBoost);
            Enchs++;
        }
        if (Config.enchAuraDarkEnable) {
            AuraDark = new HxCEnchantment(Config.enchAuraDarkID, new ResourceLocation("darkAura"),Config.enchAuraDarkWeight, EnumEnchantmentType.ARMOR, Config.enchAuraDarkLVL);
            MinecraftForge.EVENT_BUS.register(AuraDark);
            Enchs++;
        }
        if (Config.enchAuraDeadlyEnable) {
            AuraDeadly = new HxCEnchantment(Config.enchAuraDeadlyID, new ResourceLocation("deadlyAura"),Config.enchAuraDeadlyWeight, EnumEnchantmentType.ARMOR, Config.enchAuraDeadlyLVL);
            MinecraftForge.EVENT_BUS.register(AuraDeadly);
            Enchs++;
        }
        if (Config.enchAuraFieryEnable) {
            AuraFiery = new HxCEnchantment(Config.enchAuraFieryID, new ResourceLocation("flameAura"), Config.enchAuraFieryWeight, EnumEnchantmentType.ARMOR, Config.enchAuraFieryLVL);
            MinecraftForge.EVENT_BUS.register(AuraFiery);
            Enchs++;
        }
        if (Config.enchAuraThickEnable) {
            AuraThick = new HxCEnchantment(Config.enchAuraThickID, new ResourceLocation("thickAura"), Config.enchAuraThickWeight, EnumEnchantmentType.ARMOR, Config.enchAuraThickLVL);
            MinecraftForge.EVENT_BUS.register(AuraThick);
            Enchs++;
        }
        if (Config.enchAuraToxicEnable) {
            AuraToxic = new HxCEnchantment(Config.enchAuraToxicID, new ResourceLocation("toxicAura"), Config.enchAuraToxicWeight, EnumEnchantmentType.ARMOR, Config.enchAuraToxicLVL);
            MinecraftForge.EVENT_BUS.register(AuraToxic);
            Enchs++;
        }
        if (Config.enchRegenEnable) {
            ArmorRegen = new HxCEnchantment(Config.enchRegenID, new ResourceLocation("regen"), Config.enchRegenWeight, EnumEnchantmentType.ARMOR, Config.enchRegenLVL);
            MinecraftForge.EVENT_BUS.register(ArmorRegen);
            Enchs++;
        }
        if (Config.enchArrowExplosiveEnable) {
            ArrowExplosive = new HxCEnchantment(Config.enchArrowExplosiveID, new ResourceLocation("arrowExplosive"), Config.enchArrowExplosiveWeight, EnumEnchantmentType.BOW, Config.enchArrowExplosiveLVL);
            MinecraftForge.EVENT_BUS.register(ArrowExplosive);
            Enchs++;
        }
        if (Config.enchArrowLightningEnable) {
            ArrowLightning = new HxCEnchantment(Config.enchArrowLightningID, new ResourceLocation("arrowZeus"), Config.enchArrowLightningWeight, EnumEnchantmentType.BOW, Config.enchArrowLightningLVL);
            MinecraftForge.EVENT_BUS.register(ArrowLightning);
            Enchs++;
        }
        if (Config.enchArrowSeekingEnable) {
            ArrowSeeking = new HxCEnchantment(Config.enchArrowSeekingID, new ResourceLocation("arrowSeeking"), Config.enchArrowSeekingWeight, EnumEnchantmentType.BOW, Config.enchArrowSeekingLVL);
            MinecraftForge.EVENT_BUS.register(ArrowSeeking);
            Enchs++;
        }
        if (Config.enchBattleHealingEnable) {
            BattleHealing = new HxCEnchantment(Config.enchBattleHealingID, new ResourceLocation("battleHeal"), Config.enchBattleHealingWeight, EnumEnchantmentType.ARMOR, Config.enchBattleHealingLVL);
            MinecraftForge.EVENT_BUS.register(BattleHealing);
            Enchs++;
        }
        if(Config.enchBoundEnable){
            Bound = new HxCEnchantment(Config.enchBoundID, new ResourceLocation("bound"), Config.enchBoundWeight, EnumEnchantmentType.ALL, Config.enchBoundLVL);
            MinecraftForge.EVENT_BUS.register(Bound);
            Enchs++;
        }
        if (Config.enchDivineInterventionEnable) {
            DivineIntervention = new HxCEnchantment(Config.enchDivineInterventionID, new ResourceLocation("divineIntervention"), Config.enchDivineInterventionWeight, EnumEnchantmentType.ARMOR_TORSO, Config.enchDivineInterventionLVL);
            MinecraftForge.EVENT_BUS.register(DivineIntervention);
            Enchs++;
        }
        if (Config.enchExamineEnable) {
            Examine = new HxCEnchantment(Config.enchExamineID, new ResourceLocation("examine"), Config.enchExamineWeight, EnumEnchantmentType.WEAPON, Config.enchExamineLVL);
            MinecraftForge.EVENT_BUS.register(Examine);
            Enchs++;
        }
        if (Config.enchFlameTouchEnable) {
            FlameTouch = new HxCEnchantment(Config.enchFlameTouchID, new ResourceLocation("flameTouch"), Config.enchFlameTouchWeight, EnumEnchantmentType.DIGGER, Config.enchFlameTouchLVL);
            MinecraftForge.EVENT_BUS.register(FlameTouch);
            Enchs++;
        }
        if(Config.enchFlyEnable){
            Fly = new HxCEnchantment(Config.enchFlyID, new ResourceLocation("fly"), Config.enchFlyWeight, EnumEnchantmentType.ARMOR_FEET, 10);
            MinecraftForge.EVENT_BUS.register(Fly);
            Enchs++;
        }
        if(Config.enchJumpBoostEnable) {
            JumpBoost = new HxCEnchantment(Config.enchJumpBoostID, new ResourceLocation("jumpBoost"), Config.enchJumpBoostWeight, EnumEnchantmentType.ARMOR_LEGS, Config.enchJumpBoostLVL);
            MinecraftForge.EVENT_BUS.register(JumpBoost);
            Enchs++;
        }
        if(Config.enchLifeStealEnable) {
            LifeSteal = new HxCEnchantment(Config.enchLifeStealID, new ResourceLocation("lifeSteal"), Config.enchLifeStealWeight, EnumEnchantmentType.WEAPON, Config.enchLifeStealLVL);
            MinecraftForge.EVENT_BUS.register(LifeSteal);
            Enchs++;
        }
        if(Config.enchLeadFootedEnable) {
            LeadFooted = new HxCEnchantment(Config.enchLeadFootedID, new ResourceLocation("leadFooted"), Config.enchLeadFootedWeight, EnumEnchantmentType.ARMOR_FEET, Config.enchLeadFootedLVL);
            MinecraftForge.EVENT_BUS.register(LeadFooted);
            Enchs++;
        }
        if(Config.enchPoisonEnable) {
            Poison = new HxCEnchantment(Config.enchPoisonID, new ResourceLocation("poison"), Config.enchPoisonWeight, EnumEnchantmentType.BOW, Config.enchPoisonLVL);
            Venom = new HxCEnchantment(Config.enchVenomID, new ResourceLocation("venom"), Config.enchPoisonWeight, EnumEnchantmentType.WEAPON, Config.enchPoisonLVL);
            MinecraftForge.EVENT_BUS.register(Poison);
            Enchs++;
            MinecraftForge.EVENT_BUS.register(Venom);
            Enchs++;
        }
        if(Config.enchPiercingEnable) {
            Piercing = new HxCEnchantment(Config.enchPiercingID, new ResourceLocation("piercing"), Config.enchPiercingWeight, EnumEnchantmentType.WEAPON, Config.enchPiercingLVL);
            Penetrating = new HxCEnchantment(Config.enchArrowPiercingID, new ResourceLocation("penetrating"), Config.enchPiercingWeight, EnumEnchantmentType.BOW, Config.enchPiercingLVL);
            MinecraftForge.EVENT_BUS.register(Piercing);
            Enchs++;
            MinecraftForge.EVENT_BUS.register(Penetrating);
            Enchs++;
        }
        if(Config.enchRepairEnable){
            Repair = new HxCEnchantment(Config.enchRepairID, new ResourceLocation("repair"), Config.enchRepairWeight, EnumEnchantmentType.WEAPON, Config.enchRepairLVL);
            MinecraftForge.EVENT_BUS.register(Repair);
            Enchs++;
        }
        if(Config.enchShroudEnable){
            Shroud = new HxCEnchantment(Config.enchShroudID, new ResourceLocation("shroud"), Config.enchShroudWeight, EnumEnchantmentType.ARMOR, Config.enchShroudLVL);
            MinecraftForge.EVENT_BUS.register(Shroud);
            Enchs++;
        }
        if(Config.enchSoulTearEnable){
            SoulTear = new HxCEnchantment(Config.enchSoulTearID, new ResourceLocation("soulTear"), Config.enchSoulTearWeight, EnumEnchantmentType.WEAPON, Config.enchSoulTearLVL);
            MinecraftForge.EVENT_BUS.register(SoulTear);
            Enchs++;
        }
        if(Config.enchSwiftnessEnable){
            Swiftness = new HxCEnchantment(Config.enchSwiftnessID, new ResourceLocation("swiftness"), Config.enchSwiftnessWeight, EnumEnchantmentType.ARMOR_LEGS, Config.enchSwiftnessLVL);
            MinecraftForge.EVENT_BUS.register(Swiftness);
            Enchs++;
        }
        if(Config.enchStealthEnable){
            Stealth = new HxCEnchantment(Config.enchStealthID, new ResourceLocation("stealth"), Config.enchStealthWeight, EnumEnchantmentType.ARMOR_FEET, Config.enchStealthLVL);
            MinecraftForge.EVENT_BUS.register(Stealth);
            Enchs++;
        }
        if(Config.enchVampirismEnable) {
            Vampirism = new HxCEnchantment(Config.enchVampirismID, new ResourceLocation("vampirism"), Config.enchVampirismWeight, EnumEnchantmentType.WEAPON, Config.enchVampirismLVL);
            MinecraftForge.EVENT_BUS.register(Vampirism);
            Enchs++;
        }
        if(Config.enchVitalityEnable) {
            Vitality = new HxCEnchantment(Config.enchVitalityID, new ResourceLocation("vitality"), Config.enchVitalityWeight, EnumEnchantmentType.ARMOR_TORSO, Config.enchVitalityLVL);
            MinecraftForge.EVENT_BUS.register(Vitality);
            Enchs++;
        }
        if(Config.enchWitherProtectionEnable) {
            WitherProtection = new HxCEnchantment(Config.enchWitherProtectionID, new ResourceLocation("witherProt"), Config.enchWitherProtectionWeight, EnumEnchantmentType.ARMOR_HEAD, Config.enchWitherProtectionLVL);
            MinecraftForge.EVENT_BUS.register(WitherProtection);
            Enchs++;
        }
        if (HxCKDMS.HxCEnchants.Config.DebugMode) LogHelper.debug(Level.DEBUG, Enchs + "Enchantments Have Been Registered");
    }
}
