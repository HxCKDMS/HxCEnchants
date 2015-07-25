package HxCKDMS.HxCEnchants.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class HxCEnchantment extends Enchantment {
    private int MaxLevel, cost;
    public HxCEnchantment(int id, String name, int rarity, EnumEnchantmentType type, int MaxLevel, int cost) {
        super(id, rarity, type);
        setName(name);
        this.MaxLevel = MaxLevel;
        this.cost = cost;
    }

    @Override
    public int getMinEnchantability(int i) {
        return 16+i;
    }

    @Override
    public int getMaxEnchantability(int i) {
        return getMinEnchantability(i) + cost;
    }

    @Override
    public int getMaxLevel() {
        return MaxLevel;
    }
}