package stevekung.mods.moreplanets.utils.blocks;

import java.util.Optional;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockTileMP extends BlockAdvancedMP
{
    public BlockTileMP(Material material)
    {
        super(material);
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof IInventory)
        {
            InventoryHelper.dropInventoryItems(world, pos, (IInventory) tile);
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public boolean eventReceived(IBlockState state, World world, BlockPos pos, int eventID, int eventParam)
    {
        TileEntity tile = Optional.ofNullable(world.getTileEntity(pos)).orElse(null);
        return tile.receiveClientEvent(eventID, eventParam);
    }
}