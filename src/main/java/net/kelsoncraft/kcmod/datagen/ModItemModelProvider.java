package net.kelsoncraft.kcmod.datagen;

import net.kelsoncraft.kcmod.KCMod;
import net.kelsoncraft.kcmod.block.ModBlocks;
import net.kelsoncraft.kcmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, String modId, ExistingFileHelper existingFileHelper) {
        super(output, modId, existingFileHelper);
    }

    // Register item models
    @Override
    protected void registerModels() {
        basicItem(ModItems.BISMUTH.get());
        basicItem(ModItems.RAW_BISMUTH.get());


        basicItem(ModItems.STARLIGHT_ASHES.get());

        basicItem(ModItems.FROSTFIRE_ICE.get());

        basicItem(ModItems.CHISEL.get());


        // New

        buttonItem(ModBlocks.BISMUTH_BUTTON, ModBlocks.BISMUTH_BLOCK);
        fenceItem(ModBlocks.BISMUTH_FENCE, ModBlocks.BISMUTH_BLOCK);
        wallItem(ModBlocks.BISMUTH_WALL, ModBlocks.BISMUTH_BLOCK);

        basicItem(ModBlocks.BISMUTH_DOOR.asItem());
        //


        // Foods
        basicItem(ModItems.RADISH.get());

        basicItem(ModItems.BURGER.get());
        basicItem(ModItems.CHILI_PEPPER.get());

        // Minced Meat
        basicItem(ModItems.RAW_MINCED_MEAT.get());
        basicItem(ModItems.COOKED_MINCED_MEAT.get());

        // Hamburger Patty
        basicItem(ModItems.RAW_PATTY.get());
        basicItem(ModItems.COOKED_PATTY.get());

        // Sausage
        basicItem(ModItems.RAW_SAUSAGE.get());
        basicItem(ModItems.COOKED_SAUSAGE.get());

        // Amethyst Ingot
        basicItem(ModItems.AMETHYST_INGOT.get());
    }

    // https://github.com/Tutorials-By-Kaupenjoe/NeoForge-Tutorial-1.21.X/blob/12-nonBlockBlocks/src/main/java/net/kaupenjoe/tutorialmod/datagen/ModItemModelProvider.java
    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(KCMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(KCMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(KCMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }
}
