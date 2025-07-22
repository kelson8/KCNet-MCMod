package net.kelsoncraft.kcmod.commands.teleport;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.kelsoncraft.kcmod.KCMod;
import net.kelsoncraft.kcmod.util.MessageUtil;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;


//public class SetHomeCommand implements Command<CommandSource> {
public class SetHomeCommand {
    // https://www.youtube.com/watch?v=bYH2i-KOLgk

    // TODO Make this able to set multiple homes.

    // https://youtu.be/bYH2i-KOLgk?t=210
    // This works now! I had to register the command a different way in the main file.
    // I changed CommandSource to CommandSourceStack like above.
//    private static int setHome(CommandSourceStack source) throws CommandSyntaxException {
    public static int setHomeCommand(CommandSourceStack source) {

//        Player player = source.
        // This might work?

//        ServerPlayer player = source.getPlayer();
        ServerPlayer player = source.getPlayer();

        if(player != null) {
            BlockPos playerPos = player.blockPosition();
            int playerX = playerPos.getX();
            int playerY = playerPos.getY();
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
