package edu.ntnu.idatt1002.team20.controllers;

import edu.ntnu.idatt1002.team20.utils.FileHandler;
import edu.ntnu.idatt1002.team20.utils.SceneSwitcher;
import edu.ntnu.idatt1002.team20.models.income.Income;
import edu.ntnu.idatt1002.team20.models.user.CurrentUser;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Controller for the add-income-page.
 */
public class AddIncomeController {
    @FXML public TextField incomeSource;
    @FXML public TextField incomeAmount;


    /**
     * Switches scene to back to the expenseIncome-page when the 'Go back' button is pressed.
     */
    @FXML
    protected void goBackButton() {
        SceneSwitcher.getInstance().switchScene("expenseIncomePage","expenseIncome-page");
    }


    /**
     * Saves the income to the income.txt file when the 'Save' button is pressed.
     */
    @FXML
    protected void saveButtonIncome() {
        String incomeFilename = CurrentUser.getInstance().getUser().getIncomesFilePath();
        ObservableList<Income> incomes = FileHandler.readIncomes(incomeFilename);

        // Reads the input from the text fields
        String source = incomeSource.getText();
        double amount = 0;

        // Tries to parse the amount to a double
        try {
            amount = Double.parseDouble(incomeAmount.getText());
        } catch (NumberFormatException e) {
            SceneSwitcher.showAlert("Please enter a valid amount.");
            return;
        }

        // Checks if the given input is valid
        if (source == null || source.isBlank() || source.isEmpty()) {
            SceneSwitcher.showAlert("Please enter a source.");
            return;
        }
        if (amount <= 0) {
            SceneSwitcher.showAlert("Please enter a valid amount.");
            return;
        }

        // Adds the new income to the register
        incomes.add(new Income(LocalDateTime.now(), source, amount));

        // Writes the new register to the file
        List<Income> incomesList = new ArrayList<>(incomes);
        FileHandler.writeIncomes(incomeFilename, incomesList);

        goBackButton();
    }
}
