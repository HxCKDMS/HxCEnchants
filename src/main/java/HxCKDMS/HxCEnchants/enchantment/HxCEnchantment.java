package HxCKDMS.HxCEnchants.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class HxCEnchantment extends Enchantment
{
    private int MaxLevel;
    public HxCEnchantment(int id, String name, int rarity, EnumEnchantmentType type, int MaxLevel) {
        super(id, rarity, type);
        setName(name);
        this.MaxLevel = MaxLevel;
    }

    @Override
    public int getMinEnchantability(int i)
    {
        return 16+i;
    }

    @Override
    public int getMaxEnchantability(int i)
    {
        return getMinEnchantability(i) + 20;
    }

    @Override
    public int getMaxLevel()
    {
        return MaxLevel;
    }
}