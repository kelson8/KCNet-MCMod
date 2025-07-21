package net.kelsoncraft.kcmod.commands.teleport;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.kelsoncraft.kcmod.KCMod;
import net.kelsoncraft.kcmod.util.MessageUtil;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;


//public class SetHomeCommand implements Command<CommandSource> {
public class SetHomeCommand {
    // https://www.youtube.com/watch?v=bYH2i-KOLgk


    // Some ideas for the new command registering system from here
    // https://github.com/McJtyMods/InControl/blob/1.21_neo/src/main/java/mcjty/incontrol/commands/CmdReload.java
    // New command builder format.
    // TODO Figure this one out so I can use it.
    // Seems neater then my old method, especially if I can group commands together.
//    public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher) {
//        return Commands.literal("home")
//                .then(Commands.literal("set") // Arg 1
//                                .executes(SetHomeCommand::setHome)
//                            );
////                        .executes((command) -> { // Execute the command
////                            return setHome(command.getSource());
////                        }));
//
//    }

    // TODO Make this able to set multiple homes.
    public SetHomeCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        // /home set
        // This is how to make a command with arguments, working method.
        dispatcher.register(Commands.literal("home") // Command name
                .then(Commands.literal("set") // Arg 1
                        .executes((command) -> { // Execute the command
                            return setHome(command.getSource());
                        })));

    }

    // https://youtu.be/bYH2i-KOLgk?t=210
    // This works now! I had to register the command a different way in the main file.
    // I changed CommandSource to CommandSourceStack like above.
//    private static int setHome(CommandSourceStack source) throws CommandSyntaxException {
    private static int setHome(CommandSourceStack source) throws CommandSyntaxException {
//        Player player = source.
        // This might work?

//        ServerPlayer player = source.getPlayer();
        ServerPlayer player = source.getPlayer();

        if(player != null) {
            BlockPos playerPos = player.blockPosition();
            int playerX = playerPos.getX();
            int  playerY = playerPos.getY();
            int playerZ = playerPos.getZ();

            String pos = "(" + playerX + ", " + playerY + ", " + playerZ + ")";

            player.getPersistentData().putIntArray(KCMod.MOD_ID + "homepos",
                    new int[]{ playerX, playerY, playerZ});


//            source.sendSystemMessage(Component.literal("Set home at " + pos));

            MessageUtil.SendMessage(player, "Set home at " + pos);
        }


        return 1;
    }
}
