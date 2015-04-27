package HxCKDMS.HxCEnchants.Proxy;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class ClientProxy extends CommonProxy {
    @SubscribeEvent
    public void onItemTooltip (ItemTooltipEvent event) {
        int Charge = event.itemStack.getTagCompound().getInteger("HxCEnchantCharge");
        if (Charge > 0) {
            event.toolTip.add("Charge : " + Charge);
        }
    }
}
