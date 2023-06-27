package net.alba.oldworld.block;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.block.custom.CrystalImbuerBlock;
import net.alba.oldworld.item.ModItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block OLD_INGOT_BLOCK =  registerBlock("old_ingot_block", new Block(FabricBlockSettings.of(Material.METAL).strength(2.5f).requires()), ModItemGroup.OLD_WORLD);
    public static final Block OLD_ORE =  registerBlock("old_ore", new Block(FabricBlockSettings.of(Material.METAL).strength(2.0f).requiresTool()), ModItemGroup.OLD_WORLD);
    public static final Block DEEPSLATE_OLD_ORE =  registerBlock("deepslate_old_ore", new Block(FabricBlockSettings.of(Material.METAL).strength(3.0f).requires()), ModItemGroup.OLD_WORLD);
    public static final Block CRYSTAl_IMBUER =  registerBlock("crystal_imbuer", new CrystalImbuerBlock(FabricBlockSettings.of(Material.METAL).strength(2.0f).requires().nonOpaque()), ModItemGroup.OLD_WORLD);


    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registries.BLOCK, new Identifier(OldWorld.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        Item item = Registry.register(Registries.ITEM, new Identifier(OldWorld.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
        return item;
    }
    
    public static void registerModBlocks() {
        OldWorld.LOGGER.info("Registering ModBlocks for " + OldWorld.MOD_ID);
    }
}
