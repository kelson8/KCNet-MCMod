package net.kelsoncraft.neoforgetest.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.kelsoncraft.neoforgetest.NeoForgeTest;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;

public class ReturnHomeCommand {

    public ReturnHomeCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        // /home return
        // This is how to make a command with arguments, hopefully I can get it working on NeoForge 1.21.1
        dispatcher.register(Commands.literal("home") // Command name
                .then(Commands.literal("return") // Arg 1
                        .executes((command) -> { // Execute the command
                            return returnHome(command.getSource());
                        })));

    }

    private int returnHome(CommandSourceStack source) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayer();

        if (player != null) {
            boolean hasHomePos = player.getPersistentData().getIntArray(NeoForgeTest.MOD_ID + "homepos").length != 0;

            // If player has a home position
            if (hasHomePos) {
                int[] playerPos = player.getPersistentData().getIntArray(NeoForgeTest.MOD_ID + "homepos");
//                Vec3[] playerPos = player.getPersistentData().getIntArray(NeoForgeTest.MOD_ID + "homepos");
//                player.setPos(playerPos[0], playerPos[1], playerPos[2]);
                Vec3 playerPosNew = new Vec3(playerPos[0], playerPos[1], playerPos[2]);

                player.setPos(playerPosNew);

                // These can also use placeholders in the json if I wanted to make a system that has multiple homes by using %s
                source.sendSystemMessage(Component.translatable("commands.kcneoforgetest.return_home.success"));
                // TODO Look into teleport command for this
//                source.sendSystemMessage(Component.translatable("commands.kcneoforgetest.return_home.success", "homeId"));

                return 1;

            } else {
                // No home position
//                source.sendSystemMessage(Component.literal("No home position has been set!"));
                source.sendSystemMessage(Component.translatable("commands.kcneoforgetest.return_home.no_pos_found"));

                return -1;
            }
        }


        return 1;

    }
}
