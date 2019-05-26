package sample.Menues;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import sample.Constants;
import sample.Controller;

public class MainMenu {
    private VBox layout;
    private Scene scene;
    private Button createButton;
    private Button exitButton;
    private Button viewMembersButton;

    private static MainMenu instance = null;


    private MainMenu() {
        layout = new VBox();
        scene = new Scene(layout, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        createButton = new Button("Create");
        exitButton = new Button("Exit");
        viewMembersButton = new Button("View");

        // Handle button events
        createButton.setOnAction(click ->
                Controller.setActiveScene(MemberCreation.getInstance().getScene()));
        exitButton.setOnAction(click -> Platform.exit());
        viewMembersButton.setOnAction(click ->
            Controller.setActiveScene(ViewMember.getInstance().getScene()));

        // Give the buttons an upper width limit
        createButton.setMaxWidth(75);
        viewMembersButton.setMaxWidth(75);
        exitButton.setMaxWidth(75);

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

        layout.getChildren().addAll(createButton, viewMembersButton, exitButton);
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
