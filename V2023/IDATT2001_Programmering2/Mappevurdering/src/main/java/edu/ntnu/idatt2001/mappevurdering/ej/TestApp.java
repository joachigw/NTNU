package edu.ntnu.idatt2001.mappevurdering.ej;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class TestApp {

    private static final Scanner in = new Scanner(System.in);

    protected static Passage previousPassage;
    protected static Passage currentPassage;

    private static Story currentStory = new Story(null, null);

    private static Map<Link, Passage> road = new HashMap<>();

    private static final fileHandling reader = new fileHandling();

    public static void main(String[] args) throws IOException {

        newStory();
        //System.out.println("Broken links: " + currentStory.getBrokenLinks().toString());
        while (currentPassage != null){
            System.out.println(revealScene(currentPassage));
            manageChoices();
            System.out.println(currentStory.getPassages().size());
        }

    }

    private static void newStory() throws IOException {
        System.out.print("What shall the story be called?\n- ");
        String title = in.nextLine();
        road = reader.readNextPassage(road, null);
        currentStory = new Story(title, road);
        currentPassage = currentStory.getPassage(null);
    }

    public static void manageChoices() throws IOException {
        if(currentPassage.getLinks().toString().contains("end story")){
            System.exit(0);
        }
        System.out.println("Choices: ");
        for(Link link: currentPassage.getLinks()){
            System.out.println(link.getText());
        }
        System.out.print("\nPick a number between 1 and " + currentPassage.getLinks().size() + "\n- ");
        int key = in.nextInt()-1;
        Link currentLink = currentPassage.getLinks().get(key);
        if(currentLink.getReference().contains("Previous Passage")){
            previousPassage.setContent("You return to the divided path.\nWhat do you choose now?\n");
            currentPassage = previousPassage;
        } else if(!(currentLink.getReference() == null) && !currentLink.getReference().contains("Previous Passage")){
            previousPassage = currentPassage;
            road = reader.readNextPassage(road, currentLink);
            currentPassage = road.get(currentLink);
        } 
    }
    
    private static String revealScene(Passage scene) {
        return "\nTitle: " + scene.getTitle() + "\n" +
                "\n" + scene.getContent();
    }

}
