package net.kelsoncraft.kcmod.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class FlySpeedMixin {


    // Test for changing players fly speed, this permanently changes the fly speed, I would need to fix that.
    @Inject(method = "use", at = @At("HEAD"))

    public void useTest(Level level, Player player, InteractionHand usedHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        if (player != null) {

            //---
            // Fly speed testing
            //---
//            player.getAbilities().setFlyingSpeed(randomFlyingSpeed);
//            float currentFlyingSpeed = player.getAbilities().getFlyingSpeed();
//        player.getPosition(0.5f);

        }
    }



}
