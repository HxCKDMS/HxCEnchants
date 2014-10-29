package HxCKDMS.XEnchants.hooks;

import java.lang.reflect.Method;

import HxCKDMS.XEnchants.common.XEnchants;
import HxCKDMS.XEnchants.common.Config;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

public class ArmorEventHookContainer 
{
    // Booleans, my boys
    boolean isAdrenalineBoost = false;
    boolean JumpBoost = false;
    boolean isHeavyFooted = false;
    boolean isBound = false;
    boolean HealthBuffApplied = false;
    boolean morphExists = false;

    // Itnegers, ya idiots
    int HeavyFootedLevel;
    int JumpBoostLevel;
    int AirStriderLevel;
    int VitalityLevel;
    int AdrenalineBoostLevel;
    int Fly;
    int RegenLevel;
    int SpeedLevel;
    int BoundLevel;


    double SpeedBoost;

    // Misc.
    boolean respawned;
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
	ItemStack inventory[];

    @SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
	{
		if(event.entityLiving instanceof EntityPlayer)
		{
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            Boolean canFly = player.capabilities.isCreativeMode;
			ItemStack stack_feet = player.inventory.armorItemInSlot(0);
			ItemStack stack_legs = player.inventory.armorItemInSlot(1);
			ItemStack stack_torso = player.inventory.armorItemInSlot(2);
			ItemStack stack_head = player.inventory.armorItemInSlot(3);
			ItemStack[] stack_total = player.inventory.armorInventory;

            IAttributeInstance ph = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth);
            IAttributeInstance ps = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed);

            BoundLevel = EnchantmentHelper.getMaxEnchantmentLevel(XEnchants.Bound.effectId, stack_total);
            JumpBoostLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.JumpBoost.effectId, stack_legs);
            AdrenalineBoostLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.AdrenalineBoost.effectId, stack_head);
            VitalityLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.Vitality.effectId, stack_torso);
            Fly = EnchantmentHelper.getEnchantmentLevel(XEnchants.Fly.effectId, stack_feet);
            HeavyFootedLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.LeadFooted.effectId, stack_feet);
            AirStriderLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.AirStrider.effectId, stack_feet);
            RegenLevel = EnchantmentHelper.getMaxEnchantmentLevel(XEnchants.ArmorRegen.effectId, stack_total);
            SpeedLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.Swiftness.effectId, stack_legs);

            if(JumpBoostLevel > 0)
            {
                JumpBoost = true;
            }

            if(AdrenalineBoostLevel > 0)
            {
                isAdrenalineBoost = true;
            }

            if(VitalityLevel > 0 && !HealthBuffApplied)
            {
                int level = EnchantmentHelper.getEnchantmentLevel(XEnchants.Vitality.effectId, stack_torso);
                double Vitality = level * 0.5F;
                AttributeModifier HealthBuff = new AttributeModifier("HealthBuffedChestplate", Vitality, 1);
                ph.applyModifier(HealthBuff);
            }

            if(Fly > 0)
            {
                canFly = true;
            }
            if(AirStriderLevel > 0)
            {
                float FlightSpeedBuff = AirStriderLevel * 0.2F;
                player.capabilities.setFlySpeed(FlightSpeedBuff);
            }

			if(HeavyFootedLevel > 0)
			{
				isHeavyFooted = true;
			}

			if(RegenLevel > 0)
			{
                int lf = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArmorRegen.effectId, stack_feet);
                int ll = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArmorRegen.effectId, stack_legs);
                int lt = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArmorRegen.effectId, stack_torso);
                int lh = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArmorRegen.effectId, stack_head);

                if (player.getHealth() < player.getMaxHealth() && lf > 0 || ll > 0|| lt > 0|| lh > 0)
                {
                    if (Config.DebugMode){System.out.println("Regen Triggered!");}
                    float HP = (lf + lt + ll + lh) * 2;
                    player.heal(HP);
                    player.addExhaustion(HP/2);
                }
			}

			if(SpeedLevel > 0)
			{
                if(!player.isSneaking() && player.onGround && !player.isRiding())
                {
                    SpeedBoost = SpeedLevel * 0.2;
                    AttributeModifier SpeedBuff = new AttributeModifier("SpeedBuffedPants", SpeedBoost, 1);
                    ps.removeModifier(SpeedBuff);
                    ps.applyModifier(SpeedBuff);
                }
			}

			if(BoundLevel > 0 && !(player.worldObj.getWorldInfo().isHardcoreModeEnabled()))
			{
				isBound = true;
			}
            player.capabilities.allowFlying = canFly;
            if (!canFly) player.capabilities.isFlying = false;
            if (player.worldObj.isRemote && player.capabilities.isFlying && !player.capabilities.isCreativeMode) player.worldObj.spawnParticle("smoke", player.posX + Math.random() - 0.5d, player.posY - 1.62d, player.posZ + Math.random() - 0.5d, 0.0d, 0.0d, 0.0d);
        }
	}

	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
		if(event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			inventory = new ItemStack[player.inventory.getSizeInventory()];

			for(int i = 0; i < player.inventory.getSizeInventory(); i++)
			{
				int k = BoundLevel;
				
				if(k == 4)
				{
					inventory[i] = player.inventory.getStackInSlot(i);
					continue;
				}
				if(k > 0 && player.worldObj.rand.nextInt(k + 1) > 0)
				{
					inventory[i] = player.inventory.getStackInSlot(i);
				}
			}
			for(int j = 0; j < inventory.length; j++)
			{
				if(inventory[j] != null)
				{
					player.inventory.setInventorySlotContents(j, null);
				}
			}
            inventory = new ItemStack[player.inventory.getSizeInventory()];

            if(respawned && player.getHealth() > 0)
            {
                respawned = false;

                for(int k = 0; k < inventory.length; k++)
                {
                    if(inventory[k] != null)
                    {
                        player.inventory.setInventorySlotContents(k, inventory[k]);
                    }
                }
            }
        }
    }
	@SubscribeEvent
	public void afterDeathUpdate(LivingSpawnEvent event)
    {
        if(event.entityLiving instanceof EntityPlayer)
        {
        	EntityPlayer player = (EntityPlayer) event.entityLiving;
        	inventory = new ItemStack[player.inventory.getSizeInventory()];
            respawned = true;

            for(int i = 0; i < inventory.length; i++)
            {
                player.inventory.setInventorySlotContents(i, inventory[i]);
            }
        }
    }


	@SubscribeEvent
	public void playerJumping(LivingJumpEvent event)
	{
		if(event.entityLiving instanceof EntityPlayer && JumpBoost)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
            double JumpBuff = player.motionY + 0.1 * JumpBoostLevel;
            player.motionY += JumpBuff;

            //Original modders code
            /*if(jumpBoostAmount == 1)
            {
                player.motionY = 0.4655786; // Instead of adding to the player's jump height, it has to receive a new value
            } else if(jumpBoostAmount == 2)
            {
                player.motionY = 0.5725786;
            }*/
		}
	}
}