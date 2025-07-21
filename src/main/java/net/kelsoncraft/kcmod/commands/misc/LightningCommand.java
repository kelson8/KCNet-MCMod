package net.kelsoncraft.kcmod.commands.misc;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.kelsoncraft.kcmod.util.EntityUtil;
import net.kelsoncraft.kcmod.util.PlayerUtil;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;

public class LightningCommand {

    public LightningCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("lightning")
                .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .executes(command -> lightningCommand(command.getSource())));

        // '/smite' alias
        dispatcher.register(Commands.literal("smite")
                .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .executes(command -> lightningCommand(command.getSource())));
    }

    /**
     * Lightning command, this works now, strikes lightning at the block the player is looking at.
     * The idea came from the EssentialsX commands.
     * @param source The source to run this on, for the current position.
     * @return Command success
     */
    private static int lightningCommand(CommandSourceStack source) {

        ServerLevel level = source.getLevel();
        if(source.getEntity() instanceof ServerPlayer player) {
            int serverViewDistance = player.getServer().getPlayerList().getViewDistance();

            double checkDistance = serverViewDistance * 16;

            // This works for getting the block I am looking at
            PlayerUtil.getFocusedBlock(player, checkDistance).ifPresent(hitResult -> {
                BlockPos blockPos = hitResult.getBlockPos();

                // Spawn the lightning at the position of the hit block
                EntityUtil.spawnEntityAt(EntityType.LIGHTNING_BOLT, level, blockPos.getX(), blockPos.getY(), blockPos.getZ());
            });

            // Handle the case where the Optional is empty (no block was found).
            // This fixes the error that was happening.
            if (PlayerUtil.getFocusedBlock(player, checkDistance).isEmpty()) {
//                source.sendFailure(MessageUtil.sendColorMessage(source, "No block found in your line of sight.", ChatColors.AQUA));
                source.sendFailure(Component.literal("No block found in your line of sight."));            }
        }
        return Command.SINGLE_SUCCESS;
    }
}
