package sample.Menues;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import sample.Constants;
import sample.Controller;
import sample.IO.IOWriter;
import sample.Member;
import sample.MemberList;

public class DeficitMembers {
    private GridPane layout;
    private Scene scene;

    private GridPane memberInfoLayout;
    private Scene memberScene;

    private Label idLabel;
    private Label nameLabel;
    private Label disciplineLabel;
    private Label birthdayLabel;
    private Label startDateLabel;
    private Label competitiveLabel;
    private Label activeLabel;
    private Label seniorityLabel;
    private Label emailAddressLabel;
    private Label phoneNumberLabel;
    private Label deficitLabel;
    private Label balanceLabel;
    private Label trainerLabel;


    private ListView<Member> memberDeficitListView;
    private ObservableList<Member> membersInDeficit;

    private Popup depositPopUp;

    private static DeficitMembers instance = null;

    private DeficitMembers() {
        layout = new GridPane();
        scene = new Scene(layout, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        memberInfoLayout = new GridPane();
        memberScene = new Scene(memberInfoLayout, Constants.WINDOW_WIDTH,
                Constants.WINDOW_HEIGHT);

        memberDeficitListView = new ListView<Member>();
        membersInDeficit = MemberList.membersInDeficit;
        memberDeficitListView.setItems(membersInDeficit.sorted());



        Button depositButton = new Button("Deposit");
        Button backButton = new Button("Back");
        Button memberInfoBackButton = new Button("Back");

        Button popUpOkButton = new Button("Okay");
        Button popUpCancelButton = new Button("Cancel");
        TextField popUpAmountTextField = new TextField();
        Label popUpDescription = new Label("Enter amount to deposit");

        VBox popUpVBox = new VBox();
        popUpVBox.getChildren().add(popUpDescription);
        popUpVBox.getChildren().add(popUpAmountTextField);
        popUpVBox.getChildren().add(popUpOkButton);
        popUpVBox.getChildren().add(popUpCancelButton);


        depositPopUp = new Popup();

        depositPopUp.getContent().addAll(popUpVBox);

        idLabel = new Label();
        nameLabel = new Label();
        disciplineLabel = new Label();
        birthdayLabel = new Label();
        startDateLabel = new Label();
        competitiveLabel = new Label();
        activeLabel = new Label();
        seniorityLabel = new Label();
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
        competitiveLabel.setTextFill(Color.WHITE);
        activeLabel.setTextFill(Color.WHITE);
        seniorityLabel.setTextFill(Color.WHITE);
        emailAddressLabel.setTextFill(Color.WHITE);
        phoneNumberLabel.setTextFill(Color.WHITE);
        deficitLabel.setTextFill(Color.WHITE);
        balanceLabel.setTextFill(Color.WHITE);
        trainerLabel.setTextFill(Color.WHITE);

        memberInfoLayout.setAlignment(Pos.CENTER);
        memberInfoLayout.setBackground(new Background(Constants.BACKGROUND_IMAGE));

        memberInfoLayout.add(idLabel, 0, 0);
        memberInfoLayout.add(nameLabel, 0, 1);
        memberInfoLayout.add(disciplineLabel, 0, 2);
        memberInfoLayout.add(birthdayLabel, 0, 3);
        memberInfoLayout.add(startDateLabel, 0, 4);
        memberInfoLayout.add(competitiveLabel, 0, 5);
        memberInfoLayout.add(activeLabel, 0, 6);
        memberInfoLayout.add(seniorityLabel, 0, 7);
        memberInfoLayout.add(emailAddressLabel, 0, 8);
        memberInfoLayout.add(phoneNumberLabel, 0, 9);
        memberInfoLayout.add(deficitLabel, 0, 10);
        memberInfoLayout.add(balanceLabel, 0, 11);
        memberInfoLayout.add(trainerLabel, 0, 12);
        memberInfoLayout.add(memberInfoBackButton, 0, 13);


        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(Constants.BACKGROUND_IMAGE));

        layout.add(memberDeficitListView, 0, 0);
        layout.add(depositButton, 0, 1);
        layout.add(backButton, 1, 1);

        backButton.setOnAction(click -> {
            depositPopUp.hide();
            Controller.setActiveScene(MainMenu.getInstance().getScene());
        });

        memberInfoBackButton.setOnAction(click ->
                Controller.setActiveScene(scene));

        depositButton.setOnAction(click -> {
            depositPopUp.show(Controller.getStage());
        });

        popUpCancelButton.setOnAction(click -> depositPopUp.hide());
        popUpOkButton.setOnAction(click -> {
            int amount = Integer.parseInt(popUpAmountTextField.getText());
            memberDeficitListView.getSelectionModel().getSelectedItem().addToBalance(amount);
            depositPopUp.hide();
            popUpAmountTextField.clear();

            IOWriter.writeFile(memberDeficitListView.getSelectionModel().getSelectedItem());

            if (memberDeficitListView.getSelectionModel().getSelectedItem().getBalance() < 0) {
                memberDeficitListView.getSelectionModel().getSelectedItem().setDeficit(false);
                MemberList.members.add(memberDeficitListView.getSelectionModel().getSelectedItem());
                MemberList.membersInDeficit.remove(MemberList.membersInDeficit.indexOf(memberDeficitListView.getSelectionModel().getSelectedItem()));
            }

            memberDeficitListView.setItems(MemberList.membersInDeficit.sorted());
        });

        memberDeficitListView.setOnMouseClicked(click -> {
            if (click.getButton().equals(MouseButton.PRIMARY)) {
                if (click.getClickCount() == 2) {
                    Controller.setActiveScene(memberScene);
                    displayMemberInfo(memberDeficitListView.getSelectionModel().getSelectedItem());
                }
            }
        });

    }

    private void displayMemberInfo(Member member) {
        idLabel.setText("ID: " + member.getId());
        nameLabel.setText("Name: " + member.getName());
        disciplineLabel.setText("Discipline: " + member.getDiscipline());
        birthdayLabel.setText("Birthday: " + member.getBirthday());
        startDateLabel.setText("Start date: " + member.getStartDate());
        competitiveLabel.setText("Competetive: " + member.isCompetetive());
        activeLabel.setText("Active: " + member.isActive());
        seniorityLabel.setText("Senority: " + member.isSeniority());
        emailAddressLabel.setText("Email: " + member.getEmail());
        phoneNumberLabel.setText("Phone: " + member.getPhoneNumber());
        deficitLabel.setText("Deficit: " + member.isDeficit());
        balanceLabel.setText("Balance: " + member.getBalance());
        trainerLabel.setText("Trainer: " + member.getAppointedTrainer());
    }

    public ObservableList<Member> getDeficitMembers() {
        return membersInDeficit;
    }

    public static DeficitMembers getInstance() {
        if (instance == null) {
            instance = new DeficitMembers();
        }

        return instance;
    }

    public Scene getScene() { return scene; }
}
