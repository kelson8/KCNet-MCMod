package net.kelsoncraft.kcmod.util;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.kelsoncraft.kcmod.KCMod;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

// Credit to TheTurkeyDev on GitHub for some of this code
// https://github.com/TheTurkeyDev/ChanceCubes/blob/dev_1.21.x/src/main/java/chanceCubes/mcwrapper/EntityWrapper.java

public class EntityUtil {

    public static <T extends Entity> T spawnEntity(EntityType<T> type, Level level)
    {
        T ent = type.create(level);
        if(ent != null)
            level.addFreshEntity(ent);
        return ent;
    }

    public static <T extends Entity> T spawnEntityAt(EntityType<T> type, Level level, BlockPos pos)
    {
        return spawnEntityAt(type, level, pos.getX(), pos.getY(), pos.getZ());
    }

    public static <T extends Entity> T spawnEntityAt(EntityType<T> type, Level level, double x, double y, double z)
    {
        T ent = spawnEntity(type, level);
        if(ent != null)
            ent.moveTo(x, y, z, 0, 0);
        return ent;
    }

//    public static <T extends Entity> T spawnNamedEntityAt(EntityType<T> type, Level level, String name, BlockPos pos)
//    {
//        return spawnNamedEntityAt(type, level, name, pos.getX(), pos.getY(), pos.getZ());
//    }
//
//    public static <T extends Entity> T spawnNamedEntityAt(EntityType<T> type, Level level, String name, double x, double y, double z)
//    {
//        T ent = spawnEntityAt(type, level, x, y, z);
//        if(ent != null)
//            ent.setCustomName(ComponentWrapper.string(name));
//        return ent;
//    }

    /**
     * Give the player an Effect
     * @param source Player source
     * @param effect A MobEffect such as MobEffects.NIGHT_VISION
     * @param seconds The amount of time for the effect to last
     * @param amplifier The amplifier for the effect
     * @param showParticles If the effect shows particles or not.
     */
    public static int giveEffectCommand(
            CommandSourceStack source,
//            Collection<? extends Entity> targets,
            Holder<MobEffect> effect,
            @Nullable Integer seconds,
            int amplifier,
            boolean showParticles
    ) throws CommandSyntaxException {
        MobEffect mobeffect = effect.value();

        ServerPlayer player = source.getPlayer();
        if(player == null){
            KCMod.LOGGER.warn("Player is not valid.");
            return -1;
        }

        // TODO Refactor this a bit.
        int j;
        if (seconds != null) {
            if (mobeffect.isInstantenous()) {
                j = seconds;
            } else if (seconds == -1) {
                j = -1;
            } else {
                j = seconds * 20;
            }
        } else if (mobeffect.isInstantenous()) {
            j = 1;
        } else {
            j = 600;
        }

        if(player instanceof ServerPlayer) {
            MobEffectInstance mobEffectInstance = new MobEffectInstance(effect, j, amplifier, false, showParticles);
            player.addEffect(mobEffectInstance);
        }


        return Command.SINGLE_SUCCESS;
    }
}
