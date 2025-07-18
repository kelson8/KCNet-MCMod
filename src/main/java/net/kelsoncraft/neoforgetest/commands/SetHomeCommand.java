package net.kelsoncraft.neoforgetest.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.kelsoncraft.neoforgetest.NeoForgeTest;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.common.NeoForge;

// TODO Fix this to work.
public class SetHomeCommand {
    // https://www.youtube.com/watch?v=bYH2i-KOLgk

//    public SetHomeCommand(CommandDispatcher<CommandSource> dispatcher) {
    public SetHomeCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        // /home set
        // This is how to make a command with arguments, hopefully I can get it working on NeoForge 1.21.1
        dispatcher.register(Commands.literal("home") // Command name
                .then(Commands.literal("set") // Arg 1
                        .executes((command) -> { // Execute the command
                            return setHome(command.getSource());
                        })));

    }

    // https://youtu.be/bYH2i-KOLgk?t=210
    // This works now! I had to register the command a different way in the main file.
    // I changed CommandSource to CommandSourceStack like above.
    private int setHome(CommandSourceStack source) throws CommandSyntaxException {
//        Player player = source.
        // This might work?

        ServerPlayer player = source.getPlayer();

        if(player != null) {
            BlockPos playerPos = player.blockPosition();
            int playerX = playerPos.getX();
            int  playerY = playerPos.getY();
            int playerZ = playerPos.getZ();

            String pos = "(" + playerX + ", " + playerY + ", " + playerZ + ")";

            player.getPersistentData().putIntArray(NeoForgeTest.MOD_ID + "homepos",
                    new int[]{ playerX, playerY, playerZ});

            source.sendSystemMessage(Component.literal("Set home at " + pos));
        }


        return 1;
    }


}
