package HxCKDMS.HxCEnchants;

import HxCKDMS.HxCCore.HxCCore;
import HxCKDMS.HxCCore.api.Configuration.Category;
import HxCKDMS.HxCCore.api.Configuration.HxCConfig;
import HxCKDMS.HxCEnchants.Blocks.HxCEnchanter.HxCEnchanterBlock;
import HxCKDMS.HxCEnchants.Blocks.HxCEnchanter.HxCEnchanterTile;
import HxCKDMS.HxCEnchants.Handlers.ArrowEventHandler;
import HxCKDMS.HxCEnchants.Handlers.EventHandlers;
import HxCKDMS.HxCEnchants.Handlers.GUIHandler;
import HxCKDMS.HxCEnchants.Handlers.OtherHandler;
import HxCKDMS.HxCEnchants.Proxy.IProxy;
import HxCKDMS.HxCEnchants.Blocks.XPInfuser.XPInfuserBlock;
import HxCKDMS.HxCEnchants.Blocks.XPInfuser.XPInfuserTile;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import HxCKDMS.HxCEnchants.network.PacketInfuserSync;
import HxCKDMS.HxCEnchants.network.PacketKeypress;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.io.File;

import static HxCKDMS.HxCEnchants.lib.Reference.*;

@Mod(modid = MOD_ID, name = MOD_NAME, version = VERSION, dependencies = DEPENDENCIES)
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
            if (Configurations.EnableKeybinds)
                proxy.preInit(event);
            networkWrapper.registerMessage(PacketInfuserSync.handler.class, PacketInfuserSync.class, 0, Side.SERVER);
        }
        networkWrapper.registerMessage(PacketKeypress.handler.class, PacketKeypress.class, 1, Side.SERVER);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ArrowEventHandler());
        MinecraftForge.EVENT_BUS.register(new EventHandlers());
        if (Configurations.notice) {
            MinecraftForge.EVENT_BUS.register(new OtherHandler());
        }
        Enchants.load();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIHandler());
        if (Configurations.enableChargesSystem) {
            XPInfuserBlock block = new XPInfuserBlock();
            GameRegistry.registerBlock(block, "XPInfuserBlock");
            GameRegistry.registerTileEntity(XPInfuserTile.class, "XPInfuserTile");
            GameRegistry.addRecipe(new ShapedOreRecipe(block, "EBE", "BDB", "EBE", 'B', Items.experience_bottle, 'E', Items.ender_eye, 'D', Blocks.diamond_block));
        }
        HxCEnchanterBlock block = new HxCEnchanterBlock();
        GameRegistry.registerBlock(block, "HxCEnchanter");
        GameRegistry.registerTileEntity(HxCEnchanterTile.class, "HxCEnchanterTile");
        GameRegistry.addRecipe(new ShapedOreRecipe(block, "DED", "BOB", "OOO", 'B', Items.enchanted_book, 'E', Items.ender_eye, 'O', Blocks.obsidian, 'D', "gemDiamond"));
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){}

    public void registerNewConfigSys(HxCConfig config) {
        config.registerCategory(new Category("General", "General Stuff"));
        config.registerCategory(new Category("Enchants", "All Enchants"));
        config.handleConfig(Configurations.class, new File(HxCCore.HxCConfigDir, "HxCEnchants.cfg"));
    }
}
