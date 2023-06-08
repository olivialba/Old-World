package net.alba.oldworld.item;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.entity.ModEntities;
import net.alba.oldworld.item.custom.*;
import net.alba.oldworld.item.custom.staffs.*;
import net.alba.oldworld.item.custom.tools.OldHammer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    
    // ITEMS
    public static final Item OLD_INGOT = registerItem("old_ingot", new Item(settings()));

    // WEAPONS / EQUIPMENT
    public static final Item OLD_HAMMER = registerItem("old_hammer", new OldHammer(ToolMaterialGroups.OLD_METAL, 6, -3.3F, settings().maxCount(1)));
    
    public static final ToolItem OLD_SWORD = registerToolItem("old_sword", new SwordItem(ToolMaterialGroups.OLD_METAL, 4, -2F, settings()));
    public static final ToolItem OLD_PICKAXE = registerToolItem("old_pickaxe", new PickaxeItem(ToolMaterialGroups.OLD_METAL, 3, -2.8F, settings()));
    public static final ToolItem OLD_AXE = registerToolItem("old_axe", new AxeItem(ToolMaterialGroups.OLD_METAL, 6, -3.1F, settings()));
    public static final ToolItem OLD_SHOVEL = registerToolItem("old_shovel", new ShovelItem(ToolMaterialGroups.OLD_METAL, 1, -3F, settings()));
    public static final ToolItem OLD_HOE = registerToolItem("old_hoe", new HoeItem(ToolMaterialGroups.OLD_METAL, 2, -2.5F, settings()));

    
    // STAFFS
    public static final Item EARTH_STAFF = registerItem("earth_staff", new EarthStaff(settings().maxCount(1).rarity(Rarity.UNCOMMON)));
    public static final Item FIRE_STAFF = registerItem("fire_staff", new FireStaff(settings().maxCount(1).fireproof().rarity(Rarity.UNCOMMON)));
    public static final Item FROST_STAFF = registerItem("frost_staff", new FrostStaff(settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static final Item BITE_STAFF = registerItem("bite_staff", new BiteStaff(settings().maxCount(1).rarity(Rarity.RARE)));
    //public static final Item CRYSTAL_STAFF = registerItem("crystal_staff", new CrystalStaff(settings().maxCount(1).rarity(Rarity.RARE)));

    // ENTITIES
    public static final Item BLACK_SPIDER_SPAWN_EGG = registerItem("black_spider_spawn_egg", new SpawnEggItem(ModEntities.BLACK_SPIDER, 0x333E48, 0x7C868E, settings()));

    // ITEMGROUP
    public static void addItemsToItemGroup() {
        addItemToItemGroup(ModItemGroup.OLD_WORLD, OLD_HAMMER);

        addItemToItemGroup(ModItemGroup.OLD_WORLD, OLD_SWORD);
        addItemToItemGroup(ModItemGroup.OLD_WORLD, OLD_PICKAXE);
        addItemToItemGroup(ModItemGroup.OLD_WORLD, OLD_AXE);
        addItemToItemGroup(ModItemGroup.OLD_WORLD, OLD_SHOVEL);
        addItemToItemGroup(ModItemGroup.OLD_WORLD, OLD_HOE);

        addItemToItemGroup(ModItemGroup.OLD_WORLD, EARTH_STAFF);    //1
        addItemToItemGroup(ModItemGroup.OLD_WORLD, FIRE_STAFF);
        addItemToItemGroup(ModItemGroup.OLD_WORLD, FROST_STAFF);
        addItemToItemGroup(ModItemGroup.OLD_WORLD, BITE_STAFF);     //2
        //addItemToItemGroup(ModItemGroup.OLD_WORLD, CRYSTAL_STAFF);


        addItemToItemGroup(ModItemGroup.OLD_WORLD, OLD_INGOT);
        addItemToItemGroup(ModItemGroup.OLD_WORLD, BLACK_SPIDER_SPAWN_EGG);
    }


    public static FabricItemSettings settings() {
        return new FabricItemSettings();
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(OldWorld.MOD_ID, name), item);
    }

    private static ToolItem registerToolItem(String name, ToolItem toolItem) {
        return Registry.register(Registries.ITEM, new Identifier(OldWorld.MOD_ID, name), toolItem);
    }

    private static void addItemToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }
    

    // called on 'onInitialize()'
    public static void registerModItems() {
        OldWorld.LOGGER.info("Registering Mod Items for " + OldWorld.MOD_ID);

        addItemsToItemGroup();
    }
}
