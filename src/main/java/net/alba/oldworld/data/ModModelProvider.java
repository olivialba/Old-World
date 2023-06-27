package net.alba.oldworld.data;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.block.ModBlocks;
import net.alba.oldworld.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DEEPSLATE_OLD_ORE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.OLD_ORE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.OLD_INGOT_BLOCK);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.CRYSTAl_IMBUER);

        blockStateModelGenerator.registerParentedItemModel(ModItems.BLACK_SPIDER_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));

        blockStateModelGenerator.registerParentedItemModel(ModItems.FIRE_CRYSTAL_FIREBALL, getCrystalModel("fire_crystal"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.FIRE_CRYSTAL_COMBUSTION, getCrystalModel("fire_crystal"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.FIRE_CRYSTAL_METEOR, getCrystalModel("fire_crystal"));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.OLD_INGOT, Models.GENERATED);

        itemModelGenerator.register(ModItems.OLD_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.OLD_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.OLD_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.OLD_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.OLD_HOE, Models.HANDHELD);
    }

    public Identifier getCrystalModel(String baseModel) {
        return new Identifier(OldWorld.MOD_ID, "item/" + baseModel);
    }
}
