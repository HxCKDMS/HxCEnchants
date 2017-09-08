package HxCKDMS.HxCEnchants.lib;

import HxCKDMS.HxCEnchants.api.EnumHxCEnchantType;
import net.minecraft.enchantment.EnumEnchantmentType;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static HxCKDMS.HxCEnchants.api.EnumHxCEnchantType.*;
import static net.minecraft.enchantment.EnumEnchantmentType.*;

public class Reference {
    public static final String MOD_ID = "HxCEnchants";
    public static final String MOD_NAME = "HxC Enchants";
    public static final String VERSION = "3.1.0";
    public static final String CHANNEL_NAME = MOD_ID;
    public static final String DEPENDENCIES = "required-after:hxccore@[2.0.6,)";
    public static final String CLIENT_PROXY_CLASS = "HxCKDMS.HxCEnchants.Proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "HxCKDMS.HxCEnchants.Proxy.ServerProxy";
    public static final List<EnumEnchantmentType> Types = Arrays.asList(all, armor, armor_feet, armor_legs, armor_torso, armor_head, weapon, digger, fishing_rod, breakable, bow, weapon);
    public static final List<EnumHxCEnchantType> HxCTypes = Arrays.asList(ALL, ARMOR, ARMOR_FEET, ARMOR_LEGS, ARMOR_TORSO, ARMOR_HEAD, WEAPON, DIGGER, FISHING_ROD, BREAKABLE, BOW, SWORD);

    public static UUID HealthUUID = UUID.fromString("fe15f490-62d7-11e4-b116-123b93f75cba"),
            SpeedUUID = UUID.fromString("fe15f828-62d7-11e4-b116-123b93f75cba");
}
