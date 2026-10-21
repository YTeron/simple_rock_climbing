package net.yteron.simple_rock_climbing.mixin;

import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerModel.class)
public class PlayerModelMixin {

    @Inject(method = "setupAnim", at = @At("TAIL"))
    private void onSetupAnim(LivingEntity player, float limbSwing, float limbSwingAmount,
                             float ageInTicks, float netHeadYaw, float headPitch,
                             CallbackInfo ci) {
        if (player.getPose().name().equals("CLIMBING")) {
            PlayerModel<LivingEntity> model = (PlayerModel<LivingEntity>) (Object) this;
            model.body.xRot = (float) Math.toRadians(5);
            model.head.xRot = (float) Math.toRadians(-30);
            model.rightArm.xRot = (float) Math.toRadians(-120);
            model.leftArm.xRot = (float) Math.toRadians(-120);

            model.rightArm.zRot = (float) Math.toRadians(-10);
            model.leftArm.zRot = (float) Math.toRadians(10);

            model.rightLeg.xRot = (float) Math.toRadians(-25);
            model.leftLeg.xRot = (float) Math.toRadians(-25);
            model.rightLeg.zRot = (float) Math.toRadians(-10);
            model.leftLeg.zRot = (float) Math.toRadians(10);

            model.leftPants.copyFrom(model.leftLeg);
            model.rightPants.copyFrom(model.rightLeg);
            model.leftSleeve.copyFrom(model.leftArm);
            model.rightSleeve.copyFrom(model.rightArm);
            model.jacket.copyFrom(model.body);
        }

    }
}
