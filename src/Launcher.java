import controllers.OverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Manager;

/**
 * Created by Jared on 3/15/2017.
 */
public class Launcher extends Application {

    @Override
    public void start(Stage mainStage) throws Exception {
        Manager manager = new Manager();
        OverviewController overviewController = new OverviewController(mainStage, manager);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Overview.fxml"));
        loader.setController(overviewController);
        mainStage.setScene(new Scene(loader.load()));
        mainStage.setTitle(String.format("%s's contacts", System.getProperty("user.name")));
        mainStage.setResizable(false);
        mainStage.show();
    }
}
