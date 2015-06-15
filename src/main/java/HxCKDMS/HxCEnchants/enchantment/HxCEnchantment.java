package HxCKDMS.HxCEnchants.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.ResourceLocation;

public class HxCEnchantment extends Enchantment
{
    private int MaxLevel;
    public HxCEnchantment(int id, ResourceLocation name,int rarity, EnumEnchantmentType type, int MaxLevel) {
        super(id, name, rarity, type);
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