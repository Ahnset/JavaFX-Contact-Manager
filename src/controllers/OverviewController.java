package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Contact;
import models.Manager;

import java.io.IOException;
import java.net.URL;
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
        AddContactController addContactController = new AddContactController(mainStage, manager);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddContact.fxml"));
        loader.setController(addContactController);
        try {
            addDialog.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addDialog.setTitle("Add contact");
        addDialog.initOwner(mainStage);
        addDialog.initModality(Modality.WINDOW_MODAL);
        addDialog.showAndWait();
    }

    @FXML
    void onEdit(ActionEvent event) {

    }

    @FXML
    void onRemove(ActionEvent event) {
        manager.removeContact(contactsTable.getSelectionModel().getSelectedIndex());
    }

    @FXML
    void onSettings(ActionEvent event) {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("firstname"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("lastname"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("phonenumber"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("address"));
        ageCol.setCellValueFactory(new PropertyValueFactory<Contact, Integer>("age"));
        dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<Contact, Object>("dateofbirth"));
        contactsTable.setItems(manager.getContacts());
    }

    private void createListeners() {
        contactsTable.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (contactsTable.getSelectionModel().getSelectedItem() != null) {
                editBtn.setDisable(false);
                removeBtn.setDisable(true);
            }
            else {
                editBtn.setDisable(true);
                removeBtn.setDisable(true);
            }
        });
    }
}
