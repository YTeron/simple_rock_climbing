package net.yteron.simple_rock_climbing.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import net.yteron.simple_rock_climbing.SRC;
import net.yteron.simple_rock_climbing.block.system.ClimbingEntity;
import net.yteron.simple_rock_climbing.SRC;


public class ModClimbing {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, SRC.MOD_ID);

    public static final RegistryObject<EntityType<ClimbingEntity>> SIT_ENTITY_TYPE =
            ENTITY_TYPES.register("entity_climbing",
                    () -> EntityType.Builder.<ClimbingEntity>of(ClimbingEntity::new, EntityClassification.MISC)
                            .setCustomClientFactory(ClimbingEntity::new) // <-- Вот так правильно и без ошибок!
                            .setTrackingRange(256)
                            .setUpdateInterval(20)
                            .sized(0.0001F, 0.0001F)
                            .build(SRC.MOD_ID + ":entity_climbing"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}