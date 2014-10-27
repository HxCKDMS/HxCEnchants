package HxCKDMS.XEnchants.enchantment;


import HxCKDMS.XEnchants.common.Config;
import HxCKDMS.XEnchants.common.XEnchants;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.enchantment.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;


public class EnchantmentJumpBoost extends Enchantment {
	
    public EnchantmentJumpBoost(int id, int weight) {
        super(id, weight, EnumEnchantmentType.armor_legs);
        this.setName("JumpBoost");
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
        return Config.enchJumpBoostLVL;
    }


    public boolean canApply(ItemStack Item)
    {
    	if(Item.getItem() instanceof ItemArmor)
    	{
    		ItemArmor itemArmor = (ItemArmor)Item.getItem();
    		if(itemArmor.armorType == 2)
    		{
    			return true;
    		}
    	}
        return false;
    }
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent Event)
	{
        EntityPlayer player = Event.player;
        ItemStack legs = player.getCurrentArmor(2);
		boolean JumpBoost;
        JumpBoost = XEnchants.containsEnchant(legs, Config.enchJumpBoostID);

		int Level = EnchantmentHelper.getEnchantmentLevel(this.effectId, legs);
		if (JumpBoost){
    		player.addPotionEffect(new PotionEffect(Potion.jump.id, 5, Level));
    	}
	}
}