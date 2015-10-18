package HxCKDMS.HxCEnchants.api;

import HxCKDMS.HxCEnchants.enchantment.EnumHxCEnchantType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

import java.util.List;

@SuppressWarnings("unchecked")
public class HxCEnchantment extends Enchantment {
    private int MaxLevel, cost;
    private List<Integer> bannedEnchs;
    public EnumHxCEnchantType eType;
    private long charge;

    public HxCEnchantment(int id, String name, int rarity, EnumHxCEnchantType HxCType, int MaxLevel, int cost, long chargeReq, List<Integer> enchs) {
        super(id, rarity, null);
        setName(name);
        this.MaxLevel = MaxLevel;
        this.cost = cost;
        this.bannedEnchs = enchs;
        this.eType = HxCType;
        this.charge = chargeReq;
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

    public long getChargeRequirement() {
        return charge;
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {
        return !(bannedEnchs.contains(ench.effectId) || ench == this);
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return eType.canEnchantItem(stack.getItem());
    }
}