package edu.ntnu.idatt2001.mappevurdering.ej;

import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Objects;

public class Passage
{
    String title;
    String content;
    List<Link> links;

    public Passage(String title, String content, List<Link> links)
    {
        this.title = title;
        this.content = content;
        this.links = links;
    }
    //Skal vi sette opp list som empty, slik at vi bare kan sette opp en passage med title og content.

    public String getTitle() {
        return title;
    }
    public void setTitle(String newTitle){
        this.title = newTitle;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String newContent){
        this.content = newContent;
    }

    public List<Link> getLinks()
    {
        return links;
    }

    public boolean addLink(Link link)
    {
        return this.links.add(link);
    }

    public boolean hasLinks()
    {
        return links.size() > 0;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passage passage = (Passage) o;
        return title.equals(passage.title) && content.equals(passage.content) && links.equals(passage.links);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(title, content, links);
    }

    @Override
    public String toString()
    {
        return "Passage{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", links=" + links +
                '}' + "\n";
    }

    //public Link getLinksGUI() {}
}
