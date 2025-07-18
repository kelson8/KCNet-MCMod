package net.kelsoncraft.neoforgetest.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

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

    public static final FoodProperties RADISH = new FoodProperties.Builder()
            .nutrition(3)
//            .saturationModifier(0.25f)
            .saturationModifier(0.8F)

            // Can eat if not hungry
//            .alwaysEdible()

            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 0.35f).build();



}
