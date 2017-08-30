package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCEnchants.api.AABBUtils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockReed;
import net.minecraft.block.IGrowable;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.world.BlockEvent;

import java.util.*;

import static HxCKDMS.HxCEnchants.Configurations.Configurations.*;
import static net.minecraft.enchantment.Enchantment.enchantmentsList;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantments;

public class EnchantEventHandlers {
    @SubscribeEvent
    public void playerMineBlockEvent(BlockEvent.HarvestDropsEvent event) {
        if (event.harvester != null) {
            ItemStack tool = event.harvester.getHeldItem();
            if (isEnabled("FlameTouch")) {
                int AutoSmeltLevel = (short) EnchantmentHelper.getEnchantmentLevel(enchantments.get("FlameTouch").id, tool);
                if (AutoSmeltLevel > 0) {
                    for (int i = 0; i < event.drops.size(); i++) {
                        ItemStack smelted = FurnaceRecipes.smelting().getSmeltingResult(event.drops.get(i));

                        if (smelted != null) {
                            ItemStack drop = smelted.copy();
                            drop.stackSize *= AutoSmeltLevel;
                            event.drops.set(i, drop);

                            event.world.spawnEntityInWorld(new EntityXPOrb(event.world, event.x, event.y, event.z, (int) FurnaceRecipes.smelting().func_151398_b(drop)));
                        }
                    }
                }
            }
            if (isEnabled("VoidTouch")) {
                short voidLevel = (short) EnchantmentHelper.getEnchantmentLevel(enchantments.get("VoidTouch").id, tool);
                if (voidLevel > 0 && event.drops.size() > 0) {
                    List<ItemStack> stacks = event.drops;
                    for (int i = stacks.size() - 1; i == 0; i--) {
                        for (String block : VoidedItems) {
                            if (event.drops.get(i).getDisplayName().equalsIgnoreCase(new ItemStack(Block.getBlockFromName(block)).getDisplayName())) {
                                event.drops.remove(i);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private int auraDelayTimer = AuraUpdateDelay;
    @SubscribeEvent
    @SuppressWarnings("all")
    public void playerTickEvent(LivingEvent.LivingUpdateEvent event) {
        auraDelayTimer--;

        if (event.entityLiving != null && event.entityLiving instanceof EntityPlayerMP && auraDelayTimer <= 0) {
            auraDelayTimer = AuraUpdateDelay * 100;
            EntityPlayerMP player = (EntityPlayerMP) event.entityLiving;
            ItemStack ArmourHelm = player.inventory.armorItemInSlot(3),
                    ArmourChest = player.inventory.armorItemInSlot(2),
                    ArmourLegs = player.inventory.armorItemInSlot(1),
                    ArmourBoots = player.inventory.armorItemInSlot(0);

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

                        int tmpz = (int) getEnchantments(ArmourBoots).get(ench);
                        tmpz += (int) getEnchantments(ArmourLegs).get(ench);
                        tmpz += (int) getEnchantments(ArmourChest).get(ench);
                        tmpz += (int) getEnchantments(ArmourHelm).get(ench);

                        sharedEnchants.put(Enchantment.enchantmentsList[ench], tmpz);
                    }
                });
                ents.removeIf(ent -> ent == player);
                if (sharedEnchants != null && !sharedEnchants.isEmpty() && !ents.isEmpty())
                    handleAuraEvent(player, ents, sharedEnchants);
            }
        }
    }

    public void handleAuraEvent(EntityPlayerMP player, List<Entity> ents, LinkedHashMap<Enchantment, Integer> sharedEnchants) {
        World world = player.getEntityWorld();
        for (Entity entity : ents) {
            if (entity instanceof EntityLivingBase && entity != player && !entity.isDead) {
                if (sharedEnchants.keySet().contains(enchantmentsList[enchantments.get("AuraDeadly").id])) {
                    if ((AurasAffectPlayers || !(entity instanceof EntityPlayer)) && !(entity instanceof EntityGolem) && !(entity instanceof EntityAnimal) && !((EntityLivingBase) entity).isPotionActive(Potion.wither)) {
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.wither.getId(), 100, 1, true));
                    }
                }

                if (sharedEnchants.keySet().contains(enchantmentsList[enchantments.get("AuraDark").id])) {
                    if ((AurasAffectPlayers || !(entity instanceof EntityPlayer)) && !(entity instanceof EntityGolem) && !(entity instanceof EntityAnimal) && !((EntityLivingBase) entity).isPotionActive(Potion.blindness)) {
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.blindness.getId(), 100, 1, true));
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.confusion.getId(), 100, 1, true));
                    }
                }

                if (sharedEnchants.keySet().contains(enchantmentsList[enchantments.get("AuraFiery").id])) {
                    if ((AurasAffectPlayers || !(entity instanceof EntityPlayer)) && !(entity instanceof EntityGolem) && !(entity instanceof EntityAnimal) && !entity.isBurning()) {
                        entity.setFire(sharedEnchants.get(enchantmentsList[enchantments.get("AuraFiery").id]) * 2);
                    }
                    findBlocksWithinAABB(world, AABBUtils.getAreaBoundingBox((int) player.posX, (int) player.posY, (int) player.posZ, sharedEnchants.get(enchantmentsList[enchantments.get("AuraFiery").id])), Arrays.asList(Blocks.ice, Blocks.snow_layer, Blocks.snow)).forEach(meltable -> {
                        if (world.getBlock(meltable.chunkPosX, meltable.chunkPosY, meltable.chunkPosZ) == Blocks.ice)
                            world.setBlock(meltable.chunkPosX, meltable.chunkPosY, meltable.chunkPosZ, Blocks.water);
                        else world.setBlockToAir(meltable.chunkPosX, meltable.chunkPosY, meltable.chunkPosZ);
                    });
                }

                if (sharedEnchants.keySet().contains(enchantmentsList[enchantments.get("AuraThick").id])) {
                    if ((AurasAffectPlayers || !(entity instanceof EntityPlayer)) && !(entity instanceof EntityGolem) && !(entity instanceof EntityAnimal) && !((EntityLivingBase) entity).isPotionActive(Potion.moveSlowdown)) {
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 100, 1, true));
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 100, 1, true));
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.weakness.getId(), 100, 1, true));
                    }
                }

                if (sharedEnchants.keySet().contains(enchantmentsList[enchantments.get("AuraToxic").id])) {
                    if ((AurasAffectPlayers || !(entity instanceof EntityPlayer)) && !(entity instanceof EntityGolem) && !(entity instanceof EntityAnimal) && !((EntityLivingBase) entity).isPotionActive(Potion.poison)) {
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.getId(), 500, 1, true));
                    }
                }

                if (sharedEnchants.keySet().contains(enchantmentsList[enchantments.get("HealingAura").id])) {
                    if ((!(entity instanceof EntityPlayer)) && !(entity instanceof EntityMob) && !((EntityLivingBase) entity).isPotionActive(Potion.regeneration)) {
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 500, Math.round(sharedEnchants.get(enchantmentsList[enchantments.get("GaiaAura").id])/4/3), true));
                    }
                }

                if (sharedEnchants.keySet().contains(enchantmentsList[enchantments.get("IcyAura").id])) {
                    if ((AurasAffectPlayers || !(entity instanceof EntityPlayer)) && !(entity instanceof EntityGolem) && !(entity instanceof EntityAnimal) && !((EntityLivingBase) entity).isPotionActive(Potion.moveSlowdown)) {
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 500, 1, true));
                    }
                }

                if (sharedEnchants.keySet().contains(enchantmentsList[enchantments.get("ChargedAura").id])) {
                    if (!(entity instanceof EntityPlayer) && !(entity instanceof EntityGolem) && !(entity instanceof EntityAnimal)) {
                        world.addWeatherEffect(new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ));
                    }
                }

                if (sharedEnchants.keySet().contains(enchantmentsList[enchantments.get("RepulsiveAura").id])) {
                    if (!(entity instanceof EntityAnimal || entity instanceof EntityVillager || entity instanceof EntityGolem || entity instanceof EntityPlayer)) {
                        double motionX = player.posX - entity.posX;
                        double motionY = player.boundingBox.minY + player.height - entity.posY;
                        double motionZ = player.posZ - entity.posZ;
                        entity.setVelocity(-motionX / 8, -motionY / 8, -motionZ / 8);
                    }
                }
            }

            if (sharedEnchants.keySet().contains(enchantmentsList[enchantments.get("GaiaAura").id])) {
                int ran = world.rand.nextInt(Math.round(250 / (GaiasAuraSpeed * sharedEnchants.get(enchantmentsList[enchantments.get("GaiaAura").id]))));
                if (ran == 0) {
                    List<ChunkPosition> crops = getCropsWithinAABB(player.worldObj, AABBUtils.getAreaBoundingBox((short) Math.round(player.posX), (short) Math.round(player.posY), (short) Math.round(player.posZ), sharedEnchants.get(enchantmentsList[enchantments.get("GaiaAura").id])/4));
                    for (ChunkPosition pos : crops)
                        player.worldObj.getBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ).updateTick(player.worldObj, pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ, new Random());
                }
            }

            if (sharedEnchants.keySet().contains(enchantmentsList[enchantments.get("IcyAura").id])) {
                List<ChunkPosition> blocks = findBlocksWithinAABB(player.worldObj, AABBUtils.getAreaBoundingBox((short) Math.round(player.posX), (short) Math.round(player.posY), (short) Math.round(player.posZ), sharedEnchants.get(enchantmentsList[enchantments.get("GaiaAura").id])/4), Arrays.asList(Blocks.lava, Blocks.flowing_lava, Blocks.water, Blocks.fire));
                for (ChunkPosition pos : blocks) {
                    if (world.getBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ) == Blocks.lava)
                        world.setBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ, Blocks.obsidian, 0, 3);
                    if (world.getBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ) == Blocks.flowing_lava)
                        world.setBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ, Blocks.stone, 0, 3);
                    if (world.getBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ) == Blocks.water)
                        world.setBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ, Blocks.ice, 0, 3);
                    if (world.getBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ) == Blocks.fire)
                        world.setBlockToAir(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ);
                }
            }

            if (sharedEnchants.keySet().contains(enchantmentsList[enchantments.get("AuraMagnetic").id])) {
                if (entity instanceof EntityItem) {
                    double motionX = player.posX - entity.posX;
                    double motionY = player.boundingBox.minY + player.height - entity.posY;
                    double motionZ = player.posZ - entity.posZ;
                    entity.setVelocity(motionX / 4, motionY / 4, motionZ / 4);
                } else if (entity instanceof EntityXPOrb) {
                    new PlayerPickupXpEvent(player, (EntityXPOrb) entity);
                }
            }
        }
    }
    //Generic Aura find block AABB
    private static List<ChunkPosition> findBlocksWithinAABB(World world, AxisAlignedBB box, List<Block> targets) {
        List<ChunkPosition> blocks = new ArrayList<>();

        for(int x = (int)box.minX; (double)x <= box.maxX; ++x) {
            for(int y = (int)box.minY; (double)y <= box.maxY; ++y) {
                for(int z = (int)box.minZ; (double)z <= box.maxZ; ++z) {
                    Block block = world.getBlock(x, y, z);
                    if(block != null && targets.contains(block))
                        blocks.add(new ChunkPosition(x, y, z));
                }
            }
        }
        return blocks;
    }
    //Specifically for Growth AOE Aura now supports any plants if can
    private static List<ChunkPosition> getCropsWithinAABB(World world, AxisAlignedBB box) {
        List<ChunkPosition> blocks = new ArrayList<>();

        for(int x = (int)box.minX; (double)x <= box.maxX; ++x) {
            for(int y = (int)box.minY; (double)y <= box.maxY; ++y) {
                for(int z = (int)box.minZ; (double)z <= box.maxZ; ++z) {
                    Block block = world.getBlock(x, y, z);
                    if(block != null && (block instanceof IGrowable || block instanceof BlockReed || block instanceof BlockCactus))
                        blocks.add(new ChunkPosition(x, y, z));
                }
            }
        }
        return blocks;
    }

    //Generic check if enchant is enabled function incase of it changing just change one part and done not go and replace all
    private static boolean isEnabled(String name) {
        return enchantments.get(name).enabled;
    }
}
