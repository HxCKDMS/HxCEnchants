package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCEnchants.Configurations;
import HxCKDMS.HxCEnchants.EnchantConfigHandler;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
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

import static HxCKDMS.HxCEnchants.Configurations.enableChargesSystem;

@SuppressWarnings("all")
public class EventHandlers {
    EnchantHandlers handler = new EnchantHandlers();

    @SubscribeEvent
    public void livingHurtEvent(LivingHurtEvent event) {
        if (event.entityLiving instanceof EntityPlayerMP) {
            handler.playerHurtEvent((EntityPlayerMP)event.entityLiving, event.source, event.ammount);
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
        if (event.source.getSourceOfDamage() instanceof EntityPlayerMP && deadent instanceof EntityLivingBase && (!((EntityPlayerMP) event.source.getSourceOfDamage()).getDisplayName().contains("[")) && !(event.source.getSourceOfDamage() instanceof FakePlayer)){
            EntityPlayerMP Attacker = (EntityPlayerMP) event.source.getSourceOfDamage();
            ItemStack item;
            if (Attacker.getHeldItem() != null && (Attacker.getHeldItem().getItem() instanceof ItemSword || Attacker.getHeldItem().getItem() instanceof ItemAxe)) item = Attacker.getHeldItem();
            else return;
            long chrg = item.getTagCompound().getLong("HxCEnchantCharge");

            if (item.hasTagCompound() && item.isItemEnchanted())
                handler.handleDeathEvent(Attacker, (EntityLivingBase)deadent, item, chrg);
        }
    }

    @SubscribeEvent
    public void onHarvestBlocks(BlockEvent.HarvestDropsEvent event) {
        if (event.harvester != null) {
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
        if (EnchantConfigHandler.isEnabled("SpeedMine", "tool") && event.entityPlayer.getHeldItem() != null && event.entityPlayer.getHeldItem().isItemEnchanted() && EnchantmentHelper.getEnchantmentLevel(Enchants.SpeedMine.effectId, event.entityPlayer.getHeldItem()) > 0)
            event.newSpeed = (event.originalSpeed + event.originalSpeed*(EnchantmentHelper.getEnchantmentLevel(Enchants.SpeedMine.effectId, event.entityPlayer.getHeldItem()) / 10));
    }

    private int tickTimer = Configurations.updateTime;
    @SubscribeEvent
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
            }
        }
	}

	@SubscribeEvent
	public void livingJumpEvent(LivingEvent.LivingJumpEvent event) {
		if(event.entityLiving instanceof EntityPlayer && ((EntityPlayer) event.entityLiving).inventory.armorItemInSlot(1) != null && ((EntityPlayer) event.entityLiving).inventory.armorItemInSlot(1).hasTagCompound() && ((EntityPlayer) event.entityLiving).inventory.armorItemInSlot(1).isItemEnchanted()) {
            ItemStack boots = ((EntityPlayer) event.entityLiving).inventory.armorItemInSlot(1);
            int JumpBoostLevel = EnchantmentHelper.getEnchantmentLevel(Enchants.JumpBoost.effectId, boots);
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
                    meh = EnchantmentHelper.getEnchantmentLevel(Enchants.FeatherFall.effectId, boots);
                if (EnchantConfigHandler.isEnabled("MeteorFall", "armor"))
                    meh2 = EnchantmentHelper.getEnchantmentLevel(Enchants.MeteorFall.effectId, boots);

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
