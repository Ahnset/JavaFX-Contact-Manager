package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import models.Contact;
import models.Manager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jared on 3/15/2017.
 */
public class AddContactController implements Initializable {

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
            if (!firstNameField.getText().isEmpty() && !firstNameField.getText().matches("[a-zA-Z]*")) {
                warnings.append("Firstname can only contain letters.\n");
                firstNameField.clear();
            }
            if (!lastNameField.getText().isEmpty() && !lastNameField.getText().matches("[a-zA-Z]*")) {
                warnings.append("Lastname can only contain letters.\n");
                lastNameField.clear();
            }
        }

        if (!phoneNumberField.getText().isEmpty() && !phoneNumberField.getText().matches("\\d{3}[-.\\s]\\d{3}[-.\\s]\\d{4}")) {
            warnings.append("Phone number must be in ###-###-#### format and only contain numbers and dashes.\n");
            phoneNumberField.clear();
        }
        if (warnings.length() != 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING, warnings.toString());
            alert.setTitle("Warning!");
            alert.setHeaderText(null);
            alert.initModality(Modality.WINDOW_MODAL);
            alert.initOwner(addBtn.getScene().getWindow());
            alert.showAndWait();
        } else {
            manager.addContact(new Contact(firstNameField.getText(), lastNameField.getText(), phoneNumberField.getText(), addressField.getText(), dateOfBirthPicker.getValue()));
            addBtn.getScene().getWindow().hide();
        }
    }

    @FXML
    void onCancel(ActionEvent event) {
        cancelBtn.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
