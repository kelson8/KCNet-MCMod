package net.kelsoncraft.kcmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

// https://www.youtube.com/watch?v=Q45WrphLWXE&list=PLKGarocXCE1G6CQOoiYdMVx-E1d9F_itF&index=8

public class ModFoodProperties {


    // TODO Move this
    // This can make a drink instead of a food.
    // Goes into another custom item class, possibly make custom/RadishItem?
//    @Override
//    public UseAnim getUseAnimation(ItemStack stack) {
//        return UseAnim.DRINK;
//    }

    // Hmm, I could make this evil lol
    // Look into the MobEffects.java class.
    // Also look at the Foods.java class for default food values.

    // Radish
    public static final FoodProperties RADISH = new FoodProperties.Builder()
            .nutrition(3)
//            .saturationModifier(0.25f)
            .saturationModifier(0.8F)

            // Can eat if not hungry
//            .alwaysEdible()

            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 0.35f).build();

    // TODO Add smelting recipes, and smoking recipes.
    // C:\Users\kelson\Documents\git\minecraft\mods\assets\free_textures\futureazoo\items\food\to_pack
    // Burger
    public static final FoodProperties BURGER = new FoodProperties.Builder()
            .nutrition(8)
            .saturationModifier(0.6f)
            .build();

    // Chili Pepper
    // This one may be a bit overpowered, I'll probably modify it later.
    public static final FoodProperties CHILI_PEPPER = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.25f)
            // Chance to give regeneration and health boost.
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200, 4), 0.35f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 200, 4), 0.35f)
            .alwaysEdible()
            .fast()
            .build();

    // Minced Meat Raw
    public static final FoodProperties RAW_MINCED_MEAT = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.25f)
            .build();

    // Minced Meat Cooked
    public static final FoodProperties COOKED_MINCED_MEAT = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.6f)
            .build();

    // Raw Hamburger Patty
    public static final FoodProperties RAW_PATTY = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.25f)
            .build();


    // Cooked Hamburger Patty
    public static final FoodProperties COOKED_PATTY = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.6f)
            .build();

    // Sausage Raw

    public static final FoodProperties RAW_SAUSAGE = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.25f)
            .build();

    // Sausage Cooked
    public static final FoodProperties COOKED_SAUSAGE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.6f)
            .build();

}
