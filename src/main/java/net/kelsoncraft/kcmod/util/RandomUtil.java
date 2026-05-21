package net.kelsoncraft.kcmod.util;

import java.util.Random;

public class RandomUtil {

    /**
     * Generate a random number
     * TODO Make this take a minimum and maximum value later.
     * @param maxNumber The highest random number to use
     */
    public int generateRandomNumber(int maxNumber) {
        // Creating the instance of Random class
        Random random = new Random();

        // Generate random integers in range speicfied above
        return random.nextInt(maxNumber);
    }

}
