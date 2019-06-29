package sample.Menues;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Constants;
import sample.Controller;

// handles the setup of the login page
public class LoginMenu {
    private GridPane layout;
    private Scene scene;
    private Button loginButton;
    private Button exitButton;
    private Label usernameLabel;
    private Label passwordLabel;
    private TextField usernameTextField;
    private PasswordField passwordTextField;

    public LoginMenu() {
        layout = new GridPane();
        scene = new Scene(layout, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        MainMenu mainMenu = new MainMenu();

        // setup GUI

        usernameLabel = new Label("Username");
        passwordLabel = new Label("Password");

        usernameLabel.setTextFill(Color.WHITE);
        passwordLabel.setTextFill(Color.WHITE);

        usernameTextField = new TextField();
        passwordTextField = new PasswordField();

        usernameTextField.setPromptText("Username");
        passwordTextField.setPromptText("Password");

        layout.add(usernameTextField, 1, 0);
        layout.add(passwordTextField, 1, 1);

        // Set the background and gap between buttons
        layout.setBackground(new Background(Constants.BACKGROUND_IMAGE));
        layout.setVgap(5);
        layout.setHgap(10);

        layout.add(usernameLabel, 0, 0);
        layout.add(passwordLabel, 0, 1);

        usernameTextField.setMaxWidth(150);
        passwordTextField.setMaxWidth(150);

        // Create button
        loginButton = new Button("Login");
        exitButton = new Button("Exit");

        layout.add(loginButton, 0, 2);
        layout.add(exitButton, 1, 2);

        // Handle button events
        loginButton.setOnAction(click -> {
            Constants.CONTROLLER.setActiveScene(mainMenu.getScene());
        });

        exitButton.setOnAction(click -> Platform.exit());

        layout.setAlignment(Pos.CENTER);
    }

    public Scene getScene() {
        return scene;
    }
}
