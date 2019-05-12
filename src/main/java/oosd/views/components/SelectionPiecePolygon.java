package oosd.views.components;

import javafx.scene.paint.Paint;

public class SelectionPiecePolygon extends HexagonPiecePolygon {
    public SelectionPiecePolygon() {
        this.setFill(Paint.valueOf("#00C400"));
        this.setOpacity(0.4);
    }
}
