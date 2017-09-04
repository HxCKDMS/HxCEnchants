package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCEnchants.Configurations.Configurations;
import HxCKDMS.HxCEnchants.api.AABBUtils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import hxckdms.hxccore.libraries.GlobalVariables;
import hxckdms.hxccore.utilities.TeleportHelper;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static HxCKDMS.HxCEnchants.Configurations.Configurations.*;
import static net.minecraft.enchantment.Enchantment.enchantmentsList;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantments;

//Null pointer checked no NPE's can happen ignoring my NPE that will never be thrown because these enchants have already been configured and checked..
@SuppressWarnings("all")
public class EventHandlers {
    private EnchantHandlers handler = new EnchantHandlers();
    private int tickTimer = Configurations.updateTime;


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
//                handler.playerMineBlockEvent(player, heldItem, event, enchants);
            }
        }
    }

    @SubscribeEvent
    public void breakBlockEvent(BlockEvent.BreakEvent event) {
        EntityPlayer player = event.getPlayer();
        if (player.getHeldItem() != null && player.getHeldItem().hasTagCompound() && player.getHeldItem().isItemEnchanted()) {
            ItemStack item = player.getHeldItem(); int worldeater = enchantments.get("EarthEater").id;
            Block block = event.block;
            LinkedHashMap<Integer, Integer> enchs = (LinkedHashMap<Integer, Integer>) getEnchantments(item);
            if (enchs.keySet().contains(worldeater)) {
                int l = enchs.get(worldeater), width, depth, height;

                height = Math.round(l / Configurations.EarthEaterHeightModifier);
                width = Math.round(l / Configurations.EarthEaterWidthModifier);
                depth = Math.round(l / Configurations.EarthEaterDepthModifier);

                float rot = player.getRotationYawHead();
                if (player.rotationPitch < 45 && player.rotationPitch > -45) {
                    if ((rot > 45 && rot < 135) || (rot > -45 && rot < -135)) {
//                        System.out.println("West");
                        for (int x = event.x - (depth); x <= event.x; x++) {
                            for (int y = event.y - 1; y <= event.y + (height-1); y++) {
                                for (int z = event.z - (width/2); z <= event.z + (width/2); z++) {
                                    if (player.worldObj.getBlock(x, y, z).getMaterial() == block.getMaterial() &&
                                            player.canHarvestBlock(player.worldObj.getBlock(x, y, z)) &&
                                            player.worldObj.getBlock(x, y, z).getBlockHardness(player.worldObj, x, y, z) > 0) {
                                        player.worldObj.getBlock(x, y, z).harvestBlock(event.world, player, x, y, z, player.worldObj.getBlockMetadata(x, y, z));
                                        player.worldObj.setBlockToAir(x, y, z);
                                    }
                                }
                            }
                        }
                    } else if ((rot > 225 && rot < 315) || (rot > -225 && rot < -315)) {
//                        System.out.println("East");
                        for (int x = event.x; x <= event.x + (depth); x++) {
                            for (int y = event.y - 1; y <= event.y + (height-1); y++) {
                                for (int z = event.z - (width/2); z <= event.z + (width/2); z++) {
                                    if (player.worldObj.getBlock(x, y, z).getMaterial() == block.getMaterial() &&
                                            player.canHarvestBlock(player.worldObj.getBlock(x, y, z)) &&
                                            player.worldObj.getBlock(x, y, z).getBlockHardness(player.worldObj, x ,y ,z) > 0) {
                                        player.worldObj.getBlock(x, y, z).harvestBlock(event.world, player, x, y, z, player.worldObj.getBlockMetadata(x, y, z));
                                        player.worldObj.setBlockToAir(x, y, z);
                                    }
                                }
                            }
                        }
                    } else if ((rot < 225 && rot > 135) || (rot > -225 && rot < -135)) {
//                        System.out.println("North");
                        for (int x = event.x- (width/2); x <= event.x + (width/2); x++) {
                            for (int y = event.y - 1; y <= event.y + (height-1); y++) {
                                for (int z = event.z - (depth); z <= event.z; z++) {
                                    if (player.worldObj.getBlock(x, y, z).getMaterial() == block.getMaterial() &&
                                            player.canHarvestBlock(player.worldObj.getBlock(x, y, z)) &&
                                            player.worldObj.getBlock(x, y, z).getBlockHardness(player.worldObj, x, y, z) > 0) {
                                        player.worldObj.getBlock(x, y, z).harvestBlock(event.world, player, x, y, z, player.worldObj.getBlockMetadata(x, y, z));
                                        player.worldObj.setBlockToAir(x, y, z);
                                    }
                                }
                            }
                        }
                    } else {
//                        System.out.println("South");
                        for (int x = event.x- (width/2); x <= event.x + (width/2); x++) {
                            for (int y = event.y - 1; y <= event.y + (height-1); y++) {
                                for (int z = event.z; z <= event.z + (depth); z++) {
                                    if (player.worldObj.getBlock(x, y, z).getMaterial() == block.getMaterial() &&
                                            player.canHarvestBlock(player.worldObj.getBlock(x, y, z)) &&
                                            player.worldObj.getBlock(x, y, z).getBlockHardness(player.worldObj, x ,y ,z) > 0) {
                                        player.worldObj.getBlock(x, y, z).harvestBlock(event.world, player, x, y, z, player.worldObj.getBlockMetadata(x, y, z));
                                        player.worldObj.setBlockToAir(x, y, z);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    int xMod = ((rot > 45 && rot < 135) || (rot > -45 && rot < -135) ? (height/2) : (width/2));
                    int zMod = ((rot > 45 && rot < 135) || (rot > -45 && rot < -135) ? (width/2) : (height/2));
                    for (int x = event.x - xMod; x <= event.x + xMod; x++) {
                        for (int z = event.z - zMod; z <= event.z + zMod; z++) {
                            if (player.rotationPitch < -45) {
                                for (int y = event.y; y <= event.y + (depth); y++) {
                                    if (player.worldObj.getBlock(x, y, z).getMaterial() == block.getMaterial() &&
                                            player.canHarvestBlock(player.worldObj.getBlock(x, y, z)) &&
                                            player.worldObj.getBlock(x, y, z).getBlockHardness(player.worldObj, x, y, z) > 0) {
                                        player.worldObj.getBlock(x, y, z).harvestBlock(event.world, player, x, y, z, player.worldObj.getBlockMetadata(x, y, z));
                                        player.worldObj.setBlockToAir(x, y, z);
                                    }
                                }
                            } else {
                                for (int y = event.y - (depth); y <= event.y; y++) {
                                    if (player.worldObj.getBlock(x, y, z).getMaterial() == block.getMaterial() &&
                                            player.canHarvestBlock(player.worldObj.getBlock(x, y, z)) &&
                                            player.worldObj.getBlock(x, y, z).getBlockHardness(player.worldObj, x, y, z) > 0) {
                                        player.worldObj.getBlock(x, y, z).harvestBlock(event.world, player, x, y, z, player.worldObj.getBlockMetadata(x, y, z));
                                        player.worldObj.setBlockToAir(x, y, z);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void PlayerEvent(PlayerEvent.BreakSpeed event) {
        if (Configurations.enchantments.get("SpeedMine").enabled && event.entityPlayer.getHeldItem() != null && event.entityPlayer.getHeldItem().isItemEnchanted() && getEnchantmentLevel(enchantments.get("SpeedMine").id, event.entityPlayer.getHeldItem()) > 0)
            event.newSpeed = (event.originalSpeed + event.originalSpeed*(getEnchantmentLevel(enchantments.get("SpeedMine").id, event.entityPlayer.getHeldItem()) / 10));
    }


	@SubscribeEvent
	public void livingJumpEvent(LivingEvent.LivingJumpEvent event) {
		if(event.entityLiving instanceof EntityPlayer && ((EntityPlayer) event.entityLiving).inventory.armorItemInSlot(1) != null && ((EntityPlayer) event.entityLiving).inventory.armorItemInSlot(1).hasTagCompound() && ((EntityPlayer) event.entityLiving).inventory.armorItemInSlot(1).isItemEnchanted()) {
            ItemStack legs = ((EntityPlayer) event.entityLiving).inventory.armorItemInSlot(1);
            int JumpBoostLevel = getEnchantmentLevel(enchantments.get("JumpBoost").id, legs);
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
                if (enchantments.get("FeatherFall").enabled)
                    featherFall = getEnchantmentLevel(enchantments.get("FeatherFall").id, boots);
                if (enchantments.get("MeteorFall").enabled)
                    meteorFall = getEnchantmentLevel(enchantments.get("MeteorFall").id, boots);

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
