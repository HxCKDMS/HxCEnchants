package HxCKDMS.XEnchants.enchantment;

import HxCKDMS.XEnchants.common.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.StatCollector;

public class EnchantmentFasterFlight extends Enchantment
{
    public EnchantmentFasterFlight(int id, int rarity)
    {
        super(id, rarity, EnumEnchantmentType.armor_feet);
        this.setName("airStrider");
    }

    public EnchantmentFasterFlight(int id, int rarity, EnumEnchantmentType type)
    {
        super(id, rarity, type);
    }

    @Override
    public String getTranslatedName(int i)
    {
        String enchantmentName = "Air Strider";
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
        return Config.enchAirStriderLVL;
    }
}
