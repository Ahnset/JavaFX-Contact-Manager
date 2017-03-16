package util;

import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Window;

/**
 * Created by Jared on 3/16/2017.
 */
public class DialogFactory {

    public static void createDialog(String title, Window owner, String headerText, String bodyText, Alert.AlertType type) {
        Alert dialog = new Alert(type, bodyText);
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(owner);
        dialog.showAndWait();
    }
}
