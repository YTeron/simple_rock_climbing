package net.yteron.simple_rock_climbing.mixin;

import net.minecraft.entity.Pose;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(Pose.class)
public abstract class PoseMixin {



    @Shadow
    @Mutable
    private static Pose[] $VALUES;

    @Invoker("<init>")
    public static Pose invokeInit(String name, int ordinal) {
        throw new AssertionError();
    }

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void onStaticInit(CallbackInfo ci) {
        if ($VALUES == null) {
            return;
        }

        Pose climbingRope = invokeInit("CLIMBING_ROPE", $VALUES.length);
        Pose climbingProjections = invokeInit("CLIMBING_PROJECTIONS", $VALUES.length + 1);
        ArrayList<Pose> values = new ArrayList<>(Arrays.asList($VALUES));
        values.add(climbingRope);
        values.add(climbingProjections);
        $VALUES = values.toArray(new Pose[0]);
    }
}
