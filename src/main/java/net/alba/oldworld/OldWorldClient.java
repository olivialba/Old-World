package net.alba.oldworld;

import net.alba.oldworld.entity.ModEntitiesClient;
import net.alba.oldworld.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;

public class OldWorldClient implements ClientModInitializer{

    @Override
    public void onInitializeClient() {
        ModEntitiesClient.registerEntitiesRendererClient();

        ModScreenHandlers.registerAllScreens();
    }
}
