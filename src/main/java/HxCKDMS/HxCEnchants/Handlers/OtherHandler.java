package HxCKDMS.HxCEnchants.Handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class OtherHandler {
    int derp = 0;
    String name = "[\u00a73DrZed\u00a7f] : ",
            a = "\u00a79Sorry guys I wasn't paying attention.",
            b = "\u00a73Fixed the infuser crashing bug...";

    @SubscribeEvent
    public void entityJoinWorldEvent(EntityJoinWorldEvent event) {
        /*if (event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote && derp == 0) {
            ((EntityPlayer) event.entity).addChatComponentMessage(new ChatComponentText(name + a));
            ((EntityPlayer) event.entity).addChatComponentMessage(new ChatComponentText(name + b));
            derp++;
        }*/
    }
}
