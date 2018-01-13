package util;

/**
 * A collection of various static methods that perform useful operations on strings such as formatting.
 *
 * @author Jared
 */
public class StringUtil {

    private StringUtil() {
        throw new UnsupportedOperationException("Class is not instantiatable");
    }

    /**
     * A method that formats a string based on common formatting conventions regarding a persons first or last name.
     *
     * @param name The name to be formatted
     * @return A formatted string or an empty one if the given string is empty
     */
    public static String formatName(String name) {
        if (!name.isEmpty()) {
            name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase().trim();
            return name;
        }
        return "";
    }
}
