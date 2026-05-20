package net.kelsoncraft.kcmod.commands.teleport;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.kelsoncraft.kcmod.Config;
import net.kelsoncraft.kcmod.KCMod;
import net.kelsoncraft.kcmod.util.PlayerUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.DimensionArgument;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.commands.arguments.coordinates.Coordinates;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.phys.Vec3;

public class DimensionTeleportCommand {

    public static int dimensionTeleportCommand(CommandContext<CommandSourceStack> command) throws CommandSyntaxException {
        PlayerUtil playerUtil = new PlayerUtil();
        Entity playerEntity = command.getSource().getEntity();
        if(playerEntity instanceof Player player) {
            // Some of this came from DirectTeleportCommands.java in NeoEssentials
            // I didn't know I could do this like in Vanilla, now I can use ~, and -~ for positions, and it fills it in easier.
            Coordinates coords = Vec3Argument.getCoordinates(command, "coords");
            Vec3 pos = coords.getPosition(command.getSource());

            // Added for getting the dimension argument
            ServerLevel dimensionToTp = DimensionArgument.getDimension(command, "dimension");
            String[] dimensionSplit = dimensionToTp.dimension().location().toString().split(":");
//            KCMod.LOGGER.info("Dimension split: {}:{}", dimensionSplit[0], dimensionSplit[1]);

            ResourceKey<Level> dimensionKey = ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(dimensionSplit[0], dimensionSplit[1]));

//            KCMod.LOGGER.info("Dimension key: {}", dimensionKey);


            // These aren't really needed, I wonder how I can do optional command arguments?
//            int yaw = IntegerArgumentType.getInteger(command, "yaw");
//            int pitch = IntegerArgumentType.getInteger(command, "pitch");

//            String[] dimensionToTp = {"mining_dimension", "mining_dimension"};

            playerUtil.handleDimensionTeleport(player, pos, dimensionKey, 0, 0);
//            command.getSource().sendSuccess(() -> Component.literal("Teleported to " + x + ", " + y + ", " + z).withStyle(ChatFormatting.GREEN), false);
        } else {
            // If the command was not executed by a player, send an error message
            command.getSource().sendFailure(Component.literal("This command can only be used by a player!").withStyle(ChatFormatting.RED));
            return -1; // Indicate command failure
        }

        return Command.SINGLE_SUCCESS;
    }

}
