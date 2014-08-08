package kay.kayXEnchants.enchantment;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.gameevent.TickEvent;
import kay.kayXEnchants.common.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public class EnchantmentWitherProtection extends Enchantment {

    public EnchantmentWitherProtection(int id, int weight) {
        super(id, weight, EnumEnchantmentType.armor_head);
        this.setName("WitherProtection");
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
        return 1;
    }

    @Mod.EventHandler
    public void onPlayerTick(TickEvent.PlayerTickEvent Event, LivingAttackEvent LEvent) {
        EntityPlayer player = Event.player;
        ItemStack helm = player.getCurrentArmor(0);
        if (helm != null) {
            int HasEnchant = EnchantmentHelper.getEnchantmentLevel(Config.enchWitherProtectionID, helm);
            if (HasEnchant > 0 && LEvent.source == DamageSource.wither && Event.player.worldObj.rand.nextInt(10) < 5)
                Event.setCanceled(true);
        }
    }
}
