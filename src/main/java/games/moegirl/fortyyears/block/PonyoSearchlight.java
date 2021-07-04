package games.moegirl.fortyyears.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class PonyoSearchlight extends HorizontalBlock {

    public static final BooleanProperty ENABLED = BlockStateProperties.ENABLED;
    public static final VoxelShape SHAPE_NORTH = VoxelShapes.or(
            VoxelShapes.create(0.0625, 0.0, 0.0625, 0.9375, 0.0625, 0.9375),
            VoxelShapes.create(0.25, 0.0625, 0.375, 0.3125, 0.375, 0.6875),
            VoxelShapes.create(0.6875, 0.0625, 0.375, 0.75, 0.375, 0.6875),
            VoxelShapes.create(0.3125, 0.3125, 0.375, 0.6875, 0.625, 0.4375));
    public static final VoxelShape SHAPE_SOUTH = VoxelShapes.or(
            VoxelShapes.create(0.0625, 0.0, 0.046875, 0.9375, 0.0625, 0.921875),
            VoxelShapes.create(0.6875, 0.0625, 0.296875, 0.75, 0.375, 0.609375),
            VoxelShapes.create(0.25, 0.0625, 0.296875, 0.3125, 0.375, 0.609375),
            VoxelShapes.create(0.3125, 0.3125, 0.546875, 0.6875, 0.625, 0.609375));
    public static final VoxelShape SHAPE_EAST = VoxelShapes.or(
            VoxelShapes.create(0.0546875, 0.0, 0.0546875, 0.9296875, 0.0625, 0.9296875),
            VoxelShapes.create(0.3046875, 0.0625, 0.2421875, 0.6171875, 0.375, 0.3046875),
            VoxelShapes.create(0.3046875, 0.0625, 0.6796875, 0.6171875, 0.375, 0.7421875),
            VoxelShapes.create(0.5546875, 0.3125, 0.3046875, 0.6171875, 0.625, 0.6796875));
    public static final VoxelShape SHAPE_WEST = VoxelShapes.or(
            VoxelShapes.create(0.0703125, 0.0, 0.0546875, 0.9453125, 0.0625, 0.9296875),
            VoxelShapes.create(0.3828125, 0.0625, 0.6796875, 0.6953125, 0.375, 0.7421875),
            VoxelShapes.create(0.3828125, 0.0625, 0.2421875, 0.6953125, 0.375, 0.3046875),
            VoxelShapes.create(0.3828125, 0.3125, 0.3046875, 0.4453125, 0.625, 0.6796875));

    public PonyoSearchlight() {
        super(Properties.create(Material.IRON).doesNotBlockMovement().harvestLevel(2));
        setDefaultState(getStateContainer().getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(ENABLED, false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(HORIZONTAL_FACING, ENABLED);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote && player.getHeldItem(handIn).isEmpty()) {
            worldIn.setBlockState(pos, switchEnabled(state), Constants.BlockFlags.BLOCK_UPDATE);
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(HORIZONTAL_FACING)) {
            case EAST: return SHAPE_EAST;
            case WEST: return SHAPE_WEST;
            case NORTH: return SHAPE_NORTH;
            case SOUTH: return SHAPE_SOUTH;
            default: return VoxelShapes.empty();
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    public BlockState setEnabled(BlockState state, boolean enabled) {
        return state.get(ENABLED) != enabled ? state.with(ENABLED, enabled) : state;
    }

    public BlockState switchEnabled(BlockState state) {
        return state.with(ENABLED, !state.get(ENABLED));
    }
}
