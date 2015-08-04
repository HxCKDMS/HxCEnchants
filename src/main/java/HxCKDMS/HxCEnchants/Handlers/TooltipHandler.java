package HxCKDMS.HxCEnchants.Handlers;

import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TooltipHandler {
    @SubscribeEvent
    public void onItemTooltip (ItemTooltipEvent event) {
        if (event.itemStack.isItemStackDamageable() && event.itemStack.getTagCompound() != null) {
            long Charge = event.itemStack.getTagCompound().getLong("HxCEnchantCharge");
            int oc = event.itemStack.getTagCompound().getInteger("HxCOverCharge");
            if (Charge != 0)
                event.toolTip.add("Charge : " + Charge);
            if (oc != 0)
                event.toolTip.add("Stored Charge : " + oc);
        }
    }
}
