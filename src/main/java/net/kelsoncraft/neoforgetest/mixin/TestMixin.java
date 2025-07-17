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

// Good YouTube guide for Mixins: https://www.youtube.com/watch?v=Q5041hErnvA
// Fabric Wiki for Mixins: https://wiki.fabricmc.net/tutorial:mixin_injects

// Random for java: https://stackoverflow.com/questions/40431966/what-is-the-best-way-to-generate-a-random-float-value-included-into-a-specified

// The Mixins json file has to go in the resources folder.

@Mixin(Item.class)
public class TestMixin {


    // This is annoying so I disabled it.
//    @Unique
//    float flySpeedMin = 0.5f;
//    @Unique
//    float flySpeedMax = 1.0f;
//    Random r = new Random();
//    float randomFlyingSpeed = flySpeedMin + r.nextFloat() * (flySpeedMax - flySpeedMin);

    @Unique
    boolean neoForge_Test_1_21_1$isInvincible;


    // Specify where to inject this method into the code with at

    @Inject(method = "use", at = @At("HEAD"))
    public void useTest(Level level, Player player, InteractionHand usedHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        if (player != null) {
            Vec3 playerPos = player.position();

            // Powder snow toggle test
//            player.setIsInPowderSnow(!neoForge_Test_1_21_1$testBoolean);

            //---
            // Message testing
            //---

            // TODO Figure out how to send a message to the player
            // https://www.reddit.com/r/MinecraftMod/comments/17i6keo/send_chat_message_1202_forge/

            // This works, but it displays the message like the player said it in chat for everyone, not just to the current player.
//            PlayerChatMessage chatMessage = PlayerChatMessage.unsigned(player.getUUID(), "Your flying speed is now " + currentFlyingSpeed);

//            player.createCommandSourceStack().sendChatMessage(
//                    new OutgoingChatMessage.Player(chatMessage), false, ChatType.bind(ChatType.CHAT, player));

        }
    }

}
