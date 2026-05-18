package net.kelsoncraft.kcmod;


// TODO Try to use Tesseract for my config

import net.swedz.tesseract.neoforge.config.annotation.ConfigComment;
import net.swedz.tesseract.neoforge.config.annotation.ConfigKey;
import net.swedz.tesseract.neoforge.config.annotation.SubSection;
import org.checkerframework.common.value.qual.BoolVal;
import org.spongepowered.asm.util.perf.Profiler;

// https://github.com/Swedz/tesseract-neoforge/blob/1.21.1/docs/CONFIGS.md
public interface NewConfig {

    // Well this crashes in game.
    @ConfigKey("section")
    @SubSection
    Profiler.Section section();
//    Section section;

    interface Section {
        @ConfigKey("enableExtraCreativeCommands")
        @ConfigComment("Enable the custom /gmc, /creative, /gm c commands.")
        default boolean extraCreativeCommandsEnabled() {
            return false;
        }
    }







}
