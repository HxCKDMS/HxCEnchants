package HxCKDMS.XEnchants.enchantment;

import HxCKDMS.XEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentArrowLightning extends Enchantment
{
    public EnchantmentArrowLightning(int id, int rarity)
    {
        super(id, rarity, EnumEnchantmentType.bow);
        this.setName("godly");
    }

    @Override
    public int getMinEnchantability(int i)
    {
        return 10 + (5 * i);
    }

    @Override
    public int getMaxEnchantability(int i)
    {
        return getMinEnchantability(i) + 30;
    }

    @Override
    public int getMaxLevel()
    {
        return Config.enchArrowLightningLVL;
    }
}