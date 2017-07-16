package util;

/**
 * A collection of various methods that perform useful operations on strings such as formatting.
 *
 * @author Jared
 */
public class StringUtil {

    /**
     * A method that formats a string based on common formatting conventions regarding a persons first or last name.
     *
     * @param name The name to be formatted
     * @return A string or null if the given string is empty
     */
    public String formatName(String name) {
        if (!name.isEmpty()) {
            name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase().trim();
            return name;
        }
        return null;
    }
}