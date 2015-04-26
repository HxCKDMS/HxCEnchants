package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCCore.Handlers.NBTFileIO;
import HxCKDMS.HxCCore.HxCCore;
import HxCKDMS.HxCEnchants.Config;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class ArmorEventHandler
{
    NBTTagCompound mew = new NBTTagCompound();

    //UUIDs for Attributes
    public static UUID HealthUUID = UUID.fromString("fe15f490-62d7-11e4-b116-123b93f75cba");
    public static UUID SpeedUUID = UUID.fromString("fe15f828-62d7-11e4-b116-123b93f75cba");
    public static UUID StealthUUID = UUID.fromString("1e4a1a12-ab1e-4987-b527-e0adeefc904a");

    // Integers
    int ShouldRepair = (Config.enchRepairRate * 20);
    int CanRegen = (Config.enchRegenRate * 20);

    int[] Helm;
    int[] Torso;
    int[] Legging;
    int[] Boot;

    //doubles
    double SpeedBoost;
    double Vitality;
//    double Shroud;

    //ItemStacks
    ItemStack ArmourHelm = null;
    ItemStack ArmourChest = null;
    ItemStack ArmourLegs = null;
    ItemStack ArmourBoots = null;

    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
		if(event.entityLiving instanceof EntityPlayerMP) {
            mew.setIntArray("HxCEnchants", new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
            EntityPlayerMP player = (EntityPlayerMP) event.entityLiving;

            String UUID = player.getUniqueID().toString();
            File CustomPlayerData = new File(HxCCore.HxCCoreDir, "HxC-" + UUID + ".dat");
            NBTTagCompound xe = NBTFileIO.getNbtTagCompound(CustomPlayerData, "xenchants");

            IAttributeInstance ph = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth);
            IAttributeInstance ps = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed);

            AttributeModifier HealthBuff = new AttributeModifier(HealthUUID, "HealthBuffedChestplate", Vitality, 1);
            AttributeModifier SpeedBuff = new AttributeModifier(SpeedUUID, "SpeedBuffedPants", SpeedBoost, 1);

            ph.removeModifier(HealthBuff);
            ps.removeModifier(SpeedBuff);

            ArmourHelm = player.inventory.armorItemInSlot(3);
            ArmourChest = player.inventory.armorItemInSlot(2);
            ArmourLegs = player.inventory.armorItemInSlot(1);
            ArmourBoots = player.inventory.armorItemInSlot(0);
            Helm = mew.getIntArray("HxCEnchants");
            Torso = mew.getIntArray("HxCEnchants");
            Legging = mew.getIntArray("HxCEnchants");
            Boot = mew.getIntArray("HxCEnchants");
            if (ArmourHelm != null) {
                try {Helm = ArmourHelm.getTagCompound().getIntArray("HxCEnchants");}
                catch (Exception ignored) {ArmourHelm.setTagCompound(mew);}
            }
            if (ArmourChest != null) {
                try {Torso = ArmourChest.getTagCompound().getIntArray("HxCEnchants");}
                catch (Exception ignored) {ArmourChest.setTagCompound(mew);}
            }
            if (ArmourLegs != null) {
                try {Legging = ArmourLegs.getTagCompound().getIntArray("HxCEnchants");}
                catch (Exception ignored) {ArmourLegs.setTagCompound(mew);}
            }
            if (ArmourBoots != null) {
                try {Boot = ArmourBoots.getTagCompound().getIntArray("HxCEnchants");}
                catch (Exception ignored) {ArmourBoots.setTagCompound(mew);}
            }
            int Regen = (Helm[7] + Torso[7] + Legging[7] + Boot[7]);
            Vitality = Torso[25] * 0.5F;
            SpeedBoost = Legging[22] * 0.2;

            if (Helm[19] + Torso[19] + Legging[19] + Boot[19] > 0) ShouldRepair--;
            if (Regen > 0) CanRegen--;

            int FE = xe.getInteger("FHBE");
            if (Boot[14] > 0 && FE < 1){
                player.capabilities.allowFlying = true;
                player.sendPlayerAbilities();
                xe.setInteger("FHBE", 1);
            }
            if (Boot[14] < 1 && FE >= 1) {
                player.capabilities.isFlying = false;
                player.capabilities.allowFlying = false;
                xe.setInteger("FHBE", 0);
            }

            if (player.capabilities.isFlying && Boot[14] > 0 && !player.capabilities.isCreativeMode) {
                player.worldObj.spawnParticle("smoke", player.posX + Math.random() - 0.5d, player.posY - 1.62d, player.posZ + Math.random() - 0.5d, 0.0d, 0.0d, 0.0d);
                degrade(ArmourBoots,14);
            }

            if(Torso[25] > 0) {
                ph.applyModifier(HealthBuff);
                degrade(ArmourChest,25);
            }

            if(Legging[22] > 0 && !player.isSneaking() && !player.isRiding()) ps.applyModifier(SpeedBuff);

            if (player.getHealth() < player.getMaxHealth() && Regen > 0 && CanRegen <= 0) {
                player.heal(Regen * 2);
                CanRegen = Config.enchRegenRate * 20;
            }

            if(ShouldRepair <= 0) {
                RepairItems(player);
                ShouldRepair = (Config.enchRepairRate * 20);
            }
            int StealthLevel = (Helm[23] + Torso[23] + Legging[23] + Boot[23]);
            if (!player.worldObj.isRemote && StealthLevel > 0){
                Stealth(player, StealthLevel);
            }
        }
	}

    public void RepairItems(EntityPlayerMP player){
        ItemStack Inv;
        ItemStack Armor;
        for(int j = 0; j < 36; j++){
            Inv = player.inventory.getStackInSlot(j);
            if (Inv != null && Inv.isItemStackDamageable()){
                int a = HxCEnchantHelper.getEnchantLevel(Inv,19);
                int b = Inv.getItemDamage() - a;
                if (Inv.getItemDamage() < Inv.getMaxDamage())
                {
                    Inv.setItemDamage(b);
                    degrade(Inv, 19);
                }
            }
        }
        for(int j = 0; j < 4; j++){
            Armor = player.getCurrentArmor(j);
            if (Armor != null && Armor.isItemStackDamageable()){
                int c = HxCEnchantHelper.getEnchantLevel(Armor, 19);
                int d = Armor.getItemDamage() - c;
                if (Armor.getItemDamage() < Armor.getMaxDamage())
                {
                    Armor.setItemDamage(d);
                    degrade(Armor,19);
                }
            }
        }
    }

    @SubscribeEvent
    public void livingHurtEvent(LivingHurtEvent event)
    {
        if (event.entity instanceof EntityPlayerMP)
        {
            EntityPlayerMP player = (EntityPlayerMP)event.entityLiving;
            boolean allowABEffect = true;

            if (event.source.damageType.equalsIgnoreCase("wither") || event.source.damageType.equalsIgnoreCase("starve") ||
                event.source.damageType.equalsIgnoreCase("fall") || event.source.damageType.equalsIgnoreCase("explosion.player") ||
                event.source.damageType.equalsIgnoreCase("explosion") || event.source.damageType.equalsIgnoreCase("inWall"))
                allowABEffect = false;

            if(Helm[26] > 0 && event.source.damageType.equalsIgnoreCase("wither")) {
                player.removePotionEffect(Potion.wither.getId());
                degrade(ArmourHelm, 26);
            }
            if(Torso[12] > 0 && event.source.damageType.equalsIgnoreCase("generic")) {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), Torso[12] * 60, Torso[12]));
                degrade(ArmourChest,12);
            }

            if(Helm[0] > 0 && allowABEffect) {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 60, Helm[0]));
                player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 60, Helm[0]));
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 60, Helm[0]));
                player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 60, Helm[0]));
                player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 60, Helm[0]));
                degrade(ArmourHelm,0);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void Stealth (EntityPlayer player, int StealthLevel) {
        int px = Math.round((float)player.posX); int py = Math.round((float)player.posY); int pz = Math.round((float) player.posZ);
        List list  = player.worldObj.getEntitiesWithinAABB(EntityMob.class, getAreaBoundingBox(px, py, pz));
        for (EntityMob entity : (List<EntityMob>) list) {
            IAttributeInstance fr = entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.followRange);
            AttributeModifier StealthBuff = new AttributeModifier(StealthUUID, "StealthDeBuff", (StealthLevel*-15), 1);
            fr.removeModifier(StealthBuff);
            fr.applyModifier(StealthBuff);
            degrade(ArmourHelm, 23);
            degrade(ArmourChest, 23);
            degrade(ArmourLegs, 23);
            degrade(ArmourBoots, 23);
        }
    }

    protected AxisAlignedBB getAreaBoundingBox(float x, float y, float z) {
        return AxisAlignedBB.getBoundingBox(x - 24, y - 24, z - 24,
        /** Indented because CDO :P **/    x + 24, y + 24, z + 24);
    }

	@SubscribeEvent
	public void livingJumpEvent(LivingJumpEvent event) {
		if(event.entityLiving instanceof EntityPlayer && Legging[15] > 0) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
            double JumpBuff = player.motionY + 0.1 * Legging[15];
            player.motionY += JumpBuff;
            degrade(ArmourLegs,15);
		}
	}

    public void degrade(ItemStack stack, int Enchantment){
        int[] enchs = stack.getTagCompound().getIntArray("HxCEnchants");
        int power = enchs[Enchantment];
        int newPow = (stack.getTagCompound().getInteger("HxCEnchantCharge") - (power * Config.baseDrain));
        stack.getTagCompound().setInteger("HxCEnchantCharge",newPow);
    }
}
