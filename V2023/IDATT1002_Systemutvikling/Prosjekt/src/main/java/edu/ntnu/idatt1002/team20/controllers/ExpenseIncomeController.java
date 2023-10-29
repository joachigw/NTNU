package edu.ntnu.idatt1002.team20.controllers;

import edu.ntnu.idatt1002.team20.utils.FileHandler;
import edu.ntnu.idatt1002.team20.utils.SceneSwitcher;
import edu.ntnu.idatt1002.team20.models.debt.Debt;
import edu.ntnu.idatt1002.team20.models.debt.DebtRegister;
import edu.ntnu.idatt1002.team20.models.expense.Expense;
import edu.ntnu.idatt1002.team20.models.expense.ExpenseRegister;
import edu.ntnu.idatt1002.team20.models.goal.Goal;
import edu.ntnu.idatt1002.team20.models.goal.GoalRegister;
import edu.ntnu.idatt1002.team20.models.income.Income;

import edu.ntnu.idatt1002.team20.models.income.IncomeRegister;
import edu.ntnu.idatt1002.team20.models.user.CurrentUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Controller for the expense-income page.
 */
public class ExpenseIncomeController implements Initializable {

    private final ExpenseRegister expenseRegister = new ExpenseRegister();
    private final IncomeRegister incomeRegister = new IncomeRegister();
    private final DebtRegister debtRegister = new DebtRegister();
    private GoalRegister goalRegister;

    @FXML private TableColumn<Expense, LocalDateTime> expenseTableDate;
    @FXML private TableColumn<Expense, String> expenseTableSource;
    @FXML private TableColumn<Expense, Double> expenseTableAmount;
    @FXML private TableColumn<Expense, Debt> expenseTableDebt;
    @FXML private TableColumn<Expense, Goal> expenseTableGoal;
    @FXML private TableColumn<Income, LocalDateTime> incomeTableDate;
    @FXML private TableColumn<Income, String> incomeTableSource;
    @FXML private TableColumn<Income, Double> incomeTableAmount;
    @FXML private TableView<Income> tableViewIncome;
    @FXML private TableView<Expense> tableViewExpense;

    private final String expenseFile = CurrentUser.getInstance().getUser().getExpensesFilePath();
    private final String incomeFile = CurrentUser.getInstance().getUser().getIncomesFilePath();
    private final String debtFile = CurrentUser.getInstance().getUser().getDebtsFilePath();
    private final String goalFile = CurrentUser.getInstance().getUser().getGoalsFilePath();

    /**
     * Switches to the add expense scene when the 'Add expense' button is pressed.
     */
    @FXML
    protected void addExpenseButton() {
        SceneSwitcher.getInstance().switchScene("expenseIncomePage", "add-expense");
    }


    /**
     * Switches to the add income scene when the 'Add income' button is pressed.
     */
    @FXML
    protected void addIncomeButton() {
        SceneSwitcher.getInstance().switchScene("expenseIncomePage","add-income");
    }


    /**
     * Initializes the controller class and sets the values for the table columns.
     * @param url url
     * @param resourceBundle resources
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Adds the expenses and incomes read from the file to the registers
        expenseRegister.addExpensesToObservableList(FileHandler.readExpenses(expenseFile));
        incomeRegister.addIncomesToObservableList(FileHandler.readIncomes(incomeFile));

        // Adds the debts and goals read from the file to the registers
        debtRegister.addDebts(FileHandler.readDebts(debtFile));
        goalRegister = FileHandler.readGoals(goalFile);

        // Sets the values for the table columns
        expenseTableDate.setCellValueFactory(new PropertyValueFactory<>("expenseDate"));
        expenseTableSource.setCellValueFactory(new PropertyValueFactory<>("expenseSource"));
        expenseTableAmount.setCellValueFactory(new PropertyValueFactory<>("expenseAmount"));
        expenseTableDebt.setCellValueFactory(new PropertyValueFactory<>("debt"));
        expenseTableGoal.setCellValueFactory(new PropertyValueFactory<>("goal"));

        // Adds the expenses read from the file to the table
        tableViewExpense.setItems(expenseRegister.getExpenseObservableList());


        // Sets the values for the table columns
        incomeTableDate.setCellValueFactory(new PropertyValueFactory<>("incomeDate"));
        incomeTableSource.setCellValueFactory(new PropertyValueFactory<>("incomeSource"));
        incomeTableAmount.setCellValueFactory(new PropertyValueFactory<>("incomeAmount"));

        // Formats the date in the table to be displayed as a string
        expenseTableDate.setCellFactory(column -> new TableCell<Expense, LocalDateTime>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toLocalDate().toString());
                }
            }
        });

        // Formats the date in the table to be displayed as a string
        incomeTableDate.setCellFactory(column -> new TableCell<Income, LocalDateTime>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toLocalDate().toString());
                }
            }
        });

        // Formats the debt in the table to be displayed as a string
        expenseTableDebt.setCellFactory(column -> new TableCell<Expense, Debt>() {
            @Override
            protected void updateItem(Debt item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getDebtType());
                }
            }
        });

        // Formats the goal in the table to be displayed as a string
        expenseTableGoal.setCellFactory(column -> new TableCell<Expense, Goal>() {
            @Override
            protected void updateItem(Goal item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getGoalType());
                }
            }
        });

        // Adds the incomes read from the file to the table
        tableViewIncome.setItems(incomeRegister.getIncomeObservableList());

        // Add a remove button to each row in the expense table
        TableColumn<Expense, Void> removeExpenseColumn = new TableColumn<>("Remove");
        removeExpenseColumn.setPrefWidth(10);
        removeExpenseColumn.setCellFactory(column -> new TableCell<>() {
            private final Button removeButton = new Button("Remove");

            {
                removeButton.setId("removeButton");

                // Remove the debt from the table when the remove button is clicked
                removeButton.setOnAction(event -> {
                    Expense expense = getTableRow().getItem();
                    Debt debt = expense.getDebt();
                    Goal goal = expense.getGoal();
                    if (debt != null) {
                        debtRegister.getDebtByType(debt.getDebtType()).subtractAmountPaid(expense.getExpenseAmount());
                        FileHandler.writeDebts(debtFile, debtRegister.getDebts());
                    }
                    if(goal != null) {
                        goalRegister.getGoal(goal.getGoalType()).removeAmountSpent(expense.getExpenseAmount());
                        FileHandler.writeGoals(goalFile, goalRegister.getGoalsAsList());
                    }
                    expenseRegister.removeByObject(expense);
                    getTableView().getItems().remove(expense);
                    FileHandler.writeExpenses(expenseFile, expenseRegister.getExpenseObservableList());
                    tableViewExpense.refresh();
                });
            }

            // Add the remove button to the row
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(removeButton);
                }
            }
        });

        // Add a remove button to each row in the income table
        TableColumn<Income, Void> removeIncomeColumn = new TableColumn<>("Remove");
        removeIncomeColumn.setPrefWidth(10);
        removeIncomeColumn.setCellFactory(column -> new TableCell<>() {
            private final Button removeButton = new Button("Remove");

            {
                removeButton.setId("removeButton");

                // Remove the debt from the table when the remove button is clicked
                removeButton.setOnAction(event -> {
                    Income income = getTableRow().getItem();
                    incomeRegister.removeByObject(income);
                    getTableView().getItems().remove(income);
                    FileHandler.writeIncomes(incomeFile, incomeRegister.getIncomeObservableList());
                    tableViewExpense.refresh();
                });
            }

            // Add the remove button to the row
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(removeButton);
                }
            }
        });

        tableViewExpense.getColumns().add(removeExpenseColumn);
        tableViewIncome.getColumns().add(removeIncomeColumn);
    }


    /**
     * Switches to the main scene when the 'Home' button is pressed.
     */
    @FXML
    void mainPage() {
        SceneSwitcher.getInstance().switchScene("mainPage", "main-page");
    }


    /**
     * Switches to the goal scene when the 'Goals' button is pressed.
     */
    @FXML
    void goalsPage() {
        SceneSwitcher.getInstance().switchScene("goalsPage", "goals-page");
    }


    /**
     * Switches to the debt scene when the 'Debt' button is pressed.
     */
    @FXML
    void debtPage() {
        SceneSwitcher.getInstance().switchScene("debtPage", "debt-page");
    }


    /**
     * Switches to the bills scene when the 'Bills' button is pressed.
     */
    @FXML
    void billsPage() {
        SceneSwitcher.getInstance().switchScene("billsPage", "bills-page");
    }


    /**
     * Switches to the log in scene when the 'Log out' button is clicked.
     */
    @FXML
    public void logOut() {
        SceneSwitcher.getInstance().switchScene("logInPage", "logIn-page");
    }


    /**
     * Switches to the about scene when the 'About' button is clicked.
     */
    @FXML
    public void aboutExpenseIncome() {
        SceneSwitcher.getInstance().showPopupWindow("expenseIncomePage", "about-expenseIncome");
    }
}

