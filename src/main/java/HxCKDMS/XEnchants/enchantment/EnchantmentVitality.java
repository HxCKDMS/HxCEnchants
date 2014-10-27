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

	public static UUID SwiftnessUUID = UUID.fromString("edff168f-32d7-438b-8d29-189e9405e032");

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

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent Event) {
        EntityPlayer player = Event.player;
        IAttributeInstance Eentity = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth);
        ItemStack chest = player.getCurrentArmor(2);
        int level = EnchantmentHelper.getEnchantmentLevel(Config.enchVitalityID, chest);
        boolean HPBoost = XEnchants.containsEnchant(chest, Config.enchSwiftnessID);

        if (HPBoost) {
            AttributeModifier exHP = new AttributeModifier(SwiftnessUUID, "chest", level * 0.5, 1);

            Eentity.removeModifier(exHP);
            Eentity.applyModifier(exHP);
        }
    }
}
