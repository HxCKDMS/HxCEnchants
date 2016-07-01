package HxCKDMS.HxCEnchants;

import HxCKDMS.HxCCore.HxCCore;
import HxCKDMS.HxCCore.api.Configuration.HxCConfig;
import HxCKDMS.HxCEnchants.Blocks.HxCEnchanter.HxCEnchanterBlock;
import HxCKDMS.HxCEnchants.Blocks.HxCEnchanter.HxCEnchanterTile;
import HxCKDMS.HxCEnchants.Blocks.XPInfuser.XPInfuserBlock;
import HxCKDMS.HxCEnchants.Blocks.XPInfuser.XPInfuserTile;
import HxCKDMS.HxCEnchants.Configurations.Configurations;
import HxCKDMS.HxCEnchants.Handlers.ArrowEventHandler;
import HxCKDMS.HxCEnchants.Handlers.EventHandlers;
import HxCKDMS.HxCEnchants.Handlers.GUIHandler;
import HxCKDMS.HxCEnchants.Handlers.OtherHandler;
import HxCKDMS.HxCEnchants.Proxy.IProxy;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import HxCKDMS.HxCEnchants.network.PacketHxCEnchanterSync;
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
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;
import java.util.List;

import static HxCKDMS.HxCEnchants.Configurations.Configurations.*;
import static HxCKDMS.HxCEnchants.lib.Reference.*;

@Mod(modid = MOD_ID, name = MOD_NAME, version = VERSION, dependencies = DEPENDENCIES)
public class HxCEnchants {
    @Instance(MOD_ID)
    public static HxCEnchants instance;
    public static SimpleNetworkWrapper networkWrapper = new SimpleNetworkWrapper(CHANNEL_NAME);

    public static List<Enchantment> KnownRegisteredEnchants = new ArrayList<>();

    @SidedProxy(serverSide = SERVER_PROXY_CLASS, clientSide = CLIENT_PROXY_CLASS)
    public static IProxy proxy;

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        HxCConfig hxCConfig = new HxCConfig(Configurations.class, "HxCEnchants", HxCCore.HxCConfigDir, "cfg");
        hxCConfig.initConfiguration();
        if (enableChargesSystem)
            if (EnableKeybinds) {
                proxy.preInit(event);
                networkWrapper.registerMessage(PacketKeypress.handler.class, PacketKeypress.class, 1, Side.SERVER);
            }
        if (enableCustomBlocks)
            networkWrapper.registerMessage(PacketHxCEnchanterSync.handler.class, PacketHxCEnchanterSync.class, 0, Side.SERVER);
        if (enableChargesSystem && enableCustomBlocks)
            networkWrapper.registerMessage(PacketInfuserSync.handler.class, PacketInfuserSync.class, 2, Side.SERVER);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ArrowEventHandler());
        MinecraftForge.EVENT_BUS.register(new EventHandlers());
        if (notice)
            MinecraftForge.EVENT_BUS.register(new OtherHandler());
        Enchants.load();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIHandler());
        if (enableChargesSystem && enableCustomBlocks) {
            XPInfuserBlock block = new XPInfuserBlock();
            GameRegistry.registerBlock(block, "XPInfuserBlock");
            GameRegistry.registerTileEntity(XPInfuserTile.class, "XPInfuserTile");
            GameRegistry.addRecipe(new ShapedOreRecipe(block, "EBE", "BDB", "EBE", 'B', Items.experience_bottle, 'E', Items.ender_eye, 'D', Blocks.diamond_block));
        }
        if (enableCustomBlocks) {
            HxCEnchanterBlock block = new HxCEnchanterBlock();
            GameRegistry.registerBlock(block, "HxCEnchanter");
            GameRegistry.registerTileEntity(HxCEnchanterTile.class, "HxCEnchanterTile");
            GameRegistry.addRecipe(new ShapedOreRecipe(block, "DED", "BOB", "OOO", 'B', Items.enchanted_book, 'E', Items.ender_eye, 'O', Blocks.obsidian, 'D', "gemDiamond"));
        }
    }
    private static int enchnum = 0;
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (enableCustomBlocks)
            for (int i = 0; i < Enchantment.enchantmentsList.length; i++)
                if (Enchantment.enchantmentsList[i] != null) {
                    KnownRegisteredEnchants.add(Enchantment.enchantmentsList[i]);
                    enchnum++;
                }
        /*
        try {
            URL e = new URL("https://github.com/HxCKDMS/HxCLib/blob/master/HxCEnchantsCatalysts");
            InputStream inputStream = e.openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            CatalystsGrabber.load(bufferedReader);
        } catch (Exception var4) {
            LogHelper.error("Can not resolve HxCEnchantsCatalysts", "HxCKDMS Core");
            if(HxCKDMS.HxCCore.Configs.Configurations.DebugMode) {
                var4.printStackTrace();
            }
        }*/
    }
}
