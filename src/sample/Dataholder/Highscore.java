package sample.Dataholder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import sample.Member;

import java.util.Comparator;

// Data holder for high scores
public class Highscore implements Comparator<Pair<Member, Integer>> {
    private ObservableList<Member> top5Participants;
    private ObservableList<Integer> top5ParticipantTimes;

    public Highscore() {
        top5Participants = FXCollections.observableArrayList();
        top5ParticipantTimes = FXCollections.observableArrayList();
    }

    public ObservableList<Member> getTop5Participants() {
        return top5Participants;
    }

    public ObservableList<Integer> getTop5ParticipantTimes() {
        return top5ParticipantTimes;
    }

    // sort in ascending order
    public int compare(Pair<Member, Integer> a, Pair<Member, Integer> b) {
        return a.getValue() - b.getValue();
    }
}
