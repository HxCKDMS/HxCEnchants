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
            long Charge = event.itemStack.getTagCompound().getLong("Charge");
            if (Charge != 0)
                event.toolTip.add("Charge : " + Charge);
        }
    }
}
