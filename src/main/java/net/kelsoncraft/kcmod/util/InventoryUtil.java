package net.kelsoncraft.kcmod.util;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InventoryUtil {

    // TODO Figure out how to use this.
    public static class VAnvilMenu extends AnvilMenu {
        public VAnvilMenu(int containerId, Inventory inv, ServerPlayer player) {
            super(containerId, inv, ContainerLevelAccess.create(player.level(), player.blockPosition()));
        }
    }

    /**
     * I would like to make this open a chest,
     * Look into using the ChestMenu.java file.
     * This works as a trash can but cannot store items in it.
     *
     * @param player The player to open an inventory for.
     */
    public static void inventoryTest(ServerPlayer player) {

        player.openMenu(new MenuProvider() {
            @Override
            public @NotNull Component getDisplayName() {
                return Component.literal("Test Inventory");
            }

            @Override
            public @NotNull AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player player) {
                return ChestMenu.fourRows(id, inventory);
            }
        });

    }
}
