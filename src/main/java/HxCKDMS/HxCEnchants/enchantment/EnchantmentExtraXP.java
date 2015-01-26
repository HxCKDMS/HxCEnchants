package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentExtraXP extends Enchantment
{
    public EnchantmentExtraXP(int id, int rarity)
    {
        super(id, rarity, EnumEnchantmentType.weapon);
        setName("examine");
    }

    @Override
    public int getMinEnchantability(int i)
    {
        return 5 + (12 + (i - 1));
    }

    @Override
    public int getMaxEnchantability(int i)
    {
        return getMinEnchantability(i) + 20;
    }

    @Override
    public int getMaxLevel()
    {
        return Config.enchExamineLVL;
    }
}