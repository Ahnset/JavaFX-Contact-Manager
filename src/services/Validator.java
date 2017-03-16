package services;

/**
 * Created by Jared on 3/16/2017.
 */
public class Validator {

    public static boolean nameIsValid(String name) {
        return name.matches("[a-zA-Z ]*");
    }

    public static boolean phoneNumberIsValid(String phoneNumber) {
        return phoneNumber.matches("\\d{3}[-.\\s]\\d{3}[-.\\s]\\d{4}");
    }
}
