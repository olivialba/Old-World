package net.alba.oldworld.entity;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.entity.client.model.CrystalModel;
import net.alba.oldworld.entity.client.renderer.BlackSpiderRenderer;
import net.alba.oldworld.entity.client.renderer.CrystalRenderer;
import net.alba.oldworld.entity.client.renderer.ProjectileThrowRenderer;
import net.alba.oldworld.entity.custom.projectiles.BasicProjectileEntity;
import net.alba.oldworld.entity.custom.projectiles.CrystalProjectileEntity;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntitiesClient {
    
    public static final EntityType<BasicProjectileEntity> PROJECTILE_THROW = Registry.register(Registries.ENTITY_TYPE, new Identifier(OldWorld.MOD_ID, "projectile_throw"), FabricEntityTypeBuilder.<BasicProjectileEntity>create(SpawnGroup.MISC, BasicProjectileEntity::new).dimensions(EntityDimensions.fixed(0.25F, 0.25F)).trackRangeChunks(5).trackedUpdateRate(10).build());
    public static final EntityType<CrystalProjectileEntity> CRYSTAL_PROJECTILE = Registry.register(Registries.ENTITY_TYPE, new Identifier(OldWorld.MOD_ID, "crystal_projectile"), FabricEntityTypeBuilder.<CrystalProjectileEntity>create(SpawnGroup.MISC, CrystalProjectileEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).trackRangeChunks(5).trackedUpdateRate(10).build());
    public static final EntityModelLayer CRYSTAL_PROJECTILE_MODEL = new EntityModelLayer(new Identifier(OldWorld.MOD_ID, "crystal_projectile"), "crystal_projectile_model");


    // onInitializeClient() - ClientModInitializer
    public static void registerEntitiesRendererClient() {
            EntityRendererRegistry.register(ModEntities.BLACK_SPIDER, BlackSpiderRenderer::new);
            
            EntityRendererRegistry.register(PROJECTILE_THROW, ProjectileThrowRenderer::new);

            EntityRendererRegistry.register(CRYSTAL_PROJECTILE, CrystalRenderer::new);
            EntityModelLayerRegistry.registerModelLayer(CRYSTAL_PROJECTILE_MODEL, CrystalModel::getTexturedModelData);
    }
}
