package net.yteron.simple_rock_climbing.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.yteron.simple_rock_climbing.SRC;

public class ModItem {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SRC.MOD_ID);
    public static final RegistryObject<Item> PROJECTIONS;
    public static final RegistryObject<Item> CLIMBING_ROPE;
    static {
        PROJECTIONS = ITEMS.register("projections_base",
                () -> new BlockItem(ModBlock.PROJECTIONS.get(),
                        new Item.Properties().tab(ModTab.TAB)));
        CLIMBING_ROPE = ITEMS.register("climbing_rope",
                () -> new BlockItem(ModBlock.CLIMBING_ROPE.get(),
                        new Item.Properties().tab(ModTab.TAB)));
    }
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
