package HxCKDMS.HxCEnchants.api;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;

import java.util.LinkedHashMap;
import java.util.List;

public interface IEnchantHandler {
    void handleHelmetEnchant(EntityPlayerMP player, ItemStack helmet, LinkedHashMap<Enchantment, Integer> enchants);
    void handleChestplateEnchant(EntityPlayerMP player, ItemStack chestplate, LinkedHashMap<Enchantment, Integer> enchants);
    void handleLeggingEnchant(EntityPlayerMP player, ItemStack leggings, LinkedHashMap<Enchantment, Integer> enchants);
    void handleBootEnchant(EntityPlayerMP player, ItemStack boots, LinkedHashMap<Enchantment, Integer> enchants);
    void handleAttackEvent(EntityPlayerMP player, EntityLivingBase victim, ItemStack weapon, float damage, LivingHurtEvent event, LinkedHashMap<Enchantment, Integer> enchants);
    void handleDeathEvent(EntityPlayerMP player, EntityLivingBase victim, ItemStack weapon);
    void playerTickEvent(EntityPlayerMP player);
    void delayedPlayerTickEvent(EntityPlayerMP player);
    void handleAuraEvent(EntityPlayerMP player, List<Entity> ents, LinkedHashMap<Enchantment, Integer> sharedEnchants);
    void playerMineBlockEvent(EntityPlayer player, ItemStack tool, BlockEvent.HarvestDropsEvent event, LinkedHashMap<Enchantment, Integer> enchants);
    void playerHurtEvent(EntityPlayerMP player, DamageSource source, float amount, LivingHurtEvent event);
}
