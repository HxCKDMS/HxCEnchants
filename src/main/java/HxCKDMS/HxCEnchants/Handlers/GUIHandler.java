package HxCKDMS.HxCEnchants.Handlers;

import HxCKDMS.HxCEnchants.Enchanter.EnchanterContainer;
import HxCKDMS.HxCEnchants.Enchanter.EnchanterGUI;
import HxCKDMS.HxCEnchants.Enchanter.EnchanterTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world,
                                      int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if(tileEntity instanceof EnchanterTile){
            return new EnchanterContainer(player, tileEntity);
        }
        return null;
    }

    //returns an instance of the Gui you made earlier
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world,
                                      int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if(tileEntity instanceof EnchanterTile){
            return new EnchanterGUI(new EnchanterContainer(player, tileEntity));
        }
        return null;

    }
}