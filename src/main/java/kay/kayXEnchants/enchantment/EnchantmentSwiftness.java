package kay.kayXEnchants.enchantment;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import kay.kayXEnchants.common.Config;

import kay.kayXEnchants.common.kayXEnchants;
import net.minecraft.enchantment.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;


public class EnchantmentSwiftness extends Enchantment {

public EnchantmentSwiftness(int Id, int Weight)
{
	super(Id, Weight, EnumEnchantmentType.armor_legs);
	this.setName("Swiftness");
	addToBookList(this);
}

@Override
public int getMaxLevel()
    {
        return Config.enchSwiftnessLVL;
    }

	@Override
    public int getMinEnchantability(int par1)
    {
		return 10;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
     return this.getMinEnchantability(par1) + 40;
    }
    
    
    public boolean canApply(ItemStack legs)
    	{
    	if(legs.getItem() instanceof ItemArmor)
     	{
        ItemArmor itemArmor = (ItemArmor)legs.getItem();
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
        boolean SpeedBoost;
        SpeedBoost = kayXEnchants.containsEnchant(legs, Config.enchSwiftnessID);

        int Level = EnchantmentHelper.getEnchantmentLevel(this.effectId, legs);
        if (SpeedBoost){
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 5, Level));
        }
    }
}