package HxCKDMS.HxCEnchants;

import HxCKDMS.HxCCore.HxCCore;
import HxCKDMS.HxCCore.api.Configuration.Category;
import HxCKDMS.HxCCore.api.Configuration.HxCConfig;
import HxCKDMS.HxCEnchants.Handlers.ArrowEventHandler;
import HxCKDMS.HxCEnchants.Handlers.EventHandlers;
import HxCKDMS.HxCEnchants.Handlers.GUIHandler;
import HxCKDMS.HxCEnchants.Handlers.OtherHandler;
import HxCKDMS.HxCEnchants.Proxy.IProxy;
import HxCKDMS.HxCEnchants.XPInfuser.XPInfuserBlock;
import HxCKDMS.HxCEnchants.XPInfuser.XPInfuserTile;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import HxCKDMS.HxCEnchants.network.PacketEnchanterSync;
import HxCKDMS.HxCEnchants.network.PacketKeypress;
import net.minecraft.init.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.io.File;

import static HxCKDMS.HxCEnchants.lib.Reference.*;

@Mod(modid = MOD_ID, name = MOD_NAME, version = VERSION, dependencies = DEPENDENCIES)
public class HxCEnchants {
    @Mod.Instance(MOD_ID)
    public static HxCEnchants instance;
    public static SimpleNetworkWrapper networkWrapper = new SimpleNetworkWrapper(CHANNEL_NAME);

    @SidedProxy(serverSide = SERVER_PROXY_CLASS, clientSide = CLIENT_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        HxCConfig hxCConfig = new HxCConfig();
        registerNewConfigSys(hxCConfig);
        if (Configurations.enableChargesSystem) {
            proxy.preInit(event);
            networkWrapper.registerMessage(PacketEnchanterSync.handler.class, PacketEnchanterSync.class, 0, Side.SERVER);
        }
        networkWrapper.registerMessage(PacketKeypress.handler.class, PacketKeypress.class, 1, Side.SERVER);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Enchants.load();
        MinecraftForge.EVENT_BUS.register(new ArrowEventHandler());
        MinecraftForge.EVENT_BUS.register(new EventHandlers());
        if (Configurations.notice2) {
            MinecraftForge.EVENT_BUS.register(new OtherHandler());
        }
        if (Configurations.enableChargesSystem) {
            NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIHandler());
            XPInfuserBlock block = new XPInfuserBlock();
            GameRegistry.registerBlock(block, "XPInfuserBlock");
            GameRegistry.registerTileEntity(XPInfuserTile.class, "XPInfuserTile");
            GameRegistry.addRecipe(new ShapedOreRecipe(block, "EBE", "BDB", "EBE", 'B', Items.experience_bottle, 'E', Items.ender_eye, 'D', "gemDiamond"));
        }
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){}

    public void registerNewConfigSys(HxCConfig config) {
        config.registerCategory(new Category("General", "General Stuff"));
        config.registerCategory(new Category("ToolEnchants", "Commands Configurations"));
        config.registerCategory(new Category("ArmourEnchants", "Permissions System"));
        config.registerCategory(new Category("WeaponEnchants", "Permissions System"));
        config.handleConfig(Configurations.class, new File(HxCCore.HxCConfigDir, "HxCEnchants.cfg"));
    }
}
