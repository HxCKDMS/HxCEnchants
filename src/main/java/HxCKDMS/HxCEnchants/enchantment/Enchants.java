package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCCore.Configs.Configurations;
import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.HxCEnchants.EnchantConfigHandler;
import HxCKDMS.HxCEnchants.lib.Reference;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInterModComms;

@SuppressWarnings("all")
public class Enchants {
    public static short Enchs = 0;
    
    private static short[] data;

    public static Enchantment AdrenalineBoost,
            AuraDark, AuraDeadly, AuraFiery, AuraThick, AuraToxic,
            ArmorRegen, ArrowExplosive, Zeus, ArrowSeeking, Bound,
            BattleHealing, DivineIntervention, Examine, FlameTouch,
            Fly, JumpBoost, LeadFooted, LifeSteal, Poison, Piercing,
            Penetrating, Repair, Shroud, SCurse, SoulTear, Swiftness,
            Stealth, Vampirism, Venom, Vitality, Vorpal, WitherProtection,
            SpeedMine, VoidTouch, Overcharge, MeteorFall, PipeMine, EnchLeech,
            ExplosiveDischarge, FeatherFall, FlashStep, HealingAura,
            RepulsiveAura, AuraMagnetic, GaiaAura, IcyAura, LightningArrow, Gluttony, FlamingArrow;

    public static void load () {
        if (EnchantConfigHandler.isEnabled("AdrenalineBoost", "armor")) {
            data = EnchantConfigHandler.getData("AdrenalineBoost", "armor");
            AdrenalineBoost = new HxCEnchantment(data[0], new ResourceLocation("adrenalineBoost"),data[2], EnumHxCEnchantType.ARMOR_HEAD, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(AdrenalineBoost);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", AdrenalineBoost.getName());
        }
        if (EnchantConfigHandler.isEnabled("AuraDark", "armor")) {
            data = EnchantConfigHandler.getData("AuraDark", "armor");
            AuraDark = new HxCEnchantment(data[0], new ResourceLocation("darkAura"), data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(AuraDark);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", AuraDark.getName());
        }
        if (EnchantConfigHandler.isEnabled("AuraDeadly", "armor")) {
            data = EnchantConfigHandler.getData("AuraDeadly", "armor");
            AuraDeadly = new HxCEnchantment(data[0], new ResourceLocation("deadlyAura"), data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{GaiaAura, HealingAura});
            MinecraftForge.EVENT_BUS.register(AuraDeadly);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", AuraDeadly.getName());
        }
        if (EnchantConfigHandler.isEnabled("AuraFiery", "armor")) {
            data = EnchantConfigHandler.getData("AuraFiery", "armor");
            AuraFiery = new HxCEnchantment(data[0], new ResourceLocation("flameAura"), data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{HealingAura, GaiaAura, IcyAura});
            MinecraftForge.EVENT_BUS.register(AuraFiery);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", AuraFiery.getName());
        }
        if (EnchantConfigHandler.isEnabled("AuraThick", "armor")) {
            data = EnchantConfigHandler.getData("AuraThick", "armor");
            AuraThick = new HxCEnchantment(data[0], new ResourceLocation("thickAura"), data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(AuraThick);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", AuraThick.getName());
        }
        if (EnchantConfigHandler.isEnabled("AuraToxic", "armor")) {
            data = EnchantConfigHandler.getData("AuraToxic", "armor");
            AuraToxic = new HxCEnchantment(data[0], new ResourceLocation("toxicAura"), data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{GaiaAura, HealingAura});
            MinecraftForge.EVENT_BUS.register(AuraToxic);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", AuraToxic.getName());
        }
        if (EnchantConfigHandler.isEnabled("Regen", "armor")) {
            data = EnchantConfigHandler.getData("Regen", "armor");
            ArmorRegen = new HxCEnchantment(data[0], new ResourceLocation("regen"), data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{BattleHealing});
            MinecraftForge.EVENT_BUS.register(ArmorRegen);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", ArmorRegen.getName());
        }
        if (EnchantConfigHandler.isEnabled("ArrowExplosive", "weapon")) {
            data = EnchantConfigHandler.getData("ArrowExplosive", "weapon");
            ArrowExplosive = new HxCEnchantment(data[0], new ResourceLocation("arrowExplosive"), data[2], EnumHxCEnchantType.BOW, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(ArrowExplosive);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", ArrowExplosive.getName());
        }
        if (EnchantConfigHandler.isEnabled("Zeus", "weapon")) {
            data = EnchantConfigHandler.getData("Zeus", "weapon");
            Zeus = new HxCEnchantment(data[0], new ResourceLocation("arrowZeus"), data[2], EnumHxCEnchantType.BOW, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Zeus);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Zeus.getName());
        }
        if (EnchantConfigHandler.isEnabled("ArrowSeeking", "weapon")) {
            data = EnchantConfigHandler.getData("ArrowSeeking", "weapon");
            ArrowSeeking = new HxCEnchantment(data[0], new ResourceLocation("arrowSeeking"), data[2], EnumHxCEnchantType.BOW, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(ArrowSeeking);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", ArrowSeeking.getName());
        }
        if (EnchantConfigHandler.isEnabled("BattleHealing", "armor")) {
            data = EnchantConfigHandler.getData("BattleHealing", "armor");
            BattleHealing = new HxCEnchantment(data[0], new ResourceLocation("battleHeal"), data[2], EnumHxCEnchantType.ARMOR_TORSO, data[1], data[3], new Enchantment[]{ArmorRegen});
            MinecraftForge.EVENT_BUS.register(BattleHealing);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", BattleHealing.getName());
        }
        if (EnchantConfigHandler.isEnabled("Bound", "other")) {
            data = EnchantConfigHandler.getData("Bound", "other");
            Bound = new HxCEnchantment(data[0], new ResourceLocation("bound"), data[2], EnumHxCEnchantType.ALL, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Bound);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Bound.getName());
        }
        if (EnchantConfigHandler.isEnabled("DivineIntervention", "armor")) {
            data = EnchantConfigHandler.getData("DivineIntervention", "armor");
            DivineIntervention = new HxCEnchantment(data[0], new ResourceLocation("divineIntervention"), data[2], EnumHxCEnchantType.ARMOR_TORSO, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(DivineIntervention);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", DivineIntervention.getName());
        }
        if (EnchantConfigHandler.isEnabled("Examine", "weapon")) {
            data = EnchantConfigHandler.getData("Examine", "weapon");
            Examine = new HxCEnchantment(data[0], new ResourceLocation("examine"), data[2], EnumHxCEnchantType.SWORD, data[1], data[3], new Enchantment[]{SoulTear});
            MinecraftForge.EVENT_BUS.register(Examine);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Examine.getName());
        }
        if (EnchantConfigHandler.isEnabled("FlameTouch", "other")) {
            data = EnchantConfigHandler.getData("FlameTouch", "other");
            FlameTouch = new HxCEnchantment(data[0], new ResourceLocation("flameTouch"), data[2], EnumHxCEnchantType.DIGGER, data[1], data[3], new Enchantment[]{VoidTouch});
            MinecraftForge.EVENT_BUS.register(FlameTouch);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", FlameTouch.getName());
        }
        if (EnchantConfigHandler.isEnabled("Fly" , "armor")) {
            data = EnchantConfigHandler.getData("Fly" , "armor");
            Fly = new HxCEnchantment(data[0], new ResourceLocation("fly"), data[2], EnumHxCEnchantType.ARMOR_FEET, data[1], data[3], new Enchantment[]{LeadFooted, JumpBoost});
            MinecraftForge.EVENT_BUS.register(Fly);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Fly.getName());
        }
        if (EnchantConfigHandler.isEnabled("JumpBoost", "armor")) {
            data = EnchantConfigHandler.getData("JumpBoost", "armor");
            JumpBoost = new HxCEnchantment(data[0], new ResourceLocation("jumpBoost"), data[2], EnumHxCEnchantType.ARMOR_LEGS, data[1], data[3], new Enchantment[]{LeadFooted, Fly});
            MinecraftForge.EVENT_BUS.register(JumpBoost);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", JumpBoost.getName());
        }
        if (EnchantConfigHandler.isEnabled("LifeSteal", "weapon")) {
            data = EnchantConfigHandler.getData("LifeSteal", "weapon");
            LifeSteal = new HxCEnchantment(data[0], new ResourceLocation("lifeSteal"), data[2], EnumHxCEnchantType.SWORD, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(LifeSteal);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", LifeSteal.getName());
        }
        if (EnchantConfigHandler.isEnabled("LeadFooted", "armor")) {
            data = EnchantConfigHandler.getData("LeadFooted", "armor");
            LeadFooted = new HxCEnchantment(data[0], new ResourceLocation("leadFooted"), data[2], EnumHxCEnchantType.ARMOR_FEET, data[1], data[3], new Enchantment[]{Swiftness, Fly, JumpBoost, FeatherFall, Enchantment.featherFalling});
            MinecraftForge.EVENT_BUS.register(LeadFooted);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", LeadFooted.getName());
        }
        if (EnchantConfigHandler.isEnabled("Poison", "weapon")) {
            data = EnchantConfigHandler.getData("Poison", "weapon");
            Poison = new HxCEnchantment(data[0], new ResourceLocation("poison"), data[2], EnumHxCEnchantType.WEAPON, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Poison);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Poison.getName());
        }
        if (EnchantConfigHandler.isEnabled("Piercing", "weapon")) {
            data = EnchantConfigHandler.getData("Piercing", "weapon");
            Piercing = new HxCEnchantment(data[0], new ResourceLocation("piercing"), data[2], EnumHxCEnchantType.WEAPON, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Piercing);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Piercing.getName());
        }
        if (EnchantConfigHandler.isEnabled("Repair", "other")) {
            data = EnchantConfigHandler.getData("Repair", "other");
            Repair = new HxCEnchantment(data[0], new ResourceLocation("repair"), data[2], EnumHxCEnchantType.BREAKABLE, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Repair);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Repair.getName());
        }
        if (EnchantConfigHandler.isEnabled("Shroud" , "armor")){
            data = EnchantConfigHandler.getData("Shroud" , "armor");
            Shroud = new HxCEnchantment(data[0], new ResourceLocation("shroud"), data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Shroud);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Shroud.getName());
        }
        if (EnchantConfigHandler.isEnabled("SoulTear", "weapon")){
            data = EnchantConfigHandler.getData("SoulTear", "weapon");
            SoulTear = new HxCEnchantment(data[0], new ResourceLocation("soulTear"), data[2], EnumHxCEnchantType.SWORD, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(SoulTear);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", SoulTear.getName());
        }
        if (EnchantConfigHandler.isEnabled("SCurse", "weapon")){
            data = EnchantConfigHandler.getData("SCurse", "weapon");
            SCurse = new HxCEnchantment(data[0], new ResourceLocation("slayersCurse"), data[2], EnumHxCEnchantType.SWORD, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(SCurse);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", SCurse.getName());
        }
        if (EnchantConfigHandler.isEnabled("Stealth", "armor")){
            data = EnchantConfigHandler.getData("Stealth", "armor");
            Stealth = new HxCEnchantment(data[0], new ResourceLocation("stealth"), data[2], EnumHxCEnchantType.ARMOR_FEET, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Stealth);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Stealth.getName());
        }
        if (EnchantConfigHandler.isEnabled("Swiftness", "armor")){
            data = EnchantConfigHandler.getData("Swiftness", "armor");
            Swiftness = new HxCEnchantment(data[0], new ResourceLocation("swiftness"), data[2], EnumHxCEnchantType.ARMOR_LEGS, data[1], data[3], new Enchantment[]{LeadFooted});
            MinecraftForge.EVENT_BUS.register(Swiftness);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Swiftness.getName());
        }
        if (EnchantConfigHandler.isEnabled("Vampirism", "weapon")) {
            data = EnchantConfigHandler.getData("Vampirism", "weapon");
            Vampirism = new HxCEnchantment(data[0], new ResourceLocation("vampirism"), data[2], EnumHxCEnchantType.SWORD, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Vampirism);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Vampirism.getName());
        }
        if (EnchantConfigHandler.isEnabled("Vitality", "armor")) {
            data = EnchantConfigHandler.getData("Vitality", "armor");
            Vitality = new HxCEnchantment(data[0], new ResourceLocation("vitality"), data[2], EnumHxCEnchantType.ARMOR_TORSO, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Vitality);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Vitality.getName());
        }
        if (EnchantConfigHandler.isEnabled("Vorpal", "weapon")) {
            data = EnchantConfigHandler.getData("Vorpal", "weapon");
            Vorpal = new HxCEnchantment(data[0], new ResourceLocation("vorpal"), data[2], EnumHxCEnchantType.SWORD, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Vorpal);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Vorpal.getName());
        }
        if (EnchantConfigHandler.isEnabled("WitherProtection", "armor")) {
            data = EnchantConfigHandler.getData("WitherProtection", "armor");
            WitherProtection = new HxCEnchantment(data[0], new ResourceLocation("witherProt"), data[2], EnumHxCEnchantType.ARMOR_HEAD, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(WitherProtection);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", WitherProtection.getName());
        }
        if (EnchantConfigHandler.isEnabled("SpeedMine", "tool")) {
            data = EnchantConfigHandler.getData("SpeedMine", "tool");
            SpeedMine = new HxCEnchantment(data[0], new ResourceLocation("speedMine"), data[2], EnumHxCEnchantType.DIGGER, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(SpeedMine);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", SpeedMine.getName());
        }
        if (EnchantConfigHandler.isEnabled("VoidTouch", "tool")) {
            data = EnchantConfigHandler.getData("VoidTouch", "tool");
            VoidTouch = new HxCEnchantment(data[0], new ResourceLocation("voidTouch"), data[2], EnumHxCEnchantType.DIGGER, data[1], data[3], new Enchantment[]{Enchantment.fortune, Enchantment.silkTouch});
            MinecraftForge.EVENT_BUS.register(VoidTouch);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", VoidTouch.getName());
        }
        if (EnchantConfigHandler.isEnabled("EnchLeech", "weapon")) {
            data = EnchantConfigHandler.getData("EnchLeech", "weapon");
            EnchLeech = new HxCEnchantment(data[0], new ResourceLocation("enchLeech"), data[2], EnumHxCEnchantType.SWORD, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(EnchLeech);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", EnchLeech.getName());
        }
        if (EnchantConfigHandler.isEnabled("PipeMine", "tool")) {
            data = EnchantConfigHandler.getData("PipeMine", "tool");
            PipeMine = new HxCEnchantment(data[0], new ResourceLocation("pipeMine"), data[2], EnumHxCEnchantType.DIGGER, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(PipeMine);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", PipeMine.getName());
        }
        if (EnchantConfigHandler.isEnabled("FeatherFall", "armor")) {
            data = EnchantConfigHandler.getData("FeatherFall", "armor");
            FeatherFall = new HxCEnchantment(data[0], new ResourceLocation("featherFall"), data[2], EnumHxCEnchantType.ARMOR_FEET, data[1], data[3], new Enchantment[]{MeteorFall});
            MinecraftForge.EVENT_BUS.register(FeatherFall);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", FeatherFall.getName());
        }
        if (EnchantConfigHandler.isEnabled("MeteorFall", "armor")) {
            data = EnchantConfigHandler.getData("MeteorFall", "armor");
            MeteorFall = new HxCEnchantment(data[0], new ResourceLocation("meteorFall"), data[2], EnumHxCEnchantType.ARMOR_FEET, data[1], data[3], new Enchantment[]{FeatherFall, Enchantment.featherFalling, Fly});
            MinecraftForge.EVENT_BUS.register(MeteorFall);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", MeteorFall.getName());
        }
        if (EnchantConfigHandler.isEnabled("OverCharge", "weapon")) {
            data = EnchantConfigHandler.getData("OverCharge", "weapon");
            Overcharge = new HxCEnchantment(data[0], new ResourceLocation("overcharge"), data[2], EnumHxCEnchantType.SWORD, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Overcharge);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Overcharge.getName());
        }
        if (EnchantConfigHandler.isEnabled("ExplosiveDischarge", "armor")) {
            data = EnchantConfigHandler.getData("ExplosiveDischarge", "armor");
            ExplosiveDischarge = new HxCEnchantment(data[0], new ResourceLocation("explosiveDischarge"), data[2], EnumHxCEnchantType.ARMOR_TORSO, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(ExplosiveDischarge);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", ExplosiveDischarge.getName());
        }
        if (EnchantConfigHandler.isEnabled("GaiaAura", "armor")) {
            data = EnchantConfigHandler.getData("GaiaAura", "armor");
            GaiaAura = new HxCEnchantment(data[0], new ResourceLocation("gaiaAura"), data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{AuraToxic, AuraFiery, IcyAura, AuraDeadly});
            MinecraftForge.EVENT_BUS.register(GaiaAura);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", GaiaAura.getName());
        }
        if (EnchantConfigHandler.isEnabled("FlashStep", "armor")) {
            data = EnchantConfigHandler.getData("FlashStep", "armor");
            FlashStep = new HxCEnchantment(data[0], new ResourceLocation("flashStep"), data[2], EnumHxCEnchantType.ARMOR_FEET, data[1], data[3], new Enchantment[]{LeadFooted});
            MinecraftForge.EVENT_BUS.register(FlashStep);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", FlashStep.getName());
        }
        if (EnchantConfigHandler.isEnabled("FlamingArrow", "weapon")) {
            data = EnchantConfigHandler.getData("FlamingArrow", "weapon");
            FlamingArrow = new HxCEnchantment(data[0], new ResourceLocation("flamingArrow"), data[2], EnumHxCEnchantType.BOW, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(FlamingArrow);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", FlamingArrow.getName());
        }
        if (EnchantConfigHandler.isEnabled("HealingAura", "armor")) {
            data = EnchantConfigHandler.getData("HealingAura", "armor");
            HealingAura = new HxCEnchantment(data[0], new ResourceLocation("healingAura"), data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{AuraToxic, AuraDeadly, AuraFiery});
            MinecraftForge.EVENT_BUS.register(HealingAura);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", HealingAura.getName());
        }
        if (EnchantConfigHandler.isEnabled("Gluttony", "armor")) {
            data = EnchantConfigHandler.getData("Gluttony", "armor");
            Gluttony = new HxCEnchantment(data[0], new ResourceLocation("gluttony"), data[2], EnumHxCEnchantType.ARMOR_HEAD, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Gluttony);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Gluttony.getName());
        }
        if (EnchantConfigHandler.isEnabled("RepulsiveAura", "armor")) {
            data = EnchantConfigHandler.getData("RepulsiveAura", "armor");
            RepulsiveAura = new HxCEnchantment(data[0], new ResourceLocation("repulsiveAura"), data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{AuraMagnetic});
            MinecraftForge.EVENT_BUS.register(RepulsiveAura);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", RepulsiveAura.getName());
        }
        if (EnchantConfigHandler.isEnabled("AuraMagnetic", "armor")) {
            data = EnchantConfigHandler.getData("AuraMagnetic", "armor");
            AuraMagnetic = new HxCEnchantment(data[0], new ResourceLocation("auraMagnetic"), data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{RepulsiveAura});
            MinecraftForge.EVENT_BUS.register(AuraMagnetic);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", AuraMagnetic.getName());
        }
        if (EnchantConfigHandler.isEnabled("IcyAura", "armor")) {
            data = EnchantConfigHandler.getData("IcyAura", "armor");
            IcyAura = new HxCEnchantment(data[0], new ResourceLocation("icyAura"), data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{AuraFiery});
            MinecraftForge.EVENT_BUS.register(IcyAura);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", IcyAura.getName());
        }
        if (EnchantConfigHandler.isEnabled("LightningArrow", "weapon")) {
            data = EnchantConfigHandler.getData("LightningArrow", "weapon");
            LightningArrow = new HxCEnchantment(data[0], new ResourceLocation("lightningArrow"), data[2], EnumHxCEnchantType.BOW, data[1], data[3], new Enchantment[]{Zeus});
            MinecraftForge.EVENT_BUS.register(LightningArrow);
            Enchs++;
            FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", LightningArrow.getName());
        }
        if (Configurations.DebugMode)
            LogHelper.warn(Enchs + " enchantments have been registered.", Reference.MOD_NAME);
    }
}
