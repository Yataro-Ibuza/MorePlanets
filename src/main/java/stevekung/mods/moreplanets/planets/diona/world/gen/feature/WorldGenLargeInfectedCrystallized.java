package stevekung.mods.moreplanets.planets.diona.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;
import stevekung.mods.stevekunglib.utils.enums.CachedEnum;

public class WorldGenLargeInfectedCrystallized extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < 2; ++i)
        {
            for (EnumFacing facing : CachedEnum.facingValues)
            {
                Block block = MPBlocks.INFECTED_CRYSTALLIZED_CRYSTAL;

                if (world.isAirBlock(pos) && block.canPlaceBlockOnSide(world, pos, facing))
                {
                    world.setBlockState(pos, block.getDefaultState().withProperty(BlockStateProperty.FACING_ALL, facing), 2);
                }
            }
        }
        return true;
    }
}