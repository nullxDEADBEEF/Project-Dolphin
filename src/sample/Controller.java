package sample;

import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.IO.IOReader;
import sample.Menues.LoginMenu;
import sample.Menues.MainMenu;
import sample.User.TrainerList;

import java.io.FileNotFoundException;

public class Controller {
    private LoginMenu loginMenu;
    private MainMenu mainMenu;
    private static Scene activeScene;
    private static Stage stage;

    public Controller(Stage stage) {
        try {
            TrainerList.loadTrainers();
            IOReader.loadMembersFromFile();
            IOReader.loadMemberTypes();
            IOReader.loadCompetitionsFromFile();
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: " + ex);
        }

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
