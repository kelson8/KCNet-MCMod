package net.kelsoncraft.neoforgetest.commands.misc;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.kelsoncraft.neoforgetest.util.ChatColors;
import net.kelsoncraft.neoforgetest.util.EntityUtil;
import net.kelsoncraft.neoforgetest.util.MessageUtil;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.commands.synchronization.SuggestionProviders;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Wolf;

import static net.minecraft.commands.Commands.createValidationContext;

// Some code in this file came from here
// https://github.com/TheTurkeyDev/ChanceCubes/blob/dev_1.21.x/src/main/java/chanceCubes/rewards/defaultRewards/WolvesToCreepersReward.java

public class SummonMobCommand {

    // This works
    public SummonMobCommand(CommandDispatcher<CommandSourceStack> dispatcher) {


        // TODO Figure out how to make the teleport modifiers, such as '~50' on the Y makes you in the air.

        // TODO Test this, replicated from SummonCommand.java, and Commands.java for the build context.
        // This works! It builds a command list for the summon command.
        // Now, to figure out how to make a summon command that can spawn other mobs, instead of the hard-coded wolf values I have set.
        // This is a bit tricky to do, I may also need to get the position of the player.
        CommandBuildContext commandbuildcontext = createValidationContext(VanillaRegistries.createLookup());

        dispatcher.register(Commands.literal("summon_mob")
                // This here adds the auto complete for entities, although it isn't setup to spawn different ones just yet.
//                        .then(
//                Commands.argument("entity", ResourceArgument.resource(commandbuildcontext, Registries.ENTITY_TYPE)).suggests(SuggestionProviders.SUMMONABLE_ENTITIES))

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

            MessageUtil.sendColorMessage(source, "You have spawned a Wolf", ChatColors.AQUA);
        }

        return Command.SINGLE_SUCCESS;

    }


}
