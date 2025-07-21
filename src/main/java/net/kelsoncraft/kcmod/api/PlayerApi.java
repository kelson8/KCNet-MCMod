package net.kelsoncraft.kcmod.api;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;

/**
 * Basic test for a possible future player API to my mod.
 * I may try to figure out how to add getting home info and other stuff to this.
 * This Api is a very early work in progress and will be changed a lot, it is not recommended to use this currently.
 */

public class PlayerApi {


    // Private constructor to prevent instantiation, as all methods are static.
    private PlayerApi() {
    }

    /**
     * Get the player's current gamemode.
     *
     * @param player The ServerPlayer to check.
     * @return The GameType of the current player (e.g., Creative, Survival).
     */
    public static GameType getCurrentGamemode(ServerPlayer player) {
        // Ensure the player object is not null, though in most game contexts it won't be.
        if (player == null) {
            return null;
            // Or throw an IllegalArgumentException, depending on desired error handling.
//            return GameType.NOT_SET; // Or a suitable default/error GameType
        }
        return player.gameMode.getGameModeForPlayer();
    }

    /**
     * Get the players current health, untested.
     * @param player The ServerPlayer to check.
     * @return The players current health as a float.
     */
    public static float getPlayerHealth(ServerPlayer player) {
        return player.getHealth();
    }

    /**
     * Get the players current hunger, untested.
     * @param player The ServerPlayer to check.
     * @return The players current hunger as an int.
     */
    public static int getPlayerFoodLevel(ServerPlayer player) {
        return player.getFoodData().getFoodLevel();
    }

    // You could add other API methods here, for example:
    // public static void setPlayerGamemode(ServerPlayer player, GameType newGamemode) {
    //     // Call your internal setGameMode logic
    // }
}
