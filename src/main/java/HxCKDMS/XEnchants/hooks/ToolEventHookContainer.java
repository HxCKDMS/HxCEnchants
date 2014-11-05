package HxCKDMS.XEnchants.hooks;

import java.util.Random;

import HxCKDMS.XEnchants.XEnchants;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.BlockEvent;

public class ToolEventHookContainer 
{
	// Integers, ya idiot
	int VampireLevel;
	int AutoSmeltLevel;
    int LifeStealLevel;
    int VBRV = 0;

	// Misc. variables
    @SubscribeEvent
    public void LivingAttackEvent(LivingAttackEvent event){
        EntityLivingBase entity = event.entityLiving;
        Entity ent = event.source.getSourceOfDamage();
        if (ent instanceof EntityPlayerMP){
            EntityPlayerMP Attacker = (EntityPlayerMP) ent;
            EntityLiving Victim = (EntityLiving) entity;
            ItemStack item = Attacker.getHeldItem();
            LifeStealLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.LifeSteal.effectId, item);
            if (LifeStealLevel > 0){
                double PH = Victim.prevHealth;
                double CH = Victim.getHealth();
                float RH = (float)CH - (float)PH;
                Attacker.heal(RH * LifeStealLevel);
            }
        }
    }

	@SubscribeEvent
	public void LivingDeathEvent(LivingDeathEvent event)
	{
        EntityLivingBase entity = event.entityLiving;
        Entity ent = event.source.getSourceOfDamage();
        if (ent instanceof EntityPlayerMP){
            EntityLiving Victim = (EntityLiving) entity;
            EntityPlayerMP Attacker = (EntityPlayerMP) ent;
            ItemStack item = Attacker.getHeldItem();
            VampireLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.Vampirism.effectId, item);
            if (VampireLevel > 0){
                if (event.entityLiving instanceof EntityAnimal){
                    VBRV = 3;
                }else if (event.entityLiving instanceof EntityPlayerMP){
                    VBRV = 15;
                    Attacker.addChatMessage(new ChatComponentText("\u00A74You have just tasted victorious blood"));
                }else if (event.entityLiving instanceof EntityVillager){
                    VBRV = 12;
                    Attacker.addChatMessage(new ChatComponentText("\u00A74You have just tasted some rich blood"));
                }else if (event.entityLiving instanceof EntityZombie || event.entityLiving instanceof EntitySkeleton){
                    VBRV = -1;
                    Attacker.addChatMessage(new ChatComponentText("\u00A74You have just tasted some horrible blood"));
                }else if (event.entityLiving instanceof EntitySlime){
                    VBRV = 2;
                }else if (event.entityLiving instanceof EntityEnderman){
                    VBRV = 4;
                }else if (event.entityLiving instanceof EntityMob){
                    VBRV = 5;
                }else{
                    VBRV = 3;
                }
                int curFood = Attacker.getFoodStats().getFoodLevel();
                int newFud = (VBRV * VampireLevel) + curFood;
                Attacker.getFoodStats().setFoodLevel(newFud);
            }
        }
	}
	@SubscribeEvent
	public void onHarvestBlocks(BlockEvent.HarvestDropsEvent event)
	{
        if (event.harvester != null){
		EntityPlayer player = event.harvester;
		Block block = event.block;
        ItemStack itemStackBlock = new ItemStack(Item.getItemFromBlock(block), 1);
        ItemStack heldItem = player.getHeldItem();
        ItemStack result;

		AutoSmeltLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.FlameTouch.effectId, heldItem);

            if(AutoSmeltLevel > 0)
            {
                result = FurnaceRecipes.smelting().getSmeltingResult(itemStackBlock);

                if(result != null)
                {
                    result.stackSize = AutoSmeltLevel;
                    for(int i = 0; i < event.drops.size(); i++){
                        event.drops.remove(i);
                    }
                    event.drops.add(result);
                }
            }
    	}
    }
}