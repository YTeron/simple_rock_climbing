package net.yteron.simple_rock_climbing.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerController;
import net.minecraft.world.GameType;
//import net.yteron.simple_rock_climbing.block.system.ClimbingEntity;
import net.yteron.simple_rock_climbing.block.system.ClimbingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerController.class)
public class PlayerPickRangeMixIn {
    //Shadow разрешает получить приватную переменную
    @Shadow
    private GameType localPlayerMode;

    @Inject(method = "getPickRange", at = @At("HEAD"), cancellable = true)
    private void modifyGetPickRange(CallbackInfoReturnable<Float> cir) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null) {
            return;
        }

        float attrib = (float) mc.player.getAttribute(net.minecraftforge.common.ForgeMod.REACH_DISTANCE.get()).getValue();
        if (mc.player.isPassenger() && mc.player.getVehicle() instanceof ClimbingEntity) {
           attrib = attrib - 3;
        }

        float finalRange = this.localPlayerMode.isCreative() ? attrib : attrib + 4.5F;
        cir.setReturnValue(finalRange);
    }
}
