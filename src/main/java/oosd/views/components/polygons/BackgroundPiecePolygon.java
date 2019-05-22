package oosd.views.components.polygons;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import oosd.views.View;

public class BackgroundPiecePolygon extends HexagonPiecePolygon {
    public BackgroundPiecePolygon() {
    	Random rand = new Random();
    	
        this.setFill(new ImagePattern(new Image(View.class.getResource(String.format("grass_" + "%d" + ".png", rand.nextInt(10))).toString())));
        this.setStrokeWidth(2);
        this.setStroke(Paint.valueOf("#706c1c"));
    }
}
