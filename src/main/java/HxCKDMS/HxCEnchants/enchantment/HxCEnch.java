package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCEnchants.api.HxCEnchant;
import HxCKDMS.HxCEnchants.api.HxCEnchantment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HxCEnch implements HxCEnchant {
    public static List<HxCEnchantment> hxcEnchants = new ArrayList<>();

    @Override
    public void registerEnchant(String name, byte type, short defaultID, byte maxLevel, byte rarity, int cost, long requiredCharge) {
        hxcEnchants.add(new HxCEnchantment(defaultID, name, rarity, type, maxLevel, cost, requiredCharge, Collections.emptyList()));
    }

    @Override
    public void registerEnchant(String name, byte type, short defaultID, byte maxLevel, byte rarity, int cost, long requiredCharge, List<Short> bannedEnchantIds) {
        hxcEnchants.add(new HxCEnchantment(defaultID, name, rarity, type, maxLevel, cost, requiredCharge, bannedEnchantIds));
    }
}
