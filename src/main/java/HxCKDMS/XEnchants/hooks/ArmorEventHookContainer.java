package HxCKDMS.XEnchants.hooks;

import HxCKDMS.XEnchants.Config;
import HxCKDMS.XEnchants.XEnchants;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.UUID;

public class ArmorEventHookContainer
{
    boolean CanFly;
    //UUIDs for Attributes
    public static UUID HealthUUID = UUID.fromString("fe15f490-62d7-11e4-b116-123b93f75cba");
    public static UUID SpeedUUID = UUID.fromString("fe15f828-62d7-11e4-b116-123b93f75cba");
//    public static UUID ShroudUUID = UUID.fromString("1e4a1a12-ab1e-4987-b527-e0adeefc904a");

    // Integers
    int ShouldRepair = (Config.enchRepairRate * 20);
    int CanRegen = (Config.enchRegenRate * 20);

    int JumpBoostLevel;
    int AirStriderLevel;
    int VitalityLevel;
    int AdrenalineBoostLevel;
    int BattleHealingLevel;
    int WitherProt;
    int FlyLevel;
    int RegenLevel;
    int SpeedLevel;
//    int LeadFootedLevel;
//    int StealthLevel;
//    int ShroudLevel;
//    int ShroudLevel1;
//    int ShroudLevel2;
//    int ShroudLevel3;
    int H;
    int C;
    int L;
    int B;
//    int BoundLevel;

    float FlightSpeedBuff;
    //doubles
    double SpeedBoost;
    double Vitality;
//    double Shroud;

    //ItemStacks
    ItemStack ArmourHelm = null;
    ItemStack ArmourChest = null;
    ItemStack ArmourLegs = null;
    ItemStack ArmourBoots = null;

    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
	{
        ShouldRepair--;
        CanRegen--;
		if(event.entityLiving instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) event.entityLiving;
            IAttributeInstance ph = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth);
            IAttributeInstance ps = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed);
//            IAttributeInstance kr = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.knockbackResistance);
//            IAttributeInstance fr = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.followRange);

            AttributeModifier HealthBuff = new AttributeModifier(HealthUUID, "HealthBuffedChestplate", Vitality, 1);
            AttributeModifier SpeedBuff = new AttributeModifier(SpeedUUID, "SpeedBuffedPants", SpeedBoost, 1);
//            AttributeModifier LeadFoot = new AttributeModifier(SpeedUUID, "LeadFoot", LeadFootedLevel*10, 1);
            //AttributeModifier ShroudBuff = new AttributeModifier(ShroudUUID, "ShroudBuff", Shroud, 1);

            ph.removeModifier(HealthBuff);
            ps.removeModifier(SpeedBuff);
//            kr.removeModifier(LeadFoot);
            //fr.removeModifier(ShroudBuff);

            ArmourHelm = player.getCurrentArmor(3);
            ArmourChest = player.getCurrentArmor(2);
            ArmourLegs = player.getCurrentArmor(1);
            ArmourBoots = player.getCurrentArmor(0);

            //Helmet Enchants
            AdrenalineBoostLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.AdrenalineBoost.effectId, ArmourHelm);
            WitherProt = EnchantmentHelper.getEnchantmentLevel(XEnchants.WitherProtection.effectId, ArmourHelm);
//            ShroudLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.Shroud.effectId, ArmourHelm);
            H = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArmorRegen.effectId, ArmourHelm);

            //Chestplate Enchants
            VitalityLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.Vitality.effectId, ArmourChest);
            BattleHealingLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.BattleHealing.effectId, ArmourChest);
//            ShroudLevel1 = EnchantmentHelper.getEnchantmentLevel(XEnchants.Shroud.effectId, ArmourChest);
            C = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArmorRegen.effectId, ArmourChest);

            //Legging Enchants
            SpeedLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.Swiftness.effectId, ArmourLegs);
            JumpBoostLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.JumpBoost.effectId, ArmourLegs);
//            ShroudLevel2 = EnchantmentHelper.getEnchantmentLevel(XEnchants.Shroud.effectId, ArmourLegs);
            L = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArmorRegen.effectId, ArmourLegs);

            //Boot Enchants
            FlyLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.Fly.effectId, ArmourBoots);
            AirStriderLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.AirStrider.effectId, ArmourBoots);
//            ShroudLevel3 = EnchantmentHelper.getEnchantmentLevel(XEnchants.Shroud.effectId, ArmourBoots);
//            LeadFootedLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.LeadFooted.effectId, ArmourBoots);
//            StealthLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.Stealth.effectId, ArmourBoots);
            B = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArmorRegen.effectId, ArmourBoots);

            //Other Enchants
//                BoundLevel = EnchantmentHelper.getMaxEnchantmentLevel(XEnchants.Bound.effectId, Armour);
            RegenLevel = (H + C + L + B);
            Vitality = VitalityLevel * 0.5F;
            SpeedBoost = SpeedLevel * 0.2;

            //Indented for Beyond here stuff is actually done
                if(Config.enchFlyEnable){
                    if (FlyLevel > 0 || player.capabilities.isCreativeMode) CanFly = true;
                    player.capabilities.allowFlying = CanFly;
                    if (!CanFly) player.capabilities.isFlying = false;
                    player.sendPlayerAbilities();
                }

                if (player.capabilities.isFlying && FlyLevel > 0 && !player.capabilities.isCreativeMode)
                {
                    player.worldObj.spawnParticle("smoke", player.posX + Math.random() - 0.5d, player.posY - 1.62d, player.posZ + Math.random() - 0.5d, 0.0d, 0.0d, 0.0d);
                }
/*
                if(AirStriderLevel > 0){FlightSpeedBuff = AirStriderLevel * 0.1F;}
                if (player.capabilities.isFlying && AirStriderLevel > 0){
                    if(player.motionX > 0)
                    {
                        player.motionX += FlightSpeedBuff;
                    }
                    if(player.motionY > 0)
                    {
                        player.motionY += FlightSpeedBuff;
                    }
                    if(player.motionZ > 0)
                    {
                        player.motionZ += FlightSpeedBuff;
                    }
                }
*/

                if(VitalityLevel > 0)
                {
                    ph.applyModifier(HealthBuff);
                }
                /*if(LeadFootedLevel > 0)
                {
                    kr.applyModifier(LeadFoot);
                }*/
                if (player.getHealth() < player.getMaxHealth() && RegenLevel > 0 && CanRegen <= 0)
                {
                    player.heal(RegenLevel * 2);
                    CanRegen = Config.enchRegenRate * 20;
                }

                if(SpeedLevel > 0 && !player.isSneaking() && !player.isRiding())
                {
                    ps.applyModifier(SpeedBuff);
                }

                if(ShouldRepair <= 0){
                    RepairItems(player);
                    ShouldRepair = (Config.enchRepairRate * 20);
                }
        }
	}
    public void RepairItems(EntityPlayerMP player){
        ItemStack Inv;
        ItemStack Armor;
        for(int j = 0; j < 36; j++){
            Inv = player.inventory.getStackInSlot(j);
            if (Inv != null && Inv.isItemStackDamageable()){
                int a = EnchantmentHelper.getEnchantmentLevel(XEnchants.Repair.effectId, Inv);
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
                int c = EnchantmentHelper.getEnchantmentLevel(XEnchants.Repair.effectId, Armor);
                int d = Armor.getItemDamage() - c;
                if (Armor.getItemDamage() < Armor.getMaxDamage())
                {
                    Armor.setItemDamage(d);
                }
            }
        }
    }

    @SubscribeEvent
    public void LivingHurtEvent(LivingHurtEvent event)
    {
        if (event.entity instanceof EntityPlayerMP)
        {
            EntityPlayerMP player = (EntityPlayerMP) event.entity;
            boolean allowABEffect = true;

            if (event.source.damageType.equalsIgnoreCase("wither") || event.source.damageType.equalsIgnoreCase("starve") ||event.source.damageType.equalsIgnoreCase("fall") ||event.source.damageType.equalsIgnoreCase("explosion.player") ||event.source.damageType.equalsIgnoreCase("explosion") || event.source.damageType.equalsIgnoreCase("inWall"))
            {
                allowABEffect = false;
            }
            if(AdrenalineBoostLevel > 0 && allowABEffect)
            {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 60, AdrenalineBoostLevel));
            }
            if(WitherProt > 0 && event.source.damageType.equalsIgnoreCase("wither"))
            {
                event.setCanceled(true);
            }
            if(BattleHealingLevel > 0 && event.source.damageType.equalsIgnoreCase("generic"))
            {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), BattleHealingLevel * 60, BattleHealingLevel));
            }
        }
    }

	@SubscribeEvent
	public void playerJumping(LivingJumpEvent event)
	{
		if(event.entityLiving instanceof EntityPlayer && JumpBoostLevel > 0)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
            double JumpBuff = player.motionY + 0.1 * JumpBoostLevel;
            player.motionY += JumpBuff;
		}
	}
}
