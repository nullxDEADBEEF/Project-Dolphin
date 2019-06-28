package sample.Menues;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import sample.*;
import sample.Disciplines.Discipline;
import sample.Disciplines.DisciplineList;
import sample.IO.IOWriter;
import sample.User.Trainer;
import sample.User.TrainerList;

public class MemberCreation {
    private GridPane layout;
    private Scene scene;

    private Label nameLabel;
    private Label disciplineLabel;
    private Label birthdayLabel;
    private Label startDateLabel;
    private Label emailAddressLabel;
    private Label phoneNumberLabel;
    private Label balanceLabel;
    private Label trainerLabel;

    private TextField nameTextField;
    private ComboBox<Discipline> disciplineComboBox;
    private DatePicker birthdayDatePicker;
    private DatePicker startDatePicker;
    private TextField emailAddressTextField;
    private TextField phoneNumberTextField;
    private TextField balanceTextField;
    private ComboBox<Trainer> trainerComboBox;

    private Button finishButton;
    private Button backButton;
    private RadioButton activeMembershipRadioButton;
    private RadioButton passiveMembershipRadioButton;
    private RadioButton competitiveSwimmerRadioButton;
    private RadioButton exerciseSwimmerRadioButton;

    ToggleGroup membershipToggleGroup;
    ToggleGroup swimTypeToggleGroup;

    public MemberCreation() {
        layout = new GridPane();
        scene = new Scene(layout, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        DisciplineList disciplineList = new DisciplineList();
        IOWriter ioWriter = new IOWriter();
        TrainerList trainerList = new TrainerList();

        nameLabel = new Label("Name");
        disciplineLabel = new Label("Discipline");
        birthdayLabel = new Label("Birthday");
        startDateLabel = new Label("Start date");
        emailAddressLabel = new Label("E-mail address");
        phoneNumberLabel = new Label("Phone number");
        balanceLabel = new Label("Balance");
        trainerLabel = new Label("Trainer");

        nameLabel.setTextFill(Color.WHITE);
        disciplineLabel.setTextFill(Color.WHITE);
        birthdayLabel.setTextFill(Color.WHITE);
        startDateLabel.setTextFill(Color.WHITE);
        emailAddressLabel.setTextFill(Color.WHITE);
        phoneNumberLabel.setTextFill(Color.WHITE);
        balanceLabel.setTextFill(Color.WHITE);
        trainerLabel.setTextFill(Color.WHITE);

        nameTextField = new TextField();
        disciplineComboBox = new ComboBox<>();
        disciplineComboBox.setPromptText("Select discipline");
        disciplineComboBox.getItems().addAll(disciplineList.getDisciplines());

        birthdayDatePicker = new DatePicker();
        startDatePicker = new DatePicker();

        emailAddressTextField = new TextField();
        phoneNumberTextField = new TextField();
        balanceTextField = new TextField();
        trainerComboBox = new ComboBox<>();
        trainerComboBox.setPromptText("Select trainer");
        for (Trainer trainer : trainerList.getTrainers()) {
            trainerComboBox.getItems().add(trainer);
        }

        finishButton = new Button("Finish");
        backButton = new Button("Back");

        activeMembershipRadioButton = new RadioButton("Active");
        activeMembershipRadioButton.setStyle("-fx-text-fill: white;");

        passiveMembershipRadioButton = new RadioButton("Passive");
        passiveMembershipRadioButton.setStyle("-fx-text-fill: white;");

        competitiveSwimmerRadioButton = new RadioButton("Competitive");
        competitiveSwimmerRadioButton.setStyle("-fx-text-fill: white;");

        exerciseSwimmerRadioButton = new RadioButton("Exercise");
        exerciseSwimmerRadioButton.setStyle("-fx-text-fill: white;");

        // We make a toggle group to that only one of them can be selected
        membershipToggleGroup = new ToggleGroup();
        activeMembershipRadioButton.setToggleGroup(membershipToggleGroup);
        passiveMembershipRadioButton.setToggleGroup(membershipToggleGroup);

        swimTypeToggleGroup = new ToggleGroup();
        competitiveSwimmerRadioButton.setToggleGroup(swimTypeToggleGroup);
        exerciseSwimmerRadioButton.setToggleGroup(swimTypeToggleGroup);

        layout.setBackground(new Background(Constants.BACKGROUND_IMAGE));

        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10.0);
        layout.setHgap(5.0);

        layout.add(nameLabel, 0, 0);
        layout.add(disciplineLabel, 0, 1);
        layout.add(birthdayLabel, 0, 2);
        layout.add(startDateLabel, 0, 3);
        layout.add(emailAddressLabel, 0, 4);
        layout.add(phoneNumberLabel, 0, 5);
        layout.add(balanceLabel, 0, 6);
        layout.add(trainerLabel, 0, 7);

        layout.add(nameTextField, 1, 0);
        layout.add(disciplineComboBox, 1, 1);
        layout.add(birthdayDatePicker, 1, 2);
        layout.add(startDatePicker, 1, 3);
        layout.add(emailAddressTextField, 1, 4);
        layout.add(phoneNumberTextField, 1, 5);
        layout.add(balanceTextField, 1, 6);
        layout.add(trainerComboBox, 1, 7);

        layout.add(activeMembershipRadioButton, 0, 10);
        layout.add(passiveMembershipRadioButton, 1, 10);
        layout.add(competitiveSwimmerRadioButton, 0, 11);
        layout.add(exerciseSwimmerRadioButton, 1, 11);
        layout.add(finishButton, 0, 12);
        layout.add(backButton, 1, 12);

        finishButton.setOnAction(click -> {
            Alert memberCreatedAlert  =
                    new Alert(Alert.AlertType.CONFIRMATION);
            memberCreatedAlert.setContentText("Member created!");

            boolean activeMembership =
                    membershipToggleGroup.getSelectedToggle() == activeMembershipRadioButton;

            boolean competitiveSwimmer =
                    swimTypeToggleGroup.getSelectedToggle() == competitiveSwimmerRadioButton;


            Trainer trainer =
                    trainerComboBox.getSelectionModel().getSelectedItem();

            Member member =
                    new Member(nameTextField.getText(),
                            disciplineComboBox.getValue(),
                            birthdayDatePicker.getValue(), startDatePicker.getValue(),
                            competitiveSwimmer,
                            activeMembership,
                            emailAddressTextField.getText(),
                            phoneNumberTextField.getText(),
                            Integer.parseInt(balanceTextField.getText()),
                            trainer);

            member.generateId();

            ioWriter.writeFile(member);
            clearFields();

            if (member.isCompetetive() && !member.isDeficit()) {
                MemberList.competitiveMembers.add(member);
            }

            if (member.getBalance() > 0) {
                MemberList.membersInDeficit.add(member);
            } else {
                MemberList.members.add(member);
            }



            memberCreatedAlert.showAndWait();
        });

        backButton.setOnAction(click -> {
            Constants.CONTROLLER.setActiveScene(new MainMenu().getScene());
            clearFields();
        });
    }

    private void clearFields() {
        nameTextField.clear();
        emailAddressTextField.clear();
        phoneNumberTextField.clear();
        balanceTextField.clear();
        trainerComboBox.getSelectionModel().clearSelection();
        disciplineComboBox.getSelectionModel().clearSelection();

        birthdayDatePicker.getEditor().clear();
        startDatePicker.getEditor().clear();

        activeMembershipRadioButton.setSelected(false);
        passiveMembershipRadioButton.setSelected(false);
        competitiveSwimmerRadioButton.setSelected(false);
        exerciseSwimmerRadioButton.setSelected(false);
    }

    public Scene getScene() { return scene; }
}
