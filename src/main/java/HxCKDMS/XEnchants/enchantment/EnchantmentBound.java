package HxCKDMS.XEnchants.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentBound extends Enchantment
{
	public EnchantmentBound(int id, int rarity)
	{
		super(id, rarity, EnumEnchantmentType.all);
		this.setName("bound");
	}
	@Override
	public int getMinEnchantability(int i)
	{
		return 5 * i;
	}

	@Override
	public int getMaxEnchantability(int i)
	{
		return this.getMinEnchantability(i) + 30;
	}

	@Override
	public int getMaxLevel()
	{
		return 1;
	}
}