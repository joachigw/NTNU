import edu.ntnu.idatt2001.oving4joachim.cardgame.PlayingCard;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayingCardTest {

    // Assures that a possible exception is thrown if needed
    public PlayingCardTest() throws FileNotFoundException {
    }

    private final ImageView imageView = new ImageView(new Image(new FileInputStream("src/main/resources/cards/10H.jpg")));
    private final PlayingCard cardTest = new PlayingCard('H',10, imageView);

    @Test
    @DisplayName("Get as string")
    public void getAsStringTest() {
        assertEquals(String.format("%s%s", 'H', 10), cardTest.getAsString());
    }

    @Test
    @DisplayName("Get suit")
    public void getSuitTest() {
        assertEquals('H', cardTest.getSuit());
    }


    @Test
    @DisplayName("Get face")
    public void getFaceTest() {
        assertEquals(10, cardTest.getFace());
    }


    @Test
    @DisplayName("Get ImageView")
    public void getImageViewTest() {
        assertEquals(imageView, cardTest.getImageView());
    }

}
