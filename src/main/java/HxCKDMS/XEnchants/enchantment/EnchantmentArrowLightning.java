package HxCKDMS.XEnchants.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentArrowFire;
import net.minecraft.enchantment.EnchantmentArrowKnockback;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.StatCollector;

public class EnchantmentArrowLightning extends Enchantment
{
    public EnchantmentArrowLightning(int id, int rarity)
    {
        super(id, rarity, EnumEnchantmentType.bow);
        this.setName("godly");
    }
    
    @Override
	public String getTranslatedName(int i)
	{
		String enchantmentName = "Godly";
		return enchantmentName + " " + StatCollector.translateToLocal("enchantment.level." + i);
	}

    @Override
    public int getMinEnchantability(int i)
    {
        return 10 + (5 * i);
    }

    @Override
    public int getMaxEnchantability(int i)
    {
        return getMinEnchantability(i) + 30;
    }

    @Override
    public int getMaxLevel()
    {
        return 3;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment enchantment)
    {
        if(enchantment instanceof EnchantmentArrowFire)
        {
            return false;
        }
        if(enchantment instanceof EnchantmentArrowExplosive)
        {
            return false;
        }
        /*if(enchantment instanceof EnchantmentArrowCrippling)
        {
            return false;
        }*/
        if(enchantment instanceof EnchantmentArrowKnockback)
        {
            return false;
        } else
        {
            return super.canApplyTogether(enchantment);
        }
    }
}