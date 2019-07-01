package sample.Dataholder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Member;

// Data holder for the different member types
public class MemberList {
    public static ObservableList<Member> members =
            FXCollections.observableArrayList();

    public static ObservableList<Member> membersInDeficit =
            FXCollections.observableArrayList();

    public static ObservableList<Member> competitiveMembers =
            FXCollections.observableArrayList();
}
