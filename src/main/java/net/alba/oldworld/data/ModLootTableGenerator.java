package net.alba.oldworld.data;

import net.alba.oldworld.block.ModBlocks;
import net.alba.oldworld.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {

    public ModLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.OLD_INGOT_BLOCK);

        addDrop(ModBlocks.OLD_ORE, oreDrops(ModBlocks.OLD_ORE, ModItems.OLD_INGOT));
        addDrop(ModBlocks.DEEPSLATE_OLD_ORE, oreDrops(ModBlocks.DEEPSLATE_OLD_ORE, ModItems.OLD_INGOT));
    }
}
