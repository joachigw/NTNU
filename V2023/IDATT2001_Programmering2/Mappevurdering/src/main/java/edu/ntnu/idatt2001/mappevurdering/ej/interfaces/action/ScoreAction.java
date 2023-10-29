package edu.ntnu.idatt2001.mappevurdering.ej.interfaces.action;

import edu.ntnu.idatt2001.mappevurdering.ej.Player;

public class ScoreAction implements Action
{
    int score;

    public ScoreAction(int score){
        this.score = score;
    }
    @Override
    public void execute(Player player)
    {
        player.addScore(this.score);
    }
}
