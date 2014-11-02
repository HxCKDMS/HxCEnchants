package HxCKDMS.XEnchants.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

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
}
