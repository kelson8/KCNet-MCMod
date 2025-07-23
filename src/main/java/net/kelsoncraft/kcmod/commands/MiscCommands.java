package net.kelsoncraft.kcmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.datafixers.types.templates.Sum;
import net.kelsoncraft.kcmod.commands.misc.*;
import net.kelsoncraft.kcmod.util.CommandUtil;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.execution.CustomCommandExecutor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class MiscCommands {

    // For registering the commands.
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
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


        // Player info command '/playerinfo'
        CommandUtil.registerCommandOp(dispatcher, "playerinfo", PlayerInfoCommand::playerInfoCommand);

        // Summon mob command
        // '/summon_mob', just setup to spawn a wolf for now.
        CommandUtil.registerCommandOp(dispatcher, "summon_mob", SummonMobCommand::summonCommand);

        // '/heal' command
        CommandUtil.registerCommandOp(dispatcher, "heal", HealCommand::healCommand);


        // '/give_item' command, for now this just gives acacia logs, I would like to replicate the '/i' command in Essentials
        // So if you type '/i oaklog' or something it'll give you that item
        CommandUtil.registerCommandOp(dispatcher, "give_item", GiveTestCommand::giveItemCommand);

        // This needs enabled in the FlyCommand class to work, I may switch everything over to in here like my SpeedCommands.java is.
        // '/fly' command, for now this will only display the block ground position if this is working.
        // Later on, I'll add flying by recreating it from my KBP plugin on Spigot.
        // Well the block ground position I thought I found didn't work, I'm not sure how to do it in NeoForge.
        // https://github.com/kelson8/KBP/blob/master/src/main/java/net/Kelsoncraft/KBP/commands/FlyCommand.java
        CommandUtil.registerCommandOp(dispatcher, "fly", FlyCommand::flyCommand);


        // '/spawn' command, this mostly seems to work fine.
        // Send the player to the world spawn.
        CommandUtil.registerCommandOp(dispatcher, "spawn", SpawnCommand::spawnCommand);

        // '/lightning' command, strike lightning where the player is looking.
        CommandUtil.registerCommandOp(dispatcher, "lightning", LightningCommand::lightningCommand);

        // '/smite' alias
        CommandUtil.registerCommandOp(dispatcher, "smite", LightningCommand::lightningCommand);


        //-----
        // New tests
        // Basic inventory test, for now it is just a trash can and destroys items placed in it.
        CommandUtil.registerCommandOp(dispatcher, "open_inv", TestCommands::testInvCommand);
        //-----

    }
}


