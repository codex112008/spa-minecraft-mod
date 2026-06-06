package net.penguincodex.spamod;

import net.fabricmc.api.ModInitializer;

import net.penguincodex.spamod.entity.ModEntities;
import net.penguincodex.spamod.item.ModItemGroups;
import net.penguincodex.spamod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SPA implements ModInitializer {
	public static final String MOD_ID = "spa";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();

		ModEntities.registerModEntities();
		ModEntities.registerModEntitiesAttributes();
	}
}