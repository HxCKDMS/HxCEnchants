package HxCKDMS.HxCEnchants.XPInfuser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IChatComponent;

public class XPInfuserTile extends TileEntity implements IInventory {
    private ItemStack[] inv;

    public int xpti = 0;
    public String player = "";

    public XPInfuserTile(){
        inv = new ItemStack[1];
    }

    @Override
    public boolean shouldRenderInPass(int pass) {
        return pass==0;
    }

    public void infuse() {
        if (xpti > 0 && !player.isEmpty()) {
            ItemStack stack = inv[0];
            if (stack != null) {
                EntityPlayer p = worldObj.getPlayerEntityByName(player);
                int chr;
                if (xpti <= 15) chr = (xpti * xpti) + (6 * xpti);
                else if (xpti <= 30) chr = (int) Math.round(2.5 * (xpti * xpti) - (40.5 * xpti) + 360);
                else chr = (int) Math.round(4.5 * (xpti * xpti) - (162.5 * xpti) + 2220);
                if (stack.getTagCompound() != null) {
                    NBTTagCompound tagCompound = stack.getTagCompound();
                    tagCompound.setLong("HxCEnchantCharge", tagCompound.getLong("HxCEnchantCharge") + chr);
                } else {
                    NBTTagCompound tagCompound = new NBTTagCompound();
                    tagCompound.setLong("HxCEnchantCharge", chr);
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
        return worldObj.getTileEntity(pos) == this &&
                player.getDistanceSq(pos.add(.5, .5, .5)) < 64;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return stack.isItemStackDamageable();
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

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

    @Override
    public String getCommandSenderName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return null;
    }
}