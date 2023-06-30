package net.alba.oldworld.data;

import java.util.concurrent.CompletableFuture;

import net.alba.oldworld.registry.OldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

public class ModTagGenerator extends ItemTagProvider {

   public ModTagGenerator(FabricDataOutput output, CompletableFuture<WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(WrapperLookup arg) {
        getOrCreateTagBuilder(OldItems.TAG_SPELL_CRYSTALS)
            .add(OldItems.FIRE_CRYSTAL)
            .add(OldItems.FIRE_CRYSTAL_COMBUSTION)
            .add(OldItems.FIRE_CRYSTAL_FIREBALL)
            .add(OldItems.FIRE_CRYSTAL_METEOR);
    }
}
