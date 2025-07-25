package net.kelsoncraft.kcmod.datagen;

import net.kelsoncraft.kcmod.KCMod;
import net.kelsoncraft.kcmod.block.ModBlocks;
import net.kelsoncraft.kcmod.block.custom.BismuthLampBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

// This will generate the blockstates json, block model json, and the item model json file.

// https://youtu.be/0famOskqo24?list=PLKGarocXCE1G6CQOoiYdMVx-E1d9F_itF&t=586

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, String modId, ExistingFileHelper exFileHelper) {
        super(output, modId, exFileHelper);
    }

    // Register block models
    @Override
    protected void registerStatesAndModels() {
        // Add the block models
        blockWithItem(ModBlocks.BISMUTH_BLOCK);
        blockWithItem(ModBlocks.BISMUTH_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.BISMUTH_ORE);
        blockWithItem(ModBlocks.MAGIC_BLOCK);

        // Set the stairs to the bismuth block texture, I didn't know this worked like that.
        stairsBlock(ModBlocks.BISMUTH_STAIRS.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));

        slabBlock(ModBlocks.BISMUTH_SLAB.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));

        buttonBlock(ModBlocks.BISMUTH_BUTTON.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
        pressurePlateBlock(ModBlocks.BISMUTH_PRESSURE_PLATE.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));

        fenceBlock(ModBlocks.BISMUTH_FENCE.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
        fenceGateBlock(ModBlocks.BISMUTH_FENCE_GATE.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
        wallBlock(ModBlocks.BISMUTH_WALL.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));

        // The cutout render is needed for see-thru pixels
        doorBlockWithRenderType(ModBlocks.BISMUTH_DOOR.get(), modLoc("block/bismuth_door_bottom"), modLoc("block/bismuth_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.BISMUTH_TRAP_DOOR.get(), modLoc("block/bismuth_trapdoor"),  true,"cutout");



        blockItem(ModBlocks.BISMUTH_STAIRS);
        blockItem(ModBlocks.BISMUTH_SLAB);
        blockItem(ModBlocks.BISMUTH_PRESSURE_PLATE);
        blockItem(ModBlocks.BISMUTH_FENCE_GATE);
        blockItem(ModBlocks.BISMUTH_TRAP_DOOR, "_bottom");

        customLamp();

    }

    // https://github.com/Tutorials-By-Kaupenjoe/NeoForge-Tutorial-1.21.X/blob/13-blockstates/src/main/java/net/kaupenjoe/tutorialmod/datagen/ModBlockStateProvider.java#L50-L63
    private void customLamp() {
        getVariantBuilder(ModBlocks.BISMUTH_LAMP.get()).forAllStates(state -> {
            if(state.getValue(BismuthLampBlock.CLICKED)) {
                // If the state is true, get the lamp on texture
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("bismuth_lamp_on",
                        ResourceLocation.fromNamespaceAndPath(KCMod.MOD_ID, "block/" + "bismuth_lamp_on")))};
            } else {
                // If the state is false, get the lamp off texture
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("bismuth_lamp_off",
                        ResourceLocation.fromNamespaceAndPath(KCMod.MOD_ID, "block/" + "bismuth_lamp_off")))};
            }
        });

        simpleBlockItem(ModBlocks.BISMUTH_LAMP.get(), models().cubeAll("bismuth_lamp_on",
                ResourceLocation.fromNamespaceAndPath(KCMod.MOD_ID, "block/" + "bismuth_lamp_on")));
    }


    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    // https://github.com/Tutorials-By-Kaupenjoe/NeoForge-Tutorial-1.21.X/blob/12-nonBlockBlocks/src/main/java/net/kaupenjoe/tutorialmod/datagen/ModBlockStateProvider.java
    private void blockItem(DeferredBlock<?> deferredBlock) {
//        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("tutorialmod:block/" + deferredBlock.getId().getPath()));
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile( KCMod.MOD_ID + ":block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
//        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("tutorialmod:block/" + deferredBlock.getId().getPath() + appendix));
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile(KCMod.MOD_ID + ":block/" + deferredBlock.getId().getPath() + appendix));
    }

}
