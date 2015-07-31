package HxCKDMS.HxCEnchants;

import HxCKDMS.HxCCore.HxCCore;
import HxCKDMS.HxCCore.api.Configuration.Category;
import HxCKDMS.HxCCore.api.Configuration.HxCConfig;
import HxCKDMS.HxCEnchants.Handlers.*;
import HxCKDMS.HxCEnchants.Proxy.IProxy;
import HxCKDMS.HxCEnchants.XPInfuser.XPInfuserBlock;
import HxCKDMS.HxCEnchants.XPInfuser.XPInfuserTile;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import HxCKDMS.HxCEnchants.network.PacketEnchanterSync;
import HxCKDMS.HxCEnchants.network.PacketKeypress;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;

import java.io.File;

import static HxCKDMS.HxCEnchants.lib.Reference.*;

@Mod(modid = MOD_ID, name = MOD_NAME, version = VERSION, dependencies = DEPENDENCIES)
@SuppressWarnings("unused")
public class HxCEnchants {
    @Instance(MOD_ID)
    public static HxCEnchants instance;
    public static SimpleNetworkWrapper networkWrapper = new SimpleNetworkWrapper(CHANNEL_NAME);

    @SidedProxy(serverSide = SERVER_PROXY_CLASS, clientSide = CLIENT_PROXY_CLASS)
    public static IProxy proxy;

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        HxCConfig hxCConfig = new HxCConfig();
        registerNewConfigSys(hxCConfig);
        if (Configurations.enableChargesSystem) {
            proxy.preInit(event);
            networkWrapper.registerMessage(PacketEnchanterSync.handler.class, PacketEnchanterSync.class, 0, Side.SERVER);
        }
        networkWrapper.registerMessage(PacketKeypress.handler.class, PacketKeypress.class, 1, Side.SERVER);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        Enchants.load();
        MinecraftForge.EVENT_BUS.register(new ArrowEventHandler());
        MinecraftForge.EVENT_BUS.register(new ArmorEventHandler());
        FMLCommonHandler.instance().bus().register(new ArmorEventHandler());
        MinecraftForge.EVENT_BUS.register(new ToolEventHandler());
        FMLCommonHandler.instance().bus().register(new AOEEventHandler());
        if (Configurations.notice) {
            MinecraftForge.EVENT_BUS.register(new OtherHandler());
        }
        if (Configurations.enableChargesSystem) {
            NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIHandler());
            GameRegistry.registerBlock(new XPInfuserBlock(), "XPInfuserBlock");
            GameRegistry.registerTileEntity(XPInfuserTile.class, "XPInfuserTile");
        }
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){}

    public void registerNewConfigSys(HxCConfig config) {
        config.registerCategory(new Category("General", "General Stuff"));
        config.registerCategory(new Category("ToolEnchants", "Commands Configurations"));
        config.registerCategory(new Category("ArmourEnchants", "Permissions System"));
        config.registerCategory(new Category("WeaponEnchants", "Permissions System"));
        config.handleConfig(Configurations.class, new File(HxCCore.HxCConfigDir, "HxCEnchants.cfg"));
    }
}
