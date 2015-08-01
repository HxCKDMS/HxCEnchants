package HxCKDMS.HxCEnchants.Handlers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.world.BlockEvent;

public interface IEnchantHandler {
    void handleHelmetEnchant(EntityPlayerMP player, ItemStack helmet, long itemCharge);
    void handleChestplateEnchant(EntityPlayerMP player, ItemStack chestplate, long itemCharge);
    void handleLeggingEnchant(EntityPlayerMP player, ItemStack leggings, long itemCharge);
    void handleBootEnchant(EntityPlayerMP player, ItemStack boots, long itemCharge);
    void handleAttackEvent(EntityPlayerMP player, EntityLivingBase victim, ItemStack weapon, float damage, long itemCharge);
    void handleDeathEvent(EntityPlayerMP player, EntityLivingBase victim, ItemStack weapon, long itemCharge);
    void playerTickEvent(EntityPlayerMP player);
    void delayedPlayerTickEvent(EntityPlayerMP player);
    void playerMineBlockEvent(EntityPlayer player, ItemStack tool, long itemCharge, BlockEvent.HarvestDropsEvent event);
    void playerHurtEvent(EntityPlayerMP player, DamageSource source, float amount);
}
