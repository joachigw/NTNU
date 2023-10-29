package edu.ntnu.idatt2001.mappevurdering.ej;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    private final List<String> inventoryTest = new ArrayList<>();
    private Player testPlayer;

    @BeforeEach
    public void setUp() {
        inventoryTest.add("Sword");
        inventoryTest.add("Shield");
        testPlayer = new Player.Builder("Emil")
                .health(100)
                .score(50)
                .gold(25)
                .build();
        testPlayer.addToInventory("Sword");
        testPlayer.addToInventory("Shield");
    }

    @Test
    public void testAttributes(){

        assertEquals("Emil", testPlayer.getName());
        assertEquals(100, testPlayer.getHealth());
        assertEquals(50, testPlayer.getScore());
        assertEquals(25, testPlayer.getGold());
        assertEquals(2, testPlayer.getInventory().size());
        assertEquals(inventoryTest.toString(), testPlayer.getInventory().toString());

    }
    @Test
    public void changeHealthTest(){
        testPlayer.addHealth(-1);
        assertEquals(99, testPlayer.getHealth());
    }
    @Test
    public void changeScoreTest(){
        testPlayer.addScore(-1);
        assertEquals(49, testPlayer.getScore());
    }
    @Test
    public void changeGoldTest(){
        testPlayer.addGold(-1);
        assertEquals(24, testPlayer.getGold());
    }
    @Test
    public void changeInventoryTest(){
        testPlayer.addToInventory("Potion");
        inventoryTest.add("Potion");
        assertEquals(inventoryTest.toString(), testPlayer.getInventory().toString());
    }
}
