package edu.ntnu.idatt2001.mappevurdering.ej;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class AppEmil extends Application
{

    protected static Passage previousPassage;
    protected static Passage currentPassage;

    private static Story currentStory = new Story(null, null);

    private static Map<Link, Passage> road = new HashMap<>();

    private static final fileHandling reader = new fileHandling();
    adventureWindow firstRoot = new adventureWindow();

    private Boolean imageStory;

    private VBox vBox0;
    private MenuBar menuBar;
    private Menu saveMenu;
    private Menu helpMenu;
    private GridPane gridPane;

    private ColumnConstraints first;
    private RowConstraints second;

    private ImageView POV;
    private ListView<Link> Choices;
    private VBox statsArea;
    private VBox statsInfo;
    private Label playerStatsTitle;
    private Label Health;
    private Label Score;
    private Label Attack;
    private Label inventoryTitle;
    private ListView<String> inventoryList;
    private  ScrollPane log;
    private StringBuilder logList;
    private AnchorPane root;

    private Label newestContent = new Label();

    public static void main(String[] args)
    {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        newStory();
        System.out.println(reader.getBrokenLinks("src/main/resources/Scripts/thevalleyofadventures.path"));
        imageStory = reader.checkImageStory("src/main/resources/Scripts/thevalleyofadventures.path");
        createAdventureGUI();

        //button.setOnAction(e -> playerStatsTitle.setText())
        playerStatsTitle.setText("Player: Emil");
        logList = new StringBuilder(currentPassage.getContent() + "\n\n");
        newestContent.setText(logList.toString());
        newestContent.setWrapText(true);
        newestContent.setMaxWidth(Double.MAX_VALUE);
        newestContent.prefWidthProperty().bind(log.widthProperty());
        log.setContent(newestContent);

        Choices.getItems().addAll(currentPassage.getLinks());

        primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
    }


    private static void newStory() throws IOException {
        road = reader.readNextPassage(road, null);
        currentStory = new Story("Fart", road);
        currentPassage = currentStory.getPassage(null);
    }

    private void createAdventureGUI(){
        root = new AnchorPane();
        vBox0 = new VBox();

        menuBar = new MenuBar();
        saveMenu = new Menu("Save");
        helpMenu = new Menu("Help");
        menuBar.getMenus().addAll(saveMenu, helpMenu);

        gridPane = new GridPane();

        first = new ColumnConstraints();
        first.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(first, first);

        second = new RowConstraints();
        second.setVgrow(Priority.ALWAYS);
        gridPane.getRowConstraints().addAll(second, second);

        POV = new ImageView();
        POV.setFitHeight(360);
        POV.setFitWidth(400);
        Choices = new ListView<Link>();
        gridPane.add(POV,0,0);
        gridPane.add(Choices, 0,1);

        statsArea = new VBox();
        statsArea.setPadding(new Insets(10,10,10,10));
        statsInfo = new VBox();

        playerStatsTitle = new Label("Player: ");
        playerStatsTitle.setPadding(new Insets(0,0,10,0));
        Score = new Label("Score: ");
        Health = new Label("Health: ");
        Attack = new Label("Attack: 0-n");
        inventoryTitle = new Label("Inventory: ");
        inventoryTitle.setPadding(new Insets(5,0,0,0));
        statsInfo.getChildren().addAll(playerStatsTitle, Score, Health, Attack, inventoryTitle);
        inventoryList = new ListView<>();
        statsArea.getChildren().addAll(statsInfo,inventoryList);
        gridPane.add(statsArea,1,1);

        log = new ScrollPane();
        log.setFitToWidth(true);
        log.vvalueProperty().set(1.0);

        gridPane.add(log,1,0);

        root.setId("Adventure");
        root.setPrefHeight(600.0);
        root.setPrefWidth(800.0);

        vBox0.setPrefHeight(600.0);
        vBox0.setPrefWidth(800.0);

        vBox0.getChildren().addAll(menuBar, gridPane);
        root.getChildren().addAll(vBox0);



        Choices.setOnMouseClicked(e -> {
            Link linkChoice = Choices.getSelectionModel().getSelectedItem();
            if(linkChoice.getText().contains("End Story")){
                System.exit(0);
            }
            else if(linkChoice.getReference() != null) {
                if (linkChoice.getReference().contains("Previous Passage")) {
                    previousPassage.setContent("You return to the divided path.\nWhat do you choose now?\n");
                    Choices.getItems().removeAll(currentPassage.getLinks());
                    currentPassage = previousPassage;
                } else if (!(linkChoice.getReference() == null) && !linkChoice.getReference().contains("Previous Passage")) {
                    previousPassage = currentPassage;
                    try {
                        road = reader.readNextPassage(road, linkChoice);
                    } catch (IOException ex) {
                        System.out.println("Forgettaboutit");
                    }
                    currentPassage = road.get(linkChoice);
                    if (imageStory && currentPassage.getTitle().contains("(creature)")) {
                        POV.setImage(new Image(reader.getImage(currentPassage.getTitle())));
                    }

                }
                logList = logList.append(currentPassage.getContent()).append("\n\n");
                newestContent.setText(logList.toString());
                newestContent.setWrapText(true);
                newestContent.setMaxWidth(Double.MAX_VALUE);
                newestContent.prefWidthProperty().bind(log.widthProperty());
                Choices.getItems().removeAll(previousPassage.getLinks());
                Choices.getItems().addAll(currentPassage.getLinks());
                Choices.refresh();
            }

        });
    }
}
