package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCEnchants.Config;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.List;
import java.util.Random;

public class AOEEventHandler
{
    int[] HelmetAura;
    int[] TorsoAura;
    int[] LeggingsAura;
    int[] BootsAura;

    /** AuraDark     [1] **/
    /** AuraDeadly   [2] **/
    /** AuraFiery    [3] **/
    /** AuraThick    [4] **/
    /** AuraToxic    [5] **/

    //ItemStacks
    ItemStack ArmourHelm = null;
    ItemStack ArmourChest = null;
    ItemStack ArmourLegs = null;
    ItemStack ArmourBoots = null;

    @SubscribeEvent
    @SuppressWarnings({"unused", "ConstantConditions", "unchecked"})
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
		if(event.entityLiving instanceof EntityPlayerMP && !(((EntityPlayerMP) event.entityLiving).getEntityWorld().isRemote)) {
            EntityPlayerMP player = (EntityPlayerMP) event.entityLiving;
            World world = player.getEntityWorld();
            Random rand = world.rand;
            ArmourHelm = player.inventory.armorItemInSlot(3);
            ArmourChest = player.inventory.armorItemInSlot(2);
            ArmourLegs = player.inventory.armorItemInSlot(1);
            ArmourBoots = player.inventory.armorItemInSlot(0);

            int[] helmEnchs = ArmourHelm.getTagCompound().getIntArray("HxCEnchant");
            int[] torsoEnchs = ArmourHelm.getTagCompound().getIntArray("HxCEnchant");
            int[] leggingEnchs = ArmourHelm.getTagCompound().getIntArray("HxCEnchant");
            int[] bootEnchs = ArmourHelm.getTagCompound().getIntArray("HxCEnchant");

            HelmetAura = new int[]{helmEnchs[1],helmEnchs[2],helmEnchs[3],helmEnchs[4],helmEnchs[5],helmEnchs[20]};
            TorsoAura = new int[]{torsoEnchs[1],torsoEnchs[2],torsoEnchs[3],torsoEnchs[4],torsoEnchs[5],torsoEnchs[20]};
            LeggingsAura = new int[]{leggingEnchs[1],leggingEnchs[2],leggingEnchs[3],leggingEnchs[4],leggingEnchs[5],leggingEnchs[20]};
            BootsAura = new int[]{bootEnchs[1],bootEnchs[2],bootEnchs[3],bootEnchs[4],bootEnchs[5],bootEnchs[20]};

//            int shroud = HelmetAura[5] + TorsoAura[5] + LeggingsAura[5] + BootsAura[5];

            if (HelmetAura[1] > 0 && TorsoAura[1] > 0 && LeggingsAura[1] > 0 && BootsAura[1] > 0){
                int level = (HelmetAura[1] + TorsoAura[1] + LeggingsAura[1] + BootsAura[1])/4;
//                double motionY = rand.nextGaussian() + 0.02D;
//                if (shroud < 1)world.spawnParticle("magicCrit", player.posX + 0.5 + rand.nextFloat(), player.posY + 0.5 + rand.nextFloat(), player.posZ + 0.5 + rand.nextFloat(), 0, motionY, 0);
                List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, getAreaBoundingBox(player.posX, player.posY, player.posZ, level));
                for (EntityLivingBase entity : (List<EntityLivingBase>)list){
                    if (entity != player && !entity.isDead && !(entity instanceof EntityAnimal)){
                        entity.addPotionEffect(new PotionEffect(Potion.wither.getId(), 100, 1, true));
                        degrade(ArmourHelm,2); degrade(ArmourChest,2);
                        degrade(ArmourLegs,2); degrade(ArmourBoots,2);
                    }
                }
            }
            if (HelmetAura[0] > 0 && TorsoAura[0] > 0 && LeggingsAura[0] > 0 && BootsAura[0] > 0){
                int level = (HelmetAura[0] + TorsoAura[0] + LeggingsAura[0] + BootsAura[0])/4;
//                double motionY = rand.nextGaussian() + 0.02D;
//                if (shroud < 1)world.spawnParticle("largesmoke", player.posX + 0.5 + rand.nextFloat(), player.posY + 0.5 + rand.nextFloat(), player.posZ + 0.5 + rand.nextFloat(), 0, motionY, 0);
                List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, getAreaBoundingBox(player.posX, player.posY, player.posZ, level));
                for (EntityLivingBase entity : (List<EntityLivingBase>)list){
                    if (entity != player && !entity.isDead && !(entity instanceof EntityAnimal)){
                        entity.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 100, 1, true));
                        entity.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 100, 1, true));
                        degrade(ArmourHelm,1); degrade(ArmourChest,1);
                        degrade(ArmourLegs,1); degrade(ArmourBoots,1);
                    }
                }
            }
            if (HelmetAura[2] > 0 && TorsoAura[2] > 0 && LeggingsAura[2] > 0 && BootsAura[2] > 0){
                int level = (HelmetAura[2] + TorsoAura[2] + LeggingsAura[2] + BootsAura[2])/4;
//                double motionY = rand.nextGaussian() + 0.02D;
//                if (shroud < 1)world.spawnParticle("flame", player.posX + 0.5 + rand.nextFloat(), player.posY + rand.nextFloat(), player.posZ + 0.5 + rand.nextFloat(), 0, motionY, 0);
                List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, getAreaBoundingBox(player.posX, player.posY, player.posZ, level));
                for (EntityLivingBase entity : (List<EntityLivingBase>)list){
                    if (entity != player && !entity.isDead && !(entity instanceof EntityAnimal)){
                        entity.setFire(100);
                        degrade(ArmourHelm,3); degrade(ArmourChest,3);
                        degrade(ArmourLegs,3); degrade(ArmourBoots,3);
                    }
                }
            }
            if (HelmetAura[3] > 0 && TorsoAura[3] > 0 && LeggingsAura[3] > 0 && BootsAura[3] > 0){
                int level = (HelmetAura[3] + TorsoAura[3] + LeggingsAura[3] + BootsAura[3])/4;
                List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, getAreaBoundingBox(player.posX, player.posY, player.posZ, level));
                for (EntityLivingBase entity : (List<EntityLivingBase>)list){
                    if (entity != player && !entity.isDead && !(entity instanceof EntityAnimal)){
                        entity.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 100, 1, true));
                        entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 100, 1, true));
                        entity.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 100, 1, true));
                        degrade(ArmourHelm,4); degrade(ArmourChest,4);
                        degrade(ArmourLegs,4); degrade(ArmourBoots,4);
                    }
                }
            }
            if (HelmetAura[4] > 0 && TorsoAura[4] > 0 && LeggingsAura[4] > 0 && BootsAura[4] > 0){
                int level = (HelmetAura[4] + TorsoAura[4] + LeggingsAura[4] + BootsAura[4])/4;
//                double motionY = rand.nextGaussian() + 0.02D;
//                if (shroud < 1)world.spawnParticle("slime", player.posX + 0.5 + rand.nextFloat(), player.posY + 0.5 + rand.nextFloat(), player.posZ + 0.5 + rand.nextFloat(), 0, motionY, 0);
                List list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, getAreaBoundingBox(player.posX, player.posY, player.posZ, level));
                for (EntityLivingBase entity : (List<EntityLivingBase>)list){
                    if (entity != player && !entity.isDead && !(entity instanceof EntityAnimal)){
                        entity.addPotionEffect(new PotionEffect(Potion.poison.getId(), 500, 1, true));
                        degrade(ArmourHelm,5); degrade(ArmourChest,5);
                        degrade(ArmourLegs,5); degrade(ArmourBoots,5);
                    }
                }
            }
        }
	}

    protected AxisAlignedBB getAreaBoundingBox(double x, double y, double z, int modifier) {
        return AxisAlignedBB.getBoundingBox(x - modifier, y - modifier, z - modifier,
        /** Indented because CDO :P **/    x + modifier, y + modifier, z + modifier);
    }

    public void degrade(ItemStack stack, int Enchantment){
        int[] enchs = stack.getTagCompound().getIntArray("HxCEnchant");
        int power = enchs[Enchantment];
        int newPow = (stack.getTagCompound().getInteger("HxCEnchantPower") - (Math.round((power * Config.baseDrain)/2)));
        stack.getTagCompound().setInteger("HxCEnchantPower",newPow);
    }
}
