package net.kelsoncraft.kcmod.util;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.Vec3i;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.phys.Vec3;

public class CommandUtil {

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
