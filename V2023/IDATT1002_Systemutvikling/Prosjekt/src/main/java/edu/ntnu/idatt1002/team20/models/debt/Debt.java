package edu.ntnu.idatt1002.team20.models.debt;

import edu.ntnu.idatt1002.team20.utils.SceneSwitcher;
import javafx.scene.control.ProgressBar;

/**
 * Class for the Debt object.
 * Represents a debt registered by the user.
 * Includes methods for getting and setting the debt type, total amount, amount paid and interest rate,
 * as well as a method for showing the progress of the debt through a progress bar.
 */
public class Debt {

    private String debtType;
    private double totalAmount;
    private double amountPaid;
    private double interestRate;


    /**
     * Constructor for the Debt class.
     * @param debtType the type of debt
     * @param totalAmount the total amount of the debt
     * @param amountPaid the amount paid on the debt
     * @param interestRate the interest rate of the debt
     */
    public Debt(String debtType, double totalAmount, double amountPaid, double interestRate) {
        if(debtType == null || debtType.isEmpty() || debtType.isBlank()) {
            throw new IllegalArgumentException("A debt type must be specified.");
        }
        if (totalAmount <= 0 ) {
            throw new IllegalArgumentException("The debt amount must be greater than zero.");
        }
        if(amountPaid > totalAmount) {
            throw new IllegalArgumentException("The amount paid cannot exceed the total debt amount.");
        }
        if(amountPaid < 0) {
            throw new IllegalArgumentException("The amount paid cannot be less than zero.");
        }
        if (interestRate < 0) {
            throw new IllegalArgumentException("The interest rate cannot be less than or equal to zero.");
        }
        this.debtType = debtType;
        this.totalAmount = totalAmount;
        this.amountPaid = amountPaid;
        this.interestRate = interestRate;
    }


    /**
     * Constructor for the Debt class.
     * Used as a display for the top of ComboBoxes in the AddExpenseController.
     * @param name the name of the debt.
     */
    public Debt (String name) {
        this.debtType = name;
    }


    /**
     * Returns the type of the debt.
     * @return the type of the debt.
     */
    public String getDebtType() {
        return debtType;
    }


    /**
     * Returns the total amount of the debt.
     * @return the total amount of the debt.
     */
    public double getTotalAmount() {
        return totalAmount;
    }


    /**
     * Returns the amount paid of the debt.
     * @return the amount paid of the debt.
     */
    public double getAmountPaid() {
        return amountPaid;
    }


    /**
     * Returns the interest rate of the debt.
     * @return the interest rate of the debt.
     */
    public double getInterestRate() {
        return interestRate;
    }


    /**
     * Sets the type of the debt.
     * @param debtType the type of the debt.
     */
    public void setDebtType(String debtType) {
        if(debtType == null || debtType.isEmpty() || debtType.isBlank()) {
            SceneSwitcher.showAlert("A debt type must be specified.");
            throw new IllegalArgumentException("A debt type must be specified.");
        }
        this.debtType = debtType;
    }


    /**
     * Sets the total amount of the debt.
     * @param totalAmount the total amount of the debt.
     */
    public void setTotalAmount(double totalAmount) {
        if(totalAmount <= 0) {
            SceneSwitcher.showAlert("The total amount cannot be less than or equal to zero.\nPlease enter a valid amount.");
            throw new IllegalArgumentException("The total amount cannot be less than or equal to zero.");
        }
        if(totalAmount < amountPaid) {
            SceneSwitcher.showAlert("The total amount cannot be less than the amount paid.");
            throw new IllegalArgumentException("The total amount cannot be less than the amount paid.");
        }
        this.totalAmount = totalAmount;
    }


    /**
     * Sets the amount paid of the debt.
     * @param amountPaid the amount paid of the debt.
     */
    public void setAmountPaid(double amountPaid) {
        if(amountPaid > totalAmount) {
            SceneSwitcher.showAlert("The amount paid cannot exceed the total debt amount.");
            throw new IllegalArgumentException("The amount paid cannot exceed the total debt amount.");
        }
        if(amountPaid < 0) {
            SceneSwitcher.showAlert("The amount paid cannot be less than zero.");
            throw new IllegalArgumentException("The amount paid cannot be less than zero.");
        }
        this.amountPaid = amountPaid;
    }


    /**
     * Sets the interest rate of the debt.
     * @param interestRate the interest rate of the debt.
     */
    public void setInterestRate(double interestRate) {
        if (interestRate < 0) {
            SceneSwitcher.showAlert("The interest rate cannot be less than zero.");
            throw new IllegalArgumentException("The interest rate cannot be less than zero.");
        }
        this.interestRate = interestRate;
    }


    /**
     * Adds the specified amount to the amount paid.
     * @param amount the amount to be added to the amount paid.
     */
    public void addAmountPaid(double amount) {
        if(amount < 0) {
            SceneSwitcher.showAlert("The amount to be added cannot be less than zero.");
            throw new IllegalArgumentException("The amount to be added cannot be less than zero.");
        }
        if(amount + this.amountPaid > totalAmount) {
            SceneSwitcher.showAlert("The amount paid cannot exceed the total debt amount.");
            throw new IllegalArgumentException("The amount paid cannot exceed the total debt amount.");
        }
        amountPaid += amount;
    }


    /**
     * Subtracts the specified amount from the amount paid.
     * @param amount the amount to be subtracted from the amount paid.
     */
    public void subtractAmountPaid(double amount) {
        if(amount < 0) {
            SceneSwitcher.showAlert("The amount to be subtracted cannot be less than zero.");
            throw new IllegalArgumentException("The amount to be subtracted cannot be less than zero.");
        }
        if(amountPaid - amount < 0) {
            SceneSwitcher.showAlert("The amount paid cannot be less than zero.");
            throw new IllegalArgumentException("The amount paid cannot be less than zero.");
        }
        amountPaid -= amount;
    }


    /**
     * Returns the progress bar of the debt.
     * @return the progress bar of the debt.
     */
    public ProgressBar getProgressBar() {
        return new ProgressBar(amountPaid / totalAmount);
    }


    /**
     * Parse a debt from a string.
     * @param str the string to be parsed.
     * @return the debt parsed from the string.
     */
    public static Debt parse(String str) {
        String[] parts = str.substring(str.indexOf('{') + 1, str.lastIndexOf('}')).split(", ");
        String debtType = parts[0].split("=")[1].replaceAll("'", "");
        double totalAmount = Double.parseDouble(parts[1].split("=")[1]);
        double amountPaid = Double.parseDouble(parts[2].split("=")[1]);
        double interestRate = Double.parseDouble(parts[3].split("=")[1]);

        return new Debt(debtType, totalAmount, amountPaid, interestRate);
    }


    /**
     * Returns a string representation of the debt.
     * @return a string representation of the debt.
     */
    @Override
    public String toString() {
        return "Debt{" +
                "debtType='" + debtType + '\'' +
                ", totalAmount=" + totalAmount +
                ", amountPaid=" + amountPaid +
                ", interestRate=" + interestRate +
                '}';
    }
}
