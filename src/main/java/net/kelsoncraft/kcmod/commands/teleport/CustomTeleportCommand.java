package net.kelsoncraft.kcmod.commands.teleport;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.kelsoncraft.kcmod.util.MiscUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.coordinates.Coordinates;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CustomTeleportCommand {

    // TODO Possibly move this into TeleportCommands later, come up with a new name for this file and command.
    public static int teleportCommand(CommandContext<CommandSourceStack> command) {
        MiscUtil miscUtil = new MiscUtil();
        Entity playerEntity = command.getSource().getEntity();
        if(playerEntity instanceof Player player) {
            Coordinates coords = Vec3Argument.getCoordinates(command, "coords");
            Vec3 pos = coords.getPosition(command.getSource());

            teleportPlayer(command.getSource(), playerEntity, pos);
            command.getSource().sendSuccess(() -> Component.literal("Teleported to "
                            + miscUtil.truncateNumbers(pos.x) + ", "
                            + miscUtil.truncateNumbers(pos.y) + ", "
                            + miscUtil.truncateNumbers(pos.z))
                    .withStyle(ChatFormatting.GREEN), false);
        } else {
            // If the command was not executed by a player, send an error message
            command.getSource().sendFailure(Component.literal("This command can only be used by a player!").withStyle(ChatFormatting.RED));
            return -1; // Indicate command failure
        }

        return Command.SINGLE_SUCCESS;
    }

    private static void teleportPlayer(CommandSourceStack source,
                                       Entity entity,
                                       Vec3 pos) {

        if(entity instanceof Player player) {
            BlockPos blockpos = BlockPos.containing(pos.x, pos.y, pos.z);
            if (Level.isInSpawnableBounds(blockpos)) {
                entity.teleportTo(pos.x, pos.y, pos.z);
            }
        }

    }
}
