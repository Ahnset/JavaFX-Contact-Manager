package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Contact;
import models.Manager;
import util.DialogFactory;

import java.awt.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * A controller object that handles the interaction within the overview window of the application.
 *
 * @author Jared
 */
public class OverviewController implements Initializable {

    private Stage mainStage;
    private Manager manager;
    private DialogFactory dialogFactory = new DialogFactory();

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
    private MenuItem editCMI;
    @FXML
    private MenuItem removeCMI;
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
        dialogFactory.displayAboutDialog(mainStage);
    }

    @FXML
    void onAdd(ActionEvent event) {
        dialogFactory.displayAddDialog(mainStage, manager);
    }

    @FXML
    void onEdit(ActionEvent event) {
        if (contactsTable.getSelectionModel().getSelectedItem() != null) {
            dialogFactory.displayEditDialog(mainStage, manager, contactsTable.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void onRemove(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, String.format("Are you sure you want to remove %s?",
                contactsTable.getSelectionModel().getSelectedItem().getFullName()), ButtonType.YES, ButtonType.NO);
        alert.setTitle("Are you sure?");
        alert.setHeaderText(null);
        alert.initModality(Modality.WINDOW_MODAL);
        alert.initOwner(mainStage);
        Toolkit.getDefaultToolkit().beep();
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
        contactsTable.getColumns().forEach(contactTableColumn -> contactTableColumn.setStyle("-fx-alignment: CENTER;"));
        createListeners();
    }

    private void createListeners() {
        contactsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (contactsTable.getSelectionModel().getSelectedItem() != null) {
                editBtn.setDisable(false);
                removeBtn.setDisable(false);
                editCMI.setVisible(true);
                removeCMI.setVisible(true);
            } else {
                editBtn.setDisable(true);
                removeBtn.setDisable(true);
                editCMI.setVisible(false);
                removeCMI.setVisible(false);
            }
        });

        // Adds the ability to edit a contact by double clicking on them in the contacts table.
        contactsTable.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2 && contactsTable.getSelectionModel().getSelectedItem() != null) {
                dialogFactory.displayEditDialog(mainStage, manager, contactsTable.getSelectionModel().getSelectedItem());
            }
        });
    }
}
