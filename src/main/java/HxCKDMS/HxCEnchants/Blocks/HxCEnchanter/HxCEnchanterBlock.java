package HxCKDMS.HxCEnchants.Blocks.HxCEnchanter;

import HxCKDMS.HxCEnchants.HxCEnchants;
import HxCKDMS.HxCEnchants.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class HxCEnchanterBlock extends BlockContainer {
    @SideOnly(Side.CLIENT)
    private IIcon[] blockIcons;

    public HxCEnchanterBlock() {
        super(Material.rock);
        setHardness(1.0F);
        setResistance(10.0F);
        isBlockContainer = true;
        setBlockName("HxCEnchanter");
        setCreativeTab(CreativeTabs.tabDecorations);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity == null || player.isSneaking()) {
            return false;
        }
        player.openGui(HxCEnchants.instance, 0, world, x, y, z);
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
        dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, block, par6);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meh) {
        return new HxCEnchanterTile();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
        blockIcons = new IIcon[6];
        for (int i = 0; i < 6; i++) {
            blockIcons[i] = iconRegister.registerIcon(Reference.MOD_ID + ":HxCEnchanter");
        }
    }

    @Override
    public IIcon getIcon(int Side, int Metadata){
        if(Side == 1) return blockIcons[0];
        else return blockIcons[1];
    }

    private void dropItems(World world, int x, int y, int z){
        IInventory inventory = (IInventory)world.getTileEntity(x, y, z);
        ItemStack item = inventory.getStackInSlot(0);
        if (item != null && item.stackSize > 0) {
            EntityItem entityItem = new EntityItem(world,x, y, z, item);
            if (item.hasTagCompound()) entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
            world.spawnEntityInWorld(entityItem); item.stackSize = 0;
        }
    }
}