package kay.kayXEnchants.enchantment;

import kay.kayXEnchants.common.Config;
import kay.kayXEnchants.common.kayXEnchants;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

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