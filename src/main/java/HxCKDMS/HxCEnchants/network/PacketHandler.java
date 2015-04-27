package HxCKDMS.HxCEnchants.network;

import HxCKDMS.HxCEnchants.Enchanter.EnchanterTile;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class PacketHandler implements IMessage {
    public EnchanterTile tileEntity;

    public PacketHandler() {
    }

    public PacketHandler(EnchanterTile tileEntity) {
        this.tileEntity = tileEntity;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int dimensionId = buf.readInt();
        World world = DimensionManager.getWorld(dimensionId);

        int x = buf.readInt();
        int y = buf.readInt();
        int z = buf.readInt();

        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof EnchanterTile) {
            EnchanterTile teAB = (EnchanterTile)te;
            teAB.xpti = buf.readByte();
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(tileEntity.getWorldObj().provider.dimensionId);
        buf.writeInt(tileEntity.xCoord);
        buf.writeInt(tileEntity.yCoord);
        buf.writeInt(tileEntity.zCoord);
        buf.writeDouble(tileEntity.xpti);
    }

    public static class Handler implements IMessageHandler<PacketHandler, IMessage> {
        @Override
        public IMessage onMessage(PacketHandler message,	MessageContext ctx) {
            return null;
        }

    }

}