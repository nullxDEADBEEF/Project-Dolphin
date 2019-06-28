package sample.Menues;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.*;

public class MainMenu {
    private Scene scene;

    public MainMenu() {
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
        createButton.setOnAction(click -> {
            Constants.CONTROLLER.setActiveScene(new MemberCreation().getScene());
        });

        exitButton.setOnAction(click -> Platform.exit());

        viewMembersButton.setOnAction(click -> {
            Constants.CONTROLLER.setActiveScene(new ViewMember().getScene());
        });

        viewDeficitButton.setOnAction(click -> {
            Constants.CONTROLLER.setActiveScene(new DeficitMember().getScene());
        });

        createCompetitionButton.setOnAction(click -> {
            CompetitorList.loadAllCompetitors();
            Constants.CONTROLLER.setActiveScene(new CompetitionCreation().getScene());
        });

        viewCompetitionsButton.setOnAction(click -> {
            CompetitionList.loadAllCompetitions();
            Constants.CONTROLLER.setActiveScene(new ViewCompetition().getScene());
        });

        viewHighscoreButton.setOnAction(click -> {
            CompetitionList.loadAllCompetitions();
            Constants.CONTROLLER.setActiveScene(new ViewHighscore().getScene());
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

    public Scene getScene() {
        return scene;
    }
}
