package sample.Menues;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import sample.Constants;
import sample.Controller;
import sample.IO.IOWriter;
import sample.Member;
import sample.MemberList;
import sample.User.Trainer;
import sample.User.TrainerList;

// TODO: Read in file and put the data into the text fields
// write the changes to the file
public class EditMember {
    private GridPane layout;
    private Scene scene;

    private Label nameLabel;
    private Label lastNameLabel;
    private Label disciplineLabel;
    private Label birthdayLabel;
    private Label startDateLabel;
    private Label emailAddressLabel;
    private Label phoneNumberLabel;
    private Label balanceLabel;
    private Label trainerLabel;

    private TextField firstNameTextField;
    private TextField lastNameTextField;
    private ComboBox disciplineComboBox;
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

    private static EditMember instance = null;

    private EditMember() {
        layout = new GridPane();
        scene = new Scene(layout, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        nameLabel = new Label("First name");
        lastNameLabel = new Label("Last name");
        disciplineLabel = new Label("Discipline");
        birthdayLabel = new Label("Birthday");
        startDateLabel = new Label("Start date");
        emailAddressLabel = new Label("E-mail address");
        phoneNumberLabel = new Label("Phone number");
        balanceLabel = new Label("Balance");
        trainerLabel = new Label("Trainer");

        nameLabel.setTextFill(Color.WHITE);
        lastNameLabel.setTextFill(Color.WHITE);
        disciplineLabel.setTextFill(Color.WHITE);
        birthdayLabel.setTextFill(Color.WHITE);
        startDateLabel.setTextFill(Color.WHITE);
        emailAddressLabel.setTextFill(Color.WHITE);
        phoneNumberLabel.setTextFill(Color.WHITE);
        balanceLabel.setTextFill(Color.WHITE);
        trainerLabel.setTextFill(Color.WHITE);

        firstNameTextField = new TextField();
        lastNameTextField = new TextField();
        disciplineComboBox = new ComboBox();
        disciplineComboBox.setPromptText("Select discipline");
        disciplineComboBox.getItems().add("Freestyle");
        disciplineComboBox.getItems().add("Breaststroke");
        disciplineComboBox.getItems().add("Backstroke");

        TrainerList trainerList = new TrainerList();

        birthdayDatePicker = new DatePicker();
        startDatePicker = new DatePicker();

        emailAddressTextField = new TextField();
        phoneNumberTextField = new TextField();
        balanceTextField = new TextField();
        trainerComboBox = new ComboBox();
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

        // Disable the radio buttons, since we dont want to be able to
        // change their membership
        activeMembershipRadioButton.setDisable(true);
        passiveMembershipRadioButton.setDisable(true);
        competitiveSwimmerRadioButton.setDisable(true);
        exerciseSwimmerRadioButton.setDisable(true);

        layout.setBackground(new Background(Constants.BACKGROUND_IMAGE));

        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10.0);
        layout.setHgap(5.0);

        layout.add(nameLabel, 0, 0);
        layout.add(lastNameLabel, 0, 1);
        layout.add(disciplineLabel, 0, 2);
        layout.add(birthdayLabel, 0, 3);
        layout.add(startDateLabel, 0, 4);
        layout.add(emailAddressLabel, 0, 5);
        layout.add(phoneNumberLabel, 0, 6);
        layout.add(balanceLabel, 0, 7);
        layout.add(trainerLabel, 0, 8);

        layout.add(firstNameTextField, 1, 0);
        layout.add(lastNameTextField, 1, 1);
        layout.add(disciplineComboBox, 1, 2);
        layout.add(birthdayDatePicker, 1, 3);
        layout.add(startDatePicker, 1, 4);
        layout.add(emailAddressTextField, 1, 5);
        layout.add(phoneNumberTextField, 1, 6);
        layout.add(balanceTextField, 1, 7);
        layout.add(trainerComboBox, 1, 8);

        layout.add(activeMembershipRadioButton, 0, 10);
        layout.add(passiveMembershipRadioButton, 1, 10);
        layout.add(competitiveSwimmerRadioButton, 0, 11);
        layout.add(exerciseSwimmerRadioButton, 1, 11);
        layout.add(finishButton, 0, 12);
        layout.add(backButton, 1, 12);



        finishButton.setOnAction(click -> {
            boolean activeMembership;
            if (membershipToggleGroup.getSelectedToggle() == activeMembershipRadioButton) {
                activeMembership = true;
            } else {
                activeMembership = false;
            }

            boolean competitiveSwimmer;
            if (swimTypeToggleGroup.getSelectedToggle() == competitiveSwimmerRadioButton) {
                competitiveSwimmer = true;
            } else {
                competitiveSwimmer = false;
            }

            Member member =
                    new Member(firstNameTextField.getText()
                            + " " + lastNameTextField.getText(),
                            disciplineComboBox.getValue().toString(),
                            birthdayDatePicker.getValue(), startDatePicker.getValue(),
                            competitiveSwimmer,
                            activeMembership,
                            emailAddressTextField.getText(),
                            phoneNumberTextField.getText(),
                            Integer.parseInt(balanceTextField.getText()),
                            trainerComboBox.getSelectionModel().getSelectedItem());

            IOWriter.writeFile(member);
            MemberList.members.add(member);
        });

        backButton.setOnAction(click ->
            Controller.setActiveScene(ViewMember.getInstance().getScene()));
    }

    public static EditMember getInstance() {
        if (instance == null) {
            instance = new EditMember();
        }

        return instance;
    }

    public Scene getScene() { return scene; }
}