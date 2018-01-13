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
 * A controller object that handles the interaction within the EditContact Dialog and its corresponding contact object.
 *
 * @author Jared
 */
public class EditContactController {

    private final Manager manager;
    private final Contact contact;

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField addressField;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private DatePicker dateOfBirthPicker;

    public EditContactController(Manager manager, Contact contact) {
        this.manager = manager;
        this.contact = contact;
    }

    @FXML
    void onSave(ActionEvent event) {

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
            DialogFactory.displayAlertDialog("Warning!", saveBtn.getScene().getWindow(), null, warnings.toString(), Alert.AlertType.WARNING);
        } else {
            Contact tempContact = new Contact(StringUtil.formatName(firstNameField.getText()), StringUtil.formatName(lastNameField.getText()), phoneNumberField.getText(),
                    addressField.getText(), dateOfBirthPicker.getValue());

            if (manager.contactExists(tempContact)) {
                DialogFactory.displayAlertDialog("Contact already exists!", saveBtn.getScene().getWindow(), null,
                        String.format("A contact with the name %s already exists.", contact.getFullName()), Alert.AlertType.WARNING);
            } else {
                contact.setFirstName(StringUtil.formatName(firstNameField.getText()));
                contact.setLastName(StringUtil.formatName(lastNameField.getText()));
                contact.setPhoneNumber(phoneNumberField.getText());
                contact.setAddress(addressField.getText());
                contact.setDateOfBirth(dateOfBirthPicker.getValue());
                saveBtn.getScene().getWindow().hide();
            }
        }
    }

    @FXML
    void onCancel(ActionEvent event) {
        cancelBtn.getScene().getWindow().hide();
    }


    public void initialize() {
        firstNameField.setText(contact.getFirstName());
        lastNameField.setText(contact.getLastName());
        phoneNumberField.setText(contact.getPhoneNumber());
        addressField.setText(contact.getAddress());
        dateOfBirthPicker.setValue(contact.getDateOfBirth());
    }
}
