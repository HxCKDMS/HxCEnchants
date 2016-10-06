package HxCKDMS.HxCEnchants.api;

import HxCKDMS.HxCEnchants.lib.Reference;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

import java.util.List;

import static HxCKDMS.HxCEnchants.Configurations.Configurations.usecustomenum;

@SuppressWarnings("unchecked")
public class HxCEnchantment extends Enchantment {
    private int MaxLevel, cost;
    private List<Short> bannedEnchs;
    private EnumHxCEnchantType eType;
    public long charge;
    public HxCEnchantment(int id, String name, byte rarity, byte HxCType, byte MaxLevel, byte cost, long chargeReq, List<Short> enchs) {
        super(id, rarity, Reference.Types.get(HxCType));
        setName(name);
        this.MaxLevel = MaxLevel;
        this.cost = cost;
        this.bannedEnchs = enchs;
        this.eType = Reference.HxCTypes.get(HxCType);
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
        return !(bannedEnchs.contains((short) ench.effectId) || ench == this);
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return usecustomenum ? eType.canEnchantItem(stack.getItem()) : type.canEnchantItem(stack.getItem());
    }
}