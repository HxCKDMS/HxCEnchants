package HxCKDMS.HxCEnchants.Handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class OtherHandler {
    int derp = 0;
    public void doFlashStep(EntityPlayerMP player) {

    }
    public void handleOverCharge(EntityPlayerMP player) {

    }

    String name = "[\u00a73DrZed\u00a7f] : ",
            a = "\u00a79This one should be 99.9% bug free!",
            b = "\u00a73Tell me if you find the 0.1%.";

    @SubscribeEvent
    public void entityJoinWorldEvent(EntityJoinWorldEvent event) {
        if (event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote && derp == 0) {
            ((EntityPlayer) event.entity).addChatComponentMessage(new ChatComponentText(name + a));
            ((EntityPlayer) event.entity).addChatComponentMessage(new ChatComponentText(name + b));
            derp++;
        }
    }
}
