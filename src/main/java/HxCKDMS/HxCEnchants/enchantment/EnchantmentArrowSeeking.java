package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentArrowSeeking extends Enchantment 
{
	public EnchantmentArrowSeeking(int id, int rarity)
	{
		super(id, rarity, EnumEnchantmentType.bow);
		this.setName("arrowSeeking");
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
		return Config.enchArrowSeekingLVL;
	}
}