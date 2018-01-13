import controllers.OverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Manager;
import util.StageHolder;

/**
 * Handles the initialization and launch of the application.
 *
 * @author Jared
 */
public class Launcher extends Application {

    private final Manager manager = new Manager();

    @Override
    public void start(Stage mainStage) throws Exception {
        StageHolder.setStage(mainStage);
        OverviewController overviewController = new OverviewController(manager);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Overview.fxml"));
        loader.setController(overviewController);
        mainStage.setScene(new Scene(loader.load()));
        mainStage.setTitle(String.format("JavaFX Contact Manager - %s's contacts", System.getProperty("user.name")));
        mainStage.setResizable(false);
        mainStage.show();
    }
}
