package net.kelsoncraft.kcmod;

import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {

    // This should allow me to toggle the gamemode commands on/off.
    public static final ModConfigSpec COMMON_SPEC;
    public static final CommonConfig COMMON;

    static {
        // Build the common config spec
        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();
        COMMON = new CommonConfig(COMMON_BUILDER);
        COMMON_SPEC = COMMON_BUILDER.build();
    }

    public static class CommonConfig {
        // Gamemode commands
        public final ModConfigSpec.BooleanValue ENABLE_CREATIVE_COMMAND;
        public final ModConfigSpec.BooleanValue ENABLE_SURVIVAL_COMMAND;
        public final ModConfigSpec.BooleanValue ENABLE_ADVENTURE_COMMAND;
        public final ModConfigSpec.BooleanValue ENABLE_SPECTATOR_COMMAND;

        // Auto load
        // New config options for auto-loading world
        public final ModConfigSpec.BooleanValue ENABLE_AUTO_LOAD_WORLD;
        public final ModConfigSpec.ConfigValue<String> AUTO_LOAD_WORLD_NAME;

        // Misc options

        // Teleport options
        // Name of the dimension to teleport to for the /kc dimtp command
        public final ModConfigSpec.ConfigValue<String> DIMENSION_TELEPORT_NAME;

        // Flying toggles
//        public final ModConfigSpec.ConfigValue<Float> FLY_SPEED;
        public final ModConfigSpec.ConfigValue<Integer> FLY_SPEED;
        public final ModConfigSpec.ConfigValue<Boolean> FAST_FLY_SPEED_TOGGLE;
        public final ModConfigSpec.ConfigValue<Boolean> FLY_TOGGLE;
        // Tnt toggles
        public final ModConfigSpec.BooleanValue TNT_AUTO_EXPLODE;
        public final ModConfigSpec.ConfigValue<Integer> TNT_FUSE;

        public CommonConfig(ModConfigSpec.Builder builder) {
            builder.comment(getTranslationKey("general.config.tooltip"))
                    .translation(getTranslationKey("commands.category"))
                    .push("commands"); // Create a category for commands

            //--------
            // Extra command toggles
            //--------

            ENABLE_CREATIVE_COMMAND = builder
                    .comment(getTranslationKey("commands.toggle_creative_command.tooltip"))
                    .translation(getTranslationKey("commands.toggle_creative_command"))
                    .define("enableCreativeCommand", true);

            ENABLE_SURVIVAL_COMMAND = builder
                    .comment(getTranslationKey("commands.toggle_creative_command.tooltip"))
                    .translation(getTranslationKey("commands.toggle_survival_command"))
                    .define("enableSurvivalCommand", true);

            ENABLE_ADVENTURE_COMMAND = builder
                    .comment(getTranslationKey("commands.toggle_creative_command.tooltip"))
                    .translation(getTranslationKey("commands.toggle_adventure_command"))
                    .define("enableAdventureCommand", true);

            ENABLE_SPECTATOR_COMMAND = builder
                    .comment(getTranslationKey("commands.toggle_spectator_command.tooltip"))
                    .translation(getTranslationKey("commands.toggle_spectator_command"))
                    .define("enableSpectatorCommand", true);

            builder.pop(); // End the commands category

            //--------
            // Auto load config
            //--------

            builder.comment(getTranslationKey("auto_load_world.tooltip"))
                    .translation(getTranslationKey("auto_load_world.category"))
                    .push("auto_load_world");

            ENABLE_AUTO_LOAD_WORLD = builder
                    .comment(getTranslationKey("auto_load_world.toggle.tooltip"))
                    .translation(getTranslationKey("auto_load_world.toggle"))
                    .define("enableAutoLoadWorld", false); // Default to false, so it's off by default

            AUTO_LOAD_WORLD_NAME = builder
                    .comment(getTranslationKey("auto_load_world.name.tooltip"))
                    .translation(getTranslationKey("auto_load_world.name"))
                    .define("autoLoadWorldName", ""); // Default to empty string

            builder.pop(); // End auto_load_world category

            //--------
            // Misc category
            //--------

            // Why is the misc.tooltip and other main category tooltips not working in here?
            builder.comment(getTranslationKey("misc.tooltip"))
                    .translation(getTranslationKey("misc.category"))
                    .push("misc");

            DIMENSION_TELEPORT_NAME = builder
                    .comment(getTranslationKey("misc.dimension_teleport_name_tooltip"))
                    .translation(getTranslationKey("misc.dimension_teleport_name"))
                    .define("dimensionTeleportName", "");

            FLY_SPEED = builder
                    .comment(getTranslationKey("misc.fly_speed.tooltip"))
                    .translation(getTranslationKey("misc.fly_speed"))
//                    .define("flyingSpeed", 1.0f);
                    .define("flyingSpeed", 1);

            // Fly speed and speed toggle
            FAST_FLY_SPEED_TOGGLE = builder
                    .comment(getTranslationKey("misc.toggle_fast_fly_speed.tooltip"))
                    .translation(getTranslationKey("misc.toggle_fast_fly_speed"))
                    .define("fastFlyingSpeed", false);

            FLY_TOGGLE = builder
                    .comment(getTranslationKey("misc.fly_toggle.tooltip"))
                    .translation(getTranslationKey("misc.fly_toggle"))
//                    .define("flyingSpeed", 1.0f);
                    .define("flyToggle", false);



            // Tnt auto explode
            TNT_AUTO_EXPLODE = builder
                    .comment(getTranslationKey("misc.tnt_auto_explode.tooltip"))
                    .translation(getTranslationKey("misc.tnt_auto_explode"))
                    .worldRestart()
                    .define("tntAutoExplode", false);

            // TNT fuse timer
            TNT_FUSE = builder
                    .comment(getTranslationKey("misc.tnt_fuse_timer.tooltip"))
                    .translation(getTranslationKey("misc.tnt_fuse_timer"))
                    .worldRestart()
                    .define("tntFuseTimer", 4);

            builder.pop();
        }

        // Helper method to generate full translation keys
        private static String getTranslationKey(String key) {
            return "config." + KCMod.MOD_ID + "." + key;
        }
    }
}
