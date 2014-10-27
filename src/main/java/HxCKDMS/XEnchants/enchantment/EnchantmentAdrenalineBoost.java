package HxCKDMS.XEnchants.enchantment;

import HxCKDMS.XEnchants.common.Config;
import cpw.mods.fml.common.Mod;

import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;


public class EnchantmentAdrenalineBoost extends Enchantment {

    public EnchantmentAdrenalineBoost(int id, int weight) {
        super(id, weight, EnumEnchantmentType.armor_head);
        this.setName("AdrenalineBoost");
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
        return Config.enchAdrenalineBoostLVL;
    }

    public boolean canApply(ItemStack fTest)
    {
        if(fTest.getItem() instanceof ItemArmor)
        {
            ItemArmor itemArmor = (ItemArmor)fTest.getItem();
            if(itemArmor.armorType == 2)
            {
                return true;
            }
        }
        return false;
    }
    @Mod.EventHandler
    public void onPlayerTick(TickEvent.PlayerTickEvent Event, LivingAttackEvent LEvent) {
        EntityPlayer player = Event.player;
        ItemStack helm = player.getCurrentArmor(0);
        if (helm != null) {
            int HasEnchant = EnchantmentHelper.getEnchantmentLevel(Config.enchAdrenalineBoostID, helm);
            if (HasEnchant > 0 && LEvent.source != DamageSource.outOfWorld && LEvent.source != DamageSource.cactus && LEvent.source != DamageSource.anvil && LEvent.source != DamageSource.fallingBlock && LEvent.source != DamageSource.drown && Event.player.worldObj.rand.nextInt(10) < 5)
                    player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 30, HasEnchant));
                    player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 30, HasEnchant));
                    player.addPotionEffect(new PotionEffect(Potion.jump.id, 30, HasEnchant));
                Event.setCanceled(true);
        }
    }
}