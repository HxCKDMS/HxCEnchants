package HxCKDMS.HxCEnchants.Handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class OtherHandler {
    int derp = 0;
    String name = "[\u00a73DrZed\u00a7f] : ",
            a = "\u00a79I've fixed a few enchants, and changed the system",
            b = "\u00a73I've tested this build a little, but MAY have a bug..",
            c = "\u00a79Cleaned up the mod so it should be faster!.";

    @SubscribeEvent
    public void entityJoinWorldEvent(EntityJoinWorldEvent event) {
        if (event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote && derp == 0) {
            ((EntityPlayer) event.entity).addChatComponentMessage(new ChatComponentText(name + a));
            ((EntityPlayer) event.entity).addChatComponentMessage(new ChatComponentText(name + b));
            ((EntityPlayer) event.entity).addChatComponentMessage(new ChatComponentText(name + c));
            derp++;
        }
    }
}
