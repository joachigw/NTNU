package edu.ntnu.idatt2001.oving4joachim.cardgame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents an entire deck of cards, consisting of 52 cards.
 * Includes methods for dealing a random hand of any legitimate, specified size.
 */
public class DeckOfCards {

    private final ArrayList<PlayingCard> deck;


    /**
     * Constructor.
     * Represents a full deck of cards (52 cards).
     */
    public DeckOfCards()
    {
        deck = new ArrayList<>();
    }


    /**
     * Creates all the PlayingCard-objects and adds them to the deck.
     * @throws FileNotFoundException In case a filepath leads to nothing.
     */
    public void addPlayingCards() throws FileNotFoundException
    {
        for (int i = 0; i < 52; i++) {
            // Variables needed to construct PlayingCard-objects
            char cardSuit;
            int cardFace;
            ImageView imageView;
            String filePath;
            String fileName;

            // Holds a list of all the images in the "resources"-folder
            File resources = new File("src/main/resources/cards");
            File[] images = resources.listFiles();
            assert images != null;

            // Creates an ImageView based on the images
            filePath = images[i].getAbsolutePath();
            fileName = images[i].getName();

            imageView = new ImageView(new Image(new FileInputStream(filePath)));
            imageView.setFitWidth(60);
            imageView.setPreserveRatio(true);

            // Retrieves the last character of the filename before the filename extension
            int fileExtensionSeparator = fileName.lastIndexOf('.');
            cardSuit = fileName.charAt(fileExtensionSeparator - 1);

            // Retrieves the face of the cards
            if (fileName.length() == 6){
                cardFace = Character.getNumericValue(fileName.charAt(0));
            } else {
                cardFace = Integer.parseInt(fileName.substring(0, 2));
            }

            deck.add(new PlayingCard(cardSuit, cardFace, imageView));
        }
    }


    /**
     * Deals a hand of a specified size.
     *
     * @param numOfCards Number of cards wanted.
     * @return ArrayList containing all of the cards that have been randomly selected.
     */
    public ArrayList<PlayingCard> dealHand(int numOfCards)
    {
        ArrayList<PlayingCard> deckCopy = new ArrayList<>(deck); // Create a copy of the deck
        ArrayList<PlayingCard> hand = new ArrayList<>();
        Random random = new Random();

        // Deal a hand based on the number of cards specified in the TextField
        for (int i = 0; i < numOfCards; i++) {
            PlayingCard randomCard = deckCopy.get(random.nextInt(0, deckCopy.size()));
            if(!hand.contains(randomCard)) {
                hand.add(randomCard); // Adds the random card to the hand
                deckCopy.remove(randomCard); // Removes the card from the deck, to avoid duplicate call
            }
        }

        return hand;
    }

}
