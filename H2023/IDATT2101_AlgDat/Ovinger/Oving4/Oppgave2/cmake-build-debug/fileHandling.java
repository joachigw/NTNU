package edu.ntnu.idatt2001.mappevurdering.ej;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class fileHandling {

    private String storyTitle;
    private Story fileStory = null;

    private Map<Link, Passage> passageList;
    private Passage openingPassage;
    private Passage currentPassage;
    private Link currentLink;
    private String[] actionData;


    public Map<Link, Passage> readNextPassage(Map<Link, Passage> developedStory, Link chosenLink) throws IOException {
        String search;
        if(chosenLink == null){
            search = "Opening Passage";
        } else {
            search = chosenLink.getReference();
        }
        try(Scanner in = new Scanner(new File("src/main/resources/Scripts/thevalleyofadventures.path"))){
            while(in.hasNextLine()){
                String currentLine = in.nextLine().trim();

                if(currentLine.startsWith("::") && currentLine.contains(search)){
                    String passageTitle = formatPassageTitle(currentLine);

                    StringBuilder passageContent = new StringBuilder();
                    currentLine = in.nextLine().trim();
                    while (!currentLine.startsWith("[")) {
                        passageContent.append(currentLine).append(" ");
                        if (in.hasNextLine()) {
                            currentLine = in.nextLine().trim();
                        } else {
                            break;
                        }
                    }
                    ArrayList<Link> passageLinkList = new ArrayList<>();
                    while (currentLine.startsWith("[")){
                        passageLinkList.add(formatPassageLink(currentLine));
                        if (in.hasNextLine()) {
                            currentLine = in.nextLine().trim();
                        } else {
                            break;
                        }
                    }
                    currentPassage = new Passage(passageTitle, passageContent.toString(), passageLinkList);
                }
            }
            developedStory.put(chosenLink, currentPassage);
        }
        return developedStory;
    }

    private String formatPassageTitle(String currentLine) {
        return currentLine.substring(2);
    }

    private Link formatPassageLink(String currentLine){
        String linkText = null;
        if(currentLine.contains("[")){
            linkText = currentLine.substring(currentLine.indexOf("[")+1, currentLine.indexOf("]"));
        }
        String linkReference = null;
        if(currentLine.contains("(")){
            linkReference = currentLine.substring(currentLine.indexOf("(")+1, currentLine.indexOf(")"));
        }
        actionData = null;
        if(currentLine.contains("{")){
            actionData = (currentLine.substring(currentLine.indexOf("{")+1, currentLine.indexOf("}"))).split(",");
            //ArrayList<String> actionList = new ArrayList<>(Arrays.asList(actionData));
        }
        return new Link(linkText, linkReference);
    }

    public void writeFile(Story story) throws IOException {

    }


    public String getImage(String title) {
        String image;
        if(title.contains("(creature)")){
            image = title.replace("(creature)", "");
            return "pictures/Monsters/" + image + ".png";
        } else {
            return null;
        }
    }

    public Boolean checkImageStory(String s) throws FileNotFoundException {
        try(Scanner in = new Scanner(new File("src/main/resources/Scripts/thevalleyofadventures.path"))){
            while(in.hasNextLine()){
                String currentLine = in.nextLine().trim();
                if(currentLine.contains("//THIS FILE CAN SHOW PICTURES")){
                    return true;
                }
            }
        }
        return false;
    }


    public String getBrokenLinks(String fileName) throws FileNotFoundException {
        Map<String, List<Link>> brokenLinks = new HashMap<>();
        try(Scanner in = new Scanner(new File(fileName))){
            while(in.hasNextLine()){
                String currentLine = in.nextLine().trim();

                if(currentLine.contains("::")){
                    String passageTitle = formatPassageTitle(currentLine);
                    currentLine = in.nextLine().trim();

                    while(!currentLine.contains("[")){
                        currentLine = in.nextLine().trim();
                    }

                    List<Link> brokenLinkList = new ArrayList<>();
                    while (currentLine.startsWith("[")){
                        Link link = formatPassageLink(currentLine);
                        try(Scanner checker = new Scanner(new File(fileName))){
                            brokenLinkList.add(link);
                            while(checker.hasNextLine()){
                                String checkerLine = checker.nextLine().trim();
                                if ((link.getReference() == null
                                        || link.getReference().contains("Previous Passage"))
                                        || (checkerLine.contains("::")
                                        && !checkerLine.contains("[")
                                        && checkerLine.contains(link.getReference()))) {
                                    brokenLinkList.remove(link);
                                    break;
                                }
                            }
                        }
                        if (in.hasNextLine()) {
                            currentLine = in.nextLine().trim();
                        } else {
                            break;
                        }
                    }
                    if(!(brokenLinkList.size() == 0)){
                        brokenLinks.put(passageTitle, brokenLinkList);
                    }
                }
            }
        }
        StringBuilder returnString = new StringBuilder();
        if(!brokenLinks.isEmpty()){
            returnString.append("\nThere are broken links in this story:\n\n");
            brokenLinks.forEach((title, value) ->{
                returnString.append("     ").append(title).append(":");
                for(Link link: value){
                    returnString.append("\n      - ").append(link.toBigString());
                }
                returnString.append("\n\n");
            });
         }
        if(brokenLinks.isEmpty()){
            return ("There are no broken links");
        }
        return returnString.toString();
    }

}