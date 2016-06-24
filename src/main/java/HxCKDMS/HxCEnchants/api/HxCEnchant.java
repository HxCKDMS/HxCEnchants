package HxCKDMS.HxCEnchants.api;

import java.util.List;

public interface HxCEnchant {
    void registerEnchant(String name, byte type, short defaultID, byte maxLevel, byte rarity, int cost, long requiredCharge);
    void registerEnchant(String name, byte type, short defaultID, byte maxLevel, byte rarity, int cost, long requiredCharge, List<Short> bannedEnchantIds);
}
