package net.kelsoncraft.kcmod.datagen;

import net.kelsoncraft.kcmod.item.ModItems;
import net.kelsoncraft.kcmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        // For items being thrown onto the magic block, converts them into diamonds.
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                // Custom mod blocks
                .add(ModItems.BISMUTH.get())
                .add(ModItems.RAW_BISMUTH.get()
                // Vanilla items can be added to this as well
//                .add(Items.COAL)
//                .add(Items.STICK)
//                .add(Items.COMPASS);
                );




    }
}
