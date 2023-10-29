package edu.ntnu.idatt1002.team20.controllers;

import edu.ntnu.idatt1002.team20.utils.FileHandler;
import edu.ntnu.idatt1002.team20.utils.SceneSwitcher;
import edu.ntnu.idatt1002.team20.models.user.CurrentUser;
import edu.ntnu.idatt1002.team20.models.user.User;

import java.net.URL;
import java.util.ResourceBundle;

import edu.ntnu.idatt1002.team20.models.user.UserRegister;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

/**
 * The controller for the login page of the application.
 * Handles user authentication and switching to other pages.
 * <p>
 * This class is responsible for handling the user's login information, such as their username and password.
 * It provides methods to check if the user's credentials are valid and to switch to other pages in the application.
 * <p>
 * This class provides the following features:
 * - Allows the user to toggle the visibility of the password field
 * - Validates the user's credentials when the sign-in button is clicked
 * - Displays error messages if the username or password fields are empty or if the credentials are invalid
 * - Switches to the main page or sign-up page when the corresponding buttons are clicked
 */
public class LogInPageController implements Initializable {

  private final UserRegister userRegister = new UserRegister();

  @FXML
  private PasswordField passHiddenField;
  @FXML
  private TextField passText;
  @FXML
  private CheckBox passToggle;
  @FXML
  private TextField usernameField;
  @FXML
  private Label messageField;

  /**
   * Controls the visibility of the Password field
   */
  @FXML
  public void toggleVisiblePassword() {
    // Check if toggle is selected
    if (passToggle.isSelected()) {
      // If selected, show the password in clear text
      passText.setText(passHiddenField.getText());
      passText.setVisible(true);
      passHiddenField.setVisible(false);
      return;
    }
    // Otherwise, hide the password
    passHiddenField.setText(passText.getText());
    passHiddenField.setVisible(true);
    passText.setVisible(false);
  }
  @FXML
  public void toMainPage () {
    SceneSwitcher.getInstance().switchScene("mainPage", "main-page");
  }

  /**
   * Event handler for the sign-in button.
   * <p>
   * This method is called when the user clicks the sign-in button on the login page.
   * It checks if the username and password are correct and logs in the user if they are.
   * If the username or password are incorrect or missing, the method displays an error message.
   * If the user does not exist, the method displays a message saying so.
   */
  public void onLogInButton() {
    // Get the username and password from the input fields
    String username = usernameField.getText();
    String password = passHiddenField.getText();

    // Check if the username exists in the file containing usernames and passwords
    if (checkIfUserExist(username)) {
      // Check if the username and password are correct
      if (checkUsernamePassword(username, password)) {
        // If correct, create a new User object and log in
        User currentUser = userRegister.getUserByUsername(username);
        CurrentUser.getInstance().login(currentUser);
        toMainPage();
      } else if (!checkUsernamePassword(username, password)) {
        // If username or password are incorrect, display an error message
        messageField.setVisible(true);
        messageField.setText("Wrong username or password.");
      }
    } else if (!checkIfUserExist(username) && !username.isBlank()) {
      // If the user does not exist, display a message saying so
      messageField.setVisible(true);
      messageField.setText("User does not exist. Please sign up.");
    } else if ((username.isBlank() || password.isBlank())){
      // If fields are empty, display an error message
      messageField.setVisible(true);
      messageField.setText("Please enter a username and password.");
    }
  }


  /**
   * Switches to the sign-up page when the 'Log in' button is clicked.
   */
  @FXML
  public void toSignUp () {
    SceneSwitcher.getInstance().switchScene("logInPage", "signUp-page");
  }

  /**
   * Checks if the given username and password match the stored values.
   *
   * @param username the username to check
   * @param password the password to check
   * @return true if the username and password are correct, false otherwise
   */
  public boolean checkUsernamePassword(String username, String password) {
    return userRegister.hasUser(username, password);
  }

  /**
   * Checks if the given username exists in the file containing usernames and passwords.
   * <p>
   * This method reads the file containing usernames and passwords and checks if the given username
   * exists in the file. If the username is found, the method returns true, otherwise it returns false.
   * @param username the username to check
   * @return true if the username exists in the file, false otherwise
   */
  public boolean checkIfUserExist(String username) {
    return userRegister.hasUsername(username);
  }


  /**
   * Initializes the controller class.
   * @param url the url
   * @param resourceBundle the resource bundle
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    // Add all registered users to the user register

    userRegister.addUsers(FileHandler.readUsers());

    // Hide the message field when either of the input fields are focused
    usernameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue) {
        messageField.setVisible(false);
      }
    });
    passHiddenField.focusedProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue) {
        messageField.setVisible(false);
      }
    });

    // Attempt to log in when the user presses the enter key while the password field is focused
    passHiddenField.setOnKeyPressed(key -> {
      if (key.getCode().equals(KeyCode.ENTER)) {
        onLogInButton();
      }
    });
    passText.setOnKeyPressed(key -> {
      if (key.getCode().equals(KeyCode.ENTER)) {
        onLogInButton();
      }
    });
  }
}