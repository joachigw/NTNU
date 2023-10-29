package edu.ntnu.idatt1002.team20.models.goal;

import edu.ntnu.idatt1002.team20.models.goal.Goal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GoalTest {
    private final Goal testGoal = new Goal("Mat", 3000, 1200);

    @Nested
    class Getters {
        @Test
        public void getGoalTypeTest() {
            assertEquals("Mat", testGoal.getGoalType());
        }

        @Test
        public void getGoalAmountTest() {
            assertEquals(3000, testGoal.getGoalAmount());
        }

        @Test
        public void getAmountSpentTest() {
            assertEquals(1200, testGoal.getAmountSpent());
        }
    }

    @Nested
    class SetGoalAmount {
        @Test
        public void setGoalAmountTest() {
            testGoal.setGoalAmount(3100);
            assertEquals(3100, testGoal.getGoalAmount());
        }

        @Test
        @DisplayName("Tests if setting a negative goal amount throws an exception.")
        public void setGoalAmountExceptionTest() {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> testGoal.setGoalAmount(-1));
            assertEquals("Goal amount must be greater than 0.", exception.getMessage());
        }
    }

    @Nested
    class AddAmountSpent {
        @Test
        public void addAmountSpentTest() {
            testGoal.addAmountSpent(200);
            assertEquals(1400, testGoal.getAmountSpent());
        }

        @Test
        @DisplayName("Tests if adding a negative amount to the amount spent throws an exception.")
        public void addAmountSpentExceptionTest() {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> testGoal.addAmountSpent(-1));
            assertEquals("Amount added must be a positive number.", exception.getMessage());
        }
    }

    @Nested
    class RemoveAmountSpent {
        @Test
        public void removeAmountSpentTest() {
            testGoal.removeAmountSpent(200);
            assertEquals(1000, testGoal.getAmountSpent());
        }

        @Test
        @DisplayName("Tests if removing a negative amount from the amount spent throws an exception.")
        public void removeAmountSpentNegativeInputTest() {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> testGoal.removeAmountSpent(-1));
            assertEquals("Amount removed must be a positive number.", exception.getMessage());
        }

        @Test
        @DisplayName("Tests if removing a amount such that the new amount spent is less than 0 throws an exception.")
        public void removeAmountSpentNewTotalIsNegativeTest() {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> testGoal.removeAmountSpent(1201));
            assertEquals("Amount spent cannot be less than 0.", exception.getMessage());
        }
    }
}
