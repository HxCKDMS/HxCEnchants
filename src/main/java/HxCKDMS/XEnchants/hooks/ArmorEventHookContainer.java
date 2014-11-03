package HxCKDMS.XEnchants.hooks;

import java.lang.reflect.Method;
import java.util.UUID;

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
    // Booleans, my boys
    boolean isAdrenalineBoost = false;
    boolean isHeavyFooted = false;
    boolean EnableFly;
//    boolean isBound = false;
    boolean HealthBuffApplied = false;
    boolean morphExists = false;
    boolean witherprot = false;

    //UUIDs for Attributes
    public static UUID HPUUID = UUID.fromString("fe15f490-62d7-11e4-b116-123b93f75cba");
    public static UUID SPEEDUUID = UUID.fromString("fe15f828-62d7-11e4-b116-123b93f75cba");

    // Integers, ya idiots
    int HeavyFootedLevel;
    int JumpBoostLevel;
    int AirStriderLevel;
    int VitalityLevel;
    int AdrenalineBoostLevel;
    int Repair;
    int WitherProt;
    int ShouldRepair = 1;
    int Fly;
    int RegenLevel;
    int SpeedLevel;
//    int BoundLevel;


    double SpeedBoost;
    double Vitality;

    // Misc.
    public Method getMorphEntity;
    public Method getEntityAbilities;

    public ArmorEventHookContainer() {
        try {
            getMorphEntity = Class.forName("morph.api.Api").getDeclaredMethod("getMorphEntity", String.class, boolean.class);
            getEntityAbilities = Class.forName("morph.common.ability.AbilityHandler").getDeclaredMethod("getEntityAbilities", Class.class);
            morphExists = true;
        } catch (Exception e) {
            System.out.println("Morph doesn't exist");
        }
    }

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
	{
		if(event.entityLiving instanceof EntityPlayerMP)
		{
            EntityPlayerMP player = (EntityPlayerMP) event.entityLiving;
			ItemStack stack_feet = player.inventory.armorItemInSlot(0);
			ItemStack stack_legs = player.inventory.armorItemInSlot(1);
			ItemStack stack_torso = player.inventory.armorItemInSlot(2);
			ItemStack stack_head = player.inventory.armorItemInSlot(3);
			ItemStack[] stack_total = player.inventory.armorInventory;

            IAttributeInstance ph = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth);
            IAttributeInstance ps = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed);

//            BoundLevel = EnchantmentHelper.getMaxEnchantmentLevel(XEnchants.Bound.effectId, stack_total);
            JumpBoostLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.JumpBoost.effectId, stack_legs);
            AdrenalineBoostLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.AdrenalineBoost.effectId, stack_head);
            VitalityLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.Vitality.effectId, stack_torso);
            Fly = EnchantmentHelper.getEnchantmentLevel(XEnchants.Fly.effectId, stack_feet);
            HeavyFootedLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.LeadFooted.effectId, stack_feet);
            AirStriderLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.AirStrider.effectId, stack_feet);
            WitherProt = EnchantmentHelper.getEnchantmentLevel(XEnchants.WitherProtection.effectId, stack_head);
            RegenLevel = EnchantmentHelper.getMaxEnchantmentLevel(XEnchants.ArmorRegen.effectId, stack_total);
            SpeedLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.Swiftness.effectId, stack_legs);
            Repair = EnchantmentHelper.getMaxEnchantmentLevel(XEnchants.Repair.effectId, stack_total);

            AttributeModifier SpeedBuff = new AttributeModifier(SPEEDUUID, "SpeedBuffedPants", SpeedBoost, 1);
            AttributeModifier HealthBuff = new AttributeModifier(HPUUID, "HealthBuffedChestplate", Vitality, 1);

            ph.removeModifier(HealthBuff);
            ps.removeModifier(SpeedBuff);
            ShouldRepair--;

            float FlightSpeedBuff = AirStriderLevel * 0.05F;
            player.capabilities.setFlySpeed(FlightSpeedBuff);

            if(AdrenalineBoostLevel > 0)
            {
                isAdrenalineBoost = true;
            }

            if(VitalityLevel > 0 && !HealthBuffApplied)
            {
                int level = EnchantmentHelper.getEnchantmentLevel(XEnchants.Vitality.effectId, stack_torso);
                Vitality = level * 0.5F;
                ph.applyModifier(HealthBuff);
            }

			if(HeavyFootedLevel > 0)
			{
				isHeavyFooted = true;
			}

            if (WitherProt > 0)
            {
                witherprot = true;
            }

			if(RegenLevel > 0)
			{
                int lf = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArmorRegen.effectId, stack_feet);
                int ll = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArmorRegen.effectId, stack_legs);
                int lt = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArmorRegen.effectId, stack_torso);
                int lh = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArmorRegen.effectId, stack_head);
                float HP = (lf + lt + ll + lh) * 2;
                if (player.getHealth() < player.getMaxHealth() && lf > 0 || ll > 0|| lt > 0|| lh > 0)
                {
                    player.heal(HP);
                }
			}
            if (ShouldRepair <= 0){
                ShouldRepair = 20;
                RepairItems(player);
            }

            if(SpeedLevel > 0 && !player.isSneaking() && player.onGround && !player.isRiding())
            {
                SpeedBoost = SpeedLevel * 0.3;
                ps.applyModifier(SpeedBuff);
            }

            player.capabilities.allowFlying = (Fly > 0);
            if (Fly <= 0 && !player.capabilities.isCreativeMode) player.capabilities.isFlying = false;
            if (player.worldObj.isRemote && player.capabilities.isFlying && Fly > 0 && !player.capabilities.isCreativeMode) player.worldObj.spawnParticle("smoke", player.posX + Math.random() - 0.5d, player.posY - 1.62d, player.posZ + Math.random() - 0.5d, 0.0d, 0.0d, 0.0d);
        }
	}
    public void RepairItems(EntityPlayerMP player){
        ItemStack abc = null;
        for(int j = 0; j < 36; j++){
            abc = player.inventory.getStackInSlot(j);
            if (abc != null && abc.isItemStackDamageable()){
                int a = EnchantmentHelper.getEnchantmentLevel(XEnchants.Repair.effectId, abc);
                int b = abc.getItemDamage() - a;
                if (abc.getItemDamage() > abc.getMaxDamage())
                {
                    abc.setItemDamage(b);
                }
            }
        }
    }

    @SubscribeEvent
    public void LivingHurtEvent(LivingHurtEvent event){
        if (event.entity instanceof EntityPlayerMP){
            EntityPlayerMP player = (EntityPlayerMP) event.entity;
            System.out.println(event.source.damageType);
            boolean allowABEffect = true;
            if (event.source.damageType.equalsIgnoreCase("wither") || event.source.damageType.equalsIgnoreCase("starve") ||event.source.damageType.equalsIgnoreCase("fall") ||event.source.damageType.equalsIgnoreCase("explosion.player") ||event.source.damageType.equalsIgnoreCase("explosion") || event.source.damageType.equalsIgnoreCase("inWall")){
                allowABEffect = false;
            }
            if(isAdrenalineBoost && allowABEffect){
                player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 5, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 5, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 5, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 5, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 5, AdrenalineBoostLevel));
            }if(witherprot && event.source.damageType.equalsIgnoreCase("wither")){
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