package HxCKDMS.XEnchants.hooks;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

import org.lwjgl.input.Keyboard;

import HxCKDMS.XEnchants.common.XEnchants;

public class ArmorEventHookContainer 
{
	// Booleans, my boys
	boolean isJumpBoost = false;
	boolean isHeavyFooted = false;
	boolean isRegen = false;
	boolean isSpeed = false;
	boolean isBound = false;

	// Itnegers, ya idiots
	int heavyFootedAmount;
	int jumpBoostAmount;
    int Fly;
	int regenAmount;
	int speedAmount;
	int boundAmount;

	// Misc.
	int healingTimer;
	boolean respawned;
	ItemStack inventory[];

    @Mod.EventHandler
	public void livingUpdateEvent(LivingEvent.LivingUpdateEvent event)
	{
        EntityPlayer player = (EntityPlayer) event.entityLiving;
        Boolean canFly = player.capabilities.isCreativeMode;
		if(event.entityLiving instanceof EntityPlayer)
		{
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
		}
        player.capabilities.allowFlying = canFly;
        if (!canFly) player.capabilities.isFlying = false;
	}

	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
		if(event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			inventory = new ItemStack[player.inventory.getSizeInventory()];;

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
            Boolean canFly = player.capabilities.allowFlying;
            if (canFly)
            {
                if (player.worldObj.isRemote && player.capabilities.isFlying) player.worldObj.spawnParticle("smoke", player.posX + Math.random() - 0.5d, player.posY - 1.62d, player.posZ + Math.random() - 0.5d, 0.0d, 0.0d, 0.0d);
            }

			if(isRegen)
			{
				int i = 20 * ((this.regenAmount - 1) * 3); 
				int j = 440 - i;
				int k = healingTimer % j;

				if(k == 0 && player.getHealth() < player.getMaxHealth())
				{
					player.heal(1);
				}
				if(healingTimer > 44000)
				{
					healingTimer = 0;
				}

				healingTimer++;
			}
			if(isSpeed)
			{
				if(!player.isSneaking() && player.onGround)
				{
					if(Keyboard.isKeyDown(87) || Keyboard.isKeyDown(65) || Keyboard.isKeyDown(83) || Keyboard.isKeyDown(68))
					{
						double amount;
						System.out.println("Woof");

						if(speedAmount == 1)
						{
							amount = 1.1;
						} else if(speedAmount == 2)
						{
							amount = 1.25;
						} else
						{
							amount = 1.45;
						}

						player.motionX *= amount;				
						player.motionZ *= amount;
					}
				}
			}


			inventory = new ItemStack[player.inventory.getSizeInventory()];

			if(respawned && inventory != null && player.getHealth() > 0)
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
            
            if(inventory != null)
            {
                for(int i = 0; i < inventory.length; i++)
                {
                    player.inventory.setInventorySlotContents(i, inventory[i]);
                }
            }
        }
    }


	@SubscribeEvent
	public void playerJumping(LivingJumpEvent event)
	{
		if(event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;

			if(isJumpBoost = true)
			{
				if(jumpBoostAmount == 1)
				{
					player.motionY = 0.4655786; // Instead of adding to the player's jump height, it has to receive a new value
				} else if(jumpBoostAmount == 2)
				{
					player.motionY = 0.5725786;
				}
			} else
			{
				return;
			}
		}
	}
}