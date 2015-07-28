package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.HxCEnchants.Configurations;
import HxCKDMS.HxCEnchants.EnchantConfigHandler;
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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;

@SuppressWarnings("all")
public class ToolEventHandler {
	int VampireLevel, ExamineLevel, AutoSmeltLevel, LifeStealLevel, PiercingLevel, VorpalLevel, SCurseLevel;
    FurnaceRecipes furnaceRecipes = FurnaceRecipes.smelting();

	// Misc. variables
    @SubscribeEvent
    public void LivingHurtEvent(LivingHurtEvent event) {
        Entity hurtent = event.entity;
        Entity ent = event.source.getSourceOfDamage();
        if (ent instanceof EntityPlayer && hurtent instanceof EntityLiving && ((EntityPlayer) ent).getHeldItem() != null && ((EntityPlayer) ent).getHeldItem().getTagCompound() != null){
            EntityPlayer Attacker = (EntityPlayer) ent;
            EntityLiving Victim = (EntityLiving) hurtent;
            ItemStack item = Attacker.getHeldItem();
            long chrg = item.getTagCompound().getLong("HxCEnchantCharge");
            if (EnchantConfigHandler.isEnabled("LifeSteal", "weapon") && (chrg > EnchantConfigHandler.getData("LifeSteal", "weapon")[4] || !Configurations.enableChargesSystem)) {
                LifeStealLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.LifeSteal.effectId, item);
                if (LifeStealLevel > 0) {
                    Attacker.heal(event.ammount/10 * LifeStealLevel);
                    if (Configurations.enableChargesSystem)
                        item.getTagCompound().setLong("HxCEnchantCharge", chrg - EnchantConfigHandler.getData("LifeSteal", "weapon")[4]);
                }
            }

            if (EnchantConfigHandler.isEnabled("Piercing", "weapon") && (chrg > EnchantConfigHandler.getData("Piercing", "weapon")[4] || !Configurations.enableChargesSystem)) {
                PiercingLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.Piercing.effectId, item);
                if (PiercingLevel > 0)
                    Victim.attackEntityFrom(new DamageSource("Piercing").setDamageBypassesArmor().setDamageAllowedInCreativeMode()
                            .setDamageIsAbsolute(), event.ammount * Configurations.PiercingPercent);
                if (Configurations.enableChargesSystem)
                    item.getTagCompound().setLong("HxCEnchantCharge", chrg - EnchantConfigHandler.getData("Piercing", "weapon")[4]);
            }

            if (EnchantConfigHandler.isEnabled("Vorpal", "weapon") && (chrg > EnchantConfigHandler.getData("Vorpal", "weapon")[4] || !Configurations.enableChargesSystem)) {
                VorpalLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.Vorpal.effectId, item);
                if (VorpalLevel > 0) Victim.attackEntityFrom(new DamageSource("Vorpal").setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute(), VorpalLevel * EnchantConfigHandler.getData("Vorpal", "weapon")[4]);
                if (Configurations.enableChargesSystem)
                    item.getTagCompound().setLong("HxCEnchantCharge", chrg - EnchantConfigHandler.getData("Vorpal", "weapon")[4]);
            }

            if (EnchantConfigHandler.isEnabled("SCurse", "weapon") && (chrg > EnchantConfigHandler.getData("SCurse", "weapon")[4] || !Configurations.enableChargesSystem)) {
                SCurseLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.SCurse.effectId, item);
                if (SCurseLevel > 0) {
                    Victim.attackEntityFrom(new DamageSource("scurse").setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute(), EnchantConfigHandler.getData("SCurse", "weapon")[5] * SCurseLevel);
                    Attacker.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 120 * SCurseLevel, SCurseLevel, true));
                    Attacker.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 120, Math.round(SCurseLevel/3), true));
                    Attacker.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 120 * SCurseLevel, SCurseLevel * EnchantConfigHandler.getData("SCurse", "weapon")[5], true));
                    if (Configurations.enableChargesSystem)
                        item.getTagCompound().setLong("HxCEnchantCharge", chrg - EnchantConfigHandler.getData("SCurse", "weapon")[4]);
                }
            }
        }
    }

	@SubscribeEvent
	public void LivingDeathEvent(LivingDeathEvent event) {
        Entity deadent = event.entity;
        Entity ent = event.source.getSourceOfDamage();
        if (ent instanceof EntityPlayerMP && (deadent instanceof EntityLiving || deadent instanceof EntityPlayerMP) && (!((EntityPlayerMP) ent).getDisplayName().contains("[")) && !(ent instanceof FakePlayer)){
            EntityPlayerMP Attacker = (EntityPlayerMP) ent;
            try{
                ItemStack item;
                if (Attacker.getHeldItem().getItem() instanceof ItemSword) item = Attacker.getHeldItem();
                else return;
                long chrg = item.getTagCompound().getLong("HxCEnchantCharge");

                VampireLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.Vampirism.effectId, item);
                ExamineLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.Examine.effectId, item);

                if (ExamineLevel > 0 && (chrg > EnchantConfigHandler.getData("Examine", "weapon")[4] || !Configurations.enableChargesSystem))
                    if (deadent instanceof EntityLiving) {
                        deadent.worldObj.spawnEntityInWorld(new EntityXPOrb(deadent.worldObj, deadent.posX, deadent.posY + 1, deadent.posZ, ExamineLevel * EnchantConfigHandler.getData("Examine", "weapon")[4]));
                        if (Configurations.enableChargesSystem)
                            item.getTagCompound().setLong("HxCEnchantCharge", chrg - EnchantConfigHandler.getData("Examine", "weapon")[4]);
                    }

                if (VampireLevel > 0 && (chrg > EnchantConfigHandler.getData("Vampirism", "weapon")[4] || !Configurations.enableChargesSystem)) {
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

                    if (HxCKDMS.HxCCore.Configs.Configurations.DebugMode)
                        LogHelper.warn(Attacker + "has had their hunger increased by Vampirism.", Reference.MOD_ID);
                    if (Configurations.enableChargesSystem)
                        item.getTagCompound().setLong("HxCEnchantCharge", chrg - EnchantConfigHandler.getData("Vampirism", "weapon")[4]);
                }
            } catch (Exception e) {if (HxCKDMS.HxCCore.Configs.Configurations.DebugMode) LogHelper.warn(e.getLocalizedMessage(), Reference.MOD_ID);}
        }
	}
	@SubscribeEvent
    public void onHarvestBlocks(BlockEvent.HarvestDropsEvent event) {
        if (event.harvester != null && EnchantConfigHandler.isEnabled("FlameTouch", "weapon")) {
            EntityPlayer player = event.harvester;
            ItemStack heldItem = player.getHeldItem();

            AutoSmeltLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.FlameTouch.effectId, heldItem);
            if(AutoSmeltLevel > 0 && (heldItem.getTagCompound().getLong("HxCEnchantCharge") > EnchantConfigHandler.getData("FlameTouch", "weapon")[4] || !Configurations.enableChargesSystem)) {
                for(int i = 0; i < event.drops.size(); i++) {
                    ItemStack smelted = furnaceRecipes.getSmeltingResult(event.drops.get(i));

                    if(smelted != null){
                        ItemStack drop = smelted.copy();
                        drop.stackSize *= AutoSmeltLevel;
                        event.drops.set(i, drop);
                        if (Configurations.enableChargesSystem)
                            heldItem.getTagCompound().setLong("HxCEnchantCharge", heldItem.getTagCompound().getLong("HxCEnchantCharge") - (EnchantConfigHandler.getData("FlameTouch", "weapon")[4] * AutoSmeltLevel));
                    }
                }
            }
        }
    }
}