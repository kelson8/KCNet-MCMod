package net.kelsoncraft.neoforgetest.commands.misc;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class GiveTestCommand {


    public GiveTestCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("give_item")
                        .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .executes(command -> itemCommand(command.getSource())));
    }


    // TODO Figure out how to replicate this from Spigot, for a /fly command
    // https://github.com/kelson8/KBP/blob/master/src/main/java/net/Kelsoncraft/KBP/commands/FlyCommand.java
    /*
        Location location = player.getLocation();
    	int highestY = location.getWorld().getHighestBlockYAt(location);
		//Put the player on the ground so they don't die.
		location.setY(highestY + 1); //Add 1 so the player doesn't get stuck in the ground.
     */
    //
    // https://github.com/neoforged/NeoForge/issues/1630

    private int itemCommand(CommandSourceStack source) {

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
