package net.kelsoncraft.neoforgetest.util;

import net.kelsoncraft.neoforgetest.NeoForgeTest;

/**
 * Basic Logging utility class for logging to the console with my custom text.
 * TODO Switch loggers to using this class.
 */

public class LogUtil {

    public static void LogInfo(String text) {
        NeoForgeTest.LOGGER.info(Messages.KCNetMain + "{}", text);
    }

    public static void logWarning(String text) {
        NeoForgeTest.LOGGER.warn(Messages.KCNetMain + "{}",text);
    }

    public static void logError(String text) {
        NeoForgeTest.LOGGER.error(Messages.KCNetMain + "{}",text);
    }
}
