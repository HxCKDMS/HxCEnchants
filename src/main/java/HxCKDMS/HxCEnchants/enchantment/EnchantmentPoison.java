package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentPoison extends Enchantment
{
	public EnchantmentPoison(int id, int weight)
	{
		super(id, weight, EnumEnchantmentType.bow);
		setName("poison");
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
		return Config.enchPoisonLVL;
	}
}