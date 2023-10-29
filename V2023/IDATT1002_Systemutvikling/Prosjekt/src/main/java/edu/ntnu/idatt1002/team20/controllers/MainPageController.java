package edu.ntnu.idatt1002.team20.controllers;

import edu.ntnu.idatt1002.team20.models.expense.ExpenseRegister;
import edu.ntnu.idatt1002.team20.models.income.IncomeRegister;
import edu.ntnu.idatt1002.team20.utils.FileHandler;
import edu.ntnu.idatt1002.team20.utils.SceneSwitcher;
import edu.ntnu.idatt1002.team20.models.bill.Bill;
import edu.ntnu.idatt1002.team20.models.bill.BillRegister;
import edu.ntnu.idatt1002.team20.models.user.CurrentUser;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller for the main page scene.
 */
public class MainPageController implements Initializable {

    private final BillRegister upcomingBillsRegister = new BillRegister();
    private final String upcomingBillStorage = CurrentUser.getInstance().getUser().getUpcomingBillsFilePath();

    @FXML private Label welcomeLabel;
    @FXML private Label balanceAmount;
    @FXML private ProgressBar remainderProgressBar;
    @FXML private Label subBalanceInformation;
    @FXML private TableView<Bill> upcomingBillsTableView;
    @FXML private Label incomeLabel;
    @FXML private Label spentLabel;
    @FXML private Label upcomingBillsLabel;
    @FXML private Label restLabel;


    /**
     * Initializes the table view with columns and default bills.
     *
     * @param location the location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources the resources used to localize the root object, or null if the root object was not localized.
     */
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        // Load bills from file and add them to the bill register
        readBillsFromFile();

        // Set the welcome label
        welcomeLabel.setText("Welcome, " + CurrentUser.getInstance().getUser().getUsername() + "!");

        // Add a billId column
        TableColumn<Bill, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("billId"));
        idColumn.setEditable(false);

        // Add a due date column
        TableColumn<Bill, LocalDate> dueDateColumn = new TableColumn<>("Due date");
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        // Add a category column
        TableColumn<Bill, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        // Add a category column
        TableColumn<Bill, Double> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        amountColumn.setCellFactory(col -> new TextFieldTableCell<Bill, Double>(new StringConverter<Double>() {
            private final DecimalFormat df = new DecimalFormat("#,###.##");

            @Override
            public String toString(Double object) {
                if (object == null) {
                    return null;
                }
                return df.format(object);
            }

            @Override
            public Double fromString(String string) {
                try {
                    Number n = df.parse(string);
                    return n.doubleValue();
                } catch (ParseException e) {
                    return null;
                }
            }
        }) {
            @Override
            public void updateItem(Double number, boolean empty) {
                super.updateItem(number, empty);
                if (empty || number == null) {
                    setText(null);
                } else {
                    setText(new DecimalFormat("#,###.##").format(number));
                }
            }
        });

        // Ensures type safety for the table view (here: is of type Debt)
        List<TableColumn<Bill, ?>> columns = new ArrayList<>();
        columns.add(idColumn);
        columns.add(dueDateColumn);
        columns.add(categoryColumn);
        columns.add(amountColumn);
        upcomingBillsTableView.getColumns().addAll(columns);

        // Set the table view to show the debts
        upcomingBillsTableView.setItems(FXCollections.observableArrayList(upcomingBillsRegister.getUpcomingBills()));

        // Add the total income, expense, upcoming bills and remaining balance to the labels
        ExpenseRegister expenseRegister = new ExpenseRegister();
        expenseRegister.addExpensesToObservableList(FileHandler.readExpenses(CurrentUser.getInstance()
                                                        .getUser().getExpensesFilePath()));
        IncomeRegister incomeRegister = new IncomeRegister();
        incomeRegister.addIncomesToObservableList(FileHandler.readIncomes(CurrentUser.getInstance()
                                                        .getUser().getIncomesFilePath()));
        BillRegister upcomingBillsRegister = new BillRegister();
        upcomingBillsRegister.addBills(FileHandler.readBills(CurrentUser.getInstance()
                                                        .getUser().getUpcomingBillsFilePath()));

        double income = incomeRegister.getTotalIncome();
        double expenses = expenseRegister.getTotalExpenses();
        double upcomingBills = upcomingBillsRegister.getTotalAmount();

        double balance = income - expenses - upcomingBills;

        incomeLabel.setText(new DecimalFormat("#,###.##").format(income) + " kr");
        spentLabel.setText(new DecimalFormat("#,###.##").format(expenses) + " kr");
        upcomingBillsLabel.setText(new DecimalFormat("#,###.##").format(upcomingBills) + " kr");
        restLabel.setText(new DecimalFormat("#,###.##").format(balance) + " kr");

        // Set a balance based on the total amount of expenses
        balanceAmount.setText(new DecimalFormat("#,###.##").format(balance) + " kr");

        remainderProgressBar.setProgress((income-expenses-upcomingBills)/income);

        String month = LocalDate.now().getMonth().toString().toLowerCase();
        month = month.substring(0, 1).toUpperCase() + month.substring(1);
        int daysLeft = LocalDate.now().lengthOfMonth() - LocalDate.now().getDayOfMonth() + 1;
        subBalanceInformation.setText("left in " + month + "  |  " + daysLeft + " days left");

    }

    /**
     * Clears the bill register and reads the bills from the file.
     */
    public void readBillsFromFile() {
        upcomingBillsRegister.clear();
        List <Bill> readDebts = FileHandler.readBills(upcomingBillStorage);
        readDebts.forEach(upcomingBillsRegister::addBill);
    }


    /**
     * Switches to the expense and income scene when the 'Expense vs. income' button is clicked.
     */
    @FXML
    public void expenseIncomePage() {
        SceneSwitcher.getInstance().switchScene("expenseIncomePage", "expenseIncome-page");
    }


    /**
     * Switches to the debt scene when the 'Debt' button is clicked.
     */
    @FXML
    public void debtPage() {
        SceneSwitcher.getInstance().switchScene("debtPage", "debt-page");
    }


    /**
     * Switches to the goals scene when the 'Goals' button is clicked.
     */
    @FXML
    public void goalsPage() {
        SceneSwitcher.getInstance().switchScene("goalsPage", "goals-page");
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
    public void aboutHome() {
        SceneSwitcher.getInstance().showPopupWindow("mainPage", "about-main");
    }
}