package net.kelsoncraft.kcmod.commands.misc;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.kelsoncraft.kcmod.util.PlayerUtil;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.commands.arguments.item.ItemInput;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Objects;

public class GiveTestCommand {

    // https://github.com/neoforged/NeoForge/issues/1630

    /**
     * Give Item command
     * @param context
     * @return
     */
    public static int giveItemCommand(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {

//        ItemStack item = context.getArgument("item", ItemStack.class);
        ItemInput item = ItemArgument.getItem(context, "item");
        int itemAmount = IntegerArgumentType.getInteger(context, "amount");

        ItemStack commandItem = item.createItemStack(1, false);

        PlayerUtil.giveItem(Objects.requireNonNull(context.getSource().getPlayer()), commandItem, itemAmount);

        return Command.SINGLE_SUCCESS;
    }

}
