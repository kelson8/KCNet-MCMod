package net.kelsoncraft.kcmod.commands; // New package for commands

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.kelsoncraft.kcmod.KCMod;
import net.kelsoncraft.kcmod.commands.misc.GiveEffectCommand;
import net.kelsoncraft.kcmod.commands.misc.GiveTestCommand;
import net.kelsoncraft.kcmod.commands.teleport.CustomTeleportCommand;
import net.kelsoncraft.kcmod.commands.teleport.DimensionTeleportCommand;
import net.kelsoncraft.kcmod.util.PlayerUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.DimensionArgument;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.bus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Objects;

import static net.minecraft.core.registries.Registries.DIMENSION_TYPE;
import static net.minecraft.core.registries.Registries.ITEM;

// Import the main mod class to access static properties

public class KCCommands {
    // I need to try to setup Patchouli Data gen so it can generate the json files for Patchouli
    // I can use parts of this MIT licensed project for that.
    // https://github.com/KhanhPham05/PatchouliDataGen/blob/master/src/main/java/com/khanhpham/patchoulidatagen/examplecode/PatchouliGeneratorImpl.java

    // I didn't know this could be used in the translations
    // en-us.json:
    // "commands.neoessentials.teleport.admin.teleported_player_coords": "Teleported {0} to coordinates ({1}, {2}, {3}).",
    //

    // Commands, can be used like this with a translation, example from NeoEssentials
    /*
        player.teleportTo(player.serverLevel(), x, y, z, player.getYRot(), player.getXRot());
        ctx.getSource().sendSuccess(() -> MessageUtil.success("commands.neoessentials.teleport.admin.teleported_player_coords",
          player.getName().getString(), String.valueOf((int) x), String.valueOf((int) y), String.valueOf((int) z)), true);

     */


    /**
     * Event handler for registering commands.
     * This method is subscribed to the RegisterCommandsEvent, which is fired when commands are being registered.
     * It must be static if registered via `NeoForge.EVENT_BUS.register(KCCommands.class)`.
     *
     * @param event The RegisterCommandsEvent instance.
     */
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        // Get the command dispatcher from the event
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        // Register the '/kc' command
        // TODO Test permissions later
        dispatcher.register(
                Commands.literal("kc") // Defines the root command: /kc
                        .then(Commands.literal("version") // Defines a subcommand: /kc version
                                .executes(KCCommands::executeVersionCommand) // Specifies the method to execute when this subcommand is run
                        )

                        // Position command test, teleport the player to the specified coordinates.
                        .then(Commands.literal("pos") // Defines subcommand: /kc pos
                                .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                                        .then(Commands.argument("coords", Vec3Argument.vec3())
                                        .executes(CustomTeleportCommand::teleportCommand)
                                        )
                        )

                        // Dimensional teleport, tested working with mining dimension data pack.
                        // I got the idea for the ResourceArgument from the SummonCommand.
                        // TODO Try to figure out Luckperms or FTB Ranks for these permissions later.
                        .then(Commands.literal("dimtp")
                                .requires(s -> s.hasPermission(Commands.LEVEL_GAMEMASTERS))
                                        .then(Commands.argument("coords", Vec3Argument.vec3())
                                                        .then(Commands.argument("dimension", DimensionArgument.dimension())
//                                                .then(Commands.argument("yaw", DoubleArgumentType.doubleArg())
//                                                .then(Commands.argument("pitch", DoubleArgumentType.doubleArg())
                                                        .executes(DimensionTeleportCommand::dimensionTeleportCommand)
                                                )
                                        )
                                    )

                        //
                        // Give players items, like with /i from essentials.
                        //----

                        .then(Commands.literal("give")
                        .requires(s -> s.hasPermission(Commands.LEVEL_GAMEMASTERS))
                                        .then(Commands.argument("item", ItemArgument.item(event.getBuildContext()))
                                        .then(Commands.argument("amount", IntegerArgumentType.integer())
                                        .executes(GiveTestCommand::giveItemCommand)
                                )
                            )
                        )

                        //---

                        // Display player XP on screen.
                        .then(Commands.literal("getxp").executes(MessageCommands::messagePlayerXp))

                        // Give the player night vision
                        .then(Commands.literal("nv")
                                .requires(s -> s.hasPermission(Commands.LEVEL_GAMEMASTERS))
                                .executes(GiveEffectCommand::giveNightvisionCommand))

                         // Incomplete effect command
//                        .then(Commands.literal("effect")
//                                .requires(s -> s.hasPermission(Commands.LEVEL_GAMEMASTERS))
//                                .then(Commands.argument("give", ResourceArgument.getMobEffect()))
//                        )

                        // Display a test toast message
//                        .then(Commands.literal("toast").executes(MessageCommands::messageToastTest))

                        // Display a test popup message
//                        .then(Commands.literal("popup").executes(MessageCommands::messagePopupTest))

//                        .then(Commands.literal("spawnmob")
//                                .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
//                                .then(Commands.argument("x", DoubleArgumentType.doubleArg()) // Defines double argument 'x'
//                                        .then(Commands.argument("y", DoubleArgumentType.doubleArg()) // Defines double argument 'y'
//                                                .then(Commands.argument("z", DoubleArgumentType.doubleArg()) // Defines double argument 'z'
//                                        .executes(SummonCommand.createEntity(dispatcher, EntityType.ZOMBIE, ))



                // You can add more subcommands here if needed
        );
    }

    /**
     * Executes the '/kc version' command.
     * This method is called when a player runs '/kc version'.
     *
     * @param context The command context, providing access to the command source.
     * @return The result of the command execution (typically 1 for success).
     */
    private static int executeVersionCommand(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();

        //---- Mod info
        // Name: (e.g., Aqua for label, Gold for value)
        MutableComponent nameComponent = Component.literal("Name: ").withStyle(ChatFormatting.AQUA)
                .append(Component.literal(KCMod.MOD_NAME).withStyle(ChatFormatting.GOLD));

        // Version: (e.g., Aqua for label, Yellow for value)
        MutableComponent versionComponent = Component.literal("Version: ").withStyle(ChatFormatting.AQUA)
                .append(Component.literal(KCMod.MOD_VERSION).withStyle(ChatFormatting.YELLOW));

        // Description: (e.g., Aqua for label, Light Purple for value)
        MutableComponent descriptionComponent = Component.literal("Description: ").withStyle(ChatFormatting.AQUA)
                .append(Component.literal(KCMod.MOD_DESCRIPTION).withStyle(ChatFormatting.LIGHT_PURPLE));

        //---- Send messages


        // --- Mod Details --- (e.g., Green)
        source.sendSuccess(() -> Component.literal("--- Mod Details ---").withStyle(ChatFormatting.GREEN), false);

        source.sendSuccess(() -> nameComponent, false);
        source.sendSuccess(() -> versionComponent, false);
        source.sendSuccess(() -> descriptionComponent, false);

        source.sendSuccess(() -> Component.literal("-------------------").withStyle(ChatFormatting.GREEN), false);

        return Command.SINGLE_SUCCESS;
    }
}
