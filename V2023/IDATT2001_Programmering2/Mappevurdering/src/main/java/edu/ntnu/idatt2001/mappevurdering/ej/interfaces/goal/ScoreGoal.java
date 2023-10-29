package edu.ntnu.idatt2001.mappevurdering.ej.interfaces.goal;

import edu.ntnu.idatt2001.mappevurdering.ej.Player;

public class ScoreGoal implements Goal
{

    private final int scoreGoal;

    public ScoreGoal (int scoreGoal)
    {
        this.scoreGoal = scoreGoal;
    }

    @Override
    public boolean isFulfilled(Player player)
    {
        return true;
    }
}
