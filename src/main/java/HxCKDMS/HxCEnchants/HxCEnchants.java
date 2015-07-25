package HxCKDMS.HxCEnchants;

import HxCKDMS.HxCCore.network.PacketPipeline;
import HxCKDMS.HxCEnchants.Enchanter.EnchanterBlock;
import HxCKDMS.HxCEnchants.Enchanter.EnchanterTile;
import HxCKDMS.HxCEnchants.Handlers.*;
import HxCKDMS.HxCEnchants.Proxy.IProxy;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import HxCKDMS.HxCEnchants.network.PacketEnchanterSync;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import static HxCKDMS.HxCEnchants.lib.Reference.*;

@Mod(modid = MOD_ID, name = MOD_NAME, version = VERSION, dependencies = DEPENDENCIES)

public class HxCEnchants
{
    @Instance(MOD_ID)
    public static HxCEnchants instance;
    public static Config Cfg;

    public static PacketPipeline packetPipeline = new PacketPipeline();

    @SidedProxy(serverSide = SERVER_PROXY_CLASS, clientSide = CLIENT_PROXY_CLASS)
    public static IProxy proxy;

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        Cfg = new Config(new Configuration(event.getSuggestedConfigurationFile()));
        if (Config.enableChargesSystem) {
            proxy.preInit(event);
            packetPipeline.addPacket(PacketEnchanterSync.class);
            packetPipeline.initialize(CHANNEL_NAME);
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        Enchants.load();
        MinecraftForge.EVENT_BUS.register(new ArrowEventHandler());
        MinecraftForge.EVENT_BUS.register(new ArmorEventHandler());
        MinecraftForge.EVENT_BUS.register(new ToolEventHandler());
        MinecraftForge.EVENT_BUS.register(new AOEEventHandler());
        if (Config.enableChargesSystem) {
            NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIHandler());
            GameRegistry.registerBlock(new EnchanterBlock(), "EnchanterBlock");
            GameRegistry.registerTileEntity(EnchanterTile.class, "EnchanterTile");
        }
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){}
}
