package edu.ntnu.idatt1002.team20.models.bill;

import java.time.LocalDate;

/**
 * Class for bills.
 * Represents a bill with a due date, category and amount.
 *
 * @author joachigw
 */
public class Bill {

    private final LocalDate dueDate;
    private final String category;
    private final int billId;
    private double amount;
    private boolean paid = false;


    /**
     * Constructor for the Bill class.
     *
     * @param dueDate When the bill is due
     * @param category The category of the bill
     * @param billId The bills id
     * @param amount The price
     */
    public Bill(LocalDate dueDate, String category, int billId, double amount) {
        if (dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("The due date cannot be before today.");
        }
        if (category.isBlank() || category.isEmpty()) {
            throw new IllegalArgumentException("The category cannot be empty.");
        }
        if(amount <= 0) {
            throw new IllegalArgumentException("The amount cannot be less than or equal to zero.");
        }
        this.dueDate = dueDate;
        this.category = category;
        this.billId = billId;
        this.amount = amount;
    }


    /**
     * Returns the due date.
     * @return the due date
     */
    public LocalDate getDueDate() {
        return dueDate;
    }


    /**
     * Returns the category.
     * @return the category
     */
    public String getCategory() {
        return category;
    }


    /**
     * Returns the bill id.
     * @return the bill id
     */
    public int getBillId() {
        return billId;
    }


    /**
     * Returns the amount.
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }


    /**
     * Returns whether the bill is paid or not.
     * @return true if the bill is paid, false otherwise
     */
    public boolean getIsPaid() {
        return paid;
    }


    /**
     * Sets the amount.
     * @param amount the new amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }


    /**
     * Returns whether the bill is paid or not.
     * @return true if the bill is paid, false otherwise
     */
    public boolean isPaid() {
        return paid;
    }


    /**
     * Sets whether the bill is paid or not.
     * @param paid true if the bill is paid, false otherwise
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }


    /**
     * Returns a string representation of the bill.
     * @return a string representation of the bill
     */
    @Override
    public String toString() {
        return "Bill{" +
                "dueDate=" + dueDate +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                '}';
    }
}
