package HxCKDMS.HxCEnchants.Keybinds;

import HxCKDMS.HxCEnchants.EnchantConfigHandler;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class Keybinds {
    public static KeyBinding OverCharge;
    public static KeyBinding FlashStep;

    public static void init() {
        OverCharge = new KeyBinding("key.OverCharge", Keyboard.KEY_O, "key.categories.HxCCore");
        FlashStep = new KeyBinding("key.FlashStep", Keyboard.KEY_F, "key.categories.HxCCore");

        if (EnchantConfigHandler.isEnabled("OverCharge", "weapon"))
            ClientRegistry.registerKeyBinding(OverCharge);
        if (EnchantConfigHandler.isEnabled("FlashStep", "armor"))
            ClientRegistry.registerKeyBinding(FlashStep);
    }
}