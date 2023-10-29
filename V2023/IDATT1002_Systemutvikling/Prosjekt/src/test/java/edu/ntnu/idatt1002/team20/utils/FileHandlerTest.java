package edu.ntnu.idatt1002.team20.utils;

import edu.ntnu.idatt1002.team20.models.debt.Debt;
import edu.ntnu.idatt1002.team20.models.expense.Expense;
import edu.ntnu.idatt1002.team20.models.user.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileHandlerTest {

    @Test
    void testWriteExpenses() throws IOException {
        // Create a list of expenses to write to file
        List<Expense> expenses = new ArrayList<>();
        LocalDateTime dateTime = LocalDateTime.now();

        expenses.add(new Expense.Builder(dateTime, "Expense2", 200.0)
                .debt(new Debt("Debt1", 500.0, 100, 5))
                .build());

        // Write the expenses to a temporary file
        Path tempFile = Files.createTempFile("testExpenses", ".txt");
        FileHandler.writeExpenses(tempFile.toString(), expenses);

        // Read the contents of the file and compare with the expected output
        List<String> lines = Files.readAllLines(tempFile);
        assertEquals(dateTime.toString(), lines.get(0));
        assertEquals("Expense2", lines.get(1));
        assertEquals("200.0", lines.get(2));
        assertEquals("Debt{debtType='Debt1', totalAmount=500.0, amountPaid=100.0, interestRate=5.0}", lines.get(3));
        assertEquals("null", lines.get(4));
        assertEquals("", lines.get(5));

        // Delete the temporary file
        Files.delete(tempFile);
    }


    @Test
    public void testReadUsers() {
        String username = "username";
        String password = "password";
        String expenseFilePath = "src/main/resources/edu/ntnu/idatt1002/data/userData/username/expenseFilePath";
        String incomeFilePath = "src/main/resources/edu/ntnu/idatt1002/data/userData/username/incomeFilePath";
        String debtFilePath = "src/main/resources/edu/ntnu/idatt1002/data/userData/username/debtFilePath";
        String goalFilePath = "src/main/resources/edu/ntnu/idatt1002/data/userData/username/goalFilePath";
        String billsFilePath = "src/main/resources/edu/ntnu/idatt1002/data/userData/username/billsFilePath";
        String upcomingBillsFilePath = "src/main/resources/edu/ntnu/idatt1002/data/userData/username/upcomingBillsFilePath";

        User firstUser = new User(username, password, expenseFilePath, incomeFilePath, debtFilePath, goalFilePath, billsFilePath, upcomingBillsFilePath);
        assertEquals("username", firstUser.getUsername());
        assertEquals(HashPassword.hash(password), HashPassword.hash(firstUser.getHashedPassword()));
        assertEquals(expenseFilePath, firstUser.getExpensesFilePath());
        assertEquals(incomeFilePath, firstUser.getIncomesFilePath());
        assertEquals(debtFilePath, firstUser.getDebtsFilePath());
        assertEquals(goalFilePath, firstUser.getGoalsFilePath());
        assertEquals(billsFilePath, firstUser.getBillsFilePath());
        assertEquals(upcomingBillsFilePath, firstUser.getUpcomingBillsFilePath());
    }

}