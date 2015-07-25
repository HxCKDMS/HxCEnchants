package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCCore.api.Utils.AABBUtils;
import HxCKDMS.HxCEnchants.Config;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class AOEEventHandler {
    //if you're wondering the reason the size is 16 not 4 it's because incase other mods extend the armour inventory I did to prevent crashes

    int[] DeadlyAura = new int[16], FieryAura = new int[16],
            ToxicAura = new int[16], ThickAura = new int[16],
            DarkAura = new int[16];

    int deadid, darkid,
            fireid, toxid,
            thickid, shid, Shroud;

    public void init(){
        if (Config.enchAuraDeadlyEnable) deadid = Enchants.AuraDeadly.effectId;
        if (Config.enchAuraDarkEnable) darkid = Enchants.AuraDark.effectId;
        if (Config.enchAuraFieryEnable) fireid = Enchants.AuraFiery.effectId;
        if (Config.enchAuraToxicEnable) toxid = Enchants.AuraToxic.effectId;
        if (Config.enchAuraThickEnable) thickid = Enchants.AuraThick.effectId;
        if (Config.enchShroudEnable) shid = Enchants.Shroud.effectId;
    }

    @SubscribeEvent
    @SuppressWarnings({"unused", "ConstantConditions", "unchecked"})
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
		if (event.entityLiving instanceof EntityPlayerMP && !event.entityLiving.getEntityWorld().isRemote) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            World world = player.getEntityWorld();

            int slot = 0;
            for (ItemStack stack : player.inventory.armorInventory) {
                if (Config.enchAuraDeadlyEnable) DeadlyAura[slot] = EnchantmentHelper.getEnchantmentLevel(deadid, stack);
                if (Config.enchAuraDarkEnable) DarkAura[slot] = EnchantmentHelper.getEnchantmentLevel(darkid, stack);
                if (Config.enchAuraFieryEnable) FieryAura[slot] = EnchantmentHelper.getEnchantmentLevel(fireid, stack);
                if (Config.enchAuraThickEnable) ThickAura[slot] = EnchantmentHelper.getEnchantmentLevel(thickid, stack);
                if (Config.enchAuraToxicEnable) ToxicAura[slot] = EnchantmentHelper.getEnchantmentLevel(toxid, stack);
                if (Config.enchShroudEnable) Shroud += EnchantmentHelper.getEnchantmentLevel(shid, stack);
                slot++;
            }

            if (DeadlyAura[0] > 0 && DeadlyAura[1] > 0 && DeadlyAura[2] > 0 && DeadlyAura[3] > 0){
                int level = (DeadlyAura[0] + DeadlyAura[1] + DeadlyAura[2] + DeadlyAura[3])/4;
                double motionY = world.rand.nextGaussian() + 0.02D;
                if (Shroud < 1)world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, player.posX + 0.5 + world.rand.nextFloat(), player.posY + 0.5 + world.rand.nextFloat(), player.posZ + 0.5 + world.rand.nextFloat(), 0, motionY, 0);
                List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox(player.serverPosX, player.serverPosY, player.serverPosZ, level));
                for (EntityLivingBase entity : (List<EntityLivingBase>)list){
                    if (entity != player && !entity.isDead && !(entity instanceof EntityAnimal))
                        entity.addPotionEffect(new PotionEffect(Potion.wither.getId(), 100, 1, true, false));
                }
            }
            if (DarkAura[0] > 0 && DarkAura[1] > 0 && DarkAura[2] > 0 && DarkAura[3] > 0){
                int level = (DarkAura[0] + DarkAura[1] + DarkAura[2] + DarkAura[3])/4;
                double motionY = world.rand.nextGaussian() + 0.02D;
//                if (Shroud < 1)world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX + 0.5 + world.rand.nextFloat(), player.posY + 0.5 + world.rand.nextFloat(), player.posZ + 0.5 + world.rand.nextFloat(), 0, motionY, 0);
                List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox(player.serverPosX, player.serverPosY, player.serverPosZ, level));
                for (EntityLivingBase entity : (List<EntityLivingBase>)list){
                    if (entity != player && !entity.isDead && !(entity instanceof EntityAnimal)){
                        entity.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 100, 1, true, false));
                        entity.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 100, 1, true, false));
                    }
                }
            }
            if (FieryAura[0] > 0 && FieryAura[1] > 0 && FieryAura[2] > 0 && FieryAura[3] > 0){
                int level = (FieryAura[0] + FieryAura[1] + FieryAura[2] + FieryAura[3])/4;
                double motionY = world.rand.nextGaussian() + 0.02D;
//                if (Shroud < 1)world.spawnParticle(EnumParticleTypes.FLAME, player.posX + 0.5 + world.rand.nextFloat(), player.posY + world.rand.nextFloat(), player.posZ + 0.5 + world.rand.nextFloat(), 0, motionY, 0);
                List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox(player.serverPosX, player.serverPosY, player.serverPosZ, level));
                for (EntityLivingBase entity : (List<EntityLivingBase>)list){
                    if (entity != player && !entity.isDead && !(entity instanceof EntityAnimal)){
                        entity.setFire(100);
                    }
                }
            }
            if (ThickAura[0] > 0 && ThickAura[1] > 0 && ThickAura[2] > 0 && ThickAura[3] > 0){
                int level = (ThickAura[0] + ThickAura[1] + ThickAura[2] + ThickAura[3])/4;
                List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox(player.serverPosX, player.serverPosY, player.serverPosZ, level));
                for (EntityLivingBase entity : (List<EntityLivingBase>)list){
                    if (entity != player && !entity.isDead && !(entity instanceof EntityAnimal)){
                        entity.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 100, 1, true, false));
                        entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 100, 1, true, false));
                        entity.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 100, 1, true, false));
                    }
                }
            }
            if (ToxicAura[0] > 0 && ToxicAura[1] > 0 && ToxicAura[2] > 0 && ToxicAura[3] > 0){
                int level = (ToxicAura[0] + ToxicAura[1] + ToxicAura[2] + ToxicAura[3])/4;
                double motionY = world.rand.nextGaussian() + 0.02D;
//                if (Shroud < 1)world.spawnParticle(EnumParticleTypes.SLIME, player.posX + 0.5 + world.rand.nextFloat(), player.posY + 0.5 + world.rand.nextFloat(), player.posZ + 0.5 + world.rand.nextFloat(), 0, motionY, 0);
                List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AABBUtils.getAreaBoundingBox(player.serverPosX, player.serverPosY, player.serverPosZ, level));
                for (EntityLivingBase entity : (List<EntityLivingBase>)list){
                    if (entity != player && !entity.isDead && !(entity instanceof EntityAnimal)){
                        entity.addPotionEffect(new PotionEffect(Potion.poison.getId(), 500, 1, true, false));
                    }
                }
            }
        }
	}
}
