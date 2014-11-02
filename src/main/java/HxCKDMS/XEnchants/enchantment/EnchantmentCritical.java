package HxCKDMS.XEnchants.enchantment;

import HxCKDMS.XEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.StatCollector;

public class EnchantmentCritical extends Enchantment
{
    public EnchantmentCritical(int id, int weight)
    {
        super(id, weight, EnumEnchantmentType.weapon);
        setName("critical");
    }

    @Override
	public String getTranslatedName(int i)
	{
		String enchantmentName = "Critical";
		return enchantmentName + " " + StatCollector.translateToLocal("enchantment.level." + i);
	}

    @Override
    public int getMinEnchantability(int i)
    {
        return 11 + (i - 1);
    }

    @Override
    public int getMaxEnchantability(int i)
    {
        return getMinEnchantability(i) + 20;
    }

    @Override
    public int getMaxLevel()
    {
        return Config.enchCriticalLVL;
    }
}