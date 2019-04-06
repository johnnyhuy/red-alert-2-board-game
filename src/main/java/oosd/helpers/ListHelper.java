package oosd.helpers;

import java.util.List;

/**
 * GRASP: pure fabrication
 * Static helper methods to manage lists.
 */
public class ListHelper {
    /**
     * Check if the given list is empty.
     *
     * @param list used to check
     * @param <T>  generic type
     * @return boolean
     */
    public static <T> boolean isEmpty(List<T> list) {
        return list.size() == 0;
    }

    /**
     * Check if the given list is not empty.
     *
     * @param list used to check
     * @param <T>  generic type
     * @return boolean
     */
    public static <T> boolean isNotEmpty(List<T> list) {
        return list.size() > 0;
    }
}
