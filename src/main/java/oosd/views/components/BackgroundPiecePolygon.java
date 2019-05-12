package oosd.views.components;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import oosd.views.View;

public class BackgroundPiecePolygon extends HexagonPiecePolygon {
    public BackgroundPiecePolygon() {
        this.setFill(new ImagePattern(new Image(View.class.getResource("grass.png").toString())));
        this.setStrokeWidth(2);
        this.setStroke(Paint.valueOf("#706c1c"));
    }
}
