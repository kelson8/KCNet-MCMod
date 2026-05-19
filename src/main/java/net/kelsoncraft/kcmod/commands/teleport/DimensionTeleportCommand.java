package net.kelsoncraft.kcmod.commands.teleport;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.kelsoncraft.kcmod.util.PlayerUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.phys.Vec3;

public class DimensionTeleportCommand {

    public static int dimensionTeleportCommand(CommandContext<CommandSourceStack> command) {
        PlayerUtil playerUtil = new PlayerUtil();
        Entity playerEntity = command.getSource().getEntity();
        if(playerEntity instanceof Player player) {
//            command.getSource().getEntity().teleportTo();
            double x = DoubleArgumentType.getDouble(command, "x");
            double y = DoubleArgumentType.getDouble(command, "y");
            double z = DoubleArgumentType.getDouble(command, "z");

//            String dimensionToTp = StringArgumentType.getString(command, "dimension");

            // TODO Figure out how to set this up, once I fix it auto complete will work for my dimension command.
//            ResourceArgument dimensionToTp = ResourceArgument.getResource(command., "dimension", Registries.DIMENSION_TYPE);

//            int yaw = IntegerArgumentType.getInteger(command, "yaw");
//            int pitch = IntegerArgumentType.getInteger(command, "pitch");

//            String[] dimensionToTp = {"mining_dimension", "mining_dimension"};

//            playerUtil.handleDimensionTeleport(player, new Vec3(x, y, z), yaw, pitch, dimensionToTp);
            playerUtil.handleDimensionTeleport(player, new Vec3(x, y, z), 0, 0);
//            command.getSource().sendSuccess(() -> Component.literal("Teleported to " + x + ", " + y + ", " + z).withStyle(ChatFormatting.GREEN), false);
//            command.getSource().sendSuccess(() -> Component.literal("Teleported to {}{}{}").withStyle(ChatFormatting.GREEN), false);
        } else {
            // If the command was not executed by a player, send an error message
            command.getSource().sendFailure(Component.literal("This command can only be used by a player!").withStyle(ChatFormatting.RED));
            return -1; // Indicate command failure
        }

        return Command.SINGLE_SUCCESS;
    }

//    handleDimensionTeleport
}
