package edu.ntnu.idatt2001.mappevurdering.ej.interfaces.action;

import edu.ntnu.idatt2001.mappevurdering.ej.Player;

public class InventoryAction implements Action
{
    String inventoryItem;

    public InventoryAction(String inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    @Override
    public void execute(Player player)
    {
        player.addToInventory(this.inventoryItem);
    }
}
