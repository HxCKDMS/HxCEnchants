package HxCKDMS.XEnchants.enchantment;

import HxCKDMS.XEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentFlameTouch extends Enchantment
{
	public EnchantmentFlameTouch(int id, int rarity)
	{
		super(id, rarity, EnumEnchantmentType.digger);
		this.setName("flameTouch");
	}
	@Override
	public int getMinEnchantability(int i)
	{
		return i + 15;
	}

	@Override
	public int getMaxEnchantability(int i)
	{
		return getMinEnchantability(i) + 20;
	}

	@Override
	public int getMaxLevel()
	{
		return Config.enchFlameTouchLVL;
	}

}