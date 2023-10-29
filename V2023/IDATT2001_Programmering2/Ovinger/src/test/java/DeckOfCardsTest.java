import edu.ntnu.idatt2001.oving4joachim.cardgame.DeckOfCards;
import edu.ntnu.idatt2001.oving4joachim.cardgame.PlayingCard;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeckOfCardsTest {

    private final DeckOfCards deckOfCardsTest = new DeckOfCards();
    private final ArrayList<PlayingCard> arrayTest = new ArrayList<>();


    @BeforeEach
    public void setUp() throws FileNotFoundException {
        deckOfCardsTest.addPlayingCards();
        arrayTest.add(new PlayingCard('H', 10, new ImageView(new Image(new FileInputStream("src/main/resources/cards/10H.jpg")))));
        arrayTest.add(new PlayingCard('S', 9, new ImageView(new Image(new FileInputStream("src/main/resources/cards/9S.jpg")))));
        arrayTest.add(new PlayingCard('D', 8, new ImageView(new Image(new FileInputStream("src/main/resources/cards/8D.jpg")))));
    }


    @Test
    @DisplayName("Test size of dealt hand")
    public void dealHandTest() {
        assertTrue(deckOfCardsTest.dealHand(2).size() > 0);
        assertEquals(arrayTest.size(), deckOfCardsTest.dealHand(3).size());
    }

}
