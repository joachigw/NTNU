package edu.ntnu.idatt2001.mappevurdering.ej;

import edu.ntnu.idatt2001.mappevurdering.ej.interfaces.goal.Goal;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    Player player;
    Story story;
    List<Goal> goals;

    public Game(Player player, Story story, List<Goal> goals)
    {
        this.player = player;
        this.story = story;
        this.goals = goals;
    }

    public Player getPlayer()
    {
        return player;
    }

    public Story getStory()
    {
        return story;
    }

    public List<Goal> getGoals()
    {
        return goals;
    }

    public Passage begin()
    {
        return new Passage("test", "test1", new ArrayList<>());
    }

    public Passage go(Link link)
    {
        return new Passage("test2", "test3", new ArrayList<>());
    }
}