package sample;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class MainMenu {
    private VBox root;
    private Scene scene;
    private Button createButton;
    private Button editButton;
    private Button deleteButton;
    private Button exitButton;

    private static MainMenu instance = null;


    private MainMenu() {
        root = new VBox();
        scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        createButton = new Button("Create");
        editButton = new Button("Edit");
        deleteButton = new Button("Delete");
        exitButton = new Button("Exit");

        // Handle button events
        createButton.setOnAction(event ->
                Controller.setActiveScene(MemberCreation.getInstance().getScene()));

        editButton.setOnAction(event -> System.out.println("Editing member"));
        deleteButton.setOnAction(event -> System.out.println("Deleting " +
                "member"));
        exitButton.setOnAction(event -> Platform.exit());

        // Give the buttons an upper width limit
        createButton.setMaxWidth(75);
        editButton.setMaxWidth(75);
        deleteButton.setMaxWidth(75);
        exitButton.setMaxWidth(75);

        // Set background
        root.setBackground(new Background(Constants.BACKGROUND_IMAGE));

        // Setting up the layout
        root.setAlignment(Pos.CENTER);

        // Spacing between the buttons
        root.setSpacing(10);
        root.setPadding(new Insets(0, 20, 10, 20));

        GridPane.setConstraints(createButton, 1, 0);
        GridPane.setHalignment(createButton, HPos.CENTER);

        GridPane.setConstraints(editButton, 1, 1);
        GridPane.setHalignment(editButton, HPos.CENTER);

        GridPane.setConstraints(deleteButton, 1, 2);
        GridPane.setHalignment(deleteButton, HPos.CENTER);

        GridPane.setConstraints(exitButton, 1, 3);
        GridPane.setHalignment(exitButton, HPos.CENTER);

        root.getChildren().addAll(createButton, editButton, deleteButton,
                exitButton);
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

    public VBox getRoot() {
        return root;
    }
}
