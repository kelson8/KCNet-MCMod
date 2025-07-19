package net.kelsoncraft.neoforgetest.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.kelsoncraft.neoforgetest.util.XPUtilities;
import net.kelsoncraft.neoforgetest.util.MessageUtil;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class MessageCommands {
    // TODO Setup this to send a success message to the player or something.


    /// @param player The player to send the message to
    /// @param text The text to send, like Component.text()
//    public static void MessagePlayer(Player player, Component text) {
//        player.sendSystemMessage(text);
//    }

    /// Send a test toast message to the player.
    public static int MessageToastTest(CommandContext<CommandSourceStack> context) {
        Entity currentEntity = context.getSource().getEntity();
        if (currentEntity instanceof Player player) {
            MessageUtil.SendToastMessage((ServerPlayer) player, "Test Toast", "This is a test toast message");
        }

        return Command.SINGLE_SUCCESS;
    }

    ///  Send a test popup message to the player.
    public static int MessagePopupTest(CommandContext<CommandSourceStack> context) {
        Entity currentEntity = context.getSource().getEntity();
        if (currentEntity instanceof Player player) {
            MessageUtil.SendPopupMessage((ServerPlayer) player, "Test popup", "This is a test popup message");
        }

        return Command.SINGLE_SUCCESS;
    }


    /// Send a toast message on screen with the player XP
    public static int MessagePlayerXp(CommandContext<CommandSourceStack> context) {
        Entity currentEntity = context.getSource().getEntity();
        CommandSourceStack source = context.getSource();

//        ImmersiveMessageApi immersiveMessageTest = new ImmersiveMessageApi();

        // TODO Get the message to display for this from the command if needed, make a helper method for this
        if (currentEntity instanceof Player player) {
            int currentXP = XPUtilities.getPlayerXP(player);
            int currentLevels = XPUtilities.getLevelForExperience(currentXP);

            MessageUtil.SendToastMessage((ServerPlayer) player, "Current XP level", String.valueOf(currentLevels));


//            MutableComponent xpComponent = Component.literal("XP Level: ").withStyle(ChatFormatting.AQUA)
//                    .append(Component.literal(String.valueOf(currentLevels)).withStyle(ChatFormatting.GOLD));
//                    .append(Component.literal(String.valueOf(XPUtilities.getPlayerXP(player))).withStyle(ChatFormatting.GOLD));

//            source.sendSuccess(() -> xpComponent, false);
        }

        return Command.SINGLE_SUCCESS;
    }
}
