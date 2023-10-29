package edu.ntnu.idatt2001.mappevurdering.ej;

import java.util.ArrayList;
import java.util.List;

public class Player
{
    private final String name;
    private int health;
    private int score;
    private int gold;
    private final List<String> inventory;

    public static class Builder
    {
        //required parameters
        private final String name;

        //optional parameters
        private int health = 100;
        private int score = 0;
        private int gold = 0;

        public Builder(String name) {
            this.name = name;
        }

        public Builder health(int value) {
            this.health = value;
            return this;
        }

        public Builder score(int value) {
            this.score = value;
            return this;
        }

        public Builder gold(int value) {
            this.gold = value;
            return this;
        }

        public Player build() {
            return new Player(this);
        }

    }

    private Player(Builder builder) {
        name = builder.name;
        health = builder.health;
        score = builder.score;
        gold = builder.gold;
        inventory = new ArrayList<>();
    }

    /*
    public Player(String name, int health, int score, int gold, List<String> inventory)
    {
        this.name = name;
        this.health = health;
        this.score = score;
        this.gold = gold;
        this.inventory = inventory;
    }
    */

    public String getName()
    {
        return name;
    }

    public int getHealth()
    {
        return health;
    }

    public int getScore()
    {
        return score;
    }

    public int getGold()
    {
        return gold;
    }

    public List<String> getInventory()
    {
        return inventory;
    }

    public void addHealth(int health)
    {
        this.health += health;
    }

    public void addScore(int score)
    {
        this.score += score;
    }

    public void addGold(int gold)
    {
        this.gold += gold;
    }

    public void addToInventory(String item)
    {
        this.inventory.add(item);
    }
}
