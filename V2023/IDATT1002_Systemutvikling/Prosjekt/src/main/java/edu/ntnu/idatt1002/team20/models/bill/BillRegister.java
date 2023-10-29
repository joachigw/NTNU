package edu.ntnu.idatt1002.team20.models.bill;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used to store bills.
 * Includes methods for adding and removing bills, retrieving all bills, adding default bills and retrieving the
 * total amount due for all bills combined.
 */
public class BillRegister {

    private final List<Bill> bills;
    private double totalAmount;


    /**
     * Constructor for the BillRegister class.
     */
    public BillRegister() {
        bills = new ArrayList<>();
        totalAmount = 0;
    }


    /**
     * Adds a bill to the register.
     * @param bill the bill to be added.
     */
    public void addBill(Bill bill) {
        bills.add(bill);
        totalAmount += bill.getAmount();
    }


    /**
     * Adds a list of bills to the register and updates the total amount.
     * @param bills the bills to be added.
     */
    public void addBills(List<Bill> bills) {
        for (Bill bill : bills) {
            totalAmount += bill.getAmount();
            this.bills.add(bill);
        }
    }


    /**
     * Removes a bill from the register.
     * @param bill the bill to be removed.
     */
    public void removeBill(Bill bill) {
        bills.remove(bill);
        totalAmount -= bill.getAmount();
    }


    /**
     * Returns a copy of the list of bills.
     * @return a copy of the list of bills.
     */
    public List<Bill> getBills() {
        return new ArrayList<>(bills);
    }


    /**
     * Returns a list of all upcoming bills.
     * @return a list of all upcoming bills.
     */
    public List<Bill> getUpcomingBills() {
        List<Bill> upcomingBills = new ArrayList<>();
        for (Bill bill : bills) {
            if (!bill.isPaid()) {
                upcomingBills.add(bill);
            }
        }
        return upcomingBills;
    }


    /**
     * Returns the next due date for a bill.
     * @return the next due date for a bill.
     */
    public String getNextDueDate() {

        // If there are no bills, return a message
        if(bills.isEmpty()) {
            return "";
        }

        // Finds the next due date by comparing the due date of each bill to each other
        LocalDate nextDueDate = LocalDate.now().plusYears(1);
        for (Bill bill : bills) {
            if (bill.getDueDate().isBefore(nextDueDate) && !bill.isPaid()) {
                nextDueDate = bill.getDueDate();
                if (nextDueDate.isEqual(LocalDate.now())) {
                    return "Today";
                } else if (nextDueDate.isEqual(LocalDate.now().plusDays(1))) {
                    return "Tomorrow";
                }
            }
        }
        return String.valueOf(nextDueDate);
    }


    /**
     * Returns the total amount due for all bills.
     * @return the total amount due for all bills.
     */
    public double getTotalAmount()
    {
        return totalAmount;
    }


    /**
     * Clears the list of bills and resets the total amount due.
     */
    public void clear() {
        bills.clear();
        totalAmount = 0;
    }
}
