package HxCKDMS.HxCEnchants.Handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;

public class EnchantPowerDegrade {
    public void degrade(ItemStack stack, int Enchantment, int Amount){

    }
    @SubscribeEvent
    public void onPlayerUpdate(LivingEvent.LivingUpdateEvent event){
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            for (int i = 0; i < 4; i++) {
                ItemStack stack = player.inventory.armorItemInSlot(i);
                int[] enchants = stack.getTagCompound().getIntArray("HxCEnchants");

            }
        }
    }
}
