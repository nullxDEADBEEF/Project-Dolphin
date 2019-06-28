package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.IO.IOReader;
import sample.User.TrainerList;

import java.io.FileNotFoundException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            IOReader ioReader = new IOReader();
            TrainerList trainerList = new TrainerList();

            ioReader.loadMembersFromFile();
            ioReader.loadMemberTypes();
            ioReader.loadCompetitionsFromFile();
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        stage = Constants.STAGE;
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("Swimclub Dolphin - Administrative System");
        stage.setScene(Constants.CONTROLLER.getActiveScene());
        stage.show();
    }
}
