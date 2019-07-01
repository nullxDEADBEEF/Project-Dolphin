package sample.Menues;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import sample.Constants;
import sample.Disciplines.Discipline;
import sample.Disciplines.DisciplineList;
import sample.IO.IOReader;
import sample.IO.IOWriter;
import sample.Member;
import sample.Dataholder.MemberList;
import sample.User.Trainer;
import sample.User.TrainerList;

// handles the setup of the edit member page
public class EditMember implements IEditMember, IScene {
    private GridPane layout;
    private Scene scene;

    private TextField nameTextField;
    private ComboBox<Discipline> disciplineComboBox;
    private DatePicker birthdayDatePicker;
    private DatePicker startDatePicker;
    private TextField emailAddressTextField;
    private TextField phoneNumberTextField;
    private TextField balanceTextField;
    private ComboBox<Trainer> trainerComboBox;

    private Label nameLabel;
    private Label disciplineLabel;
    private Label birthdayLabel;
    private Label startDateLabel;
    private Label emailAddressLabel;
    private Label phoneNumberLabel;
    private Label balanceLabel;
    private Label trainerLabel;

    private Button finishButton;
    private Button backButton;
    private RadioButton activeMembershipRadioButton;
    private RadioButton passiveMembershipRadioButton;
    private RadioButton competitiveSwimmerRadioButton;
    private RadioButton exerciseSwimmerRadioButton;

    private Member selectedMember;
    private Member memberToEdit;

    private ToggleGroup membershipToggleGroup;
    private ToggleGroup swimTypeToggleGroup;

    private IOReader ioReader;

    private TrainerList trainerList;

    public EditMember() {
        layout = new GridPane();
        scene = new Scene(layout, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        ioReader = new IOReader();
        IOWriter ioWriter = new IOWriter();
        DisciplineList disciplineList = new DisciplineList();
        trainerList = new TrainerList();

        // setup GUI

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

        // Disable the radio buttons, since we dont want to be able to
        // change their membership
        activeMembershipRadioButton.setDisable(true);
        passiveMembershipRadioButton.setDisable(true);
        competitiveSwimmerRadioButton.setDisable(true);
        exerciseSwimmerRadioButton.setDisable(true);

        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10.0);
        layout.setHgap(5.0);

        setupPageElements();


        // edit member data with new information
        finishButton.setOnAction(click -> {
            Alert memberEditedAlert = new Alert(Alert.AlertType.CONFIRMATION);
            memberEditedAlert.setContentText("Member edited!");

            boolean activeMembership =
                    membershipToggleGroup.getSelectedToggle() == activeMembershipRadioButton;

            boolean competitiveSwimmer =
                    swimTypeToggleGroup.getSelectedToggle() == competitiveSwimmerRadioButton;

            Member member =
                    new Member(nameTextField.getText(),
                            disciplineComboBox.getValue(),
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
            ioWriter.writeFile(member);

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
            new ViewMember().getMembersListView().setItems(MemberList.members.sorted());

            clearFields();
            memberEditedAlert.showAndWait();
        });

        backButton.setOnAction(click -> {
            ViewMember viewMember = new ViewMember();
            viewMember.getMembersListView().refresh();
            clearFields();
            Constants.CONTROLLER.setActiveScene(viewMember.getScene());
        });
    }

    private void setupPageElements() {
        layout.setBackground(new Background(Constants.BACKGROUND_IMAGE));

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
    }

    /**
     * clears the text boxes to avoid data staying in them
     */
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

    /**
     * Fills the member data into the GUI elements
     */
    public void fillMemberData() {
        memberToEdit =
                ioReader.readMemberFile(Constants.MEMBER_PATH + selectedMember.getId());

        // Find discipline
        for (int i = 0; i < disciplineComboBox.getItems().size(); i++) {
            if (disciplineComboBox.getItems().get(i).toString().equalsIgnoreCase(memberToEdit.getDiscipline().toString())) {
                disciplineComboBox.getSelectionModel().select(i);
                break;
            }
        }

        // Find trainer
        for (int i = 0; i < trainerList.getTrainers().size(); i++) {
            if (trainerList.getTrainers().get(i) == memberToEdit.getAppointedTrainer()) {
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

    public void setSelectedMember(Member member) {
        selectedMember = member;
    }

    public Scene getScene() { return scene; }
}
