package sample.Menues;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import sample.CompetitorList;
import sample.Constants;
import sample.Controller;
import sample.CompetitionList;

public class MainMenu {
    private Scene scene;

    private static MainMenu instance = null;

    private MainMenu() {
        VBox layout = new VBox();
        scene = new Scene(layout, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        Button createButton = new Button("Create Member");
        Button exitButton = new Button("Exit");
        Button viewMembersButton = new Button("View Members");
        Button viewDeficitButton = new Button("View Deficit");
        Button viewCompetitionsButton = new Button("View competitions");
        Button createCompetitionButton = new Button("Create Competition");
        Button viewHighscoreButton = new Button("View High Scores");

        // Handle button events
        createButton.setOnAction(click ->
                Controller.setActiveScene(MemberCreation.getInstance().getScene()));

        exitButton.setOnAction(click -> Platform.exit());

        viewMembersButton.setOnAction(click ->
            Controller.setActiveScene(ViewMember.getInstance().getScene()));

        viewDeficitButton.setOnAction(click ->
            Controller.setActiveScene(DeficitMember.getInstance().getScene()));

        createCompetitionButton.setOnAction(click -> {
            CompetitorList.loadAllCompetitors();
            Controller.setActiveScene(CompetitionCreation.getInstance().getScene());
        });

        viewCompetitionsButton.setOnAction(click -> {
            CompetitionList.loadAllCompetitions();
            Controller.setActiveScene(ViewCompetition.getInstance().getScene());
        });

        viewHighscoreButton.setOnAction(click -> {
            CompetitionList.loadAllCompetitions();
            Controller.setActiveScene(ViewHighscore.getInstance().getScene());
        });

        // Give the buttons an upper width limit
        createButton.setMaxWidth(140);
        viewMembersButton.setMaxWidth(140);
        exitButton.setMaxWidth(140);
        viewDeficitButton.setMaxWidth(140);
        createCompetitionButton.setMaxWidth(140);
        viewCompetitionsButton.setMaxWidth(140);
        viewHighscoreButton.setMaxWidth(140);

        // Set background
        layout.setBackground(new Background(Constants.BACKGROUND_IMAGE));

        // Setting up the layout
        layout.setAlignment(Pos.CENTER);

        // Spacing between the buttons
        layout.setSpacing(10);
        layout.setPadding(new Insets(0, 20, 10, 20));

        GridPane.setConstraints(createButton, 1, 0);
        GridPane.setHalignment(createButton, HPos.CENTER);

        GridPane.setConstraints(viewMembersButton, 1, 1);
        GridPane.setHalignment(viewMembersButton, HPos.CENTER);

        GridPane.setConstraints(exitButton, 1, 2);
        GridPane.setHalignment(exitButton, HPos.CENTER);

        layout.getChildren().addAll(createButton, viewMembersButton,
                viewDeficitButton, createCompetitionButton, viewCompetitionsButton,
                viewHighscoreButton, exitButton);
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }

        return instance;
    }

    public Scene getScene() {
        return scene;
    }
}
