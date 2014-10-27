package HxCKDMS.XEnchants.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.StatCollector;

public class EnchantmentLeadFooted extends Enchantment
{
	public EnchantmentLeadFooted(int id, int rarity)
	{
		super(id, rarity, EnumEnchantmentType.armor_feet);
		this.setName("Lead Footed");
	}
	
	public EnchantmentLeadFooted(int id, int rarity, EnumEnchantmentType type)
	{
		super(id, rarity, type);
	}
	
	@Override
	public String getTranslatedName(int i)
	{
		String enchantmentName = "Lead Footed";
		return enchantmentName + " " + StatCollector.translateToLocal("enchantment.level." + i);
	}

	@Override
	public int getMinEnchantability(int i)
	{
		return 15;
	}

	@Override
	public int getMaxEnchantability(int i)
	{
		return i + 20;
	}

	@Override
	public int getMaxLevel()
	{
		return 4;
	}

	@Override
	public boolean canApplyTogether(Enchantment enchantment)
	{
		if(enchantment instanceof EnchantmentProtection)
		{
			if(((EnchantmentProtection)enchantment).protectionType == 2)
			{
				return false;
			} else
			{
				return super.canApplyTogether(enchantment);
			}
		}
		if(enchantment instanceof EnchantmentJumpBoost)
		{
			return false;
		} else
		{
			return super.canApplyTogether(enchantment);
		}
	}
}