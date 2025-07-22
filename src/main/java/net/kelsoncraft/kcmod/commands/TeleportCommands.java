package net.kelsoncraft.kcmod.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.kelsoncraft.kcmod.KCMod;
import net.kelsoncraft.kcmod.commands.teleport.ReturnHomeCommand;
import net.kelsoncraft.kcmod.commands.teleport.SetHomeCommand;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.server.command.ConfigCommand;

// I got this working, I had to change something in the KCMod.java file.

// Changed from this
//        modEventBus.register(ModEvents.class);
// To this
//        NeoForge.EVENT_BUS.register(ModEvents.class);

public class TeleportCommands {

    // For registering the commands.
    // TODO Test this
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(Commands.literal("home")

                .then(Commands.literal("set")
                        .executes((command) -> { // Execute the command
                            return SetHomeCommand.setHomeCommand(command.getSource());
                        }))

                .then(Commands.literal("return")
                        .executes(command -> ReturnHomeCommand.returnHomeCommand(command.getSource()))
                )

        );
    }


//        @SubscribeEvent
//    public static void onCommandsRegister(RegisterCommandsEvent event) {
//        new SetHomeCommand(event.getDispatcher());
//        new ReturnHomeCommand(event.getDispatcher());
//
//        ConfigCommand.register(event.getDispatcher());
//    }


    // I don't think this one works.

    /**
     * Event handler for PlayerEvent.Clone.
     * This event is fired when a player is cloned (e.g., on death, changing dimensions).
     * It's used to transfer persistent data from the old player entity to the new one.
     * This is for when the player dies, and it should reset the NBT back.
     * <a href="https://youtu.be/bYH2i-KOLgk?t=750">...</a>
     * @param event The PlayerEvent.Clone instance.
     */
    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event) {
        // Ensure this logic only runs on the server side
        // event.getOriginal().getLevel().isClientSide() checks if the world is client-side.
        // The '!' negates it, so the block runs if it's NOT client-side (i.e., server-side).
        // Using getLevel() instead of direct 'level' access.
        if (!event.getOriginal().level().isClientSide()) {
            // Get the integer value for "homepos" from the original player's persistent data.
            // Assuming "homepos" stores a single integer (e.g., a custom home ID).
            int homePosValue = event.getOriginal().getPersistentData().getInt(KCMod.MOD_ID + "homepos");

            // Put the retrieved integer value into the new player's persistent data.
            // Corrected from putIntArray to putInt, as getInt returns a single int.
            // Using getNewPlayer() instead of getPlayer().
            event.getEntity().getPersistentData().putInt(KCMod.MOD_ID + "homepos", homePosValue);

            KCMod.LOGGER.info("Transferred homepos data for player {} (value: {})",
                    event.getEntity().getName().getString(), homePosValue); // Use getNewPlayer() for logging
        }
    }


}
