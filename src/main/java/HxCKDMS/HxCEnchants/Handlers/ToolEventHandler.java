package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCCore.Handlers.NBTFileIO;
import HxCKDMS.HxCCore.HxCCore;
import HxCKDMS.HxCEnchants.Config;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.util.Random;
@SuppressWarnings("unused")
public class ToolEventHandler
{
	int Vampire;
    int Examine;
    int Poison;
	int AutoSmelt;
    int LifeSteal;
    int SoulTear;

    float VBRV = 0;

    Random random = new Random();

    int derp = random.nextInt(50);

    @SubscribeEvent
    public void LivingHurtEvent(LivingHurtEvent event) {
        Entity hurtent = event.entity;
        Entity ent = event.source.getSourceOfDamage();
        if (ent instanceof EntityPlayerMP && hurtent instanceof EntityLiving){
            EntityPlayerMP Attacker = (EntityPlayerMP) ent;
            EntityLiving Victim = (EntityLiving) hurtent;
            ItemStack item = Attacker.getHeldItem();
            LifeSteal = HxCEnchantHelper.getEnchantLevel(item,17);
            Poison = HxCEnchantHelper.getEnchantLevel(item,18);

            double PH = Victim.prevHealth;
            double CH = Victim.getHealth();
            float RH = (float)CH - (float)PH;

            if (LifeSteal > 0){
                Attacker.heal(RH * LifeSteal);
                degrade(item,17);
            }
            if (Poison > 0){
                Victim.addPotionEffect(new PotionEffect(Potion.poison.getId(), Poison * 60, Poison));
                degrade(item,18);
            }
            if (AutoSmelt > 0){
                Victim.setFire(AutoSmelt*60);
                degrade(item,13);
            }
            if (SoulTear > 0){
                int newPow = (Math.round(RH) + item.getTagCompound().getInteger("HxCEnchantCharge"));
                item.getTagCompound().setInteger("HxCEnchantCharge",newPow);
                degrade(item,21);
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
            if (Attacker.getHeldItem().getItem() instanceof ItemSword) {item = Attacker.getHeldItem();}
            else item = null;
            if (item != null) Vampire = HxCEnchantHelper.getEnchantLevel(item, 24);
            else Vampire = 0;
            if (item != null) Examine = HxCEnchantHelper.getEnchantLevel(item,27);
            else Examine = 0;

            if (Examine > 0){
                if (deadent instanceof EntityLiving) {
                    deadent.worldObj.spawnEntityInWorld(new EntityXPOrb(deadent.worldObj, deadent.posX, deadent.posY+1, deadent.posZ, Examine*Config.enchXPrate));
                    degrade(item,27);
                }
            }

            if (Vampire > 0) {
                degrade(item,24);
                if (deadent instanceof EntityAnimal){
                    VBRV = 1.3F;
                }else if (deadent instanceof EntityPlayerMP){
                    VBRV = 10;
                    if(Config.Feedback){
                        int NP = PlayersEaten++;
                        EatingTracker.setInteger("PlayersEaten", NP);
                        NBTFileIO.setNbtTagCompound(CustomPlayerData, "xenchants", EatingTracker);
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
                }else if (deadent instanceof EntityVillager){
                    VBRV = 8.5F;
                    if(Config.Feedback){
                        int NV = VillagersEaten++;
                        EatingTracker.setInteger("PlayersEaten", NV);
                        NBTFileIO.setNbtTagCompound(CustomPlayerData, "xenchants", EatingTracker);
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
                }else if (((EntityLiving) deadent).isEntityUndead()){
                    VBRV = -1;
                    if(Config.Feedback){
                        int NU = UndeadEaten++;
                        EatingTracker.setInteger("UndeadEaten", NU);
                        NBTFileIO.setNbtTagCompound(CustomPlayerData, "xenchants", EatingTracker);
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
                }else if (deadent instanceof EntitySlime){
                    VBRV = 1.1F;
                }else if (deadent instanceof EntityEnderman){
                    VBRV = 2.2F;
                }else if (deadent instanceof EntityMob){
                    VBRV = 3.2F;
                }else{
                    VBRV = 1.25F;
                }
                int curFood = Attacker.getFoodStats().getFoodLevel();
                float newFud = (VBRV/4 * Vampire) + curFood;
                if (curFood < 40 && newFud < 40){
                    Attacker.getFoodStats().setFoodLevel(Math.round(newFud));
                }else if (curFood < 40 && newFud > 40){
                    Attacker.getFoodStats().setFoodLevel(40);
                }else{
                    Attacker.getFoodStats().setFoodLevel(40);
                }
                if (Config.DebugMode)
                    FMLCommonHandler.instance().getFMLLogger().log(Level.DEBUG, "[Enchants] Setting " + Attacker + "'s Food Level to" + newFud);
            }
        } catch (Exception ignored) {}
        }
	}
	@SubscribeEvent
    public void onHarvestBlocks(BlockEvent.HarvestDropsEvent event) {
        if (event.harvester != null){
            EntityPlayer player = event.harvester;
            Block block = event.block;
            ItemStack itemStackBlock = new ItemStack(Item.getItemFromBlock(block), 1);
            ItemStack heldItem = player.getHeldItem();
            ItemStack result;

            AutoSmelt = HxCEnchantHelper.getEnchantLevel(heldItem,13);
            if(AutoSmelt > 0) {
                result = FurnaceRecipes.smelting().getSmeltingResult(itemStackBlock);
                degrade(heldItem,13);
                if(result != null) {
                    result.stackSize = AutoSmelt;
                    for(int i = 0; i < event.drops.size(); i++) {
                        event.drops.remove(i);
                    }
                    event.drops.add(result);
                }
            }
        }
    }

    public void degrade(ItemStack stack, int Enchantment){
        int[] enchs = stack.getTagCompound().getIntArray("HxCEnchants");
        int power = enchs[Enchantment];
        int newPow = (stack.getTagCompound().getInteger("HxCEnchantCharge") - (power * Config.baseDrain));
        stack.getTagCompound().setInteger("HxCEnchantCharge",newPow);
    }
}