package net.alba.oldworld.entity.client.renderer;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.entity.client.model.BlackSpiderModel;
import net.alba.oldworld.entity.custom.mobs.BlackSpiderEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BlackSpiderRenderer extends GeoEntityRenderer<BlackSpiderEntity>{

    public BlackSpiderRenderer(Context renderManager) {
        super(renderManager, new BlackSpiderModel());
    }

    @Override
    public Identifier getTextureLocation(BlackSpiderEntity animatable) {
        return new Identifier(OldWorld.MOD_ID, "textures/entity/black_spider.png");
    }

    @Override
    public void render(BlackSpiderEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
            VertexConsumerProvider bufferSource, int packedLight) {
        
        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
