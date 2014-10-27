package HxCKDMS.XEnchants.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.StatCollector;

public class EnchantmentVampirism extends Enchantment
{
	public EnchantmentVampirism(int id, int rarity)
	{
		super(id, rarity, EnumEnchantmentType.armor_feet);
		this.setName("vampirism");
	}

	public EnchantmentVampirism(int id, int rarity, EnumEnchantmentType type)
	{
		super(id, rarity, type);
	}

	@Override
	public String getTranslatedName(int i)
	{
		String enchantmentName = "Vampirism";
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
		return this.getMinEnchantability(i) + 30;
	}

	@Override
	public int getMaxLevel()
	{
		return 1;
	}

	/*
	@Override 
	public boolean canApplyTogether(Enchantment enchantment)
    {
        if(enchantment instanceof EnchantmentBound)
        {
            return false;
        } else
        {
            return super.canApplyTogether(enchantment);
        }
    }*/
}