package net.kelsoncraft.neoforgetest.api;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

public class WorldApi {

    private WorldApi() {
    }

    /**
     * This works fine for the '/spawn' command.
     * Get the world's current spawn point coordinates.
     * @param world The ServerLevel
     * @return The world spawn point.
     */
    public static BlockPos getWorldSpawn(ServerLevel world) {
        return world.getSharedSpawnPos();
    }

}
