package net.yteron.simple_rock_climbing.mixin;

import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
//import net.yteron.simple_rock_climbing.block.system.ClimbingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PlayerEntity.class)
public class PlayerPoseMixin {
    @Inject(method = "updatePlayerPose", at = @At("HEAD"), cancellable = true)
    private void onUpdatePlayerPose(CallbackInfo ci) {
        PlayerEntity self = (PlayerEntity)(Object)this;

//        if (self.isPassenger() && self.getVehicle() instanceof ClimbingEntity) {
//            self.setPose(Pose.valueOf("CLIMBING"));
//            ci.cancel();
//        }
    }
}
