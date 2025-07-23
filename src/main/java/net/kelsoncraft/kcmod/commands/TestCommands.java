package net.kelsoncraft.kcmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.kelsoncraft.kcmod.test.PlayerTest;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public class TestCommands {

    /**
     * Basic test inventory, it can be used as a trash can so far.
     * @param context The command context
     * @return Command success
     */
    public static int testInvCommand(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        Entity entity = source.getEntity();

        if(entity instanceof ServerPlayer player) {
            PlayerTest.inventoryTest(player);
        }

        return Command.SINGLE_SUCCESS;
    }

    // TODO Try to use this later.
//    private void clientTest(CommandSourceStack source) {
//
//        Entity currentPlayer = source.getEntity();
//        if(currentPlayer instanceof Player) {
//            Minecraft client = Minecraft.getInstance();
//        }
//    }


    // https://github.com/FTBTeam/FTB-Essentials/blob/main/common/src/main/java/dev/ftb/mods/ftbessentials/commands/SimpleCommandPlayer.java
//    public List<LiteralArgumentBuilder<CommandSourceStack>> register() {
//        var command = Commands.literal(name);

//    }

}
