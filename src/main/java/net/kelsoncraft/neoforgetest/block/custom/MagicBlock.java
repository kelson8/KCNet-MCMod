package net.kelsoncraft.neoforgetest.block.custom;

import net.kelsoncraft.neoforgetest.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TargetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

// Basic magic block test
// https://www.youtube.com/watch?v=DBNkfoVAzNE&list=PLKGarocXCE1G6CQOoiYdMVx-E1d9F_itF&index=7

// Requires an iron pickaxe to be mined in survival.

public class MagicBlock extends Block {
//public class MagicBlock extends TargetBlock {

    public MagicBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(@NotNull BlockState state, Level level, @NotNull BlockPos pos,
                                               @NotNull Player player, @NotNull BlockHitResult hitResult) {

        // Play a sound on the block.
        level.playSound(player, pos, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, 1f, 1f);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Entity entity) {

        // Check if the entity is an item
        if(entity instanceof ItemEntity itemEntity) {
            // If item dropped onto block is Raw Bismuth
            if(itemEntity.getItem().getItem() == ModItems.RAW_BISMUTH.get()) {
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

    // Magma block testing
//    @Override
//    public void stepOn(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, Entity entity) {
//
//        // Taken from MagmaBlock.
////        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity) {
////            entity.hurt(level.damageSources().hotFloor(), 1.0F);
////        }
//
//        super.stepOn(level, pos, state, entity);
//    }


    // TODO Setup a target block to have a redstone signal like in vanilla.
//    @Override
//    protected void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
//        super.onProjectileHit(level, state, hit, projectile);
//    }


    //    @Override
//    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
//        return super.isFlammable(state, level, pos, direction);
//    }
}
