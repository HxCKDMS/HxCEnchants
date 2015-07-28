package HxCKDMS.HxCEnchants.enchantment;

import net.minecraft.item.*;

public enum EnumHxCEnchantType {
    ALL,
    ARMOR,
    ARMOR_FEET,
    ARMOR_LEGS,
    ARMOR_TORSO,
    ARMOR_HEAD,
    WEAPON,
    DIGGER,
    FISHING_ROD,
    BREAKABLE,
    SWORD,
    BOW;

    public boolean canEnchantItem(Item item) {
        if (this == ALL) return true;
        else if (this == BREAKABLE && item.isDamageable()) return true;
        else if (this == WEAPON) return (item instanceof ItemSword || item instanceof ItemBow);

        else if (item instanceof ItemArmor) {
            if (this == ARMOR)
                return true;
            else {
                ItemArmor itemarmor = (ItemArmor) item;
                return itemarmor.armorType == 0 ? this == ARMOR_HEAD : (itemarmor.armorType == 2 ? this == ARMOR_LEGS : (itemarmor.armorType == 1 ? this == ARMOR_TORSO : (itemarmor.armorType == 3 && this == ARMOR_FEET)));
            }
        }

        else return item instanceof ItemSword ? this == SWORD :
                    (item instanceof ItemTool ? this == DIGGER :
                    (item instanceof ItemBow ? this == BOW :
                            (item instanceof ItemFishingRod && this == FISHING_ROD)));
    }
}