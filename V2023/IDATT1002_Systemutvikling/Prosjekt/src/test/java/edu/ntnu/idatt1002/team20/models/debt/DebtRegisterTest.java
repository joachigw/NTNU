package edu.ntnu.idatt1002.team20.models.debt;

import edu.ntnu.idatt1002.team20.models.debt.Debt;
import edu.ntnu.idatt1002.team20.models.debt.DebtRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebtRegisterTest {

    private DebtRegister testDebtRegister;
    private List<Debt> testDebts;

    @BeforeEach
    public void setUp() {
        testDebtRegister = new DebtRegister();
        testDebts = new ArrayList<>();
        testDebtRegister.addDebt(new Debt("Vehicle", 200000.2, 50000.0, 6.2));
        testDebtRegister.addDebt(new Debt("House", 3000000.5, 10000, 2.8));
        testDebtRegister.addDebt(new Debt("Company", 1200000.1, 55200.6, 4.4));
        testDebtRegister.addDebt(new Debt("House", 500050, 199999.2, 3.7));
        testDebts.add(new Debt("Vehicle", 200000.2, 50000.0, 6.2));
        testDebts.add(new Debt("House", 3000000.5, 10000, 2.8));
        testDebts.add(new Debt("Company", 1200000.1, 55200.6, 4.4));
        testDebts.add(new Debt("House", 500050, 199999.2, 3.7));
    }

    @Test
    @DisplayName("Add new debt")
    public void addDebtTest() {
        testDebtRegister.addDebt(new Debt("House", 500050, 199999.2, 3.7));
        assertEquals(5, testDebtRegister.getNumberOfDebts());
    }

    @Test
    @DisplayName("Get all debts")
    public void getDebtsTest() {
        assertEquals(testDebts.toString(), testDebtRegister.getDebts().toString());
    }

}

