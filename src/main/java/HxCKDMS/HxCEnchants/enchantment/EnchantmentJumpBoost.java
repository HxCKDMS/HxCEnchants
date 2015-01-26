package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCEnchants.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentJumpBoost extends Enchantment {
	
    public EnchantmentJumpBoost(int id, int weight) {
        super(id, weight, EnumEnchantmentType.armor_legs);
        this.setName("JumpBoost");
    }
    
    @Override
    public int getMinEnchantability(int par1)
    {
        return 10;
    }
    
    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 40;
    }

    @Override
    public int getMaxLevel()
    {
        return Config.enchJumpBoostLVL;
    }
}