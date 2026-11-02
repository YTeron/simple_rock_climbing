package net.yteron.simple_rock_climbing.block.system;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
import net.yteron.simple_rock_climbing.init.ModClimbing;

public abstract class AClimbingEntity extends Entity {
    private String poseName;

    public AClimbingEntity(EntityType<?> p_i48580_1_, World world) {
        super(p_i48580_1_, world);
    }
    public AClimbingEntity(FMLPlayMessages.SpawnEntity packet, World world) {
        super(ModClimbing.SIT_ENTITY_TYPE.get(), world);
    }

    public AClimbingEntity(World world,String poseName,BlockPos pos){
        super(ModClimbing.SIT_ENTITY_TYPE.get(), world);
        this.poseName =poseName;
        PositionPlayer(pos);
        noCulling = true;
        noPhysics =true;
    }
    abstract public void PositionPlayer(BlockPos pos);

    public Pose getPose() {
        if (this.isPassenger() && this.getVehicle() instanceof LivingEntity) {
            return Pose.valueOf(poseName);
        }
        return super.getPose();
    }
    @Override
    public ActionResultType interact(PlayerEntity player, Hand hand) {
        player.setForcedPose(Pose.valueOf(poseName));
        if (!this.getCommandSenderWorld().isClientSide) {
            player.startRiding(this);
        }
        System.out.println(player.getPose());
        return ActionResultType.SUCCESS;
    }

    @Override
    protected void addPassenger(Entity p_184200_1_) {
        super.addPassenger(p_184200_1_);
        if (p_184200_1_ instanceof PlayerEntity) {
            ((PlayerEntity) p_184200_1_).setForcedPose(Pose.valueOf(poseName));
            System.out.println(((PlayerEntity) p_184200_1_).getPose());
        }
    }
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
    @Override
    public void remove()
    {
        super.remove();
    }

    @Override
    protected void removePassenger(Entity p_184225_1_) {
        super.removePassenger(p_184225_1_);
        if (p_184225_1_ instanceof PlayerEntity) {
            ((PlayerEntity) p_184225_1_).setForcedPose(null);
        }
        if (!this.level.isClientSide) {
            this.remove();
        }
    }

    @Override
    public void positionRider(Entity p_184232_1_) {
        super.positionRider(p_184232_1_);
        if (p_184232_1_ instanceof PlayerEntity){
            ((PlayerEntity)p_184232_1_).setForcedPose(Pose.valueOf(poseName));
        }
    }

    @Override
    public void tick() {
        super.tick();
    }
}
