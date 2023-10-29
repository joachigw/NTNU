package edu.ntnu.idatt1002.team20.models.income;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class for storing incomes.
 */
public class IncomeRegister {

    private final HashMap<String, Income> incomes;
    private final ObservableList<Income> incomeObservableList;


    /**
     * Constructor for income register.
     */
    public IncomeRegister() {
        incomes = new HashMap<>();
        incomeObservableList = FXCollections.observableArrayList();
    }

    /**
     * Get method for income.
     *
     * @param type Type given as a string.
     * @return Incomes
     */
    public Income getIncome(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Type can not be empty.");
        }
        return incomes.get(type);
    }

    /**
     *
     * Get method for incomes.txt.
     *
     * @return Values of incomes.txt.
     */
    public List<Income> getIncomes() {
        return new ArrayList<>(incomes.values());
    }


    /**
     * Get method for total income.
     * @return Total income.
     */
    public double getTotalIncome() {
        double totalIncome = 0;
        for (Income income : incomeObservableList) {
            totalIncome += income.getIncomeAmount();
        }
        return totalIncome;
    }


    /**
     * Get method for income observable list.
     * @return Income observable list.
     */
    public ObservableList<Income> getIncomeObservableList() {
        return incomeObservableList;
    }


    /**
     * Method to add income to hashmap.
     *
     * @param income Income.
     */
    public void addIncome(Income income) {
        if (income == null) {
            throw new IllegalArgumentException("The income is empty, and can therefor not be added.");
        }
        String type = income.getIncomeSource();
        incomes.put(type, income);
    }


    /**
     * Method to remove income from observable list.
     * @param income Income to be removed.
     */
    public void removeByObject(Income income) {
        incomes.remove(income.getIncomeSource(), income);
        incomeObservableList.remove(income);
    }


    /**
     * Add all incomes to the observable list.
     * @param incomes List of incomes to be added.
     */
    public void addIncomesToObservableList(List<Income> incomes) {
        incomeObservableList.addAll(incomes);
    }
}
