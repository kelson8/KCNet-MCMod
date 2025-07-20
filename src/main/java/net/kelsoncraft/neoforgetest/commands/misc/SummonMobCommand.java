package net.kelsoncraft.neoforgetest.commands.misc;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.kelsoncraft.neoforgetest.util.EntityUtil;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Wolf;

// Some code in this file came from here
// https://github.com/TheTurkeyDev/ChanceCubes/blob/dev_1.21.x/src/main/java/chanceCubes/rewards/defaultRewards/WolvesToCreepersReward.java

public class SummonMobCommand {

    // This works
    public SummonMobCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("summon_mob")
                .executes(command -> {
                    return summonMob(command.getSource());
                }));
    }

    // Spawn a mob, so far just spawns a wolf, I will add command arguments to this later.
    private int summonMob(CommandSourceStack source) throws CommandSyntaxException {
        ServerLevel level = source.getLevel();

        Entity playerEntity = source.getEntity();

        if (playerEntity instanceof ServerPlayer player) {
            BlockPos playerPos = player.blockPosition();

            Wolf wolf = EntityUtil.spawnEntityAt(EntityType.WOLF, level, playerPos);
            wolf.setTame(true, true);
            wolf.setOwnerUUID(player.getUUID());
            wolf.setCustomName(Component.literal("Wolf"));
        }

        return Command.SINGLE_SUCCESS;

    }


}
