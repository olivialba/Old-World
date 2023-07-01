package net.alba.oldworld.registry;

import org.lwjgl.glfw.GLFW;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.entity.client.model.BallBasicModel;
import net.alba.oldworld.entity.client.model.CrystalModel;
import net.alba.oldworld.entity.client.renderer.BallBasicRenderer;
import net.alba.oldworld.entity.client.renderer.BlackSpiderRenderer;
import net.alba.oldworld.entity.client.renderer.CrystalRenderer;
import net.alba.oldworld.entity.client.renderer.ProjectileThrowRenderer;
import net.alba.oldworld.item.custom.crystals.FireCrystal;
import net.alba.oldworld.screen.CrystalImbuerBlock.CrystalImbuerScreen;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;

public class OldClient {

    // Key Bindings
    public static KeyBinding NextSpell = new KeyBinding("key.oldworld.next_spell", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_N, "key.category.oldworld");
    public static KeyBinding PreviousSpell = new KeyBinding("key.oldworld.previous_spell", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_B, "key.category.oldworld");

    // Entities PROJECTILE MODEL
    public static final EntityModelLayer CRYSTAL_PROJECTILE_MODEL = new EntityModelLayer(new Identifier(OldWorld.MOD_ID, "crystal_projectile"), "crystal_projectile_model");
    public static final EntityModelLayer BASIC_BALL_PROJECTILE_MODEL = new EntityModelLayer(new Identifier(OldWorld.MOD_ID, "ball_basic"), "ball_basic_model");

    public static void register() {

        // Entities Renderer
        EntityRendererRegistry.register(OldEntities.BLACK_SPIDER, BlackSpiderRenderer::new);
        EntityRendererRegistry.register(OldEntities.PROJECTILE_THROW, ProjectileThrowRenderer::new);
        EntityRendererRegistry.register(OldEntities.CRYSTAL_PROJECTILE, CrystalRenderer::new);
        EntityRendererRegistry.register(OldEntities.BASIC_BALL_PROJECTILE, BallBasicRenderer::new);

        // Entities Model
        EntityModelLayerRegistry.registerModelLayer(CRYSTAL_PROJECTILE_MODEL, CrystalModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BASIC_BALL_PROJECTILE_MODEL, BallBasicModel::getTexturedModelData);

        // Screen
        HandledScreens.register(OldScreenHandlers.CRYSTAL_IMBUER_SCREEN_HANDLER, CrystalImbuerScreen::new);

        // Keys
        KeyBindingHelper.registerKeyBinding(NextSpell);
        KeyBindingHelper.registerKeyBinding(PreviousSpell);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((FireCrystal) stack.getItem()).getColor(tintIndex),
				OldItems.FIRE_CRYSTAL, OldItems.FIRE_CRYSTAL_FIREBALL, OldItems.FIRE_CRYSTAL_COMBUSTION, OldItems.FIRE_CRYSTAL_METEOR);
    }
}
