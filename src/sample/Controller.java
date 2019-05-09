package sample;

import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Menues.LoginMenu;
import sample.Menues.MainMenu;

public class Controller {
    private MainMenu mainMenu;
    private LoginMenu loginMenu;
    private static Scene activeScene;
    private static Stage Stage;

    public Controller(Stage stage) {
        Stage = stage;
        mainMenu = MainMenu.getInstance();
        loginMenu = LoginMenu.getInstance();

        activeScene = loginMenu.getScene();
    }

    public static Scene getActiveScene() {
        return activeScene;
    }
    public static void setActiveScene(Scene newScene) {
        activeScene = newScene;
        Stage.setScene(activeScene);
    }
}
