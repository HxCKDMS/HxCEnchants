package HxCKDMS.HxCEnchants.api;

import HxCKDMS.HxCEnchants.enchantment.EnumHxCEnchantType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

import java.util.List;

import static net.minecraft.enchantment.EnumEnchantmentType.*;
import static HxCKDMS.HxCEnchants.enchantment.EnumHxCEnchantType.*;

@SuppressWarnings("unchecked")
public class HxCEnchantment extends Enchantment {
    private int MaxLevel, cost;
    private List<Short> bannedEnchs;
    public EnumHxCEnchantType eType;
    private long charge;
    private static EnumEnchantmentType[] Types = new EnumEnchantmentType[]{all, armor, armor_feet, armor_legs, armor_torso, armor_head, weapon, digger, fishing_rod, breakable, bow, weapon};
    private static EnumHxCEnchantType[] HxCTypes = new EnumHxCEnchantType[]{ALL, ARMOR, ARMOR_FEET, ARMOR_LEGS, ARMOR_TORSO, ARMOR_HEAD, WEAPON, DIGGER, FISHING_ROD, BREAKABLE, BOW, SWORD};

    public HxCEnchantment(int id, String name, int rarity, byte HxCType, int MaxLevel, int cost, long chargeReq, List<Short> enchs) {
        super(id, rarity, null);
        setName(name);
        this.MaxLevel = MaxLevel;
        this.cost = cost;
        this.bannedEnchs = enchs;
        this.eType = HxCTypes[HxCType];
        this.type = Types[HxCType];
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
        return eType.canEnchantItem(stack.getItem());
    }
}