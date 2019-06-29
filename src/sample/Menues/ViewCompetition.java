package sample.Menues;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Competition;
import sample.Constants;
import sample.Controller;
import sample.CompetitionList;

// handles the setup of viewing competitions
public class ViewCompetition {
    private Scene scene;

    public ViewCompetition() {
        GridPane layout = new GridPane();
        scene = new Scene(layout, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        // setup GUI

        ListView<Competition> competitionListView = new ListView<>();
        competitionListView.setItems(CompetitionList.competitions.sorted());

        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(Constants.BACKGROUND_IMAGE));

        Button backButton = new Button("Back");
        backButton.setOnAction(click -> {
            Constants.CONTROLLER.setActiveScene(new MainMenu().getScene());
        });

        layout.add(competitionListView, 0, 0);
        layout.add(backButton, 0, 1);
    }

    public Scene getScene() {
        return scene;
    }
}
