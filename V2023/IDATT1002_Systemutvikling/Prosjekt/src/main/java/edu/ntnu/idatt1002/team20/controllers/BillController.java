package edu.ntnu.idatt1002.team20.controllers;

import edu.ntnu.idatt1002.team20.models.expense.Expense;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Class for the bill controller.
 * Handles the bill view.
 * @author joachigw
 */
public class BillController implements Initializable {

    private static final BillRegister billRegister = new BillRegister();
    private final List<Expense> expenses = FileHandler.readExpenses(CurrentUser.getInstance().getUser().getExpensesFilePath());
    String billStorage = CurrentUser.getInstance().getUser().getBillsFilePath();
    String upcomingBillStorageFile = CurrentUser.getInstance().getUser().getUpcomingBillsFilePath();

    @FXML private TableView<Bill> billsTableView;
    @FXML private Label amountOfDueBillsLabel;

    /**
     * Initializes the tableview displaying bills.
     * @param url url
     * @param resourceBundle resources
     */
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Load bills from file and add them to the bill register
        readBillsFromFile();

        // Sets values for the overview labels
        setOverviewLabels();

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

        // Add an amount column
        TableColumn<Bill, Double> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        amountColumn.setCellFactory(col -> new TextFieldTableCell<Bill, Double>(new StringConverter<Double>() {
            private final DecimalFormat df = new DecimalFormat("#,##0.00");

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

        // Add a status column
        TableColumn<Bill, Boolean> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("isPaid"));
        statusColumn.setCellFactory(col -> new TableCell<>() {
            public final Label label = new Label();

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    label.setText(item ? "Paid" : "Unpaid");
                    setGraphic(label);
                }
            }
        });

        // Add a 'mark as paid' button to each row
        TableColumn<Bill, Void> paidColumn = new TableColumn<>("Mark as paid");
        paidColumn.setPrefWidth(10);
        paidColumn.setCellFactory(column -> new TableCell<>() {
            private final Button paidButton = new Button("Mark as paid");

            {
                paidButton.setId("markAsPaidButton");
                // Mark the bill as paid when the button is clicked
                paidButton.setOnAction(event -> {
                    Bill bill = getTableRow().getItem();

                    if (bill.getIsPaid()) {
                        SceneSwitcher.showAlert("This bill has already been paid.");
                        return;
                    }

                    // Set the bill as paid and save the bills to file
                    bill.setPaid(true);
                    FileHandler.writeBills(billStorage, billRegister.getBills());
                    billRegister.removeBill(bill);
                    FileHandler.writeUpcomingBills(upcomingBillStorageFile, billRegister.getUpcomingBills());

                    expenses.add(new Expense.Builder(LocalDateTime.now(), bill.getCategory(), bill.getAmount()).build());
                    FileHandler.writeExpenses(CurrentUser.getInstance().getUser().getExpensesFilePath(), expenses);

                    setOverviewLabels();
                    readBillsFromFile();
                    billsTableView.refresh();
                });
            }

            // Add the paid button to the row
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(paidButton.isVisible() ? paidButton : null);
                }
            }
        });

        // Add a remove button to each row
        TableColumn<Bill, Void> removeColumn = new TableColumn<>("Remove");
        removeColumn.setPrefWidth(10);
        removeColumn.setCellFactory(column -> new TableCell<>() {
            private final Button removeButton = new Button("Remove");

            {
                removeButton.setId("removeButton");
                // Remove the debt from the table when the remove button is clicked
                removeButton.setOnAction(event -> {
                    Bill bill = getTableRow().getItem();
                    getTableView().getItems().remove(bill);
                    billRegister.removeBill(bill);

                    FileHandler.writeUpcomingBills(upcomingBillStorageFile, billRegister.getUpcomingBills());
                    FileHandler.writeBills(billStorage, billRegister.getBills());

                    expenses.remove(new Expense.Builder(LocalDateTime.now(), bill.getCategory(), bill.getAmount()).build());
                    FileHandler.writeExpenses(CurrentUser.getInstance().getUser().getExpensesFilePath(), expenses);

                    setOverviewLabels();
                    billsTableView.refresh();
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

        // Ensures type safety for the table view (here: is of type Bill)
        List<TableColumn<Bill, ?>> columns = new ArrayList<>();
        columns.add(idColumn);
        columns.add(dueDateColumn);
        columns.add(categoryColumn);
        columns.add(amountColumn);
        columns.add(statusColumn);
        columns.add(paidColumn);
        columns.add(removeColumn);
        billsTableView.getColumns().addAll(columns);

        // Set the table view to show the debts
        billsTableView.setItems(FXCollections.observableArrayList(billRegister.getBills()));
    }

    /**
     * Updates the bill register.
     */
    private void readBillsFromFile() {
        // Clear the debt register to avoid duplicates
        billRegister.clear();

        // Read the debts from the file
        List <Bill> readBills = FileHandler.readBills(billStorage);
        readBills.forEach(billRegister::addBill);
    }


    /**
     * Sets values for the overview labels.
     */
    private void setOverviewLabels() {
        amountOfDueBillsLabel.setText(String.valueOf(billRegister.getUpcomingBills().size()));
    }

    /**
     * Switches to the main-page when the 'Home' button is pressed.
     */
    @FXML
    void mainPage() {
        SceneSwitcher.getInstance().switchScene("mainPage", "main-page");
    }


    /**
     * Switches to the expenseIncome-page when the 'Expense vs. income' button is pressed.
     */
    @FXML
    void expenseIncomePage() {
        SceneSwitcher.getInstance().switchScene("expenseIncomePage", "expenseIncome-page");
    }


    /**
     * Switches to the debt-page when the 'Debt' button is pressed.
     */
    @FXML
    void debtPage() {
        SceneSwitcher.getInstance().switchScene("debtPage", "debt-page");
    }


    /**
     * Switches to the goals-page when the 'Goals' button is pressed.
     */
    @FXML
    void goalsPage() {
        SceneSwitcher.getInstance().switchScene("goalsPage", "goals-page");
    }


    /**
     * Switches to the add-bill-page when the 'Register new bill' button is pressed.
     */
    @FXML
    void registerBill() {
        SceneSwitcher.getInstance().switchScene("billsPage", "add-bill");
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
    public void aboutBills() {
        SceneSwitcher.getInstance().showPopupWindow("billsPage", "about-bills");
    }
}
