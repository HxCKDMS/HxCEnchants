package HxCKDMS.XEnchants.enchantment;

import HxCKDMS.XEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentVampirism extends Enchantment
{
	public EnchantmentVampirism(int id, int rarity)
	{
		super(id, rarity, EnumEnchantmentType.weapon);
		this.setName("vampirism");
	}

	@Override
	public int getMinEnchantability(int i)
	{
		return 15;
	}

	@Override
	public int getMaxEnchantability(int i)
	{
		return this.getMinEnchantability(i) + 30;
	}

	@Override
	public int getMaxLevel()
	{
		return Config.enchVampirismLVL;
	}
}