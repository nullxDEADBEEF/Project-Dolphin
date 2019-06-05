package sample;

import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.IO.IOReader;
import sample.Menues.LoginMenu;
import sample.Menues.MainMenu;
import sample.User.TrainerList;

public class Controller {
    private LoginMenu loginMenu;
    private MainMenu mainMenu;
    private static Scene activeScene;
    private static Stage stage;

    public Controller(Stage stage) {
        TrainerList.loadTrainers();
        IOReader.loadMembers();

        this.stage = stage;
        mainMenu = MainMenu.getInstance();
        loginMenu = LoginMenu.getInstance();

        activeScene = loginMenu.getScene();
    }

    public static Scene getActiveScene() {
        return activeScene;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setActiveScene(Scene newScene) {
        activeScene = newScene;
        stage.setScene(activeScene);
    }
}
