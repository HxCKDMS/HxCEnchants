package HxCKDMS.HxCEnchants.Proxy;

import HxCKDMS.HxCEnchants.Handlers.TooltipHandler;
import HxCKDMS.HxCEnchants.Keybinds.KeyInputHandler;
import HxCKDMS.HxCEnchants.Keybinds.Keybinds;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy implements IProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new TooltipHandler());
        FMLCommonHandler.instance().bus().register(new KeyInputHandler());
        Keybinds.init();
    }
}
