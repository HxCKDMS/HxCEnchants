package HxCKDMS.HxCEnchants.Handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

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
