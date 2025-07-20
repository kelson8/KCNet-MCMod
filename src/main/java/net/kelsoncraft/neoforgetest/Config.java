package net.kelsoncraft.neoforgetest;

import java.util.List;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
        public final ModConfigSpec.BooleanValue ENABLE_CREATIVE_COMMAND;
        public final ModConfigSpec.BooleanValue ENABLE_SURVIVAL_COMMAND;
        public final ModConfigSpec.BooleanValue ENABLE_ADVENTURE_COMMAND;
        public final ModConfigSpec.BooleanValue ENABLE_SPECTATOR_COMMAND;

        public CommonConfig(ModConfigSpec.Builder builder) {
            builder.comment("General Mod Configuration")
                    .translation(getTranslationKey("commands.category"))
                    .push("commands"); // Create a category for commands


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
        }

        // Helper method to generate full translation keys
        private static String getTranslationKey(String key) {
            return "config." + NeoForgeTest.MOD_ID + "." + key;
        }
    }
}
