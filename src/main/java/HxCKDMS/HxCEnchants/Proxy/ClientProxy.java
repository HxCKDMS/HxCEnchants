package HxCKDMS.HxCEnchants.Proxy;

import HxCKDMS.HxCEnchants.Handlers.TooltipHandler;
import HxCKDMS.HxCEnchants.Keybinds.KeyInputHandler;
import HxCKDMS.HxCEnchants.Keybinds.Keybinds;
import HxCKDMS.HxCEnchants.XPInfuser.XPInfuserSpecialRenderer;
import HxCKDMS.HxCEnchants.XPInfuser.XPInfuserTile;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy implements IProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new TooltipHandler());
        FMLCommonHandler.instance().bus().register(new KeyInputHandler());
        Keybinds.init();
        ClientRegistry.registerTileEntity(XPInfuserTile.class, "XPInfuser", new XPInfuserSpecialRenderer());
    }
}
