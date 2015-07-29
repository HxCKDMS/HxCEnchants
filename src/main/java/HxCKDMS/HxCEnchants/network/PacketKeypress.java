package HxCKDMS.HxCEnchants.network;

import HxCKDMS.HxCCore.api.AbstractPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public class PacketKeypress extends AbstractPacket{
    int button;
    public PacketKeypress() {}

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf byteBuf) {
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf byteBuf) {
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
        //NOOP
    }

    @Override
    public void handleServerSide(EntityPlayer p) {

    }
}
