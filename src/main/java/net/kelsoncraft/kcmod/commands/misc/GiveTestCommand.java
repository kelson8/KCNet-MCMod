package net.kelsoncraft.kcmod.commands.misc;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class GiveTestCommand {



    // https://github.com/neoforged/NeoForge/issues/1630

    public static int giveItemCommand(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        Entity playerEntity = source.getEntity();

        if (source.getEntity() instanceof Player player) {
//            player.openCommandBlock();
//            CommandBlockEntity commandBlockEntity = new CommandBlockEntity();
//            player.inventoryMenu


            //---
            // https://forums.minecraftforge.net/topic/151365-giving-a-player-an-item/
            // This works for adding an item to the players inventory, now to figure out how to get the user input.
            // Optionally, an inventory slot can also be specified before the item stack.
            player.getInventory().add(new ItemStack(Items.ACACIA_LOG));
            //---
        }
        return Command.SINGLE_SUCCESS;
    }

}
