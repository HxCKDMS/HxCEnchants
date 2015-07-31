package HxCKDMS.HxCEnchants.network;

import HxCKDMS.HxCEnchants.XPInfuser.XPInfuserTile;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class PacketEnchanterSync implements IMessage {
    private int x = 0, y = 0, z = 0, xpti = 0;
    private String player;

    public PacketEnchanterSync() {}

    public PacketEnchanterSync(int x, int y, int z, int xpti, String player) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    public static class handler implements IMessageHandler<PacketEnchanterSync, IMessage>
    {
        @Override
        public IMessage onMessage(PacketEnchanterSync message, MessageContext ctx) {
            TileEntity tileEntity = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);

            if (tileEntity != null && tileEntity instanceof XPInfuserTile) {
                XPInfuserTile HxCTile = (XPInfuserTile) tileEntity;
                HxCTile.xpti = message.xpti;
                HxCTile.player = message.player;
                ctx.getServerHandler().playerEntity.worldObj.markBlockForUpdate(message.x, message.y, message.z);
            }

            return null;
        }
/*
        @Override
        public void handleServerSide(EntityPlayer p) {
            TileEntity tileEntity = p.worldObj.getTileEntity(x, y, z);

            if (tileEntity != null && tileEntity instanceof XPInfuserTile) {
                XPInfuserTile HxCTile = (XPInfuserTile) tileEntity;
                HxCTile.xpti = xpti;
                HxCTile.player = player;
                p.worldObj.markBlockForUpdate(x, y, z);
            }
        }*/
    }
}