package net.kelsoncraft.kcmod.util;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Unique;

import java.util.Optional;

public class PlayerUtil {

    // TODO Move this elsewhere
    public enum SpeedType {
        WALK,
        FLY,
    }

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
     * This checks if the entity is a player.
     * @param entity The entity to check if they are a player.
     * @return If the entity is a ServerPlayer.
     */
    public static boolean isPlayer(Entity entity) {
        return entity instanceof ServerPlayer;
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

    /**
     * Sets the players speed, moved out of SpeedCommands.java.
     * I may remove the conversions from this later and switch to using an easier method.
     * @param player The player to set the speed for.
     * @param speedType The speed type from the SpeedType enum, such as SpeedType.WALK, SpeedType.FLY.
     * @param speed An integer value from 1-10 for the fly/walk speed.
     */
    @Unique

    public static void setSpeed(Player player, SpeedType speedType, float speed) {
        // I didn't realize this was as easy as this, I didn't need that entire switch statement
        // https://github.com/ZeroG-Network-PTY-LTD/NeoEssentials/blob/Released/src/main/java/com/zerog/neoessentials/util/commands/PlayerStateCommands.java#L280
        float mcSpeed = Math.min(speed / 10f, 1.0f);
//        float flySpeed = switch (speed) {
//            case 1 -> 0.1f;
//            case 2 -> 0.2f;
//            case 3 -> 0.3f;
//            case 4 -> 0.4f;
//            case 5 -> 0.5f;
//            case 6 -> 0.6f;
//            case 7 -> 0.7f;
//            case 8 -> 0.8f;
//            case 9 -> 0.9f;
//            case 10 -> 1.0f;
//            default -> 0.1f;
//        };

        switch (speedType) {
            case WALK:
                player.getAbilities().setWalkingSpeed(mcSpeed);
                MessageUtil.sendColorMessage(player, "Walking speed set to " + speed, ChatColors.AQUA);
                break;
            case FLY:
                player.getAbilities().setFlyingSpeed(mcSpeed);
                MessageUtil.sendColorMessage(player, "Flying speed set to " + speed, ChatColors.AQUA);
                break;
            default:
                break;

        }

    }

    /**
     * Check if the player is flying, TODO Test this.
     * @param player The player to check.
     * @return If the player is flying.
     */
    public static boolean isPlayerFlying(ServerPlayer player) {
        return player.getAbilities().flying;
    }



}
