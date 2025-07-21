package net.kelsoncraft.kcmod.commands; // New package for commands

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.kelsoncraft.kcmod.KCMod;
import net.kelsoncraft.kcmod.commands.teleport.CustomTeleportCommand;
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
        // TODO Test permissions later
        dispatcher.register(
                Commands.literal("kc") // Defines the root command: /kc
                        .then(Commands.literal("version") // Defines a subcommand: /kc version
                                .executes(KCCommands::executeVersionCommand) // Specifies the method to execute when this subcommand is run
                        )

                        // Position command test, teleport the player to the specified coordinates.
                        .then(Commands.literal("pos") // Defines subcommand: /kc pos
                                .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                                .then(Commands.argument("x", DoubleArgumentType.doubleArg())
                                        .then(Commands.argument("y", DoubleArgumentType.doubleArg())
                                                .then(Commands.argument("z", DoubleArgumentType.doubleArg())
                                                        .executes(CustomTeleportCommand::TeleportCommand)
                                                )
                                        )
                                )
                        )

                        // Display player XP on screen.
                        .then(Commands.literal("getxp").executes(MessageCommands::MessagePlayerXp))

                        // Display a test toast message
                        .then(Commands.literal("toast").executes(MessageCommands::MessageToastTest))

                        // Display a test popup message
                        .then(Commands.literal("popup").executes(MessageCommands::MessagePopupTest))

//                        .then(Commands.literal("spawnmob")
//                                .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
//                                .then(Commands.argument("x", DoubleArgumentType.doubleArg()) // Defines double argument 'x'
//                                        .then(Commands.argument("y", DoubleArgumentType.doubleArg()) // Defines double argument 'y'
//                                                .then(Commands.argument("z", DoubleArgumentType.doubleArg()) // Defines double argument 'z'
//                                        .executes(SummonCommand.createEntity(dispatcher, EntityType.ZOMBIE, ))



                // You can add more subcommands here if needed
        );
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
                .append(Component.literal(KCMod.MOD_NAME).withStyle(ChatFormatting.GOLD));

        // Version: (e.g., Aqua for label, Yellow for value)
        MutableComponent versionComponent = Component.literal("Version: ").withStyle(ChatFormatting.AQUA)
                .append(Component.literal(KCMod.MOD_VERSION).withStyle(ChatFormatting.YELLOW));

        // Description: (e.g., Aqua for label, Light Purple for value)
        MutableComponent descriptionComponent = Component.literal("Description: ").withStyle(ChatFormatting.AQUA)
                .append(Component.literal(KCMod.MOD_DESCRIPTION).withStyle(ChatFormatting.LIGHT_PURPLE));

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
