package util;

/**
 * Created by Jared on 3/15/2017.
 */
public class Formatter {

    public static String formatName(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase().trim();
        return name;
    }
}
