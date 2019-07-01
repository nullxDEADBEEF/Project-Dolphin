package sample.Dataholder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Competition;

// Data holder for the different competition types
public class CompetitionList {
    public static ObservableList<Competition> competitions =
            FXCollections.observableArrayList();

    public static ObservableList<Competition> freestyleCompetitions;

    public static ObservableList<Competition> backstrokeCompetitions;

    public static ObservableList<Competition> breaststrokeCompetitions;

    /**
     * loads the freestyle type competitions into the program
     */
    private static void loadFreestyleCompetitions() {
        freestyleCompetitions = FXCollections.observableArrayList();

        for (Competition competition : competitions) {
            if (competition.getName().equalsIgnoreCase("Freestyle")) {
                freestyleCompetitions.add(competition);
            }
        }
    }

    /**
     * loads the backstroke type competitions into the program
     */
    private static void loadBackstrokeCompetitions() {
        backstrokeCompetitions = FXCollections.observableArrayList();

        for (Competition competition : competitions) {
            if (competition.getName().equalsIgnoreCase("Backstroke")) {
                backstrokeCompetitions.add(competition);
            }
        }
    }

    /**
     * loads the breaststroke type competitions into the program
     */
    private static void loadBreaststrokeCompetitions() {
        breaststrokeCompetitions = FXCollections.observableArrayList();

        for (Competition competition : competitions) {
            if (competition.getName().equalsIgnoreCase("Breaststroke")) {
                breaststrokeCompetitions.add(competition);
            }
        }
    }

    /**
     * loads all type of competitions into the program
     */
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
