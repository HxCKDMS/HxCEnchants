package HxCKDMS.HxCEnchants.XPInfuser;

import HxCKDMS.HxCEnchants.HxCEnchants;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class XPInfuserBlock extends BlockContainer {
    @SideOnly(Side.CLIENT)

    public XPInfuserBlock() {
        super(Material.rock);
        setHardness(1.0F);
        setResistance(10.0F);
        isBlockContainer = true;
        setUnlocalizedName("XPInfuser");
        setCreativeTab(CreativeTabs.tabDecorations);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity == null || player.isSneaking()) {
            return false;
        }
        player.openGui(HxCEnchants.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState block) {
        dropItems(world, pos);
        super.breakBlock(world, pos, block);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meh) {
        return new XPInfuserTile();
    }

//    @Override
//    @SideOnly(Side.CLIENT)
//    public void registerIcons(IIconRegister iconRegister){
//        blockIcons = new IIcon[6];
//        for (int i = 0; i < 6; i++) {
//            blockIcons[i] = iconRegister.registerIcon(Reference.MOD_ID + ":XPInfuser");
//        }
//    }
//
//    @Override
//    public IIcon getIcon(int Side, int Metadata){
//        if(Side == 1) return blockIcons[0];
//        else return blockIcons[1];
//    }

    private void dropItems(World world, BlockPos pos){
        IInventory inventory = (IInventory)world.getTileEntity(pos);
        ItemStack item = inventory.getStackInSlot(0);
        if (item != null && item.stackSize > 0) {
            EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), item);
            if (item.hasTagCompound()) entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
            world.spawnEntityInWorld(entityItem); item.stackSize = 0;
        }
    }
}