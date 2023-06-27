package net.alba.oldworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.alba.oldworld.block.ModBlockEntities;
import net.alba.oldworld.block.ModBlocks;
import net.alba.oldworld.entity.ModEntities;
import net.alba.oldworld.item.ModItemGroup;
import net.alba.oldworld.item.ModItems;
import net.alba.oldworld.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;


public class OldWorld implements ModInitializer {
	public static final String MOD_ID = "oldworld";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroup.registerItemGroups();
		
		ModItems.registerModItems();
        
		ModBlocks.registerModBlocks();
        ModBlockEntities.registerBlockEntities();

		ModEntities.registerEntities();

        ModScreenHandlers.registerAllScreenHandlers();
	}       
}