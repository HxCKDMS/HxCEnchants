package HxCKDMS.HxCEnchants.network;

import HxCKDMS.HxCEnchants.Blocks.HxCEnchanter.HxCEnchanterTile;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.tileentity.TileEntity;

import java.util.LinkedHashMap;

public class PacketHxCEnchanterSync implements IMessage {
    private int x = 0, y = 0, z = 0, xpte = 0;
    private String player, enchs;

    public PacketHxCEnchanterSync() {}

    public PacketHxCEnchanterSync(int x, int y, int z, int xpte, String player, String enchs) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.xpte = xpte;
        this.player = player;
        this.enchs = enchs;
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeInt(x);
        byteBuf.writeInt(y);
        byteBuf.writeInt(z);
        byteBuf.writeInt(xpte);
        ByteBufUtils.writeUTF8String(byteBuf, player);
        ByteBufUtils.writeUTF8String(byteBuf, enchs);
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        x = byteBuf.readInt();
        y = byteBuf.readInt();
        z = byteBuf.readInt();
        xpte = byteBuf.readInt();
        player = ByteBufUtils.readUTF8String(byteBuf);
        enchs = ByteBufUtils.readUTF8String(byteBuf);
    }

    public static class handler implements IMessageHandler<PacketHxCEnchanterSync, IMessage> {
        @Override
        public IMessage onMessage(PacketHxCEnchanterSync message, MessageContext ctx) {
            TileEntity tileEntity = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);

            LinkedHashMap<Enchantment, Integer> denchs = new LinkedHashMap<>();
            if (tileEntity != null && tileEntity instanceof HxCEnchanterTile) {
                HxCEnchanterTile HxCTile = (HxCEnchanterTile) tileEntity;
                HxCTile.xpte = message.xpte;
                HxCTile.player = message.player;
                String[] b = message.enchs.split(":");
                for (int i = 0; i < b.length; i+=2)
                    denchs.put(Enchantment.enchantmentsList[Integer.parseInt(b[i])], Integer.parseInt(b[i+1]));
                HxCTile.enchs = denchs;
                HxCTile.enchant();
            }
            return null;
        }
    }
}