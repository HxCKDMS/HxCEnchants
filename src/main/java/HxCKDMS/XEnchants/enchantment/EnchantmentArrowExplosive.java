package HxCKDMS.XEnchants.enchantment;

import HxCKDMS.XEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentArrowExplosive extends Enchantment 
{
	public EnchantmentArrowExplosive(int id, int rarity)
	{
		super(id, rarity, EnumEnchantmentType.bow);
		this.setName("arrowExplosive");
	}

	@Override
	public int getMinEnchantability(int i)
	{
		return 5 + (12 + (i - 1));
	}

	@Override
	public int getMaxEnchantability(int i)
	{
		return getMinEnchantability(i) + 20;
	}

	@Override
	public int getMaxLevel()
	{
		return Config.enchArrowExplosiveLVL;
	}
}