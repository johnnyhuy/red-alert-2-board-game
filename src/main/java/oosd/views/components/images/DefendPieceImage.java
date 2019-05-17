package oosd.views.components.images;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import oosd.views.View;

public class DefendPieceImage extends ImageView {
    public DefendPieceImage() {
        this.setX(20);
        this.setY(38);
        this.setFitWidth(20);
        this.setFitHeight(20);
        this.setImage(new Image(View.class.getResource("shield_2.png").toString()));
        this.setVisible(false);
    }

    /**
     * Peek a' boo!
     */
    public void show() {
        setVisible(true);
    }

    /**
     * Hide the polygon.
     */
    public void hide() {
        setVisible(false);
    }
}
