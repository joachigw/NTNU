package edu.ntnu.idatt1002.team20.controllers;

import edu.ntnu.idatt1002.team20.utils.FileHandler;
import edu.ntnu.idatt1002.team20.utils.SceneSwitcher;
import edu.ntnu.idatt1002.team20.models.debt.Debt;
import edu.ntnu.idatt1002.team20.models.user.CurrentUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the add debt scene.
 */
public class AddDebtController implements Initializable {

    private final String debtsFilename = CurrentUser.getInstance().getUser().getDebtsFilePath();

    @FXML TextField typeField;
    @FXML TextField totalField;
    @FXML TextField paidField;
    @FXML TextField interestField;


    /**
     * Submits the new debt to the text file and switches back to the debt page.
     */
    @FXML
    public void submit() {

        String debtType = typeField.getText();
        double totalAmount;
        double paidAmount;
        double interestRate;

        // Check if the debt type is valid
        if (debtType == null || debtType.isBlank() || debtType.isEmpty()) {
            SceneSwitcher.showAlert("The debt type cannot be null, empty or blank.");
            return;
        }

        // Check if the total amount is valid
        try {
            totalAmount = Double.parseDouble(totalField.getText());
        } catch (Exception e) {
            SceneSwitcher.showAlert("Please enter a valid total amount.");
            return;
        }
        if (totalAmount <= 0) {
            SceneSwitcher.showAlert("The total amount cannot be null, empty or blank.\n" +
                    "The total amount cannot be less than zero.");
            return;
        }

        // Check if the amount paid is valid
        try {
            paidAmount = Double.parseDouble(paidField.getText());
        } catch (Exception e) {
            SceneSwitcher.showAlert("Please enter a valid amount paid.");
            return;
        }
        if (paidAmount < 0) {
            SceneSwitcher.showAlert("The amount paid cannot be less than zero.");
            return;
        }
        if (paidAmount > totalAmount) {
            SceneSwitcher.showAlert("The amount paid cannot be greater than the total amount.");
            return;
        }

        // Check if the interest rate is valid
        try {
            interestRate = Double.parseDouble(interestField.getText());
        } catch (Exception e) {
            SceneSwitcher.showAlert("Please enter a valid interest rate.");
            return;
        }
        if (interestRate < 0) {
            SceneSwitcher.showAlert("The interest rate cannot be less than zero.");
            return;
        }

        // Write the new debt to the file
        FileHandler.writeAddNewDebt(debtsFilename, new Debt(debtType, totalAmount, paidAmount, interestRate));

        // Switch back to the debt page
        goBackButton();
    }


    /**
     * Switches the scene to the debt scene when the go back button is pressed.
     */
    public void goBackButton() {
        SceneSwitcher.getInstance().switchScene("debtPage", "debt-page");
    }


    /**
     * Clears the text fields when the scene is loaded.
     * @param url the url of the scene
     * @param resourceBundle the resource bundle of the scene
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeField.clear();
        totalField.clear();
        paidField.clear();
        interestField.clear();
    }
}
