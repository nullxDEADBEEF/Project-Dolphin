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

        // NOTE: for some reason the background gets a small tearing
        // on the right side when applying the resizable property
        // but disappears when you go to the next scene
        stage.setResizable(false);
        stage.setTitle("Swimclub Dolphin - Administrative System");
        stage.setScene(controller.getActiveScene());
        stage.show();
    }
}
