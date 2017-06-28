package util;

import controllers.AboutController;
import controllers.AddContactController;
import controllers.EditContactController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Contact;
import models.Manager;

import java.awt.*;
import java.io.IOException;

/**
 * A collection of methods that facilitate the easy construction of JavaFX dialogs with variable content.
 *
 * @author Jared
 */
public class DialogFactory {

    /**
     * Creates an alert dialog in which receives varies attributes based on passed in parameters.
     *
     * @param title      The title of the dialog
     * @param parentWin  The parent window of the dialog
     * @param headerText The header text of the dialog
     * @param bodyText   The body text of the dialog
     * @param type       The type of dialog to be shown
     */
    public void displayAlertDialog(String title, Window parentWin, String headerText, String bodyText, Alert.AlertType type) {
        Alert dialog = new Alert(type, bodyText);
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(parentWin);
        if (type.equals(Alert.AlertType.ERROR) || type.equals(Alert.AlertType.WARNING)) {
            Toolkit.getDefaultToolkit().beep();
        }
        dialog.showAndWait();
    }

    /**
     * Display a dialog containing info about the application.
     *
     * @param parentWin The parent window of the dialog
     */
    public void displayAboutDialog(Stage parentWin) {
        Stage aboutDialog = new Stage();
        AboutController aboutController = new AboutController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/About.fxml"));
        loader.setController(aboutController);
        try {
            aboutDialog.setScene(new Scene(loader.load()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        aboutDialog.setTitle("Application Info");
        aboutDialog.initOwner(parentWin);
        aboutDialog.initModality(Modality.WINDOW_MODAL);
        aboutDialog.setResizable(false);
        aboutDialog.showAndWait();
    }

    /**
     * Displays a dialog allowing the user to add a new contact to the application.
     *
     * @param parentWin The parent window of the dialog
     * @param manager   The manager object that this dialog will use when adding a new contact
     */
    public void displayAddDialog(Stage parentWin, Manager manager) {
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
        addDialog.initOwner(parentWin);
        addDialog.initModality(Modality.WINDOW_MODAL);
        addDialog.showAndWait();
    }

    /**
     * Displays an edit dialog allowing for the editing of that particular contacts info based on the passed in contact.
     *
     * @param parentWin The parent window of the dialog
     * @param manager   The manager object that this dialog will use when changing a contacts info
     * @param contact   The contact object to be edited
     */
    public void displayEditDialog(Stage parentWin, Manager manager, Contact contact) {
        Stage editDialog = new Stage();
        EditContactController editContactController = new EditContactController(manager, contact);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditContact.fxml"));
        loader.setController(editContactController);
        try {
            editDialog.setScene(new Scene(loader.load()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        editDialog.setTitle("Edit contact");
        editDialog.initOwner(parentWin);
        editDialog.initModality(Modality.WINDOW_MODAL);
        editDialog.showAndWait();
    }
}
