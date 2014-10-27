package HxCKDMS.XEnchants.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentFly extends Enchantment {

    public EnchantmentFly(int id, int weight) {
        super(id, weight, EnumEnchantmentType.armor_feet);
        this.setName("Fly");
    }

    @Override
    public int getMinEnchantability(int par1) {
        return 15;
    }

    @Override
    public int getMaxEnchantability(int par1) {
        return super.getMinEnchantability(par1) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

}