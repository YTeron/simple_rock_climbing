package net.yteron.simple_rock_climbing.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
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
import net.yteron.simple_rock_climbing.block.system.RopeEntity;
//import net.yteron.nucrad.radiation.ChunkRaditonManager;

public class ClimbingRopeee extends Block {

    private static final VoxelShape SHAPE = Block.box(6.0D, 0.0D, 6.0D,
            10.0D, 16.0D, 10.0D);
    private static final Properties PROPERTIES = Properties.copy(Blocks.IRON_BLOCK)
            .harvestLevel(0)
            .strength(5.0f, 10.0f)
            .harvestTool(ToolType.AXE)
            .requiresCorrectToolForDrops()
            .noOcclusion()
            .lightLevel((state) -> 0)
            .dynamicShape()
            .sound(SoundType.WOOL);
    public ClimbingRopeee() {
        super(PROPERTIES);
        this.registerDefaultState(
                this.stateDefinition.any()
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
        Direction face = blockRayTraceResult.getDirection();
        if (!world.isClientSide) {
            RopeEntity entity = new RopeEntity(world, pos,face,"CLIMBING_ROPE");
            world.addFreshEntity(entity);
            player.startRiding(entity);
        }

        return ActionResultType.SUCCESS;

    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world,
                               BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
