package oosd.helpers;

/**
 * GRASP: pure fabrication
 * Static helper methods to deal with numeric calculations.
 */
public class NumberHelper {
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static boolean isOdd(int number) {
        return number % 2 != 0;
    }
}
