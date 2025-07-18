package net.kelsoncraft.neoforgetest.util;

import net.kelsoncraft.neoforgetest.NeoForgeTest;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(NeoForgeTest.MOD_ID, name));
        }
    }

    // This is mostly in use for when items are thrown onto the magic block
    // To modify which ones can be thrown onto it, change up the transformable_items.json.
    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(NeoForgeTest.MOD_ID, name));
        }
    }
}
