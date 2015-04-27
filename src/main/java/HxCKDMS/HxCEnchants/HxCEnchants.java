package HxCKDMS.HxCEnchants;

import HxCKDMS.HxCCore.Utils.LogHelper;
import HxCKDMS.HxCEnchants.Enchanter.EnchanterBlock;
import HxCKDMS.HxCEnchants.Enchanter.EnchanterTile;
import HxCKDMS.HxCEnchants.Handlers.*;
import HxCKDMS.HxCEnchants.Proxy.CommonProxy;
import HxCKDMS.HxCEnchants.lib.Reference;
import HxCKDMS.HxCEnchants.network.PacketHandler;
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
import net.minecraftforge.common.config.Configuration;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)

public class HxCEnchants
{
    @Instance(Reference.MOD_ID)
    public static HxCEnchants instance;
    public static Config Config;
    public static SimpleNetworkWrapper Network;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        Network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.CHANNEL_NAME);
        Network.registerMessage(PacketHandler.Handler.class, PacketHandler.class, 0, Side.SERVER);
        Config = new Config(new Configuration(event.getSuggestedConfigurationFile()));
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        Enchants.load();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIHandler());
        MinecraftForge.EVENT_BUS.register(new ArrowEventHandler());
        MinecraftForge.EVENT_BUS.register(new ArmorEventHandler());
        MinecraftForge.EVENT_BUS.register(new ToolEventHandler());
        MinecraftForge.EVENT_BUS.register(new AOEEventHandler());
        GameRegistry.registerBlock(new EnchanterBlock(), "EnchanterBlock");
        GameRegistry.registerTileEntity(EnchanterTile.class, "EnchanterTile");
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        LogHelper.info("HxCEnchants has completed loading.", Reference.MOD_NAME);
    }
}
