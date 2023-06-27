package net.alba.oldworld.data;

import java.util.List;
import java.util.function.Consumer;

import net.alba.oldworld.block.ModBlocks;
import net.alba.oldworld.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

public class ModRecipeGenerator extends FabricRecipeProvider {

    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSmelting(exporter, List.of(ModBlocks.OLD_ORE), RecipeCategory.MISC, ModItems.OLD_INGOT,
            0.7f, 200, "old_ingot");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.OLD_INGOT, 
            RecipeCategory.DECORATIONS, ModBlocks.OLD_INGOT_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.OLD_SWORD)
            .pattern("O")
            .pattern("S")
            .pattern("S")
            .input('O', ModItems.OLD_INGOT)
            .input('S', Items.STICK)
            .criterion(FabricRecipeProvider.hasItem(Items.STICK), 
                FabricRecipeProvider.conditionsFromItem(Items.STICK))
            .criterion(FabricRecipeProvider.hasItem(ModItems.OLD_INGOT), 
                FabricRecipeProvider.conditionsFromItem(ModItems.OLD_INGOT))
            .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModItems.OLD_SWORD)));
    }
}
