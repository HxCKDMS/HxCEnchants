package HxCKDMS.HxCEnchants.Keybinds;
import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.HxCEnchants.Configurations.Configurations;
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
            if (Configurations.EnabledEnchants.get("OverCharge") && Keybinds.OverCharge.isPressed() && mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() instanceof ItemSword && mc.thePlayer.getHeldItem().hasTagCompound() && EnchantmentHelper.getEnchantmentLevel((int) Configurations.EnchantIDs.get("Overcharge"), mc.thePlayer.getHeldItem()) > 0)
                HxCEnchants.networkWrapper.sendToServer(new PacketKeypress(2));
            if (Configurations.EnabledEnchants.get("FlashStep") && Keybinds.FlashStep.isPressed() && mc.thePlayer.inventory.armorItemInSlot(0) != null && mc.thePlayer.inventory.armorItemInSlot(0).hasTagCompound() && EnchantmentHelper.getEnchantmentLevel((int) Configurations.EnchantIDs.get("FlashStep"), mc.thePlayer.inventory.armorItemInSlot(0)) > 0 && (mc.thePlayer.inventory.armorItemInSlot(0).getTagCompound().getInteger("HxCEnchantCharge") >= Configurations.EnchantChargeNeeded.get("FlashStep") || !Configurations.enableChargesSystem))
                HxCEnchants.networkWrapper.sendToServer(new PacketKeypress(1));
        } catch (Exception e) {
            LogHelper.warn("Something happened in Key handler. " + e.getMessage(), Reference.MOD_NAME);
        }
    }
}