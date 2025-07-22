package net.kelsoncraft.kcmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.kelsoncraft.kcmod.util.LogUtil;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class SpeedCommands {

    // For registering the commands, combined from GamemodeCommands
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
//        SpeedCommands.SpeedCmds(event.getDispatcher());
        // Get the command dispatcher from the event
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        // Register the '/speed' command
        // TODO Fix this to work right.
        dispatcher.register(
                Commands.literal("speed")
                        // Walk speed
                        .then(Commands.literal("walk")
                                .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                                .executes(SpeedCommands::walkSpeedCommand))

                        // Fly speed
                        .then(Commands.literal("fly")
                                .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                                .executes(SpeedCommands::flySpeedCommand))
        );
    }

    private static int walkSpeedCommand(CommandContext<CommandSourceStack> context) {
        if (context.getSource().getEntity() instanceof ServerPlayer player) {
            player.sendSystemMessage(Component.literal("Walk speed test"));

            LogUtil.logInfo("Walk speed test ran.");
        }

        return Command.SINGLE_SUCCESS;

    }


    private static int flySpeedCommand(CommandContext<CommandSourceStack> context) {
        if (context.getSource().getEntity() instanceof ServerPlayer player) {
            player.sendSystemMessage(Component.literal("Fly speed test"));

            LogUtil.logInfo("Fly speed test ran.");
        }

        return Command.SINGLE_SUCCESS;

    }

}
