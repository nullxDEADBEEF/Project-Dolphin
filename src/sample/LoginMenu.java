package sample;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class LoginMenu {
    private GridPane root;
    private Scene scene;
    private Button loginButton;
    private Button exitButton;
    private Label usernameLabel;
    private Label passwordLabel;
    private TextField usernameTextField;
    private PasswordField passwordTextField;

    private static LoginMenu instance = null;

    private LoginMenu() {
        root = new GridPane();
        scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        usernameLabel = new Label("Username");
        passwordLabel = new Label("Password");

        usernameLabel.setTextFill(Color.WHITE);
        passwordLabel.setTextFill(Color.WHITE);

        usernameTextField = new TextField();
        passwordTextField = new PasswordField();

        root.add(usernameTextField, 1, 0);
        root.add(passwordTextField, 1, 1);

        // Set the background and gap between buttons
        root.setBackground(new Background(Constants.BACKGROUND_IMAGE));
        root.setVgap(5);
        root.setHgap(10);

        root.add(usernameLabel, 0, 0);
        root.add(passwordLabel, 0, 1);

        usernameTextField.setMaxWidth(150);
        passwordTextField.setMaxWidth(150);

        // Create button
        loginButton = new Button("Login");
        exitButton = new Button("Exit");

        root.add(loginButton, 0, 2);
        root.add(exitButton, 1, 2);

        // Handle button events
        loginButton.setOnAction(click -> Controller.setActiveScene(MainMenu.getInstance().getScene()));
        exitButton.setOnAction(click -> Platform.exit());

        root.setAlignment(Pos.CENTER);
    }

    public static LoginMenu getInstance() {
        if (instance == null) {
            instance = new LoginMenu();
        }

        return instance;
    }

    public Scene getScene() {
        return scene;
    }
}
