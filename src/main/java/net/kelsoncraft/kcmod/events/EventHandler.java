package net.kelsoncraft.kcmod.events;

import net.kelsoncraft.kcmod.KCMod;
import net.kelsoncraft.kcmod.util.MessageUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AnvilUpdateEvent;
import net.neoforged.neoforge.event.entity.EntityMobGriefingEvent;
import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;
import net.neoforged.neoforge.event.entity.EntityTravelToDimensionEvent;

@EventBusSubscriber(modid = KCMod.MOD_ID)
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

    // Here is one more specific to 1.21.1
    // https://github.com/neoforged/NeoForge/tree/port/1.21/src/main/java/net/neoforged/neoforge/event
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
//    public static void onItemThrown(ItemTossEvent e) {
//        if (e.)
//    }

//    @SubscribeEvent
//    public static void toggleMobGriefing(EntityMobGriefingEvent e, boolean toggle) {
//        e.setCanGrief(toggle);
//    }

//    @SubscribeEvent
//    public static void onEntityStruckByLightning(EntityStruckByLightningEvent e) {
//
//    }

    @SubscribeEvent
    public static void onEntityTravelToDimension(EntityTravelToDimensionEvent e) {
        if(e.getEntity() instanceof ServerPlayer player) {
//            if(e.getDimension().location().getNamespace())

            MessageUtil.printToConsole("Entity dimension namespace: " + e.getDimension().location().getNamespace());

            // Seems to return 'overworld', 'the_nether' or 'the_end', this is probably what I would to check what dimension the player is in.
            // Hmm, using the Java console when debugging is very useful.
            // I may set up my own log file for this.
            MessageUtil.printToConsole("Entity dimension path: " + e.getDimension().location().getPath());

            String dimension = e.getDimension().location().getPath();

            switch(dimension) {
                case "overworld" ->
                        {
                            MessageUtil.printToConsole(player.getDisplayName().getString());
//                            player.setSpeed();
                            player.sendSystemMessage(Component.literal("Switched to the overworld."));
                        }
                case "the_nether" -> player.sendSystemMessage(Component.literal("Switched to the nether."));
                case "the_end" -> player.sendSystemMessage(Component.literal("Switched to the end."));
            }

        }
    }


    // TODO See if I can return this value in chat
    // Well this just spams the chat with 0
    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent e) {

//        e.getPlayer().sendSystemMessage(Component.literal("Cost for anvil update: " + e.getCost()));
//        return e.getCost();
    }

//    @SubscribeEvent
//    public void enderDragonTest()

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
