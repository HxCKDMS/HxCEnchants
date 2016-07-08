package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCCore.api.Utils.AABBUtils;
import HxCKDMS.HxCEnchants.Configurations.Configurations;
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

import static HxCKDMS.HxCEnchants.Configurations.Configurations.EnabledEnchants;
import static HxCKDMS.HxCEnchants.Configurations.Configurations.EnchantIDs;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantments;

//Null pointer checked no NPE's can happen ignoring my NPE that will never be thrown because these enchants have already been configured and checked..
@SuppressWarnings({"unchecked", "ConstantConditions", "unused"})
public class EventHandlers {
    private EnchantHandlers handler = new EnchantHandlers();
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
                handler.handleDeathEvent(Attacker, (EntityLivingBase)deadent, item);
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
                LinkedHashMap<Enchantment, Integer> enchants = new LinkedHashMap<>();
                LinkedHashMap<Integer, Integer> enchs = (LinkedHashMap<Integer, Integer>) getEnchantments(heldItem);
                enchs.forEach((x, y) -> enchants.put(Enchantment.enchantmentsList[x], y));
                handler.playerMineBlockEvent(player, heldItem, event, enchants);
            }
        }
    }

    @SubscribeEvent
    public void breakBlockEvent(BlockEvent.BreakEvent event) {
        EntityPlayer player = event.getPlayer();
        if (player.getHeldItem() != null && player.getHeldItem().hasTagCompound() && player.getHeldItem().isItemEnchanted()) {
            ItemStack item = player.getHeldItem(); int pml = (int) EnchantIDs.get("EarthEater");
            Block block = event.block;
            LinkedHashMap<Integer, Integer> enchs = (LinkedHashMap<Integer, Integer>) getEnchantments(item);
            if (enchs.keySet().contains(pml)) {
                int l = enchs.get(pml), X, Ym = 1, Z, YM;

                Z = Math.round(l/2.2f);
                X = Math.round(l/1.6f);
                YM = Math.round(l/2);

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
        if (Configurations.EnabledEnchants.get("SpeedMine") && event.entityPlayer.getHeldItem() != null && event.entityPlayer.getHeldItem().isItemEnchanted() && getEnchantmentLevel((int) EnchantIDs.get("SpeedMine"), event.entityPlayer.getHeldItem()) > 0)
            event.newSpeed = (event.originalSpeed + event.originalSpeed*(getEnchantmentLevel((int) EnchantIDs.get("SpeedMine"), event.entityPlayer.getHeldItem()) / 10));
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

                if (ArmourChest != null && ArmourChest.hasTagCompound() && ArmourChest.isItemEnchanted()) {
                    LinkedHashMap<Enchantment, Integer> enchants = new LinkedHashMap<>();
                    LinkedHashMap<Integer, Integer> enchs = (LinkedHashMap<Integer, Integer>) getEnchantments(ArmourChest);
                    enchs.forEach((x, y) -> enchants.put(Enchantment.enchantmentsList[x], y));
                    handler.handleChestplateEnchant(player, ArmourChest, enchants);
                }

                if (ArmourLegs != null && ArmourLegs.hasTagCompound() && ArmourLegs.isItemEnchanted()) {
                    LinkedHashMap<Enchantment, Integer> enchants = new LinkedHashMap<>();
                    LinkedHashMap<Integer, Integer> enchs = (LinkedHashMap<Integer, Integer>) getEnchantments(ArmourLegs);
                    enchs.forEach((x, y) -> enchants.put(Enchantment.enchantmentsList[x], y));
                    handler.handleLeggingEnchant(player, ArmourLegs, enchants);
                }

                if (ArmourBoots != null && ArmourBoots.hasTagCompound() && ArmourBoots.isItemEnchanted()) {
                    LinkedHashMap<Enchantment, Integer> enchants = new LinkedHashMap<>();
                    LinkedHashMap<Integer, Integer> enchs = (LinkedHashMap<Integer, Integer>) getEnchantments(ArmourBoots);
                    enchs.forEach((x, y) -> enchants.put(Enchantment.enchantmentsList[x], y));
                    handler.handleBootEnchant(player, ArmourBoots, enchants);
                }

                if (ArmourHelm != null && ArmourHelm.hasTagCompound() && ArmourHelm.isItemEnchanted()) {
                    LinkedHashMap<Enchantment, Integer> enchants = new LinkedHashMap<>();
                    LinkedHashMap<Integer, Integer> enchs = (LinkedHashMap<Integer, Integer>) getEnchantments(ArmourHelm);
                    enchs.forEach((x, y) -> enchants.put(Enchantment.enchantmentsList[x], y));
                    handler.handleHelmetEnchant(player, ArmourHelm, enchants);
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
                    ents.removeIf(ent -> ent == player);
                    if (sharedEnchants != null && !sharedEnchants.isEmpty() && !ents.isEmpty())
                        handler.handleAuraEvent(player, ents, sharedEnchants);
                }
            }
        }
	}

	@SubscribeEvent
	public void livingJumpEvent(LivingEvent.LivingJumpEvent event) {
		if(event.entityLiving instanceof EntityPlayer && ((EntityPlayer) event.entityLiving).inventory.armorItemInSlot(1) != null && ((EntityPlayer) event.entityLiving).inventory.armorItemInSlot(1).hasTagCompound() && ((EntityPlayer) event.entityLiving).inventory.armorItemInSlot(1).isItemEnchanted()) {
            ItemStack legs = ((EntityPlayer) event.entityLiving).inventory.armorItemInSlot(1);
            int JumpBoostLevel = getEnchantmentLevel((int) EnchantIDs.get("JumpBoost"), legs);
            if (JumpBoostLevel > 0) {
                EntityPlayer player = (EntityPlayer) event.entityLiving;
                double JumpBuff = player.motionY + 0.1 * JumpBoostLevel;
                player.motionY += JumpBuff;
            }
		}
	}

    @SubscribeEvent
    public void livingFallEvent(LivingFallEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            if (player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).hasTagCompound() && player.inventory.armorItemInSlot(0).isItemEnchanted()) {
                ItemStack boots = player.inventory.armorItemInSlot(0);
                int featherFall = 0, meteorFall = 0;
                if (EnabledEnchants.get("FeatherFall"))
                    featherFall = getEnchantmentLevel((int) EnchantIDs.get("FeatherFall"), boots);
                if (EnabledEnchants.get("MeteorFall"))
                    meteorFall = getEnchantmentLevel((int) EnchantIDs.get("MeteorFall"), boots);

                if (featherFall < 4 && featherFall > 0)event.distance /= featherFall;
                else if (featherFall > 4) event.distance = 0;

                if (meteorFall > 0 && event.distance > 10) {
                    player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, event.distance / 5 * meteorFall, false);
                    event.distance = 0;
                }
            }
        }
    }
}
