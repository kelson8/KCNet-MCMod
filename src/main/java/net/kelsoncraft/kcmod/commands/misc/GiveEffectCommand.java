package net.kelsoncraft.kcmod.commands.misc;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.kelsoncraft.kcmod.KCMod;
import net.kelsoncraft.kcmod.util.ChatColors;
import net.kelsoncraft.kcmod.util.EntityUtil;
import net.kelsoncraft.kcmod.util.MessageUtil;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class GiveEffectCommand {

    public static int giveNightvisionCommand(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = context.getSource().getPlayer();
        if(player == null){
            KCMod.LOGGER.warn("Player is not valid.");
            return -1;
        }

        if(player.hasEffect(MobEffects.NIGHT_VISION)){
            MessageUtil.sendColorMessage(player, "You already have night vision!", ChatColors.RED);
            return -1;
        }

        EntityUtil.giveEffectCommand(context.getSource(), MobEffects.NIGHT_VISION, 10000, 1, false);

        return Command.SINGLE_SUCCESS;
    }
}
