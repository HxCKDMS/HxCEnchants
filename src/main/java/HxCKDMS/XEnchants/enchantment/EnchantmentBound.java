package HxCKDMS.XEnchants.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.StatCollector;

public class EnchantmentBound extends Enchantment
{
	public EnchantmentBound(int id, int rarity)
	{
		super(id, rarity, EnumEnchantmentType.all);
		this.setName("bound");
	}

	public EnchantmentBound(int id, int rarity, EnumEnchantmentType type)
	{
		super(id, rarity, type);
	}

	@Override
	public String getTranslatedName(int i)
	{
		String enchantmentName = "Binding";
		return enchantmentName + " " + StatCollector.translateToLocal("enchantment.level." + i);
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
		return 4;
	}

	@Override 
	public boolean canApplyTogether(Enchantment enchantment)
    {
        if(enchantment instanceof EnchantmentVampirism)
        {
            return false;
        } else
        {
            return super.canApplyTogether(enchantment);
        }
    }
}