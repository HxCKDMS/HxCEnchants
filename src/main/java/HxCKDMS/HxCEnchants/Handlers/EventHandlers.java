package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCCore.api.Utils.AABBUtils;
import HxCKDMS.HxCEnchants.Configurations;
import HxCKDMS.HxCEnchants.EnchantConfigHandler;
import HxCKDMS.HxCEnchants.api.HxCEnchantment;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;

import java.util.LinkedHashMap;
import java.util.List;

import static HxCKDMS.HxCEnchants.Configurations.enableChargesSystem;

//Null pointer checked no NPE's can happen ignoring my NPE that will never be thrown because these enchants have already been configured and checked..
public class EventHandlers {
    EnchantHandlers handler = new EnchantHandlers();
    private int tickTimer = Configurations.updateTime;

    @SubscribeEvent
    public void livingHurtEvent(LivingHurtEvent event) {
        if (event.entityLiving instanceof EntityPlayerMP) {
            handler.playerHurtEvent((EntityPlayerMP)event.entityLiving, event.source, event.ammount, event);
        }
        if (event.source.getSourceOfDamage() instanceof EntityPlayerMP && ((EntityPlayerMP) event.source.getSourceOfDamage()).getHeldItem() != null && ((EntityPlayerMP) event.source.getSourceOfDamage()).getHeldItem().hasTagCompound() && ((EntityPlayerMP) event.source.getSourceOfDamage()).getHeldItem().isItemEnchanted()) {
            EntityPlayerMP player = (EntityPlayerMP)event.source.getSourceOfDamage();
            ItemStack item = player.getHeldItem();
            long chrg = -1;
            if (enableChargesSystem) chrg = item.getTagCompound().getLong("HxCEnchantCharge");
            handler.handleAttackEvent(player, event.entityLiving, item, event.ammount, chrg);
        }
    }

    @SubscribeEvent
    public void LivingDeathEvent(LivingDeathEvent event) {
        Entity deadent = event.entity;
        if (deadent instanceof EntityLivingBase && event.source.getSourceOfDamage() instanceof EntityPlayerMP && (!((EntityPlayerMP) event.source.getSourceOfDamage()).getDisplayName().contains("[")) && !(event.source.getSourceOfDamage() instanceof FakePlayer)){
            EntityPlayerMP Attacker = (EntityPlayerMP) event.source.getSourceOfDamage();
            ItemStack item;
            if (Attacker.getHeldItem() != null && (Attacker.getHeldItem().getItem() instanceof ItemSword || Attacker.getHeldItem().getItem() instanceof ItemAxe)) item = Attacker.getHeldItem();
            else return;

            if (item.hasTagCompound() && item.isItemEnchanted()){
				long chrg = item.getTagCompound().getLong("HxCEnchantCharge");
                handler.handleDeathEvent(Attacker, (EntityLivingBase)deadent, item, chrg);
			}
        }
    }

    @SubscribeEvent
    public void onHarvestBlocks(BlockEvent.HarvestDropsEvent event) {
        if (event.harvester != null && event.harvester.getHeldItem() != null) {
            EntityPlayer player = event.harvester;
            ItemStack heldItem = player.getHeldItem();
            long chrg = -1;
            if (heldItem.hasTagCompound() && heldItem.isItemEnchanted()) {
                if (enableChargesSystem)
                    chrg = heldItem.getTagCompound().getLong("HxCEnchantCharge");

                handler.playerMineBlockEvent(player, heldItem, chrg, event);
            }
        }
    }

    @SubscribeEvent
    public void PlayerEvent(PlayerEvent.BreakSpeed event) {
        if (EnchantConfigHandler.isEnabled("SpeedMine", "tool") && event.entityPlayer.getHeldItem() != null && event.entityPlayer.getHeldItem().isItemEnchanted() && EnchantmentHelper.getEnchantmentLevel((int)EnchantConfigHandler.getData("SpeedMine", "other")[0], event.entityPlayer.getHeldItem()) > 0)
            event.newSpeed = (event.originalSpeed + event.originalSpeed*(EnchantmentHelper.getEnchantmentLevel((int)EnchantConfigHandler.getData("SpeedMine", "other")[0], event.entityPlayer.getHeldItem()) / 10));
    }

    @SubscribeEvent
    @SuppressWarnings("unchecked")
	public void playerTickEvent(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving != null && event.entityLiving instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP)event.entityLiving;
            handler.playerTickEvent(player);
            tickTimer--;
            if (tickTimer <= 0) {
                tickTimer = Configurations.updateTime;
                handler.delayedPlayerTickEvent(player);

                ItemStack ArmourHelm = player.inventory.armorItemInSlot(3),
                        ArmourChest = player.inventory.armorItemInSlot(2),
                        ArmourLegs = player.inventory.armorItemInSlot(1),
                        ArmourBoots = player.inventory.armorItemInSlot(0);

                long HChrg = -1, CChrg = -1, LChrg = -1, BChrg = -1;
                if (Configurations.enableChargesSystem) {
                    if (ArmourHelm != null && ArmourHelm.getTagCompound() != null)
                        HChrg = ArmourHelm.getTagCompound().getLong("HxCEnchantCharge");
                    if (ArmourChest != null && ArmourChest.getTagCompound() != null)
                        CChrg = ArmourChest.getTagCompound().getLong("HxCEnchantCharge");
                    if (ArmourLegs != null && ArmourLegs.getTagCompound() != null)
                        LChrg = ArmourLegs.getTagCompound().getLong("HxCEnchantCharge");
                    if (ArmourBoots != null && ArmourBoots.getTagCompound() != null)
                        BChrg = ArmourBoots.getTagCompound().getLong("HxCEnchantCharge");
                }

                if (ArmourChest != null && ArmourChest.hasTagCompound() && ArmourChest.isItemEnchanted()) {
                    handler.handleChestplateEnchant(player, ArmourChest, CChrg);
                }

                if (ArmourLegs != null && ArmourLegs.hasTagCompound() && ArmourLegs.isItemEnchanted()) {
                    handler.handleLeggingEnchant(player, ArmourLegs, LChrg);
                }

                if (ArmourBoots != null && ArmourBoots.hasTagCompound() && ArmourBoots.isItemEnchanted()) {
                    handler.handleBootEnchant(player, ArmourBoots, BChrg);
                }

                if (ArmourHelm != null && ArmourHelm.hasTagCompound() && ArmourHelm.isItemEnchanted()) {
                    handler.handleHelmetEnchant(player, ArmourHelm, HChrg);
                }

                List ents = player.worldObj.getEntitiesWithinAABB(Entity.class, AABBUtils.getAreaBoundingBox((short) Math.round(player.posX), (short) Math.round(player.posY), (short) Math.round(player.posZ), 10));
                if (ArmourChest != null && ArmourChest.hasTagCompound() && ArmourChest.isItemEnchanted() &&
                        ArmourLegs != null && ArmourLegs.hasTagCompound() && ArmourLegs.isItemEnchanted() &&
                        ArmourBoots != null && ArmourBoots.hasTagCompound() && ArmourBoots.isItemEnchanted() &&
                        ArmourHelm != null && ArmourHelm.hasTagCompound() && ArmourHelm.isItemEnchanted() &&
                        !ents.isEmpty()) {
                    LinkedHashMap<Enchantment, Integer> sharedEnchants = new LinkedHashMap<>();
                    LinkedHashMap<Integer, Integer> enchs = (LinkedHashMap<Integer, Integer>)EnchantmentHelper.getEnchantments(ArmourBoots);
                    ((LinkedHashMap<Integer, Integer>) EnchantmentHelper.getEnchantments(ArmourLegs)).forEach(enchs::putIfAbsent);
                    ((LinkedHashMap<Integer, Integer>) EnchantmentHelper.getEnchantments(ArmourChest)).forEach(enchs::putIfAbsent);
                    ((LinkedHashMap<Integer, Integer>) EnchantmentHelper.getEnchantments(ArmourHelm)).forEach(enchs::putIfAbsent);
                    enchs.keySet().forEach(ench -> {
                                if (EnchantmentHelper.getEnchantments(ArmourBoots).containsKey(ench) &&
                                        EnchantmentHelper.getEnchantments(ArmourLegs).containsKey(ench) &&
                                        EnchantmentHelper.getEnchantments(ArmourChest).containsKey(ench) &&
                                        EnchantmentHelper.getEnchantments(ArmourHelm).containsKey(ench)) {
                                    int tmpz = 0;
                                    tmpz += (int)EnchantmentHelper.getEnchantments(ArmourBoots).get(ench);
                                    tmpz += (int)EnchantmentHelper.getEnchantments(ArmourLegs).get(ench);
                                    tmpz += (int)EnchantmentHelper.getEnchantments(ArmourChest).get(ench);
                                    tmpz += (int)EnchantmentHelper.getEnchantments(ArmourHelm).get(ench);

                                    if (Enchantment.enchantmentsList[ench] instanceof HxCEnchantment) {
                                        sharedEnchants.put(Enchantment.enchantmentsList[ench], tmpz);
                                    }
                                }
                            });
                    if (sharedEnchants != null && !sharedEnchants.isEmpty())
                        handler.handleAuraEvent(player, ents, sharedEnchants);
                }
            }
        }
	}

	@SubscribeEvent
	public void livingJumpEvent(LivingEvent.LivingJumpEvent event) {
		if(event.entityLiving instanceof EntityPlayer && ((EntityPlayer) event.entityLiving).inventory.armorItemInSlot(1) != null && ((EntityPlayer) event.entityLiving).inventory.armorItemInSlot(1).hasTagCompound() && ((EntityPlayer) event.entityLiving).inventory.armorItemInSlot(1).isItemEnchanted()) {
            ItemStack boots = ((EntityPlayer) event.entityLiving).inventory.armorItemInSlot(1);
            int JumpBoostLevel = EnchantmentHelper.getEnchantmentLevel((int)EnchantConfigHandler.getData("JumpBoost", "armor")[0], boots);
            if (JumpBoostLevel > 0 && (boots.getTagCompound().getLong("HxCEnchantCharge") > EnchantConfigHandler.getData("JumpBoost", "armor")[4] || !Configurations.enableChargesSystem)) {
                EntityPlayer player = (EntityPlayer) event.entityLiving;
                double JumpBuff = player.motionY + 0.1 * JumpBoostLevel;
                player.motionY += JumpBuff;
                if (Configurations.enableChargesSystem)
                    boots.getTagCompound().setLong("HxCEnchantCharge", boots.getTagCompound().getLong("HxCEnchantCharge") - EnchantConfigHandler.getData("JumpBoost", "armor")[4]);
            }
		}
	}

    @SubscribeEvent
    public void livingFallEvent(LivingFallEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            if (player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).hasTagCompound() && player.inventory.armorItemInSlot(0).isItemEnchanted()) {
                ItemStack boots = player.inventory.armorItemInSlot(0);
                int meh = 0, meh2 = 0;
                if (EnchantConfigHandler.isEnabled("FeatherFall", "armor"))
                    meh = EnchantmentHelper.getEnchantmentLevel((int)EnchantConfigHandler.getData("FeatherFall", "armor")[0], boots);
                if (EnchantConfigHandler.isEnabled("MeteorFall", "armor"))
                    meh2 = EnchantmentHelper.getEnchantmentLevel((int)EnchantConfigHandler.getData("MeteorFall", "armor")[0], boots);

                if (meh < 4 && meh > 0)event.distance /= meh;
                else if (meh > 4) event.distance = 0;

                if (meh2 > 0 && event.distance > 10) {
                    player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, event.distance / 2 * meh2, false);
                    event.distance = 0;
                }
            }
        }
    }
}
