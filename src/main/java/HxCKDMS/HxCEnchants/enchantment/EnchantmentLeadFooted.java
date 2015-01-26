package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentLeadFooted extends Enchantment
{
	public EnchantmentLeadFooted(int id, int rarity)
	{
		super(id, rarity, EnumEnchantmentType.armor_feet);
		this.setName("LeadFooted");
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
		return Config.enchLeadFootedLVL;
	}

}