package edu.ntnu.idatt2001.mappevurdering.ej;

import edu.ntnu.idatt2001.mappevurdering.ej.interfaces.action.Action;
import edu.ntnu.idatt2001.mappevurdering.ej.interfaces.action.GoldAction;
import edu.ntnu.idatt2001.mappevurdering.ej.interfaces.action.InventoryAction;
import edu.ntnu.idatt2001.mappevurdering.ej.interfaces.action.ScoreAction;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LinkTest {

    private Link testLink = new Link("Option1", "Passage1");
    private List<Action> actionsTest = testLink.getActions();

    @Before
    public void setUp()
    {
        testLink.addAction(new GoldAction(5));
        actionsTest.add(new InventoryAction("Item1"));
    }

    @Test
    @DisplayName("Test get text")
    public void testGetText()
    {
        assertEquals("Option1", testLink.getText());
    }

    @Test
    @DisplayName("Test get reference")
    public void testGetReference()
    {
        assertEquals("Passage1", testLink.getReference());
    }

    @Test
    @DisplayName("Test get actions")
    public void testGetActions()
    {
        assertEquals(actionsTest, testLink.getActions());
    }

    @Test
    @DisplayName("Test empty Action-list")
    public void testGetActionsFalse()
    {
        actionsTest.clear();
        assertFalse(testLink.getActions().size() > 0);
    }

    @Test
    @DisplayName("Test add new Action")
    public void testAddAction()
    {
        assertTrue(testLink.addAction(new ScoreAction(5)));
    }

    @Test
    @DisplayName("Test the equals comparison")
    public void testEquals()
    {
        Link testLink2 = new Link("Option1", "Passage1");
        Link testLink3 = testLink;
        assertEquals(testLink, testLink2);
        assertEquals(testLink, testLink3);
    }

    @Test
    @DisplayName("Test the equals comparison")
    public void testEqualsFalse()
    {
        Link testLink4 = new Link("Option2", "Passage2");
        assertNotEquals(testLink, testLink4);
    }

    @Test
    @DisplayName("Test if hashCode() returns int")
    public void testHashCode()
    {
        assertInstanceOf(Integer.class, testLink.hashCode());
        assertTrue(testLink.hashCode() > Integer.MIN_VALUE && testLink.hashCode() < Integer.MAX_VALUE);
    }

    @Test
    @DisplayName("Test the Link toString()")
    public void testToString()
    {
        assertEquals("Link{" +
                "text='" + testLink.getText() + '\'' +
                ", reference='" + testLink.getReference() + '\'' +
                ", actions=" + testLink.getActions() +
                "}", testLink.toString());
    }
}
