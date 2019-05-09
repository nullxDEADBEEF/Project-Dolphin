package sample.Menues;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import sample.Constants;
import sample.Controller;
import sample.User.Member;

public class ViewMember {
    private GridPane layout;
    private GridPane memberInfoLayout;
    private Scene scene;
    private Scene memberScene;

    private ListView<Member> membersListView;
    private ObservableList members;
    private Button editButton;
    private Button deleteButton;
    private Button backButton;
    private Button memberInfobackButton;

    private Label idLabel;
    private Label nameLabel;
    private Label disciplineLabel;
    private Label birthdayLabel;
    private Label startDateLabel;
    private Label competetiveLabel;
    private Label activeLabel;
    private Label senorityLabel;
    private Label emailAddressLabel;
    private Label phoneNumberLabel;
    private Label deficitLabel;
    private Label balanceLabel;
    private Label trainerLabel;

    private static ViewMember instance = null;

    private ViewMember() {
        layout = new GridPane();
        memberInfoLayout = new GridPane();

        idLabel = new Label();
        nameLabel = new Label();
        disciplineLabel = new Label();
        birthdayLabel = new Label();
        startDateLabel = new Label();
        competetiveLabel = new Label();
        activeLabel = new Label();
        senorityLabel = new Label();
        emailAddressLabel = new Label();
        phoneNumberLabel = new Label();
        deficitLabel = new Label();
        balanceLabel = new Label();
        trainerLabel = new Label();

        idLabel.setTextFill(Color.WHITE);
        nameLabel.setTextFill(Color.WHITE);
        disciplineLabel.setTextFill(Color.WHITE);
        birthdayLabel.setTextFill(Color.WHITE);
        startDateLabel.setTextFill(Color.WHITE);
        competetiveLabel.setTextFill(Color.WHITE);
        activeLabel.setTextFill(Color.WHITE);
        senorityLabel.setTextFill(Color.WHITE);
        emailAddressLabel.setTextFill(Color.WHITE);
        phoneNumberLabel.setTextFill(Color.WHITE);
        deficitLabel.setTextFill(Color.WHITE);
        balanceLabel.setTextFill(Color.WHITE);
        trainerLabel.setTextFill(Color.WHITE);

        scene = new Scene(layout, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        memberScene = new Scene(memberInfoLayout, Constants.WINDOW_WIDTH,
                Constants.WINDOW_HEIGHT);

        membersListView = new ListView<Member>();
        members = FXCollections.observableArrayList();
        editButton = new Button("Edit");
        deleteButton = new Button("Delete");
        backButton = new Button("Back");
        memberInfobackButton = new Button("Back");

        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(Constants.BACKGROUND_IMAGE));

        memberInfoLayout.setAlignment(Pos.CENTER);
        memberInfoLayout.setBackground(new Background(Constants.BACKGROUND_IMAGE));

        memberInfoLayout.add(idLabel, 0, 0);
        memberInfoLayout.add(nameLabel, 0, 1);
        memberInfoLayout.add(disciplineLabel, 0, 2);
        memberInfoLayout.add(birthdayLabel, 0, 3);
        memberInfoLayout.add(startDateLabel, 0, 4);
        memberInfoLayout.add(competetiveLabel, 0, 5);
        memberInfoLayout.add(activeLabel, 0, 6);
        memberInfoLayout.add(senorityLabel, 0, 7);
        memberInfoLayout.add(emailAddressLabel, 0, 8);
        memberInfoLayout.add(phoneNumberLabel, 0, 9);
        memberInfoLayout.add(deficitLabel, 0, 10);
        memberInfoLayout.add(balanceLabel, 0, 11);
        memberInfoLayout.add(trainerLabel, 0, 12);
        memberInfoLayout.add(memberInfobackButton, 0, 13);

        layout.add(membersListView, 0, 0);
        layout.add(editButton, 0, 1);
        layout.add(deleteButton, 1, 1);
        layout.add(backButton, 0, 4);

        backButton.setOnAction(click ->
                Controller.setActiveScene(MainMenu.getInstance().getScene()));
        memberInfobackButton.setOnAction(click -> Controller.setActiveScene(scene));

        // NOTE: MouseButton.PRIMARY means left click
        membersListView.setOnMouseClicked(click -> {
            if (click.getButton().equals(MouseButton.PRIMARY)) {
                if (click.getClickCount() == 2) {
                    Controller.setActiveScene(memberScene);
                    displayMemberInfoPage(membersListView.getSelectionModel().getSelectedItem());
                }
            }
        });

        Member niklas = new Member("0", "Niklas Lund Brock", "", null,
                null, false, true, false,
               "test@gmail.com", "45454545", false, 0,
        null);

        Member frederik = new Member("1", "Frederik Primdahl", "", null,
                null, false, true, false,
                "test11@gmail.com", "45454533", false, 0,
                null);

        Member mikael = new Member("2", "Mikael Pasovski", "", null,
                null, false, true, false,
                "test34@gmail.com", "43344545", false, 0,
                null);

        members.addAll(niklas, frederik, mikael);

        membersListView.setItems(members);
    }

    private void displayMemberInfoPage(Member member) {
        idLabel.setText("ID: " + member.getId());
        nameLabel.setText("Name: " + member.getName());
        disciplineLabel.setText("Discipline: " + member.getDiscipline());
        birthdayLabel.setText("Birthday: " + member.getBirthday());
        startDateLabel.setText("Start date: " + member.getStartDate());
        competetiveLabel.setText("Competetive: " + member.isCompetetive());
        activeLabel.setText("Active: " + member.isActive());
        senorityLabel.setText("Senority: " + member.isSenority());
        emailAddressLabel.setText("Email: " + member.getEmail());
        phoneNumberLabel.setText("Phone: " + member.getPhoneNumber());
        deficitLabel.setText("Deficit: " + member.isDeficit());
        balanceLabel.setText("Balance: " + member.getBalance());
        trainerLabel.setText("Trainer: " + member.getAppointedTrainer());
    }

    public static ViewMember getInstance() {
        if (instance == null) {
            instance = new ViewMember();
        }

        return instance;
    }

    public Scene getScene() { return scene; }
}
