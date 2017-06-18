package util;

import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Window;

/**
 * A collection of static methods that facilitate the easy construction of JavaFX dialogs with variable content.
 *
 * @author Jared
 */
public class DialogFactory {

    /**
     * Creates a dialog in JavaFX which receives varies attributes based on passed in parameters.
     *
     * @param title      The title of the dialog
     * @param owner      The parent window of the dialog
     * @param headerText The header text of the dialog
     * @param bodyText   The body text of the dialog
     * @param type       The type of dialog to be shown
     */
    public static void createDialog(String title, Window owner, String headerText, String bodyText, Alert.AlertType type) {
        Alert dialog = new Alert(type, bodyText);
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(owner);
        dialog.showAndWait();
    }
}
