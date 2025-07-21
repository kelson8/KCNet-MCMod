package net.kelsoncraft.neoforgetest.datagen;

import net.kelsoncraft.neoforgetest.NeoForgeTest;
import net.kelsoncraft.neoforgetest.block.ModBlocks;
import net.kelsoncraft.neoforgetest.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }


    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        List<ItemLike> BISMUTH_SMELTABLES = List.of(ModItems.RAW_BISMUTH,
                ModBlocks.BISMUTH_ORE, ModBlocks.BISMUTH_DEEPSLATE_ORE);

        // Foods
        List<ItemLike> EXTRA_FOODS_COOKABLES = List.of(ModItems.RAW_PATTY, ModItems.RAW_MINCED_MEAT); //ModItems.RAW_MINCED_MEAT, ModItems.RAW_SAUSAGE);

        //


        // Shaped crafting

        // Create Bismuth block out of 9 Bismuth
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BISMUTH_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.BISMUTH.get())
                .unlockedBy("has_bismuth", has(ModItems.BISMUTH)).save(recipeOutput);


        // Shapeless crafting
        // Create 9 Bismuth out of a Bismuth block
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BISMUTH, 9)
                .requires(ModBlocks.BISMUTH_BLOCK)
                .unlockedBy("has_bismuth_block", has(ModBlocks.BISMUTH_BLOCK)).save(recipeOutput);

        // Convert magic block to bismuth, requires a custom name for multiple recipes of the same block.
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BISMUTH, 9)
                .requires(ModBlocks.MAGIC_BLOCK)
                .unlockedBy("has_magic_block", has(ModBlocks.MAGIC_BLOCK))
                .save(recipeOutput, "kcnet_mod:bismuth_from_magic_block");

        // Smelting/Blasting
        oreSmelting(recipeOutput, BISMUTH_SMELTABLES, RecipeCategory.MISC, ModItems.BISMUTH.get(), 0.25f, 200, "bismuth");
        oreBlasting(recipeOutput, BISMUTH_SMELTABLES, RecipeCategory.MISC, ModItems.BISMUTH.get(), 0.25f, 100, "bismuth");

        // New
        stairBuilder(ModBlocks.BISMUTH_STAIRS.get(), Ingredient.of(ModItems.BISMUTH)).group("bismuth")
                .unlockedBy("has_bismuth", has(ModItems.BISMUTH)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BISMUTH_SLAB.get(), ModItems.BISMUTH.get());

        buttonBuilder(ModBlocks.BISMUTH_BUTTON.get(), Ingredient.of(ModItems.BISMUTH.get())).group("bismuth")
                .unlockedBy("has_bismuth", has(ModItems.BISMUTH.get())).save(recipeOutput);
        pressurePlate(recipeOutput, ModBlocks.BISMUTH_PRESSURE_PLATE.get(), ModItems.BISMUTH.get());

        fenceBuilder(ModBlocks.BISMUTH_FENCE.get(), Ingredient.of(ModItems.BISMUTH.get())).group("bismuth")
                .unlockedBy("has_bismuth", has(ModItems.BISMUTH.get())).save(recipeOutput);
        fenceGateBuilder(ModBlocks.BISMUTH_FENCE_GATE.get(), Ingredient.of(ModItems.BISMUTH.get())).group("bismuth")
                .unlockedBy("has_bismuth", has(ModItems.BISMUTH.get())).save(recipeOutput);
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BISMUTH_WALL.get(), ModItems.BISMUTH.get());

        doorBuilder(ModBlocks.BISMUTH_DOOR.get(), Ingredient.of(ModItems.BISMUTH.get())).group("bismuth")
                .unlockedBy("has_bismuth", has(ModItems.BISMUTH.get())).save(recipeOutput);
        trapdoorBuilder(ModBlocks.BISMUTH_TRAP_DOOR.get(), Ingredient.of(ModItems.BISMUTH.get())).group("bismuth")
                .unlockedBy("has_bismuth", has(ModItems.BISMUTH.get())).save(recipeOutput);


        //


        //----
        // Cooking - Foods
        //-----
        // I almost got these working here
        // Disabled - You can make Cooked Patty from Raw minced Meat in a smoker.

        // TODO Compact this, make a helper method that can make the cooking(smelting), smoking, and camp fire recipes for each one.
        // TODO Try to figure out why this duplicates everything so much.
        // Raw patty to cooked patty
        // cooking
//        foodCooking(recipeOutput, EXTRA_FOODS_COOKABLES, RecipeCategory.FOOD, ModItems.COOKED_PATTY.get(), 0.25f, 100, "cooked_patty");
//        foodSmoking(recipeOutput, EXTRA_FOODS_COOKABLES, RecipeCategory.FOOD, ModItems.COOKED_PATTY.get(), 0.25f, 100, "cooked_patty");
//        foodCampFire(recipeOutput, EXTRA_FOODS_COOKABLES, RecipeCategory.FOOD, ModItems.COOKED_PATTY.get(), 0.25f, 100, "cooked_patty");
//
//
//        // Raw Minced Meat to Cooked Minced Meat
//        foodCooking(recipeOutput, EXTRA_FOODS_COOKABLES, RecipeCategory.FOOD, ModItems.COOKED_MINCED_MEAT.get(), 0.25f, 100, "cooked_minced_meat");
//        foodSmoking(recipeOutput, EXTRA_FOODS_COOKABLES, RecipeCategory.FOOD, ModItems.COOKED_MINCED_MEAT.get(), 0.25f, 100, "cooked_minced_meat");
//        foodCampFire(recipeOutput, EXTRA_FOODS_COOKABLES, RecipeCategory.FOOD, ModItems.COOKED_MINCED_MEAT.get(), 0.25f, 100, "cooked_minced_meat");
//


//        foodSmelting(recipeOutput, EXTRA_FOODS_COOKABLES, RecipeCategory.FOOD, ModItems.COOKED_SAUSAGE.get(), 0.25f, 100, "cooking");
//        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, EXTRA_FOODS_COOKABLES, RecipeCategory.MISC, ModItems.RAW_PATTY.get(), 0.25f, 200, "", "");


    }

    // https://github.com/Tutorials-By-Kaupenjoe/NeoForge-Tutorial-1.21.X/blob/11-datagen/src/main/java/net/kaupenjoe/tutorialmod/datagen/ModRecipeProvider.java#L46-L65
    // Move the recipes out of the 'minecraft' folder in data and into the 'kcnet_mod' folder.
    protected static void oreSmelting(@NotNull RecipeOutput recipeOutput,
                                      List<ItemLike> pIngredients,
                                      @NotNull RecipeCategory pCategory,
                                      @NotNull ItemLike pResult,
                                      float pExperience, int pCookingTIme, @NotNull String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(@NotNull RecipeOutput recipeOutput, List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult,
                                      float pExperience, int pCookingTime, @NotNull String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    // Foods
    //
     //
    protected static void foodCooking(@NotNull RecipeOutput recipeOutput,
                                      List<ItemLike> pIngredients,
                                      @NotNull RecipeCategory pCategory,
                                      @NotNull ItemLike pResult,
                                      float pExperience, int pCookingTIme, @NotNull String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_cooking");
    }

    protected static void foodSmoking(@NotNull RecipeOutput recipeOutput,
                                       List<ItemLike> pIngredients,
                                       @NotNull RecipeCategory pCategory,
                                       @NotNull ItemLike pResult,
                                       float pExperience, int pCookingTIme, @NotNull String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smoking");
    }

    protected static void foodCampFire(@NotNull RecipeOutput recipeOutput,
                                      List<ItemLike> pIngredients,
                                      @NotNull RecipeCategory pCategory,
                                      @NotNull ItemLike pResult,
                                      float pExperience, int pCookingTIme, @NotNull String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_campfire_cooking");
    }

    // TODO Fix this later, not sure how to do it.
    // https://stackoverflow.com/questions/1759549/java-generics-multiple-generic-parameters
//    protected  static <S extends Recipe<RecipeInput>> void allFoodsCooking(@NotNull RecipeOutput recipeOutput,
//                                          // TODO Test this
//                                          RecipeSerializer<S> recipeSerializer,
//                                          List<ItemLike> pIngredients,
//                                          @NotNull RecipeCategory pCategory,
//                                          @NotNull ItemLike pResult,
//                                          float pExperience, int pCookingTIme, @NotNull String pGroup) {
//        oreCooking(recipeOutput, recipeSerializer, CampfireCookingRecipe::new, pIngredients, pCategory, pResult,
////        oreCooking(recipeOutput, RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, pIngredients, pCategory, pResult,
//                pExperience, pCookingTIme, pGroup, "_from_campfire_cooking");
//    }


    // https://github.com/vectorwing/FarmersDelight/blob/1.20/src/main/java/vectorwing/farmersdelight/data/recipe/SmeltingRecipes.java#L59-L70
//    private static void foodSmeltingRecipes(String name, ItemLike ingredient, ItemLike result, float experience, Consumer<FinishedRecipe> consumer) {
//        String namePrefix = new ResourceLocation(NeoForgeTest.MOD_ID, name).toString();
//        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 200)
//                .unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
//                .save(consumer);
//        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 600)
//                .unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
//                .save(consumer, namePrefix + "_from_campfire_cooking");
//        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 100)
//                .unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
//                .save(consumer, namePrefix + "_from_smoking");
//    }
    //

    protected static <T extends AbstractCookingRecipe> void oreCooking(@NotNull RecipeOutput recipeOutput,
                                                                       RecipeSerializer<T> pCookingSerializer,
                                                                       AbstractCookingRecipe.@NotNull Factory<T> factory,
                                                                       List<ItemLike> pIngredients,
                                                                       @NotNull RecipeCategory pCategory,
                                                                       @NotNull ItemLike pResult,
                                                                       float pExperience,
                                                                       int pCookingTime,
                                                                       @NotNull String pGroup,
                                                                       String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, NeoForgeTest.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
