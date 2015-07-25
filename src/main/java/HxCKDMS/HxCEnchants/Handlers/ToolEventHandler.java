package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.HxCEnchants.Config;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import HxCKDMS.HxCEnchants.lib.Reference;
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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
@SuppressWarnings("unused")
public class ToolEventHandler {
    int VampireLevel, ExamineLevel, AutoSmeltLevel, LifeStealLevel, PiercingLevel, VorpalLevel, SCurseLevel;
    FurnaceRecipes furnaceRecipes = FurnaceRecipes.instance();

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
                if (LifeStealLevel > 0)
                    Attacker.heal(event.ammount * LifeStealLevel);
            }
            if (Config.enchPiercingEnable) {
                PiercingLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.Piercing.effectId, item);
                if (PiercingLevel > 0) Victim.attackEntityFrom(new DamageSource("Piercing").setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute(), event.ammount * Config.PiercingPercent);
            }
            if (Config.enchVorpalEnable) {
                VorpalLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.Vorpal.effectId, item);
                if (VorpalLevel > 0) Victim.attackEntityFrom(new DamageSource("Vorpal").setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute(), VorpalLevel * Config.enchVorpalVals[4]);
            }
            if (Config.enchSCurseEnable) {
                SCurseLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.SCurse.effectId, item);
                if (SCurseLevel > 0) {
                    Victim.attackEntityFrom(new DamageSource("scurse").setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute(), Config.enchSCurseVals[4] * SCurseLevel);
                    Attacker.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 120, Config.enchSCurseVals[4] * SCurseLevel, true, false));
                    Attacker.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 120, Config.enchSCurseVals[4] * SCurseLevel, true, false));
                    Attacker.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 120 * SCurseLevel, SCurseLevel, true, false));
                }
            }
        }
    }

	@SubscribeEvent
	public void LivingDeathEvent(LivingDeathEvent event) {
        Entity deadent = event.entity;
        Entity ent = event.source.getSourceOfDamage();
        if (ent instanceof EntityPlayerMP && (deadent instanceof EntityLiving || deadent instanceof EntityPlayerMP) && (!((EntityPlayerMP) ent).getDisplayNameString().contains("[")) && !(ent instanceof FakePlayer)){
            EntityPlayerMP Attacker = (EntityPlayerMP) ent;
            try{
                ItemStack item;
                if (Attacker.getHeldItem().getItem() instanceof ItemSword) item = Attacker.getHeldItem();
                else item = null;

                if (item != null) VampireLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.Vampirism.effectId, item);
                else VampireLevel = 0;

                if (item != null) ExamineLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.Examine.effectId, item);
                else ExamineLevel = 0;

                if (ExamineLevel > 0)
                    if (deadent instanceof EntityLiving)
                        deadent.worldObj.spawnEntityInWorld(new EntityXPOrb(deadent.worldObj, deadent.posX, deadent.posY+1, deadent.posZ, ExamineLevel*Config.enchExamineVals[4]));

                if (VampireLevel > 0) {
                    if (deadent instanceof EntityAnimal)
                        Attacker.getFoodStats().addStats(1, 0.3F);
                    else if (deadent instanceof EntityPlayerMP)
                        Attacker.getFoodStats().addStats(10, 0.5F);
                    else if (deadent instanceof EntityVillager)
                        Attacker.getFoodStats().addStats(5, 0.5F);
                    else if (((EntityLiving) deadent).isEntityUndead())
                        Attacker.getFoodStats().addStats(0, 0);
                    else if (deadent instanceof EntitySlime)
                        Attacker.getFoodStats().addStats(1, 0.1F);
                    else if (deadent instanceof EntityEnderman)
                        Attacker.getFoodStats().addStats(2, 0.2F);
                    else if (deadent instanceof EntityMob)
                        Attacker.getFoodStats().addStats(3, 0.2F);

                    else Attacker.getFoodStats().addStats(1, 0.1F);

                    if (Config.DebugMode)
                        LogHelper.warn(Attacker + "has had their hunger increased by Vampirism.", Reference.MOD_ID);
                }
            } catch (Exception e) {if (Config.DebugMode) LogHelper.warn(e.getLocalizedMessage(), Reference.MOD_ID);}
        }
	}
	@SubscribeEvent
    public void onHarvestBlocks(BlockEvent.HarvestDropsEvent event) {
        if (event.harvester != null && Config.enchFlameTouchEnable){
            EntityPlayer player = event.harvester;
            ItemStack heldItem = player.getHeldItem();

            AutoSmeltLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.FlameTouch.effectId, heldItem);
            if(AutoSmeltLevel > 0) {
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