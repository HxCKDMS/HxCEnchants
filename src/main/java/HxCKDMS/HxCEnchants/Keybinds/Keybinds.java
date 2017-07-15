package HxCKDMS.HxCEnchants.Keybinds;

import HxCKDMS.HxCEnchants.Configurations.Configurations;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class Keybinds {
    static KeyBinding OverCharge;
    static KeyBinding FlashStep;
    static KeyBinding charge;

    public static void init() {
        OverCharge = new KeyBinding("key.OverCharge", Keyboard.KEY_O, "key.categories.HxCCore");
        FlashStep = new KeyBinding("key.FlashStep", Keyboard.KEY_F, "key.categories.HxCCore");
        charge = new KeyBinding("key.charge", Keyboard.KEY_G, "key.categories.HxCCore");

        if (Configurations.enchantments.get("OverCharge").enabled)
            ClientRegistry.registerKeyBinding(OverCharge);
        if (Configurations.enchantments.get("FlashStep").enabled)
            ClientRegistry.registerKeyBinding(FlashStep);
        if (Configurations.enableChargesSystem)
            ClientRegistry.registerKeyBinding(charge);
    }
}