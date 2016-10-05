package HxCKDMS.HxCEnchants.Blocks.HxCEnchanter;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import java.util.LinkedHashMap;

public class HxCEnchanterTile extends TileEntity implements IInventory {
    private ItemStack[] inv;

    public int xpte = 0;
    public String player = "";
    public LinkedHashMap<Enchantment, Integer> enchs = new LinkedHashMap<>();

    public HxCEnchanterTile(){
        inv = new ItemStack[2];
    }

    @Override
    public void updateEntity() {}

    @Override
    public boolean canUpdate() {
        return false;
    }

    public void enchant() {
        if (xpte > 0 && !player.isEmpty()) {
            ItemStack stack = inv[0];
            if (stack != null) {
                EntityPlayer p = worldObj.getPlayerEntityByName(player);
                assert p.experienceLevel >= xpte;
                enchs.forEach(stack::addEnchantment);
//                for (int i = 0; i < Math.round(xpte/8); i++) {
//                    int re = worldObj.rand.nextInt(Enchantment.enchantmentsList.length);
//                    if (Enchantment.enchantmentsList[re] == null) {
//                        i--;
//                    } else {
//                        int lvl = Enchantment.enchantmentsList[re].getMaxLevel() - (worldObj.rand.nextInt(Enchantment.enchantmentsList[re].getMaxLevel() - 1));
//                        stack.addEnchantment(Enchantment.enchantmentsList[re], lvl);
//                    }
//                }
                p.addExperienceLevel(-xpte);
                xpte = 0;
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
        return "HxCEnchanter";
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
        return stack.isItemEnchantable() &! stack.isItemEnchanted();
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