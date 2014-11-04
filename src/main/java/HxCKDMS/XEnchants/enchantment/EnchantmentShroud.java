package HxCKDMS.XEnchants.enchantment;

import HxCKDMS.XEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentShroud extends Enchantment {

    public EnchantmentShroud(int Id, int Weight)
    {
        super(Id, Weight, EnumEnchantmentType.armor);
        this.setName("Shrouded");
    }
    @Override
    public int getMinEnchantability(int par1)
    {
        return 10;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 40;
    }

    @Override
    public int getMaxLevel()
    {
        return Config.enchShroudLVL;
    }

}