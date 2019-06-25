package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CompetitorList {
    public static ObservableList<Member> freestyleCompetitors =
            FXCollections.observableArrayList();

    public static ObservableList<Member> backstrokeCompetitors =
            FXCollections.observableArrayList();

    public static ObservableList<Member> breaststrokeCompetitors =
            FXCollections.observableArrayList();

    public static void loadFreestyleCompetitors() {
        freestyleCompetitors = FXCollections.observableArrayList();

        for (Member member : MemberList.competitiveMembers) {
            if (member.getDiscipline().equalsIgnoreCase("Freestyle")) {
                freestyleCompetitors.add(member);
            }
        }
    }

    public static void loadBackstrokeCompetitors() {
        backstrokeCompetitors = FXCollections.observableArrayList();

        for (Member member : MemberList.competitiveMembers) {
            if (member.getDiscipline().equalsIgnoreCase("Backstroke")) {
                backstrokeCompetitors.add(member);
            }
        }
    }

    public static void loadBreaststrokeCompetitors() {
        breaststrokeCompetitors = FXCollections.observableArrayList();

        for (Member member : MemberList.competitiveMembers) {
            if (member.getDiscipline().equalsIgnoreCase("Breaststroke")) {
                breaststrokeCompetitors.add(member);
            }
        }
    }

    public static void loadAllCompetitors() {
        loadFreestyleCompetitors();
        loadBackstrokeCompetitors();
        loadBreaststrokeCompetitors();
    }
}
