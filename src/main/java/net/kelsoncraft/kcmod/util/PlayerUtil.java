package net.kelsoncraft.kcmod.util;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class PlayerUtil {

    /**
     * Get the players current gamemode
     * @param player The player to check
     * @return The GameType of the current player, such as Creative
     */
//    public static GameType currentGameMode(ServerPlayer player) {
//        return player.gameMode.getGameModeForPlayer();
//    }

    // TODO Setup more of these.
    /**
     * Heal the player
     * @param player The player to heal
     */
    public static void healPlayer(ServerPlayer player) {
        player.heal(20.0f);
        player.getFoodData().setExhaustion(0.0f);
        player.getFoodData().setFoodLevel(20);
        // This gives me too much saturation lol.
//            player.getFoodData().setSaturation(20.0f);

        MessageUtil.sendColorMessage(player.createCommandSourceStack(), "You have been healed!", ChatColors.AQUA);
    }

    /**
     * This should get the current block position of the block the player is looking at.
     * <a href="https://github.com/FTBTeam/FTB-Essentials/blob/main/common/src/main/java/dev/ftb/mods/ftbessentials/util/BlockUtil.java">...</a>
     * @param player The player to check for
     * @param maxDist the max distance to check for.
     * @return The block the player is currently looking at.
     */
    public static Optional<BlockHitResult> getFocusedBlock(ServerPlayer player, double maxDist) {
        Vec3 entityVec = player.getEyePosition(1f);
        Vec3 maxDistVec = entityVec.add(player.getViewVector(1F).scale(maxDist));
        ClipContext ctx = new ClipContext(entityVec, maxDistVec, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player);
        BlockHitResult hitResult = player.level().clip(ctx);
        return hitResult.getType() == HitResult.Type.BLOCK ? Optional.of(hitResult) : Optional.empty();
    }



}
