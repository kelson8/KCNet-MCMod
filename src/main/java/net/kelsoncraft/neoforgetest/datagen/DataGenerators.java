package net.kelsoncraft.neoforgetest.datagen;


import net.kelsoncraft.neoforgetest.NeoForgeTest;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = NeoForgeTest.MOD_ID) // If it only handles ModBusEvents, this annotation is not strictly necessary here anymore

// To test the data generator
// Open the gradle tab -> mod development -> runData

public class DataGenerators {

    @SubscribeEvent
     public static void gatherData(GatherDataEvent event) {
        // Classes to generate the custom data
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // Loot table provider
        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider
                ));

        // Recipe provider
        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));

        // Block/item tags provider
        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(packOutput, lookupProvider, NeoForgeTest.MOD_ID, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new ModItemTagProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), NeoForgeTest.MOD_ID, existingFileHelper));

        // Mod Item/Block state provider
//        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, NeoForgeTest.MOD_ID, existingFileHelper));
//        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, NeoForgeTest.MOD_ID, existingFileHelper));

        // Fuels
        generator.addProvider(event.includeServer(), new ModDataMapProvider(packOutput, lookupProvider));

     }

}
