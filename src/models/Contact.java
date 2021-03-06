package models;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

/**
 * An object representing a contact with basic info such as a name and a phone number and methods to interact with this data.
 *
 * @author Jared
 */
public class Contact {

    private SimpleStringProperty firstName, lastName;
    private SimpleStringProperty phoneNumber;
    private SimpleStringProperty address;
    private SimpleObjectProperty<LocalDate> dateOfBirth;

    /**
     * Creates a new contact object with the given attributes based on passed in parameters.
     *
     * @param firstName   The firstname of the contact
     * @param lastName    The lastname of the contact
     * @param phoneNumber The phone number of the contact
     * @param address     The address of the contact
     * @param dateOfBirth The date of birth of the contact
     */
    public Contact(String firstName, String lastName, String phoneNumber, String address, LocalDate dateOfBirth) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.address = new SimpleStringProperty(address);
        this.dateOfBirth = new SimpleObjectProperty<>(dateOfBirth);
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public SimpleObjectProperty<LocalDate> dateOfBirthProperty() {
        return dateOfBirth;
    }

    public String getFullName() {
        return firstName.get() + " " + lastName.get();
    }
}
