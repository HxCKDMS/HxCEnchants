package HxCKDMS.HxCEnchants.Keybinds;
import HxCKDMS.HxCEnchants.Handlers.ArmorEventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class KeyInputHandler {
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if(Keybinds.OverCharge.isPressed())
            ArmorEventHandler.OverCharge = !ArmorEventHandler.OverCharge;
//        if(Keybinds.FlashStep.isPressed())
//            System.out.println("pong");
    }
}