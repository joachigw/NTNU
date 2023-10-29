package edu.ntnu.idatt2001.mappevurdering.ej;


import edu.ntnu.idatt2001.mappevurdering.ej.interfaces.action.Action;
import edu.ntnu.idatt2001.mappevurdering.ej.interfaces.action.GoldAction;
import edu.ntnu.idatt2001.mappevurdering.ej.interfaces.action.HealthAction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class StoryTest {

    private List<Action> actionsTest = new ArrayList<>();

    private Link linkTest;
    private List<Link> linksTest = new ArrayList<>();
    private Passage OpeningpassageTest;


    private String title;
    private Map <Link, Passage> passages = new HashMap<Link, Passage>();
    private Story storyTest;

    @Before
    public void setUp(){
        title = "The Dragon Slayer";
        actionsTest.add(new GoldAction(10));
        actionsTest.add(new HealthAction(10));
        linkTest = new Link("Option1", "Passage1");
        linksTest.add(linkTest);
        OpeningpassageTest = new Passage("Title1", "Content1", linksTest);
        this.passages.put(linkTest, OpeningpassageTest);
        this.storyTest = new Story(title, passages);
        //Det blir laget ett object, men den blir aldri lagret i en liste i Story klassen


    }
    @Test
    public void testAttributes(){
        setUp();
        assertEquals(title, storyTest.getTitle());
        assertEquals(OpeningpassageTest, storyTest.getPassages());
        assertEquals(OpeningpassageTest, storyTest.getPassage(linksTest.get(0)));
    }






}
