package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCEnchants.Blocks.HxCEnchanter.HxCEnchanterContainer;
import HxCKDMS.HxCEnchants.Blocks.HxCEnchanter.HxCEnchanterGUI;
import HxCKDMS.HxCEnchants.Blocks.HxCEnchanter.HxCEnchanterTile;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GUIHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof HxCEnchanterTile)
            return new HxCEnchanterContainer(player, (HxCEnchanterTile)tileEntity);
        return null;
    }
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof HxCEnchanterTile)
            return new HxCEnchanterGUI(new HxCEnchanterContainer(player, (HxCEnchanterTile)tileEntity), player);
        return null;
    }
}