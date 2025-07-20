package net.kelsoncraft.neoforgetest.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.kelsoncraft.neoforgetest.Config;
import net.kelsoncraft.neoforgetest.util.LogUtil;
import net.kelsoncraft.neoforgetest.util.MessageUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.GameType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class GamemodeCommands {

    // Replicate game mode commands from Essentials

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {

        // Gamemode commands
        // Creative: /gmc, /creative, /gm c
        // Survival: /gms, /survival, /gm s
        // Adventure: /gma, /adventure, /gm a
        // Spectator: /gmsp, /spectator, /gm sp
        GamemodeCommands.GamemodeCmds(event.getDispatcher());

    }


    /// Essentials like commands for singleplayer, this should work on the server, but I haven't tested it.
    /// These can be turned on/off in game with the config, and if you exit the world and rejoin, it will toggle the commands.
    public static void GamemodeCmds(CommandDispatcher<CommandSourceStack> dispatcher) {

        // This could possibly be used to override the original gamemode command.
//        LiteralArgumentBuilder<CommandSourceStack> gamemodeCommands = Commands.literal("gamemode")
//                .requires(sourceStack -> sourceStack.hasPermission(2));

//                .executes(context -> {
//                context.getSource().sendFailure(Component.literal("Usage: /gamemode <type>").withStyle(ChatFormatting.RED));
//                return 0;
//                });

        // Register the base command
//        dispatcher.register(gamemodeCommands);

        //---
        // Creative
        //---

        // Register aliases that point to the same command logic
//        gamemodeCommands.then(Commands.literal("creative"))
//                .executes(command -> creativeCommand(command.getSource()));

//        NeoForgeTest.LOGGER.info("Registered command alias: /creative");


//        dispatcher.register(Commands.literal(""))

        if (Config.COMMON.ENABLE_CREATIVE_COMMAND.get()) {

            dispatcher.register(Commands.literal("gmc") // Alias /gmc
                    .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                    .executes(command -> creativeCommand(command.getSource())));

            dispatcher.register(Commands.literal("creative") // Alias /creative
                    .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                    .executes(command -> creativeCommand(command.getSource())));

            dispatcher.register(Commands.literal("gm") // Alias /gm c
                    .then(Commands.literal("c")
                            .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                            .executes(command -> creativeCommand(command.getSource()))
                    ));

//            LogUtil.LogInfo("Registered creative gamemode aliases (/gmc, /creative, /gm c).");
        } else {
            LogUtil.LogInfo("Creative gamemode aliases are disabled in config.");
        }

        //---
        // Survival
        //---


//        gamemodeCommands.then(Commands.literal("survival"))
//                .executes(command -> survivalCommand(command.getSource()));

        if (Config.COMMON.ENABLE_SURVIVAL_COMMAND.get()) {
            dispatcher.register(Commands.literal("gms")
                    .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                    .executes(command -> survivalCommand(command.getSource())));

            // Separate '/survival' command
            dispatcher.register(Commands.literal("survival")
                    .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                    .executes(command -> survivalCommand(command.getSource()))
            );

            dispatcher.register(Commands.literal("gm")
                    .then(Commands.literal("s")
                            .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                            .executes(command -> survivalCommand(command.getSource()))
                    ));

//        LogUtil.LogInfo("Registered survival gamemode aliases (/gms, /survival, /gm s).");
        } else {
            LogUtil.LogInfo("Survival gamemode aliases are disabled in config.");
        }


        //---
        // Adventure
        //---

//        gamemodeCommands.then(Commands.literal("adventure")
//                .executes(command -> adventureCommand(command.getSource())));

        if (Config.COMMON.ENABLE_ADVENTURE_COMMAND.get()) {
            dispatcher.register(Commands.literal("gma")
                    .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                    .executes(command -> adventureCommand(command.getSource())));

            dispatcher.register(Commands.literal("adventure")
                    .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                    .executes(command -> adventureCommand(command.getSource())));

            dispatcher.register(Commands.literal("gm")
                    .then(Commands.literal("a")
                            .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                            .executes(command -> adventureCommand(command.getSource()))
                    ));

        } else {
            LogUtil.LogInfo("Adventure gamemode aliases are disabled in config.");
        }


        //---
        // Spectator
        //---

//        gamemodeCommands.then(Commands.literal("spectator")
//                .executes(command -> spectatorCommand(command.getSource())));

        if (Config.COMMON.ENABLE_SPECTATOR_COMMAND.get()) {
            dispatcher.register(Commands.literal("gmsp")
                    .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                    .executes(command -> spectatorCommand(command.getSource())));

            dispatcher.register(Commands.literal("spectator")
                    .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                    .executes(command -> spectatorCommand(command.getSource())));

            dispatcher.register(Commands.literal("gm")
                    .then(Commands.literal("sp")
                            .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                            .executes(command -> spectatorCommand(command.getSource()))
                    ));

//            LogUtil.LogInfo("Registered spectator gamemode aliases (/gmsp, /spectator, /gm sp).");
        } else {
            LogUtil.LogInfo("Spectator gamemode aliases are disabled in config.");
        }


        // Register the main /gamemode command with the sub commands
//        dispatcher.register(gamemodeCommands);
    }

    //-----
    // Commands
    //-----

    // Creative
    private static int creativeCommand(CommandSourceStack source) {
        setGameMode(source, GameType.CREATIVE);
        return Command.SINGLE_SUCCESS;
    }

    // TODO Set these below to set the player on the ground if in creative, I've done it in Spigot.
    // Survival
    private static int survivalCommand(CommandSourceStack source) {
        setGameMode(source, GameType.SURVIVAL);
        return Command.SINGLE_SUCCESS;
    }

    // Adventure
    private static int adventureCommand(CommandSourceStack source) {
        setGameMode(source, GameType.ADVENTURE);
        return Command.SINGLE_SUCCESS;
    }

    // Spectator
    private static int spectatorCommand(CommandSourceStack source) {
        setGameMode(source, GameType.SPECTATOR);
        return Command.SINGLE_SUCCESS;
    }

    //-----
    // Functions
    //-----

    private static void setGameMode(CommandSourceStack source, GameType gameMode) {
        Entity entity = source.getEntity();
        if (entity instanceof ServerPlayer player) {

            if (player.gameMode.getGameModeForPlayer() == gameMode) {
                source.sendFailure(Component.literal("You are already in " + gameMode.getName() + " mode!").withStyle(ChatFormatting.RED));
                return;
            }

            player.setGameMode(gameMode);

            switch (gameMode) {
                case CREATIVE -> MessageUtil.SendMessage(player, "Set gamemode to creative");
                case SURVIVAL -> MessageUtil.SendMessage(player, "Set gamemode to survival");
                case ADVENTURE -> MessageUtil.SendMessage(player, "Set gamemode to adventure");
                case SPECTATOR -> MessageUtil.SendMessage(player, "Set gamemode to spectator");

                default -> MessageUtil.SendChatMessage(source, "Error: Game mode invalid");
            }

            MessageUtil.SendMessage(player, "Set gamemode to creative");
        }
    }

}
