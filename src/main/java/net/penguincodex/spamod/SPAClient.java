package net.penguincodex.spamod;

import net.fabricmc.api.ClientModInitializer;
import net.penguincodex.spamod.entity.ModEntities;

public class SPAClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModEntities.registerModEntitiesModelLayers();
        ModEntities.registerModEntitiesRenderers();
    }
}
