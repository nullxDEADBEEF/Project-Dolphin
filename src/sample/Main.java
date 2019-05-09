package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Controller controller = new Controller(stage);

        stage.setResizable(false);
        stage.setTitle("Swimclub Dolphin - Administrative System");
        stage.setScene(controller.getActiveScene());
        stage.show();
    }
}
