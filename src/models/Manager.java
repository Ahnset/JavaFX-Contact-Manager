package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * An object that acts as a container for all of the user's contacts and provides functionality to manipulate those contacts contained inside.
 *
 * @author Jared
 */
public class Manager {

    // The list that contains all of the contact objects this particular manager object has.
    private ObservableList<Contact> contacts = FXCollections.observableArrayList();

    /**
     * Returns the contact list responsible for holding all of the user's contacts for this particular object.
     *
     * @return An ObservableList<Contact>
     */
    public ObservableList<Contact> getContacts() {
        return contacts;
    }

    /**
     * Returns a contact based on the given index from the manager object's contact list.
     *
     * @param index The index to retrieve a contact from
     * @return A contact object
     */
    public Contact getContact(int index) {
        return contacts.get(index);
    }

    /**
     * Adds a given contact to the manager object's contact list.
     *
     * @param contact The contact to add
     */
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    /**
     * Removes a contact from the manager object's contact list based on a given index.
     *
     * @param index The index to remove the contact from.
     */
    public void removeContact(int index) {
        contacts.remove(index);
    }

    /**
     * Checks if a given contact object already exists inside of the manager object's contact list.
     *
     * @param contact The contact to check
     * @return True or false based on whether or not a given contact already exists inside of the contact list.
     */
    public boolean contactExists(Contact contact) {
        for (Contact c : contacts) {
            if (contact.getFullName().equals(c.getFullName())) {
                return true;
            }
        }
        return false;
    }
}
