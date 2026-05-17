package net.kelsoncraft.kcmod.events;

// For testing with TNT, looking into how this mod below works.
// https://github.com/Krxwallo/InstantTNT/blob/1.20.X/src/main/java/io/github/krxwallo/instant_tnt/event/EventHandler.java

// I couldn't get this to launch on NeoForge 1.21.1.

//import luckytntlib.util.tnteffects.PrimedTNTEffect;
//import net.kelsoncraft.kcmod.Config;
//import net.kelsoncraft.kcmod.KCMod;
//import net.minecraft.core.BlockPos;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.item.PrimedTnt;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.block.TntBlock;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.fml.common.EventBusSubscriber;
//import net.neoforged.fml.common.Mod;
//import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.logging.Logger;

import static net.kelsoncraft.kcmod.KCMod.LOGGER;

//@EventBusSubscriber(modid = KCMod.MOD_ID)
//public class TntHandler {
//
//    @SubscribeEvent
//    public static void onBlockPlace(BlockEvent.EntityPlaceEvent evt) {
//
//        // Disable this if not enabled, by default this is disabled in the config.
//        if(!Config.COMMON.TNT_AUTO_EXPLODE.get()) {
////            LOGGER.debug("TNT auto explode not enabled, not running action!");
//            return;
//        }
//
//        if(evt.getPlacedBlock().getBlock() instanceof TntBlock && evt.getEntity() instanceof Player) {
//            if(!evt.getEntity().isCrouching()) {
//                var server = evt.getLevel().getServer();
//                if(server == null) {
//                    LOGGER.error("Server is null in the on place event");
//                }
//
//                var world = server.overworld();
//                // Remove the TNT
//                world.setBlockAndUpdate(evt.getPos(), Blocks.AIR.defaultBlockState());
//                BlockPos pos = evt.getPos();
//                PrimedTnt tntEntity = null;
////                 primedTntEffect;
//                PrimedTNTEffect tntEffect =  null;
////                tntEffect.
//
//                if(!world.isClientSide()) {
//                    // Create a tnt entity at the position where the tnt block was
//                    tntEntity = new PrimedTnt(world, (double) pos.getX() + 0.5D,
//                            pos.getY(), (double) pos.getZ() + 0.5D, (LivingEntity) evt.getEntity());
//
//
//                    // Set the charge duration
//                    tntEntity.setFuse((int) (Config.COMMON.TNT_FUSE.get() * 20));
//
//                    // Add the entity to the world
//                    world.addFreshEntity(tntEntity);
//
//                    // Play the charge sound
//                    world.playSound(null, tntEntity.getX(), tntEntity.getY(), tntEntity.getZ(),
//                            SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
//                }
//                if (tntEntity == null) {
//                    LOGGER.warn("tntEntity is null.");
//                } else {
//                    LOGGER.debug("Charged TNT, charge duration: " + Config.COMMON.TNT_FUSE.get() + " seconds");
//                }
//            } else {
//                LOGGER.debug("Player is sneaking, ignoring tnt.");
//            }
//        }
//    }
//}
