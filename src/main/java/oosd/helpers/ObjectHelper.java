package oosd.helpers;

/**
 * GRASP: pure fabrication
 * Static helper methods to manage lists.
 */
public class ObjectHelper {
    /**
     * Check if the given object is null.
     *
     * @param object used to check
     * @param <T>    generic type
     * @return boolean
     */
    public static <T> boolean isNull(T object) {
        return object == null;
    }

    /**
     * Check if the given object is not null.
     *
     * @param object used to check
     * @param <T>    generic type
     * @return boolean
     */
    public static <T> boolean isNotNull(T object) {
        return object != null;
    }

    /**
     * Check if the given object is not null.
     *
     * @param object used to check
     * @param <T>    generic type
     * @return boolean
     */
    public static <T> boolean exists(T object) {
        return isNotNull(object);
    }
}
