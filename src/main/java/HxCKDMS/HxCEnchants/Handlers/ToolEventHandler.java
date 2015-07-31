package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCCore.api.Utils.AABBUtils;
import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.HxCEnchants.Configurations;
import HxCKDMS.HxCEnchants.EnchantConfigHandler;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import HxCKDMS.HxCEnchants.lib.Reference;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class ToolEventHandler {
    private short VampireLevel, ExamineLevel, VoidLevel, AutoSmeltLevel, LifeStealLevel, PiercingLevel, VorpalLevel, SCurseLevel;
    FurnaceRecipes furnaceRecipes = FurnaceRecipes.smelting();

    @SubscribeEvent
    public void LivingHurtEvent(LivingHurtEvent event) {
        Entity hurtent = event.entity;
        Entity ent = event.source.getSourceOfDamage();
        if (ent instanceof EntityPlayer && hurtent instanceof EntityLivingBase && ((EntityPlayer) ent).getHeldItem() != null && ((EntityPlayer) ent).getHeldItem().hasTagCompound()){
            EntityPlayer Attacker = (EntityPlayer) ent;
            EntityLivingBase Victim = (EntityLivingBase) hurtent;
            ItemStack item = Attacker.getHeldItem();
            long chrg = item.getTagCompound().getLong("HxCEnchantCharge");
            if (EnchantConfigHandler.isEnabled("LifeSteal", "weapon") && (chrg > EnchantConfigHandler.getData("LifeSteal", "weapon")[4] || !Configurations.enableChargesSystem)) {
                LifeStealLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.LifeSteal.effectId, item);
                if (LifeStealLevel > 0) {
                    Attacker.heal(event.ammount/10 * LifeStealLevel);
                    if (Configurations.enableChargesSystem)
                        item.getTagCompound().setLong("HxCEnchantCharge", chrg - EnchantConfigHandler.getData("LifeSteal", "weapon")[4]);
                }
            }

            if (EnchantConfigHandler.isEnabled("Piercing", "weapon") && (chrg > EnchantConfigHandler.getData("Piercing", "weapon")[4] || !Configurations.enableChargesSystem)) {
                PiercingLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.Piercing.effectId, item);
                if (PiercingLevel > 0)
                    Victim.attackEntityFrom(new DamageSource("Piercing").setDamageBypassesArmor().setDamageAllowedInCreativeMode()
                            .setDamageIsAbsolute(), event.ammount * Configurations.PiercingPercent);
                if (Configurations.enableChargesSystem)
                    item.getTagCompound().setLong("HxCEnchantCharge", chrg - EnchantConfigHandler.getData("Piercing", "weapon")[4]);
            }

            if (EnchantConfigHandler.isEnabled("Vorpal", "weapon") && (chrg > EnchantConfigHandler.getData("Vorpal", "weapon")[4] || !Configurations.enableChargesSystem)) {
                VorpalLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.Vorpal.effectId, item);
                if (VorpalLevel > 0) Victim.attackEntityFrom(new DamageSource("Vorpal").setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute(), VorpalLevel * EnchantConfigHandler.getData("Vorpal", "weapon")[4]);
                if (Configurations.enableChargesSystem)
                    item.getTagCompound().setLong("HxCEnchantCharge", chrg - EnchantConfigHandler.getData("Vorpal", "weapon")[4]);
            }

            if (EnchantConfigHandler.isEnabled("SCurse", "weapon") && (chrg > EnchantConfigHandler.getData("SCurse", "weapon")[4] || !Configurations.enableChargesSystem)) {
                SCurseLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.SCurse.effectId, item);
                if (SCurseLevel > 0) {
                    Victim.attackEntityFrom(new DamageSource("scurse").setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute(), EnchantConfigHandler.getData("SCurse", "weapon")[5] * SCurseLevel);
                    Attacker.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 120 * SCurseLevel, SCurseLevel, true));
                    Attacker.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 120, Math.round(SCurseLevel/3), true));
                    Attacker.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 120 * SCurseLevel, SCurseLevel * EnchantConfigHandler.getData("SCurse", "weapon")[5], true));
                    if (Configurations.enableChargesSystem)
                        item.getTagCompound().setLong("HxCEnchantCharge", chrg - EnchantConfigHandler.getData("SCurse", "weapon")[4]);
                }
            }

            if (EnchantConfigHandler.isEnabled("OverCharge", "weapon") && (chrg > EnchantConfigHandler.getData("OverCharge", "weapon")[4] || !Configurations.enableChargesSystem) && item.getTagCompound().getBoolean("StoredCharge")) {
                int OverChage = EnchantmentHelper.getEnchantmentLevel(Enchants.Overcharge.effectId, item);
                int storedCharge = item.getTagCompound().getInteger("HxCOverCharge");
                if (OverChage > 0) {
                    List<EntityLivingBase> list = event.entityLiving.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox((int)Math.round(Attacker.posX), (int)Math.round(Attacker.posY), (int)Math.round(Attacker.posZ), OverChage*5));
                    int damage = storedCharge/list.size();
                    for (EntityLivingBase liv : list) {
                        if (liv != Attacker) {
                            liv.attackEntityFrom(new DamageSource("OverCharge").setDamageIsAbsolute().setDamageAllowedInCreativeMode(), damage);
                        }
                    }
                    item.getTagCompound().setInteger("HxCOverCharge", 0);
                    item.getTagCompound().setBoolean("StoredCharge", false);
                    Map<Integer, Integer> enchs = EnchantmentHelper.getEnchantments(item);
                    enchs.remove(EnchantConfigHandler.getData("OverCharge", "weapon")[0]);
                    if (OverChage > 1) enchs.put((int)EnchantConfigHandler.getData("OverCharge", "weapon")[0], OverChage - 1);
                    EnchantmentHelper.setEnchantments(enchs, item);
                }
            }
        }
    }

	@SubscribeEvent
	public void LivingDeathEvent(LivingDeathEvent event) {
        Entity deadent = event.entity;
        Entity ent = event.source.getSourceOfDamage();
        if (ent instanceof EntityPlayerMP && deadent instanceof EntityLivingBase && (!((EntityPlayerMP) ent).getDisplayName().contains("[")) && !(ent instanceof FakePlayer)){
            EntityPlayerMP Attacker = (EntityPlayerMP) ent;
            try{
                ItemStack item;
                if (Attacker.getHeldItem().getItem() instanceof ItemSword) item = Attacker.getHeldItem();
                else return;
                long chrg = item.getTagCompound().getLong("HxCEnchantCharge");

                VampireLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.Vampirism.effectId, item);
                ExamineLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.Examine.effectId, item);

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
        if (event.harvester != null) {
            EntityPlayer player = event.harvester;
            ItemStack heldItem = player.getHeldItem();

            if (EnchantConfigHandler.isEnabled("FlameTouch", "tool")) {
                AutoSmeltLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.FlameTouch.effectId, heldItem);
                if (AutoSmeltLevel > 0 && (heldItem.getTagCompound().getLong("HxCEnchantCharge") > EnchantConfigHandler.getData("FlameTouch", "tool")[4] || !Configurations.enableChargesSystem)) {
                    for (int i = 0; i < event.drops.size(); i++) {
                        ItemStack smelted = furnaceRecipes.getSmeltingResult(event.drops.get(i));

                        if (smelted != null) {
                            ItemStack drop = smelted.copy();
                            drop.stackSize *= AutoSmeltLevel;
                            event.drops.set(i, drop);
                            if (Configurations.enableChargesSystem)
                                heldItem.getTagCompound().setLong("HxCEnchantCharge", heldItem.getTagCompound().getLong("HxCEnchantCharge") - (EnchantConfigHandler.getData("FlameTouch", "tool")[4] * AutoSmeltLevel));
                        }
                    }
                }
            }

            if (EnchantConfigHandler.isEnabled("VoidTouch", "tool")) {
                VoidLevel = (short)EnchantmentHelper.getEnchantmentLevel(Enchants.VoidTouch.effectId, heldItem);
                if (VoidLevel > 0 && (heldItem.getTagCompound().getLong("HxCEnchantCharge") > EnchantConfigHandler.getData("VoidTouch", "tool")[4] || !Configurations.enableChargesSystem)) {
                    for(String block : Configurations.VoidedItems)
                        event.drops.remove(new ItemStack(Block.getBlockFromName(block)));
                    if (Configurations.enableChargesSystem)
                        heldItem.getTagCompound().setLong("HxCEnchantCharge", heldItem.getTagCompound().getLong("HxCEnchantCharge") - (EnchantConfigHandler.getData("VoidTouch", "tool")[4] * VoidLevel));
                }
            }
        }
    }

    @SubscribeEvent
    public void PlayerEvent(PlayerEvent.BreakSpeed event) {
        if (EnchantConfigHandler.isEnabled("SpeedMine", "tool") && event.entityPlayer.getHeldItem() != null && event.entityPlayer.getHeldItem().isItemEnchanted() && EnchantmentHelper.getEnchantmentLevel(Enchants.SpeedMine.effectId, event.entityPlayer.getHeldItem()) > 0)
            event.newSpeed = (event.originalSpeed + event.originalSpeed*(EnchantmentHelper.getEnchantmentLevel(Enchants.SpeedMine.effectId, event.entityPlayer.getHeldItem()) / 10));
    }
}