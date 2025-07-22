package net.kelsoncraft.kcmod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.datafixers.types.templates.Sum;
import net.kelsoncraft.kcmod.commands.misc.*;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class MiscCommands {

    // For registering the commands.
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {

        // TODO Switch these to using this method
        //----

        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        // For using this with multiple arguments:
        /*
        dispatcher.register(
                Commands.literal("speed")
                        // Walk speed
                        .then(Commands.literal("")
                                .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                                .executes(SpeedCommands::walkSpeedCommand))

                        // Fly speed
                        .then(Commands.literal("fly")
                                .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                                .executes(SpeedCommands::flySpeedCommand))
        );

         */

        // For using this with single arguments:
        /*
                dispatcher.register(
                Commands.literal("heal")
                    .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                    .executes(HealCommand::healCommand))

        );
         */


        // Player info command
        // '/playerinfo'
//        new PlayerInfoCommand(event.getDispatcher());

        dispatcher.register(Commands.literal("playerinfo")
                .executes(PlayerInfoCommand::playerInfoCommand));

        // Summon mob command
        // '/summon_mob', just setup to spawn a wolf for now.
        dispatcher.register(
                Commands.literal("summon_mob")
                        .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                                .executes(SummonMobCommand::summonCommand));

        // '/heal' command
        dispatcher.register(
                Commands.literal("heal")
                        .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                        .executes(HealCommand::healCommand));


        // '/give_item' command, for now this just gives acacia logs, I would like to replicate the '/i' command in Essentials
        // So if you type '/i oaklog' or something it'll give you that item
        dispatcher.register(
                Commands.literal("give_item")
                .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .executes(GiveTestCommand::giveItemCommand));

        // This needs enabled in the FlyCommand class to work, I may switch everything over to in here like my SpeedCommands.java is.
        // '/fly' command, for now this will only display the block ground position if this is working.
        // Later on, I'll add flying by recreating it from my KBP plugin on Spigot.
        // Well the block ground position I thought I found didn't work, I'm not sure how to do it in NeoForge.
        // https://github.com/kelson8/KBP/blob/master/src/main/java/net/Kelsoncraft/KBP/commands/FlyCommand.java
//        new FlyCommand(event.getDispatcher());

        dispatcher.register(
                Commands.literal("fly")
                        .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                        .executes(FlyCommand::flyCommand));


        // '/spawn' command, this mostly seems to work fine.
        // Send the player to the world spawn.
//        new SpawnCommand(event.getDispatcher());

        dispatcher.register(Commands.literal("spawn")
                .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .executes(SpawnCommand::spawnCommand)
        );

        // '/lightning' command, strike lightning where the player is looking.
//        new LightningCommand(event.getDispatcher());

        dispatcher.register(Commands.literal("lightning")
                .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .executes(LightningCommand::lightningCommand));

        // '/smite' alias
        dispatcher.register(Commands.literal("smite")
                .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .executes(LightningCommand::lightningCommand));

    }
}


