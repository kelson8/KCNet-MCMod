package net.kelsoncraft.kcmod.events;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityMobGriefingEvent;

//@EventBusSubscriber(modid = NeoForgeTest.MOD_ID)
public class EventHandler {

    // Test for basic events
    // https://docs.neoforged.net/docs/concepts/events/

    // Neo Forge has a lot of events.
    // https://github.com/neoforged/NeoForge/tree/1.21.x/src/client/java/net/neoforged/neoforge/client/event

    // This works! I can mess with a lot of this stuff.
//    @SubscribeEvent
//    public static void onLivingJump(LivingEvent.LivingJumpEvent e) {
//        LivingEntity entity = e.getEntity();
//        if (!entity.level().isClientSide()) {
////            entity.heal(1);
//            if(entity instanceof ServerPlayer player) {
//
//            }
//        }
//    }

//    @SubscribeEvent
//    public void onLivingDeath(LivingDeathEvent e) {
//        LivingEntity entity = e.getEntity();
//
////        if (!(entity instanceof ServerPlayer player)) {
////            for(int i = 0; i < )
//            // TODO Make this spawn a tnt block if a mob dies.
////        }
//    }

    //------------

    // I found more events here
    // https://github.com/neoforged/NeoForge/tree/1.21.x/src/main/java/net/neoforged/neoforge/event
//    @SubscribeEvent
//    public void onPlayerEnchantItem(PlayerEnchantItemEvent e) {
//        if(e.getEnchantedItem().getItem() == Items.WOODEN_SWORD){
//        }
//    }

//    @SubscribeEvent
//    public void onPlayerAttackEntity(AttackEntityEvent e) {
//        if(e.getTarget().getType() == EntityType.CAT) {
//
//        }
//    }

//    @SubscribeEvent
//    public void onItemThrown(ItemTossEvent e) {
//        if (e.)
//    }

    @SubscribeEvent
    public void toggleMobGriefing(EntityMobGriefingEvent e, boolean toggle) {
        e.setCanGrief(toggle);
    }

//    @SubscribeEvent
//    public void onEntityMount(EntityMountEvent e) {
//        if(e.isMounting()) {
//            Entity mountEntity = e.getEntityMounting();
//            if(mountEntity.getType() == EntityType.HORSE) {
//
//            }
//        }
//    }









}
