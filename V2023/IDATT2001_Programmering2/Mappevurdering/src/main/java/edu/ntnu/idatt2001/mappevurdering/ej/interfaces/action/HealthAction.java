package edu.ntnu.idatt2001.mappevurdering.ej.interfaces.action;

import edu.ntnu.idatt2001.mappevurdering.ej.Player;

public class HealthAction implements Action
{
    int health;

    public HealthAction(int health) {

        if(this.health + health < 0) {
            throw new IllegalArgumentException("The health cannot be reduced to below zero.");
        }
        else {
            this.health = health;
        }
    }

    @Override
    public void execute(Player player)
    {
        player.addHealth(this.health);
    }
}
