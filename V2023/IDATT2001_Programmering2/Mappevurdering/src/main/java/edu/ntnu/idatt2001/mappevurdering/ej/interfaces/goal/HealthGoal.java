package edu.ntnu.idatt2001.mappevurdering.ej.interfaces.goal;

import edu.ntnu.idatt2001.mappevurdering.ej.Player;

public class HealthGoal implements Goal
{
    private final int healthGoal;

    public HealthGoal(int healthGoal)
    {
        this.healthGoal = healthGoal;
    }

    @Override
    public boolean isFulfilled(Player player)
    {
        return true;
    }
}
