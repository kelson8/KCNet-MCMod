package net.kelsoncraft.kcmod.commands.misc;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.kelsoncraft.kcmod.util.ChatColors;
import net.kelsoncraft.kcmod.util.MessageUtil;
import net.kelsoncraft.kcmod.util.PlayerUtil;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public class HealCommand {

    /**
     * Heal the player with max health and max food.
     * @param context The source player to heal.
     * @return A success message
     */
    public static int healCommand(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        Entity playerEntity = source.getEntity();

        if (playerEntity instanceof ServerPlayer player) {
            PlayerUtil.healPlayer(player);
        }

        return Command.SINGLE_SUCCESS;
    }


}
