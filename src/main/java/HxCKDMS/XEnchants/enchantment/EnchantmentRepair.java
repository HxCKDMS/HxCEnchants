package HxCKDMS.XEnchants.enchantment;

import HxCKDMS.XEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentRepair extends Enchantment {

    public EnchantmentRepair(int id, int weight) {
        super(id, weight, EnumEnchantmentType.all);
        this.setName("Repair");
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
        return Config.enchRepairLVL;
    }
}