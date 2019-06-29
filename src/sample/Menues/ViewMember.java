package sample.Menues;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import sample.*;
import sample.IO.IOWriter;

// handles the setup of the view member page
public class ViewMember {
    private GridPane layout;
    private GridPane memberInfoLayout;
    private Scene scene;
    private Scene memberScene;

    private ListView<Member> membersListView;
    private Button editButton;
    private Button deleteButton;
    private Button backButton;
    private Button memberInfobackButton;

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

    private Popup depositPopUp;

    private IOWriter ioWriter;
    private EditMember editMember;

    public ViewMember() {
        layout = new GridPane();
        memberInfoLayout = new GridPane();

        ioWriter = new IOWriter();
        editMember = new EditMember();

        // setup GUI

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

        scene = new Scene(layout, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        memberScene = new Scene(memberInfoLayout, Constants.WINDOW_WIDTH,
                Constants.WINDOW_HEIGHT);

        Button depositButton = new Button("Deposit");

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

        membersListView = new ListView<Member>();
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
        memberInfoLayout.add(competitiveLabel, 0, 5);
        memberInfoLayout.add(activeLabel, 0, 6);
        memberInfoLayout.add(seniorityLabel, 0, 7);
        memberInfoLayout.add(emailAddressLabel, 0, 8);
        memberInfoLayout.add(phoneNumberLabel, 0, 9);
        memberInfoLayout.add(deficitLabel, 0, 10);
        memberInfoLayout.add(balanceLabel, 0, 11);
        memberInfoLayout.add(trainerLabel, 0, 12);
        memberInfoLayout.add(memberInfobackButton, 0, 13);

        layout.add(membersListView, 0, 0);
        layout.add(editButton, 0, 1);
        layout.add(depositButton, 1, 1);
        layout.add(deleteButton, 1, 2);
        layout.add(backButton, 0, 4);

        membersListView.setItems(MemberList.members.sorted());


        backButton.setOnAction(click -> {
            depositPopUp.hide();
            Constants.CONTROLLER.setActiveScene(new MainMenu().getScene());
        });

        // Deletes a member from the system
        deleteButton.setOnAction(click -> {
            Member fileName =
                    membersListView.getSelectionModel().getSelectedItem();

            ioWriter.deleteFile(fileName);

            MemberList.members.remove(membersListView.getSelectionModel().getSelectedItem());

            switch (membersListView.getSelectionModel().getSelectedItem().getDiscipline().toString()) {
                case "Freestyle":
                    CompetitorList.freestyleCompetitors.remove(
                            CompetitorList.freestyleCompetitors
                                    .indexOf(membersListView.getSelectionModel().getSelectedItem()));
                    break;
                case "Backstroke":
                    CompetitorList.backstrokeCompetitors.remove(
                            CompetitorList.backstrokeCompetitors
                                    .indexOf(membersListView.getSelectionModel().getSelectedItem()));
                    break;
                case "Breaststroke":
                    CompetitorList.breaststrokeCompetitors.remove(
                            CompetitorList.breaststrokeCompetitors
                                    .indexOf(membersListView.getSelectionModel().getSelectedItem()));
                    break;
                default:
                    System.out.println("Discipline not recognized");
                    break;
            }
        });

        // edits a specified member's data
        editButton.setOnAction(click -> {
            editMember.setSelectedMember(membersListView.getSelectionModel().getSelectedItem());
            editMember.fillMemberData();
            Constants.CONTROLLER.setActiveScene(editMember.getScene());
        });

        // show deposit box
        depositButton.setOnAction(click -> {
            depositPopUp.show(Constants.CONTROLLER.getStage());
        });

        popUpCancelButton.setOnAction(click -> depositPopUp.hide());

        // deposit payment
        popUpOkButton.setOnAction(click -> {
            int amount = Integer.parseInt(popUpAmountTextField.getText());
            membersListView.getSelectionModel().getSelectedItem().addToBalance(amount);
            depositPopUp.hide();
            popUpAmountTextField.clear();

            ioWriter.writeFile(membersListView.getSelectionModel().getSelectedItem());

            if (membersListView.getSelectionModel().getSelectedItem().getBalance() > 0) {
                membersListView.getSelectionModel().getSelectedItem().setDeficit(true);
                MemberList.membersInDeficit.add(membersListView.getSelectionModel().getSelectedItem());
                MemberList.members.remove(MemberList.members.indexOf(membersListView.getSelectionModel().getSelectedItem()));
            }

            membersListView.setItems(MemberList.members.sorted());
        });

        memberInfobackButton.setOnAction(click -> {
            Constants.CONTROLLER.setActiveScene(scene);
        });

        // NOTE: MouseButton.PRIMARY means left click
        // displays detailed information about a member
        membersListView.setOnMouseClicked(click -> {
            if (click.getButton().equals(MouseButton.PRIMARY)) {
                if (click.getClickCount() == 2) {
                    Constants.CONTROLLER.setActiveScene(memberScene);
                    displayMemberInfo(membersListView.getSelectionModel().getSelectedItem());
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
        competitiveLabel.setText("Competitive: " + member.isCompetetive());
        activeLabel.setText("Active: " + member.isActive());
        seniorityLabel.setText("Senority: " + member.isSeniority());
        emailAddressLabel.setText("Email: " + member.getEmail());
        phoneNumberLabel.setText("Phone: " + member.getPhoneNumber());
        deficitLabel.setText("Deficit: " + member.isDeficit());
        balanceLabel.setText("Balance: " + member.getBalance());
        trainerLabel.setText("Trainer: " + member.getAppointedTrainer());
    }

    public ListView<Member> getMembersListView() {
        return membersListView;
    }

    public Scene getScene() { return scene; }
}
