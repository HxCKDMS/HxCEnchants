package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCCore.Handlers.NBTFileIO;
import HxCKDMS.HxCCore.HxCCore;
import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.HxCEnchants.Config;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import HxCKDMS.HxCEnchants.lib.Reference;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;

import java.io.File;
@SuppressWarnings("unused")
public class ToolEventHandler {
	int VampireLevel, ExamineLevel, AutoSmeltLevel, LifeStealLevel, PiercingLevel;
    float VBRV = 0;
    FurnaceRecipes furnaceRecipes = FurnaceRecipes.smelting();

	// Misc. variables
    @SubscribeEvent
    public void LivingHurtEvent(LivingHurtEvent event) {
        Entity hurtent = event.entity;
        Entity ent = event.source.getSourceOfDamage();
        if (ent instanceof EntityPlayerMP && hurtent instanceof EntityLiving && ((EntityPlayerMP) ent).getHeldItem() != null){
            EntityPlayerMP Attacker = (EntityPlayerMP) ent;
            EntityLiving Victim = (EntityLiving) hurtent;
            ItemStack item = Attacker.getHeldItem();
            if (Config.enchLifeStealEnable) {
                LifeStealLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.LifeSteal.effectId, item);
                if (LifeStealLevel > 0) {
                    double PH = Victim.prevHealth;
                    double CH = Victim.getHealth();
                    float RH = (float) CH - (float) PH;
                    Attacker.heal(RH * LifeStealLevel);
                }
            }
            if (Config.enchPiercingEnable) {
                PiercingLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.Piercing.effectId, item);
                if (PiercingLevel > 0) Victim.attackEntityFrom(new DamageSource("Piercing").setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute(), event.ammount * Config.PiercingPercent);
            }
        }
    }

	@SubscribeEvent
	public void LivingDeathEvent(LivingDeathEvent event) {
        Entity deadent = event.entity;
        Entity ent = event.source.getSourceOfDamage();
        if (ent instanceof EntityPlayerMP && (deadent instanceof EntityLiving || deadent instanceof EntityPlayerMP) && (!((EntityPlayerMP) ent).getDisplayName().contains("[")) && !(ent instanceof FakePlayer)){
            EntityPlayerMP Attacker = (EntityPlayerMP) ent;
            String UUID = Attacker.getUniqueID().toString();
            try{
                File CustomPlayerData = new File(HxCCore.HxCCoreDir, "HxC-" + UUID + ".dat");
                NBTTagCompound EatingTracker = NBTFileIO.getNbtTagCompound(CustomPlayerData, "xenchants");

                int UndeadEaten = EatingTracker.getInteger("UndeadEaten");
                int VillagersEaten = EatingTracker.getInteger("VillagersEaten");
                int PlayersEaten = EatingTracker.getInteger("PlayersEaten");

                ItemStack item;
                if (Attacker.getHeldItem().getItem() instanceof ItemSword) item = Attacker.getHeldItem();
                else item = null;

                if (item != null) VampireLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.Vampirism.effectId, item);
                else VampireLevel = 0;

                if (item != null) ExamineLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.Examine.effectId, item);
                else ExamineLevel = 0;

                if (ExamineLevel > 0) {
                    if (deadent instanceof EntityLiving)
                        deadent.worldObj.spawnEntityInWorld(new EntityXPOrb(deadent.worldObj, deadent.posX, deadent.posY+1, deadent.posZ, ExamineLevel*Config.enchXPrate));
                }

                if (VampireLevel > 0) {
                    if (deadent instanceof EntityAnimal){
                        VBRV = 1.3F;
                    } else if (deadent instanceof EntityPlayerMP){
                        VBRV = 10;
                        if(Config.Feedback){
                            int NP = PlayersEaten++;
                            EatingTracker.setInteger("PlayersEaten", NP);
                            NBTFileIO.setNbtTagCompound(CustomPlayerData, "xenchants", EatingTracker);
                            if (PlayersEaten == 0) Attacker.addChatMessage(new ChatComponentText("\u00A74This tastes lovely."));
                            if (PlayersEaten == 16) Attacker.addChatMessage(new ChatComponentText("\u00A74So this is what winning all the time tastes like."));
                            if (PlayersEaten == 32) Attacker.addChatMessage(new ChatComponentText("\u00A74I think this guy just Shat himself"));
                            if (PlayersEaten == 64) Attacker.addChatMessage(new ChatComponentText("\u00A74This taste is very addictive."));
                            if (PlayersEaten == 128) Attacker.addChatMessage(new ChatComponentText("\u00A74So yeah this is addictive. I think I need help"));
                            if (PlayersEaten == 512) Attacker.addChatMessage(new ChatComponentText("\u00A74Well this addiction is difficult to end."));
                        }
                    } else if (deadent instanceof EntityVillager){
                        VBRV = 8.5F;
                        if(Config.Feedback) {
                            int NV = VillagersEaten++;
                            EatingTracker.setInteger("PlayersEaten", NV);
                            NBTFileIO.setNbtTagCompound(CustomPlayerData, "xenchants", EatingTracker);
                            if (VillagersEaten == 0) Attacker.addChatMessage(new ChatComponentText("\u00A74Wow this blood is rich."));
                            if (VillagersEaten == 16) Attacker.addChatMessage(new ChatComponentText("\u00A74I wonder if this blood is too rich."));
                            if (VillagersEaten == 32) Attacker.addChatMessage(new ChatComponentText("\u00A74Yuck diabetes blood."));
                            if (VillagersEaten == 64) Attacker.addChatMessage(new ChatComponentText("\u00A74I kind of wonder if this blood can cause problems in the future."));
                            if (VillagersEaten == 72) Attacker.addChatMessage(new ChatComponentText("\u00A74Mmm virgin blood."));
                            if (VillagersEaten == 128) Attacker.addChatMessage(new ChatComponentText("\u00A74I may need to get a blood test for any STDs."));
                        }
                    } else if (((EntityLiving) deadent).isEntityUndead()){
                        VBRV = -1;
                        if(Config.Feedback){
                            int NU = UndeadEaten++;
                            EatingTracker.setInteger("UndeadEaten", NU);
                            NBTFileIO.setNbtTagCompound(CustomPlayerData, "xenchants", EatingTracker);
                            if (UndeadEaten == 0) Attacker.addChatMessage(new ChatComponentText("\u00A74Yuck"));
                            if (UndeadEaten == 16) Attacker.addChatMessage(new ChatComponentText("\u00A74I really don't like this taste."));
                            if (UndeadEaten == 32) Attacker.addChatMessage(new ChatComponentText("\u00A74When this thing was alive it must've been a fat dude."));
                            if (UndeadEaten == 64) Attacker.addChatMessage(new ChatComponentText("\u00A74I think this taste is driving me insane."));
                            if (UndeadEaten == 128) Attacker.addChatMessage(new ChatComponentText("\u00A74I am starting to wonder if sucking the blood of the undead can cause some real problems."));
                            if (UndeadEaten == 256) Attacker.addChatMessage(new ChatComponentText("\u00A74If mojang added guns I would make one instantly so I can shoot myself this tastes horrible."));
                            if (UndeadEaten == 512) Attacker.addChatMessage(new ChatComponentText("\u00A74Can these things stop coming near me? I am tired of this taste. :("));
                        }
                    }
                    else if (deadent instanceof EntitySlime) VBRV = 1.1F;
                    else if (deadent instanceof EntityEnderman) VBRV = 2.2F;
                    else if (deadent instanceof EntityMob) VBRV = 3.2F;
                    else VBRV = 1.25F;

                    int curFood = Attacker.getFoodStats().getFoodLevel();
                    float newFud = (VBRV/4 * VampireLevel) + curFood;
                    if (curFood < 40 && newFud < 40) Attacker.getFoodStats().addStats(Math.round(newFud), 0);
                    else if (curFood < 40 && newFud > 40) Attacker.getFoodStats().addStats(40, 0);
                    else Attacker.getFoodStats().addStats(40, 0);

                    if (Config.DebugMode)
                        LogHelper.debug("[Enchants] Setting " + Attacker + "'s Food Level to" + newFud, Reference.MOD_ID);
                }
            } catch (Exception e) {if (Config.DebugMode) LogHelper.debug(e.getLocalizedMessage(), Reference.MOD_ID);}
        }
	}
	@SubscribeEvent
    public void onHarvestBlocks(BlockEvent.HarvestDropsEvent event) {
        if (event.harvester != null && Config.enchFlameTouchEnable){
            EntityPlayer player = event.harvester;
            ItemStack heldItem = player.getHeldItem();

            AutoSmeltLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.FlameTouch.effectId, heldItem);
            if(AutoSmeltLevel > 0) {
                FurnaceRecipes furnaceRecipes = FurnaceRecipes.smelting();

                for(int i = 0; i < event.drops.size(); i++) {
                    ItemStack smelted = furnaceRecipes.getSmeltingResult(event.drops.get(i));

                    if(smelted != null){
                        ItemStack drop = smelted.copy();
                        drop.stackSize *= AutoSmeltLevel;
                        event.drops.set(i, drop);
                    }
                }
            }
        }
    }
}