package oosd.helpers;

/**
 * GRASP: pure fabrication
 * Static helper methods to deal with numeric calculations.
 */
public class NumberHelper {
    /**
     * Check if the number is even.
     *
     * @param number used to check is even
     * @return boolean
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    /**
     * Check if the number is odd.
     *
     * @param number used to check is odd
     * @return boolean
     */
    public static boolean isOdd(int number) {
        return number % 2 != 0;
    }

    /**
     * Convert a long an int.
     *
     * @param number to convert
     * @return converted long to int
     */
    public static int toInt(Long number) {
        return number.intValue();
    }
}
