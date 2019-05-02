package sample;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class MainMenu {
    private VBox root;
    private Scene scene;
    private Button createButton;
    private Button editButton;
    private Button deleteButton;
    private Button exitButton;
    private BackgroundImage background;


    public MainMenu() {
        root = new VBox();
        scene = new Scene(root, 500, 500);

        background = new BackgroundImage(new Image("/images/dolphin.jpg", 500,
                500, false, true), BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        createButton = new Button("Create");
        editButton = new Button("Edit");
        deleteButton = new Button("Delete");
        exitButton = new Button("Exit");

        // Handle button events
        createButton.setOnAction(event -> System.out.println("Creating " +
                "member"));
        editButton.setOnAction(event -> System.out.println("Editing member"));
        deleteButton.setOnAction(event -> System.out.println("Deleting " +
                "member"));
        exitButton.setOnAction(event -> System.out.println("Exiting"));

        // Give the buttons an upper width limit
        createButton.setMaxWidth(75);
        editButton.setMaxWidth(75);
        deleteButton.setMaxWidth(75);
        exitButton.setMaxWidth(75);

        // Set background
        root.setBackground(new Background(background));

        // Setting up the layout
        root.setAlignment(Pos.CENTER);

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

    public Scene getScene() {
        return scene;
    }

    public VBox getRoot() {
        return root;
    }
}
