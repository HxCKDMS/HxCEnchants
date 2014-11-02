package HxCKDMS.XEnchants.enchantment;

import HxCKDMS.XEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentFasterFlight extends Enchantment
{
    public EnchantmentFasterFlight(int id, int rarity)
    {
        super(id, rarity, EnumEnchantmentType.armor_feet);
        this.setName("airStrider");
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
        return Config.enchAirStriderLVL;
    }
}
