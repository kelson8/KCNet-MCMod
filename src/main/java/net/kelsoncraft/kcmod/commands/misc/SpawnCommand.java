package net.kelsoncraft.kcmod.commands.misc;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.kelsoncraft.kcmod.api.WorldApi;
import net.kelsoncraft.kcmod.util.ChatColors;
import net.kelsoncraft.kcmod.util.MessageUtil;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class SpawnCommand {

    public SpawnCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("spawn")
                .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .executes(command -> spawnCommand(command.getSource()))
        );
    }

    /**
     * Basic spawn command, this works fine now after changing it a bit.
     * This teleports the player to the world spawn point.
     *
     * @param source The source to run this on.
     * @return Command success.
     */
    private static int spawnCommand(CommandSourceStack source) {
        Entity entity = source.getEntity();

        teleportToSpawn(source, entity);

        return Command.SINGLE_SUCCESS;
    }

    /**
     * Teleport the player to the spawn point, runs from the spawnCommand function.
     * TODO Figure out how to get the spawn rotations such as rotX and rotY.
     *
     * @param source The source to run this on.
     * @param entity The entity to teleport.
     */
    private static void teleportToSpawn(CommandSourceStack source, Entity entity) {
        if (entity instanceof ServerPlayer player) {

            Level world = player.level();
            // TODO Is this needed?
//            if (world.isClientSide) {
//                return 0;
//            }

            ServerLevel serverWorld = (ServerLevel) world;

            // Get world spawn point, current working
            BlockPos respawnLocation = WorldApi.getWorldSpawn(serverWorld);

            int respawnX = respawnLocation.getX();
            int respawnY = respawnLocation.getY();
            int respawnZ = respawnLocation.getZ();
//            int respawnXrot = respawnLocation

            entity.teleportTo(respawnX, respawnY, respawnZ);
//            entity.setXRot();

            MessageUtil.sendColorMessage(source, "Teleported to spawn", ChatColors.AQUA);

            // To show the coordinates for testing.
//            MessageUtil.sendColorMessage(source, "Spawn pos: X: " + respawnX + " Y: " + respawnY + " Z: " + respawnZ,
//                    ChatColors.BLUE);
        }
    }
}
