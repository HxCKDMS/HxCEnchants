package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCEnchants.Configurations.Configurations;
import HxCKDMS.HxCEnchants.api.AABBUtils;
import HxCKDMS.HxCEnchants.api.EnchantingUtils;
import HxCKDMS.HxCEnchants.api.IEnchantHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import hxckdms.hxccore.libraries.GlobalVariables;
import hxckdms.hxccore.utilities.HxCPlayerInfoHandler;
import hxckdms.hxccore.utilities.TeleportHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.world.BlockEvent;

import java.util.*;

import static HxCKDMS.HxCEnchants.Configurations.Configurations.*;
import static HxCKDMS.HxCEnchants.lib.Reference.HealthUUID;
import static HxCKDMS.HxCEnchants.lib.Reference.SpeedUUID;
import static net.minecraft.enchantment.Enchantment.enchantmentsList;

@SuppressWarnings({"unchecked", "ConstantConditions", "all"})
public class EnchantHandlers implements IEnchantHandler {
    private int repairTimer = 60, regenTimer = 60, vitTimer = 600;
    private FurnaceRecipes furnaceRecipes = FurnaceRecipes.smelting();

    @Override
    public void handleHelmetEnchant(EntityPlayerMP player, ItemStack helmet, LinkedHashMap<Enchantment, Integer> enchants) {
        if (enchants.containsKey(enchantmentsList[enchantments.get("Gluttony").id])) {
            short gluttony = (short)EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("Gluttony").id, helmet);
            LinkedHashMap<Boolean, Item> tmp = hasFood(player);
            if (gluttony > 0 && !tmp.isEmpty() && player.getFoodStats().getFoodLevel() <= (gluttony / 2) + 5 && tmp.containsKey(true) && tmp.get(true) != null) {
                player.getFoodStats().addStats(((ItemFood) Items.apple).func_150905_g(new ItemStack(tmp.get(true))), ((ItemFood) Items.apple).func_150906_h(new ItemStack(tmp.get(true))));
                for (short slot = 0; slot < player.inventory.mainInventory.length; slot++) {
                    if (player.inventory.mainInventory[slot] != null && player.inventory.mainInventory[slot].getItem() instanceof ItemFood && player.inventory.mainInventory[slot].getItem() == tmp.get(true)) {
                        player.inventory.decrStackSize(slot, 1);
                        break;
                    }
                }
            }
        }
        if (enchants.containsKey(enchantmentsList[enchantments.get("Nightvision").id])) {
            short vision = (short)EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("Nightvision").id, helmet);
            if (vision > 0)
                player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 600, 1, true));
        }
    }

    @Override
    public void handleChestplateEnchant(EntityPlayerMP player, ItemStack chestplate, LinkedHashMap<Enchantment, Integer> enchants) {
        if (enchants.containsKey(enchantmentsList[enchantments.get("Vitality").id])) {
            vitTimer--;
            IAttributeInstance ph = player.getEntityAttribute(SharedMonsterAttributes.maxHealth);
            short vitalityLevel = (short) EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("Vitality").id, chestplate);
            double vitality = vitalityLevel * 0.5F;
            AttributeModifier HealthBuff = new AttributeModifier(HealthUUID, "HealthBuffedChestplate", vitality, 0);
            if (!ph.func_111122_c().contains(HealthBuff) && vitalityLevel != 0)
                ph.applyModifier(HealthBuff);
            if (ph.func_111122_c().contains(HealthBuff) && vitalityLevel == 0)
                ph.removeModifier(HealthBuff);

            if (vitTimer <= 0 && vitalityLevel > 0) {
                vitTimer = 600;
            }
        }
    }

    @Override
    public void handleLeggingEnchant(EntityPlayerMP player, ItemStack leggings, LinkedHashMap<Enchantment, Integer> enchants) {
        if (enchants.containsKey(enchantmentsList[enchantments.get("Swiftness").id])) {
            IAttributeInstance ps = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
            short speedLevel = (short) EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("Swiftness").id, leggings);
            double speedBoost = speedLevel * SpeedTweak;
            AttributeModifier SpeedBuff = new AttributeModifier(SpeedUUID, "SpeedBuffedPants", speedBoost, 0);
            if (!ps.func_111122_c().contains(SpeedBuff) && speedLevel != 0)
                ps.applyModifier(SpeedBuff);
            if (ps.func_111122_c().contains(SpeedBuff) && speedLevel == 0)
                ps.removeModifier(SpeedBuff);
        }
    }

    @Override
    public void handleBootEnchant(EntityPlayerMP player, ItemStack boots, LinkedHashMap<Enchantment, Integer> enchants) {
        NBTTagCompound c = HxCPlayerInfoHandler.getTagCompound(player, "backLocation");
        if (enchants.containsKey(enchantmentsList[enchantments.get("Fly").id]) &! c.getBoolean("flightEnc")) {
            c.setBoolean("fly", true);
            c.setBoolean("flightEnc", true);
        } else if (c.getBoolean("flightEnc")) {
            c.setBoolean("fly", false);
            c.setBoolean("flightEnc", false);
        }
        player.sendPlayerAbilities();
    }

    @Override
    public void handleDeathEvent(EntityPlayerMP player, EntityLivingBase victim, ItemStack stack) {
        short vampireLevel = (short) EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("Vampirism").id, stack);
        short examineLevel = (short) EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("Examine").id, stack);
        if (examineLevel > 0)
            if (victim instanceof EntityLiving) {
                victim.worldObj.spawnEntityInWorld(new EntityXPOrb(victim.worldObj, victim.posX, victim.posY + 1, victim.posZ, examineLevel));
            }

        if (vampireLevel > 0) {
            if (victim instanceof EntityAnimal)
                player.getFoodStats().addStats(1, 0.3F);
            else if (victim instanceof EntityPlayerMP)
                player.getFoodStats().addStats(10, 0.5F);
            else if (victim instanceof EntityVillager)
                player.getFoodStats().addStats(5, 0.5F);
            else if (victim.isEntityUndead())
                player.getFoodStats().addStats(0, 0);
            else if (victim instanceof EntitySlime)
                player.getFoodStats().addStats(1, 0.1F);
            else if (victim instanceof EntityEnderman)
                player.getFoodStats().addStats(2, 0.2F);
            else if (victim instanceof EntityMob)
                player.getFoodStats().addStats(3, 0.2F);

            else player.getFoodStats().addStats(1, 0.1F);
        }
    }

    public static void chargeItem(EntityPlayer player) {
        if (player.getHeldItem() != null && player.getHeldItem().isItemEnchanted() && player.experienceLevel > 0) {
            player.addExperienceLevel(-1);

            if (player.getHeldItem().hasTagCompound()) {
                player.getHeldItem().getTagCompound().setLong("Charge", player.getHeldItem().getTagCompound().getLong("Charge") + EnchantingUtils.xpFromLevel(player.experienceLevel));
            } else {
                NBTTagCompound tg = new NBTTagCompound();
                tg.setLong("Charge", EnchantingUtils.xpFromLevel(player.experienceLevel));
                player.getHeldItem().setTagCompound(tg);
            }
        }
    }

    public static void flash(EntityPlayerMP player) {
        if (player.getCurrentArmor(0) != null && player.getCurrentArmor(0).isItemEnchanted()) {
            int FlashLevel = EnchantmentHelper.getEnchantmentLevel((int) Configurations.enchantments.get("FlashStep").id, player.getCurrentArmor(0));
            if (FlashLevel > 0) {
                World world = player.worldObj;
                Vec3 vec3 = Vec3.createVectorHelper(player.posX, player.posY, player.posZ);
                Vec3 vec31 = player.getLook(1.0f);
                Vec3 vec32 = vec3.addVector(vec31.xCoord * 200, vec31.yCoord * 200, vec31.zCoord * 200);
                MovingObjectPosition rayTrace = GlobalVariables.server.worldServerForDimension(player.dimension).rayTraceBlocks(vec3, vec32);
                if (rayTrace != null) {
                    if (rayTrace.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK ) {
                        for (int i = 0; i < 5; i++) {
                            if (world.getBlock(rayTrace.blockX, rayTrace.blockY + i, rayTrace.blockZ) == Blocks.air) {
                                player.playerNetServerHandler.setPlayerLocation(rayTrace.blockX, rayTrace.blockY + i, rayTrace.blockZ, player.cameraYaw, player.cameraPitch);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void playerTickEvent(EntityPlayerMP player) {
        if (player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).hasTagCompound() && player.inventory.armorItemInSlot(0).isItemEnchanted() && player.motionY < -0.8 && !player.isSneaking()) {
            int tmp = 0, tmp2 = 0;
            if (isEnabled("FeatherFall"))
                tmp = EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("FeatherFall").id, player.inventory.armorItemInSlot(0));
            if (isEnabled("MeteorFall"))
                tmp2 = EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("MeteorFall").id, player.inventory.armorItemInSlot(0));

            if (tmp > 0)
                player.addVelocity(0, 0.01f * (float) tmp, 0);

            if (tmp2 > 0)
                player.addVelocity(0, 0.02f * (float) -tmp2, 0);;
        }

        if (player.inventory.armorItemInSlot(1) != null && isEnabled("Swiftness")) {
            if (player.inventory.armorItemInSlot(1) == null) {
                IAttributeInstance ps = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
                AttributeModifier SpeedBuff = new AttributeModifier(SpeedUUID, "SpeedBuffedPants", 0, 0);
                ps.removeModifier(SpeedBuff);
            }
        }

        if (player.inventory.armorItemInSlot(0) != null && isEnabled("Vitality")) {
            if (player.inventory.armorItemInSlot(2) == null) {
                IAttributeInstance ph = player.getEntityAttribute(SharedMonsterAttributes.maxHealth);
                AttributeModifier VitBuff = new AttributeModifier(HealthUUID, "HealthBuffedChestplate", 0, 0);
                ph.removeModifier(VitBuff);
            }
        }

        if (isEnabled("Fly")) {
            NBTTagCompound c = HxCPlayerInfoHandler.getTagCompound(player, "backLocation");
            if (player.inventory.armorItemInSlot(0) == null && c != null && c.hasKey("flightEnc") && c.getBoolean("flightEnc")) {
                c.setBoolean("fly", false);
                c.setBoolean("flightEnc", false);
            }
        }
        player.sendPlayerAbilities();
    }
    
    private static boolean isEnabled(String name) {
        return enchantments.get(name).enabled;
    }

    @Override
    public void delayedPlayerTickEvent(EntityPlayerMP player) {
        repairTimer--; regenTimer--;
        if (isEnabled("Repair") && repairTimer <= 0) {
            RepairItems(player);
            repairTimer = Configurations.repairTimer;
        }

        if (isEnabled("Regen") && regenTimer <= 0) {
            short H = 0, C = 0, L = 0, B = 0, rid = (short) enchantments.get("Regen").id;
            byte Regen = 0;
            ItemStack ArmourHelm = player.inventory.armorItemInSlot(3),
                    ArmourChest = player.inventory.armorItemInSlot(2),
                    ArmourLegs = player.inventory.armorItemInSlot(1),
                    ArmourBoots = player.inventory.armorItemInSlot(0);

            if (ArmourHelm != null)
                H = (short) EnchantmentHelper.getEnchantmentLevel((int)rid, player.inventory.armorItemInSlot(3));
            if (ArmourChest != null)
                C = (short) EnchantmentHelper.getEnchantmentLevel((int)rid, player.inventory.armorItemInSlot(2));
            if (ArmourLegs != null)
                L = (short) EnchantmentHelper.getEnchantmentLevel((int)rid, player.inventory.armorItemInSlot(1));
            if (ArmourBoots != null)
                B = (short) EnchantmentHelper.getEnchantmentLevel((int)rid, player.inventory.armorItemInSlot(0));

            if (H > 0) Regen += 1;
            if (B > 0) Regen += 1;
            if (C > 0) Regen += 1;
            if (L > 0) Regen += 1;

            if (player.getHealth() < player.getMaxHealth() && Regen > 0) {
                float hp = player.getMaxHealth() - player.getHealth();
                regenTimer = Configurations.regenTimer;
                player.heal(Regen);
            }
        }
        player.sendPlayerAbilities();
    }

    @Override
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
                    getMeltablesWithinAABB(world, AABBUtils.getAreaBoundingBox((int) player.posX, (int) player.posY, (int) player.posZ, sharedEnchants.get(enchantmentsList[enchantments.get("AuraFiery").id]))).forEach(meltable -> {
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
                    if (entity instanceof EntityLivingBase && (AurasAffectPlayers || !(entity instanceof EntityPlayer)) && !(entity instanceof EntityGolem) && !(entity instanceof EntityAnimal) && !((EntityLivingBase) entity).isPotionActive(Potion.poison)) {
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
                List<ChunkPosition> blocks = getFreezablesWithinAABB(player.worldObj, AABBUtils.getAreaBoundingBox((short) Math.round(player.posX), (short) Math.round(player.posY), (short) Math.round(player.posZ), sharedEnchants.get(enchantmentsList[enchantments.get("GaiaAura").id])/4));
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

    @Override
    public void handleAttackEvent(EntityPlayerMP player, EntityLivingBase victim, ItemStack weapon, float damage, LivingHurtEvent event, LinkedHashMap<Enchantment, Integer> enchants) {
        if (enchants.containsKey(enchantmentsList[enchantments.get("LifeSteal").id])) {
            player.heal(damage/10 * enchants.get(enchantmentsList[enchantments.get("LifeSteal").id]));
        }

        if (enchants.containsKey(enchantmentsList[enchantments.get("Piercing").id])) {
                victim.attackEntityFrom(new DamageSource("Piercing").setDamageBypassesArmor().setDamageAllowedInCreativeMode()
                        .setDamageIsAbsolute(), damage * (PiercingPercent * enchants.get(enchantmentsList[enchantments.get("Piercing").id])));
        }

        if (enchants.containsKey(enchantmentsList[enchantments.get("Vorpal").id])) {
            victim.attackEntityFrom(new DamageSource("Vorpal").setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute(),
                    enchants.get(enchantmentsList[enchantments.get("Vorpal").id]) * 0.35f * damage);
        }

        if (enchants.containsKey(enchantmentsList[enchantments.get("BloodRazor").id])) {
            event.ammount += (victim.getMaxHealth() * (0.05f * enchants.get(enchantmentsList[enchantments.get("BloodRazor").id])));
        }

        if (enchants.containsKey(enchantmentsList[enchantments.get("SCurse").id])) {
            int SCurseLevel = enchants.get(enchantmentsList[enchantments.get("SCurse").id]);
            victim.attackEntityFrom(new DamageSource("scurse").setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute(), damage * (0.2f * SCurseLevel));
            player.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 120 * SCurseLevel, SCurseLevel, true));
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 120, Math.round(SCurseLevel /3), true));
            player.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 120 * SCurseLevel, SCurseLevel, true));
        }

        if (enchants.containsKey(enchantmentsList[enchantments.get("VoidTouch").id])) {
            short voidLevel = (short) EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("VoidTouch").id, weapon);
            if (voidLevel > 0) {
                victim.attackEntityFrom(new DamageSource("Void").setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute(), damage * (0.2f * voidLevel));
            }
        }

        if (enchants.containsKey(enchantmentsList[enchantments.get("OverCharge").id]) && weapon.getTagCompound().getInteger("Charge") > 0) {
            int OverCharge = enchants.get(enchantmentsList[enchantments.get("OverCharge").id]);
            int storedCharge = weapon.getTagCompound().getInteger("Charge");
            if (OverCharge > 0) {
                List<EntityLivingBase> list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox((int)Math.round(player.posX), (int)Math.round(player.posY), (int)Math.round(player.posZ), OverCharge*5));
                int ndamage = (int) ((damage * 0.3f * ((list.size()) + (storedCharge/15)) / list.size()));
                list.stream().filter(liv -> liv != player).forEach(liv -> liv.attackEntityFrom(new DamageSource("OverCharge").setDamageIsAbsolute().setDamageAllowedInCreativeMode(), ndamage));
                weapon.getTagCompound().setInteger("Charge", 0);
                Map<Integer, Integer> enchs = EnchantmentHelper.getEnchantments(weapon);
                if (overChargeDecays) enchs.remove((int) enchantments.get("OverCharge").id);
                if (OverCharge > 1 && overChargeDecays) enchs.put((int) enchantments.get("OverCharge").id, OverCharge - 1);
                EnchantmentHelper.setEnchantments(enchs, weapon);
            }
        }
    }

    @Override
    public void playerMineBlockEvent(EntityPlayer player, ItemStack tool, BlockEvent.HarvestDropsEvent event, LinkedHashMap<Enchantment, Integer> enchants) {
        if (isEnabled("FlameTouch")) {
            int AutoSmeltLevel = (short)EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("FlameTouch").id, tool);
            if (AutoSmeltLevel > 0) {
                for (int i = 0; i < event.drops.size(); i++) {
                    ItemStack smelted = furnaceRecipes.getSmeltingResult(event.drops.get(i));

                    if (smelted != null) {
                        ItemStack drop = smelted.copy();
                        drop.stackSize *= AutoSmeltLevel;
                        event.drops.set(i, drop);
                    }
                }
            }
        }

        if (isEnabled("VoidTouch")) {
            short voidLevel = (short) EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("VoidTouch").id, tool);
            if (voidLevel > 0) {
                for(String block : VoidedItems)
                    event.drops.remove(new ItemStack(Block.getBlockFromName(block)));
            }
        }
    }

    @Override
    public void playerHurtEvent(EntityPlayerMP player, DamageSource source, float ammount, LivingHurtEvent event) {
        boolean allowEffect = !(source.damageType.equalsIgnoreCase("wither") ||
                source.damageType.equalsIgnoreCase("starve") ||
                source.damageType.equalsIgnoreCase("fall") ||
                source.damageType.equalsIgnoreCase("explosion.player") ||
                source.damageType.equalsIgnoreCase("explosion") ||
                source.damageType.equalsIgnoreCase("inWall") ||
                source.damageType.equalsIgnoreCase("poison"));

        ItemStack ArmourHelm = player.inventory.armorItemInSlot(3),
                ArmourChest = player.inventory.armorItemInSlot(2);

        int AdrenalineBoostLevel = 0, BattleHealingLevel = 0, WitherProt = 0, DivineInterventionLevel = 0, ExplosiveDischarge = 0;

        if (ArmourChest != null && ArmourChest.hasTagCompound() && ArmourChest.isItemEnchanted()) {
            if (isEnabled("BattleHealing"))
                BattleHealingLevel = EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("BattleHealing").id, ArmourChest);

            if (isEnabled("DivineIntervention"))
                DivineInterventionLevel = EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("DivineIntervention").id, ArmourChest);

            if (isEnabled("ExplosiveDischarge") && allowEffect)
                ExplosiveDischarge = EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("ExplosiveDischarge").id, ArmourChest);

            if (BattleHealingLevel > 0 && source.damageType.equalsIgnoreCase("generic")) {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), BattleHealingLevel * 60, BattleHealingLevel));
            }


            if (DivineInterventionLevel > 0 && player.prevHealth - ammount <= 1) {
                player.heal(5);
                int x, y, z;
                if (player.getBedLocation(0) != null) {
                    x = player.getBedLocation(0).posX;
                    y = player.getBedLocation(0).posY;
                    z = player.getBedLocation(0).posZ;
                } else {
                    ChunkCoordinates coords = GlobalVariables.server.worldServerForDimension(0).getSpawnPoint();
                    x = coords.posX;
                    y = coords.posY;
                    z = coords.posZ;
                }
                if (player.dimension != 0) TeleportHelper.teleportEntityToDimension(player, x, y, z, 0);
                else player.playerNetServerHandler.setPlayerLocation(x, y, z, 90, 0);
                Map<Integer, Integer> enchs = EnchantmentHelper.getEnchantments(ArmourChest);
                enchs.remove((int) enchantments.get("DivineIntervention").id);
                if (DivineInterventionLevel > 1) enchs.put((int) enchantments.get("DivineIntervention").id, DivineInterventionLevel - 1);
                EnchantmentHelper.setEnchantments(enchs, ArmourChest);
            }

            if (ExplosiveDischarge > 0) {
                player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, 2f * ExplosiveDischarge, false);
            }
        }

        if (ArmourHelm != null && ArmourHelm.hasTagCompound() && ArmourHelm.isItemEnchanted()) {
            if (isEnabled("AdrenalineBoost"))
                AdrenalineBoostLevel = EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("AdrenalineBoost").id, ArmourHelm);

            if (isEnabled("WitherProtection"))
                WitherProt = EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("WitherProtection").id, ArmourHelm);

            if (WitherProt > 0 && source.damageType.equalsIgnoreCase("wither")) {
                event.setCanceled(true);
            }

            if(AdrenalineBoostLevel > 0 && allowEffect) {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 60, AdrenalineBoostLevel));
                player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 60, AdrenalineBoostLevel));
            }
        }
    }

    private LinkedHashMap<Boolean, Item> hasFood(EntityPlayerMP player) {
        LinkedHashMap<Boolean, Item> meh = new LinkedHashMap<>();
        for (ItemStack item : player.inventory.mainInventory)
            if (item != null && item.getItem() instanceof ItemFood)
                meh.put(true, item.getItem());
        return meh;
    }

    private void RepairItems(EntityPlayerMP player){
        ItemStack Inv;
        ItemStack Armor;
        long tmp = 0;
        for(int j = 0; j < 36; j++){
            Inv = player.inventory.getStackInSlot(j);
            if (Inv != null && Inv.isItemStackDamageable() && Inv.hasTagCompound() && Inv.isItemEnchanted() && Inv.getMaxDamage() != Inv.getItemDamageForDisplay()){
                int a = EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("Repair").id, Inv);
                int b = Inv.getItemDamageForDisplay() - a;
                if (Inv.getItemDamageForDisplay() > 0 && tmp >= Inv.getItemDamageForDisplay()) {
                    Inv.setItemDamage(b);
                }
            }
        }
        for(int j = 0; j < 4; j++){
            Armor = player.getCurrentArmor(j);
            if (Armor != null && Armor.isItemStackDamageable() && Armor.hasTagCompound() && Armor.isItemEnchanted()){
                int c = EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("Repair").id, Armor);
                int d = Armor.getItemDamageForDisplay() - c;
                if (Armor.getItemDamageForDisplay() > 0 && (tmp >= Armor.getItemDamageForDisplay())) {
                    Armor.setItemDamage(d);
                }
            }
        }
    }

    @SubscribeEvent
    public void setAttackTarget(LivingSetAttackTargetEvent event) {
        if (event.target instanceof EntityPlayer && ((EntityPlayer) event.target).inventory != null &&
                ((EntityPlayer) event.target).inventory.armorItemInSlot(0) != null &&
                ((EntityPlayer) event.target).inventory.armorItemInSlot(0).hasTagCompound() &&
                ((EntityPlayer) event.target).inventory.armorItemInSlot(0).isItemEnchanted()) {
            int a = EnchantmentHelper.getEnchantmentLevel((int) enchantments.get("Stealth").id, ((EntityPlayer) event.target).inventory.armorItemInSlot(0));
            if (a > 0) {
                event.setCanceled(true);
            }
        }
    }

    private static ArrayList<ChunkPosition> getCropsWithinAABB(World world, AxisAlignedBB box) {
        ArrayList<ChunkPosition> crops = new ArrayList();

        for(int x = (int)box.minX; (double)x <= box.maxX; ++x) {
            for(int y = (int)box.minY; (double)y <= box.maxY; ++y) {
                for(int z = (int)box.minZ; (double)z <= box.maxZ; ++z) {
                    Block block = world.getBlock(x, y, z);
                    if(block != null && (block instanceof BlockCrops || block instanceof IGrowable || block == Blocks.cactus || block instanceof IPlantable || block == Blocks.vine))
                        crops.add(new ChunkPosition(x, y, z));
                }
            }
        }
        return crops;
    }

    private static ArrayList<ChunkPosition> getFreezablesWithinAABB(World world, AxisAlignedBB box) {
        ArrayList<ChunkPosition> blocks = new ArrayList();

        for(int x = (int)box.minX; (double)x <= box.maxX; ++x) {
            for(int y = (int)box.minY; (double)y <= box.maxY; ++y) {
                for(int z = (int)box.minZ; (double)z <= box.maxZ; ++z) {
                    Block block = world.getBlock(x, y, z);
                    if(block != null && (block == Blocks.lava || block == Blocks.flowing_lava || block == Blocks.water || block == Blocks.fire))
                        blocks.add(new ChunkPosition(x, y, z));
                }
            }
        }
        return blocks;
    }

    private static ArrayList<ChunkPosition> getMeltablesWithinAABB(World world, AxisAlignedBB box) {
        ArrayList<ChunkPosition> blocks = new ArrayList();

        for(int x = (int)box.minX; (double)x <= box.maxX; ++x) {
            for(int y = (int)box.minY; (double)y <= box.maxY; ++y) {
                for(int z = (int)box.minZ; (double)z <= box.maxZ; ++z) {
                    Block block = world.getBlock(x, y, z);
                    if(block != null && (block == Blocks.ice || block == Blocks.snow || block == Blocks.snow_layer))
                        blocks.add(new ChunkPosition(x, y, z));
                }
            }
        }
        return blocks;
    }
}
