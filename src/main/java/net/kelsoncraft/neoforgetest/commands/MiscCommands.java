package net.kelsoncraft.neoforgetest.commands;

import net.kelsoncraft.neoforgetest.commands.misc.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class MiscCommands {

    // For registering the commands.
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        // Player info command
        // '/playerinfo'
        new PlayerInfoCommand(event.getDispatcher());

        // Summon mob command
        // '/summon_mob', just setup to spawn a wolf for now.
        new SummonMobCommand(event.getDispatcher());

        // '/heal' command
        new HealCommand(event.getDispatcher());

        // '/give_item' command, for now this just gives acacia logs, I would like to replicate the '/i' command in Essentials
        // So if you type '/i oaklog' or something it'll give you that item
        new GiveTestCommand(event.getDispatcher());

        // '/fly' command, for now this will only display the block ground position if this is working.
        // Later on, I'll add flying by recreating it from my KBP plugin on Spigot.
        // Well the block ground position I thought I found didn't work, I'm not sure how to do it in NeoForge.
        // https://github.com/kelson8/KBP/blob/master/src/main/java/net/Kelsoncraft/KBP/commands/FlyCommand.java
//        new FlyCommand(event.getDispatcher());
    }
}


