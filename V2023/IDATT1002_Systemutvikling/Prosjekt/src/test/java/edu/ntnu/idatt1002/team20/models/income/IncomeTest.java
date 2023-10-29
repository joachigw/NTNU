package edu.ntnu.idatt1002.team20.models.income;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.AssertJUnit.assertEquals;


public class IncomeTest {
    Income income = new Income(LocalDateTime.now(), "Job", 100);

    @Test
    @DisplayName("Testing accessing Income's income source.")
    public void getIncomeSourceTest() {
        assertEquals("Job", income.getIncomeSource());
    }

    @Test
    @DisplayName("Testing accessing Income's income amount.")
    public void getIncomeAmountTest() {
        assertEquals(100, income.getIncomeAmount(), 1e-6);
    }

    @Test
    @DisplayName("Testing the set method for income amount.")
    public void setIncomeAmountTest() {
        income.setIncomeAmount(200);
        assertEquals(200, income.getIncomeAmount(), 1e-6);
    }

    @Test
    @DisplayName("Testing the exception handling for income source in Income's constructor.")
    public void incomeSourceExceptionHandlingTest() {
        assertThrows(IllegalArgumentException.class, () -> new Income(LocalDateTime.now(), "", 100));
    }

    @Test
    @DisplayName("Testing the exception handling for income amount in Income's constructor.")
    public void incomeAmountExceptionHandlingTest() {
        assertThrows(IllegalArgumentException.class, () -> new Income(LocalDateTime.now(), "Job", -4));
    }

    @Test
    @DisplayName("Testing the exception handling for the set method of income amount.")
    public void setIncomeAmountExceptionHandlingTest() {
        assertThrows(IllegalArgumentException.class, () -> income.setIncomeAmount(-5));
    }
}
