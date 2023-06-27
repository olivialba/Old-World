package net.alba.oldworld.entity;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.entity.custom.mobs.BlackSpiderEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<BlackSpiderEntity> BLACK_SPIDER = Registry.register(Registries.ENTITY_TYPE, new Identifier(OldWorld.MOD_ID, "black_spider"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BlackSpiderEntity::new).dimensions(EntityDimensions.fixed(1.5F, 1.75F)).build());


    // onInitialize() - ModInitializer
    public static void registerEntities() {
        FabricDefaultAttributeRegistry.register(ModEntities.BLACK_SPIDER, BlackSpiderEntity.setAttributes());
    }
}       
