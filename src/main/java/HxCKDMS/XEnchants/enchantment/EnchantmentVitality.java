package HxCKDMS.XEnchants.enchantment;

import HxCKDMS.XEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentVitality extends Enchantment {

    public EnchantmentVitality(int id, int weight) {
        super(id, weight, EnumEnchantmentType.armor_torso);
        this.setName("Vitality");
        addToBookList(this);
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
        return Config.enchVitalityLVL;
    }
}
