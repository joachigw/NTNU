package edu.ntnu.idatt1002.team20.models.expense;

import edu.ntnu.idatt1002.team20.models.expense.Expense;
import edu.ntnu.idatt1002.team20.models.expense.ExpenseRegister;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

public class ExpenseRegisterTest {

    private ExpenseRegister expenseRegister;

    @BeforeEach
    public void setUp() {
        expenseRegister = new ExpenseRegister();
    }

    @Test
    public void testGetTotalExpenses() {
        // Create some test expenses
        Expense expense1 = new Expense.Builder(LocalDateTime.now(), "Groceries", 100).build();
        Expense expense2 = new Expense.Builder(LocalDateTime.now(), "Gasoline", 50).build();
        Expense expense3 = new Expense.Builder(LocalDateTime.now(), "Eating out", 75).build();

        // Add the expenses to the expense register
        expenseRegister.addExpensesToObservableList(Arrays.asList(expense1, expense2, expense3));

        // Verify that the total expenses are calculated correctly
        Assertions.assertEquals(225, expenseRegister.getTotalExpenses());
    }

    @Test
    public void testAddExpensesToObservableList() {
        // Create a test expense
        Expense expense = new Expense.Builder(LocalDateTime.now(), "Groceries", 100).build();

        // Add the expense to the expense register
        expenseRegister.addExpensesToObservableList(Collections.singletonList(expense));

        // Verify that the expense was added to the expense register
        ObservableList<Expense> expectedObservableList = FXCollections.observableArrayList(Collections.singletonList(expense));
        Assertions.assertEquals(expectedObservableList, expenseRegister.getExpenseObservableList());
    }

    @Test
    public void testRemoveByObject() {
        // Create some test expenses
        Expense expense1 = new Expense.Builder(LocalDateTime.now(), "Groceries", 100).build();
        Expense expense2 = new Expense.Builder(LocalDateTime.now(), "Gasoline", 50).build();
        Expense expense3 = new Expense.Builder(LocalDateTime.now(), "Eating out", 75).build();

        // Add the expenses to the expense register
        expenseRegister.addExpensesToObservableList(Arrays.asList(expense1, expense2, expense3));

        // Remove expense2 from the expense register
        expenseRegister.removeByObject(expense2);

        // Verify that the expense was removed from the expense register
        ObservableList<Expense> expectedObservableList = FXCollections.observableArrayList(Arrays.asList(expense1, expense3));
        Assertions.assertEquals(expectedObservableList, expenseRegister.getExpenseObservableList());
    }
}
