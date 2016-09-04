package HxCKDMS.HxCEnchants.network;

import HxCKDMS.HxCCore.HxCCore;
import HxCKDMS.HxCEnchants.Handlers.EnchantHandlers;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketKeypress implements IMessage {
    private int keyid = -1;
    private String name = "";

    public PacketKeypress() {}

    public PacketKeypress(int id) { this.keyid = id;}

    @Override
    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeInt(keyid);
        ByteBufUtils.writeUTF8String(byteBuf, name);
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        keyid = byteBuf.readInt();
        name = ByteBufUtils.readUTF8String(byteBuf);
    }

    public static class handler implements IMessageHandler<PacketKeypress, IMessage> {
        @Override
        public IMessage onMessage(PacketKeypress message, MessageContext ctx) {
            EntityPlayerMP p = null;
            if (!message.name.isEmpty()) p = (EntityPlayerMP)HxCCore.server.getEntityWorld().getPlayerEntityByName(message.name);
            if (message.keyid == 1) EnchantHandlers.flash(p);
            else if (message.keyid == 2) EnchantHandlers.overcharge(p);
            else if (message.keyid == 3) EnchantHandlers.chargeItem(p);
            return null;
        }
    }
}