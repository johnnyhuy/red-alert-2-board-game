package oosd.views.components.polygons;

import javafx.scene.paint.Paint;

public class SelectionPiecePolygon extends HexagonPiecePolygon {
    public SelectionPiecePolygon() {
        this.setFill(Paint.valueOf("#00C400"));
        this.setOpacity(0.4);
    }

    public void fillGreen() {
        setFill(Paint.valueOf("#00C400"));
    }

    public void fillRed() {
        setFill(Paint.valueOf("#FF0000"));
    }
}
