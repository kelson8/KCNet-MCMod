package net.kelsoncraft.neoforgetest.events;

import net.kelsoncraft.neoforgetest.NeoForgeTest;
import net.minecraft.client.telemetry.TelemetryProperty;
import net.minecraft.server.commands.SummonCommand;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.GameType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

//@EventBusSubscriber(modid = NeoForgeTest.MOD_ID)
public class EventHandler {

    // Test for basic events
    // https://docs.neoforged.net/docs/concepts/events/

    // Neo Forge has a lot of events.
    // https://github.com/neoforged/NeoForge/tree/1.21.x/src/client/java/net/neoforged/neoforge/client/event

    // This works! I can mess with a lot of this stuff.
//    @SubscribeEvent
//    public static void onLivingJump(LivingEvent.LivingJumpEvent event) {
//        LivingEntity entity = event.getEntity();
//        if (!entity.level().isClientSide()) {
////            entity.heal(1);
//            if(entity instanceof ServerPlayer player) {
//
//            }
//        }
//    }

//    @SubscribeEvent
//    public void onLivingDeath(LivingDeathEvent event) {
//        LivingEntity entity = event.getEntity();
//
////        if (!(entity instanceof ServerPlayer player)) {
////            for(int i = 0; i < )
//            // TODO Make this spawn a tnt block if a mob dies.
////        }
//    }
}
