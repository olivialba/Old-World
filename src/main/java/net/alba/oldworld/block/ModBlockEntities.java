package net.alba.oldworld.block;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.block.entity.CrystalImbuerBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static BlockEntityType<CrystalImbuerBlockEntity> CRYSTAL_IMBUER;

    public static void registerBlockEntities() {
        CRYSTAL_IMBUER = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            new Identifier(OldWorld.MOD_ID, "crystal_infuser"),
            FabricBlockEntityTypeBuilder.create(CrystalImbuerBlockEntity::new,
                ModBlocks.CRYSTAl_IMBUER).build(null));
    }
}
