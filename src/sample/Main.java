package sample;

import javafx.application.Application;
import javafx.stage.Stage;

// TODO: make logins for the different users
// TODO: make login check
// TODO: implement creation of competitions (trainer)
// TODO: implement depositting (cashier)
// TODO: implement discipline top 5 across all disciplines (trainer)
// TODO: show people in deficit (cashier)
// TODO: implemement kontingent(wording) pricing
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Controller controller = new Controller(stage);

        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("Swimclub Dolphin - Administrative System");
        stage.setScene(controller.getActiveScene());
        stage.show();
    }
}
