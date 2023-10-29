package edu.ntnu.idatt1002.team20.models.goal;

import javafx.scene.control.ProgressBar;

/**
 * Class representing a goal.
 */
public class Goal {

    private final String goalType;
    private double goalAmount;
    private double amountSpent;

    /**
     * Constructor for Goal class.
     *
     * @param goalType The type of the goal.
     * @param goalAmount The amount you are trying to spend less than.
     * @param amountSpent The amount you have currently spent.
     * @throws IllegalArgumentException If the goalType is not specified,
     * the goalAmount is less or equal to 0, or the amount spent is less than 0.
     */
    public Goal(String goalType, double goalAmount, double amountSpent) {
        if (goalType == null || goalType.isBlank() || goalType.isEmpty()) {
            throw new IllegalArgumentException("Goal type must be specified.");
        }
        if (goalAmount <= 0) {
            throw new IllegalArgumentException("Goal amount must be greater than 0.");
        }
        if (amountSpent < 0) {
            throw new IllegalArgumentException("Amount spent cannot be less than 0.");
        }

        this.goalType = goalType;
        this.goalAmount = goalAmount;
        this.amountSpent = amountSpent;
    }


    /**
     * Constructor for goals with no amountSpent.
     *
     * @param goalType The type of the goal.
     * @param goalAmount The amount you are trying to spend less than.
     */
    public Goal(String goalType, double goalAmount) {
        this(goalType, goalAmount, 0);
    }


    /**
     * Gets the type of the goal.
     *
     * @return The type of the goal.
     */
    public String getGoalType() {
        return goalType;
    }


    /**
     * Gets the goal amount.
     *
     * @return The goal amount.
     */
    public double getGoalAmount() {
        return goalAmount;
    }


    /**
     * Gets the amount spent.
     *
     * @return The amount spent.
     */
    public double getAmountSpent() {
        return amountSpent;
    }


    /**
     * Sets a new value for the goal amount.
     *
     * @param goalAmount The new goal amount.
     * @throws IllegalArgumentException If the new goal amount is less or equal to 0,
     * or less than the current amount spent.
     */
    public void setGoalAmount(double goalAmount) {
        if (goalAmount <= 0) {
            throw new IllegalArgumentException("Goal amount must be greater than 0.");
        }

        this.goalAmount = goalAmount;
    }


    /**
     * Adds an amount to the amount spent.
     *
     * @param addedAmount The added amount.
     * @throws IllegalArgumentException If the added amount is less than 0.
     */
    public void addAmountSpent(double addedAmount) {
        if (addedAmount < 0) {
            throw new IllegalArgumentException("Amount added must be a positive number.");
        }

        amountSpent += addedAmount;
    }


    /**
     * Removes an amount from the amount spent.
     *
     * @param removedAmount The removed amount.
     * @throws IllegalArgumentException If the removed amount is less than 0,
     * or the removed amount is higher than the amount spent.
     */
    public void removeAmountSpent(double removedAmount) {
        if (removedAmount < 0) {
            throw new IllegalArgumentException("Amount removed must be a positive number.");
        }
        if (amountSpent < removedAmount) {
            throw new IllegalArgumentException("Amount spent cannot be less than 0.");
        }

        amountSpent -= removedAmount;
    }


    /**
     * Gets a progressbar depicting how much of the goal amount is spent.
     *
     * @return The goals progressbar.
     */
    public ProgressBar getProgressBar() {
        return new ProgressBar(amountSpent / goalAmount);
    }


    /**
     * Parses a goal from a string.
     *
     * @param str The string to parse.
     * @return The parsed goal.
     */
    public static Goal parse(String str) {
        String[] parts = str.substring(str.indexOf('{') + 1, str.lastIndexOf('}')).split(", ");
        String goalType = parts[0].split("=")[1].replaceAll("'", "");
        double goalAmount = Double.parseDouble(parts[1].split("=")[1]);
        double amountSpent = Double.parseDouble(parts[2].split("=")[1]);

        return new Goal(goalType, goalAmount, amountSpent);
    }


    /**
     * To string method for Goal.
     *
     * @return To string, consisting of goal type, goal amount and amount spent.
     */
    @Override
    public String toString() {
        return "Goal{" +
                "goalType='" + goalType + '\'' +
                ", goalAmount=" + goalAmount +
                ", amountSpent=" + amountSpent +
                '}';
    }
}