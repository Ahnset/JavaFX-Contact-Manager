package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Contact;
import models.Manager;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Jared on 3/15/2017.
 */
public class OverviewController implements Initializable {

    private Stage mainStage;
    private Manager manager;

    @FXML
    private TableView<Contact> contactsTable;
    @FXML
    private TableColumn<Contact, String> firstNameCol;
    @FXML
    private TableColumn<Contact, String> lastNameCol;
    @FXML
    private TableColumn<Contact, String> phoneNumberCol;
    @FXML
    private TableColumn<Contact, String> addressCol;
    @FXML
    private TableColumn<Contact, Integer> ageCol;
    @FXML
    private TableColumn<Contact, Object> dateOfBirthCol;
    @FXML
    private MenuItem addCMI;
    @FXML
    private Button settingsBtn;
    @FXML
    private Button aboutBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button removeBtn;

    public OverviewController(Stage mainStage, Manager manager) {
        this.mainStage = mainStage;
        this.manager = manager;
    }

    @FXML
    void onAbout(ActionEvent event) {

    }

    @FXML
    void onAdd(ActionEvent event) {
        Stage addDialog = new Stage();
        AddContactController addContactController = new AddContactController(manager);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddContact.fxml"));
        loader.setController(addContactController);
        try {
            addDialog.setScene(new Scene(loader.load()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        addDialog.setTitle("Add contact");
        addDialog.initOwner(mainStage);
        addDialog.initModality(Modality.WINDOW_MODAL);
        addDialog.showAndWait();
    }

    @FXML
    void onEdit(ActionEvent event) {
        Stage editDialog = new Stage();
        EditContactController editContactController = new EditContactController(contactsTable.getSelectionModel().getSelectedItem());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditContact.fxml"));
        loader.setController(editContactController);
        try {
            editDialog.setScene(new Scene(loader.load()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        editDialog.setTitle("Edit contact");
        editDialog.initOwner(mainStage);
        editDialog.initModality(Modality.WINDOW_MODAL);
        editDialog.showAndWait();
    }

    @FXML
    void onRemove(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, String.format("Are you sure you want to remove %s?",
                contactsTable.getSelectionModel().getSelectedItem().getFullName()), ButtonType.YES, ButtonType.NO);
        alert.setTitle("Are you sure?");
        alert.setHeaderText(null);
        alert.initModality(Modality.WINDOW_MODAL);
        alert.initOwner(mainStage);
        Optional<ButtonType> choice = alert.showAndWait();
        if (choice.get() == ButtonType.YES) {
            manager.removeContact(contactsTable.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    void onSettings(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("lastName"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("phoneNumber"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("address"));
        ageCol.setCellValueFactory(new PropertyValueFactory<Contact, Integer>("age"));
        dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<Contact, Object>("dateOfBirth"));
        contactsTable.setItems(manager.getContacts());
        createListeners();
    }

    private void createListeners() {
        contactsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (contactsTable.getSelectionModel().getSelectedItem() != null) {
                editBtn.setDisable(false);
                removeBtn.setDisable(false);
            } else {
                editBtn.setDisable(true);
                removeBtn.setDisable(true);
            }
        });
    }
}
