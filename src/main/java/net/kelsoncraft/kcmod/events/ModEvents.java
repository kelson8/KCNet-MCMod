package net.kelsoncraft.kcmod.events;

import net.kelsoncraft.kcmod.KCMod;
import net.kelsoncraft.kcmod.item.custom.HammerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.HashSet;
import java.util.Set;

@EventBusSubscriber(modid = KCMod.MOD_ID)
public class ModEvents {

    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    // Done with the help of https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java
    // Don't be a jerk License
    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        // Make sure the tool is the hammer, the player is a server player, and they are not in creative.
        if(mainHandItem.getItem() instanceof HammerItem hammer &&
                player instanceof ServerPlayer serverPlayer &&
                !serverPlayer.gameMode.isCreative()) {

            // Get the initial block position for the hammer.
            BlockPos initialBlockPos = event.getPos();
            // If the harvested blocks are the initial block position, don't do anything.
            if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            // Set the range, initial block position and the player.
            for(BlockPos pos : HammerItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                if(pos.equals(initialBlockPos) || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                // Add and remove the blocks, otherwise this could possibly cause a crash.
                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }

}
