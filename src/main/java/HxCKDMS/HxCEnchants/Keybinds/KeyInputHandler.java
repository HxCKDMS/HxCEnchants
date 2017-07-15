package HxCKDMS.HxCEnchants.Keybinds;

import HxCKDMS.HxCEnchants.Configurations.Configurations;
import HxCKDMS.HxCEnchants.HxCEnchants;
import HxCKDMS.HxCEnchants.lib.Reference;
import HxCKDMS.HxCEnchants.network.PacketKeypress;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import hxckdms.hxcutils.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemSword;

@SuppressWarnings("all")
public class KeyInputHandler {
    Minecraft mc = Minecraft.getMinecraft();
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        try {
            if (Configurations.EnabledEnchants.get("OverCharge") && Keybinds.OverCharge.isPressed() && mc.thePlayer.getHeldItem() != null && (mc.thePlayer.getHeldItem().getItem() instanceof ItemSword || mc.thePlayer.getHeldItem().getItem() instanceof ItemAxe) && mc.thePlayer.getHeldItem().hasTagCompound() && EnchantmentHelper.getEnchantmentLevel((int) Configurations.EnchantIDs.get("Overcharge"), mc.thePlayer.getHeldItem()) > 0)
                HxCEnchants.networkWrapper.sendToServer(new PacketKeypress(2, mc.thePlayer.getDisplayName()));
            if (Configurations.EnabledEnchants.get("FlashStep") && Keybinds.FlashStep.isPressed() && mc.thePlayer.inventory.armorItemInSlot(0) != null && mc.thePlayer.inventory.armorItemInSlot(0).hasTagCompound() && (mc.thePlayer.inventory.armorItemInSlot(0).getTagCompound().getInteger("HxCEnchantCharge") >= Configurations.EnchantChargeNeeded.get("FlashStep") || !Configurations.enableChargesSystem))
                HxCEnchants.networkWrapper.sendToServer(new PacketKeypress(1, mc.thePlayer.getDisplayName()));
            if (Configurations.enableChargesSystem && Keybinds.charge.isPressed())
                HxCEnchants.networkWrapper.sendToServer(new PacketKeypress(3, mc.thePlayer.getDisplayName()));
        } catch (Exception e) {
            LogHelper.warn("Something happened in Key handler. " + e.getMessage(), Reference.MOD_NAME);
        }
    }
}