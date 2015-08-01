package HxCKDMS.HxCEnchants.network;

import HxCKDMS.HxCEnchants.Handlers.EnchantHandlers;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketKeypress implements IMessage {
    private int keyid = -1;

    public PacketKeypress() {}

    public PacketKeypress(int id) { this.keyid = id;}

    @Override
    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeInt(keyid);
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        keyid = byteBuf.readInt();
    }

    public static class handler implements IMessageHandler<PacketKeypress, IMessage> {
        @Override
        public IMessage onMessage(PacketKeypress message, MessageContext ctx) {
            if (message.keyid == 1) EnchantHandlers.FlashButton = true;
            if (message.keyid == 2) EnchantHandlers.OverCharge = true;
            return null;
        }
    }
}