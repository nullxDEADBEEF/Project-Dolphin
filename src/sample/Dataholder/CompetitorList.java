package sample.Dataholder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Member;

// holds data for different competitor types
public class CompetitorList {
    public static ObservableList<Member> freestyleCompetitors =
            FXCollections.observableArrayList();

    public static ObservableList<Member> backstrokeCompetitors =
            FXCollections.observableArrayList();

    public static ObservableList<Member> breaststrokeCompetitors =
            FXCollections.observableArrayList();

    /**
     * loads all the freestyle competitors into the program
     */
    public static void loadFreestyleCompetitors() {
        freestyleCompetitors = FXCollections.observableArrayList();

        for (Member member : MemberList.competitiveMembers) {
            if (member.getDiscipline().toString().equalsIgnoreCase("Freestyle")) {
                freestyleCompetitors.add(member);
            }
        }
    }

    /**
     * loads all the backstroke competitors into the program
     */
    public static void loadBackstrokeCompetitors() {
        backstrokeCompetitors = FXCollections.observableArrayList();

        for (Member member : MemberList.competitiveMembers) {
            if (member.getDiscipline().toString().equalsIgnoreCase("Backstroke")) {
                backstrokeCompetitors.add(member);
            }
        }
    }

    /**
     * loads all the breaststroke competitors into the program
     */
    public static void loadBreaststrokeCompetitors() {
        breaststrokeCompetitors = FXCollections.observableArrayList();

        for (Member member : MemberList.competitiveMembers) {
            if (member.getDiscipline().toString().equalsIgnoreCase("Breaststroke")) {
                breaststrokeCompetitors.add(member);
            }
        }
    }

    /**
     * loads all the competitor types into the program
     */
    public static void loadAllCompetitors() {
        loadFreestyleCompetitors();
        loadBackstrokeCompetitors();
        loadBreaststrokeCompetitors();
    }
}
