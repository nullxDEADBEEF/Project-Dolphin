package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CompetitionList {
    public static ObservableList<Competition> competitions =
            FXCollections.observableArrayList();

    public static ObservableList<Competition> freestyleCompetitions;

    public static ObservableList<Competition> backstrokeCompetitions;

    public static ObservableList<Competition> breaststrokeCompetitions;

    private static void loadFreestyleCompetitions() {
        freestyleCompetitions = FXCollections.observableArrayList();

        for (Competition competition : competitions) {
            if (competition.getName().equalsIgnoreCase("Freestyle")) {
                freestyleCompetitions.add(competition);
            }
        }
    }

    private static void loadBackstrokeCompetitions() {
        backstrokeCompetitions = FXCollections.observableArrayList();

        for (Competition competition : competitions) {
            if (competition.getName().equalsIgnoreCase("Backstroke")) {
                backstrokeCompetitions.add(competition);
            }
        }
    }

    private static void loadBreaststrokeCompetitions() {
        breaststrokeCompetitions = FXCollections.observableArrayList();

        for (Competition competition : competitions) {
            if (competition.getName().equalsIgnoreCase("Breaststroke")) {
                breaststrokeCompetitions.add(competition);
            }
        }
    }

    public static void loadAllCompetitions() {
        loadFreestyleCompetitions();
        loadBackstrokeCompetitions();
        loadBreaststrokeCompetitions();
    }

    public ObservableList<Competition> getFreestyleCompetitions() {
        return freestyleCompetitions;
    }

    public ObservableList<Competition> getBackstrokeCompetitions() {
        return backstrokeCompetitions;
    }

    public ObservableList<Competition> getBreaststrokeCompetitions() {
        return breaststrokeCompetitions;
    }
}
