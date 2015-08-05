package HxCKDMS.HxCEnchants.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
public class HxCEnchantment extends Enchantment {
    private int MaxLevel, cost;
    private List<Enchantment> bannedEnchs;
    public EnumHxCEnchantType eType;

    public HxCEnchantment(int id, String name, int rarity, EnumHxCEnchantType HxCType, int MaxLevel, int cost, Enchantment[] enchs) {
        super(id, rarity, EnumEnchantmentType.all);
        setName(name);
        this.MaxLevel = MaxLevel;
        this.cost = cost;
        this.bannedEnchs = Arrays.asList(enchs);
        this.eType = HxCType;
    }

    @Override
    public int getMinEnchantability(int i) {
        return 15+i;
    }

    @Override
    public int getMaxEnchantability(int i) {
        return getMinEnchantability(i) + cost;
    }

    @Override
    public int getMaxLevel() {
        return MaxLevel;
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {
        return !(bannedEnchs.contains(ench) || ench == this);
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return eType.canEnchantItem(stack.getItem());
    }
}