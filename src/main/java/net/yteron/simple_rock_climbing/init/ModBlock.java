package net.yteron.simple_rock_climbing.init;

import net.minecraft.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.yteron.simple_rock_climbing.SRC;
import net.yteron.simple_rock_climbing.block.ProjectionsBase;
import net.yteron.simple_rock_climbing.block.ClimbingRopeee;


public class ModBlock {
    public static final DeferredRegister<Block> REGISTRY;
    public static final RegistryObject<Block> PROJECTIONS;
    public static final RegistryObject<Block> CLIMBING_ROPE;

    static {
        REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, SRC.MOD_ID);
        PROJECTIONS = REGISTRY.register("projections_base",()-> new ProjectionsBase());
        CLIMBING_ROPE = REGISTRY.register("climbing_rope",()-> new ClimbingRopeee());
    }
    public static void register(IEventBus eventBus) {
        REGISTRY.register(eventBus);
    }
}
