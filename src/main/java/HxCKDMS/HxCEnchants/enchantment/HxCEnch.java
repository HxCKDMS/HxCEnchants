package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCEnchants.api.HxCEnchant;
import HxCKDMS.HxCEnchants.api.HxCEnchantment;

import java.util.ArrayList;
import java.util.List;

public class HxCEnch implements HxCEnchant {
    public static List<HxCEnchantment> hxcEnchants = new ArrayList<>();
    @Override
    public void registerEnchant(String name, EnumHxCEnchantType type, int defaultID, int maxLevel, int rarity, int cost, long requiredCharge, List<Integer> bannedEnchantIds) {
        hxcEnchants.add(new HxCEnchantment(defaultID, name, rarity, type, maxLevel, cost, requiredCharge, bannedEnchantIds));
    }
}
