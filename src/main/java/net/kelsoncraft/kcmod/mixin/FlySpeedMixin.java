package net.kelsoncraft.kcmod.mixin;

import net.kelsoncraft.kcmod.Config;
import net.kelsoncraft.kcmod.util.ChatColors;
import net.kelsoncraft.kcmod.util.MessageUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.GrassBlock;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Activates flying speed changes in the config when right clicking with any block.
// I will change this to a command later.
// TODO Make this to where it can only be clicked one time every so often, instead of being spammed.

@Mixin(Item.class)
public class FlySpeedMixin {

    // If this is toggled, then it'll enable this Mixin testing.
    // It mostly just allows you to change your flying speed set to a value from the config and if it's enabled.
    // I need to restrict this to certain blocks or something, possibly make the player only
    // be able to fly if holding a flower with special NBT or something.
    @Unique
    private final boolean flySpeedChanges = false;

    @Shadow
    @Final
    private static Logger LOGGER;
    // TODO Make these able to be changed with a command, for now I am adding the values to a config for testing.

    @Unique
    private int flySpeedConfig = 1;

    @Unique
    float defaultFlySpeed = 0.1f;

    // Flying speeds are converted like this
    // 1 = 0.1
    // 2 = 0.2
    // 3 = 0.3
    // 10 = 1.0

    /**
     * Check if the flying speed is valid.
     * This is not implemented yet.
     * @param flyingSpeed The flying speed integer from 1 to 10
     * @return If the flying speed is valid
     */
    @Unique
    private boolean IsFlySpeedValid(int flyingSpeed) {
//        return !(flySpeed > 1.0f) && !(flySpeed < 0.0f);
        return false;
    }

    /**
     * Sets the players flying speed
     * @param player The player to set the fly speed for
     * @param newFlySpeed An integer value from 1-10 for the flying speed.
     */
    @Unique

    private void setFlySpeed(Player player, int newFlySpeed) {
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

    // Test for changing players fly speed
    @Inject(method = "use", at = @At("HEAD"))
    public void useTest(Level level, Player player, InteractionHand usedHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {

        // Do nothing if this isn't enabled.
        if(!flySpeedChanges)
        {
            return;
        }

        ItemStack itemStack = player.getItemInHand(usedHand);
        Item heldItem = itemStack.getItem();

        // This works!
        // TODO Figure out how to check if this has a specific NBT value, such as fly: enabled or something.
//        Item newItem = Items.ACACIA_LOG;
        Item newItem = Items.POPPY;

        if(heldItem == newItem) {
            LOGGER.info("Item right clicked with special data");
            // This works now, I had to fix something in the function
            if (Config.COMMON.FAST_FLY_SPEED_TOGGLE.get()) {
                setFlySpeed(player, Config.COMMON.FLY_SPEED.get());
            }
        } else {

//            LOGGER.info("Item ID clicked with: {}", heldItem.getName(itemStack));
            LOGGER.info("Item right clicked with: {}", heldItem);
        }

//        if(itemStack.is(item))
//        if (itemStack instanceof TntBlock)
//        if(itemStack == ItemStack.)


    }



}
