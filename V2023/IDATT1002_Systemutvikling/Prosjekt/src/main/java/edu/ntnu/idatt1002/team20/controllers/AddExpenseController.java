package edu.ntnu.idatt1002.team20.controllers;

import edu.ntnu.idatt1002.team20.utils.FileHandler;
import edu.ntnu.idatt1002.team20.utils.SceneSwitcher;
import edu.ntnu.idatt1002.team20.models.debt.Debt;
import edu.ntnu.idatt1002.team20.models.debt.DebtRegister;
import edu.ntnu.idatt1002.team20.models.expense.Expense;
import edu.ntnu.idatt1002.team20.models.goal.Goal;
import edu.ntnu.idatt1002.team20.models.goal.GoalRegister;
import edu.ntnu.idatt1002.team20.models.user.CurrentUser;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Controller for the add-expense-page.
 */
public class AddExpenseController {

    private final DebtRegister debtRegister = new DebtRegister();
    private final GoalRegister goalRegister = new GoalRegister();

    private final String expensesFilename = CurrentUser.getInstance().getUser().getExpensesFilePath();
    private final String debtFilename = CurrentUser.getInstance().getUser().getDebtsFilePath();
    private final String goalFilename = CurrentUser.getInstance().getUser().getGoalsFilePath();

    @FXML private TextField expenseAmount;
    @FXML private TextField expenseSource;
    @FXML private ComboBox<Debt> debtComboBox;
    @FXML private ComboBox<Goal> goalComboBox;


    /**
     * Initializes the ComboBoxes.
     */
    public void initialize() {

        // Reads the debts and goals from their respective files
        debtRegister.addDebts(FileHandler.readDebts(debtFilename));
        goalRegister.addGoals(FileHandler.readGoals(goalFilename).getGoalsAsList());

        // Adds the debts and goals to the ComboBoxes that were read from the file, if any
        if (debtRegister.getDebts().isEmpty()) {
            debtComboBox.setPromptText("No debts");
        } else {
            debtComboBox.getItems().addAll(debtRegister.getDebts());
        }
        if (goalRegister.getGoals().isEmpty()) {
            goalComboBox.setPromptText("No goals");
        } else {
            goalComboBox.getItems().addAll(goalRegister.getGoals().values());
        }

        // Formats the values in the dropdown of the ComboBox to show the type of the debt instead of the object
        debtComboBox.setCellFactory(new Callback<ListView<Debt>, ListCell<Debt>>() {
            @Override
            public ListCell<Debt> call(ListView<Debt> param) {
                return new ListCell<Debt>() {
                    @Override
                    protected void updateItem(Debt item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText("");
                        } else {
                            setText(item.getDebtType());
                        }
                    }
                };
            }
        });

        // Formats the values in the dropdown of the ComboBox to show the type of the goal instead of the object
        goalComboBox.setCellFactory(new Callback<ListView<Goal>, ListCell<Goal>>() {
            @Override
            public ListCell<Goal> call(ListView<Goal> param) {
                return new ListCell<Goal>() {
                    @Override
                    protected void updateItem(Goal item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText("");
                        } else {
                            setText(item.getGoalType());
                        }
                    }
                };
            }
        });
    }


    /**
     * Switches back to the 'Expense vs. income' page when the 'Go back' button is clicked.
     */
    @FXML
    protected void goBackButton() {
        SceneSwitcher.getInstance().switchScene("expenseIncomePage","expenseIncome-page");
    }


    /**
     * Saves the expense to the expenses.txt file.
     */
    @FXML
    protected void saveButtonExpense() {

        ObservableList<Expense> expenses = FileHandler.readExpenses(expensesFilename);

        // Retrieves the information from the input fields and ComboBoxes
        String source = expenseSource.getText();
        double amount;

        // Checks if the given input is valid
        try {
            amount = Double.parseDouble(expenseAmount.getText());
        } catch (NumberFormatException e) {
            SceneSwitcher.showAlert("Please enter a valid amount.");
            return;
        }

        Debt debt = debtComboBox.getValue();
        Goal goal = goalComboBox.getValue();

        // Checks if the given input is valid
        if (source == null || source.isBlank() || source.isEmpty()){
            SceneSwitcher.showAlert("Please enter a source.");
            return;
        }
        if (amount <= 0){
            SceneSwitcher.showAlert("Please enter a valid amount.");
            return;
        }

        // Adds the expense to the list of expenses
        expenses.add(new Expense.Builder(LocalDateTime.now(), source, amount).debt(debt).goal(goal).build());

        // Adds the amount to the debt and goal and saves the new registers to the file (if they were selected)
        if (debt != null) {
            debt.addAmountPaid(amount);
            FileHandler.writeDebts(debtFilename, debtRegister.getDebts());
        }
        if (goal != null) {
            goal.addAmountSpent(amount);
            FileHandler.writeGoals(goalFilename, goalRegister.getGoalsAsList());
        }

        // Writes the new register to the file
        List<Expense> expensesList = new ArrayList<>(expenses);
        FileHandler.writeExpenses(expensesFilename, expensesList);

        goBackButton();
    }
}
