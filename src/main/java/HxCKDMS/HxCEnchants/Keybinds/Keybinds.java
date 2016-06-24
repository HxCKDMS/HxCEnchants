package HxCKDMS.HxCEnchants.Keybinds;

import HxCKDMS.HxCEnchants.Configurations.Configurations;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class Keybinds {
    static KeyBinding OverCharge;
    static KeyBinding FlashStep;

    public static void init() {
        OverCharge = new KeyBinding("key.OverCharge", Keyboard.KEY_O, "key.categories.HxCCore");
        FlashStep = new KeyBinding("key.FlashStep", Keyboard.KEY_F, "key.categories.HxCCore");

        if (Configurations.EnabledEnchants.get("OverCharge"))
            ClientRegistry.registerKeyBinding(OverCharge);
        if (Configurations.EnabledEnchants.get("FlashStep"))
            ClientRegistry.registerKeyBinding(FlashStep);
    }
}