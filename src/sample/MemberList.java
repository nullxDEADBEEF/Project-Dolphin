package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MemberList {
    public static ObservableList<Member> members =
            FXCollections.observableArrayList();

    public static ObservableList<Member> getMembers() {
        return members;
    }
}
