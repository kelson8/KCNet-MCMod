package net.kelsoncraft.kcmod.block.custom;

import net.kelsoncraft.kcmod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// Basic magic block test
// https://www.youtube.com/watch?v=DBNkfoVAzNE&list=PLKGarocXCE1G6CQOoiYdMVx-E1d9F_itF&index=7

// Requires an iron pickaxe to be mined in survival.

public class MagicBlock extends Block {

    public MagicBlock(Properties properties) {
        super(properties);

    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, Level level, @NotNull BlockPos pos,
                                                        @NotNull Player player, @NotNull BlockHitResult hitResult) {
        // Play a sound on the block.
        level.playSound(player, pos, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, 1f, 1f);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Entity entity) {
        // Magma block test, this makes the block act like a magma block
        // It will make you take damage also.

        // Look into magma block source file.
//        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity) {
//            entity.hurt(level.damageSources().hotFloor(), 1.0F);
//        }
        //

//        if(entity instanceof Player player) {
//            if(InvTest.hasItemInVanillaInventory(player, Items.DIAMOND)){
//
//            }
//        }


        // Check if the entity is an item
        if(entity instanceof ItemEntity itemEntity) {
            // If the item stack thrown onto the block, is part of this transformable items tag.
            if(isValidItem(itemEntity.getItem())) {
//            if(itemEntity.getItem().getItem() == ModItems.RAW_BISMUTH.get()) {

                // Change item to a Diamond
                itemEntity.setItem(new ItemStack(Items.DIAMOND, itemEntity.getItem().getCount()));
            }

            // Change dandelion to wither rose
            if(itemEntity.getItem().getItem() == Items.DANDELION) {
                itemEntity.setItem(new ItemStack(Items.WITHER_ROSE, itemEntity.getItem().getCount()));
            }


        }

        super.stepOn(level, pos, state, entity);
    }

    /// Check if item is valid for being dropped onto the magic block.
    private boolean isValidItem(ItemStack item) {

        return item.is(ModTags.Items.TRANSFORMABLE_ITEMS);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        // Formatting codes can be used in the en_us.json for colors
        // https://minecraft.wiki/w/Formatting_codes
        tooltipComponents.add(Component.translatable("tooltip.kcnet_mod.magic_block.tooltip"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }


    //--------------


    //    @Override
//    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
//        return super.isFlammable(state, level, pos, direction);
//    }
}
