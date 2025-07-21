package net.kelsoncraft.neoforgetest.item;

import net.kelsoncraft.neoforgetest.NeoForgeTest;
import net.kelsoncraft.neoforgetest.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NeoForgeTest.MOD_ID);

    public static final Supplier<CreativeModeTab> BISMUTH_ITEMS_TAB = CREATIVE_MODE_TAB.register("bismuth_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BISMUTH.get()))
                    .title(Component.translatable("creativetab.kcnet_mod.bismuth_items"))
                    .displayItems((itemDisplayParameters, output) -> {

                        // Add items in here for custom creative tab
                        output.accept(ModItems.BISMUTH);
                        output.accept(ModItems.RAW_BISMUTH);

                        output.accept(ModItems.CHISEL);

                        // Food
                        output.accept(ModItems.RADISH);

                        // Fuel items
                        output.accept(ModItems.FROSTFIRE_ICE);
                        output.accept(ModItems.STARLIGHT_ASHES);

                    }).build());

    // Food tabs
    public static final Supplier<CreativeModeTab> EXTRA_FOODS_TAB = CREATIVE_MODE_TAB.register("extra_foods_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BURGER.get()))
                    .title(Component.translatable("creativetab.kcnet_mod.extra_food_items"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModItems.BURGER);
                        output.accept(ModItems.CHILI_PEPPER);

                        // Minced meat
                        output.accept(ModItems.RAW_MINCED_MEAT);
                        output.accept(ModItems.COOKED_MINCED_MEAT);

                        // Hamburger patty
                        output.accept(ModItems.RAW_PATTY);
                        output.accept(ModItems.COOKED_PATTY);

                        // Sausage
                        output.accept(ModItems.RAW_SAUSAGE);
                        output.accept(ModItems.COOKED_SAUSAGE);

                    }).build());

            //


    public static final Supplier<CreativeModeTab> BISMUTH_BLOCK_TAB = CREATIVE_MODE_TAB.register("bismuth_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BISMUTH_BLOCK))
                    // Make sure this name matches the previous creative mode tab
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(NeoForgeTest.MOD_ID, "bismuth_items_tab"))
                    .title(Component.translatable("creativetab.kcnet_mod.bismuth_blocks"))

                    .displayItems((itemDisplayParameters, output) -> {

                        // Add items in here for custom creative tab
                        // Bismuth
                        output.accept(ModBlocks.BISMUTH_BLOCK);
                        output.accept(ModBlocks.BISMUTH_ORE);
                        output.accept(ModBlocks.BISMUTH_DEEPSLATE_ORE);

                        // Magic block
                        output.accept(ModBlocks.MAGIC_BLOCK);

                        // New blocks
                        output.accept(ModBlocks.BISMUTH_STAIRS);
                        output.accept(ModBlocks.BISMUTH_SLAB);

                        output.accept(ModBlocks.BISMUTH_PRESSURE_PLATE);
                        output.accept(ModBlocks.BISMUTH_BUTTON);

                        output.accept(ModBlocks.BISMUTH_FENCE);
                        output.accept(ModBlocks.BISMUTH_FENCE_GATE);
                        output.accept(ModBlocks.BISMUTH_WALL);

                        output.accept(ModBlocks.BISMUTH_DOOR);
                        output.accept(ModBlocks.BISMUTH_TRAP_DOOR);


                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
