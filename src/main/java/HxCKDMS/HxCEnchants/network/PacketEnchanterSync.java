package HxCKDMS.HxCEnchants.network;

import HxCKDMS.HxCEnchants.XPInfuser.XPInfuserTile;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketEnchanterSync implements IMessage {
    private int x = 0, y = 0, z = 0, xpti = 0;
    private String player;

    public PacketEnchanterSync() {}

    public PacketEnchanterSync(BlockPos pos, int xpti, String player) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        this.xpti = xpti;
        this.player = player;
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeInt(x);
        byteBuf.writeInt(y);
        byteBuf.writeInt(z);
        byteBuf.writeInt(xpti);
        ByteBufUtils.writeUTF8String(byteBuf, player);
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        x = byteBuf.readInt();
        y = byteBuf.readInt();
        z = byteBuf.readInt();
        xpti = byteBuf.readInt();
        player = ByteBufUtils.readUTF8String(byteBuf);
    }

    public static class handler implements IMessageHandler<PacketEnchanterSync, IMessage> {
        @Override
        public IMessage onMessage(final PacketEnchanterSync message, final MessageContext ctx) {
            IThreadListener mainThread = (WorldServer)ctx.getServerHandler().playerEntity.worldObj;
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {
                TileEntity tileEntity = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(new BlockPos(message.x, message.y, message.z));

                if (tileEntity != null && tileEntity instanceof XPInfuserTile) {
                    XPInfuserTile HxCTile = (XPInfuserTile) tileEntity;
                    HxCTile.xpti = message.xpti;
                    HxCTile.player = message.player;
                    HxCTile.infuse();
                }
                }
            });
            return null;
        }
    }
}