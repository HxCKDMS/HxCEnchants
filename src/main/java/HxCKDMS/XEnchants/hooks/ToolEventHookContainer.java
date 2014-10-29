package HxCKDMS.XEnchants.hooks;

import java.util.Random;

import HxCKDMS.XEnchants.common.XEnchants;
import com.sun.istack.internal.NotNull;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.world.BlockEvent;

public class ToolEventHookContainer 
{
	// Booleans, my boys
	boolean isVampire = false;
	boolean isFlameTouched = false;

	// Integers, ya idiot
	int vampireAmount;
	int flameTouchAmount;
	
	// Misc. variables
	int i;
	Random random = new Random();

	@SubscribeEvent
	public void LivingAttackEvent(LivingAttackEvent event)
	{
		if(event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			ItemStack stack = player.inventory.getCurrentItem();
			this.vampireAmount = EnchantmentHelper.getEnchantmentLevel(XEnchants.Vampirism.effectId, stack);

			if(vampireAmount > 0)
			{
				isVampire = true;
				
				if(random.nextInt(3) == 0)
				{
					if(((EntityPlayer) event.entityLiving).getFoodStats().getFoodLevel() <= 19)
					{
						((EntityPlayer) event.entityLiving).getFoodStats().setFoodLevel(((EntityPlayer) event.entityLiving).getFoodStats().getFoodLevel() + 1);
					} else
					{
						((EntityPlayer) event.entityLiving).heal(1);
					}
					
					i++;
				}
			}
		}
	}
	@SubscribeEvent
	public void onHarvestBlocks(BlockEvent.HarvestDropsEvent event)
	{
        if (!(event.harvester instanceof EntityPlayer)){
            return;
        }else{
		EntityPlayer player = event.harvester;
		Block block = event.block;
        ItemStack heldItem = player.getHeldItem();

		flameTouchAmount = EnchantmentHelper.getEnchantmentLevel(XEnchants.FlameTouch.effectId, heldItem);
		
		if(heldItem != null && flameTouchAmount > 0)
        {
            int Level = EnchantmentHelper.getEnchantmentLevel(XEnchants.FlameTouch.effectId, heldItem);
            ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(block));
            if (flameTouchAmount > 0){
                if(result != null)
                event.drops.add(result);
                event.drops.remove(event.block);
            }
        }



		/*if(isFlameTouched = true)
		{
			if(player.worldObj.rand.nextInt(2) == 0)
			{
				// So I was going to use FurnaceRecipes, but then I decided against it because this way gives me more flexibility
				if(block == Block.oreIron)
				{
					event.drops.add(new ItemStack(Item.ingotIron, 1));
					event.drops.remove(15);
				}
				if(block == Block.oreGold)
				{
					event.drops.add(new ItemStack(Item.ingotGold, 1));
					event.drops.remove(Block.oreGold);
				}
			}
		}*/
	}
    }
}