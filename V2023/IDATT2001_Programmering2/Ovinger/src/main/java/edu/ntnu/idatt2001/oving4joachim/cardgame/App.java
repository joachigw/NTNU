package edu.ntnu.idatt2001.oving4joachim.cardgame;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    /**
     * Main method.
     * Used to launch the stage used to visualize the programs contents.
     * @param args Written code.
     */
    public static void main(String[] args)
    {
        launch(args);
    }


    /**
     * Initializes the scene and the stage used to visualize the contents.
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws Exception If encountered.
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("IDATT2001 A4: Card game");

        DeckOfCards deckOfCards = new DeckOfCards();
        deckOfCards.addPlayingCards(); // Fills the deck with all 52 cards

        // Pane initializations
        VBox root = new VBox();
        GridPane gridPane = new GridPane();
        VBox controlsAndInfo = new VBox();
        GridPane infoGridPane = new GridPane();
        TilePane handPane  = new TilePane();

        // Node initializations
        Label programTitle = new Label("Card game! \uD83C\uDCCF");
        Label handTitle = new Label("Current hand:");
        Label controlsAndInfoTitle = new Label("Enter amount of cards:");
        TextField amountOfCardsInput = new TextField();
        Button drawBtn = new Button("Draw");
        Label sumOfCardsTitle = new Label("Sum of faces:");
        Label allHeartsTitle = new Label("Cards of heart-suit:");
        Label flushYesNoTitle = new Label("Flush:");
        Label queenOfSpadesYesNoTitle = new Label("Queen of spades:");
        Text sumOfCards = new Text();
        Text allHearts = new Text();
        Text flushYesNo = new Text();
        Text queenOfSpadesYesNo = new Text();


        // Property adjustments of panes
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(5, 10, 10, 10));
        controlsAndInfo.setSpacing(10);
        controlsAndInfo.setPrefWidth(400);
        controlsAndInfo.setAlignment(Pos.TOP_LEFT);
        handPane.setMinHeight(84.5); // Height of a card-image
        handPane.setMinWidth(300); // Width to fit 5 cards in a default TilePane row (5 tiles per row)
        handPane.setBackground(new Background(new BackgroundFill(Color.GRAY, new CornerRadii(5), Insets.EMPTY)));

        // Property adjustments of nodes
        GridPane.setHalignment(controlsAndInfoTitle, HPos.LEFT);
        GridPane.setHalignment(handTitle, HPos.CENTER);
        programTitle.setStyle("-fx-font-weight: BOLD; -fx-font-size: 18");
        controlsAndInfoTitle.setStyle("-fx-font-weight: BOLD; -fx-font-size: 12");
        handTitle.setStyle("-fx-font-weight: BOLD; -fx-font-size: 12");
        sumOfCardsTitle.setStyle("-fx-font-weight: BOLD");
        allHeartsTitle.setStyle("-fx-font-weight: BOLD");
        flushYesNoTitle.setStyle("-fx-font-weight: BOLD");
        queenOfSpadesYesNoTitle.setStyle("-fx-font-weight: BOLD");

        // Creation of a TextField for number input and a button for re-dealing a hand
        addAmountOfCardsInput(amountOfCardsInput,controlsAndInfo);
        addDrawButton(drawBtn, controlsAndInfo);

        // Button on-click event
        drawBtn.setOnAction(actionEvent -> {
            drawBtnOnClick(stage, amountOfCardsInput, handPane, deckOfCards, sumOfCards, allHearts, flushYesNo, queenOfSpadesYesNo);
        });

        // Pane and node interconnections
        infoGridPane.add(sumOfCardsTitle, 0, 0);
        infoGridPane.add(sumOfCards, 1, 0);
        infoGridPane.add(allHeartsTitle, 0, 1);
        infoGridPane.add(allHearts, 1, 1);
        infoGridPane.add(flushYesNoTitle, 0, 2);
        infoGridPane.add(flushYesNo, 1, 2);
        infoGridPane.add(queenOfSpadesYesNoTitle, 0, 3);
        infoGridPane.add(queenOfSpadesYesNo, 1, 3);
        controlsAndInfo.getChildren().add(infoGridPane);
        gridPane.add(controlsAndInfoTitle, 0, 0);
        gridPane.add(controlsAndInfo, 0, 1);
        gridPane.add(handTitle, 1, 0);
        gridPane.add(handPane, 1, 1);
        root.getChildren().addAll(programTitle, gridPane);

        // Scene creation and stage-visualization
        stage.setScene(new Scene(root));
        stage.show();
    }


    /**
     * Adds a Button to a specified Pane.
     * Also used to modify the properties of the Button.
     *
     * @param btn The initialized Button to add.
     * @param pane The Pane which will receive the specified Button.
     */
    public void addDrawButton(Button btn, Pane pane)
    {
        btn.setPrefWidth(130);
        pane.getChildren().add(btn);
    }


    /**
     * Adds a TextField node to a specified Pane.
     * Also used to modify the properties of the TextField.
     *
     * @param field The initialized TextField to add.
     * @param pane The Pane which will receive the specified TextField.
     */
    public void addAmountOfCardsInput(TextField field, Pane pane)
    {
        field.setPrefWidth(130);
        field.setMaxWidth(130);
        field.setPromptText("Amount of cards ([1-52])");
        pane.getChildren().add(field);
    }


    /**
     * Displays the dealt hand retreived from DeckOfCards' 'dealHand'
     *
     * @param pane                      Pane to display the images in.
     * @param deck                      DeckOfCards class.
     * @param numOfCards                Entered number of cards to display.
     * @param sumOfCards                Text to display the sum of the hand.
     * @param allHearts                 Text to display the hearts in the hand.
     * @param flushYesNo                Text to display whether the hand has a flush or not.
     * @param queenOfSpadesYesNo        Text to display whether the hand includes the Queen of Spades.
     * @throws IllegalArgumentException If the specified amount is less than 1 or larger than 52.
     */
    public void showHandAndInfo(Pane pane, DeckOfCards deck, int numOfCards, Text sumOfCards, Text allHearts,
                         Text flushYesNo, Text queenOfSpadesYesNo) throws IllegalArgumentException
    {
        if (numOfCards < 1 || numOfCards > 52) {
            throw new IllegalArgumentException("You must enter a number between 1 and 52.");
        }
        pane.getChildren().clear(); // Empties the TilePane 'hand'
        ArrayList<PlayingCard> dealtHand = deck.dealHand(numOfCards); // Retrieves a hand
        dealtHand.forEach(card -> pane.getChildren().add(card.getImageView())); // Adds all images to the specified pane

        HandOfCards handOfCards = new HandOfCards(dealtHand); // Represents the dealt hand, used to retrieve info

        // Displays the sum of the cards in the hand (ace counts as 1 and the king as 13)
        int sumOfCardsValue = handOfCards.getSumOfCards();
        sumOfCards.setText(String.valueOf(sumOfCardsValue));

        // Displays the cards of hearts in the hand, if any are present
        List<PlayingCard> heartsInHand = handOfCards.getHeartsInHand();
        StringBuilder heartsText = new StringBuilder();
        for (PlayingCard heartCard : heartsInHand) {
            heartsText.append(" ").append(heartCard.getAsString());
        }
        allHearts.setText(heartsText.toString());

        // Displays Yes/No depending on whether the hand has at least one flush
        String hasFlush = "No";
        if(handOfCards.checkHasFlush()) {
            hasFlush = "Yes";
        }
        flushYesNo.setText(hasFlush);

        // Displays Yes/No depending on whether the hand includes the Queen of Spades
        String hasQueenOfSpades = "No";
        if(handOfCards.checkHasQueenOfSpades()) {
            hasQueenOfSpades = "Yes";
        }

        queenOfSpadesYesNo.setText(hasQueenOfSpades);
    }


    /**
     * Attempts to restart the application.
     * Used after exception-handling.
     *
     * @param stage The main Stage.
     */
    public void restartApplication(Stage stage)
    {
        try {
            start(stage); // Tries restarting the application
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    /**
     * Defines the actionEvent to be executed.
     *
     * @param stage the main Stage.
     * @param amountOfCardsInput the field the button executes a read from.
     * @param hand the pane the button modifies the contents of.
     * @param deckOfCards the class which contains the method the button shall execute.
     */
    public void drawBtnOnClick(Stage stage, TextField amountOfCardsInput, TilePane hand, DeckOfCards deckOfCards,
                               Text sumOfCards, Text allHearts, Text flushYesNo, Text queenOfSpadesYesNo)
    {
        try {
            String numberInputStr = amountOfCardsInput.getText();

            // Validates the text/numbers in the TextField 'amountOfCardsInput'
            if (numberInputStr.isBlank() || numberInputStr.isEmpty()) {
                throw new IllegalArgumentException();
            } else if (!numberInputStr.matches("[0-9]+")) {
                throw new NumberFormatException();
            } else {
                int numberInputInt = Integer.parseInt(numberInputStr);

                // Displays the dealt hand
                showHandAndInfo(hand, deckOfCards, Integer.parseInt(numberInputStr),
                        sumOfCards, allHearts, flushYesNo, queenOfSpadesYesNo);

                // Decides whether to resize amount of columns in the TilePane 'hand'
                if (numberInputInt >= 5 && numberInputInt < 8) {
                    hand.setPrefColumns(numberInputInt);
                } else if (numberInputInt >= 8) {
                    hand.setPrefColumns(8);
                }

                //Resizes the repositions the stage
                stage.sizeToScene();
                stage.centerOnScreen();
            }

        }
        catch (NumberFormatException numForEx) {
            //Displays an alert for the user
            Alert numForExAlert = new Alert(Alert.AlertType.WARNING);
            numForExAlert.setTitle("Number format exception");
            numForExAlert.setHeaderText(null);
            numForExAlert.setContentText("The entered input could not be interpreted as a number." +
                    "\nEntered input: '" + amountOfCardsInput.getCharacters() + "'" +
                    "\nPlease try again.");
            numForExAlert.showAndWait();
            restartApplication(stage);

        }
        catch (IllegalArgumentException illArgEx) {
            //Displays an alert for the user
            String exMessage = illArgEx.getMessage();
            Alert illArgExAlert = new Alert(Alert.AlertType.WARNING);
            illArgExAlert.setTitle("Empty text-field");
            illArgExAlert.setHeaderText(null);

            // Adds a detailed exception message if the exception is thrown with a message
            if (exMessage != null) {
                illArgExAlert.setContentText(exMessage);
            } else {
                illArgExAlert.setContentText("You must enter a number before pressing 'Draw'." +
                        "\n");
            }
            illArgExAlert.showAndWait();

        }
        catch (Exception e) {
            // Displays an alert for the user
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Program information");
            alert.setHeaderText(null);
            alert.setContentText("The program encountered an error." +
                    "\nPlease mind that the amount of cards available per hand is between 1 and 52.");
            alert.showAndWait();

            restartApplication(stage);
        }
    }
}
