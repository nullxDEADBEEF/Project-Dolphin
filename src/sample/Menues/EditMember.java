package sample.Menues;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import sample.Constants;
import sample.Controller;
import sample.IO.IOReader;
import sample.IO.IOWriter;
import sample.Member;
import sample.MemberList;
import sample.User.Trainer;
import sample.User.TrainerList;

public class EditMember {
    private GridPane layout;
    private Scene scene;

    private TextField nameTextField;
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

    private static Member selectedMember;
    private Member memberToEdit;

    private ToggleGroup membershipToggleGroup;
    private ToggleGroup swimTypeToggleGroup;

    private static EditMember instance = null;

    private EditMember() {

        layout = new GridPane();
        scene = new Scene(layout, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        Label nameLabel = new Label("Name");
        Label disciplineLabel = new Label("Discipline");
        Label birthdayLabel = new Label("Birthday");
        Label startDateLabel = new Label("Start date");
        Label emailAddressLabel = new Label("E-mail address");
        Label phoneNumberLabel = new Label("Phone number");
        Label balanceLabel = new Label("Balance");
        Label trainerLabel = new Label("Trainer");

        nameLabel.setTextFill(Color.WHITE);
        disciplineLabel.setTextFill(Color.WHITE);
        birthdayLabel.setTextFill(Color.WHITE);
        startDateLabel.setTextFill(Color.WHITE);
        emailAddressLabel.setTextFill(Color.WHITE);
        phoneNumberLabel.setTextFill(Color.WHITE);
        balanceLabel.setTextFill(Color.WHITE);
        trainerLabel.setTextFill(Color.WHITE);

        nameTextField = new TextField();
        disciplineComboBox = new ComboBox();
        disciplineComboBox.setPromptText("Select discipline");
        disciplineComboBox.getItems().add("Freestyle");
        disciplineComboBox.getItems().add("Breaststroke");
        disciplineComboBox.getItems().add("Backstroke");

        birthdayDatePicker = new DatePicker();
        startDatePicker = new DatePicker();

        emailAddressTextField = new TextField();
        phoneNumberTextField = new TextField();
        balanceTextField = new TextField();
        trainerComboBox = new ComboBox();
        trainerComboBox.setPromptText("Select trainer");

        for (Trainer trainer : TrainerList.getTrainers()) {
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
            Alert memberEditedAlert = new Alert(Alert.AlertType.CONFIRMATION);
            memberEditedAlert.setContentText("Member edited!");

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
                    new Member(nameTextField.getText(),
                            disciplineComboBox.getValue().toString(),
                            birthdayDatePicker.getValue(), startDatePicker.getValue(),
                            competitiveSwimmer,
                            activeMembership,
                            emailAddressTextField.getText(),
                            phoneNumberTextField.getText(),
                            Integer.parseInt(balanceTextField.getText()),
                            trainerComboBox.getSelectionModel().getSelectedItem());

            // NOTE: we set the member id here so that we get the correct id
            // therefore writing to the correct file
            member.setId(memberToEdit.getId());
            IOWriter.writeFile(member);

            if (member.getBalance() <= 0) {
                // Remove the old data in the list view
                MemberList.members.remove(selectedMember);

                // Add the new data in the list view
                MemberList.members.add(member);
            } else if (member.getBalance() > 0) {
                MemberList.members.remove(selectedMember);
                MemberList.membersInDeficit.add(member);
            }

            // Sort the list, using natural ordering
            ViewMember.getInstance().getMembersListView().setItems(MemberList.members.sorted());

            clearFields();
            memberEditedAlert.showAndWait();
        });

        backButton.setOnAction(click -> {
            ViewMember.getInstance().getMembersListView().refresh();
            clearFields();
            Controller.setActiveScene(ViewMember.getInstance().getScene());
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
    }

    public void fillMemberData() {
        memberToEdit =
                IOReader.readMemberFile(Constants.MEMBER_PATH + selectedMember.getId());

        // Find discipline
        for (int i = 0; i < disciplineComboBox.getItems().size(); i++) {
            if (disciplineComboBox.getItems().get(i).toString().equalsIgnoreCase(memberToEdit.getDiscipline())) {
                disciplineComboBox.getSelectionModel().select(i);
                break;
            }
        }

        // Find trainer
        for (int i = 0; i < TrainerList.getTrainers().size(); i++) {
            if (TrainerList.getTrainers().get(i) == memberToEdit.getAppointedTrainer()) {
                trainerComboBox.getSelectionModel().select(i);
                break;
            }
        }

        // Fill in the rest of the data
        nameTextField.setText(memberToEdit.getName());
        birthdayDatePicker.getEditor().setText(memberToEdit.getBirthday().toString());
        startDatePicker.getEditor().setText(memberToEdit.getStartDate().toString());
        emailAddressTextField.setText(memberToEdit.getEmail());
        phoneNumberTextField.setText(memberToEdit.getPhoneNumber());
        balanceTextField.setText(String.valueOf(memberToEdit.getBalance()));
    }

    public static EditMember getInstance() {
        if (instance == null) {
            instance = new EditMember();
        }

        return instance;
    }

    // NOTE: we make it static since the constructor will be run first
    // therefore giving a null exception before we can set the selected member
    // to edit from
    public static void setSelectedMember(Member member) {
        selectedMember = member;
    }

    public Scene getScene() { return scene; }
}
