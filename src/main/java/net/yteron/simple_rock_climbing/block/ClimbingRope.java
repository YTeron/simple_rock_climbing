package net.yteron.simple_rock_climbing.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.yteron.simple_rock_climbing.block.system.ClimbingEntity;

import javax.annotation.Nullable;
//import net.yteron.nucrad.radiation.ChunkRaditonManager;

public class ClimbingRope extends Block {
    private static final VoxelShape NORTH_SHAPE = Block.box(
            0.0D, 0.0D, 14.0D,
            16.0D, 16.0D, 16.0D
    );
    private static final VoxelShape SOUTH_SHAPE = Block.box(
            0.0D, 0.0D, 0.0D,
            16.0D, 16.0D, 2.0D
    );
    private static final VoxelShape WEST_SHAPE = Block.box(
            14.0D, 0.0D, 0.0D,
            16.0D, 16.0D, 16.0D
    );
    private static final VoxelShape EAST_SHAPE = Block.box(
            0.0D, 0.0D, 0.0D,
            2.0D, 16.0D, 16.0D
    );
    private static final Properties PROPERTIES = Properties.copy(Blocks.IRON_BLOCK)
            .harvestLevel(0)
            .strength(5.0f, 10.0f)
            .harvestTool(ToolType.AXE)
            .requiresCorrectToolForDrops()
            .noOcclusion()
            .lightLevel((state) -> 0)
            .dynamicShape()
            .sound(SoundType.WOOL);
    public ClimbingRope() {
        super(PROPERTIES);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
        );
    }

    @Override
    public void onPlace(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!world.isClientSide) {

        }
    }

    @Override
    public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!world.isClientSide && newState.getBlock() != this) {
        }
        super.onRemove(state, world, pos, newState, isMoving);
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        if (hand != Hand.MAIN_HAND) return ActionResultType.PASS;
        if (player.isShiftKeyDown()) return ActionResultType.PASS;
        Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        if (!world.isClientSide) {
            double x =pos.getX();
            double y =pos.getY();
            double z =pos.getZ();
            BlockPos sitPos =new BlockPos(x,y,z);
            ClimbingEntity entity = new ClimbingEntity(world, sitPos,facing);
            world.addFreshEntity(entity);
            player.startRiding(entity);
        }

        return ActionResultType.SUCCESS;

    }
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState()
                .setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world,
                               BlockPos pos, ISelectionContext context) {
        switch (state.getValue(BlockStateProperties.HORIZONTAL_FACING)) {
            case NORTH: return NORTH_SHAPE;
            case SOUTH: return SOUTH_SHAPE;
            case WEST:  return WEST_SHAPE;
            case EAST:  return EAST_SHAPE;
            default:    return NORTH_SHAPE;
        }
    }
    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING);
    }
}
