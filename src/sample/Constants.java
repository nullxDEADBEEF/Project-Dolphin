package sample;

import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class Constants {
    static final int WINDOW_HEIGHT = 500;
    static final int WINDOW_WIDTH = 500;
    static final BackgroundImage BACKGROUND_IMAGE = new BackgroundImage(
            new Image("file:images/dolphin.png", WINDOW_WIDTH, WINDOW_HEIGHT,
            false, true),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
}
