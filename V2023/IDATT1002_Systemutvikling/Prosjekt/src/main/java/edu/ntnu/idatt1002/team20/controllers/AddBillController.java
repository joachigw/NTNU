package edu.ntnu.idatt1002.team20.controllers;

import edu.ntnu.idatt1002.team20.utils.FileHandler;
import edu.ntnu.idatt1002.team20.utils.SceneSwitcher;
import edu.ntnu.idatt1002.team20.models.bill.Bill;
import edu.ntnu.idatt1002.team20.models.bill.BillRegister;
import edu.ntnu.idatt1002.team20.models.user.CurrentUser;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller for the add bill scene.
 */
public class AddBillController {

    private final BillRegister billRegister = new BillRegister();

    @FXML private DatePicker dueDateField;
    @FXML private TextField categoryField;
    @FXML private TextField amountDueField;


    /**
     * Ensure that the due date field is shown when the user holds enter.
     */
    public void initialize() {
        dueDateField.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                dueDateField.show();
                event.consume();
            }
        });
    }


    /**
     * Returns to the main scene when the go back button is pressed.
     */
    @FXML
    void goBack() {
        SceneSwitcher.getInstance().switchScene("billsPage", "bills-page");
    }


    /**
     * Adds a bill to the bill register based on the input fields.
     */
    @FXML
    void submit() {
        if(dueDateField.getValue() == null) {
            SceneSwitcher.showAlert("Please enter a due date.");
            return;
        }
        if(dueDateField.getValue().isBefore(LocalDate.now())) {
            SceneSwitcher.showAlert("Please enter a valid due date.");
            return;
        }
        if(categoryField.getText().isEmpty() || categoryField.getText().isBlank()) {
            SceneSwitcher.showAlert("Please enter a category.");
            return;
        }
        if(amountDueField.getText().isEmpty() || amountDueField.getText().isBlank()) {
            SceneSwitcher.showAlert("Please enter an amount.");
            return;
        }
        if(!amountDueField.getText().matches("[0-9]+")) {
            SceneSwitcher.showAlert("Please enter a valid amount.");
            return;
        }
        if(Double.parseDouble(amountDueField.getText()) <= 0) {
            SceneSwitcher.showAlert("Please enter a valid amount.");
            return;
        }

        // Read the new bill from the input fields and add it to the bill register
        LocalDate dueDate = dueDateField.getValue();
        String category = categoryField.getText();
        double amount = Double.parseDouble(amountDueField.getText());
        List<Bill> bills = FileHandler.readBills(CurrentUser.getInstance().getUser().getBillsFilePath());
        billRegister.addBill(new Bill(dueDate, category, bills.size() + 1, amount));

        // Write the new bill to the bills file
        String billsStorage = CurrentUser.getInstance().getUser().getBillsFilePath();
        String upcomingBillStorage = CurrentUser.getInstance().getUser().getUpcomingBillsFilePath();
        FileHandler.writeAddNewBill(billsStorage, upcomingBillStorage, new Bill(dueDate, category, bills.size() + 1, amount));

        // Returns to the bills scene
        SceneSwitcher.getInstance().switchScene("billsPage", "bills-page");
    }
}
