package sample;

import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

public class Constants {
    public static final int WINDOW_HEIGHT = 500;
    public static final int WINDOW_WIDTH = 500;
    public static final BackgroundImage BACKGROUND_IMAGE = new BackgroundImage(
            new Image("file:images/dolphin_pretty.png", WINDOW_WIDTH,
                    WINDOW_HEIGHT,
            false, true),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT, new BackgroundSize(WINDOW_WIDTH,
            WINDOW_HEIGHT, false, false, false, true));

    public static final String MEMBER_PATH = "data/members/";
    public static final String COMPETITION_PATH = "data/competitions/";

    public static final Stage STAGE = new Stage();
    public static final Controller CONTROLLER = new Controller(STAGE);
}
