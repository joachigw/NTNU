package edu.ntnu.idatt1002.team20.models.expense;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Register for storing expenses while the application is running.
 * Includes methods for adding expenses to observable list, removing expenses from observable list,
 * getting observable list and getting total expenses.
 */
public class ExpenseRegister {

  private final ObservableList<Expense> expenseObservableList;


  /**
   * Constructor for expense register.
   * Initializes observable list.
   */
  public ExpenseRegister() {
    expenseObservableList = FXCollections.observableArrayList();
  }


  /**
   * Method to get total expenses.
   * @return Total expenses.
   */
  public double getTotalExpenses() {
    double totalExpenses = 0;
    for (Expense expense : expenseObservableList) {
      totalExpenses += expense.getExpenseAmount();
    }
    return totalExpenses;
  }


  /**
   * Method to add expenses to observable list.
   * @param expenses Expenses to add to observable list.
   */
  public void addExpensesToObservableList(List<Expense> expenses) {
    expenseObservableList.addAll(expenses);
  }


  /**
   * Method to remove expense from observable list.
   * @param expense Expense to remove from observable list.
   */
  public void removeByObject(Expense expense) {
    expenseObservableList.remove(expense);
  }


  /**
   * Method to get expense observable list.
   * @return Expense observable list.
   */
  public ObservableList<Expense> getExpenseObservableList() {
    return expenseObservableList;
  }
}