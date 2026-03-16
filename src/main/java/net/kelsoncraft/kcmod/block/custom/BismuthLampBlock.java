package net.kelsoncraft.kcmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

// https://www.youtube.com/watch?v=U3y55P4c-P8&list=PLKGarocXCE1G6CQOoiYdMVx-E1d9F_itF&index=13

public class BismuthLampBlock extends Block {
    // Clicked can be used if clicking the block should turn it on
//    public static final BooleanProperty CLICKED = BooleanProperty.create("clicked");
    // I have this setup like a Redstone Lamp now
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public BismuthLampBlock(Properties properties) {
        super(properties);
        // Set the default value, make the lamp turned off when placed.
//        this.registerDefaultState(this.defaultBlockState().setValue(CLICKED, false));
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
    }

    // To enable clicking on the block to turn it on, enable this and disable the neighborChanged, and tick methods
//    @Override
//    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hitResult) {
//        if(!level.isClientSide) {
//            // Turning it on/off
//            boolean currentState = state.getValue(CLICKED);
//            level.setBlockAndUpdate(pos, state.setValue(CLICKED, !currentState));
//        }
//
//        return InteractionResult.SUCCESS;
//    }

    // Required for new properties on a block
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
//        builder.add(CLICKED);
        builder.add(LIT);
    }

    /**
     * Update the block to be more like a Redstone Lamp
     * Look into RedstoneLampBlock.java to figure out how to do this.
     */
    @Override
    protected void neighborChanged(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos,
                                   @NotNull Block neighborBlock, @NotNull BlockPos neighborPos, boolean movedByPiston) {

        if (!level.isClientSide) {
            boolean isLit = state.getValue(LIT);
            if (isLit != level.hasNeighborSignal(pos)) {
                if (isLit) {
                    level.scheduleTick(pos, this, 4);
                } else {
                    level.setBlock(pos, state.cycle(LIT), 2);
                }
            }
        }
    }

    @Override
    protected void tick(BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (state.getValue(LIT) && !level.hasNeighborSignal(pos)) {
            level.setBlock(pos, state.cycle(LIT), 2);
        }
    }
}
