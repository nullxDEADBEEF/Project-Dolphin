package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        MainMenu mainMenu = new MainMenu();

        stage.setTitle("Swimclub Dolphin - Administrative System");
        stage.setScene(mainMenu.getScene());
        stage.show();
    }
}
