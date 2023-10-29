package edu.ntnu.idatt1002.team20.controllers;

import edu.ntnu.idatt1002.team20.utils.FileHandler;
import edu.ntnu.idatt1002.team20.utils.SceneSwitcher;
import edu.ntnu.idatt1002.team20.models.debt.Debt;
import edu.ntnu.idatt1002.team20.models.debt.DebtRegister;
import edu.ntnu.idatt1002.team20.models.user.CurrentUser;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;


/**
 * Controller for the debt scene.
 * @author joachigw
 */
public class DebtController implements Initializable {

    private static final DebtRegister debtRegister = new DebtRegister();
    String debtsStorage = CurrentUser.getInstance().getUser().getDebtsFilePath();

    @FXML private TableView<Debt> debtTableView;
    @FXML private Label debtTitle;

    /**
     * Initializes the table view with columns and default debts.
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Load debts from file and add them to the debt register
        readDebtsFromFile();

        if (debtRegister.getDebts().isEmpty()) {
            debtTitle.setText("No debts registered.");
        }

        // Add a type column
        TableColumn<Debt, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("debtType"));

        // Add cell factory for editing and updating debtType
        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Add cell factory to update the debt type
        typeColumn.setOnEditCommit(event -> {
            if(event.getNewValue() == null || event.getNewValue().isEmpty() || event.getNewValue().isBlank()) {
                SceneSwitcher.showAlert("Please enter a valid debt type.");
                return;
            }

            // Set the type of the debt and update the table view
            Debt debt = event.getRowValue();
            debt.setDebtType(event.getNewValue());
            FileHandler.writeDebts(debtsStorage, debtRegister.getDebts());
            debtTableView.refresh();
        });

        // Add a category column
        TableColumn<Debt, Double> totalAmountColumn = new TableColumn<>("Total amount");
        totalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        // Format the total amount column to show currency
        setDoubleCellFactory(totalAmountColumn, " kr");

        // Add cell factory for editing and updating totalAmount
        totalAmountColumn.setOnEditCommit(event -> {
            if(event.getNewValue() == null) {
                SceneSwitcher.showAlert("Please enter a valid amount.");
                return;
            }

            // Set the total amount of the debt, update the progress bar and table view
            Debt debt = event.getRowValue();
            debt.setTotalAmount(event.getNewValue());
            FileHandler.writeDebts(debtsStorage, debtRegister.getDebts());
            debtTableView.refresh();
        });

        // Add a category column
        TableColumn<Debt, Double> amountPaidColumn = new TableColumn<>("Amount paid");
        amountPaidColumn.setCellValueFactory(new PropertyValueFactory<>("amountPaid"));
        amountPaidColumn.setEditable(false);
        // Format the amount paid column to show currency
        setDoubleCellFactory(amountPaidColumn, " kr");

        // Add an amount column
        TableColumn<Debt, Double> interestRateColumn = new TableColumn<>("Interest rate");
        interestRateColumn.setCellValueFactory(new PropertyValueFactory<>("interestRate"));
        // Format the interest rate column to show percentage
        setDoubleCellFactory(interestRateColumn, " %");

        // Add cell factory for editing and updating interestRate
        interestRateColumn.setOnEditCommit(event -> {
            if(event.getNewValue() == null) {
                SceneSwitcher.showAlert("Please enter a valid interest rate.");
                return;
            }

            // Set the interest rate of the debt
            Debt debt = event.getRowValue();
            debt.setInterestRate(event.getNewValue());
            FileHandler.writeDebts(debtsStorage, debtRegister.getDebts());
            debtTableView.refresh();
        });

        // Add a progress bar column
        TableColumn<Debt, ProgressBar> progressBarColumn = new TableColumn<>("Progress");
        progressBarColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getProgressBar()));

        // Sort the progress bar column by progress
        progressBarColumn.setComparator(Comparator.comparingDouble(ProgressIndicator::getProgress));

        // Format the progress bar cells to also show the percentage
        progressBarColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(ProgressBar bar, boolean empty) {
                super.updateItem(bar, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    // Add a label next to the progress bar to show the percentage
                    Label label = new Label(new DecimalFormat("##.##").format(bar.getProgress() * 100) + " %");
                    label.setPadding(new Insets(0, 0, 0, 5));
                    bar.setPrefWidth(75);
                    setGraphic(new HBox(bar, label));
                }
            }
        });

        // Add a remove button to each row
        TableColumn<Debt, Void> removeColumn = new TableColumn<>("Remove");
        removeColumn.setPrefWidth(10);
        removeColumn.setCellFactory(column -> new TableCell<>() {
            private final Button removeButton = new Button("Remove");

            {
                removeButton.setId("removeButton");
                // Remove the debt from the table when the remove button is clicked
                removeButton.setOnAction(event -> {
                    Debt debt = getTableRow().getItem();
                    debtRegister.removeDebt(debt);
                    getTableView().getItems().remove(debt);
                    FileHandler.writeDebts(debtsStorage, debtRegister.getDebts());
                    debtTableView.refresh();
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

        // Ensures type safety for the table view (here: is of type Debt)
        List<TableColumn<Debt, ?>> columns = new ArrayList<>();
        columns.add(typeColumn);
        columns.add(totalAmountColumn);
        columns.add(amountPaidColumn);
        columns.add(interestRateColumn);
        columns.add(progressBarColumn);
        columns.add(removeColumn);
        debtTableView.getColumns().addAll(columns);

        // Set the table view to show the debts
        debtTableView.setItems(FXCollections.observableArrayList(debtRegister.getDebts()));
    }


    /**
     * Registers a new debt when the 'Register new debt' button is clicked.
     */
    @FXML
    void registerDebt() {
        SceneSwitcher.getInstance().switchScene("debtPage", "add-debt");
    }


    /**
     * Switches to the main scene when the 'Home' button is clicked.
     */
    @FXML
    void mainPage() {
        SceneSwitcher.getInstance().switchScene("mainPage", "main-page");
    }


    /**
     * Switches to the expense and income scene when the 'Expense vs. income' button is clicked.
     */
    @FXML
    void expenseIncomePage() {
        SceneSwitcher.getInstance().switchScene("expenseIncomePage", "expenseIncome-page");
    }


    /**
     * Switches to the goals scene when the 'Goals' button is clicked.
     */
    @FXML
    void goalsPage() {
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
    public void aboutDebt() {
        SceneSwitcher.getInstance().showPopupWindow("debtPage", "about-debt");
    }


    /**
     * Sets a cell factory for a table column that formats the cell value as a double with two decimals.
     * @param column the column to set the cell factory for
     */
    private void setDoubleCellFactory(TableColumn<Debt, Double> column, String unit) {
        column.setCellFactory(col -> new TextFieldTableCell<Debt, Double>(new StringConverter<Double>() {
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
                    setText(new DecimalFormat("#,##0.00").format(number) + unit);
                }
            }
        });
    }


    /**
     * Reads the debts from the storage-file and adds them to the debt register.
     */
    public void readDebtsFromFile() {
        // Clear the debt register to avoid duplicates
        debtRegister.clear();
        // Read the debts from the file
        List <Debt> readDebts = FileHandler.readDebts(debtsStorage);
        readDebts.forEach(debtRegister::addDebt);
    }
}