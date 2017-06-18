package util;

/**
 * A collection of various static methods that perform formatting on various types of data such as the names of people.
 *
 * @author Jared
 */
public class Formatter {

    /**
     * A method that formats a string based on common formatting conventions regarding a persons first or last name.
     *
     * @param name The name to be formatted
     * @return A string
     */
    public static String formatName(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase().trim();
        return name;
    }
}
