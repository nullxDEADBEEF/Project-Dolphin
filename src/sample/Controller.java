package sample;

import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Menues.LoginMenu;


public class Controller {
    private static Scene activeScene;
    private static Stage stage;

    public Controller(Stage stage) {
        this.stage = stage;
        LoginMenu loginMenu = new LoginMenu();

        activeScene = loginMenu.getScene();
    }

    public Scene getActiveScene() {
        return activeScene;
    }

    public Stage getStage() {
        return stage;
    }

    public void setActiveScene(Scene newScene) {
        activeScene = newScene;
        stage.setScene(activeScene);
    }
}
