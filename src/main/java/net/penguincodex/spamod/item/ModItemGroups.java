package net.penguincodex.spamod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.penguincodex.spamod.SPA;

public class ModItemGroups {
    public static final ItemGroup SPA_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(SPA.MOD_ID, "spa_items"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.FLORAL_STAFF))
                    .displayName(Text.translatable("itemgroup.spa.spa_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.FLORAL_STAFF);
                    }).build()
    );

    public static void registerItemGroups(){
        SPA.LOGGER.info("Registering Item Groups for " + SPA.MOD_ID);
    }
}
