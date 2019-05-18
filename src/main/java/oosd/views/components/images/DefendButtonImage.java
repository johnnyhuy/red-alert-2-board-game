package oosd.views.components.images;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import oosd.views.View;

public class DefendButtonImage extends ImageView {
    public DefendButtonImage() {
        this.setFitWidth(20);
        this.setFitHeight(20);
        this.setImage(new Image(View.class.getResource("shield.png").toString()));
    }
}
