package net.alba.oldworld.item;

import net.alba.oldworld.OldWorld;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static ItemGroup OLD_WORLD;

    public static void registerItemGroups() {
        OLD_WORLD = FabricItemGroup.builder(new Identifier(OldWorld.MOD_ID, "oldworld"))
            .displayName(Text.literal("Old World"))
            .icon(() -> new ItemStack(ModItems.OLD_HAMMER))
            .build();
    }
}
