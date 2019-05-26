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
    private static Stage Stage;

    public Controller(Stage stage) {
        TrainerList.loadTrainers();
        IOReader.loadMembers();

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
