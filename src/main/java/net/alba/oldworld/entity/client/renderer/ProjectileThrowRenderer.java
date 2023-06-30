package net.alba.oldworld.entity.client.renderer;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.entity.custom.projectiles.BasicProjectileEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.LlamaSpitEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class ProjectileThrowRenderer extends EntityRenderer<BasicProjectileEntity> {
    private static final Identifier TEXTURE = new Identifier(OldWorld.MOD_ID, "textures/entity/projectile_throw.png");
    private final LlamaSpitEntityModel<BasicProjectileEntity> model;

    
    public ProjectileThrowRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new LlamaSpitEntityModel<BasicProjectileEntity>(context.getPart(EntityModelLayers.LLAMA_SPIT));
    }

    public void render(BasicProjectileEntity entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0D, 0.15000000596046448D, 0.0D);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, entity.prevYaw, entity.getYaw()) - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, entity.prevPitch, entity.getPitch())));
        this.model.setAngles(entity, g, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
        super.render(entity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public Identifier getTexture(BasicProjectileEntity entity) {
        return TEXTURE;
    }
}