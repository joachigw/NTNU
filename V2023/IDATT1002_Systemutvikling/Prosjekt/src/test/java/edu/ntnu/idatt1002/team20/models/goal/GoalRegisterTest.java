package edu.ntnu.idatt1002.team20.models.goal;

import edu.ntnu.idatt1002.team20.models.goal.Goal;
import edu.ntnu.idatt1002.team20.models.goal.GoalRegister;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GoalRegisterTest {
    private final GoalRegister testGoalRegister = new GoalRegister();
    Goal testGoal1 = new Goal("Mat", 4000, 200);
    Goal testGoal2 = new Goal("Mat", 600, 200);

    @Nested
    class AddGoal {
        @Test
        @DisplayName("Checks if adding a new Goal type returns null.")
        void addGoalNewGoalTypeShouldAddAndReturnNull() {
            assertNull(testGoalRegister.addGoal(testGoal1));
            assertEquals(testGoal1, testGoalRegister.getGoal("Mat"));
        }

        @Test
        @DisplayName("Tests if adding a Goal of an existing type returns the old goal.")
        void addGoalExistingGoalTypeShouldAddNewGoalAndReturnOld() {
            testGoalRegister.addGoal(testGoal1);

            assertEquals(testGoal1, testGoalRegister.addGoal(testGoal2));
            assertEquals(testGoal2, testGoalRegister.getGoal("Mat"));
        }
    }

    @Test
    @DisplayName("Tests if getGoal returns the correct Goal.")
    void getGoalShouldReturnGoalOfType() {
        testGoalRegister.addGoal(testGoal1);

        assertEquals(testGoal1, testGoalRegister.getGoal("Mat"));
    }
}