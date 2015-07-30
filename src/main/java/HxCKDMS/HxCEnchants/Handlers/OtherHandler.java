package HxCKDMS.HxCEnchants.Handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class OtherHandler {
    public void doFlashStep(EntityPlayerMP player) {

    }
    public void handleOverCharge(EntityPlayerMP player) {

    }

    String name = "[DrZed] : ",
            a = "I'm really sorry I've been releasing broken builds.",
            b = "I've been quite distracted lately with multiple mods to manage and multiple ideas in my head.",
            c = "I'll get it right soon I just added a ton of balance checks and Fixed a few things and lightened lag.",
            d = "Please keep in mind even though we have 11 members on our team only 2 of us are active or any good at code...",
            e = "If you don't want to see these messages just disable them in the config under notice=true";

    @SubscribeEvent
    public void entityJoinWorldEvent(EntityJoinWorldEvent event) {
        if (event.entity instanceof EntityPlayer) {
            ((EntityPlayer) event.entity).addChatComponentMessage(new ChatComponentText(name + a));
            ((EntityPlayer) event.entity).addChatComponentMessage(new ChatComponentText(name + b));
            ((EntityPlayer) event.entity).addChatComponentMessage(new ChatComponentText(name + c));
            ((EntityPlayer) event.entity).addChatComponentMessage(new ChatComponentText(name + d));
            ((EntityPlayer) event.entity).addChatComponentMessage(new ChatComponentText(name + e));
        }
    }
}
