package net.kelsoncraft.neoforgetest.datagen;

import net.kelsoncraft.neoforgetest.NeoForgeTest;
import net.kelsoncraft.neoforgetest.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

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
    }
}
