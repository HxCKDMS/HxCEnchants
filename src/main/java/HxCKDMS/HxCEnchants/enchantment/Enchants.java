package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCEnchants.Configurations;
import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.HxCEnchants.EnchantConfigHandler;
import HxCKDMS.HxCEnchants.lib.Reference;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;

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
            AdrenalineBoost = new HxCEnchantment(data[0], "adrenalineBoost",data[2], EnumHxCEnchantType.ARMOR_HEAD, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(AdrenalineBoost);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", AdrenalineBoost.getName());
        }
        if (EnchantConfigHandler.isEnabled("AuraDark", "armor")) {
            data = EnchantConfigHandler.getData("AuraDark", "armor");
            AuraDark = new HxCEnchantment(data[0], "darkAura", data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(AuraDark);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", AuraDark.getName());
        }
        if (EnchantConfigHandler.isEnabled("AuraDeadly", "armor")) {
            data = EnchantConfigHandler.getData("AuraDeadly", "armor");
            AuraDeadly = new HxCEnchantment(data[0], "deadlyAura", data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{GaiaAura, HealingAura});
            MinecraftForge.EVENT_BUS.register(AuraDeadly);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", AuraDeadly.getName());
        }
        if (EnchantConfigHandler.isEnabled("AuraFiery", "armor")) {
            data = EnchantConfigHandler.getData("AuraFiery", "armor");
            AuraFiery = new HxCEnchantment(data[0], "flameAura", data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{HealingAura, GaiaAura, IcyAura});
            MinecraftForge.EVENT_BUS.register(AuraFiery);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", AuraFiery.getName());
        }
        if (EnchantConfigHandler.isEnabled("AuraThick", "armor")) {
            data = EnchantConfigHandler.getData("AuraThick", "armor");
            AuraThick = new HxCEnchantment(data[0], "thickAura", data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(AuraThick);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", AuraThick.getName());
        }
        if (EnchantConfigHandler.isEnabled("AuraToxic", "armor")) {
            data = EnchantConfigHandler.getData("AuraToxic", "armor");
            AuraToxic = new HxCEnchantment(data[0], "toxicAura", data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{GaiaAura, HealingAura});
            MinecraftForge.EVENT_BUS.register(AuraToxic);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", AuraToxic.getName());
        }
        if (EnchantConfigHandler.isEnabled("Regen", "armor")) {
            data = EnchantConfigHandler.getData("Regen", "armor");
            ArmorRegen = new HxCEnchantment(data[0], "regen", data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{BattleHealing});
            MinecraftForge.EVENT_BUS.register(ArmorRegen);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", ArmorRegen.getName());
        }
        if (EnchantConfigHandler.isEnabled("ArrowExplosive", "weapon")) {
            data = EnchantConfigHandler.getData("ArrowExplosive", "weapon");
            ArrowExplosive = new HxCEnchantment(data[0], "arrowExplosive", data[2], EnumHxCEnchantType.BOW, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(ArrowExplosive);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", ArrowExplosive.getName());
        }
        if (EnchantConfigHandler.isEnabled("Zeus", "weapon")) {
            data = EnchantConfigHandler.getData("Zeus", "weapon");
            Zeus = new HxCEnchantment(data[0], "arrowZeus", data[2], EnumHxCEnchantType.BOW, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Zeus);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Zeus.getName());
        }
        if (EnchantConfigHandler.isEnabled("ArrowSeeking", "weapon")) {
            data = EnchantConfigHandler.getData("ArrowSeeking", "weapon");
            ArrowSeeking = new HxCEnchantment(data[0], "arrowSeeking", data[2], EnumHxCEnchantType.BOW, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(ArrowSeeking);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", ArrowSeeking.getName());
        }
        if (EnchantConfigHandler.isEnabled("BattleHealing", "armor")) {
            data = EnchantConfigHandler.getData("BattleHealing", "armor");
            BattleHealing = new HxCEnchantment(data[0], "battleHeal", data[2], EnumHxCEnchantType.ARMOR_TORSO, data[1], data[3], new Enchantment[]{ArmorRegen});
            MinecraftForge.EVENT_BUS.register(BattleHealing);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", BattleHealing.getName());
        }
        if (EnchantConfigHandler.isEnabled("Bound", "other")) {
            data = EnchantConfigHandler.getData("Bound", "other");
            Bound = new HxCEnchantment(data[0], "bound", data[2], EnumHxCEnchantType.ALL, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Bound);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Bound.getName());
        }
        if (EnchantConfigHandler.isEnabled("DivineIntervention", "armor")) {
            data = EnchantConfigHandler.getData("DivineIntervention", "armor");
            DivineIntervention = new HxCEnchantment(data[0], "divineIntervention", data[2], EnumHxCEnchantType.ARMOR_TORSO, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(DivineIntervention);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", DivineIntervention.getName());
        }
        if (EnchantConfigHandler.isEnabled("Examine", "weapon")) {
            data = EnchantConfigHandler.getData("Examine", "weapon");
            Examine = new HxCEnchantment(data[0], "examine", data[2], EnumHxCEnchantType.SWORD, data[1], data[3], new Enchantment[]{SoulTear});
            MinecraftForge.EVENT_BUS.register(Examine);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Examine.getName());
        }
        if (EnchantConfigHandler.isEnabled("FlameTouch", "other")) {
            data = EnchantConfigHandler.getData("FlameTouch", "other");
            FlameTouch = new HxCEnchantment(data[0], "flameTouch", data[2], EnumHxCEnchantType.DIGGER, data[1], data[3], new Enchantment[]{VoidTouch});
            MinecraftForge.EVENT_BUS.register(FlameTouch);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", FlameTouch.getName());
        }
        if (EnchantConfigHandler.isEnabled("Fly" , "armor")) {
            data = EnchantConfigHandler.getData("Fly" , "armor");
            Fly = new HxCEnchantment(data[0], "fly", data[2], EnumHxCEnchantType.ARMOR_FEET, data[1], data[3], new Enchantment[]{LeadFooted, JumpBoost});
            MinecraftForge.EVENT_BUS.register(Fly);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Fly.getName());
        }
        if (EnchantConfigHandler.isEnabled("JumpBoost", "armor")) {
            data = EnchantConfigHandler.getData("JumpBoost", "armor");
            JumpBoost = new HxCEnchantment(data[0], "jumpBoost", data[2], EnumHxCEnchantType.ARMOR_LEGS, data[1], data[3], new Enchantment[]{LeadFooted, Fly});
            MinecraftForge.EVENT_BUS.register(JumpBoost);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", JumpBoost.getName());
        }
        if (EnchantConfigHandler.isEnabled("LifeSteal", "weapon")) {
            data = EnchantConfigHandler.getData("LifeSteal", "weapon");
            LifeSteal = new HxCEnchantment(data[0], "lifeSteal", data[2], EnumHxCEnchantType.SWORD, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(LifeSteal);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", LifeSteal.getName());
        }
        if (EnchantConfigHandler.isEnabled("LeadFooted", "armor")) {
            data = EnchantConfigHandler.getData("LeadFooted", "armor");
            LeadFooted = new HxCEnchantment(data[0], "leadFooted", data[2], EnumHxCEnchantType.ARMOR_FEET, data[1], data[3], new Enchantment[]{Swiftness, Fly, JumpBoost, FeatherFall, Enchantment.featherFalling});
            MinecraftForge.EVENT_BUS.register(LeadFooted);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", LeadFooted.getName());
        }
        if (EnchantConfigHandler.isEnabled("Poison", "weapon")) {
            data = EnchantConfigHandler.getData("Poison", "weapon");
            Poison = new HxCEnchantment(data[0], "poison", data[2], EnumHxCEnchantType.WEAPON, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Poison);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Poison.getName());
        }
        if (EnchantConfigHandler.isEnabled("Piercing", "weapon")) {
            data = EnchantConfigHandler.getData("Piercing", "weapon");
            Piercing = new HxCEnchantment(data[0], "piercing", data[2], EnumHxCEnchantType.WEAPON, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Piercing);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Piercing.getName());
        }
        if (EnchantConfigHandler.isEnabled("Repair", "other")) {
            data = EnchantConfigHandler.getData("Repair", "other");
            Repair = new HxCEnchantment(data[0], "repair", data[2], EnumHxCEnchantType.BREAKABLE, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Repair);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Repair.getName());
        }
        if (EnchantConfigHandler.isEnabled("Shroud" , "armor")){
            data = EnchantConfigHandler.getData("Shroud" , "armor");
            Shroud = new HxCEnchantment(data[0], "shroud", data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Shroud);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Shroud.getName());
        }
        if (EnchantConfigHandler.isEnabled("SoulTear", "weapon")){
            data = EnchantConfigHandler.getData("SoulTear", "weapon");
            SoulTear = new HxCEnchantment(data[0], "soulTear", data[2], EnumHxCEnchantType.SWORD, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(SoulTear);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", SoulTear.getName());
        }
        if (EnchantConfigHandler.isEnabled("SCurse", "weapon")){
            data = EnchantConfigHandler.getData("SCurse", "weapon");
            SCurse = new HxCEnchantment(data[0], "slayersCurse", data[2], EnumHxCEnchantType.SWORD, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(SCurse);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", SCurse.getName());
        }
        if (EnchantConfigHandler.isEnabled("Stealth", "armor")){
            data = EnchantConfigHandler.getData("Stealth", "armor");
            Stealth = new HxCEnchantment(data[0], "stealth", data[2], EnumHxCEnchantType.ARMOR_FEET, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Stealth);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Stealth.getName());
        }
        if (EnchantConfigHandler.isEnabled("Swiftness", "armor")){
            data = EnchantConfigHandler.getData("Swiftness", "armor");
            Swiftness = new HxCEnchantment(data[0], "swiftness", data[2], EnumHxCEnchantType.ARMOR_LEGS, data[1], data[3], new Enchantment[]{LeadFooted});
            MinecraftForge.EVENT_BUS.register(Swiftness);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Swiftness.getName());
        }
        if (EnchantConfigHandler.isEnabled("Vampirism", "weapon")) {
            data = EnchantConfigHandler.getData("Vampirism", "weapon");
            Vampirism = new HxCEnchantment(data[0], "vampirism", data[2], EnumHxCEnchantType.SWORD, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Vampirism);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Vampirism.getName());
        }
        if (EnchantConfigHandler.isEnabled("Vitality", "armor")) {
            data = EnchantConfigHandler.getData("Vitality", "armor");
            Vitality = new HxCEnchantment(data[0], "vitality", data[2], EnumHxCEnchantType.ARMOR_TORSO, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Vitality);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Vitality.getName());
        }
        if (EnchantConfigHandler.isEnabled("Vorpal", "weapon")) {
            data = EnchantConfigHandler.getData("Vorpal", "weapon");
            Vorpal = new HxCEnchantment(data[0], "vorpal", data[2], EnumHxCEnchantType.SWORD, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Vorpal);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Vorpal.getName());
        }
        if (EnchantConfigHandler.isEnabled("WitherProtection", "armor")) {
            data = EnchantConfigHandler.getData("WitherProtection", "armor");
            WitherProtection = new HxCEnchantment(data[0], "witherProt", data[2], EnumHxCEnchantType.ARMOR_HEAD, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(WitherProtection);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", WitherProtection.getName());
        }
        if (EnchantConfigHandler.isEnabled("SpeedMine", "tool")) {
            data = EnchantConfigHandler.getData("SpeedMine", "tool");
            SpeedMine = new HxCEnchantment(data[0], "speedMine", data[2], EnumHxCEnchantType.DIGGER, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(SpeedMine);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", SpeedMine.getName());
        }
        if (EnchantConfigHandler.isEnabled("VoidTouch", "tool")) {
            data = EnchantConfigHandler.getData("VoidTouch", "tool");
            VoidTouch = new HxCEnchantment(data[0], "voidTouch", data[2], EnumHxCEnchantType.DIGGER, data[1], data[3], new Enchantment[]{Enchantment.fortune, Enchantment.silkTouch});
            MinecraftForge.EVENT_BUS.register(VoidTouch);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", VoidTouch.getName());
        }
        if (EnchantConfigHandler.isEnabled("EnchLeech", "weapon")) {
            data = EnchantConfigHandler.getData("EnchLeech", "weapon");
            EnchLeech = new HxCEnchantment(data[0], "enchLeech", data[2], EnumHxCEnchantType.SWORD, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(EnchLeech);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", EnchLeech.getName());
        }
        if (EnchantConfigHandler.isEnabled("PipeMine", "tool")) {
            data = EnchantConfigHandler.getData("PipeMine", "tool");
            PipeMine = new HxCEnchantment(data[0], "pipeMine", data[2], EnumHxCEnchantType.DIGGER, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(PipeMine);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", PipeMine.getName());
        }
        if (EnchantConfigHandler.isEnabled("FeatherFall", "armor")) {
            data = EnchantConfigHandler.getData("FeatherFall", "armor");
            FeatherFall = new HxCEnchantment(data[0], "featherFall", data[2], EnumHxCEnchantType.ARMOR_FEET, data[1], data[3], new Enchantment[]{MeteorFall});
            MinecraftForge.EVENT_BUS.register(FeatherFall);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", FeatherFall.getName());
        }
        if (EnchantConfigHandler.isEnabled("MeteorFall", "armor")) {
            data = EnchantConfigHandler.getData("MeteorFall", "armor");
            MeteorFall = new HxCEnchantment(data[0], "meteorFall", data[2], EnumHxCEnchantType.ARMOR_FEET, data[1], data[3], new Enchantment[]{FeatherFall, Enchantment.featherFalling, Fly});
            MinecraftForge.EVENT_BUS.register(MeteorFall);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", MeteorFall.getName());
        }
        if (EnchantConfigHandler.isEnabled("OverCharge", "weapon")) {
            data = EnchantConfigHandler.getData("OverCharge", "weapon");
            Overcharge = new HxCEnchantment(data[0], "overcharge", data[2], EnumHxCEnchantType.SWORD, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Overcharge);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Overcharge.getName());
        }
        if (EnchantConfigHandler.isEnabled("ExplosiveDischarge", "armor")) {
            data = EnchantConfigHandler.getData("ExplosiveDischarge", "armor");
            ExplosiveDischarge = new HxCEnchantment(data[0], "explosiveDischarge", data[2], EnumHxCEnchantType.ARMOR_TORSO, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(ExplosiveDischarge);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", ExplosiveDischarge.getName());
        }
        if (EnchantConfigHandler.isEnabled("GaiaAura", "armor")) {
            data = EnchantConfigHandler.getData("GaiaAura", "armor");
            GaiaAura = new HxCEnchantment(data[0], "gaiaAura", data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{AuraToxic, AuraFiery, IcyAura, AuraDeadly});
            MinecraftForge.EVENT_BUS.register(GaiaAura);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", GaiaAura.getName());
        }
        if (EnchantConfigHandler.isEnabled("FlashStep", "armor")) {
            data = EnchantConfigHandler.getData("FlashStep", "armor");
            FlashStep = new HxCEnchantment(data[0], "flashStep", data[2], EnumHxCEnchantType.ARMOR_FEET, data[1], data[3], new Enchantment[]{LeadFooted});
            MinecraftForge.EVENT_BUS.register(FlashStep);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", FlashStep.getName());
        }
        if (EnchantConfigHandler.isEnabled("FlamingArrow", "weapon")) {
            data = EnchantConfigHandler.getData("FlamingArrow", "weapon");
            FlamingArrow = new HxCEnchantment(data[0], "flamingArrow", data[2], EnumHxCEnchantType.BOW, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(FlamingArrow);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", FlamingArrow.getName());
        }
        if (EnchantConfigHandler.isEnabled("HealingAura", "armor")) {
            data = EnchantConfigHandler.getData("HealingAura", "armor");
            HealingAura = new HxCEnchantment(data[0], "healingAura", data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{AuraToxic, AuraDeadly, AuraFiery});
            MinecraftForge.EVENT_BUS.register(HealingAura);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", HealingAura.getName());
        }
        if (EnchantConfigHandler.isEnabled("Gluttony", "armor")) {
            data = EnchantConfigHandler.getData("Gluttony", "armor");
            Gluttony = new HxCEnchantment(data[0], "gluttony", data[2], EnumHxCEnchantType.ARMOR_HEAD, data[1], data[3], new Enchantment[]{});
            MinecraftForge.EVENT_BUS.register(Gluttony);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", Gluttony.getName());
        }
        if (EnchantConfigHandler.isEnabled("RepulsiveAura", "armor")) {
            data = EnchantConfigHandler.getData("RepulsiveAura", "armor");
            RepulsiveAura = new HxCEnchantment(data[0], "repulsiveAura", data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{AuraMagnetic});
            MinecraftForge.EVENT_BUS.register(RepulsiveAura);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", RepulsiveAura.getName());
        }
        if (EnchantConfigHandler.isEnabled("AuraMagnetic", "armor")) {
            data = EnchantConfigHandler.getData("AuraMagnetic", "armor");
            AuraMagnetic = new HxCEnchantment(data[0], "auraMagnetic", data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{RepulsiveAura});
            MinecraftForge.EVENT_BUS.register(AuraMagnetic);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", AuraMagnetic.getName());
        }
        if (EnchantConfigHandler.isEnabled("IcyAura", "armor")) {
            data = EnchantConfigHandler.getData("IcyAura", "armor");
            IcyAura = new HxCEnchantment(data[0], "icyAura", data[2], EnumHxCEnchantType.ARMOR, data[1], data[3], new Enchantment[]{AuraFiery});
            MinecraftForge.EVENT_BUS.register(IcyAura);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", IcyAura.getName());
        }
        if (EnchantConfigHandler.isEnabled("LightningArrow", "weapon")) {
            data = EnchantConfigHandler.getData("LightningArrow", "weapon");
            LightningArrow = new HxCEnchantment(data[0], "lightningArrow", data[2], EnumHxCEnchantType.BOW, data[1], data[3], new Enchantment[]{Zeus});
            MinecraftForge.EVENT_BUS.register(LightningArrow);
            Enchs++;
            if (Configurations.blacklistEnchantsFromEnchantingPlus2)
                FMLInterModComms.sendMessage("eplus", "blacklist-enchantment", LightningArrow.getName());
        }
        if (HxCKDMS.HxCCore.Configs.Configurations.DebugMode)
            LogHelper.warn(Enchs + " enchantments have been registered.", Reference.MOD_NAME);
    }
}
