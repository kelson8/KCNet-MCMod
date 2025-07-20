package net.kelsoncraft.neoforgetest.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.kelsoncraft.neoforgetest.commands.misc.HealCommand;
import net.kelsoncraft.neoforgetest.commands.misc.PlayerInfoCommand;
import net.kelsoncraft.neoforgetest.commands.misc.SummonMobCommand;
import net.minecraft.commands.CommandSourceStack;
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

    }
}


