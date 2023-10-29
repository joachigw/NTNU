package edu.ntnu.idatt1002.team20.models.goal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Register for storing goals while the application is running.
 */
public class GoalRegister {

    private final Map<String, Goal> goals;


    /**
     * Constructor for GoalRegister class.
     */
    public GoalRegister() {
        goals = new HashMap<>();
    }


    /**
     * Adds a goal to the register.
     *
     * @param goal The goal that will be added.
     * @return The old goal with the same goal type, or null if no such goal was registered.
     */
    public Goal addGoal(Goal goal) {
        return goals.put(goal.getGoalType(), goal);
    }


    /**
     * Adds a list of goals to the register.
     * @param goals The list of goals to be added.
     */
    public void addGoals(List<Goal> goals) {
        if (goals == null) {
            throw new NullPointerException("The list of goals to be added is 'null'.");
        }
        goals.forEach(this::addGoal);
    }


    /**
     * Returns a goal from the register that matches the given goal type.
     * @param goalType The goal type to be matched.
     * @return The goal that matches the given goal type.
     */
    public Goal getGoal(String goalType) {
        return goals.get(goalType);
    }


    /**
     * Returns the map containing all registered goals.
     *
     * @return Map containing all registered goals.
     */
    public Map<String, Goal> getGoals() {
        return goals;
    }

    public List<Goal> getGoalsAsList() {
        List<Goal> goalList = new ArrayList<>();
        goals.forEach((type, goal) -> goalList.add(goal));
        return goalList;
    }


    /**
     * Gets all goals as a list.
     *
     * @return A list containing all registered goals.
     */
    public List<Goal> getGoalList() {
        List<Goal> goalList = new ArrayList<>();
        goals.forEach((type, goal) -> goalList.add(goal));
        return goalList;
    }


    /**
     * Removes a goal from the register.
     *
     * @param goalType The type of the goal that will be removed.
     */
    public void removeGoal(String goalType) {
        goals.remove(goalType);
    }


    /**
     * Gets the total amount spent on all goals.
     *
     * @return The sum of the amount spent on all goals.
     */
    public double getTotalAmountSpent() {
        return getGoalList().stream()
                .map(Goal::getAmountSpent)
                .reduce(0.0, Double::sum);
    }


    /**
     * Gets the sum of all goals.
     *
     * @return The sum of all goal amounts.
     */
    public double getTotalGoalAmount() {
        return getGoalList().stream()
                .map(Goal::getGoalAmount)
                .reduce(0.0, Double::sum);
    }
}