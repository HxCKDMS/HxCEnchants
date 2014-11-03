package HxCKDMS.XEnchants.hooks;

import java.util.UUID;

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

public class ArmorEventHookContainer
{
    //UUIDs for Attributes
    public static UUID HealthUUID = UUID.fromString("fe15f490-62d7-11e4-b116-123b93f75cba");
    public static UUID SpeedUUID = UUID.fromString("fe15f828-62d7-11e4-b116-123b93f75cba");

    // Integers
    int ShouldRepair = (Config.enchRepairRate * 20);
    
    int HeavyFootedLevel;
    int JumpBoostLevel;
    int AirStriderLevel;
    int VitalityLevel;
    int AdrenalineBoostLevel;
    int WitherProt;
    int FlyLevel;
    int RegenLevel;
    int SpeedLevel;
    int H;
    int C;
    int L;
    int B;
//    int BoundLevel;

    float FlightSpeedBuff;
    //doubles
    double SpeedBoost;
    double Vitality;

    //ItemStacks
    ItemStack Armour = null;
    ItemStack Inv = null;
    ItemStack ArmourHelm = null;
    ItemStack ArmourChest = null;
    ItemStack ArmourLegs = null;
    ItemStack ArmourBoots = null;

    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
	{
        ShouldRepair--;
		if(event.entityLiving instanceof EntityPlayerMP)
		{
            EntityPlayerMP player = (EntityPlayerMP) event.entityLiving;

            IAttributeInstance ph = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth);
            IAttributeInstance ps = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed);

            AttributeModifier HealthBuff = new AttributeModifier(HealthUUID, "HealthBuffedChestplate", Vitality, 1);
            AttributeModifier SpeedBuff = new AttributeModifier(SpeedUUID, "SpeedBuffedPants", SpeedBoost, 1);

            ph.removeModifier(HealthBuff);
            ps.removeModifier(SpeedBuff);

            ArmourHelm = player.inventory.armorItemInSlot(3);
            ArmourChest = player.inventory.armorItemInSlot(2);
            ArmourLegs = player.inventory.armorItemInSlot(1);
            ArmourBoots = player.inventory.armorItemInSlot(0);

            //Helmet Enchants
            AdrenalineBoostLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.AdrenalineBoost.effectId, ArmourHelm);
            WitherProt = EnchantmentHelper.getEnchantmentLevel(XEnchants.WitherProtection.effectId, ArmourHelm);
            H = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArmorRegen.effectId, ArmourHelm);

            //Chestplate Enchants
            VitalityLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.Vitality.effectId, ArmourChest);
            C = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArmorRegen.effectId, ArmourChest);

            //Legging Enchants
            SpeedLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.Swiftness.effectId, ArmourLegs);
            JumpBoostLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.JumpBoost.effectId, ArmourLegs);
            L = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArmorRegen.effectId, ArmourLegs);

            //Boot Enchants
            FlyLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.Fly.effectId, ArmourBoots);
            HeavyFootedLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.LeadFooted.effectId, ArmourBoots);
            AirStriderLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.AirStrider.effectId, ArmourBoots);
            B = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArmorRegen.effectId, ArmourBoots);

            //Other Enchants
//                BoundLevel = EnchantmentHelper.getMaxEnchantmentLevel(XEnchants.Bound.effectId, Armour);
            RegenLevel = (H + C + L + B);

            Vitality = VitalityLevel * 0.5F;
            SpeedBoost = SpeedLevel * 0.2;
            if(AirStriderLevel > 0){FlightSpeedBuff = AirStriderLevel * 0.05F;}

            //Indented for Beyond here stuff is actually done

                player.capabilities.setFlySpeed(FlightSpeedBuff);

                if (FlyLevel > 0)
                {
                    player.capabilities.allowFlying = true;
                }
                if(FlyLevel < 1 && !player.capabilities.isCreativeMode)
                {
                    player.capabilities.allowFlying = false;
                    player.capabilities.isFlying = false;
                }

                if (player.worldObj.isRemote && player.capabilities.isFlying && FlyLevel > 0 && !player.capabilities.isCreativeMode)
                {
                    player.worldObj.spawnParticle("smoke", player.posX + Math.random() - 0.5d, player.posY - 1.62d, player.posZ + Math.random() - 0.5d, 0.0d, 0.0d, 0.0d);
                }

                if(VitalityLevel > 0)
                {
                    ph.applyModifier(HealthBuff);
                }

                if (player.getHealth() < player.getMaxHealth() && RegenLevel > 0)
                {
                    player.heal(RegenLevel * 2);
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
        ItemStack Inv = null;
        ItemStack Armor = null;
        for(int j = 0; j < 36; j++){
            Inv = player.inventory.getStackInSlot(j);
            if (Inv != null && Inv.isItemStackDamageable()){
                int a = EnchantmentHelper.getEnchantmentLevel(XEnchants.Repair.effectId, Inv);
                int b = Inv.getItemDamage() - a;
                if (Inv.getItemDamage() > Inv.getMaxDamage())
                {
                    Inv.setItemDamage(b);
                }
            }
        }
        for(int j = 0; j < 36; j++){
            Armor = player.inventory.getStackInSlot(j);
            if (Armor != null && Armor.isItemStackDamageable()){
                int a = EnchantmentHelper.getEnchantmentLevel(XEnchants.Repair.effectId, Armor);
                int b = Armor.getItemDamage() - a;
                if (Armor.getItemDamage() > Armor.getMaxDamage())
                {
                    Armor.setItemDamage(b);
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
                player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 5, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 5, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 5, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 5, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 5, AdrenalineBoostLevel));
            }
            if(WitherProt > 0 && event.source.damageType.equalsIgnoreCase("wither"))
            {
                event.setCanceled(true);
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
