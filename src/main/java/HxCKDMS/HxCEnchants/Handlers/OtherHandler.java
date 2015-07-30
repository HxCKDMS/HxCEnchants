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
            a = "\u00a79I'm really sorry I've been releasing broken builds.",
            b = "\u00a73I've been quite distracted lately with multiple mods to \u00a73manage and multiple ideas in my head.",
            c = "\u00a79I'll get it right soon I just added a ton of balance \u00a79checks and Fixed a few things and lightened lag.",
            d = "\u00a73Please keep in mind even though we have 11 members \u00a73on our team only 2 of us are active or any good at code...",
            e = "\u00a79If you don't want to see these messages just \u00a79disable them in the config under notice=true";

    @SubscribeEvent
    public void entityJoinWorldEvent(EntityJoinWorldEvent event) {
        if (event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote && derp == 0) {
            ((EntityPlayer) event.entity).addChatComponentMessage(new ChatComponentText(name + a));
            ((EntityPlayer) event.entity).addChatComponentMessage(new ChatComponentText(name + b));
            ((EntityPlayer) event.entity).addChatComponentMessage(new ChatComponentText(name + c));
            ((EntityPlayer) event.entity).addChatComponentMessage(new ChatComponentText(name + d));
            ((EntityPlayer) event.entity).addChatComponentMessage(new ChatComponentText(name + e));
            derp++;
        }
    }
}
