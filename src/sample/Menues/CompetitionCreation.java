package sample.Menues;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import sample.Constants;
import sample.Controller;

public class CompetitionCreation {
    private GridPane layout;
    private Scene scene;

    private static CompetitionCreation instance = null;

    private CompetitionCreation() {
        layout = new GridPane();
        scene = new Scene(layout, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        Button finishButton = new Button("Finish");
        Button backButton = new Button("Back");

        backButton.setOnAction(click ->
                Controller.setActiveScene(MainMenu.getInstance().getScene()));
    }

    public static CompetitionCreation getInstance() {
        if (instance == null) {
            instance = new CompetitionCreation();
        }

        return instance;
    }

    public Scene getScene() {
        return scene;
    }
}
