package HxCKDMS.HxCEnchants.network;

import HxCKDMS.HxCEnchants.Handlers.EnchantHandlers;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import hxckdms.hxccore.libraries.GlobalVariables;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketKeypress implements IMessage {
    private int keyid = -1;
    private String name = "";

    public PacketKeypress() {}

    public PacketKeypress(int id, String name) {
        this.keyid = id;
        this.name = name;
    }

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
            if (message.name.isEmpty()) return null;

            EntityPlayerMP p = (EntityPlayerMP) GlobalVariables.server.getEntityWorld().getPlayerEntityByName(message.name);

            switch (message.keyid) {
                case 1 :
                    EnchantHandlers.flash(p);
                    return null;
                case 2 :
                    EnchantHandlers.overcharge(p);
                    return null;
                case 3 :
                    EnchantHandlers.chargeItem(p);
                    return null;
                default: return null;
            }
        }
    }
}