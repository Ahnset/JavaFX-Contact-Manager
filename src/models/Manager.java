package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Jared on 3/15/2017.
 */
public class Manager {

    private ObservableList<Contact> contacts = FXCollections.observableArrayList();

    public ObservableList<Contact> getContacts() {
        return contacts;
    }

    public Contact getContact(int index) {
        return contacts.get(index);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(int index) {
        contacts.remove(index);
    }

    public boolean contactExists(Contact contact) {
        for (Contact c : contacts) {
            if (contact.getFullName().equals(c.getFullName())) {
                return true;
            }
        }
        return false;
    }
}
