package net.kelsoncraft.kcmod.util;

public class MiscUtil {

    // Misc utilities for anything Java related, these should work on any Java application.

    // https://dev.to/realedwintorres/truncating-decimals-manually-in-java-50di

    /**
     * Truncate a set amount of numbers from a double, currently this can only keep up to 2 decimal places, such as 22.22
     * @param numberToTruncate The number to get rid of a lot of decimal values from, mostly useful for Vectors.
     * @param amount - This is an incomplete value, I will fix this later.
     */
//    public void truncateNumbers(float numberToTruncate, int amount) {
    public double truncateNumbers(double numberToTruncate) {
        return (int) (numberToTruncate * 100) / 100.0;
    }

}
