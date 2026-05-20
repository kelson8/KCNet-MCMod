package net.kelsoncraft.kcmod.util;

import net.kelsoncraft.kcmod.Config;
import net.kelsoncraft.kcmod.KCMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;
import org.spongepowered.asm.mixin.Unique;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

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
    public static boolean isPlayerFlying(Player player) {
        return player.getAbilities().flying;

    }

    /**
     * Set the players flying status.
     * @param player The player to set flying for.
     * @param flying If flying is enabled or not.
     */
    public static void setPlayerFlying(Player player, boolean flying) {
        player.getAbilities().flying = flying;

        // I could possible use these attributes once I figure it out.
//        NeoForgeMod.CREATIVE_FLIGHT;

    }


    /**
     * Get the current item the player is holding
     * @param player The player to get the item from.
     * @param usedHand The usedHand parameter from an Item Mixin.
     * @return The current item the player is holding.
     */
    public static Item getCurrentHeldItem(Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        return itemStack.getItem();
    }

    /**
     * Give a player an effect, TODO Fix this later.
     * @param player The player to give an effect to.
     * @param effect The effect to give to the player.
     * @param seconds The seconds for the effect.
     * @param amplifier The amplifier for the effect.
     */
    public static void givePlayerEffect(Player player, MobEffect effect, int seconds, int amplifier) {
//        player.
//        Holder<MobEffect> effectHolder;
//        MobEffectInstance effectInstance = new MobEffectInstance(effectHolder, seconds, amplifier);
    }

    /**
     * Give the player an item, TODO Make this work with custom fireworks also.
     * @param item
     */
    public static void giveItem(Player player, ItemStack item) {
        player.addItem(item);
    }


    //-----
    // Credit to ezTxmMC on GitHub for the below code, it is licensed under MIT and I have modified it a bit.
    // I have removed the config options to this for now.
    // https://github.com/ezTxmMC/DimensionSpawn/blob/neoforge/1.21.1/src/main/java/de/eztxm/dimensionspawn/event/SpawnEvent.java


    /**
     * Handle a dimensional teleport
     * @param player The player to teleport.
     * @param pos The position to set the player to.
     * @param dimensionToTp The dimension namespace to teleport to, such as mining_dimension:mining_dimension, disabled argument.
     * @param yaw The yaw for the location.
     * @param pitch The pitch for the location.
     */
    public void handleDimensionTeleport(Player player, Vec3 pos, ResourceKey<Level> dimensionToTp, float yaw, float pitch) {

            KCMod.LOGGER.info("Dimension key: {}", dimensionToTp.toString());

            Level level = player.level();
            ServerLevel dimension = Objects.requireNonNull(level.getServer()).getLevel(dimensionToTp);
            if (dimension == null) {
                MessageUtil.sendColorMessage(player, "Dimension " + dimensionToTp.toString() + " is invalid!", ChatColors.AQUA);
                player.sendSystemMessage(Component.literal("[DimensionSpawn] The dimension " + dimensionToTp.toString() + " does not exist in this instance."));
                return;
            }
            DimensionTransition transition = dimensionTransition(player, new Vec3(pos.x, pos.y, pos.z), yaw, pitch, dimension, false, 0);
            if (transition == null) {
                return;
            }
            player.changeDimension(transition);

            double x = pos.x;
            double y = pos.y;
            double z = pos.z;
            player.teleportTo((ServerLevel) player.level(), x, y, z, Collections.emptySet(), yaw, pitch);
//        }
    }

    /**
     * Teleport the player to another dimesnion
     * @param entity The player to teleport
     * @param pos The position to set the player to.
     * @param yaw The yaw for the location.
     * @param pitch The pitch for the location.
     * @param destWorld The world to tepeort to.
     * @param safeSpawn If the spawn should be checked for hazards.
     * @param safeSpawnRange The range of the safe spawn.
     * @return
     */
    private DimensionTransition dimensionTransition(Entity entity, Vec3 pos, float yaw, float pitch, ServerLevel destWorld, boolean safeSpawn, int safeSpawnRange) {
            if (safeSpawn) {
                BlockPos blockPos = new BlockPos((int) pos.x, (int) pos.y, (int) pos.z);
                BlockPos safeBlockPos = validPlayerSpawnLocation(destWorld, blockPos, safeSpawnRange);
                if (safeBlockPos == null) {
                    return new DimensionTransition(destWorld, entity, entity1 -> entity1.teleportTo(destWorld, pos.x, pos.y, pos.z, Collections.emptySet(), yaw, pitch));
                }
                entity.teleportTo(destWorld, safeBlockPos.getX(), safeBlockPos.getY(), safeBlockPos.getZ(), Collections.emptySet(), yaw, pitch);
                return new DimensionTransition(destWorld, entity, entity1 -> entity1.teleportTo(destWorld, safeBlockPos.getX(), safeBlockPos.getY(), safeBlockPos.getZ(), Collections.emptySet(), yaw, pitch));
            }
            return new DimensionTransition(destWorld, entity, entity1 -> entity1.teleportTo(destWorld, pos.x, pos.y, pos.z, Collections.emptySet(), yaw, pitch));
    }

    /**
     * Check for a safe spawn location.
     * @param world The world to check for.
     * @param position The coordinates for the location.
     * @param maximumRange The range to check if it's safe.
     * @return If the spawn location is safe.
     */
    private BlockPos validPlayerSpawnLocation(ServerLevel world, BlockPos position, int maximumRange) {
        BlockPos.MutableBlockPos currentPos = new BlockPos.MutableBlockPos();
        for (int range = 0; range < maximumRange; range++) {
            int radiusSq = range * range;
            int outerRadiusSq = (range + 1) * (range + 1);
            for (int yOffset = -range; yOffset <= range; yOffset++) {
                for (int xOffset = -range; xOffset <= range; xOffset++) {
                    for (int zOffset = -range; zOffset <= range; zOffset++) {
                        int distanceSq = xOffset * xOffset + yOffset * yOffset + zOffset * zOffset;
                        if (distanceSq >= radiusSq && distanceSq < outerRadiusSq) {
                            currentPos.set(position.getX() + xOffset, position.getY() + yOffset, position.getZ() + zOffset);
                            if (world.getBlockState(currentPos.below()).canOcclude() &&
                                    world.getBlockState(currentPos).isAir() &&
                                    world.getBlockState(currentPos.above()).isAir()) {
                                return currentPos;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    //-----


}
