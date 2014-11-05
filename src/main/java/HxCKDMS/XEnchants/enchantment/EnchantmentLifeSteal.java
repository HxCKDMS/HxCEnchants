package HxCKDMS.XEnchants.enchantment;

import HxCKDMS.XEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentLifeSteal extends Enchantment {

    public EnchantmentLifeSteal(int id, int weight) {
        super(id, weight, EnumEnchantmentType.weapon);
        this.setName("LifeSteal");
    }

    @Override
    public int getMinEnchantability(int par1)
    {
        return 10;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 40;
    }

    @Override
    public int getMaxLevel()
    {
        return Config.enchLifeStealLVL;
    }
}