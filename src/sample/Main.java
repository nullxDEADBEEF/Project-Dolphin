package sample;

import javafx.application.Application;
import javafx.stage.Stage;

// TODO: implement creation of competitions (trainer) (3)
// TODO: implement discipline top 5 across all disciplines (trainer) (4)
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
