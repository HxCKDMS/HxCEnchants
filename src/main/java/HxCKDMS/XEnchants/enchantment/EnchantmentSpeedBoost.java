package HxCKDMS.XEnchants.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.StatCollector;

public class EnchantmentSpeedBoost extends Enchantment
{
	public EnchantmentSpeedBoost(int id, int rarity)
	{
		super(id, rarity, EnumEnchantmentType.armor_feet);
		this.setName("speedBoost");
	}

	public EnchantmentSpeedBoost(int id, int rarity, EnumEnchantmentType type)
	{
		super(id, rarity, type);
	}

	@Override
	public String getTranslatedName(int i)
	{
		String enchantmentName = "Swiftness";
		return enchantmentName + " " + StatCollector.translateToLocal("enchantment.level." + i);
	}

	@Override
	public int getMinEnchantability(int i)
	{
		return 5 + (i - 1) * 10;
	}

	@Override
	public int getMaxEnchantability(int i)
	{
		return this.getMinEnchantability(i) + 20;
	}

	@Override
	public int getMaxLevel()
	{
		return 3;
	}
}