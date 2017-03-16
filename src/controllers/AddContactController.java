package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Manager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jared on 3/15/2017.
 */
public class AddContactController implements Initializable {

    private Stage mainStage;
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

    @FXML
    void onAdd(ActionEvent event) {

        StringBuilder warnings = new StringBuilder();

        if (!phoneNumberField.getText().matches("\\d{3}[-.\\s]\\d{3}[-.\\s]\\d{4}")) {
            warnings.append("Phone number must be in the proper format and only in numbers.\n");
        }





    }

    @FXML
    void onCancel(ActionEvent event) {

    }

    public AddContactController(Stage mainStage, Manager manager) {
        this.mainStage = mainStage;
        this.manager = manager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
