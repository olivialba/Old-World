package net.alba.oldworld.screen;

import net.alba.oldworld.screen.CrystalImbuerBlock.CrystalImbuerScreen;
import net.alba.oldworld.screen.CrystalImbuerBlock.CrystalImbuerScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;

public class ModScreenHandlers {
    public static ScreenHandlerType<CrystalImbuerScreenHandler> CRYSTAL_IMBUER_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        CRYSTAL_IMBUER_SCREEN_HANDLER = new ScreenHandlerType<>(CrystalImbuerScreenHandler::new, FeatureFlags.VANILLA_FEATURES);
    }

    // Client
    public static void registerAllScreens() {
        HandledScreens.register(ModScreenHandlers.CRYSTAL_IMBUER_SCREEN_HANDLER, CrystalImbuerScreen::new);
    }
}
