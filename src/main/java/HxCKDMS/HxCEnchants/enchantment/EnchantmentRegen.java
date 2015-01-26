package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentRegen extends Enchantment
{
	public EnchantmentRegen(int id, int rarity)
	{
		super(id, rarity, EnumEnchantmentType.armor);
		this.setName("Regen");
	}

	@Override
	public int getMinEnchantability(int i)
	{
		return 20 + (i - 1);
	}

	@Override
	public int getMaxEnchantability(int i)
	{
		return this.getMinEnchantability(i) + 20;
	}

	@Override
	public int getMaxLevel()
	{
		return Config.enchRegenLVL;
	}
}