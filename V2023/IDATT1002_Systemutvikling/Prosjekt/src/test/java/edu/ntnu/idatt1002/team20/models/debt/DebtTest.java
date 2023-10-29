package edu.ntnu.idatt1002.team20.models.debt;

import edu.ntnu.idatt1002.team20.models.debt.Debt;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebtTest {

    private final Debt testDebt = new Debt("Vehicle", 200000.2, 50000.0, 6.2);

    @Test
    public void getLoanTypeTest() {
        assertEquals("Vehicle", testDebt.getDebtType());
    }

    @Test
    public void getTotalAmountTest() {
        assertEquals(200000.2, testDebt.getTotalAmount());
    }

    @Test
    public void getAmountPaidTest() {
        assertEquals(50000.0, testDebt.getAmountPaid());
    }

    @Test
    public void getInterestRateTest() {
        assertEquals(6.2, testDebt.getInterestRate());
    }

    @Test
    public void setTotalAmountTest() {
        testDebt.setTotalAmount(100000.2);
        assertEquals(100000.2, testDebt.getTotalAmount());
    }

    @Test
    public void setAmountPaidTest() {
        testDebt.setAmountPaid(51000.3);
        assertEquals(51000.3, testDebt.getAmountPaid());
    }

    @Test
    public void setInterestRateTest() {
        testDebt.setInterestRate(8.9);
        assertEquals(8.9, testDebt.getInterestRate());
    }
}

