package HxCKDMS.HxCEnchants.enchantment;

import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.HxCEnchants.lib.Reference;
import net.minecraftforge.common.MinecraftForge;
import org.apache.commons.lang3.StringUtils;

import static HxCKDMS.HxCEnchants.Configurations.Configurations.*;
@SuppressWarnings("all")
public class Enchants {
    private static short[] data;
    public static HxCEnch enchant = new HxCEnch();
    public static void load () {
        for (String ench : EnabledEnchants.keySet()){
            if (EnabledEnchants.get(ench))
                if (Reference.ENCH_INCOMPATS.containsKey(ench))
                    enchant.registerEnchant(StringUtils.uncapitalize(ench), (byte) 0, EnchantIDs.get(ench), EnchantLevels.get(ench), EnchantWeight.get(ench), EnchantCost.get(ench), EnchantChargeNeeded.get(ench), Reference.ENCH_INCOMPATS.get(ench));
                else
                    enchant.registerEnchant(StringUtils.uncapitalize(ench), (byte) 0, EnchantIDs.get(ench), EnchantLevels.get(ench), EnchantWeight.get(ench), EnchantCost.get(ench), EnchantChargeNeeded.get(ench));
        }
        HxCEnch.hxcEnchants.forEach(z -> MinecraftForge.EVENT_BUS.register(z));
        LogHelper.info(HxCEnch.hxcEnchants.size() + " enchantments have been registered.", Reference.MOD_NAME);
    }
}