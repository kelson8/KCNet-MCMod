package net.kelsoncraft.neoforgetest.item;

import net.kelsoncraft.neoforgetest.NeoForgeTest;
import net.kelsoncraft.neoforgetest.item.custom.ChiselItem;
import net.kelsoncraft.neoforgetest.item.custom.FuelItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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

    //------
    // Foods
    //------
    public static final DeferredItem<Item> RADISH = ITEMS.register("radish",
            // Using an anonymous class for this, any method can be overwritten in here.
            () -> new Item(new Item.Properties().food(ModFoodProperties.RADISH)) {
                @Override
                public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.kcneoforgetest.radish.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    // New
    //----

    // Burger
    public static final DeferredItem<Item> BURGER = ITEMS.register("burger",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BURGER)));


    // Chili Pepper
    public static final DeferredItem<Item> CHILI_PEPPER = ITEMS.register("chili_pepper",
            () -> new Item(new Item.Properties().food(ModFoodProperties.CHILI_PEPPER)));

    // Minced Meat
    public static final DeferredItem<Item> RAW_MINCED_MEAT = ITEMS.register("raw_minced_meat",
            () -> new Item(new Item.Properties().food(ModFoodProperties.RAW_MINCED_MEAT)));

    public static final DeferredItem<Item> COOKED_MINCED_MEAT = ITEMS.register("cooked_minced_meat",
            () -> new Item(new Item.Properties().food(ModFoodProperties.COOKED_MINCED_MEAT)));

    // Hamburger
    public static final DeferredItem<Item> RAW_PATTY = ITEMS.register("raw_patty",
            () -> new Item(new Item.Properties().food(ModFoodProperties.RAW_PATTY)));

    public static final DeferredItem<Item> COOKED_PATTY = ITEMS.register("cooked_patty",
            () -> new Item(new Item.Properties().food(ModFoodProperties.COOKED_PATTY)));

    // Sausage
    public static final DeferredItem<Item> RAW_SAUSAGE = ITEMS.register("raw_sausage",
            () -> new Item(new Item.Properties().food(ModFoodProperties.RAW_SAUSAGE)));

    public static final DeferredItem<Item> COOKED_SAUSAGE = ITEMS.register("cooked_sausage",
            () -> new Item(new Item.Properties().food(ModFoodProperties.COOKED_SAUSAGE)));


    //----


    // Fuel
    // Adding with a class
    public static final DeferredItem<Item> FROSTFIRE_ICE = ITEMS.register("frostfire_ice",
            () -> new FuelItem(new Item.Properties(), 800));

    // Adding with a data map, in data/neoforge/data_maps/item/furnace_fuels.json
    // This one might be more useful to change easier.
    public static final DeferredItem<Item> STARLIGHT_ASHES = ITEMS.register("starlight_ashes",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
