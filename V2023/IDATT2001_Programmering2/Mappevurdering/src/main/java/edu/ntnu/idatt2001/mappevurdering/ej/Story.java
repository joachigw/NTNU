package edu.ntnu.idatt2001.mappevurdering.ej;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Story
{
    private String title;
    private Map<Link, Passage> passages;

    private Link openingLink = new Link("Start Walking", "First Action");

    private List<Link> openingLinkList = new ArrayList<>();
    private Passage openingPassage;

    public Story(String title, Map<Link, Passage> passages)
    {
        this.title = title;
        this.passages = passages;
        //passages.put((new Link(openingPassage.getTitle(), openingPassage.getContent()), openingPassage);
    }

    public String getTitle() {
        return title;
    }

    public Collection<Passage> getPassages()
    {
        return passages.values();
    }
    public Passage getPassage(Link link)
    {
        return passages.get(link);
    }


    public void removePassage(Link link) {
        boolean remove = true;

        for(Passage passage: passages.values()){
            if (passage.getLinks().contains(link)) {
                remove = false;
                break;
            }
        }
        if(remove){
            passages.remove(link);
        }
    }


    @Override
    public String toString() {
        return "Story{" +
                "title='" + title + '\'' +
                ", passages=" + passages +
                ", openingPassage=" + openingPassage +
                '}';
    }
}


/** List<Passages> result = story.getPassages().stream().toLIst();
 * assertEquals(1, result.size()));
 * assertEquals(title, result.get(0).getTitle());
 * assertEquals(content, result.get(0).getContent()));
 */

