package edu.ntnu.idatt1002.team20.models.expense;

import edu.ntnu.idatt1002.team20.models.debt.Debt;
import edu.ntnu.idatt1002.team20.models.goal.Goal;

import java.time.LocalDateTime;
import java.util.Objects;


/**
 * Class for expense.
 * Represents an expense with a source, amount, deadline and linked debt or goal.
 * Methods for manipulating the expenses values and getting the linked debt or goal (or both).
 */
public class Expense {

  private final LocalDateTime expenseDate;
  private final String expenseSource;
  private final Double expenseAmount;
  private final Goal goal;
  private final Debt debt;


  /**
   * Builder-constructor class for Expense.
   *
   * @param builder Builder object.
   */
  private Expense(Builder builder) {

    if (builder.expenseDate == null) {
      throw new IllegalArgumentException("Expense date cannot be null.");
    }
    if (builder.expenseDate.isAfter(LocalDateTime.now())) {
      throw new IllegalArgumentException("Expense date cannot be before today.");
    }
    if(builder.expenseSource == null || builder.expenseSource.isEmpty() || builder.expenseSource.isBlank()) {
      throw new IllegalArgumentException("Expense source cannot be 'null', blank nor empty.");
    }
    if(builder.expenseAmount <= 0) {
      throw new IllegalArgumentException("Expense amount cannot be lower than or equal to zero.");
    }
    this.expenseDate = builder.expenseDate;
    this.expenseSource = builder.expenseSource;
    this.expenseAmount = builder.expenseAmount;
    this.debt = builder.debt;
    this.goal = builder.goal;
  }


  /**
   * Return the LocalDateTime of when the expense was created.
   * @return LocalDateTime of the expense.
   */
  public LocalDateTime getExpenseDate() {
    return expenseDate;
  }


  /**
   * Returns the source of the expense.
   * @return Source of the expense.
   */
  public String getExpenseSource() {
    return expenseSource;
  }


  /**
   * Get method for expense amount.
   * @return Expense amount.
   */
  public double getExpenseAmount() {
    return expenseAmount;
  }


  /**
   * Get method for linked debt.
   * @return Debt.
   */
  public Debt getDebt() {
        return debt;
  }


  /**
   * Get method for linked goal.
   * @return Goal.
   */
  public Goal getGoal() {
        return goal;
  }


  /**
   * Checks if the object is equal to the expense.
   * @param o Object to be compared.
   * @return True if the object is equal to the expense, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Expense expense)) {
      return false;
    }
    return Double.compare(expense.getExpenseAmount(), getExpenseAmount()) == 0
        && Objects.equals(getExpenseSource(), expense.getExpenseSource());
  }


  /**
   * Returns the hash code of the expense.
   * @return Hash code of the expense.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getExpenseSource(), getExpenseAmount());
  }


  /**
   * Returns a string representation of the expense.
   * @return String representation of the expense.
   */
  @Override
  public String toString() {
    return expenseSource + ": " + expenseAmount;
  }


  /**
   * Builder class for expense.
   * Used to create an expense object with required and optional parameters.
   * Required parameters are expenseDateTime, expenseSource and expenseAmount.
   * Optional parameters are objects of type Deadline, Goal and Debt.
   */
  public static class Builder {
    //Required params
    public LocalDateTime expenseDate;
    private final String expenseSource;
    private final Double expenseAmount;

    //Optional params - default set as null
    private Goal goal = null;
    private Debt debt = null;

    public Builder(LocalDateTime expenseDate, String expenseSource, double expenseAmount) {
      this.expenseDate = expenseDate;
      this.expenseSource = expenseSource;
      this.expenseAmount = expenseAmount;
    }

    public Builder goal(Goal goal) {
      this.goal = goal;
      return this;
    }

    public Builder debt(Debt debt) {
      this.debt = debt;
      return this;
    }

    public Expense build() {
      return new Expense(this);
    }
  }
}
