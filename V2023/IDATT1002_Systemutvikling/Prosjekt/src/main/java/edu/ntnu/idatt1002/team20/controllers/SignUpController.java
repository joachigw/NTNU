package edu.ntnu.idatt1002.team20.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import edu.ntnu.idatt1002.team20.utils.FileHandler;
import edu.ntnu.idatt1002.team20.utils.HashPassword;
import edu.ntnu.idatt1002.team20.utils.SceneSwitcher;
import edu.ntnu.idatt1002.team20.models.user.UserRegister;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * The controller for the sign-up page of the application.
 * Handles user creation and switching to the login page.
 *
 * This class is responsible for handling the user's sign-up information, such as their username and password.
 * It provides methods to write the user's information to a file and to switch to the login page in the application.
 */
public class SignUpController implements Initializable {

  private final UserRegister userRegister = new UserRegister();

  // Username input field
  @FXML private TextField usernameField;
  // Password input fields and visibility toggle
  @FXML private PasswordField passHiddenField;
  @FXML private TextField passText;
  @FXML private CheckBox passToggle;


  /**
   * Controls the visibility of the Password field.
   *
   * This method is called when the user clicks on the password visibility toggle.
   * If the toggle is selected, the password is shown in clear text.
   * Otherwise, the password is hidden.
   */
  @FXML
  public void toggleVisiblePassword() {
    if (passToggle.isSelected()) {
      passText.setText(passHiddenField.getText());
      passText.setVisible(true);
      passHiddenField.setVisible(false);
      return;
    }
    passHiddenField.setText(passText.getText());
    passHiddenField.setVisible(true);
    passText.setVisible(false);
  }


  /**
   * Writes the user's sign-up information to a file and switches to the login page.
   *
   * This method is called when the user clicks on the create account button.
   * It gets the user's username and password from the input fields,
   * hashes the password, writes them to a file, and switches to the login page.
   *
   */
  @FXML
  public void createAccount() {
    String username = usernameField.getText();
    String password = passHiddenField.getText();
    String expensesFile = "src/main/resources/edu/ntnu/idatt1002/team20/data/userData/" + username + "/expenses.txt";
    String incomesFile = "src/main/resources/edu/ntnu/idatt1002/team20/data/userData/" + username + "/incomes.txt";
    String debtsFile = "src/main/resources/edu/ntnu/idatt1002/team20/data/userData/" + username + "/debts.txt";
    String goalsFile = "src/main/resources/edu/ntnu/idatt1002/team20/data/userData/" + username + "/goals.txt";
    String billsFile = "src/main/resources/edu/ntnu/idatt1002/team20/data/userData/" + username + "/bills.txt";
    String upcomingBillsFile = "src/main/resources/edu/ntnu/idatt1002/team20/data/userData/" + username + "/upcomingBills.txt";

    if (username.isEmpty() || username.isBlank()) {
      SceneSwitcher.showAlert("Please enter a valid username.");
      return;
    }
    if(password.isEmpty() || password.isBlank()) {
      SceneSwitcher.showAlert("Please enter a valid password.");
      return;
    }
    if(userRegister.hasUsername(username)) {
      SceneSwitcher.showAlert("Username already exists.");
      return;
    }

    try {
      File file = new File("src/main/resources/edu/ntnu/idatt1002/team20/data/users.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file, true));

      String hashedPassword = HashPassword.hash(password);

      out.write(username);
      out.newLine();
      out.write(hashedPassword);
      out.newLine();
      out.write(expensesFile);
      out.newLine();
      out.write(incomesFile);
      out.newLine();
      out.write(debtsFile);
      out.newLine();
      out.write(goalsFile);
      out.newLine();
      out.write(billsFile);
      out.newLine();
      out.write(upcomingBillsFile);
      out.newLine();
      out.write("\n");
      out.close();
      switchToLogIn();
    } catch (IOException e) {
      e.printStackTrace();
    }

    Path userDirectory = Paths.get("src/main/resources/edu/ntnu/idatt1002/team20/data/userData/" + username);

    try {
      Files.createDirectories(userDirectory);
      Files.createFile(Paths.get(userDirectory + "/expenses.txt"));
      Files.createFile(Paths.get(userDirectory + "/incomes.txt"));
      Files.createFile(Paths.get(userDirectory + "/debts.txt"));
      Files.createFile(Paths.get(userDirectory + "/goals.txt"));
      Files.createFile(Paths.get(userDirectory + "/bills.txt"));
      Files.createFile(Paths.get(userDirectory + "/upcomingBills.txt"));

    } catch (IOException e) {
      SceneSwitcher.showAlert("Could not create files or directories.");
      e.printStackTrace();
      throw new RuntimeException("Could not create files or directories.");
    }

  }


  /**
   * Switches scene to the login screen.
   */
  @FXML
  void switchToLogIn() {
    SceneSwitcher.getInstance().switchScene("logInPage", "logIn-page");
  }

  /**
   * Adds users to the user register
   *
   * @param url the location used to resolve relative paths for the root object, or null if the location is not known.
   * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    userRegister.addUsers(FileHandler.readUsers());
  }
}

