package sample.Menues;

import javafx.scene.Scene;
import sample.Member;

public interface IEditMember {
    public void fillMemberData();
    public void setSelectedMember(Member member);
    public Scene getScene();
}
