package edu.ntnu.idatt2001.mappevurdering.ej;

import edu.ntnu.idatt2001.mappevurdering.ej.interfaces.action.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Link
{
    private final String text;
    private final String reference;
    private List<Action> actions;

    public Link(String text, String reference)
    {
        this.text = text;
        this.reference = reference;
        this.actions = new ArrayList<>();
    }

    public String getText() {
        return text;
    }

    public String getReference() {
        return reference;
    }

    public List<Action> getActions() {
        return actions;
    }

    public boolean addAction(Action action)
    {
        return actions.add(action);
    }


    @Override
    public String toString()
    {
        return text;
    }

    public String toBigString(){
        return  "Link: [ [" + text + "] (" + reference + ") {" + actions + "} ]";
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return text.equals(link.text) && reference.equals(link.reference) && actions.equals(link.actions);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(text, reference, actions);
    }
}
