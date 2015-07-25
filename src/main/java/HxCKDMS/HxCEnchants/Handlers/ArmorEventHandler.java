package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCCore.Handlers.NBTFileIO;
import HxCKDMS.HxCCore.HxCCore;
import HxCKDMS.HxCCore.api.Utils.AABBUtils;
import HxCKDMS.HxCCore.api.Utils.Teleporter;
import HxCKDMS.HxCEnchants.Config;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("all")
public class ArmorEventHandler {
    //UUIDs for Attributes
    public static UUID HealthUUID = UUID.fromString("fe15f490-62d7-11e4-b116-123b93f75cba"),
            SpeedUUID = UUID.fromString("fe15f828-62d7-11e4-b116-123b93f75cba"),
            StealthUUID = UUID.fromString("1e4a1a12-ab1e-4987-b527-e0adeefc904a");

    private int ShouldRepair = 60, CanRegen = 60;

    @SubscribeEvent
    public void playerTickEvent(TickEvent.PlayerTickEvent event) {
        double SpeedBoost, Vitality;
        int VitalityLevel, FlyLevel, RegenLevel, SpeedLevel, StealthLevel, H = 0, C = 0, L = 0, B = 0;
        EntityPlayer player = event.player;
        ShouldRepair--;
        CanRegen--;

        String UUID = player.getUniqueID().toString();
        File CustomPlayerData = new File(HxCCore.HxCCoreDir, "HxC-" + UUID + ".dat");


        ItemStack ArmourHelm = player.inventory.armorItemInSlot(3),
                ArmourChest = player.inventory.armorItemInSlot(2),
                ArmourLegs = player.inventory.armorItemInSlot(1),
                ArmourBoots = player.inventory.armorItemInSlot(0);

        //Chestplate Enchants
        if (Config.enchVitalityEnable && ArmourChest != null) {
            IAttributeInstance ph = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth);
            VitalityLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.Vitality.effectId, ArmourChest);
            Vitality = VitalityLevel * 0.5F;
            AttributeModifier HealthBuff = new AttributeModifier(HealthUUID, "HealthBuffedChestplate", Vitality, 1);
            if (!ph.func_111122_c().contains(HealthBuff) && VitalityLevel != 0)
                ph.applyModifier(HealthBuff);
            if (ph.func_111122_c().contains(HealthBuff) && VitalityLevel == 0)
                ph.removeModifier(HealthBuff);
        }

        //Legging Enchants
        if (Config.enchSwiftnessEnable && ArmourLegs != null) {
            IAttributeInstance ps = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed);
            SpeedLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.Swiftness.effectId, ArmourLegs);
            SpeedBoost = SpeedLevel * 0.2;
            AttributeModifier SpeedBuff = new AttributeModifier(SpeedUUID, "SpeedBuffedPants", SpeedBoost, 1);
            if (!ps.func_111122_c().contains(SpeedBuff) && SpeedLevel != 0)
                ps.applyModifier(SpeedBuff);
            if (ps.func_111122_c().contains(SpeedBuff) && SpeedLevel == 0)
                ps.removeModifier(SpeedBuff);
        }


        //Boot Enchants
        if (Config.enchFlyEnable && ArmourBoots != null) {
            FlyLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.Fly.effectId, ArmourBoots);
            boolean flyhbt = NBTFileIO.getBoolean(CustomPlayerData, "EFlyHasChanged");
            if (FlyLevel > 0 && !player.capabilities.allowFlying) {
                player.capabilities.allowFlying = true;
                player.sendPlayerAbilities();
                NBTFileIO.setBoolean(CustomPlayerData, "EFlyHasChanged", true);
            }
            if (FlyLevel < 1 && flyhbt) {
                player.capabilities.allowFlying = false;
                player.capabilities.isFlying = false;
                player.sendPlayerAbilities();
                NBTFileIO.setBoolean(CustomPlayerData, "EFlyHasChanged", false);
            }
            if (player.capabilities.isFlying && FlyLevel > 0 && !player.capabilities.isCreativeMode)
                player.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, true, player.posX + Math.random() - 0.5d,
                        player.posY - 1.62d, player.posZ + Math.random() - 0.5d, 0.0d, 0.0d, 0.0d);

        }

        if (Config.enchStealthEnable && ArmourBoots != null) {
            StealthLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.Stealth.effectId, ArmourBoots);

            Stealth(player, StealthLevel);
        }


        if (Config.enchRepairEnable && ShouldRepair <= 0) {
            RepairItems(player);
            ShouldRepair = (Config.enchRepairVals[5] * 20);
        }

        if (Config.enchRegenEnable && CanRegen <= 0){
            CanRegen = Config.enchRegenVals[4] * 20;
            RegenLevel = 0;
            RegenLevel += EnchantmentHelper.getEnchantmentLevel(Enchants.ArmorRegen.effectId, ArmourHelm);
            RegenLevel += EnchantmentHelper.getEnchantmentLevel(Enchants.ArmorRegen.effectId, ArmourBoots);
            RegenLevel += EnchantmentHelper.getEnchantmentLevel(Enchants.ArmorRegen.effectId, ArmourChest);
            RegenLevel += EnchantmentHelper.getEnchantmentLevel(Enchants.ArmorRegen.effectId, ArmourLegs);
            if (player.getHealth() < player.getMaxHealth() && RegenLevel > 0)
                player.heal(RegenLevel/2);
        }
	}

    public void RepairItems(EntityPlayer player){
        ItemStack Inv;
        ItemStack Armor;
        for(int j = 0; j < 36; j++){
            Inv = player.inventory.getStackInSlot(j);
            if (Inv != null && Inv.isItemStackDamageable()){
                int a = EnchantmentHelper.getEnchantmentLevel(Enchants.Repair.effectId, Inv);
                int b = Inv.getItemDamage() - a;
                if (Inv.getItemDamage() < Inv.getMaxDamage())
                {
                    Inv.setItemDamage(b);
                }
            }
        }
        for(int j = 0; j < 4; j++){
            Armor = player.getCurrentArmor(j);
            if (Armor != null && Armor.isItemStackDamageable()){
                int c = EnchantmentHelper.getEnchantmentLevel(Enchants.Repair.effectId, Armor);
                int d = Armor.getItemDamage() - c;
                if (Armor.getItemDamage() < Armor.getMaxDamage())
                {
                    Armor.setItemDamage(d);
                }
            }
        }
    }

    @SubscribeEvent
    public void livingHurtEvent(LivingHurtEvent event) {
        if (event.entity instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP)event.entityLiving;
            boolean allowABEffect = true;
            int WitherProt = EnchantmentHelper.getEnchantmentLevel(Enchants.WitherProtection.effectId, player.inventory.armorItemInSlot(3)),
                BattleHealingLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.BattleHealing.effectId, player.inventory.armorItemInSlot(2)),
                AdrenalineBoostLevel =  EnchantmentHelper.getEnchantmentLevel(Enchants.AdrenalineBoost.effectId, player.inventory.armorItemInSlot(3));
            if (event.source.damageType.equalsIgnoreCase("wither") || event.source.damageType.equalsIgnoreCase("starve") ||event.source.damageType.equalsIgnoreCase("fall") ||event.source.damageType.equalsIgnoreCase("explosion.player") ||event.source.damageType.equalsIgnoreCase("explosion") || event.source.damageType.equalsIgnoreCase("inWall"))
                allowABEffect = false;
            if(WitherProt > 0 && event.source.damageType.equalsIgnoreCase("wither"))
                event.setCanceled(true);
            if(BattleHealingLevel > 0 && event.source.damageType.equalsIgnoreCase("generic"))
                player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), BattleHealingLevel * 60, BattleHealingLevel));

            if(AdrenalineBoostLevel > 0 && allowABEffect) {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 60, AdrenalineBoostLevel));
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void Stealth (EntityPlayer player, int StealthLevel) {
        int px = Math.round((float)player.posX); int py = Math.round((float)player.posY); int pz = Math.round((float) player.posZ);
        List list  = player.worldObj.getEntitiesWithinAABB(EntityMob.class, AABBUtils.getAreaBoundingBox(px, py, pz, 24));
        for (EntityMob entity : (List<EntityMob>) list) {
            IAttributeInstance fr = entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.followRange);
            AttributeModifier StealthBuff = new AttributeModifier(StealthUUID, "StealthDeBuff", (StealthLevel*-15), 1);
            fr.removeModifier(StealthBuff);
            fr.applyModifier(StealthBuff);
        }
    }

	@SubscribeEvent
	public void livingJumpEvent(LivingJumpEvent event) {
		if(event.entityLiving instanceof EntityPlayer && EnchantmentHelper.getEnchantmentLevel(Enchants.JumpBoost.effectId, ((EntityPlayer) event.entityLiving).inventory.armorItemInSlot(1)) > 0) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
            double JumpBuff = player.motionY + 0.1 * EnchantmentHelper.getEnchantmentLevel(Enchants.JumpBoost.effectId, ((EntityPlayer) event.entityLiving).inventory.armorItemInSlot(1));
            player.motionY += JumpBuff;
		}
	}

    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void LivingHurtEvent(LivingHurtEvent event){
        Entity hurtEntity = event.entity;
        if (hurtEntity instanceof EntityPlayerMP){
            EntityPlayerMP player = (EntityPlayerMP) hurtEntity;
            ItemStack ArmourChest = player.inventory.armorItemInSlot(2);
            int DivineInterventionLevel = 0;
            if (Config.enchDivineInterventionEnable) DivineInterventionLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.DivineIntervention.effectId, ArmourChest);
            if (DivineInterventionLevel > 0){
                if (player.getHealth() - event.ammount <= 1) {
                    player.heal(5);
                    BlockPos pos;
                        if (player.getBedLocation(0) != null) pos = player.getBedLocation(0);
                        else pos = HxCCore.server.worldServerForDimension(0).getSpawnPoint();

                        if (player.dimension != 0)Teleporter.transferPlayerToDimension(player, 0, pos);
                        else player.playerNetServerHandler.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), 90, 0);
                    Map<Integer, Integer> enchs = EnchantmentHelper.getEnchantments(ArmourChest);
                    enchs.remove(Config.enchDivineInterventionVals[0]);
                    if (DivineInterventionLevel > 1) enchs.put(Config.enchDivineInterventionVals[0], DivineInterventionLevel - 1);
                    EnchantmentHelper.setEnchantments(enchs, ArmourChest);
                }
            }
        }
    }
}
