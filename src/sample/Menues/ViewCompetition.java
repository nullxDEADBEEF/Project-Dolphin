package sample.Menues;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import sample.Competition;
import sample.Constants;
import sample.Controller;
import sample.CompetitionList;

public class ViewCompetition {
    private Scene scene;

    private static ViewCompetition instance = null;

    private ViewCompetition() {
        GridPane layout = new GridPane();
        scene = new Scene(layout, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        ListView<Competition> competitionListView = new ListView<>();
        competitionListView.setItems(CompetitionList.competitions.sorted());

        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(Constants.BACKGROUND_IMAGE));

        Button backButton = new Button("Back");
        backButton.setOnAction(click -> {
            Controller.setActiveScene(MainMenu.getInstance().getScene());
        });

        layout.add(competitionListView, 0, 0);
        layout.add(backButton, 0, 1);
    }

    public static ViewCompetition getInstance() {
        if (instance == null) {
            instance = new ViewCompetition();
        }

        return instance;
    }

    public Scene getScene() {
        return scene;
    }
}
