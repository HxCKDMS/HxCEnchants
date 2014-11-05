package HxCKDMS.XEnchants.enchantment;

import HxCKDMS.XEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

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
}
