package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.HxCEnchants.Config;
import HxCKDMS.HxCEnchants.lib.Reference;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

public class Enchants {

    private static int Enchs = 0;

    public static Enchantment AdrenalineBoost,
            AuraDark, AuraDeadly, AuraFiery, AuraThick, AuraToxic,
            ArmorRegen, ArrowExplosive, ArrowLightning, ArrowSeeking, Bound,
            BattleHealing, DivineIntervention, Examine, FlameTouch, Fly, JumpBoost,
            LeadFooted, LifeSteal, Poison, Piercing, Penetrating, Repair, Shroud, SCurse,
            SoulTear, Swiftness, Stealth, Vampirism, Venom, Vitality, Vorpal, WitherProtection;

    public static void load () {
        if (Config.enchAdrenalineBoostEnable) {
            AdrenalineBoost = new HxCEnchantment(Config.enchAdrenalineBoostVals[0], new ResourceLocation("adrenalineBoost"), Config.enchAdrenalineBoostVals[2], EnumEnchantmentType.ARMOR_HEAD, Config.enchAdrenalineBoostVals[1], Config.enchAdrenalineBoostVals[3]);
            MinecraftForge.EVENT_BUS.register(AdrenalineBoost);
            Enchs++;
        }
        if (Config.enchAuraDarkEnable) {
            AuraDark = new HxCEnchantment(Config.enchAuraDarkVals[0], new ResourceLocation("darkAura"), Config.enchAuraDarkVals[2], EnumEnchantmentType.ARMOR, Config.enchAuraDarkVals[1], Config.enchAuraDarkVals[3]);
            MinecraftForge.EVENT_BUS.register(AuraDark);
            Enchs++;
        }
        if (Config.enchAuraDeadlyEnable) {
            AuraDeadly = new HxCEnchantment(Config.enchAuraDeadlyVals[0], new ResourceLocation("deadlyAura"), Config.enchAuraDeadlyVals[2], EnumEnchantmentType.ARMOR, Config.enchAuraDeadlyVals[1], Config.enchAuraDeadlyVals[3]);
            MinecraftForge.EVENT_BUS.register(AuraDeadly);
            Enchs++;
        }
        if (Config.enchAuraFieryEnable) {
            AuraFiery = new HxCEnchantment(Config.enchAuraFieryVals[0], new ResourceLocation("flameAura"), Config.enchAuraFieryVals[2], EnumEnchantmentType.ARMOR, Config.enchAuraFieryVals[1], Config.enchAuraFieryVals[3]);
            MinecraftForge.EVENT_BUS.register(AuraFiery);
            Enchs++;
        }
        if (Config.enchAuraThickEnable) {
            AuraThick = new HxCEnchantment(Config.enchAuraThickVals[0], new ResourceLocation("thickAura"), Config.enchAuraThickVals[2], EnumEnchantmentType.ARMOR, Config.enchAuraThickVals[1], Config.enchAuraThickVals[3]);
            MinecraftForge.EVENT_BUS.register(AuraThick);
            Enchs++;
        }
        if (Config.enchAuraToxicEnable) {
            AuraToxic = new HxCEnchantment(Config.enchAuraToxicVals[0], new ResourceLocation("toxicAura"), Config.enchAuraToxicVals[2], EnumEnchantmentType.ARMOR, Config.enchAuraToxicVals[1], Config.enchAuraToxicVals[3]);
            MinecraftForge.EVENT_BUS.register(AuraToxic);
            Enchs++;
        }
        if (Config.enchRegenEnable) {
            ArmorRegen = new HxCEnchantment(Config.enchRegenVals[0], new ResourceLocation("regen"), Config.enchRegenVals[2], EnumEnchantmentType.ARMOR, Config.enchRegenVals[1], Config.enchRegenVals[3]);
            MinecraftForge.EVENT_BUS.register(ArmorRegen);
            Enchs++;
        }
        if (Config.enchArrowExplosiveEnable) {
            ArrowExplosive = new HxCEnchantment(Config.enchArrowExplosiveVals[0], new ResourceLocation("arrowExplosive"), Config.enchArrowExplosiveVals[2], EnumEnchantmentType.BOW, Config.enchArrowExplosiveVals[1], Config.enchArrowExplosiveVals[3]);
            MinecraftForge.EVENT_BUS.register(ArrowExplosive);
            Enchs++;
        }
        if (Config.enchArrowLightningEnable) {
            ArrowLightning = new HxCEnchantment(Config.enchArrowLightningVals[0], new ResourceLocation("arrowZeus"), Config.enchArrowLightningVals[2], EnumEnchantmentType.BOW, Config.enchArrowLightningVals[1], Config.enchArrowLightningVals[3]);
            MinecraftForge.EVENT_BUS.register(ArrowLightning);
            Enchs++;
        }
        if (Config.enchArrowSeekingEnable) {
            ArrowSeeking = new HxCEnchantment(Config.enchArrowSeekingVals[0], new ResourceLocation("arrowSeeking"), Config.enchArrowSeekingVals[2], EnumEnchantmentType.BOW, Config.enchArrowSeekingVals[1], Config.enchArrowSeekingVals[3]);
            MinecraftForge.EVENT_BUS.register(ArrowSeeking);
            Enchs++;
        }
        if (Config.enchBattleHealingEnable) {
            BattleHealing = new HxCEnchantment(Config.enchBattleHealingVals[0], new ResourceLocation("battleHeal"), Config.enchBattleHealingVals[2], EnumEnchantmentType.ARMOR_TORSO, Config.enchBattleHealingVals[1], Config.enchBattleHealingVals[3]);
            MinecraftForge.EVENT_BUS.register(BattleHealing);
            Enchs++;
        }
        if(Config.enchBoundEnable){
            Bound = new HxCEnchantment(Config.enchBoundVals[0], new ResourceLocation("bound"), Config.enchBoundVals[2], EnumEnchantmentType.ALL, Config.enchBoundVals[1], Config.enchBoundVals[3]);
            MinecraftForge.EVENT_BUS.register(Bound);
            Enchs++;
        }
        if (Config.enchDivineInterventionEnable) {
            DivineIntervention = new HxCEnchantment(Config.enchDivineInterventionVals[0], new ResourceLocation("divineIntervention"), Config.enchDivineInterventionVals[2], EnumEnchantmentType.ARMOR_TORSO, Config.enchDivineInterventionVals[1], Config.enchDivineInterventionVals[3]);
            MinecraftForge.EVENT_BUS.register(DivineIntervention);
            Enchs++;
        }
        if (Config.enchExamineEnable) {
            Examine = new HxCEnchantment(Config.enchExamineVals[0], new ResourceLocation("examine"), Config.enchExamineVals[2], EnumEnchantmentType.WEAPON, Config.enchExamineVals[1], Config.enchExamineVals[3]);
            MinecraftForge.EVENT_BUS.register(Examine);
            Enchs++;
        }
        if (Config.enchFlameTouchEnable) {
            FlameTouch = new HxCEnchantment(Config.enchFlameTouchVals[0], new ResourceLocation("flameTouch"), Config.enchFlameTouchVals[2], EnumEnchantmentType.DIGGER, Config.enchFlameTouchVals[1], Config.enchFlameTouchVals[3]);
            MinecraftForge.EVENT_BUS.register(FlameTouch);
            Enchs++;
        }
        if(Config.enchFlyEnable){
            Fly = new HxCEnchantment(Config.enchFlyVals[0], new ResourceLocation("fly"), Config.enchFlyVals[2], EnumEnchantmentType.ARMOR_FEET, Config.enchFlyVals[1], Config.enchFlyVals[3]);
            MinecraftForge.EVENT_BUS.register(Fly);
            Enchs++;
        }
        if(Config.enchJumpBoostEnable) {
            JumpBoost = new HxCEnchantment(Config.enchJumpBoostVals[0], new ResourceLocation("jumpBoost"), Config.enchJumpBoostVals[2], EnumEnchantmentType.ARMOR_LEGS, Config.enchJumpBoostVals[1], Config.enchJumpBoostVals[3]);
            MinecraftForge.EVENT_BUS.register(JumpBoost);
            Enchs++;
        }
        if(Config.enchLifeStealEnable) {
            LifeSteal = new HxCEnchantment(Config.enchLifeStealVals[0], new ResourceLocation("lifeSteal"), Config.enchLifeStealVals[2], EnumEnchantmentType.WEAPON, Config.enchLifeStealVals[1], Config.enchLifeStealVals[3]);
            MinecraftForge.EVENT_BUS.register(LifeSteal);
            Enchs++;
        }
        if(Config.enchLeadFootedEnable) {
            LeadFooted = new HxCEnchantment(Config.enchLeadFootedVals[0], new ResourceLocation("leadFooted"), Config.enchLeadFootedVals[2], EnumEnchantmentType.ARMOR_FEET, Config.enchLeadFootedVals[1], Config.enchLeadFootedVals[3]);
            MinecraftForge.EVENT_BUS.register(LeadFooted);
            Enchs++;
        }
        if(Config.enchPoisonEnable) {
            Poison = new HxCEnchantment(Config.enchPoisonVals[0], new ResourceLocation("poison"), Config.enchPoisonVals[2], EnumEnchantmentType.BOW, Config.enchPoisonVals[1], Config.enchPoisonVals[3]);
            Venom = new HxCEnchantment(Config.enchVenomVals[0], new ResourceLocation("venom"), Config.enchPoisonVals[2], EnumEnchantmentType.WEAPON, Config.enchPoisonVals[1], Config.enchPoisonVals[3]);
            MinecraftForge.EVENT_BUS.register(Poison);
            Enchs++;
            MinecraftForge.EVENT_BUS.register(Venom);
            Enchs++;
        }
        if(Config.enchPiercingEnable) {
            Piercing = new HxCEnchantment(Config.enchPiercingVals[0], new ResourceLocation("piercing"), Config.enchPiercingVals[2], EnumEnchantmentType.WEAPON, Config.enchPiercingVals[1], Config.enchPiercingVals[3]);
            Penetrating = new HxCEnchantment(Config.enchArrowPiercingVals[0], new ResourceLocation("penetrating"), Config.enchPiercingVals[2], EnumEnchantmentType.BOW, Config.enchPiercingVals[1], Config.enchPiercingVals[3]);
            MinecraftForge.EVENT_BUS.register(Piercing);
            Enchs++;
            MinecraftForge.EVENT_BUS.register(Penetrating);
            Enchs++;
        }
        if(Config.enchRepairEnable){
            Repair = new HxCEnchantment(Config.enchRepairVals[0], new ResourceLocation("repair"), Config.enchRepairVals[2], EnumEnchantmentType.WEAPON, Config.enchRepairVals[1], Config.enchRepairVals[3]);
            MinecraftForge.EVENT_BUS.register(Repair);
            Enchs++;
        }
        if(Config.enchShroudEnable){
            Shroud = new HxCEnchantment(Config.enchShroudVals[0], new ResourceLocation("shroud"), Config.enchShroudVals[2], EnumEnchantmentType.ARMOR, Config.enchShroudVals[1], Config.enchShroudVals[3]);
            MinecraftForge.EVENT_BUS.register(Shroud);
            Enchs++;
        }
        if(Config.enchSoulTearEnable){
            SoulTear = new HxCEnchantment(Config.enchSoulTearVals[0], new ResourceLocation("soulTear"), Config.enchSoulTearVals[2], EnumEnchantmentType.WEAPON, Config.enchSoulTearVals[1], Config.enchSoulTearVals[3]);
            MinecraftForge.EVENT_BUS.register(SoulTear);
            Enchs++;
        }
        if(Config.enchSCurseEnable){
            SCurse = new HxCEnchantment(Config.enchSCurseVals[0], new ResourceLocation("slayersCurse"), Config.enchSCurseVals[2], EnumEnchantmentType.WEAPON, Config.enchSCurseVals[1], Config.enchSCurseVals[3]);
            MinecraftForge.EVENT_BUS.register(SCurse);
            Enchs++;
        }
        if(Config.enchStealthEnable){
            Stealth = new HxCEnchantment(Config.enchStealthVals[0], new ResourceLocation("stealth"), Config.enchStealthVals[2], EnumEnchantmentType.ARMOR_FEET, Config.enchStealthVals[1], Config.enchStealthVals[3]);
            MinecraftForge.EVENT_BUS.register(Stealth);
            Enchs++;
        }
        if(Config.enchSwiftnessEnable){
            Swiftness = new HxCEnchantment(Config.enchSwiftnessVals[0], new ResourceLocation("swiftness"), Config.enchSwiftnessVals[2], EnumEnchantmentType.ARMOR_LEGS, Config.enchSwiftnessVals[1], Config.enchSwiftnessVals[3]);
            MinecraftForge.EVENT_BUS.register(Swiftness);
            Enchs++;
        }
        if(Config.enchVampirismEnable) {
            Vampirism = new HxCEnchantment(Config.enchVampirismVals[0], new ResourceLocation("vampirism"), Config.enchVampirismVals[2], EnumEnchantmentType.WEAPON, Config.enchVampirismVals[1], Config.enchVampirismVals[3]);
            MinecraftForge.EVENT_BUS.register(Vampirism);
            Enchs++;
        }
        if(Config.enchVitalityEnable) {
            Vitality = new HxCEnchantment(Config.enchVitalityVals[0], new ResourceLocation("vitality"), Config.enchVitalityVals[2], EnumEnchantmentType.ARMOR_TORSO, Config.enchVitalityVals[1], Config.enchVitalityVals[3]);
            MinecraftForge.EVENT_BUS.register(Vitality);
            Enchs++;
        }
        if(Config.enchVitalityEnable) {
            Vorpal = new HxCEnchantment(Config.enchVorpalVals[0], new ResourceLocation("vorpal"), Config.enchVorpalVals[2], EnumEnchantmentType.WEAPON, Config.enchVitalityVals[1], Config.enchVorpalVals[3]);
            MinecraftForge.EVENT_BUS.register(Vorpal);
            Enchs++;
        }
        if(Config.enchWitherProtectionEnable) {
            WitherProtection = new HxCEnchantment(Config.enchWitherProtectionVals[0], new ResourceLocation("witherProt"), Config.enchWitherProtectionVals[2], EnumEnchantmentType.ARMOR_HEAD, Config.enchWitherProtectionVals[1], Config.enchWitherProtectionVals[3]);
            MinecraftForge.EVENT_BUS.register(WitherProtection);
            Enchs++;
        }
        if (HxCKDMS.HxCEnchants.Config.DebugMode)
            LogHelper.warn(Enchs + " enchantments have been registered.", Reference.MOD_NAME);
    }
}
