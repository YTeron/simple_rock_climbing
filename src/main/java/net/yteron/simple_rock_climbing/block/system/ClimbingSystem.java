package net.yteron.simple_rock_climbing.block.system;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;

import java.util.HashMap;
import java.util.Map;

public class ClimbingSystem {
    //https://github.com/Oth3r/Sit/blob/master/src/main/java/one/oth3r/sit/utl/Utl.java#L171
    private final BlockPos pos;
    private final PlayerEntity player;
    private boolean isActive;
    private static Map<PlayerEntity, BlockPos> pBlock =new HashMap<>();

    public ClimbingSystem(BlockPos pos, PlayerEntity player,boolean isActive) {
        this.pos = pos;
        this.player = player;
        this.isActive = isActive;
    }
    public void startClimbing() {
        if (player.getCommandSenderWorld().isClientSide) return;
        ClimbingEvent event = new ClimbingEvent(player, pos);
        if (MinecraftForge.EVENT_BUS.post(event)) {
            return;
        }
        teleportToRope();
        isActive = true;
    }
    public void stopClimbing() {
        isActive =false;
    }
    private void teleportToRope() {
        if (!(player instanceof ServerPlayerEntity)) return;
        ServerPlayerEntity sp = (ServerPlayerEntity) player;
        sp.setPos(player.getX(),player.getY(),player.getZ());
    }

}
