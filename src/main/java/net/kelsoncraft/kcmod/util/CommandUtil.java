package net.kelsoncraft.kcmod.util;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.Vec3i;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.phys.Vec3;

public class CommandUtil {

    //------
    // Register commands
    //------
    /**
     * Registers a simple literal command that doesn't require permissions
     *
     * @param dispatcher The CommandDispatcher.
     * @param commandName The name of the command (e.g., "gmc").
     * @param executor The method reference for the command's execution logic.
     */
    public static void registerCommand(CommandDispatcher<CommandSourceStack> dispatcher, String commandName, Command<CommandSourceStack> executor) {
        dispatcher.register(Commands.literal(commandName)
                .executes(executor)
        );
    }


    /**
     * Registers a simple literal command that requires permission level 2 (OP/Gamemaster).
     *
     * @param dispatcher The CommandDispatcher.
     * @param commandName The name of the command (e.g., "gmc").
     * @param executor The method reference for the command's execution logic.
     */
    public static void registerCommandOp(CommandDispatcher<CommandSourceStack> dispatcher, String commandName, Command<CommandSourceStack> executor) {
        dispatcher.register(Commands.literal(commandName)
                .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .executes(executor)
        );
    }

    /**
     * Registers a literal command with a single literal subcommand that requires permission level 2 (OP/Gamemaster).
     * Useful for commands like /gm c.
     *
     * @param dispatcher The CommandDispatcher.
     * @param rootCommandName The name of the root command (e.g., "gm").
     * @param subCommandName The name of the subcommand (e.g., "c").
     * @param executor The method reference for the command's execution logic.
     */
    public static void registerCommandWithArg(CommandDispatcher<CommandSourceStack> dispatcher, String rootCommandName, String subCommandName, Command<CommandSourceStack> executor) { // Changed executor type
        dispatcher.register(Commands.literal(rootCommandName)
                .then(Commands.literal(subCommandName)
                        .requires(sourceStack -> sourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS)) // Requires OP
                        .executes(executor) // Directly pass the executor
                )
        );
    }


    //------
    // Execute commands
    //------
    // Credit to TheTurkeyDev on GitHub for some of this code
    // https://github.com/TheTurkeyDev/ChanceCubes/blob/dev_1.21.x/src/main/java/chanceCubes/util/RewardsUtil.java#L412-L426

    // I may use this to execute commands such as spawning mobs.

    public static void executeCommand(ServerLevel level, Player player, Vec3i pos, String command)
    {
        CommandUtil.executeCommand(level, player, new Vec3(pos.getX(), pos.getY(), pos.getZ()), command);
    }

    public static void executeCommand(ServerLevel level, Player player, Vec3 pos, String command)
    {
        MinecraftServer server = level.getServer();
        boolean rule = level.getGameRules().getBoolean(GameRules.RULE_COMMANDBLOCKOUTPUT);
        level.getGameRules().getRule(GameRules.RULE_COMMANDBLOCKOUTPUT).set(false, server);
        CommandSourceStack cs = new CommandSourceStack(player, pos, player.getRotationVector(), level, 2, player.getName().getString(), player.getDisplayName(), server, player);
        cs = cs.withSuppressedOutput();
        server.getCommands().performPrefixedCommand(cs, command);
        level.getGameRules().getRule(GameRules.RULE_COMMANDBLOCKOUTPUT).set(rule, server);
    }


}
