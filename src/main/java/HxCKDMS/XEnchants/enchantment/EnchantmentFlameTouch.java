package HxCKDMS.XEnchants.enchantment;

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

	public EnchantmentFlameTouch(int id, int rarity, EnumEnchantmentType type)
	{
		super(id, rarity, type);
	}

	@Override
	public String getTranslatedName(int i)
	{
		String enchantmentName = "Flame Touch";
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
		return 2;
	}

	@Override 
	public boolean canApplyTogether(Enchantment enchantment)
    {
        if(enchantment instanceof EnchantmentLootBonus)
        {
            return false;
        }
        /*if(enchantment instanceof EnchantmentLumberjack)
        {
            return false;
        }*/
        if(enchantment instanceof EnchantmentUntouching)
        {
            return false;
        } else
        {
            return super.canApplyTogether(enchantment);
        }
    }
}