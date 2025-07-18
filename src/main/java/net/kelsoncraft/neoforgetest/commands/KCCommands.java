package net.kelsoncraft.neoforgetest.commands; // New package for commands

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.kelsoncraft.neoforgetest.NeoForgeTest;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.bus.api.SubscribeEvent;

// Import the main mod class to access static properties

// Created with help from Google Gemini, I couldn't figure this out.

public class KCCommands {

    /**
     * Event handler for registering commands.
     * This method is subscribed to the RegisterCommandsEvent, which is fired when commands are being registered.
     * It must be static if registered via `NeoForge.EVENT_BUS.register(KCCommands.class)`.
     *
     * @param event The RegisterCommandsEvent instance.
     */
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        // Get the command dispatcher from the event
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        // Register the '/kc' command
        dispatcher.register(
                Commands.literal("kc") // Defines the root command: /kc
                        .then(Commands.literal("version") // Defines a subcommand: /kc version
                                .executes(KCCommands::executeVersionCommand) // Specifies the method to execute when this subcommand is run
                        )
                // You can add more subcommands here if needed
        );
        NeoForgeTest.LOGGER.info("Registered command: /kc version"); // Use the main mod's logger
    }

    /**
     * Executes the '/kc version' command.
     * This method is called when a player runs '/kc version'.
     *
     * @param context The command context, providing access to the command source.
     * @return The result of the command execution (typically 1 for success).
     */
    private static int executeVersionCommand(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();

        // Access the static properties from NeoForgeTest
        source.sendSuccess(() -> Component.literal("--- " + NeoForgeTest.MOD_NAME + " Details ---"), false);
        source.sendSuccess(() -> Component.literal("Name: " + NeoForgeTest.MOD_NAME), false);
        source.sendSuccess(() -> Component.literal("Version: " + NeoForgeTest.MOD_VERSION), false);
        source.sendSuccess(() -> Component.literal("Description: " + NeoForgeTest.MOD_DESCRIPTION), false);
        source.sendSuccess(() -> Component.literal("-------------------"), false);

        return Command.SINGLE_SUCCESS; // Indicate that the command executed successfully
    }
}
