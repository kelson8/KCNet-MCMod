package net.kelsoncraft.neoforgetest.item.custom;

import net.kelsoncraft.neoforgetest.block.ModBlocks;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ChiselItem extends Item {

    // Map of all items for the chisel
    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.END_STONE, Blocks.END_STONE_BRICKS,
                    Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS,

                    // Extras
                    Blocks.GOLD_BLOCK, Blocks.IRON_BLOCK,
                    Blocks.IRON_BLOCK, Blocks.STONE,
                    // .get is required for custom blocks.
                    Blocks.NETHERRACK, ModBlocks.BISMUTH_BLOCK.get()


            );

    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        // Check what the clicked block was.
        if(CHISEL_MAP.containsKey(clickedBlock)){
            // On the server
            if(!level.isClientSide) {

                level.setBlockAndUpdate(context.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());

                // Make sure the player exists
                if (context.getPlayer() != null) {
                    context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                            item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                    level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);

                }
            }
        }

        return InteractionResult.SUCCESS;
    }

//    override
}
