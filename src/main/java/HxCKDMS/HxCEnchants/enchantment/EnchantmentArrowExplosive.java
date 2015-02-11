package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCEnchants.Config;
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
	public int getMinEnchantability(int par1)
	{
		return 25;
	}

	@Override
	public int getMaxEnchantability(int par1)
	{
		return getMinEnchantability(par1) + 50;
	}

	@Override
	public int getMaxLevel()
	{
		return Config.enchArrowExplosiveLVL;
	}
}