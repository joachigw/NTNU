package edu.ntnu.idatt1002.team20.models.expense;

import edu.ntnu.idatt1002.team20.models.debt.Debt;
import edu.ntnu.idatt1002.team20.models.expense.Expense;
import edu.ntnu.idatt1002.team20.models.goal.Goal;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTest {

    @Test
    void testBuilderRequiredParameters() {
        LocalDateTime expenseDate = LocalDateTime.now();
        String expenseSource = "Rent";
        Double expenseAmount = 1000.0;

        Expense expense = new Expense.Builder(expenseDate, expenseSource, expenseAmount).build();

        assertEquals(expenseDate, expense.getExpenseDate());
        assertEquals(expenseSource, expense.getExpenseSource());
        assertEquals(expenseAmount, expense.getExpenseAmount());
        assertNull(expense.getGoal());
        assertNull(expense.getDebt());
    }

    @Test
    void testBuilderAllParameters() {
        LocalDateTime expenseDate = LocalDateTime.now();
        String expenseSource = "Electricity Bill";
        Double expenseAmount = 100.0;
        Goal goal = new Goal("Save for a new car", 5000.0, 100);
        Debt debt = new Debt("Credit Card Debt", 5000.0, 500, 0.15);

        Expense expense = new Expense.Builder(expenseDate, expenseSource, expenseAmount)
                .goal(goal)
                .debt(debt)
                .build();

        assertEquals(expenseDate, expense.getExpenseDate());
        assertEquals(expenseSource, expense.getExpenseSource());
        assertEquals(expenseAmount, expense.getExpenseAmount());
        assertEquals(goal, expense.getGoal());
        assertEquals(debt, expense.getDebt());
    }

    @Test
    void testEqualsAndHashCode() {
        LocalDateTime expenseDate = LocalDateTime.now();
        String expenseSource = "Internet Bill";
        double expenseAmount = 50.0;

        Expense expense1 = new Expense.Builder(expenseDate, expenseSource, expenseAmount)
                .build();

        Expense expense2 = new Expense.Builder(expenseDate, expenseSource, expenseAmount)
                .build();

        assertEquals(expense1, expense2);
        assertEquals(expense1.hashCode(), expense2.hashCode());
    }

    @Test
    void testToString() {
        LocalDateTime expenseDate = LocalDateTime.now();
        String expenseSource = "Groceries";
        double expenseAmount = 80.0;

        Expense expense = new Expense.Builder(expenseDate, expenseSource, expenseAmount).build();

        String expectedString = "Groceries: 80.0";

        assertEquals(expectedString, expense.toString());
    }
}
