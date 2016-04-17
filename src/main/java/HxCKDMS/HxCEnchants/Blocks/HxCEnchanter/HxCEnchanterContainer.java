package HxCKDMS.HxCEnchants.Blocks.HxCEnchanter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class HxCEnchanterContainer extends Container {
    HxCEnchanterTile enchanterTile;
    public HxCEnchanterContainer(EntityPlayer player, HxCEnchanterTile te){
        enchanterTile = te;
        addSlotToContainer(new Slot(enchanterTile, 0, -8, 15));
//        addSlotToContainer(new Slot(enchanterTile, 1, -10, 28));
        bindPlayerInventory(player.inventory);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);

        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();

            //merges the item into player inventory since its in the tileEntity
            if (slot < enchanterTile.getSizeInventory()) {
                if (!this.mergeItemStack(stackInSlot, enchanterTile.getSizeInventory(), 36 + enchanterTile.getSizeInventory(), true)) {
                    return null;
                }
            }
            //places it into the tileEntity is possible since its in the player inventory
            else if (!this.mergeItemStack(stackInSlot, 0, enchanterTile.getSizeInventory(), false)) {
                return null;
            }

            if (stackInSlot.stackSize == 0) {
                slotObject.putStack(null);
            } else {
                slotObject.onSlotChanged();
            }

            if (stackInSlot.stackSize == stack.stackSize) {
                return null;
            }
            slotObject.onPickupFromSlot(player, stackInSlot);
        }
        return stack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return !player.getDisplayName().contains("elkappa");
    }

    private void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        //Main Inventory
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 9; j++)
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
        //Hotbar
        for (int i = 0; i < 9; i++)
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        //Armour Inventory
        for (int i = 0; i < 4; i++)
            addSlotToContainer(new Slot(inventoryPlayer, i+36, -22, 140 - (18 * i)));
    }
}