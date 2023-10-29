package edu.ntnu.idatt2001.mappevurdering.ej;

import edu.ntnu.idatt2001.mappevurdering.ej.interfaces.action.Action;
import edu.ntnu.idatt2001.mappevurdering.ej.interfaces.action.GoldAction;
import edu.ntnu.idatt2001.mappevurdering.ej.interfaces.action.HealthAction;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PassageTest {

    private List<Link> linksTest = new ArrayList<>();
    private List<Action> actionsTest = new ArrayList<>();
    private Passage passageTest = new Passage("Title1", "Content1", linksTest);

    @Before
    public void setUp()
    {
        actionsTest.add(new GoldAction(5));
        actionsTest.add(new HealthAction(50));
        assertTrue(passageTest.addLink(new Link("Option1", "Passage1")));
    }

    @Test
    @DisplayName("Test all Passage attributes")
    public void testPassageAttributes()
    {
        linksTest.add(new Link("Option1", "Passage1"));

        assertEquals("Title1", passageTest.getTitle());
        assertEquals("Content1", passageTest.getContent());
        assertEquals("[Link{" +
                "text='" + linksTest.get(0).getText() + '\'' +
                ", reference='" + linksTest.get(0).getReference() + '\'' +
                ", actions=" + linksTest.get(0).getActions() +
                "}]", passageTest.getLinks().toString());

    }

    @Test
    @DisplayName("Test return of Passage links")
    public void testGetLinks()
    {
        assertEquals(linksTest, passageTest.getLinks());
    }

    @Test
    @DisplayName("Test whether the Passage has link(s)")
    public void testHasLinks()
    {
        assertEquals(linksTest.size() > 0, passageTest.hasLinks());
    }

    @Test
    @DisplayName("Test empty Links-list")
    public void testHasNoLinks()
    {
        linksTest.clear();
        assertFalse(passageTest.hasLinks());
    }

    @Test
    @DisplayName("Test the equals comparison")
    public void testEquals()
    {
        Passage passageTest2 = new Passage("Title1", "Content1", linksTest);
        Passage passageTest3 = passageTest;
        assertEquals(passageTest, passageTest2);
        assertEquals(passageTest, passageTest3);
    }

    @Test
    @DisplayName("Test the equals comparison")
    public void testEqualsFalse()
    {
        Passage passageTest4 = new Passage("Title2", "Content2", linksTest);
        assertNotEquals(passageTest, passageTest4);
    }

    @Test
    @DisplayName("Test if hashCode() returns int")
    public void testHashCode()
    {
        assertInstanceOf(Integer.class, passageTest.hashCode());
        assertTrue(passageTest.hashCode() > Integer.MIN_VALUE && passageTest.hashCode() < Integer.MAX_VALUE);
    }

    @Test
    @DisplayName("Test the Passage toString()")
    public void testToString()
    {
        assertEquals("Passage{" +
                    "title='" + passageTest.getTitle() + '\'' +
                    ", content='" + passageTest.getContent() + '\'' +
                    ", links=" + passageTest.getLinks() +
                    "}", passageTest.toString());
    }

}
