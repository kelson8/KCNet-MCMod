package net.kelsoncraft.neoforgetest.commands.misc;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.kelsoncraft.neoforgetest.util.ChatColors;
import net.kelsoncraft.neoforgetest.util.LogUtil;
import net.kelsoncraft.neoforgetest.util.MessageUtil;
import net.kelsoncraft.neoforgetest.util.PlayerUtil;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;


/**
 * So far this just checks if the player is on the ground, it doesn't actually enable/disable flying just yet.
 */

public class FlyCommand {

    public FlyCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("fly")
                .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .executes(command -> flyCommand(command.getSource())));
    }




    // TODO Fix this command, if the player is in survival and disables fly, it will put them on the ground.
    // For now I'm just seeing if this will show the ground Y coordinate.
    private int flyCommand(CommandSourceStack source) {
        if (source.getEntity() instanceof ServerPlayer player) {

//            if(isPlayerOnGround(player.createCommandSourceStack())) {
//                MessageUtil.sendColorMessage(source, "You are on the ground.", ChatColors.BLUE);
//            } else {
//                MessageUtil.sendColorMessage(source, "You are in the air.", ChatColors.BLUE);
//            }

            BlockPos playerBlockPos = player.blockPosition();
            int blockPosX = playerBlockPos.getX();
            int blockPosY = playerBlockPos.getY() - 1;
            int blockPosZ = playerBlockPos.getZ();

            BlockPos newBlockPos = new BlockPos(blockPosX, blockPosY, blockPosZ);

            // https://stackoverflow.com/questions/5324007/java-equivalent-of-pythons-format
            String blockPosStr = String.format("Block position below: X:%s, Y:%s, Z:%s", blockPosX, blockPosY, blockPosZ);

            // Log the block position message, I'm wondering where it shows the coords.
            LogUtil.LogInfo(blockPosStr);


            // Get the players current gamemode
            GameType currentGameMode = PlayerUtil.currentGameMode(player);


//            Vec3i newBlockPos = new Vec3i(playerBlockPos.getX(), playerBlockPos.getY() - 1, playerBlockPos.getZ());
            // Well this shows 0.0 if on the ground, and infinity if in the air.
            var groundPos = source.getLevel().getBlockFloorHeight(playerBlockPos);
//            var groundPos = source.getLevel().getGameRules();
//            var groundPos = source.getLevel().;

            // If the player is flying
            if (player.getAbilities().flying) {

                // This switch probably isn't needed anymore, TODO Remove.
                switch (currentGameMode) {
                    case CREATIVE, SURVIVAL, ADVENTURE, SPECTATOR -> {
                        player.getAbilities().flying = false;
//                            MessageUtil.sendColorMessage(source, "You are in the air, putting you back on the ground, disabling fly mode.", ChatColors.BLUE);
                        MessageUtil.sendColorMessage(source, "Disabling fly mode.", ChatColors.BLUE);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + currentGameMode);
                }
            } else {
                MessageUtil.sendColorMessage(source, "Flying has been enabled.", ChatColors.BLUE);
                player.getAbilities().flying = true;
            }
        }

        return Command.SINGLE_SUCCESS;
    }

    /**
     * Basic test to check if a player is in the air or on the ground, seems to work.
     *
     * @param source The source player to check
     * @return Command success
     */
    private int onGroundTest(CommandSourceStack source) {
        if (source.getEntity() instanceof ServerPlayer player) {
            BlockPos playerBlockPos = player.blockPosition();
            BlockPos newBlockPos = new BlockPos(playerBlockPos.getX(), playerBlockPos.getY() - 1, playerBlockPos.getZ());

            if (source.getLevel().isEmptyBlock(newBlockPos)) {
                MessageUtil.sendColorMessage(source, "You are in the air.", ChatColors.BLUE);
            } else {
                MessageUtil.sendColorMessage(source, "You are on the ground.", ChatColors.BLUE);
            }

        }

        return Command.SINGLE_SUCCESS;
    }

    /**
     * TODO Fix this to work, it works fine in the method above.
     * Check if the player is on the ground or not, incomplete.
     *
     * @param source The player to check for.
     * @return If the player has a block under them.
     */
    private boolean isPlayerOnGround(CommandSourceStack source) {
//        if(source.getEntity() instanceof Player player) {
        if (source.getEntity() instanceof ServerPlayer player) {

            BlockPos playerBlockPos = player.blockPosition();
            BlockPos newBlockPos = new BlockPos(playerBlockPos.getX(), playerBlockPos.getY() - 1, playerBlockPos.getZ());

            return source.getLevel().isEmptyBlock(newBlockPos);
        }

        return false;
    }
//
}
