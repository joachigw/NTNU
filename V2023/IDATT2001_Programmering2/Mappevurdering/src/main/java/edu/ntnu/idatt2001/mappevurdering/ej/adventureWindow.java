package edu.ntnu.idatt2001.mappevurdering.ej;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class adventureWindow {

    private VBox vBox0;
    private MenuBar menuBar;
    private Menu saveMenu;
    private Menu helpMenu;
    private GridPane gridPane;

    private ColumnConstraints first;
    private RowConstraints second;

    private ImageView POV;
    private ListView<String> Choices;
    private VBox statsArea;
    private VBox statsInfo;
    private Label playerStatsTitle;
    private Label Health;
    private Label Score;
    private Label Attack;
    private Label inventoryTitle;
    private ListView<String> inventoryList;
    private  ScrollPane log;
    private AnchorPane root;

    public AnchorPane getRoot() {
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
        Choices = new ListView<>();
        gridPane.add(POV,0,0);
        gridPane.add(Choices, 1,0);

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
        gridPane.add(log,0,1);

        root.setId("Adventure");
        root.setPrefHeight(600.0);
        root.setPrefWidth(800.0);

        vBox0.setPrefHeight(600.0);
        vBox0.setPrefWidth(800.0);

        vBox0.getChildren().addAll(menuBar, gridPane);
        root.getChildren().addAll(vBox0);

        return root;
    }
}