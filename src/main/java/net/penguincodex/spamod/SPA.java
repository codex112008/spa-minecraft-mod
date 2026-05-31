package net.penguincodex.spamod;

import net.fabricmc.api.ModInitializer;

import net.penguincodex.spamod.entity.ModEntities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SPA implements ModInitializer {
	public static final String MOD_ID = "spa";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModEntities.registerModEntities();
		ModEntities.registerModEntitiesAttributes();
	}
}