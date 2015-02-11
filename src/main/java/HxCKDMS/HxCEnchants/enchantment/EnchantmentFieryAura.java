package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;


public class EnchantmentFieryAura extends Enchantment {

    public EnchantmentFieryAura(int id, int weight) {
        super(id, weight, EnumEnchantmentType.armor);
        this.setName("FieryAura");
    }
    
    @Override
    public int getMinEnchantability(int par1)
    {
        return 20;
    }
    
    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 50;
    }

    @Override
    public int getMaxLevel()
    {
        return Config.enchAuraFieryLVL;
    }
}