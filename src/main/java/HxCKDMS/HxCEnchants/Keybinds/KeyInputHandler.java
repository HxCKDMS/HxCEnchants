package HxCKDMS.HxCEnchants.Keybinds;

import HxCKDMS.HxCEnchants.Configurations;
import HxCKDMS.HxCEnchants.EnchantConfigHandler;
import HxCKDMS.HxCEnchants.HxCEnchants;
import HxCKDMS.HxCEnchants.enchantment.Enchants;
import HxCKDMS.HxCEnchants.network.PacketKeypress;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

@SuppressWarnings("all")
public class KeyInputHandler {
    Minecraft mc = Minecraft.getMinecraft();
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        try {
            if (EnchantConfigHandler.isEnabled("OverCharge", "weapon") && Keybinds.OverCharge.isPressed() && mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() instanceof ItemSword && mc.thePlayer.getHeldItem().hasTagCompound() && EnchantmentHelper.getEnchantmentLevel(Enchants.Overcharge.effectId, mc.thePlayer.getHeldItem()) > 0) {
                HxCEnchants.networkWrapper.sendToServer(new PacketKeypress(2));
            }
            if (EnchantConfigHandler.isEnabled("FlashStep", "armor") && Keybinds.FlashStep.isPressed() && mc.thePlayer.inventory.armorItemInSlot(0) != null && mc.thePlayer.inventory.armorItemInSlot(0).hasTagCompound() && EnchantmentHelper.getEnchantmentLevel(Enchants.FlashStep.effectId, mc.thePlayer.inventory.armorItemInSlot(0)) > 0 && (mc.thePlayer.inventory.armorItemInSlot(0).getTagCompound().getInteger("HxCEnchantCharge") >= EnchantConfigHandler.getData("FlashStep", "armor")[4] || !Configurations.enableChargesSystem)) {
                HxCEnchants.networkWrapper.sendToServer(new PacketKeypress(1));
            }
        } catch (Exception e) {
            LogHelper.warn("Something happened in Key handler. " + e.getMessage(), Reference.MOD_NAME);
        }
    }
}
