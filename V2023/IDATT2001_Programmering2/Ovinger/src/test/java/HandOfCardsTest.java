import edu.ntnu.idatt2001.oving4joachim.cardgame.DeckOfCards;
import edu.ntnu.idatt2001.oving4joachim.cardgame.HandOfCards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class HandOfCardsTest {

    private final DeckOfCards deckOfCardsTest = new DeckOfCards();
    private HandOfCards handOfCardsTest;


    @BeforeEach
    public void setUp() throws FileNotFoundException {
        deckOfCardsTest.addPlayingCards();
    }


    @Test
    @DisplayName("Get sum of cards in hand")
    public void getSumOfCardsTest() {
        handOfCardsTest = new HandOfCards(deckOfCardsTest.dealHand(1));
        assertTrue(handOfCardsTest.getSumOfCards() > 0);
    }


    @Test
    @DisplayName("Get sum of cards, negative test")
    public void getSumOfCardsTestFalse() {
        handOfCardsTest = new HandOfCards(deckOfCardsTest.dealHand(0));
        assertFalse(handOfCardsTest.getSumOfCards() > 0);
    }

    @Test
    @DisplayName("Check has QoS")
    public void checkHasQueenOfSpadesTest() {
        handOfCardsTest = new HandOfCards(deckOfCardsTest.dealHand(52));
        assertTrue(handOfCardsTest.checkHasQueenOfSpades());
    }


    @Test
    @DisplayName("Check flush condition, negative test")
    public void checkCannotHaveFlush() {
        handOfCardsTest = new HandOfCards(deckOfCardsTest.dealHand(4));
        assertFalse(handOfCardsTest.checkHasFlush());
    }
}
