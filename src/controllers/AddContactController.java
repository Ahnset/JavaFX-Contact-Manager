package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.Contact;
import models.Manager;
import services.Validator;
import util.DialogFactory;
import util.Formatter;

/**
 * Created by Jared on 3/15/2017.
 */
public class AddContactController {

    private Manager manager;

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField addressField;
    @FXML
    private Button addBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private DatePicker dateOfBirthPicker;

    public AddContactController(Manager manager) {
        this.manager = manager;
    }

    @FXML
    void onAdd(ActionEvent event) {

        StringBuilder warnings = new StringBuilder();

        if (firstNameField.getText().isEmpty() && lastNameField.getText().isEmpty()) {
            warnings.append("A firstname or lastname must be given.\n");
        } else {
            if (!firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty() && firstNameField.getText().equals(lastNameField.getText())) {
                warnings.append("Firstname and lastname cannot be identical.\n");
                firstNameField.clear();
                lastNameField.clear();
            }
            if (!firstNameField.getText().isEmpty() && !Validator.nameIsValid(firstNameField.getText())) {
                warnings.append("Firstname can only contain letters.\n");
                firstNameField.clear();
            }
            if (!lastNameField.getText().isEmpty() && !Validator.nameIsValid(lastNameField.getText())) {
                warnings.append("Lastname can only contain letters.\n");
                lastNameField.clear();
            }
        }
        if (!phoneNumberField.getText().isEmpty() && !Validator.phoneNumberIsValid(phoneNumberField.getText())) {
            warnings.append("Phone number must be in ###-###-#### format and have no white space anywhere.\n");
            phoneNumberField.clear();
        }
        if (warnings.length() != 0) {
            DialogFactory.createDialog("Warning!", addBtn.getScene().getWindow(), null, warnings.toString(), Alert.AlertType.WARNING);
        } else {
            Contact contact = new Contact(Formatter.formatName(firstNameField.getText()), Formatter.formatName(lastNameField.getText()), phoneNumberField.getText(),
                    addressField.getText(), dateOfBirthPicker.getValue());

            if (manager.contactExists(contact)) {
                DialogFactory.createDialog("Contact already exists!", addBtn.getScene().getWindow(), null,
                        String.format("The contact %s already exists.", contact.getFullName()), Alert.AlertType.WARNING);
            } else {
                manager.addContact(contact);
                addBtn.getScene().getWindow().hide();
            }
        }
    }

    @FXML
    void onCancel(ActionEvent event) {
        cancelBtn.getScene().getWindow().hide();
    }
}
