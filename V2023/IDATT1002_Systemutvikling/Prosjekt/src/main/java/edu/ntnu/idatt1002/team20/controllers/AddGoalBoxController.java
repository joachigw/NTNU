package edu.ntnu.idatt1002.team20.controllers;

import edu.ntnu.idatt1002.team20.utils.FileHandler;
import edu.ntnu.idatt1002.team20.utils.SceneSwitcher;
import edu.ntnu.idatt1002.team20.models.goal.Goal;
import edu.ntnu.idatt1002.team20.models.goal.GoalRegister;
import edu.ntnu.idatt1002.team20.models.user.CurrentUser;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller for the add goal scene.
 */
public class AddGoalBoxController {

    @FXML
    private TextField newGoalAmountInput;

    @FXML
    private TextField newGoalTypeInput;


    /**
     * Adds a goal and registers it to a file.
     */
    @FXML
    public void addGoal() {
        String goalType = newGoalTypeInput.getText();

        try {
            double goalAmount = Double.parseDouble(newGoalAmountInput.getText());
            String goalFile = CurrentUser.getInstance().getUser().getGoalsFilePath();

            // Checks if the given input is valid
            if (goalType.isEmpty()) {
                SceneSwitcher.showAlert("Please enter a goal type.");
                return;
            }
            if (goalAmount <= 0) {
                SceneSwitcher.showAlert("Please enter a valid goal amount.");
                return;
            }

            GoalRegister updateRegister = FileHandler.readGoals(goalFile);
            updateRegister.addGoal(new Goal(goalType, goalAmount));
            FileHandler.writeGoals(goalFile, updateRegister.getGoalList());
            goalsPage();
        } catch (Exception e) {
            SceneSwitcher.showAlert("Please enter a valid goal amount.");
        }
    }

    /**
     * Switches scene to goal page.
     */
    @FXML
    private void goalsPage() {
        SceneSwitcher.getInstance().switchScene("goalsPage","goals-page");
    }
}
