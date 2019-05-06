package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class MemberCreation {
    private GridPane root;
    private Scene scene;

    private Label firstNameLabel;
    private Label lastNameLabel;
    private Label emailAddressLabel;
    private Label phoneNumberLabel;
    private Label disciplineLabel;

    private TextField firstNameTextField;
    private TextField lastnameTextField;
    private TextField emailAddressTextField;
    private TextField phoneNumberTextField;
    private TextField disciplineTextField;

    private Button finishButton;
    private Button cancelButton;

    private static MemberCreation instance = null;

    private MemberCreation() {
        root = new GridPane();
        scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        firstNameLabel = new Label("First name");
        lastNameLabel = new Label("Last name");
        emailAddressLabel = new Label("E-mail address");
        phoneNumberLabel = new Label("Phone number");
        disciplineLabel = new Label("Discipline");

        firstNameLabel.setTextFill(Color.WHITE);
        lastNameLabel.setTextFill(Color.WHITE);
        emailAddressLabel.setTextFill(Color.WHITE);
        phoneNumberLabel.setTextFill(Color.WHITE);
        disciplineLabel.setTextFill(Color.WHITE);

        firstNameTextField = new TextField();
        lastnameTextField = new TextField();
        emailAddressTextField = new TextField();
        phoneNumberTextField = new TextField();
        disciplineTextField = new TextField();

        finishButton = new Button("Finish");
        cancelButton = new Button("Cancel");

        root.setBackground(new Background(Constants.BACKGROUND_IMAGE));

        root.setAlignment(Pos.CENTER);

        root.add(firstNameLabel, 0, 0);
        root.add(lastNameLabel, 0, 1);
        root.add(emailAddressLabel, 0, 2);
        root.add(phoneNumberLabel, 0, 3);
        root.add(disciplineLabel, 0, 4);

        root.add(firstNameTextField, 1, 0);
        root.add(lastnameTextField, 1, 1);
        root.add(emailAddressTextField, 1, 2);
        root.add(phoneNumberTextField, 1, 3);
        root.add(disciplineTextField, 1, 4);

        root.add(finishButton, 0, 6);
        root.add(cancelButton, 1, 6);

        // NOTE: need to make use of our IO classes, but dont have them made yet
        // to store our member on the harddisk
        finishButton.setOnAction(click -> System.out.println("Member created"));
        cancelButton.setOnAction(click -> Controller.setActiveScene(MainMenu.getInstance().getScene()));

    }

    public static MemberCreation getInstance() {
        if (instance == null) {
            instance = new MemberCreation();
        }

        return instance;
    }

    public Scene getScene() { return scene; }
}
