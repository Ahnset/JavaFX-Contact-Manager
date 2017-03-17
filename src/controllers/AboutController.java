package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Jared on 3/16/2017.
 */
public class AboutController {

    @FXML
    private Hyperlink ghLink;

    @FXML
    void onGhLink(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/MaliceGFS/Advanced-Contact-Manager").toURI());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
}
