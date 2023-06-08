package net.alba.oldworld;

import net.alba.oldworld.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;

public class OldWorldClient implements ClientModInitializer{

    @Override
    public void onInitializeClient() {
        ModEntities.registerEntitiesRendererClient();
    }
}
