package edu.ntnu.idatt1002.team20.controllers;

import edu.ntnu.idatt1002.team20.utils.FileHandler;
import edu.ntnu.idatt1002.team20.utils.SceneSwitcher;
import edu.ntnu.idatt1002.team20.models.goal.Goal;
import edu.ntnu.idatt1002.team20.models.goal.GoalRegister;
import edu.ntnu.idatt1002.team20.models.user.CurrentUser;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.io.File;

/**
 * Controller for the goal scene.
 */
public class GoalController {
    @FXML
    private TableView<Goal> goalTableView;
    @FXML
    private TableColumn<Goal, Double> amountSpentColumn;
    @FXML
    private TableColumn<Goal, Double> goalAmountColumn;
    @FXML
    private TableColumn<Goal, String> goalTypeColumn;
    @FXML
    private TableColumn<Goal, ProgressBar> progressBarColumn;
    @FXML
    private TableColumn<Goal, Void> removeColumn;
    @FXML
    private VBox titleBox;

    private final File goalFile = new File(CurrentUser.getInstance().getUser().getGoalsFilePath());
    private GoalRegister goalRegister = new GoalRegister();

    /**
     * Initializes the tableview with goals.
     */
    @FXML
    public void initialize() {
        goalRegister = FileHandler.readGoals(String.valueOf(goalFile));
        setTotalInfo();

        goalTypeColumn.setCellValueFactory(new PropertyValueFactory<>("goalType"));
        goalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("goalAmount"));
        //

        //Format the goal amount column to show currency
        setDoubleCellFactory(goalAmountColumn, " kr");
        //Add cell factory for editing and updating goalAmount
        goalAmountColumn.setOnEditCommit(event -> {
            try {
                Goal goal = event.getRowValue();
                goalRegister.getGoal(goal.getGoalType()).setGoalAmount(event.getNewValue());
                FileHandler.writeGoals(String.valueOf(goalFile), goalRegister.getGoalList());
                goalTableView.refresh();
            } catch (Exception e) {
                SceneSwitcher.showAlert("Please enter a valid amount.");
            }

            goalTableView.refresh();
        });

        amountSpentColumn.setCellValueFactory(new PropertyValueFactory<>("amountSpent"));
        //Format the amount spent column to shot currency
        setDoubleCellFactory(amountSpentColumn, " kr");
        amountSpentColumn.setEditable(false);

        progressBarColumn.setCellValueFactory(param ->
                new SimpleObjectProperty<>(param.getValue().getProgressBar()));
        //Format the progress bar cells to also show percentage.
        progressBarColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(ProgressBar bar, boolean empty) {
                super.updateItem(bar, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Label label = new Label(new DecimalFormat("##.##").format(bar.getProgress() * 100) + " %");
                    label.setPadding(new Insets(0, 0, 0, 5));
                    bar.setPrefWidth(150);
                    bar.setPadding(new Insets(0, 0, 0, 20));
                    setGraphic(new HBox(bar, label));
                }
            }
        });

        removeColumn.setCellFactory(column -> new TableCell<>() {
            private final Button removeButton = new Button("Remove");

            {
                removeButton.setId("removeButton");
                // Remove the goal from the table when the remove button is clicked
                removeButton.setOnAction(event -> {
                    Goal goal = getTableRow().getItem();
                    getTableView().getItems().remove(goal);
                    goalRegister.removeGoal(goal.getGoalType());
                    FileHandler.writeGoals(String.valueOf(goalFile), goalRegister.getGoalList());
                    setTotalInfo();
                });
            }
            // Add the remove button to the row
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(removeButton);
                }
            }
        });

        //Add the registered goals to the table
        ObservableList<Goal> goalList = FXCollections.observableArrayList(goalRegister.getGoalList());
        //Set table view to show the goals.
        goalTableView.setItems(goalList);
        goalTableView.setEditable(true);
    }

    /**
     * Switches to the add goal scene when the 'Add goal' button is pressed.
     */
    @FXML
    void addGoalButtonPressed() {
        SceneSwitcher.getInstance().switchScene("goalsPage", "add-goal");
    }

    /**
     * Switches to the main scene when the 'Home' button is pressed.
     */
    @FXML
    void mainPage() {
        SceneSwitcher.getInstance().switchScene("mainPage", "main-page");
    }


    /**
     * Switches to the expense and income scene when the 'Expense vs. income' button is pressed.
     */
    @FXML
    void expenseIncomePage() {
        SceneSwitcher.getInstance().switchScene("expenseIncomePage", "expenseIncome-page");
    }


    /**
     * Switches to the debt scene when the 'Debt' button is pressed.
     */
    @FXML
    public void debtPage() {
        SceneSwitcher.getInstance().switchScene("debtPage", "debt-page");
    }


    /**
     * Switches to the bills scene when the 'Bills' button is pressed.
     */
    @FXML
    void billsPage() {
        SceneSwitcher.getInstance().switchScene("billsPage", "bills-page");
    }


    /**
     * Switches to the log in scene when the 'Log out' button is clicked.
     */
    @FXML
    public void logOut() {
        SceneSwitcher.getInstance().switchScene("logInPage", "logIn-page");
    }


    /**
     * Switches to the about scene when the 'About' button is clicked.
     */
    @FXML
    public void aboutGoals() {
        SceneSwitcher.getInstance().showPopupWindow("goalsPage", "about-goals");
    }


    /**
     * Sets a cell factory for a table column that formats the cell value as a double with two decimals.
     *
     * @param column the column to set the cell factory for
     */
    private void setDoubleCellFactory(TableColumn<Goal, Double> column, String unit) {
        column.setCellFactory(col -> new TextFieldTableCell<Goal, Double>(new StringConverter<Double>() {
            private final DecimalFormat df = new DecimalFormat("#,###.##");

            @Override
            public String toString(Double object) {
                if (object == null) {
                    return null;
                }
                return df.format(object);
            }

            @Override
            public Double fromString(String string) {
                try {
                    Number n = df.parse(string);
                    return n.doubleValue();
                } catch (ParseException e) {
                    return null;
                }
            }
        }) {
            @Override
            public void updateItem(Double number, boolean empty) {
                super.updateItem(number, empty);
                if (empty || number == null) {
                    setText(null);
                } else {
                    setText(new DecimalFormat("#,###.##").format(number) + unit);
                }
            }
        });
    }

    /**
     * Displays the information regarding the total goal amount, and total amount spent.
     */
    private void setTotalInfo() {
        titleBox.getChildren().clear();
        Label title = new Label();
        title.setStyle("-fx-font-size: 30px; -fx-font-weight:bold;");
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().add(title);

        //Displays information regarding the sum of all goals.
        if (goalRegister.getGoals().isEmpty()) {
            title.setText("No goals registered.");
        } else {
            title.setText("Goals overview");

            Label goalTotalTitle = new Label("Total goal progress:");
            Label remainingAmountLabel = new Label(goalRegister.getTotalGoalAmount()
                    - goalRegister.getTotalAmountSpent() + " kr remaining");

            ProgressBar totalProgress = new ProgressBar(goalRegister.getTotalAmountSpent()
                    / goalRegister.getTotalGoalAmount());

            Label status = new Label(goalRegister.getTotalAmountSpent()
                    + " kr of " + goalRegister.getTotalGoalAmount() + " kr spent");

            goalTotalTitle.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 0 0 5 0");
            remainingAmountLabel.setStyle("-fx-font-size: 13px; -fx-padding: 0 0 5 0");
            status.setStyle("-fx-font-size: 13px;");
            titleBox.getChildren().addAll(goalTotalTitle, remainingAmountLabel, totalProgress, status);
        }
    }

}