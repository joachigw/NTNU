package edu.ntnu.idatt2001.mappevurdering.ej.interfaces.goal;

import edu.ntnu.idatt2001.mappevurdering.ej.Player;
import edu.ntnu.idatt2001.mappevurdering.ej.interfaces.action.InventoryAction;

public class InventoryGoal implements Goal
{

    public InventoryGoal ()
    {

    }
    @Override
    public boolean isFulfilled(Player player)
    {
        return true;
    }
}
