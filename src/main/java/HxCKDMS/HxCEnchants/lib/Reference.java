package HxCKDMS.HxCEnchants.lib;

import java.util.UUID;

public class Reference {
    public static final String MOD_ID = "HxCEnchants";
    public static final String MOD_NAME = "HxC Enchants";
    public static final String VERSION = "2.3.0";
    public static final String CHANNEL_NAME = MOD_ID;
    public static final String DEPENDENCIES = "required-after:HxCCore@[1.11.0,)";
    public static final String CLIENT_PROXY_CLASS = "HxCKDMS.HxCEnchants.Proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "HxCKDMS.HxCEnchants.Proxy.ServerProxy";

    public static UUID HealthUUID = UUID.fromString("fe15f490-62d7-11e4-b116-123b93f75cba"),
            SpeedUUID = UUID.fromString("fe15f828-62d7-11e4-b116-123b93f75cba");
}
