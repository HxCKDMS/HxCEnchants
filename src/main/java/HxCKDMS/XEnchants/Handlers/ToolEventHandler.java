package HxCKDMS.XEnchants.Handlers;

import HxCKDMS.XEnchants.Config;
import HxCKDMS.XEnchants.XEnchants;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
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
import org.apache.logging.log4j.Level;

import java.util.Random;

public class ToolEventHandler
{
	// Integers, ya idiot
	int VampireLevel;
	int AutoSmeltLevel;
    int LifeStealLevel;
    int UndeadEaten = 0;
    int VillagersEaten = 0;
    int PlayersEaten = 0;
    float VBRV = 0;

    Random random = new Random();

    int derp = random.nextInt(50);

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
            EntityPlayerMP Attacker = (EntityPlayerMP) ent;
            ItemStack item = Attacker.getHeldItem();
            VampireLevel = EnchantmentHelper.getEnchantmentLevel(XEnchants.Vampirism.effectId, item);
            if (VampireLevel > 0){
                if (entity instanceof EntityAnimal){
                    VBRV = 1.3F;
                }else if (entity instanceof EntityPlayerMP){
                    VBRV = 10;
                    if(Config.Feedback){
                        PlayersEaten++;
                        if (PlayersEaten == 0) {
                            Attacker.addChatMessage(new ChatComponentText("\u00A74This tastes lovely."));
                        }if (PlayersEaten == 16) {
                            Attacker.addChatMessage(new ChatComponentText("\u00A74So this is what winning all the time tastes like."));
                        }if (PlayersEaten > 16 && derp == 30) {
                            Attacker.addChatMessage(new ChatComponentText("\u00A74I think this guy just Sh*t himself"));
                        }if (PlayersEaten == 64) {
                            Attacker.addChatMessage(new ChatComponentText("\u00A74This taste is very addictive."));
                        }if (PlayersEaten == 128) {
                            Attacker.addChatMessage(new ChatComponentText("\u00A74So yeah this is addictive. I think I need help"));
                        }if (PlayersEaten == 512) {
                            Attacker.addChatMessage(new ChatComponentText("\u00A74Well this addiction is difficult to end."));
                        }
                    }
                }else if (entity instanceof EntityVillager){
                    VBRV = 8.5F;
                    if(Config.Feedback){
                        VillagersEaten++;
                        if (VillagersEaten == 0) {
                            Attacker.addChatMessage(new ChatComponentText("\u00A74Wow this blood is rich."));
                        }if (VillagersEaten == 16) {
                            Attacker.addChatMessage(new ChatComponentText("\u00A74I wonder if this blood is too rich."));
                        }if (VillagersEaten > 16 && derp == 12) {
                            Attacker.addChatMessage(new ChatComponentText("\u00A74Yuck diabetes blood."));
                        }if (VillagersEaten == 64) {
                            Attacker.addChatMessage(new ChatComponentText("\u00A74I kind of wonder if this blood can cause problems in the future."));
                        }if (VillagersEaten > 64 && derp == 14) {
                            Attacker.addChatMessage(new ChatComponentText("\u00A74mmm virgin blood."));
                        }if (VillagersEaten == 128) {
                            Attacker.addChatMessage(new ChatComponentText("\u00A74I may need to get a blood test for any STDs."));
                        }
                    }
                }else if (entity.isEntityUndead()){
                    VBRV = -1;
                    if(Config.Feedback){
                        UndeadEaten++;
                        if (UndeadEaten == 0) {
                            Attacker.addChatMessage(new ChatComponentText("\u00A74Yuck"));
                        }if (UndeadEaten == 16) {
                            Attacker.addChatMessage(new ChatComponentText("\u00A74I really don't like this taste."));
                        }if (UndeadEaten > 16 && derp == 15) {
                            Attacker.addChatMessage(new ChatComponentText("\u00A74When this thing was alive it must've been a fat dude."));
                        }if (UndeadEaten == 64) {
                            Attacker.addChatMessage(new ChatComponentText("\u00A74I think this taste is driving me insane."));
                        }if (UndeadEaten == 128) {
                            Attacker.addChatMessage(new ChatComponentText("\u00A74I am starting to wonder if sucking the blood of the undead can cause some real problems."));
                        }if (UndeadEaten == 256) {
                            Attacker.addChatMessage(new ChatComponentText("\u00A74If mojang added guns I would make one instantly so I can shoot myself this tastes horrible."));
                        }if (UndeadEaten == 512) {
                            Attacker.addChatMessage(new ChatComponentText("\u00A74Can these things stop coming near me? I am tired of eating them. :("));
                        }
                    }
                }else if (entity instanceof EntitySlime){
                    VBRV = 1.1F;
                }else if (entity instanceof EntityEnderman){
                    VBRV = 2.2F;
                }else if (entity instanceof EntityMob){
                    VBRV = 3.2F;
                }else{
                    VBRV = 1.25F;
                }
                int curFood = Attacker.getFoodStats().getFoodLevel();
                float newFud = (VBRV * VampireLevel) + curFood;
                if (curFood < 40 && newFud < 40){
                    Attacker.getFoodStats().setFoodLevel(Math.round(newFud));
                }else if (curFood < 40 && newFud > 40){
                    Attacker.getFoodStats().setFoodLevel(40);
                }else{
                    Attacker.getFoodStats().setFoodLevel(40);
                }
                if (Config.DebugMode)
                    FMLCommonHandler.instance().getFMLLogger().log(Level.DEBUG, "[XEnchants] Setting " + Attacker + "'s Food Level to" + newFud);
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