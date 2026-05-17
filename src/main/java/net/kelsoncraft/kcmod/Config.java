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

        // Flying toggles
//        public final ModConfigSpec.ConfigValue<Float> FLY_SPEED;
        public final ModConfigSpec.ConfigValue<Integer> FLY_SPEED;
        public final ModConfigSpec.ConfigValue<Boolean> FAST_FLY_SPEED_TOGGLE;
        // Tnt toggles
        public final ModConfigSpec.BooleanValue TNT_AUTO_EXPLODE;
        public final ModConfigSpec.ConfigValue<Integer> TNT_FUSE;

        public CommonConfig(ModConfigSpec.Builder builder) {
            builder.comment("General Mod Configuration")
                    .translation(getTranslationKey("commands.category"))
                    .push("commands"); // Create a category for commands

            //--------
            // Extra command toggles
            //--------

            ENABLE_CREATIVE_COMMAND = builder
                    .comment("Enable the custom /gmc, /creative, /gm c commands.")
                    .translation(getTranslationKey("commands.toggle_creative_command"))
                    .define("enableCreativeCommand", true);

            ENABLE_SURVIVAL_COMMAND = builder
                    .comment("Enable the custom /gms, /survival, /gm s commands.")
                    .translation(getTranslationKey("commands.toggle_survival_command"))
                    .define("enableSurvivalCommand", true);

            ENABLE_ADVENTURE_COMMAND = builder
                    .comment("Enable the custom /gma, /adventure, /gm a commands.")
                    .translation(getTranslationKey("commands.toggle_adventure_command"))
                    .define("enableAdventureCommand", true);

            ENABLE_SPECTATOR_COMMAND = builder
                    .comment("Enable the custom /gmsp, /spectator, /gm sp commands.")
                    .translation(getTranslationKey("commands.toggle_spectator_command"))
                    .define("enableSpectatorCommand", true);

            builder.pop(); // End the commands category

            //--------
            // Auto load config
            //--------

            builder.comment("Auto World Loader for Development")
                    .translation(getTranslationKey("auto_load_world.category"))
                    .push("auto_load_world");
//            builder.translation(getTranslationKey("auto_load_world.category"));

            ENABLE_AUTO_LOAD_WORLD = builder
                    .comment("Enable automatic loading of a specific world on game startup. For development purposes.")
                    .translation(getTranslationKey("auto_load_world.toggle"))
                    .define("enableAutoLoadWorld", false); // Default to false, so it's off by default

            AUTO_LOAD_WORLD_NAME = builder
                    .comment("The name of the world to automatically load. Must be an exact match (folder name).")
                    .translation(getTranslationKey("auto_load_world.name"))
                    .define("autoLoadWorldName", ""); // Default to empty string

            builder.pop(); // End auto_load_world category

            //--------
            // Misc category
            //--------

            builder.comment("Misc Options")
                    .translation(getTranslationKey("misc.category"))
                    .push("misc");

            // Fly speed and speed toggle
            FAST_FLY_SPEED_TOGGLE = builder
                    .comment("Toggle faster flying speed")
                    .translation(getTranslationKey("misc.toggle_fast_fly_speed"))
                    .define("fastFlyingSpeed", false);

            FLY_SPEED = builder
                    .comment("Change your flying speed")
                    .translation(getTranslationKey("misc.fly_speed"))
//                    .define("flyingSpeed", 1.0f);
                    .define("flyingSpeed", 1);


            // Tnt auto explode
            TNT_AUTO_EXPLODE = builder
                    .comment("Set TNT to automatically explode when placed")
                    .translation(getTranslationKey("misc.tnt_auto_explode"))
                    .worldRestart()
                    .define("tntAutoExplode", false);

            // TNT fuse timer
            TNT_FUSE = builder
                    .comment("Set the length of the TNT fuse in seconds..")
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
