package net.kelsoncraft.kcmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.kelsoncraft.kcmod.Config;
import net.kelsoncraft.kcmod.KCMod;
import net.kelsoncraft.kcmod.util.LogUtil;
import net.kelsoncraft.kcmod.util.MessageUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.GameType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import static net.kelsoncraft.kcmod.util.CommandUtil.registerCommandOp;
import static net.kelsoncraft.kcmod.util.CommandUtil.registerCommandWithArg;

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

        if (Config.COMMON.ENABLE_CREATIVE_COMMAND.get()) {

            // Creative commands
            registerCommandOp(dispatcher, "gmc", GamemodeCommands::creativeCommand);
            registerCommandOp(dispatcher, "creative", GamemodeCommands::creativeCommand);
            registerCommandWithArg(dispatcher, "gm", "c", GamemodeCommands::creativeCommand);
            KCMod.LOGGER.info("Registered creative gamemode aliases (/gmc, /creative, /gm c).");

//            LogUtil.LogInfo("Registered creative gamemode aliases (/gmc, /creative, /gm c).");
        } else {
            LogUtil.logInfo("Creative gamemode aliases are disabled in config.");
        }

        //---
        // Survival
        //---

        if (Config.COMMON.ENABLE_SURVIVAL_COMMAND.get()) {
            // Survival commands
            registerCommandOp(dispatcher, "gms", GamemodeCommands::survivalCommand);
            registerCommandOp(dispatcher, "survival", GamemodeCommands::survivalCommand);
            registerCommandWithArg(dispatcher, "gm", "s", GamemodeCommands::survivalCommand);
            KCMod.LOGGER.info("Registered survival gamemode aliases (/gms, /survival, /gm s).");

//        LogUtil.LogInfo("Registered survival gamemode aliases (/gms, /survival, /gm s).");
        } else {
            LogUtil.logInfo("Survival gamemode aliases are disabled in config.");
        }


        //---
        // Adventure
        //---

        if (Config.COMMON.ENABLE_ADVENTURE_COMMAND.get()) {

            // Adventure commands
            registerCommandOp(dispatcher, "gma", GamemodeCommands::adventureCommand);
            registerCommandOp(dispatcher, "adventure", GamemodeCommands::adventureCommand);
            registerCommandWithArg(dispatcher, "gm", "a", GamemodeCommands::adventureCommand);
            KCMod.LOGGER.info("Registered adventure gamemode aliases (/gma, /adventure, /gm a).");

        } else {
            LogUtil.logInfo("Adventure gamemode aliases are disabled in config.");
        }


        //---
        // Spectator
        //---

        if (Config.COMMON.ENABLE_SPECTATOR_COMMAND.get()) {
            // Adventure commands
            registerCommandOp(dispatcher, "gmsp", GamemodeCommands::spectatorCommand);
            registerCommandOp(dispatcher, "spectator", GamemodeCommands::spectatorCommand);
            registerCommandWithArg(dispatcher, "gm", "sp", GamemodeCommands::spectatorCommand);
            KCMod.LOGGER.info("Registered creative gamemode aliases (/gmsp, /spectator, /gm sp).");

//            LogUtil.LogInfo("Registered spectator gamemode aliases (/gmsp, /spectator, /gm sp).");
        } else {
            LogUtil.logInfo("Spectator gamemode aliases are disabled in config.");
        }


        // Register the main /gamemode command with the sub commands
//        dispatcher.register(gamemodeCommands);
    }

    //-----
    // Commands
    //-----

    // Creative
//    private static int creativeCommand(CommandSourceStack source) {
    private static int creativeCommand(CommandContext<CommandSourceStack> context) {
        setGameMode(context.getSource(), GameType.CREATIVE);
        return Command.SINGLE_SUCCESS;
    }

    // TODO Set these below to set the player on the ground if in creative, I've done it in Spigot.
    // Survival
    private static int survivalCommand(CommandContext<CommandSourceStack> context) {
        setGameMode(context.getSource(), GameType.SURVIVAL);
        return Command.SINGLE_SUCCESS;
    }

    // Adventure
    private static int adventureCommand(CommandContext<CommandSourceStack> context) {
        setGameMode(context.getSource(), GameType.ADVENTURE);
        return Command.SINGLE_SUCCESS;
    }

    // Spectator
    private static int spectatorCommand(CommandContext<CommandSourceStack> context) {
        setGameMode(context.getSource(), GameType.SPECTATOR);
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
