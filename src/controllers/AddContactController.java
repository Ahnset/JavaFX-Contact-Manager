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
import util.StringUtil;

/**
 * A controller object that handles the interaction within the AddContact Dialog and its corresponding contact object.
 *
 * @author Jared
 */
public class AddContactController {

    private final Manager manager;

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

        // Validate the entered first and last names.
        if (firstNameField.getText().isEmpty() && lastNameField.getText().isEmpty()) {
            warnings.append("A first or last name must be given.\n");
        } else {
            if (!firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty() && firstNameField.getText().equals(lastNameField.getText())) {
                warnings.append("The first and last name cannot be identical.\n");
                firstNameField.clear();
                lastNameField.clear();
            }
            if (!firstNameField.getText().isEmpty() && !Validator.isValidName(firstNameField.getText())) {
                warnings.append("Firstname can only contain letters.\n");
                firstNameField.clear();
            }
            if (!lastNameField.getText().isEmpty() && !Validator.isValidName(lastNameField.getText())) {
                warnings.append("Lastname can only contain letters.\n");
                lastNameField.clear();
            }
        }
        // Validate the entered phone number.
        if (!phoneNumberField.getText().isEmpty() && !Validator.isValidPhoneNumber(phoneNumberField.getText())) {
            warnings.append("The phone number must be in ###-###-#### format and have no white space anywhere.\n");
            phoneNumberField.clear();
        }
        if (warnings.length() != 0) {
            DialogFactory.displayAlertDialog("Warning!", addBtn.getScene().getWindow(), null, warnings.toString(), Alert.AlertType.WARNING);
        } else {
            Contact contact = new Contact(StringUtil.formatName(firstNameField.getText()), StringUtil.formatName(lastNameField.getText()), phoneNumberField.getText(),
                    addressField.getText(), dateOfBirthPicker.getValue());

            if (manager.contactExists(contact)) {
                DialogFactory.displayAlertDialog("Contact already exists!", addBtn.getScene().getWindow(), null,
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
