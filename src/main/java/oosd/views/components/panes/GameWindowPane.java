package oosd.views.components.panes;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import oosd.views.View;

public class GameWindowPane extends BorderPane {
    public GameWindowPane() {
        Image image = new Image(View.class.getResource("menu.png").toString());
        BackgroundSize backgroundSize = new BackgroundSize(this.getWidth(), this.getHeight(), true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        this.setBackground(background);
    }
}
