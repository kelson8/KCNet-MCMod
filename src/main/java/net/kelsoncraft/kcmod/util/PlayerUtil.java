package net.kelsoncraft.kcmod.util;

import com.hrznstudio.titanium.nbthandler.NBTManager;
import net.kelsoncraft.kcmod.Config;
import net.kelsoncraft.kcmod.KCMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
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

import static net.kelsoncraft.kcmod.KCMod.LOGGER;

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
     * Sets the players flying speed
     * TODO Fix this to work in a command.
     * @param player The player to set the fly speed for
     * @param newFlySpeed An integer value from 1-10 for the flying speed.
     */
    @Unique

    public static void setFlySpeed(Player player, int newFlySpeed) {
        if(newFlySpeed < 1 || newFlySpeed > 10) {
            MessageUtil.sendColorMessage(player, "Error: Speed must be between 1 and 10", ChatColors.RED);
            return;
        }

        float flySpeed = switch (newFlySpeed) {
            case 1 -> 0.1f;
            case 2 -> 0.2f;
            case 3 -> 0.3f;
            case 4 -> 0.4f;
            case 5 -> 0.5f;
            case 6 -> 0.6f;
            case 7 -> 0.7f;
            case 8 -> 0.8f;
            case 9 -> 0.9f;
            case 10 -> 1.0f;
            default -> 0.1f;
        };

        // Make sure the faster fly speed toggle is enabled
        if (Config.COMMON.FAST_FLY_SPEED_TOGGLE.get()) {
            player.getAbilities().setFlyingSpeed(flySpeed);
            MessageUtil.sendColorMessage(player, "Flying speed set to " + newFlySpeed, ChatColors.AQUA);
        }

        // Fall back to default flying speed if this is invalid.
//        if (!IsFlySpeedValid(Config.COMMON.FLY_SPEED.get())) {
//            flySpeed = defaultFlySpeed;
//            LOGGER.warn("FlySpeed {} is invalid!", flySpeed);
//        }
    }

    /**
     * Sets the players speed, moved out of SpeedCommands.java.
     * I may remove the conversions from this later and switch to using an easier method.
     * TODO Fix this to work, it displays a message but doesn't update the speeds.
     * @param player The player to set the speed for.
     * @param speedType The speed type from the SpeedType enum, such as SpeedType.WALK, SpeedType.FLY.
     * @param speed An integer value from 1-10 for the fly/walk speed.
     */
    @Unique

    public static void setSpeed(Player player, SpeedType speedType, float speed) {
        // I didn't realize this was as easy as this, I didn't need that entire switch statement
        // https://github.com/ZeroG-Network-PTY-LTD/NeoEssentials/blob/Released/src/main/java/com/zerog/neoessentials/util/commands/PlayerStateCommands.java#L280

        if(speed < 1f || speed > 10f) {
            MessageUtil.sendColorMessage(player, "Error: Speed must be between 1 and 10", ChatColors.RED);
            return;
        }

        float mcSpeed = Math.min(speed / 10f, 1.0f);

        switch (speedType) {
            case WALK:
                player.getAbilities().setWalkingSpeed(mcSpeed);
                MessageUtil.sendColorMessage(player, "Walking speed set to " + speed, ChatColors.AQUA);
                break;
            case FLY:
                setFlySpeed(player, (int)mcSpeed);
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
//    public static boolean isPlayerFlying(Player player) {
    public static boolean canPlayerFly(Player player) {
        return player.getAbilities().mayfly;
    }

    /**
     * Set the players flying status.
     * @param player The player to set flying for.
     * @param flying If flying is enabled or not.
     */
    public static void setPlayerFlying(Player player, boolean flying) {
        // Well mayfly is deprecated but NeoEssentials is using it, I'm not sure how to replace it.
        // I could possibly use these attributes once I figure it out.
//        NeoForgeMod.CREATIVE_FLIGHT;
        player.getAbilities().mayfly = flying;

        // This message works, but it spams it where it is set to work on right click.
        String flyingText = PlayerUtil.canPlayerFly(player) ? "Enabled" : "Disabled";
        LOGGER.info("Flying has been {}.", flyingText);
        MessageUtil.sendColorMessage(player, "Flying has been " + flyingText + ".", ChatColors.AQUA);

        // Check if the player is flying first, if so attempt to put them on the ground.
        // TODO Fix this.
//        if(player.getAbilities().flying) {

//        }



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

    // TODO Figure out the NBT data, I tried getting the Titanium library working.
    public static void setNbtData(ItemStack item, String nbtItem) {
//        item.set(NBTUtil.FLY_ITEM);

//        var compound = NBTManager.getInstance().writeTileEntity(tile, new CompoundTag());

//        boolean hasTag = item.hasTag();
    }

    public static boolean isNbtFlyingEnabled(ItemStack item, String nbtItem) {
        if(item.has(NBTUtil.FLY_ITEM)) {
            LOGGER.info("Item has the flying NBT data");
            return true;
        }

        return false;
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
     * @param item The item to give to the player.
     * @param amount The amount of the item to give to the player.
     */
    public static void giveItem(Player player, ItemStack item, int amount) {

        //---
        // https://forums.minecraftforge.net/topic/151365-giving-a-player-an-item/
        // This works for adding an item to the players inventory.
        player.getInventory().add(item);
    }

    /**
     * Basic function to get the players current dimension
     * @param player The player to get the dimension for.
     * @return The current dimension the player is in, such as 'minecraft:overworld'.
     */
    public static String getPlayerDimension(Player player) {
        if (player instanceof ServerPlayer) {
            return player.level().dimension().location().toString();
        }

        return "Can only be used on a player.";

    }

    /**
     * Set the players dimension
     * @param player The player to set the dimension for.
     * @param dimension The dimension to teleport to, such as 'minecraft:overworld'.
     */
    public static void setPlayerDimension(Player player, String dimension) {
    }


    // Teleport test
    // Well oops, this puts me on top of the mining dimension.
    // Adapted from the FTB Essentials RTP teleport code, this needs modified.
    // This somewhat works, but it doesn't always work in other dimensions.
    // TODO Figure out how to use this.
    private static final TagKey<Block> IGNORE_RTP_BLOCKS = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(KCMod.MOD_ID, "ignore_rtp"));
    private static final int RTP_MAX_TRIES = 3;

    public static void teleportTest(Player player, Vec3 pos) {

        // Some random values for distances
        int minDistance = 6; // 500
        int maxDistance = 30;
        Level level = player.level();
        // Attempting to make this a safe teleport.
        ServerLevel serverLevel = (ServerLevel) level;

        // Make this fail in the mining dimension
        if(getPlayerDimension(player).equals("mining_dimension:mining_dimension")) {
            MessageUtil.sendColorMessage(player, "Error, this does not work in the mining dimension", ChatColors.RED);
            return;
        }

        for (int attempt = 0; attempt < RTP_MAX_TRIES; attempt++) {

            // TODO Try to figure out how this is working.
            // This just randomizes the teleport for the RTP.
            double dist = minDistance + serverLevel.random.nextDouble() * (maxDistance - minDistance);
            double angle = serverLevel.random.nextDouble() * Math.PI * 2D;

            int x = Mth.floor(Math.cos(angle) * dist);
            int y = 256;
            int z = Mth.floor(Math.sin(angle) * dist);
            BlockPos currentPos = new BlockPos(x, y, z);

            BlockPos heightMapPos = new BlockPos(currentPos);

            serverLevel.getChunkAt(heightMapPos);
            BlockPos hmPos = serverLevel.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, heightMapPos);

            // TODO Make this fail on the mining dimension for now.
            if (hmPos.getY() > 0) {
                BlockPos goodPos = null;
                if (hmPos.getY() < ((ServerLevel) level).getLogicalHeight()) {
                    goodPos = hmPos;
                } else {
                    // broken heightmap (nether, other mod dimensions)
                    for (BlockPos newPos : BlockPos.spiralAround(new BlockPos(hmPos.getX(), level.getSeaLevel(), hmPos.getZ()), 16, Direction.EAST, Direction.SOUTH)) {
                        BlockState bs = level.getBlockState(newPos);
                        if (bs.blocksMotion() && !bs.is(IGNORE_RTP_BLOCKS) && level.isEmptyBlock(newPos.above(1))
                                && level.isEmptyBlock(newPos.above(2)) && level.isEmptyBlock(newPos.above(3))) {
                            goodPos = newPos.immutable();
                            break;
                        }
                    }
                }

                // Teleport the player if the position is valid.
                if (goodPos != null) {
//                    LOGGER.info("Random pos: X: {} Y: {} Z: {}", goodPos.getX(), y, z);
                    String randomPos = String.format(" @ [x %d, y %d, z %d]", goodPos.getX(), goodPos.getY(), goodPos.getZ());
                    LOGGER.info("Random pos: {}", randomPos);
//                    LOGGER.info("Current dimension: {}", level.dimension().location());
                    getPlayerDimension(player);
                    player.teleportTo(goodPos.getX(), goodPos.getY(), goodPos.getZ());
                }
            }
        }

        //
    }

    //-----
    // Credit to ezTxmMC on GitHub for the below code, it is licensed under MIT and I have modified it a bit.
    // I have removed the config options to this for now.
    // https://github.com/ezTxmMC/DimensionSpawn/blob/neoforge/1.21.1/src/main/java/de/eztxm/dimensionspawn/event/SpawnEvent.java


    /**
     * Handle a dimensional teleport.
     * TODO Make this try to avoid putting the player inside of a block, lava, and other hazards.
     * @param player The player to teleport.
     * @param pos The position to set the player to.
     * @param dimensionToTp The dimension namespace to teleport to, such as mining_dimension:mining_dimension, disabled argument.
     * @param yaw The yaw for the location.
     * @param pitch The pitch for the location.
     */
    public static void handleDimensionTeleport(Player player, Vec3 pos, ResourceKey<Level> dimensionToTp, float yaw, float pitch) {

//            LOGGER.info("Dimension key: {}", dimensionToTp.toString());

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
     * Teleport the player to another dimension.
     * @param entity The player to teleport.
     * @param pos The position to set the player to.
     * @param yaw The yaw for the location.
     * @param pitch The pitch for the location.
     * @param destWorld The world to tepeort to.
     * @param safeSpawn If the spawn should be checked for hazards.
     * @param safeSpawnRange The range of the safe spawn.
     * @return
     */
    private static DimensionTransition dimensionTransition(Entity entity, Vec3 pos, float yaw, float pitch, ServerLevel destWorld, boolean safeSpawn, int safeSpawnRange) {
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
    private static BlockPos validPlayerSpawnLocation(ServerLevel world, BlockPos position, int maximumRange) {
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
