package net.kelsoncraft.neoforgetest.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

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
}
