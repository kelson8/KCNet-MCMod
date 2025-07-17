package net.kelsoncraft.neoforgetest.item;

import net.kelsoncraft.neoforgetest.NeoForgeTest;
import net.kelsoncraft.neoforgetest.item.custom.ChiselItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NeoForgeTest.MOD_ID);

    // Bismuth
    public static final DeferredItem<Item> BISMUTH = ITEMS.register("bismuth",
            () -> new Item(new Item.Properties()));

    // Raw Bismuth
    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.register("raw_bismuth",
            () -> new Item(new Item.Properties()));

    // Chisel
    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
    () -> new ChiselItem(new Item.Properties()
            .durability(32)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
