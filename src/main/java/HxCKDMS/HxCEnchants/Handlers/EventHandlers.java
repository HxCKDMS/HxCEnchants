package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCCore.api.Utils.AABBUtils;
import HxCKDMS.HxCEnchants.Configurations;
import HxCKDMS.HxCEnchants.EnchantConfigHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
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
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantments;

//Null pointer checked no NPE's can happen ignoring my NPE that will never be thrown because these enchants have already been configured and checked..
@SuppressWarnings({"unchecked", "ConstantConditions", "unused"})
public class EventHandlers {
    EnchantHandlers handler = new EnchantHandlers();
    private int tickTimer = Configurations.updateTime;

    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void livingHurtEvent(LivingHurtEvent event) {
        if (event.entityLiving instanceof EntityPlayerMP) {
            handler.playerHurtEvent((EntityPlayerMP)event.entityLiving, event.source, event.ammount, event);
        }
        if (event.source.getSourceOfDamage() instanceof EntityPlayerMP && ((EntityPlayerMP) event.source.getSourceOfDamage()).getHeldItem() != null && ((EntityPlayerMP) event.source.getSourceOfDamage()).getHeldItem().hasTagCompound() && ((EntityPlayerMP) event.source.getSourceOfDamage()).getHeldItem().isItemEnchanted()) {
            EntityPlayerMP player = (EntityPlayerMP)event.source.getSourceOfDamage();
            ItemStack item = player.getHeldItem();
            LinkedHashMap<Enchantment, Integer> enchants = new LinkedHashMap<>();
            LinkedHashMap<Integer, Integer> enchs = (LinkedHashMap<Integer, Integer>) getEnchantments(item);
            enchs.forEach((x, y) -> enchants.put(Enchantment.enchantmentsList[x], y));
            handler.handleAttackEvent(player, event.entityLiving, item, event.ammount, event, enchants);
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
    public void breakBlockEvent(BlockEvent.BreakEvent event) {
        EntityPlayer player = event.getPlayer();
        if (player.getHeldItem() != null && player.getHeldItem().hasTagCompound() && player.getHeldItem().isItemEnchanted()) {
            ItemStack item = player.getHeldItem(); int pml = (int) EnchantConfigHandler.getData("EarthEater", "other")[0];
            Block block = event.block;
            LinkedHashMap<Integer, Integer> enchs = (LinkedHashMap<Integer, Integer>) getEnchantments(item);
            if (enchs.keySet().contains(pml)) {
                int l = enchs.get(pml), X, Ym, Z, YM;
                switch (l) {
                    case 1 : X = 0; Ym = 1; YM = 1; Z = 0;break;
                    case 2 : X = 1; Ym = 1; YM = 1; Z = 0;break;
                    case 3 : X = 1; Ym = 1; YM = 1; Z = 1;break;
                    case 4 : X = 2; Ym = 1; YM = 2; Z = 1;break;
                    case 5 : X = 2; Ym = 1; YM = 2; Z = 2;break;
                    case 6 : X = 2; Ym = 1; YM = 3; Z = 2;break;
                    case 7 : X = 3; Ym = 1; YM = 3; Z = 2;break;
                    case 8 : X = 3; Ym = 1; YM = 3; Z = 3;break;
                    case 9 : X = 4; Ym = 1; YM = 4; Z = 3;break;
                    case 10 : X = 4; Ym = 1;YM = 4; Z = 4; break;
                    default : X = 5; Ym = 1;YM = 5; Z = 5; break;
                }
                float rot = player.getRotationYawHead();

                if (player.rotationPitch > 45 && player.rotationPitch < -45) {
                                              /* west || east */
                    if ((rot < 135 && rot > 45) || (rot < -45 && rot > -135)) {
                        int tmp = Z;
                        Z = X;
                        X = tmp;
                    }
                } else {
                    int tmp = Z;
                    Z = X;
                    if (player.rotationPitch > 45) {
                        Ym += tmp;
                    } else {
                        YM += tmp;
                    }
                }

                for (int a = event.x - X; a <= event.x + X; a++) {
                    for (int b = event.y - Ym; b <= event.y + YM; b++) {
                        for (int c = event.z - Z; c <= event.z + Z; c++) {
                            if (player.worldObj.getBlock(a, b, c).getMaterial() == block.getMaterial() && player.canHarvestBlock(player.worldObj.getBlock(a, b, c)))
                                player.worldObj.breakBlock(a, b, c, !player.capabilities.isCreativeMode);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void PlayerEvent(PlayerEvent.BreakSpeed event) {
        if (EnchantConfigHandler.isEnabled("SpeedMine", "other") && event.entityPlayer.getHeldItem() != null && event.entityPlayer.getHeldItem().isItemEnchanted() && getEnchantmentLevel((int) EnchantConfigHandler.getData("SpeedMine", "other")[0], event.entityPlayer.getHeldItem()) > 0)
            event.newSpeed = (event.originalSpeed + event.originalSpeed*(getEnchantmentLevel((int) EnchantConfigHandler.getData("SpeedMine", "other")[0], event.entityPlayer.getHeldItem()) / 10));
    }

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

                List ents = player.worldObj.getEntitiesWithinAABB(Entity.class, AABBUtils.getAreaBoundingBox((short) Math.round(player.posX), (short) Math.round(player.posY), (short) Math.round(player.posZ), 10));
                if (ArmourChest != null && ArmourChest.hasTagCompound() && ArmourChest.isItemEnchanted() &&
                        ArmourLegs != null && ArmourLegs.hasTagCompound() && ArmourLegs.isItemEnchanted() &&
                        ArmourBoots != null && ArmourBoots.hasTagCompound() && ArmourBoots.isItemEnchanted() &&
                        ArmourHelm != null && ArmourHelm.hasTagCompound() && ArmourHelm.isItemEnchanted() &&
                        !ents.isEmpty()) {
                    LinkedHashMap<Enchantment, Integer> sharedEnchants = new LinkedHashMap<>();
                    LinkedHashMap<Integer, Integer> enchs = (LinkedHashMap<Integer, Integer>) getEnchantments(ArmourBoots);
                    ((LinkedHashMap<Integer, Integer>) getEnchantments(ArmourLegs)).forEach(enchs::putIfAbsent);
                    ((LinkedHashMap<Integer, Integer>) getEnchantments(ArmourChest)).forEach(enchs::putIfAbsent);
                    ((LinkedHashMap<Integer, Integer>) getEnchantments(ArmourHelm)).forEach(enchs::putIfAbsent);
                    enchs.keySet().forEach(ench -> {
                                if (getEnchantments(ArmourBoots).containsKey(ench) &&
                                        getEnchantments(ArmourLegs).containsKey(ench) &&
                                        getEnchantments(ArmourChest).containsKey(ench) &&
                                        getEnchantments(ArmourHelm).containsKey(ench)) {

                                    int tmpz =  (int) getEnchantments(ArmourBoots).get(ench);
                                    tmpz += (int) getEnchantments(ArmourLegs).get(ench);
                                    tmpz += (int) getEnchantments(ArmourChest).get(ench);
                                    tmpz += (int) getEnchantments(ArmourHelm).get(ench);

                                    sharedEnchants.put(Enchantment.enchantmentsList[ench], tmpz);
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
            int JumpBoostLevel = getEnchantmentLevel((int) EnchantConfigHandler.getData("JumpBoost", "armor")[0], boots);
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
                    meh = getEnchantmentLevel((int) EnchantConfigHandler.getData("FeatherFall", "armor")[0], boots);
                if (EnchantConfigHandler.isEnabled("MeteorFall", "armor"))
                    meh2 = getEnchantmentLevel((int) EnchantConfigHandler.getData("MeteorFall", "armor")[0], boots);

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
