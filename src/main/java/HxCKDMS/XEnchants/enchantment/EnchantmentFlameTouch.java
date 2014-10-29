package HxCKDMS.XEnchants.enchantment;

import HxCKDMS.XEnchants.common.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLootBonus;
import net.minecraft.enchantment.EnchantmentUntouching;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.StatCollector;

public class EnchantmentFlameTouch extends Enchantment
{
	public EnchantmentFlameTouch(int id, int rarity)
	{
		super(id, rarity, EnumEnchantmentType.digger);
		this.setName("flameTouch");
	}

	@Override
	public String getTranslatedName(int i)
	{
		String enchantmentName = "Auto-Smelt";
		return enchantmentName + " " + StatCollector.translateToLocal("enchantment.level." + i);
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