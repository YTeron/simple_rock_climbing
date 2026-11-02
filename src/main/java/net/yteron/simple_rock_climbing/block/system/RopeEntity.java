package net.yteron.simple_rock_climbing.block.system;

import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class RopeEntity extends AClimbingEntityDirection{


    public RopeEntity(EntityType<?> p_i48580_1_, World world) {
        super(p_i48580_1_, world);
    }

    public RopeEntity(World world, BlockPos pos, Direction facing,String poseName) {
        super(world, poseName, pos, facing);
    }

    public RopeEntity(FMLPlayMessages.SpawnEntity packet, World world) {
        super(packet, world);
    }
    @Override
    public void PositionPlayer(BlockPos pos, Direction facing) {
        double x = pos.getX() + 0.5D;
        double y = pos.getY() - 0.25D;
        double z = pos.getZ() + 0.5D;
        switch (facing){
            case NORTH:
                z = pos.getZ() - 0.1D;
                break;
            case SOUTH:
                z = pos.getZ()  +1.1D;
                break;
            case WEST:
                x = pos.getX() -0.1D;
                break;
            case EAST:
                x = pos.getX() +1.1D;
                break;
            default:
                break;
        }
        setPos(x,y,z);
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT p_70037_1_) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT p_213281_1_) {

    }
}
