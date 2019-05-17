package oosd.views.components;

import javafx.scene.shape.Polygon;

/**
 * Design pattern: template pattern used by inheriting classes and modifying logic to differentiate objects
 */
public class HexagonPiecePolygon extends Polygon {
    public HexagonPiecePolygon() {
        double x = 0;
        double y = 0;

        this.getPoints().addAll(
                x, y,
                x + Hexagon.SIZE, y,
                x + Hexagon.GAP, y + Hexagon.HALF_INCREMENT,
                x + Hexagon.SIZE, y + Hexagon.FULL_INCREMENT,
                x, y + Hexagon.FULL_INCREMENT,
                x - (Hexagon.SIZE / 2.0), y + Hexagon.HALF_INCREMENT
        );
    }
}
