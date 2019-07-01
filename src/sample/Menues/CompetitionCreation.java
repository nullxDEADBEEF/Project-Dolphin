package sample.Menues;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import sample.*;
import sample.Dataholder.CompetitorList;
import sample.Disciplines.Discipline;
import sample.Disciplines.DisciplineList;
import sample.IO.IOWriter;
import sample.Dataholder.CompetitionList;

import java.util.ArrayList;

// handles the setup of the competition creation page
public class CompetitionCreation implements IScene {
    private Scene scene;

    private DatePicker competitionDate;

    private ComboBox<Discipline> disciplineComboBox;

    private ComboBox<Member> competitorSelect1;
    private ComboBox<Member> competitorSelect2;
    private ComboBox<Member> competitorSelect3;
    private ComboBox<Member> competitorSelect4;
    private ComboBox<Member> competitorSelect5;

    private TextField firstParticipantTime;
    private TextField secondParticipantTime;
    private TextField thirdParticipantTime;
    private TextField fourthParticipantTime;
    private TextField fifthParticipantTime;

    private IOWriter ioWriter;

    public CompetitionCreation() {
        GridPane layout = new GridPane();
        scene = new Scene(layout, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        ioWriter = new IOWriter();

        competitionDate = new DatePicker();
        DisciplineList disciplineList = new DisciplineList();

        // load in the different discipline members
        CompetitorList.loadFreestyleCompetitors();
        CompetitorList.loadBackstrokeCompetitors();
        CompetitorList.loadBreaststrokeCompetitors();

        // NOTE: we add this many comboboxes with the same data
        // because javafx doesnt allow us to add the same combobox twice
        competitorSelect1 = new ComboBox<Member>();
        competitorSelect2 = new ComboBox<Member>();
        competitorSelect3 = new ComboBox<Member>();
        competitorSelect4 = new ComboBox<Member>();
        competitorSelect5 = new ComboBox<Member>();

        disciplineComboBox = new ComboBox<>();
        disciplineComboBox.setPromptText("Select discipline");
        disciplineComboBox.getItems().addAll(disciplineList.getDisciplines());

        // NOTE: gives a NullPointerException for some reason, but doesnt
        // affect the program, dont know how that works
        disciplineComboBox.getSelectionModel().selectedItemProperty()
                .addListener( (v, oldValue, newValue) -> {
                    switch (newValue.toString()) {
                        case "Freestyle":
                            setSelectorsToFreestyle();
                            break;
                        case "Backstroke":
                            setSelectorsToBackstroke();
                            break;
                        case "Breaststroke":
                            setSelectorsToBreaststroke();
                            break;
                        default:
                            System.out.println("Did not find discipline");
                            break;
                    }
                });

        // setup GUI

        Button finishButton = new Button("Finish");
        Button backButton = new Button("Back");

        Label disciplineLabel = new Label("Discipline");
        Label dateLabel = new Label("Date");
        Label firstParticipant = new Label("1. participant");
        Label secondParticipant = new Label("2. participant");
        Label thirdParticipant = new Label("3. participant");
        Label fourthParticipant = new Label("4. participant");
        Label fifthParticipant = new Label("5. participant");

        firstParticipantTime = new TextField();
        secondParticipantTime = new TextField();
        thirdParticipantTime = new TextField();
        fourthParticipantTime = new TextField();
        fifthParticipantTime = new TextField();

        firstParticipantTime.setPromptText("Time in seconds");
        secondParticipantTime.setPromptText("Time in seconds");
        thirdParticipantTime.setPromptText("Time in seconds");
        fourthParticipantTime.setPromptText("Time in seconds");
        fifthParticipantTime.setPromptText("Time in seconds");

        disciplineLabel.setTextFill(Color.WHITE);
        dateLabel.setTextFill(Color.WHITE);
        firstParticipant.setTextFill(Color.WHITE);
        secondParticipant.setTextFill(Color.WHITE);
        thirdParticipant.setTextFill(Color.WHITE);
        fourthParticipant.setTextFill(Color.WHITE);
        fifthParticipant.setTextFill(Color.WHITE);

        layout.add(disciplineLabel, 0, 0);
        layout.add(disciplineComboBox, 0, 1);
        layout.add(dateLabel, 0, 2);
        layout.add(competitionDate, 0, 3);

        layout.add(firstParticipant, 0, 4);
        layout.add(competitorSelect1, 0, 5);
        layout.add(firstParticipantTime, 1, 5);

        layout.add(secondParticipant, 0, 6);
        layout.add(competitorSelect2, 0, 7);
        layout.add(secondParticipantTime, 1, 7);

        layout.add(thirdParticipant, 0, 8);
        layout.add(competitorSelect3, 0, 9);
        layout.add(thirdParticipantTime, 1, 9);

        layout.add(fourthParticipant, 0, 10);
        layout.add(competitorSelect4, 0, 11);
        layout.add(fourthParticipantTime, 1, 11);

        layout.add(fifthParticipant, 0, 12);
        layout.add(competitorSelect5, 0, 13);
        layout.add(fifthParticipantTime, 1, 13);

        layout.add(finishButton, 0, 14);
        layout.add(backButton, 1, 14);

        layout.setBackground(new Background(Constants.BACKGROUND_IMAGE));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setVgap(10.0);
        layout.setHgap(1.0);

        // write competition data into a file and is added to the list of
        // competitions
        finishButton.setOnAction(click -> {
            Alert competitionCreatedAlert =
                    new Alert(Alert.AlertType.CONFIRMATION);
            competitionCreatedAlert.setContentText("Competition created!");

            ArrayList<Member> participants = new ArrayList<>();
            participants.add(competitorSelect1.getValue());
            participants.add(competitorSelect2.getValue());
            participants.add(competitorSelect3.getValue());
            participants.add(competitorSelect4.getValue());
            participants.add(competitorSelect5.getValue());

            ArrayList<Integer> participantTimes = new ArrayList<>();
            participantTimes.add(Integer.parseInt(firstParticipantTime.getText()));
            participantTimes.add(Integer.parseInt(secondParticipantTime.getText()));
            participantTimes.add(Integer.parseInt(thirdParticipantTime.getText()));
            participantTimes.add(Integer.parseInt(fourthParticipantTime.getText()));
            participantTimes.add(Integer.parseInt(fifthParticipantTime.getText()));

            String disciplineName =
                    disciplineComboBox.getSelectionModel().getSelectedItem().toString();

            Competition competition = new Competition(disciplineName,
                    competitionDate.getValue(), participants, participantTimes);

            ioWriter.writeFile(competition);
            CompetitionList.competitions.add(competition);

            clearFields();
            competitionCreatedAlert.showAndWait();
        });

        backButton.setOnAction(click -> {
            clearFields();
            Constants.CONTROLLER.setActiveScene(new MainMenu().getScene());
        });
    }

    /**
     * clears the text boxes to avoid data staying there
     */
    private void clearFields() {
        disciplineComboBox.getSelectionModel().clearSelection();

        competitorSelect1.getSelectionModel().clearSelection();
        competitorSelect2.getSelectionModel().clearSelection();
        competitorSelect3.getSelectionModel().clearSelection();
        competitorSelect4.getSelectionModel().clearSelection();
        competitorSelect5.getSelectionModel().clearSelection();

        firstParticipantTime.clear();
        secondParticipantTime.clear();
        thirdParticipantTime.clear();
        fourthParticipantTime.clear();
        fifthParticipantTime.clear();

        competitionDate.getEditor().clear();
    }

    /**
     * sets the comboboxes to freestyle competitors based on the swim discipline
     */
    private void setSelectorsToFreestyle() {
        competitorSelect1.setItems(CompetitorList.freestyleCompetitors.sorted());
        competitorSelect2.setItems(CompetitorList.freestyleCompetitors.sorted());
        competitorSelect3.setItems(CompetitorList.freestyleCompetitors.sorted());
        competitorSelect4.setItems(CompetitorList.freestyleCompetitors.sorted());
        competitorSelect5.setItems(CompetitorList.freestyleCompetitors.sorted());
    }

    /**
     * sets the comboboxes to breaststroke competitors based on the swim
     * discipline
     */
    private void setSelectorsToBreaststroke() {
        competitorSelect1.setItems(CompetitorList.breaststrokeCompetitors.sorted());
        competitorSelect2.setItems(CompetitorList.breaststrokeCompetitors.sorted());
        competitorSelect3.setItems(CompetitorList.breaststrokeCompetitors.sorted());
        competitorSelect4.setItems(CompetitorList.breaststrokeCompetitors.sorted());
        competitorSelect5.setItems(CompetitorList.breaststrokeCompetitors.sorted());
    }

    /**
     * sets the comboboxes to backstroke competitors based on the discipline
     */
    private void setSelectorsToBackstroke() {
        competitorSelect1.setItems(CompetitorList.backstrokeCompetitors.sorted());
        competitorSelect2.setItems(CompetitorList.backstrokeCompetitors.sorted());
        competitorSelect3.setItems(CompetitorList.backstrokeCompetitors.sorted());
        competitorSelect4.setItems(CompetitorList.backstrokeCompetitors.sorted());
        competitorSelect5.setItems(CompetitorList.backstrokeCompetitors.sorted());
    }

    public Scene getScene() {
        return scene;
    }
}
