package net.penguincodex.spamod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.penguincodex.spamod.SPA;
import net.penguincodex.spamod.item.custom.FloralStaffItem;

public class ModItems {
    public static final Item FLORAL_STAFF = registerItem("floral_staff", new FloralStaffItem(new Item.Settings()));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(SPA.MOD_ID, name), item);
    }

    public static void registerModItems(){
        SPA.LOGGER.info("Registering Mod Items for " + SPA.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(FLORAL_STAFF);
        });

        FloralStaffItem.registerDamageCallback();
    }
}
