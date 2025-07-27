package net.kelsoncraft.kcmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.kelsoncraft.kcmod.test.PlayerTest;
import net.kelsoncraft.kcmod.util.InventoryUtil;
import net.kelsoncraft.kcmod.util.PlayerUtil;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AnvilMenu;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
            InventoryUtil.inventoryTest(player);
        }

        return Command.SINGLE_SUCCESS;
    }

//    public int anvilCommand(CommandContext<CommandSourceStack> context) {

    /**
     * Future command use will be for an Anvil.
     * I'm not sure how to do it just yet
     * Look into this library:
     * <a href="https://www.curseforge.com/minecraft/mc-mods/puzzles-lib">puzzles-lib</a>
     * @param context The command context
     * @return Command success
     */
    public static int anvilCommand(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        Entity entity = source.getEntity();
        ServerPlayer player = source.getPlayer();

        if(player != null && PlayerUtil.isPlayer(entity)) {
            // TODO Figure out how to do this.
//            AbstractContainerMenu
//            AnvilMenu anvilMenu = new AnvilMenu(1, player.getInventory());
            // TODO Test this, not sure what it'll do.
//            AnvilMenu anvilMenu = new AnvilMenu(1, new Inventory(player));
//
//            MenuProvider menuProvider = new MenuProvider() {
//                @Override
//                public @NotNull Component getDisplayName() {
//                    return Component.literal("Test Menu");
//                }
//
//                @Override
//                public @Nullable AbstractContainerMenu createMenu(int i, @NotNull Inventory inventory, @NotNull Player player) {
//                    return null;
//                }
//            };

//            Inventory inventory = new Inventory(player);
//            AnvilScreen anvilScreen = new AnvilScreen();
//            menuProvider.createMenu(1, anvilScreen.);

//            player.getInventory().
//            player.openMenu(menuProvider);
//            player.openMenu()
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
