package net.kelsoncraft.neoforgetest.commands.teleport;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class CustomTeleportCommand {

    public static int TeleportCommand(CommandContext<CommandSourceStack> command) throws CommandSyntaxException {
        Entity playerEntity = command.getSource().getEntity();
//        if(command.getSource().getEntity() instanceof Player player) {
        if(playerEntity instanceof Player player) {
//            command.getSource().getEntity().teleportTo();
            double x = DoubleArgumentType.getDouble(command, "x");
            double y = DoubleArgumentType.getDouble(command, "y");
            double z = DoubleArgumentType.getDouble(command, "z");

            teleportPlayer(command.getSource(), playerEntity, x, y, z);
            command.getSource().sendSuccess(() -> Component.literal("Teleported to " + x + ", " + y + ", " + z).withStyle(ChatFormatting.GREEN), false);
        } else {
            // If the command was not executed by a player, send an error message
            command.getSource().sendFailure(Component.literal("This command can only be used by a player!").withStyle(ChatFormatting.RED));
            return -1; // Indicate command failure
        }

        return Command.SINGLE_SUCCESS;
    }

    private static void teleportPlayer(CommandSourceStack source,
                                       Entity entity,

                                       double x,
                                       double y,
                                       double z) {

        if(entity instanceof Player player) {
            BlockPos blockpos = BlockPos.containing(x, y, z);
            if (Level.isInSpawnableBounds(blockpos)) {
                entity.teleportTo(x, y, z);
            }
        }

    }
}
