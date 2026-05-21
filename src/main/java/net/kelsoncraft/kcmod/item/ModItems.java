package net.kelsoncraft.kcmod.item;

import net.kelsoncraft.kcmod.KCMod;
import net.kelsoncraft.kcmod.item.custom.ChiselItem;
import net.kelsoncraft.kcmod.item.custom.CustomPickaxeItem;
import net.kelsoncraft.kcmod.item.custom.FuelItem;
import net.kelsoncraft.kcmod.item.custom.HammerItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(KCMod.MOD_ID);

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
    // Tools
    // https://www.youtube.com/watch?v=QMIk1k67pnw&list=PLKGarocXCE1G6CQOoiYdMVx-E1d9F_itF&index=15
    //------

    public static final DeferredItem<SwordItem> BISMUTH_SWORD = ITEMS.register("bismuth_sword",
            () -> new SwordItem(ModToolTiers.BISMUTH, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.BISMUTH, 5.0F, -2.4f))));

        public static final DeferredItem<CustomPickaxeItem> BISMUTH_PICKAXE = ITEMS.register("bismuth_pickaxe",
            () -> new CustomPickaxeItem(ModToolTiers.BISMUTH, false));


    public static final DeferredItem<ShovelItem> BISMUTH_SHOVEL = ITEMS.register("bismuth_shovel",
            () -> new ShovelItem(ModToolTiers.BISMUTH, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.BISMUTH, 1.5F, -3.0f))));

    public static final DeferredItem<AxeItem> BISMUTH_AXE = ITEMS.register("bismuth_axe",
            () -> new AxeItem(ModToolTiers.BISMUTH, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.BISMUTH, 6F, -3.2f))));

    public static final DeferredItem<HoeItem> BISMUTH_HOE = ITEMS.register("bismuth_hoe",
            () -> new HoeItem(ModToolTiers.BISMUTH, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.BISMUTH, 0F, -3.0f))));

    public static final DeferredItem<HammerItem> BISMUTH_HAMMER = ITEMS.register("bismuth_hammer",
            () -> new HammerItem(ModToolTiers.BISMUTH, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.BISMUTH, 7F, -3.5f))));

    //------
    // Foods
    //------
    public static final DeferredItem<Item> RADISH = ITEMS.register("radish",
            // Using an anonymous class for this, any method can be overwritten in here.
            () -> new Item(new Item.Properties().food(ModFoodProperties.RADISH)) {
                @Override
                public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.kcnet_mod.radish.tooltip"));
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


    //-----
    // Test
    //-----
    // This is a custom texture test, it doesn't look too good but something to mess around with
    // Adapted from the emerald.
    public static final DeferredItem<Item> AMETHYST_INGOT = ITEMS.register("amethyst_ingot",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
