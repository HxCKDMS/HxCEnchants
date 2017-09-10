package HxCKDMS.HxCEnchants;

import HxCKDMS.HxCEnchants.Blocks.HxCEnchanter.HxCEnchanterBlock;
import HxCKDMS.HxCEnchants.Blocks.HxCEnchanter.HxCEnchanterTile;
import HxCKDMS.HxCEnchants.Configurations.Configurations;
import HxCKDMS.HxCEnchants.Handlers.ArrowEventHandler;
import HxCKDMS.HxCEnchants.Handlers.EnchantEventHandlers;
import HxCKDMS.HxCEnchants.Handlers.GUIHandler;
import HxCKDMS.HxCEnchants.Proxy.IProxy;
import HxCKDMS.HxCEnchants.lib.Reference;
import HxCKDMS.HxCEnchants.network.PacketHxCEnchanterSync;
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
import hxckdms.hxcconfig.HxCConfig;
import hxckdms.hxcconfig.handlers.SpecialHandlers;
import hxckdms.hxccore.libraries.GlobalVariables;
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

    public HxCConfig hxCConfig;
    public static List<Enchantment> KnownRegisteredEnchants = new ArrayList<>();

    @SidedProxy(serverSide = SERVER_PROXY_CLASS, clientSide = CLIENT_PROXY_CLASS)
    public static IProxy proxy;

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        hxCConfig = new HxCConfig(Configurations.class, "HxCEnchants", GlobalVariables.modConfigDir, "cfg", Reference.MOD_ID);
        SpecialHandlers.registerSpecialClass(Configurations.DummyEnchant.class);
        hxCConfig.initConfiguration();
        (new Configurations()).init();
        hxCConfig.initConfiguration();

        if (EnableKeybinds) {
            proxy.preInit(event);
            networkWrapper.registerMessage(PacketKeypress.handler.class, PacketKeypress.class, 1, Side.SERVER);
        }
        if (!EnableKeybinds) {
            enchantments.get("FlashStep").enabled = false;
            enchantments.get("OverCharge").enabled = false;
        }
        if (enableCustomBlocks)
            networkWrapper.registerMessage(PacketHxCEnchanterSync.handler.class, PacketHxCEnchanterSync.class, 0, Side.SERVER);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        Configurations.enchantments.values().forEach(Configurations.DummyEnchant::init);
//        Configurations.enchants.values().forEach(HxCEnchantmentDummy::init);
        MinecraftForge.EVENT_BUS.register(new ArrowEventHandler());
        MinecraftForge.EVENT_BUS.register(new EnchantEventHandlers());
//        Enchants.load();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIHandler());
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
