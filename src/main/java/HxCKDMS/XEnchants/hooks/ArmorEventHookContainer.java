package HxCKDMS.XEnchants.hooks;

import HxCKDMS.XEnchants.common.Config;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

import org.lwjgl.input.Keyboard;

import HxCKDMS.XEnchants.common.XEnchants;

import java.lang.reflect.Method;

public class ArmorEventHookContainer 
{
    public boolean morphExists = false;
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
	// Booleans, my boys
    boolean isAdrenalineBoost = false;
	boolean isJumpBoost = false;
    boolean isVitality = false;
	boolean isHeavyFooted = false;
	boolean isRegen = false;
	boolean isSpeed = false;
	boolean isBound = false;

	// Itnegers, ya idiots
	int heavyFootedAmount;
    int jumpBoostAmount;
    int vitalityAmount;
    int AdrenalineBoostAmount;
    int Fly;
	int regenAmount;
	int speedAmount;
	int boundAmount;

	// Misc.
	int healingTimer;
	boolean respawned;
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

            jumpBoostAmount = EnchantmentHelper.getEnchantmentLevel(XEnchants.JumpBoost.effectId, stack_legs);

            if(jumpBoostAmount > 0)
            {
                isJumpBoost = true;
            }

            AdrenalineBoostAmount = EnchantmentHelper.getEnchantmentLevel(XEnchants.AdrenalineBoost.effectId, stack_head);

            if(AdrenalineBoostAmount > 0)
            {
                isAdrenalineBoost = true;
            }

            vitalityAmount = EnchantmentHelper.getEnchantmentLevel(XEnchants.Vitality.effectId, stack_torso);

            if(vitalityAmount > 0)
            {
                isVitality = true;
            }

            Fly = EnchantmentHelper.getEnchantmentLevel(XEnchants.Fly.effectId, stack_feet);

            if(Fly > 0)
            {
                canFly = true;
            }

			heavyFootedAmount = EnchantmentHelper.getEnchantmentLevel(XEnchants.LeadFooted.effectId, stack_feet);

			if(heavyFootedAmount > 0)
			{
				isHeavyFooted = true;
			}

			regenAmount = EnchantmentHelper.getMaxEnchantmentLevel(XEnchants.ArmorRegen.effectId, stack_total);

			if(regenAmount > 0)
			{
				isRegen = true;
			}

			speedAmount = EnchantmentHelper.getEnchantmentLevel(XEnchants.Swiftness.effectId, stack_legs);

			if(speedAmount > 0)
			{
				isSpeed = true;
			}

			boundAmount = EnchantmentHelper.getMaxEnchantmentLevel(XEnchants.Bound.effectId, stack_total);

			if(boundAmount > 0 && !(player.worldObj.getWorldInfo().isHardcoreModeEnabled()))
			{
				isBound = true;
			}
            player.capabilities.allowFlying = canFly;
            if (!canFly) player.capabilities.isFlying = false;
		}
        this.applyEffects(event);
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
				int k = boundAmount;
				
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
		}
	}
	@SubscribeEvent
	public void applyEffects(LivingEvent.LivingUpdateEvent event)
	{
		if(event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;

            IAttributeInstance ph = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth);
            IAttributeInstance ps = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed);

            Boolean canFly = player.capabilities.allowFlying;
            if (canFly)
            {
                if (player.worldObj.isRemote && player.capabilities.isFlying && !player.capabilities.isCreativeMode) player.worldObj.spawnParticle("smoke", player.posX + Math.random() - 0.5d, player.posY - 1.62d, player.posZ + Math.random() - 0.5d, 0.0d, 0.0d, 0.0d);
            }

			if(isRegen)
			{
                ItemStack stack_feet = player.inventory.armorItemInSlot(0);
                ItemStack stack_legs = player.inventory.armorItemInSlot(1);
                ItemStack stack_torso = player.inventory.armorItemInSlot(2);
                ItemStack stack_head = player.inventory.armorItemInSlot(3);

                int lf = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArmorRegen.effectId, stack_feet);
                int ll = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArmorRegen.effectId, stack_legs);
                int lt = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArmorRegen.effectId, stack_torso);
                int lh = EnchantmentHelper.getEnchantmentLevel(XEnchants.ArmorRegen.effectId, stack_head);

                if (player.getHealth() < player.getMaxHealth() && lf > 0 || ll > 0|| lt > 0|| lh > 0){
                    if (Config.DebugMode){System.out.println("Regen Triggered!");}
                    float HP = (lf + lt + ll + lh) * 2;
                    player.heal(HP);
                    player.addExhaustion(HP/2);
                }
			}
            if(isVitality)
            {
                ItemStack stack_torso = player.inventory.armorItemInSlot(2);

                int level = EnchantmentHelper.getEnchantmentLevel(XEnchants.Vitality.effectId, stack_torso);
                double Vitality = level * 0.5F;
                AttributeModifier HealthBuff = new AttributeModifier("HealthBuffedChestplate", Vitality, 1);
                ph.removeModifier(HealthBuff);
                ph.applyModifier(HealthBuff);
            }
			if(isSpeed)
			{
				if(!player.isSneaking() && player.onGround && !player.isRiding())
				{
                    ItemStack stack_legs = player.inventory.armorItemInSlot(1);
                    int level = EnchantmentHelper.getEnchantmentLevel(XEnchants.Swiftness.effectId, stack_legs);
                    double Speed = level * 0.2;
                    AttributeModifier SpeedBuff = new AttributeModifier("SpeedBuffedPants", Speed, 1);
                    ps.removeModifier(SpeedBuff);
                    ps.applyModifier(SpeedBuff);
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
		if(event.entityLiving instanceof EntityPlayer && isJumpBoost)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
            ItemStack stack_legs = player.getCurrentArmor(2);
            int level = EnchantmentHelper.getEnchantmentLevel(XEnchants.JumpBoost.effectId, stack_legs);
            double JumpBuff = player.motionY + 0.1 * level;
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