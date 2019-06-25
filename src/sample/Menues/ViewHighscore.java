package sample.Menues;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import sample.*;

import java.util.ArrayList;

public class ViewHighscore {
    private GridPane layout;
    private Scene scene;

    private TextArea highscores;

    private static ViewHighscore instance = null;

    private ViewHighscore() {
        layout = new GridPane();
        scene = new Scene(layout, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        Button freestyleScoreButton = new Button("Freestyle");
        Button backstrokeScoreButton = new Button("Backstroke");
        Button breaststrokeButton = new Button("Breaststroke");
        Button backButton = new Button("Back");

        highscores = new TextArea();

        layout.setBackground(new Background(Constants.BACKGROUND_IMAGE));
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(5.0);
        layout.setVgap(5.0);

        layout.add(freestyleScoreButton, 0, 0);
        layout.add(backstrokeScoreButton, 1, 0);
        layout.add(breaststrokeButton, 2, 0);
        layout.add(highscores, 0, 3, 4, 4);
        layout.add(backButton, 0, 8);

        freestyleScoreButton.setOnAction(click -> {
            highscores.clear();
            Highscore highscore = new Highscore();
            ArrayList<Pair<Member, Integer>> scores =
                    determineTop5(highscore, CompetitionList.freestyleCompetitions);

            scores.sort(new Highscore());

            displayTop5(scores);
        });

        backstrokeScoreButton.setOnAction(click -> {
            highscores.clear();
            Highscore highscore = new Highscore();
            ArrayList<Pair<Member, Integer>> scores =
                    determineTop5(highscore, CompetitionList.backstrokeCompetitions);

            scores.sort(new Highscore());

            displayTop5(scores);
        });

        breaststrokeButton.setOnAction(click -> {
            highscores.clear();
            Highscore highscore = new Highscore();
            ArrayList<Pair<Member, Integer>> scores =
                    determineTop5(highscore, CompetitionList.breaststrokeCompetitions);

            scores.sort(new Highscore());

            displayTop5(scores);

        });

        backButton.setOnAction(click -> {
            highscores.clear();
            Controller.setActiveScene(MainMenu.getInstance().getScene());
        });
    }

    private ArrayList<Pair<Member, Integer>> determineTop5(Highscore highscore,
                               ObservableList<Competition> competitions) {
        ArrayList<Pair<Member, Integer>> scores = new ArrayList<>();

        for (Competition competition : competitions) {
            for (Member member : competition.getParticipants()) {
                highscore.getTop5Participants().add(member);
            }

            for (Integer time : competition.getParticipantTime()) {
                highscore.getTop5ParticipantTimes().add(time);
            }
        }

        for (int i = 0; i < highscore.getTop5Participants().size(); i++) {
            scores.add(new Pair<>(highscore.getTop5Participants().get(i)
            , highscore.getTop5ParticipantTimes().get(i)));
        }

        return scores;
    }

    private void displayTop5(ArrayList<Pair<Member, Integer>> top5) {
        highscores.setText(
                "1. place\n" + top5.get(0).getKey() + "\n" +
                        top5.get(0).getValue() + " " + "seconds" + "\n\n" +
                "2. place\n" + top5.get(1).getKey() + "\n" +
                        top5.get(1).getValue() + " " + "seconds" + "\n\n" +
                "3. place\n" + top5.get(2).getKey() + "\n" +
                        top5.get(2).getValue() + " " + "seconds" + "\n\n" +
                "4. place\n" + top5.get(3).getKey() + "\n" +
                        top5.get(3).getValue() + " " + "seconds" + "\n\n" +
                "5. place\n" +  top5.get(4).getKey() + "\n" +
                        top5.get(4).getValue() + " " + "seconds");
    }

    public Scene getScene() {
        return scene;
    }

    public static ViewHighscore getInstance() {
        if (instance == null) {
            instance = new ViewHighscore();
        }

        return instance;
    }
}
