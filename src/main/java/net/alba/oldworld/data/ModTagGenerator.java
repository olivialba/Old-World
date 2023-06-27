package net.alba.oldworld.data;

import java.util.concurrent.CompletableFuture;
import net.alba.oldworld.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

public class ModTagGenerator extends ItemTagProvider {

   public ModTagGenerator(FabricDataOutput output, CompletableFuture<WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(WrapperLookup arg) {
        getOrCreateTagBuilder(ModItems.TAG_SPELL_CRYSTALS)
            .add(ModItems.FIRE_CRYSTAL)
            .add(ModItems.FIRE_CRYSTAL_COMBUSTION)
            .add(ModItems.FIRE_CRYSTAL_FIREBALL)
            .add(ModItems.FIRE_CRYSTAL_METEOR);
    }
}
