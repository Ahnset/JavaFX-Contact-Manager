package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Jared on 3/16/2017.
 */
public class AboutController {

    @FXML
    private Button visitBtn;
    @FXML
    private Button closeBtn;

    @FXML
    void onVisit(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/MaliceGFS/Advanced-Contact-Manager").toURI());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void onClose(ActionEvent event) {
        closeBtn.getScene().getWindow().hide();
    }
}
