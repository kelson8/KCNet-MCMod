package net.kelsoncraft.kcmod.item.custom;

import net.kelsoncraft.kcmod.block.ModBlocks;
import net.kelsoncraft.kcmod.component.ModDataComponents;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChiselItem extends Item {

    // Map of all items for the chisel
    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.END_STONE, Blocks.END_STONE_BRICKS,
                    Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS,


                    // Sandstone to cut sandstone, and cut sandstone to chiseled sandstone.
                    Blocks.SANDSTONE, Blocks.CUT_SANDSTONE,
                    Blocks.CUT_SANDSTONE, Blocks.CHISELED_SANDSTONE,


                    // Extras
//                    Blocks.GOLD_BLOCK, Blocks.IRON_BLOCK,
//                    Blocks.IRON_BLOCK, Blocks.STONE,
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

                    // New for getting coordinates for ModDataComponents
                    // Saves to the item tooltip.
                    // null can be passed to this to clear it.
                    context.getItemInHand().set(ModDataComponents.COORDINATES, context.getClickedPos());

                }
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        // If the player is pressing shift, then show the description.
        if(Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.kcnet_mod.chisel.shift_down"));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.kcnet_mod.chisel"));
        }

        // Set last used coordinates to display on the chisel
        if(stack.get(ModDataComponents.COORDINATES) != null) {
            String lastBlockChanged = "Last Block changed at "
                    + "X: " + Objects.requireNonNull(stack.get(ModDataComponents.COORDINATES)).getX()
                    + " Y: " + Objects.requireNonNull(stack.get(ModDataComponents.COORDINATES)).getY()
                    + " Z: " + Objects.requireNonNull(stack.get(ModDataComponents.COORDINATES)).getZ();
            tooltipComponents.add(Component.literal(lastBlockChanged));
        }


        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
