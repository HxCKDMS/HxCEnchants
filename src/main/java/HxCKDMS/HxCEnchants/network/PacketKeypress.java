package HxCKDMS.HxCEnchants.network;

import HxCKDMS.HxCEnchants.Handlers.EnchantHandlers;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

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
        public IMessage onMessage(final PacketKeypress message, final MessageContext ctx) {
            IThreadListener mainThread = (WorldServer)ctx.getServerHandler().playerEntity.worldObj;
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {
                if (message.keyid == 1) EnchantHandlers.FlashButton = true;
                if (message.keyid == 2) EnchantHandlers.OverCharge = true;
                }
            });
            return null;
        }
    }
}