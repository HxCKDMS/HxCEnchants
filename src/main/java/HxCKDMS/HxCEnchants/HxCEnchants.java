package HxCKDMS.HxCEnchants;

import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.HxCEnchants.Handlers.AOEEventHandler;
import HxCKDMS.HxCEnchants.Handlers.ArmorEventHandler;
import HxCKDMS.HxCEnchants.Handlers.ArrowEventHandler;
import HxCKDMS.HxCEnchants.Handlers.ToolEventHandler;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import HxCKDMS.HxCEnchants.lib.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)

public class HxCEnchants
{
    @Instance(Reference.MOD_ID)
    public static HxCEnchants instance;
    public static Config Config;
    ArrowEventHandler arrow = new ArrowEventHandler();
    ArmorEventHandler armor = new ArmorEventHandler();
    ToolEventHandler tool = new ToolEventHandler();
    AOEEventHandler aoe = new AOEEventHandler();
    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        Config = new Config(new Configuration(event.getSuggestedConfigurationFile()));
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        aoe.init();
        Enchants.load();
        MinecraftForge.EVENT_BUS.register(arrow);
        MinecraftForge.EVENT_BUS.register(armor);
        MinecraftForge.EVENT_BUS.register(tool);
        MinecraftForge.EVENT_BUS.register(aoe);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        LogHelper.info("HxCEnchants has completed loading.", Reference.MOD_NAME);
    }
}
