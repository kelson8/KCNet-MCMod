package net.kelsoncraft.kcmod.datagen;

import net.kelsoncraft.kcmod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                               String modId,@Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    // This is a replacement for auto generating the pickaxe.json, shovel.json and other tool json files.
    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.BISMUTH_BLOCK.get())
                .add(ModBlocks.BISMUTH_ORE.get())
                .add(ModBlocks.BISMUTH_DEEPSLATE_ORE.get())
                .add(ModBlocks.MAGIC_BLOCK.get())

                // New items
                .add(ModBlocks.BISMUTH_FENCE.get())
                .add(ModBlocks.BISMUTH_FENCE_GATE.get())
                .add(ModBlocks.BISMUTH_WALL.get())

                .add(ModBlocks.BISMUTH_STAIRS.get())
                .add(ModBlocks.BISMUTH_SLAB.get())

                .add(ModBlocks.BISMUTH_DOOR.get())
                .add(ModBlocks.BISMUTH_TRAP_DOOR.get())

                .add(ModBlocks.BISMUTH_BUTTON.get())
                .add(ModBlocks.BISMUTH_PRESSURE_PLATE.get()

                );

        // If needed, possibly for other blocks in the future
//        tag(BlockTags.MINEABLE_WITH_AXE)
//                .add(ModBlocks.BISMUTH_BLOCK.get());

//        tag(BlockTags.MINEABLE_WITH_SHOVEL)
//                .add(ModBlocks.BISMUTH_BLOCK.get());
//
//        tag(BlockTags.MINEABLE_WITH_HOE)
//                .add(ModBlocks.BISMUTH_BLOCK.get());


//        tag(BlockTags.SIGNS)
//                .add(ModBlocks.BISMUTH_BLOCK.get());

        //


        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BISMUTH_DEEPSLATE_ORE.get())
                .add(ModBlocks.MAGIC_BLOCK.get());

        tag(BlockTags.FENCES).add(ModBlocks.BISMUTH_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.BISMUTH_FENCE_GATE.get());
        tag(BlockTags.WALLS).add(ModBlocks.BISMUTH_WALL.get());

    }
}
