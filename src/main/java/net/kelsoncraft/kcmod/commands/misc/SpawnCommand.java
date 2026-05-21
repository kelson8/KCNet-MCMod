package net.kelsoncraft.kcmod.commands.misc;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.kelsoncraft.kcmod.api.WorldApi;
import net.kelsoncraft.kcmod.util.ChatColors;
import net.kelsoncraft.kcmod.util.MessageUtil;
import net.kelsoncraft.kcmod.util.PlayerUtil;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SpawnCommand {

    /**
     * Basic spawn command, this works fine now after changing it a bit.
     * This teleports the player to the world spawn point.
     *
     * @param context The command context
     * @return Command success.
     */
    public static int spawnCommand(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();

        teleportToSpawn(source, source.getPlayer());

        return Command.SINGLE_SUCCESS;
    }

    /**
     * Teleport the player to the spawn point, runs from the spawnCommand function.
     * TODO Figure out how to get the spawn rotations such as rotX and rotY.
     *
     * @param source The source to run this on.
     * @param player The player to teleport.
     */
    private static void teleportToSpawn(CommandSourceStack source, Player player) {
        if (player instanceof ServerPlayer) {

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
            Vec3 respawnPos = new Vec3(respawnX, respawnY, respawnZ);

            ResourceKey<Level> dimensionToTp = ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath("minecraft", "overworld"));

            // Make this teleport the player back to the overworld if they aren't already there.
            if(!world.dimension().location().toString().equals("minecraft:overworld")) {
                PlayerUtil.handleDimensionTeleport(player, respawnPos, dimensionToTp, 0f, 0f);
            } else {
                player.teleportTo(respawnX, respawnY, respawnZ);
            }

            MessageUtil.sendColorMessage(source, "Teleported to spawn", ChatColors.AQUA);
        }
    }
}
