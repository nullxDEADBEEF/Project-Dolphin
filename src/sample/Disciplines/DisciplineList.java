package sample.Disciplines;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DisciplineList {
    private ObservableList<Discipline> disciplines;

    public DisciplineList() {
        disciplines = FXCollections.observableArrayList();

        disciplines.addAll(new Backstroke(),
                           new Breaststroke(),
                           new Freestyle());

    }

    public ObservableList<Discipline> getDisciplines() {
        return disciplines;
    }


}
