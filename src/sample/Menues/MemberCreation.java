package sample.Menues;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import sample.Constants;
import sample.Controller;

public class MemberCreation {
    private GridPane layout;
    private Scene scene;

    private Label idLabel;
    private Label nameLabel;
    private Label lastNameLabel;
    private Label disciplineLabel;
    private Label birthdayLabel;
    private Label startDateLabel;
    private Label emailAddressLabel;
    private Label phoneNumberLabel;
    private Label balanceLabel;
    private Label trainerLabel;

    private TextField idTextField;
    private TextField firstNameTextField;
    private TextField lastnameTextField;
    private TextField disciplineTextField;
    private DatePicker birthdayDatePicker;
    private DatePicker startDatePicker;
    private TextField emailAddressTextField;
    private TextField phoneNumberTextField;
    private TextField balanceTextField;
    private TextField trainerTextField;

    private Button finishButton;
    private Button cancelButton;

    private static MemberCreation instance = null;

    private MemberCreation() {
        layout = new GridPane();
        scene = new Scene(layout, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        idLabel = new Label("ID");
        nameLabel = new Label("First name");
        lastNameLabel = new Label("Last name");
        disciplineLabel = new Label("Discipline");
        birthdayLabel = new Label("Birthday");
        startDateLabel = new Label("Start date");
        emailAddressLabel = new Label("E-mail address");
        phoneNumberLabel = new Label("Phone number");
        balanceLabel = new Label("Balance");
        trainerLabel = new Label("Trainer");

        idLabel.setTextFill(Color.WHITE);
        nameLabel.setTextFill(Color.WHITE);
        lastNameLabel.setTextFill(Color.WHITE);
        disciplineLabel.setTextFill(Color.WHITE);
        birthdayLabel.setTextFill(Color.WHITE);
        startDateLabel.setTextFill(Color.WHITE);
        emailAddressLabel.setTextFill(Color.WHITE);
        phoneNumberLabel.setTextFill(Color.WHITE);
        balanceLabel.setTextFill(Color.WHITE);
        trainerLabel.setTextFill(Color.WHITE);

        idTextField = new TextField();
        firstNameTextField = new TextField();
        lastnameTextField = new TextField();
        disciplineTextField = new TextField();
        birthdayDatePicker = new DatePicker();
        startDatePicker = new DatePicker();
        emailAddressTextField = new TextField();
        phoneNumberTextField = new TextField();
        balanceTextField = new TextField();
        trainerTextField = new TextField();

        finishButton = new Button("Finish");
        cancelButton = new Button("Cancel");

        layout.setBackground(new Background(Constants.BACKGROUND_IMAGE));

        layout.setAlignment(Pos.CENTER);

        layout.add(idLabel, 0, 0);
        layout.add(nameLabel, 0, 1);
        layout.add(lastNameLabel, 0, 2);
        layout.add(disciplineLabel, 0, 3);
        layout.add(birthdayLabel, 0, 4);
        layout.add(startDateLabel, 0, 5);
        layout.add(emailAddressLabel, 0, 6);
        layout.add(phoneNumberLabel, 0, 7);
        layout.add(balanceLabel, 0, 8);
        layout.add(trainerLabel, 0, 9);

        layout.add(idTextField, 1, 0);
        layout.add(firstNameTextField, 1, 1);
        layout.add(lastnameTextField, 1, 2);
        layout.add(disciplineTextField, 1, 3);
        layout.add(birthdayDatePicker, 1, 4);
        layout.add(startDatePicker, 1, 5);
        layout.add(emailAddressTextField, 1, 6);
        layout.add(phoneNumberTextField, 1, 7);
        layout.add(balanceTextField, 1, 8);
        layout.add(trainerTextField, 1, 9);

        layout.add(finishButton, 0, 10);
        layout.add(cancelButton, 1, 10);

        // TODO: write member info into a file when "finish" is clicked
        finishButton.setOnAction(click -> System.out.println("Member created"));
        cancelButton.setOnAction(click -> {
                Controller.setActiveScene(MainMenu.getInstance().getScene());
                clearTextFields();
        });


    }

    private void clearTextFields() {
        firstNameTextField.clear();
        lastnameTextField.clear();
        emailAddressTextField.clear();
        phoneNumberTextField.clear();
        disciplineTextField.clear();
    }

    public static MemberCreation getInstance() {
        if (instance == null) {
            instance = new MemberCreation();
        }

        return instance;
    }

    public Scene getScene() { return scene; }
}
