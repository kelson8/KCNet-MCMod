package net.kelsoncraft.neoforgetest.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class PutPlayerInSkyMixin {

    @Unique
    boolean neoForge_Test_1_21_1$isInvincible;


    // This works when most items are right-clicked.
    // Hmm, I think this runs twice on most items.
//    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    @Inject(method = "use", at = @At("HEAD"))
    public void useTest(Level level, Player player, InteractionHand usedHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
//        player.kill();

        if (player != null) {
            Vec3 playerPos = player.position();
            double newY = playerPos.y + 50;

            // Set the player into the air, well this works lol.
            // I need to add a cooldown to this... It can be spammed

            // I added a limit of 255 for this, that seems to work
            // Added -58 for bedrock build limit, should never happen here but might as well add it just in case.
            if (newY <= 255 && newY >= -58) {
                player.setPos(playerPos.x, newY, playerPos.z);
            }

            // Well this just makes me invincible forever.
            // Was I missing this inversion?
            neoForge_Test_1_21_1$isInvincible = !neoForge_Test_1_21_1$isInvincible;
            player.setInvulnerable(neoForge_Test_1_21_1$isInvincible);

            // Hmm, both of these seem to run.
//            if (player.isInvulnerable()) {
//                player.sendSystemMessage(Component.literal("You have teleported into the sky, and are now are invincible."));
//            } else {
//                player.sendSystemMessage(Component.literal("You have teleported into the sky, and are no longer invincible."));
//            }

        }
    }

}
