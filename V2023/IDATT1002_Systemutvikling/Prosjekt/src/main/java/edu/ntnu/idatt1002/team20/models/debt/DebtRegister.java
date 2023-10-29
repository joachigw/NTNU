package edu.ntnu.idatt1002.team20.models.debt;

import edu.ntnu.idatt1002.team20.utils.SceneSwitcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for storing debts.
 * Includes methods for adding, removing and getting debts, as well as the amount of debts.
 */
public class DebtRegister {

    private final List<Debt> debts;


    /**
     * Creates a new debt register.
     */
    public DebtRegister() {
        this.debts = new ArrayList<>();
    }


    /**
     * Returns the number of debts in the register.
     * @return the number of debts in the register.
     */
    public int getNumberOfDebts() {
        return debts.size();
    }


    /**
     * Returns a copy of the list of debts.
     * @return a copy of the list of debts.
     */
    public List<Debt> getDebts() {
        return new ArrayList<>(debts);
    }


    /**
     * Returns a debt from the register that matches the given debt type.
     * @param debtType the debt type to be matched.
     * @return the debt that matches the given debt type.
     */
    public Debt getDebtByType(String debtType) {
        for (Debt debt : debts) {
            if (debt.getDebtType().equals(debtType)) {
                return debt;
            }
        }
        return null;
    }


    /**
     * Adds a debt to the register.
     * @param debt the debt to be added.
     */
    public void addDebt(Debt debt) {
        if (debt == null) {
            throw new NullPointerException("The debt to be added is 'null'.");
        }
        if(debts.contains(debt)) {
            SceneSwitcher.showAlert("The debt with this name is already in the register.");
            throw new IllegalArgumentException("The debt with this name is already in the register.");
        }
        debts.add(debt);
    }


    /**
     * Adds a list of debts to the register.
     * @param debts the list of debts to be added.
     */
    public void addDebts(List<Debt> debts) {
        if (debts == null) {
            throw new NullPointerException("The list of debts to be added is 'null'.");
        }
        this.debts.addAll(debts);
    }


    /**
     * Removes a debt from the register.
     * @param debt the debt to be removed.
     */
    public void removeDebt(Debt debt) {
        if (debt == null) {
            throw new IllegalArgumentException("Cannot remove a debt with a 'null' value.");
        }
        debts.remove(debt);
    }


    /**
     * Removes all debts from the register.
     */
    public void clear() {
        debts.clear();
    }


    /**
     * Returns a string representation of the debt register.
     * @return a string representation of the debt register.
     */
    @Override
    public String toString() {
        return "DebtRegister{" +
                "debts=" + debts +
                '}';
    }
}
