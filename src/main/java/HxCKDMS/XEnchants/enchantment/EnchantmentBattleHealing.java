package HxCKDMS.XEnchants.enchantment;

import HxCKDMS.XEnchants.common.Config;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.gameevent.TickEvent;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
public class EnchantmentBattleHealing extends Enchantment {

    public EnchantmentBattleHealing(int id, int weight) {
        super(id, weight, EnumEnchantmentType.armor_torso);
        this.setName("BattleHealing");
    }
    
    @Override
    public int getMinEnchantability(int par1)
    {
        return 15;
    }
    
    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 50;
    }

    @Override
    public int getMaxLevel()
    {
        return Config.enchBattleHealingLVL;
    }

    @Mod.EventHandler
    public void onPlayerTick(TickEvent.PlayerTickEvent Event, LivingAttackEvent LEvent) {
        EntityPlayer player = Event.player;
        ItemStack chest = player.getCurrentArmor(1);
        if (chest != null) {
            int HasEnchant = EnchantmentHelper.getEnchantmentLevel(Config.enchBattleHealingID, chest);
            if (LEvent.source != DamageSource.outOfWorld && LEvent.source != DamageSource.anvil && LEvent.source != DamageSource.cactus && LEvent.source != DamageSource.magic && HasEnchant > 0 && Event.player.worldObj.rand.nextInt(10) < 5)
                player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 30, HasEnchant));
                player.addPotionEffect(new PotionEffect(Potion.resistance.id, 30, HasEnchant));
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 30, HasEnchant));
                Event.setCanceled(true);
        }
    }
}
