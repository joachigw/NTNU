package edu.ntnu.idatt2001.oving4joachim.cardgame;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a hand dealt through the DeckOfCards-class.
 * Includes methods for returning the sum of the hand, all cards of Hearts, whether there is a flush present,
 * or if the Queen of Spades is present or not.
 */
public class HandOfCards {

    private final ArrayList<PlayingCard> handOfCards;
    private int sumOfCards = 0;
    private boolean hasFlush = false;
    private boolean hasQueenOfSpades = false;


    /**
     * Constructor.
     * Represents a hand of cards.
     *
     * @param dealtHand the randomly generated hand that has been dealt.
     */
    public HandOfCards(ArrayList<PlayingCard> dealtHand)
    {
        this.handOfCards = dealtHand;
    }


    /**
     * Used to get the sum of the cards currently in hand.
     *
     * @return int of the sum of the cards.
     */
    public int getSumOfCards()
    {
        for (PlayingCard card : this.handOfCards) {
            this.sumOfCards += card.getFace();
        }

        return this.sumOfCards;
    }


    /**
     * Used to gather up all of the hearts in a hand.
     *
     * @return List of the cards of the suit 'heart'.
     */
    public List<PlayingCard> getHeartsInHand()
    {
        return this.handOfCards.stream().filter(c -> 'H' == c.getSuit()).toList();
    }

    /**
     * Checks whether the current hand includes the Queen of Spades.
     *
     * @return boolean of whether the hand has the Queen of Spades.
     */
    public boolean checkHasQueenOfSpades()
    {
        if (this.handOfCards.stream().anyMatch(qS -> 'S' == qS.getSuit()) &&
                this.handOfCards.stream().anyMatch(qF -> 12 == qF.getFace())) {
            this.hasQueenOfSpades = true;
        }
        return this.hasQueenOfSpades;
    }


    /**
     * Checks whether the current hand has five or more of at least one suit.
     *
     * @return boolean of whether the hand includes a flush.
     */
    public boolean checkHasFlush()
    {
        List<PlayingCard> spades = this.handOfCards.stream().filter(s -> 'S' == s.getSuit()).toList();
        List<PlayingCard> hearts = this.handOfCards.stream().filter(h -> 'H' == h.getSuit()).toList();
        List<PlayingCard> clubs = this.handOfCards.stream().filter(c -> 'C' == c.getSuit()).toList();
        List<PlayingCard> diamonds = this.handOfCards.stream().filter(d -> 'D' == d.getSuit()).toList();

        // Checks whether any of the lists above have a size larger than or equivalent to the requirement for a flush
        if(spades.size() >= 5 || hearts.size() >= 5 || clubs.size() >= 5 || diamonds.size() >=5) {
            this.hasFlush = true;
        }

        return this.hasFlush;
    }
}
