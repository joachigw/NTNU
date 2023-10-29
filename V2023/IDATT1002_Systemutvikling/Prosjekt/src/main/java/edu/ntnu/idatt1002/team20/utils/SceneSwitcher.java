package edu.ntnu.idatt1002.team20.utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


/**
 * Class for switching between scenes.
 * This class has methods for switching scenes, showing alerts and centering the stage on the screen.
 */
public class SceneSwitcher extends Application {
  private static SceneSwitcher instance;
  private Stage stage;


  /**
   * Private constructor to ensure that the SceneSwitcher is a singleton.
   * Cannot be instantiated outside of this class.
   */
  private SceneSwitcher() {
  }


  /**
   * Returns the instance of the SceneSwitcher.
   * @return the instance of the SceneSwitcher
   */
  public static SceneSwitcher getInstance() {
    // Ensures that only one instance of the SceneSwitcher has been
    if (instance == null) {
      instance = new SceneSwitcher();
    }
    return instance;
  }


  /**
   * Creates all the scenes and adds them to the sceneMap.
   * @param primaryStage the stage to show the scenes on
   */
  @Override
  public void start(Stage primaryStage) {
    stage = primaryStage;

    // Start the application on the main scene
    switchScene("logInPage", "logIn-page");
    stage.setResizable(false);
    stage.show();
    centerOnScreen(stage);
  }


  /**
   * Switches to the scene with the given name.
   * @param pageName the name of the page to switch to
   */
  public void switchScene(String pageFolder, String pageName) {

    String fxmlPath = "/edu/ntnu/idatt1002/team20/view/" + pageFolder + "/" + pageName + ".fxml";
    String cssPath = "/edu/ntnu/idatt1002/team20/view/" + pageFolder + "/" + pageName + ".css";

    try {
      FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(fxmlPath)));
      Parent root = loader.load();
      Scene scene;
      if (pageName.equals("logIn-page") || pageName.equals("signUp-page")) {
        scene = new Scene(root, 250, 350);
      } else {
        scene = new Scene(root, 900, 600);
      }
      scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(cssPath)).toExternalForm());
      stage.setScene(scene);

      Object controller = loader.getController();
      loader.setController(controller);

      centerOnScreen(stage);
    } catch (IOException e) {
      SceneSwitcher.showAlert("Could not switch scene.");
      throw new RuntimeException("Could not switch scene.");
    }
  }


  /**
   * Shows a smaller popup window with the given name.
   * @param pageFolder the folder the page is in
   * @param pageName the name of the page to show
   */
  public void showPopupWindow(String pageFolder, String pageName) {
    String fxmlPath = "/edu/ntnu/idatt1002/team20/view/" + pageFolder + "/" + pageName + ".fxml";

    Stage stage = new Stage();
    stage.setTitle("About");

    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
      Parent root = loader.load();
      Scene scene = new Scene(root, 600, 400);

      stage.setScene(scene);
      stage.show();

      centerOnScreen(stage);
    } catch (IOException e) {
      SceneSwitcher.showAlert("Could not switch scene.");
      throw new RuntimeException("Could not switch scene.");
    }
  }


  /**
   * Shows an alert with the given message.
   * @param message the message to show
   */
  public static void showAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }


  /**
   * Centers the stage on the screen.
   * @param stage the stage to center
   */
  public void centerOnScreen(Stage stage) {
    Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
    stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
    stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
  }


  /**
   * Main method.
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
}