package net.kelsoncraft.neoforgetest.commands; // New package for commands

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.kelsoncraft.neoforgetest.NeoForgeTest;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
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

        //---- Mod info
        // Name: (e.g., Aqua for label, Gold for value)
        MutableComponent nameComponent = Component.literal("Name: ").withStyle(ChatFormatting.AQUA)
                .append(Component.literal(NeoForgeTest.MOD_NAME).withStyle(ChatFormatting.GOLD));

        // Version: (e.g., Aqua for label, Yellow for value)
        MutableComponent versionComponent = Component.literal("Version: ").withStyle(ChatFormatting.AQUA)
                .append(Component.literal(NeoForgeTest.MOD_VERSION).withStyle(ChatFormatting.YELLOW));

        // Description: (e.g., Aqua for label, Light Purple for value)
        MutableComponent descriptionComponent = Component.literal("Description: ").withStyle(ChatFormatting.AQUA)
                .append(Component.literal(NeoForgeTest.MOD_DESCRIPTION).withStyle(ChatFormatting.LIGHT_PURPLE));

        //---- Send messages

        // --- Mod Details --- (e.g., Green)
        source.sendSuccess(() -> Component.literal("--- Mod Details ---").withStyle(ChatFormatting.GREEN), false);

        source.sendSuccess(() -> nameComponent, false);
        source.sendSuccess(() -> versionComponent, false);
        source.sendSuccess(() -> descriptionComponent, false);

        source.sendSuccess(() -> Component.literal("-------------------").withStyle(ChatFormatting.GREEN), false);

        return Command.SINGLE_SUCCESS;
    }
}
