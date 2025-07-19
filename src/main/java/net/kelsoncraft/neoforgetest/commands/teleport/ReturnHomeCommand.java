package net.kelsoncraft.neoforgetest.commands.teleport;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.kelsoncraft.neoforgetest.NeoForgeTest;
import net.kelsoncraft.neoforgetest.util.MessageUtil;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class ReturnHomeCommand {

    // TODO Make this able to return to multiple homes.
    // TODO Make a delete command, make this into new format: '/home <name>' instead of '/home return <name>'
    public ReturnHomeCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        // /home return
        // This is how to make a command with arguments, tested working on NeoForge 1.21.1.
        dispatcher.register(Commands.literal("home") // Command name
                .then(Commands.literal("return") // Arg 1
                        .executes((command) -> { // Execute the command
                            return returnHome(command.getSource());
                        })));

    }

    private int returnHome(CommandSourceStack source) throws CommandSyntaxException {
        Entity entity = source.getEntity();

        // Check if the entity is a ServerPlayer
        if(entity instanceof ServerPlayer player) {
            boolean hasHomePos = player.getPersistentData().getIntArray(NeoForgeTest.MOD_ID + "homepos").length != 0;

            // If player has a home position
            if (hasHomePos) {
                int[] playerPos = player.getPersistentData().getIntArray(NeoForgeTest.MOD_ID + "homepos");
                Vec3 playerPosNew = new Vec3(playerPos[0], playerPos[1], playerPos[2]);

                // This works! I had to change it to entity and check if the entity is a player.
                entity.teleportTo(playerPos[0], playerPos[1], playerPos[2]);


                // These can also use placeholders in the json if I wanted to make a system that has multiple homes by using %s
//                source.sendSystemMessage(Component.translatable("commands.kcneoforgetest.return_home.success"));
//                source.sendSystemMessage(Component.translatable("commands.kcneoforgetest.return_home.success", "homeId"));

                MessageUtil.SendMessage(player, "You were returned to your home.");

                return 1;

            } else {
                // No home position
//                source.sendSystemMessage(Component.literal("No home position has been set!"));
//                source.sendSystemMessage(Component.translatable("commands.kcneoforgetest.return_home.no_pos_found"));
                MessageUtil.SendMessage(player, "You do not have a home set!");

                return -1;
            }
        }


        return 1;

    }
}
