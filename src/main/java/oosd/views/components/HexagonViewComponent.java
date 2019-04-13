package oosd.views.components;

import javafx.scene.shape.Polygon;
import oosd.models.board.Hexagon;

public class HexagonViewComponent {
    private final Polygon[][] hexagons;

    public HexagonViewComponent(Polygon[][] hexagons) {
        this.hexagons = hexagons;
    }

    public Polygon getHexagon(Hexagon hexagon) {
        return hexagons[hexagon.getColumn()][hexagon.getRow()];
    }

    public Polygon getHexagon(int column, int row) {
        return hexagons[column][row];
    }
}
