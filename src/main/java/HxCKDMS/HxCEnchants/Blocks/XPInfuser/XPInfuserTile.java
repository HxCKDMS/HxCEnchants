package HxCKDMS.HxCEnchants.Blocks.XPInfuser;

import HxCKDMS.HxCEnchants.api.EnchantingUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class XPInfuserTile extends TileEntity implements IInventory {
    private ItemStack[] inv;

    public int xpti = 0;
    public String player = "";

    public XPInfuserTile(){
        inv = new ItemStack[1];
    }

    @Override
    public void updateEntity() {}

    @Override
    public boolean canUpdate() {
        return false;
    }

    public void infuse() {
        if (xpti > 0 && !player.isEmpty()) {
            ItemStack stack = inv[0];
            if (stack != null) {
                EntityPlayer p = worldObj.getPlayerEntityByName(player);
                if (stack.getTagCompound() != null) {
                    NBTTagCompound tagCompound = stack.getTagCompound();
                    tagCompound.setLong("HxCEnchantCharge", tagCompound.getLong("HxCEnchantCharge") + EnchantingUtils.xpAtLevel(xpti));
                } else {
                    NBTTagCompound tagCompound = new NBTTagCompound();
                    tagCompound.setLong("HxCEnchantCharge", EnchantingUtils.xpAtLevel(xpti));
                    stack.setTagCompound(tagCompound);
                }
                p.addExperienceLevel(-xpti);
                xpti = 0;
            }
        }
    }


    @Override
    public int getSizeInventory() {
        return inv.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inv[slot];
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inv[slot] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit())
            stack.stackSize = getInventoryStackLimit();
    }

    @Override
    public String getInventoryName() {
        return "XP Infuser";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amt) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amt) {
                setInventorySlotContents(slot, null);
            } else {
                stack = stack.splitStack(amt);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            setInventorySlotContents(slot, null);
        }
        return stack;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this &&
                player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return stack.isItemStackDamageable();
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        if (inv[0] == null) {
            NBTTagList tagList = tagCompound.getTagList("Inventory", 1);
            for (int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound tag = tagList.getCompoundTagAt(i);
                byte slot = tag.getByte("Slot");
                if (slot >= 0 && slot < inv.length) {
                    inv[slot] = ItemStack.loadItemStackFromNBT(tag);
                }
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);

        if (inv[0] != null) {
            NBTTagList itemList = new NBTTagList();
            ItemStack stack = inv[0];
            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) 0);
                stack.writeToNBT(tag);
                itemList.appendTag(tag);
            }
            tagCompound.setTag("Inventory", itemList);
        }
    }
}