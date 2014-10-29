package HxCKDMS.XEnchants.enchantment;

import java.util.*;

import HxCKDMS.XEnchants.common.Config;
import HxCKDMS.XEnchants.common.XEnchants;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;

public class EnchantmentVitality extends Enchantment {

    public EnchantmentVitality(int id, int weight) {
        super(id, weight, EnumEnchantmentType.armor_torso);
        this.setName("Vitality");
        addToBookList(this);
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
        return Config.enchVitalityLVL;
    }
}
