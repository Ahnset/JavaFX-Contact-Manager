package services;

/**
 * A collection of static methods that validate passed in data based on a set of rules.
 *
 * @author Jared
 */
public class Validator {

    private Validator() {
        throw new UnsupportedOperationException("Class is not instantiatable");
    }

    /**
     * A method to check if a string represents a valid name for a person.
     *
     * @param name The name to be checked
     * @return True or false based on whether or not the string given represents a valid name.
     */
    public static boolean isValidName(String name) {
        return name.matches("[a-zA-Z ]*");
    }

    /**
     * A method to check if a given string represents a valid phone number.
     *
     * @param phoneNumber The phone number to be checked.
     * @return True or false based on whether or not it is a valid phone number.
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{3}[-.\\s]\\d{3}[-.\\s]\\d{4}");
    }
}
