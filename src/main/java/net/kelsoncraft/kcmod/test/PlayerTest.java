package net.kelsoncraft.kcmod.test;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerTest {

    /**
     * Does the player currently have armor
     * TODO Test this later.
     * @param player The player to check for.
     * @return If the player has armor currently on.
     */
    private boolean doesPlayerHaveArmor(ServerPlayer player) {

        return player.hasItemInSlot(EquipmentSlot.HEAD)
                || player.hasItemInSlot(EquipmentSlot.CHEST)
                || player.hasItemInSlot(EquipmentSlot.LEGS)
                || player.hasItemInSlot(EquipmentSlot.FEET);
    }

    //    private void Test1(Player player) {
    private void Test1(ServerPlayer player, ServerLevel level, Entity entity) {

        //        player.getAbilities().setFlyingSpeed(0.8f);

        //        level.playSound();
//        level.getBlockFloorHeight()
//        level.setDefaultSpawnPos();

        //----
        // Useful spawn functions
//        entity.getServer().getSpawnRadius()

//        entity.getServer().getSpawnProtectionRadius();
//        entity.getServer().isSpawningAnimals()
//        entity.getServer().isSpawningMonsters()

        // TODO Look at this for my fly command
        // Well this didn't work like I wanted it to, I'm not sure how to use this.
//        entity.setOnGround();

        // Some code and ideas came from here for the spawns.
        // https://github.com/Serilum/Set-World-Spawn-Point
        // https://github.com/Serilum/Set-World-Spawn-Point/blob/1.21.1/Common/src/main/java/com/natamus/setworldspawnpoint/events/WorldSpawnEvent.java#L44-L76


        Level world = player.level();
        if (world.isClientSide) {
            return;
        }

        ServerLevel serverWorld = (ServerLevel) world;
        // Get world spawn point
        BlockPos respawnLocation = serverWorld.getSharedSpawnPos();

//        player.getHealth();


        // TODO Test this later, I should be able to check for the players item in hand
        if(player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Items.ACACIA_CHEST_BOAT) {

        }

//        player.getAbilities().loadSaveData();

        // TODO Figure this out
//        player.getStats().getValue()

//        player.getStats().getValue(Stats.ANIMALS_BRED);
//        player.getStats().getValue((Block)Stats.BLOCK_MINED);
//        Stats.ANIMALS_BRED

//
        // TODO Figure out how to get if a player is in a minecart, boat, on a horse or other rideable mobs.
//        player.showVehicleHealth()
//        if(player.getVehicle())


        // This might be useful for throwing lightning where I am looking at like Essentials.
//        player.getEyePosition()

//        player.getServer().getPlayerList().broadcastAll();
    }


    private void setSpawn(ServerPlayer player, ServerLevel level, Entity entity) {
//        player.adjustSpawnLocation()
//        player.setRespawnPosition();


    }

    /**
     * I would like to make this open a chest,
     * Look into using the ChestMenu.java file.
     * TODO Test this.
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
            public @Nullable AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player player) {
                return ChestMenu.fourRows(id, inventory);
            }
        });

    }

}
