package net.kelsoncraft.kcmod.util;

import net.kelsoncraft.kcmod.KCMod;

/**
 * Basic Logging utility class for logging to the console with my custom text.
 * TODO Switch loggers to using this class.
 * TODO Create a new log file for my mod
 * <a href="https://stackoverflow.com/questions/15758685/how-to-write-logs-in-text-file-when-using-java-util-logging-logger">Write to logs in text file with Java Logger</a>

 TODO Look into this
 * <a href="https://duckduckgo.com/?t=ffab&q=log+system.out+to+a+file+java&ia=web">...</a>
 * <a href="https://codingtechroom.com/question/redirect-system-out-to-logger-java">...</a>
 */

public class LogUtil {

    /**
     * Logs an informational message with the mod's prefix.
     * @param text The text to display.
     */
    public static void logInfo(String text) {
        KCMod.LOGGER.info(Messages.KCNetMain + "{}", text);
    }


    /**
     * Logs a warning message with the mod's prefix.
     * @param text The text to display.
     */
    public static void logWarning(String text) {
        KCMod.LOGGER.warn(Messages.KCNetMain + "{}",text);
    }


    /**
     * Logs an error message with the mod's prefix.
     * @param text The text to display.
     */
    public static void logError(String text) {
        KCMod.LOGGER.error(Messages.KCNetMain + "{}",text);
    }
}
