package net.kelsoncraft.neoforgetest.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.kelsoncraft.neoforgetest.commands.misc.PlayerInfoCommand;
import net.minecraft.commands.CommandSourceStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class MiscCommands {

    // For registering the commands.
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new PlayerInfoCommand(event.getDispatcher());
    }

    // TODO Remove this
    public MiscCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        // Misc commands
        // This one is now located in /kc coords.
//        dispatcher.register(Commands.literal("test1")
//                .then(Commands.literal("coords") // Defines subcommand: /kc coords
//                        .then(Commands.argument("x", DoubleArgumentType.doubleArg()) // Defines double argument 'x'
//                                .then(Commands.argument("y", DoubleArgumentType.doubleArg()) // Defines double argument 'y'
//                                        .then(Commands.argument("z", DoubleArgumentType.doubleArg()) // Defines double argument 'z'
////                                                .executes(MiscCommands::command_kc_teleport) // Executes when /kc coords <x> <y> <z> is run
//                                                .executes(CustomTeleportCommand::command_kc_teleport) // Executes when /kc coords <x> <y> <z> is run
//                                        )
//                                )
//                        )
//                )
//        );
    }
}
