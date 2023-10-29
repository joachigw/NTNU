package edu.ntnu.idatt1002.team20.models.income;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

public class IncomeRegisterTest {

    IncomeRegister incomeRegister = new IncomeRegister();
    Income income1 = new Income(LocalDateTime.now(), "Work", 500);
    Income income2 = new Income(LocalDateTime.now(), "Food", 300);

    @Test
    @DisplayName("Testing the add income method, by the size of the list.")
    public void addIncomeTestBySize() {
        incomeRegister.addIncome(income1);
        incomeRegister.addIncome(income2);
        assertEquals(2, incomeRegister.getIncomes().size());
    }

    @Test
    @DisplayName("Testing the add income method, by the index of the list.")
    public void addIncomeTestByIndex() {
        incomeRegister.addIncome(income1);
        String string = income1.getIncomeSource();
        assertEquals(income1, incomeRegister.getIncome(string));
    }


    @Test
    @DisplayName("Testing the exception handling of the get income method.")
    public void getIncomeExceptionHandlingTest() {
        assertThrows(IllegalArgumentException.class, () -> incomeRegister.getIncome(null));
    }

    @Test
    @DisplayName("Testing the exception handling of the add income method.")
    public void addIncomeExceptionHandlingTest() {
        assertThrows(IllegalArgumentException.class, () -> incomeRegister.addIncome(null));
    }
}
