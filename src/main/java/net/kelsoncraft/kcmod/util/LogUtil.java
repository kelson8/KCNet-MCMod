package net.kelsoncraft.kcmod.util;

import net.kelsoncraft.kcmod.KCMod;

/**
 * Basic Logging utility class for logging to the console with my custom text.
 * TODO Switch loggers to using this class.
 */

public class LogUtil {

    public static void LogInfo(String text) {
        KCMod.LOGGER.info(Messages.KCNetMain + "{}", text);
    }

    public static void logWarning(String text) {
        KCMod.LOGGER.warn(Messages.KCNetMain + "{}",text);
    }

    public static void logError(String text) {
        KCMod.LOGGER.error(Messages.KCNetMain + "{}",text);
    }
}
