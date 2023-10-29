package edu.ntnu.idatt1002.team20;

import edu.ntnu.idatt1002.team20.utils.SceneSwitcher;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class for the application.
 */
public class MainApp extends Application {


    /**
     * Starts the application.
     * @param stage the stage to show the scenes on
     */
    @Override
    public void start(Stage stage) {
        SceneSwitcher sceneSwitcher = SceneSwitcher.getInstance();
        sceneSwitcher.start(stage);
    }


    /**
     * Main method.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
