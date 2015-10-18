package HxCKDMS.HxCEnchants.api;

import HxCKDMS.HxCEnchants.enchantment.EnumHxCEnchantType;

import java.util.List;

public interface HxCEnchant {
    void registerEnchant(String name, EnumHxCEnchantType type, int defaultID, int maxLevel, int rarity, int cost, long requiredCharge, List<Integer> bannedEnchantIds);
}
