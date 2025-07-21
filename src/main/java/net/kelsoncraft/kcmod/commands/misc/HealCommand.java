package net.kelsoncraft.kcmod.commands.misc;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.kelsoncraft.kcmod.util.ChatColors;
import net.kelsoncraft.kcmod.util.MessageUtil;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public class HealCommand {

    public HealCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("heal")
                .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                        .executes(command -> healPlayer(command.getSource()))
        );
    }

    /**
     * Heal the player with max health and max food.
     * @param source The source player to heal.
     * @return A success message
     */
    private static int healPlayer(CommandSourceStack source) {
        Entity playerEntity = source.getEntity();

        if (playerEntity instanceof ServerPlayer player) {
            player.heal(20.0f);
            player.getFoodData().setExhaustion(0.0f);
            player.getFoodData().setFoodLevel(20);
            // This gives me too much saturation lol.
//            player.getFoodData().setSaturation(20.0f);

            MessageUtil.sendColorMessage(source, "You have been healed!", ChatColors.AQUA);
        }

        return Command.SINGLE_SUCCESS;
    }
}
