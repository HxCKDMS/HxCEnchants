package HxCKDMS.HxCEnchants.Keybinds;
import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.HxCEnchants.Configurations.Configurations;
import HxCKDMS.HxCEnchants.EnchantConfigHandler;
import HxCKDMS.HxCEnchants.HxCEnchants;
import HxCKDMS.HxCEnchants.lib.Reference;
import HxCKDMS.HxCEnchants.network.PacketKeypress;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemSword;

@SuppressWarnings("all")
public class KeyInputHandler {
    Minecraft mc = Minecraft.getMinecraft();
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        try {
            if (EnchantConfigHandler.isEnabled("OverCharge", "weapon") && Keybinds.OverCharge.isPressed() && mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() instanceof ItemSword && mc.thePlayer.getHeldItem().hasTagCompound() && EnchantmentHelper.getEnchantmentLevel((int)EnchantConfigHandler.getData("Overcharge", "weapon")[0], mc.thePlayer.getHeldItem()) > 0)
                HxCEnchants.networkWrapper.sendToServer(new PacketKeypress(2));
            if (EnchantConfigHandler.isEnabled("FlashStep", "armor") && Keybinds.FlashStep.isPressed() && mc.thePlayer.inventory.armorItemInSlot(0) != null && mc.thePlayer.inventory.armorItemInSlot(0).hasTagCompound() && EnchantmentHelper.getEnchantmentLevel((int)EnchantConfigHandler.getData("FlashStep", "armor")[0], mc.thePlayer.inventory.armorItemInSlot(0)) > 0 && (mc.thePlayer.inventory.armorItemInSlot(0).getTagCompound().getInteger("HxCEnchantCharge") >= EnchantConfigHandler.getData("FlashStep", "armor")[4] || !Configurations.enableChargesSystem))
                HxCEnchants.networkWrapper.sendToServer(new PacketKeypress(1));
        } catch (Exception e) {
            LogHelper.warn("Something happened in Key handler. " + e.getMessage(), Reference.MOD_NAME);
        }
    }
}