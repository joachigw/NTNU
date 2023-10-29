package edu.ntnu.idatt2001.mappevurdering.ej.interfaces.goal;

import edu.ntnu.idatt2001.mappevurdering.ej.Player;

public class GoldGoal implements Goal
{
    private final int goldGoal;

    public GoldGoal(int goldGoal)
    {
        this.goldGoal = goldGoal;
    }

    @Override
    public boolean isFulfilled(Player player)
    {
        return true;
    }
}
