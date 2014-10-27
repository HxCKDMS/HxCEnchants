package HxCKDMS.XEnchants.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.StatCollector;

public class EnchantmentPoison extends Enchantment
{
	public EnchantmentPoison(int id, int weight)
	{
		super(id, weight, EnumEnchantmentType.weapon);
		setName("poison");
	}
	
	@Override
	public String getTranslatedName(int i)
	{
		String enchantmentName = "Poison";
		return enchantmentName + " " + StatCollector.translateToLocal("enchantment.level." + i);
	}

	@Override
	public int getMinEnchantability(int i)
	{
		return 15 + (i - 1);
	}

	@Override
	public int getMaxEnchantability(int i)
	{
		return getMinEnchantability(i) + 20;
	}

	@Override
	public int getMaxLevel()
	{
		return 5;
	}
}