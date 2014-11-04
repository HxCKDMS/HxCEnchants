package HxCKDMS.XEnchants.enchantment;

import HxCKDMS.XEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentStealth extends Enchantment {

    public EnchantmentStealth(int Id, int Weight)
    {
        super(Id, Weight, EnumEnchantmentType.armor_feet);
        this.setName("Stealth");
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
        return Config.enchStealthLVL;
    }

}