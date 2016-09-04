package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCCore.Configs.Configurations;
import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.HxCEnchants.lib.Reference;
import net.minecraftforge.common.MinecraftForge;
import org.apache.commons.lang3.StringUtils;

import static HxCKDMS.HxCEnchants.Configurations.Configurations.*;
import static HxCKDMS.HxCEnchants.lib.Reference.ENCH_TYPE;

@SuppressWarnings("all")
public class Enchants {
    private static short[] data;
    public static HxCEnch enchant = new HxCEnch();
    public static void load () {
        for (String ench : EnabledEnchants.keySet()){
            if (EnabledEnchants.get(ench))
                System.out.println(ench);
                if (Reference.ENCH_INCOMPATS.containsKey(ench))
                    enchant.registerEnchant(StringUtils.uncapitalize(ench), ENCH_TYPE.get(ench), EnchantIDs.get(ench), EnchantLevels.get(ench), EnchantWeight.get(ench), EnchantCost.get(ench), EnchantChargeNeeded.get(ench), Reference.ENCH_INCOMPATS.get(ench));
                else
                    enchant.registerEnchant(StringUtils.uncapitalize(ench), ENCH_TYPE.get(ench), EnchantIDs.get(ench), EnchantLevels.get(ench), EnchantWeight.get(ench), EnchantCost.get(ench), EnchantChargeNeeded.get(ench));
            if (Configurations.DebugMode)
                LogHelper.info("Registered : " + ench, Reference.MOD_NAME);
        }
        HxCEnch.hxcEnchants.forEach(z -> MinecraftForge.EVENT_BUS.register(z));
        LogHelper.info(HxCEnch.hxcEnchants.size() + " enchantments have been registered.", Reference.MOD_NAME);
    }
}