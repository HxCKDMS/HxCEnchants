package HxCKDMS.HxCEnchants.network;

import HxCKDMS.HxCCore.api.AbstractPacket;
import HxCKDMS.HxCEnchants.Enchanter.EnchanterTile;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class PacketEnchanterSync extends AbstractPacket {
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
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf byteBuf) {
        byteBuf.writeInt(x);
        byteBuf.writeInt(y);
        byteBuf.writeInt(z);
        byteBuf.writeInt(xpti);
        ByteBufUtils.writeUTF8String(byteBuf, player);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf byteBuf) {
        x = byteBuf.readInt();
        y = byteBuf.readInt();
        z = byteBuf.readInt();
        xpti = byteBuf.readInt();
        player = ByteBufUtils.readUTF8String(byteBuf);
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
        //NOOP
    }

    @Override
    public void handleServerSide(EntityPlayer p) {
        TileEntity tileEntity = p.worldObj.getTileEntity(x, y, z);

        if(tileEntity != null && tileEntity instanceof EnchanterTile){
            EnchanterTile HxCTile = (EnchanterTile) tileEntity;
            HxCTile.xpti = xpti;
            HxCTile.player = player;
            p.worldObj.markBlockForUpdate(x, y, z);
        }
    }
}