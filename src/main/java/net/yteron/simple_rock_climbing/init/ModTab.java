package net.yteron.simple_rock_climbing.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.yteron.simple_rock_climbing.SRC;

public class ModTab {

    // Вкладка. Ключ локализации будет itemGroup.simple_rock_climbing
    public static final ItemGroup TAB = new ItemGroup(SRC.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.SNOW_BLOCK); // иконка вкладки
        }
    };

}