package HxCKDMS.HxCEnchants;

import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.HxCEnchants.Handlers.AOEEventHandler;
import HxCKDMS.HxCEnchants.Handlers.ArmorEventHandler;
import HxCKDMS.HxCEnchants.Handlers.ArrowEventHandler;
import HxCKDMS.HxCEnchants.Handlers.ToolEventHandler;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import HxCKDMS.HxCEnchants.lib.Reference;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)

public class HxCEnchants
{
    @Instance(Reference.MOD_ID)
    public static HxCEnchants instance;
    public static Config Config;

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        Config = new Config(new Configuration(event.getSuggestedConfigurationFile()));
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        Enchants.load();
        MinecraftForge.EVENT_BUS.register(new ArrowEventHandler());
        MinecraftForge.EVENT_BUS.register(new ArmorEventHandler());
        FMLCommonHandler.instance().bus().register(new ArmorEventHandler());
        MinecraftForge.EVENT_BUS.register(new ToolEventHandler());
        FMLCommonHandler.instance().bus().register(new AOEEventHandler());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        LogHelper.info("Completed loading.", Reference.MOD_NAME);
    }
}
