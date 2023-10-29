package edu.ntnu.idatt1002.team20.models.income;

import java.time.LocalDateTime;

/**
 * Represents an income.
 */
public class Income {

    private final LocalDateTime incomeDate;
    private final String incomeSource;
    private double incomeAmount;


    /**
     * Constructor for income.

     * @param incomeSource The source of the income.
     * @param incomeAmount The income amount.
     */
    public Income(LocalDateTime date, String incomeSource, double incomeAmount) {
        if (date == null) {
            throw new IllegalArgumentException("Date can not be null.");
        }
        if (date.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Date can not be before today.");
        }
        if (incomeSource == null || incomeSource.isBlank() || incomeSource.isEmpty()) {
            throw new IllegalArgumentException("Income source can not be 'null', blank nor empty.");
        }
        if (incomeAmount <= 0) {
            throw new IllegalArgumentException("Income amount has to be greater than 0.");
        }
        this.incomeDate = date;
        this.incomeSource = incomeSource;
        this.incomeAmount = incomeAmount
        ;
    }


    /**
     * Get method for income datetime.

     * @return Income datetime.
     */
    public LocalDateTime getIncomeDate() {
        return incomeDate;
    }


    /**
     * Get method for income source.

     * @return Income source.
     */
    public String getIncomeSource() {
        return incomeSource;
    }


    /**
     * Get method for income amount.

     * @return Income amount.
     */
    public double getIncomeAmount() {
        return incomeAmount;
    }


    /**
     * Set method to set new income amount.

     * @param newIncomeAmount The new income amount.
     */
    public void setIncomeAmount(double newIncomeAmount) {
        if (newIncomeAmount <= 0) {
            throw new IllegalArgumentException("Income amount has to be greater than 0.");
        }
        this.incomeAmount = newIncomeAmount;
    }


    /**
     * To string method for income.
     *
     * @return To string, consisting of income source and income amount.
     */
    @Override
    public String toString() {
        return "Income" + "\n" +
                "Income source: " + incomeSource + "\n" +
                "Income amount: " + incomeAmount;
    }
}