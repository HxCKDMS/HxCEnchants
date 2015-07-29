package HxCKDMS.HxCEnchants.Keybinds;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class Keybinds {
    public static KeyBinding OverCharge;
    public static KeyBinding FlashStep;

    public static void init() {
        OverCharge = new KeyBinding("key.OverCharge", Keyboard.KEY_O, "key.categories.HxCCore");
        FlashStep = new KeyBinding("key.FlashStep", Keyboard.KEY_F, "key.categories.HxCCore");

        // Register both KeyBindings to the ClientRegistry
        ClientRegistry.registerKeyBinding(OverCharge);
        ClientRegistry.registerKeyBinding(FlashStep);
    }
}