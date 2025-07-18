package net.kelsoncraft.neoforgetest.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import java.util.UUID;

public class MiscCommands {


    // For registering the commands.
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new MiscCommands(event.getDispatcher());
    }

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

//    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
////         I've never really used this syntax much before in Java, the MiscCommands::execute
////        dispatcher.register(Commands.literal("playerinfo").executes(MiscCommands::command_playerinfo));
//        dispatcher.register(Commands.literal("kc_teleport")
//                        .then(Commands.argument("x", DoubleArgumentType.doubleArg()))
//                        .then(Commands.argument("y", DoubleArgumentType.doubleArg()))
//                        .then(Commands.argument("z", DoubleArgumentType.doubleArg())
//                                .executes(MiscCommands::command_kc_teleport)));
////                .executes(MiscCommands::command_kc_teleport));
//
//    }


    // TODO Move this into PlayerInfoCommand.java.
    private int command_playerinfo(CommandContext<CommandSourceStack> command) {

        if(command.getSource().getEntity() instanceof Player player) {
            UUID uuid = player.getGameProfile().getId();
            float playerHealth = player.getHealth();
            boolean isPlayerInWater = player.isInWater();
            double playerX = player.getX();
            double playerY = player.getY();
            double playerZ = player.getZ();

            // TODO Make this use string from Messages class instead of in here.
            String KCNet_Main = "[KCNet]: ";

            player.sendSystemMessage(Component.literal(KCNet_Main + "Your uuid is: " + uuid));
            player.sendSystemMessage(Component.literal(KCNet_Main + "Your health is: " + playerHealth));
            player.sendSystemMessage(Component.literal(KCNet_Main + "X: " + playerX));
            player.sendSystemMessage(Component.literal(KCNet_Main + "Y: " + playerY));
            player.sendSystemMessage(Component.literal(KCNet_Main + "Z: " + playerZ));
        }

        return Command.SINGLE_SUCCESS;
    }



}
