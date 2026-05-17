package net.kelsoncraft.kcmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.kelsoncraft.kcmod.Config;
import net.kelsoncraft.kcmod.util.*;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import org.spongepowered.asm.mixin.Unique;

public class SpeedCommands {

    // For registering the commands, combined from GamemodeCommands
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
//        SpeedCommands.SpeedCmds(event.getDispatcher());
        // Get the command dispatcher from the event
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        // Register the '/speed' command
        // TODO Fix this to work right.

        // I honestly forgot about creating the CommandUtil class, this should help out quite a bit.
        // This is still giving an error, so I disabled it.
        // Gives this below error:
        // No such argument 'fly' exists on this command
//        CommandUtil.registerCommandWithArg(dispatcher, "speed", "fly", SpeedCommands::flySpeedCommand);
//        CommandUtil.registerCommandWithArg(dispatcher, "speed", "walk", SpeedCommands::walkSpeedCommand);

//        dispatcher.register(
//                Commands.literal("speed")
//
//                        .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
//                        // Walk speed
//                        .then(Commands.literal("walk")
//
//                                .executes(SpeedCommands::walkSpeedCommand))
//
//                        // Fly speed
//                        // TODO Fix this to work..
//                        // Why does command arguments not work here? It works in Neo Essentials...
//                        // I'll deal with this later, I thought I could've fixed it.
//                        .then(Commands.literal("fly")
//                                .then(Commands.argument("speed", FloatArgumentType.floatArg(0f, 10f)))
//                                .executes(ctx -> flySpeedCommand(ctx, FloatArgumentType.getFloat(ctx, "speed"))))
//        );
    }

    /**
     * Set the players walk speed
     */
    private static int walkSpeedCommand(CommandContext<CommandSourceStack> context) {
        if (context.getSource().getEntity() instanceof ServerPlayer player) {
            player.sendSystemMessage(Component.literal("Walk speed test"));

            LogUtil.logInfo("Walk speed test ran.");
        }

        return Command.SINGLE_SUCCESS;

    }

    // TODO Adapt this with values from FlySpeedMixin, I have to figure out command arguments again.
//    private static int flySpeedCommand(CommandContext<CommandSourceStack> context, int flyingSpeed) {
    private static int flySpeedCommand(CommandContext<CommandSourceStack> context, float speed) {

        if (context.getSource().getEntity() instanceof ServerPlayer player) {
//            context.getArgument("speed", Integer.class);


//            float flyingSpeed = FloatArgumentType.getFloat(context, "speed");

            // Moved to this function
            PlayerUtil.setSpeed(player, PlayerUtil.SpeedType.FLY, speed);
        }

        return Command.SINGLE_SUCCESS;

    }

}
