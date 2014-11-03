package HxCKDMS.XEnchants.hooks;

import java.util.Random;

import HxCKDMS.XEnchants.XEnchants;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.world.BlockEvent;

public class ToolEventHookContainer 
{
	// Integers, ya idiot
	int VampireLevel;
	int AutoSmeltLevel;

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
			VampireLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.Vampirism.effectId, stack);

		}
	}
	@SubscribeEvent
	public void onHarvestBlocks(BlockEvent.HarvestDropsEvent event)
	{
        if (event.harvester != null){
		EntityPlayer player = event.harvester;
		Block block = event.block;
        ItemStack DroppedBlock = new ItemStack(block);
        ItemStack heldItem = player.getHeldItem();

		AutoSmeltLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.FlameTouch.effectId, heldItem);
		
		if(AutoSmeltLevel > 0)
        {
            ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(block));
            if(result != null)
            {
                event.drops.remove(DroppedBlock);
                event.drops.add(result);
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