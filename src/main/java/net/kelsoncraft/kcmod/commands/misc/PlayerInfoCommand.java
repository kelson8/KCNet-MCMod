package net.kelsoncraft.kcmod.commands.misc;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.kelsoncraft.kcmod.commands.MessageCommands;
import net.kelsoncraft.kcmod.util.Messages;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class PlayerInfoCommand {


    /**
     * Basic player info command, shows a few stats for the player.
     *
     * @param context The command context
     * @return Command success.
     */
    public static int playerInfoCommand(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        Entity entity = source.getEntity();

//        if(command.getSource().getEntity() instanceof Player player) {
        if(source.getEntity() instanceof Player player) {
//            UUID uuid = player.getGameProfile().getId();
            float playerHealth = player.getHealth();
            boolean isPlayerInWater = player.isInWater();
            double playerX = player.getX();
            double playerY = player.getY();
            double playerZ = player.getZ();

//            player.sendSystemMessage(Component.literal(Messages.KCNetMain + "Your uuid is: " + uuid));
//            player.sendSystemMessage(Component.literal(Messages.KCNetMain + "Your health is: " ).withStyle(ChatFormatting.AQUA)
//                    .append(Component.literal(String.valueOf(playerHealth)).withStyle(ChatFormatting.GOLD)
//                    ));

            MessageCommands.sendColoredMessage(source,
                    // Prefix
                    Component.literal(Messages.KCNetMain).withStyle(ChatFormatting.GRAY),

                    // UUID line
//                    Component.literal("Your UUID is: ").withStyle(ChatFormatting.AQUA),
//                    Component.literal(uuid).withStyle(ChatFormatting.GOLD),
//                    Component.literal("\n"), // Newline for the next line

                    // Health line
                    Component.literal("Your health is: ").withStyle(ChatFormatting.AQUA),
                    Component.literal(String.valueOf(playerHealth)).withStyle(ChatFormatting.GOLD),
                    Component.literal("\n"), // Newline

                    // Coordinates line
                    Component.literal("Your coordinates is: ").withStyle(ChatFormatting.AQUA),
                    Component.literal("X: ").withStyle(ChatFormatting.AQUA),
                    Component.literal(String.format("%.3f", playerX)).withStyle(ChatFormatting.YELLOW), // Formatted to 3 decimal places
                    Component.literal(", Y: ").withStyle(ChatFormatting.AQUA),
                    Component.literal(String.format("%.3f", playerY)).withStyle(ChatFormatting.YELLOW), // Formatted to 3 decimal places
                    Component.literal(", Z: ").withStyle(ChatFormatting.AQUA),
                    Component.literal(String.format("%.3f", playerZ)).withStyle(ChatFormatting.YELLOW)  // Formatted to 3 decimal places
            );

//            player.sendSystemMessage(Component.literal(Messages.KCNetMain + "X: " + playerX));
//            player.sendSystemMessage(Component.literal(Messages.KCNetMain + "Y: " + playerY));
//            player.sendSystemMessage(Component.literal(Messages.KCNetMain + "Z: " + playerZ));
        }

        return Command.SINGLE_SUCCESS;
    }

}
