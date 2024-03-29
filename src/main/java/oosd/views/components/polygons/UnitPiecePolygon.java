package oosd.views.components.polygons;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import oosd.models.units.Unit;
import oosd.views.View;

public class UnitPiecePolygon extends HexagonPiecePolygon {
    public void setUnitImage(Unit unit) {
        this.setFill(new ImagePattern(new Image(View.class.getResource(unit.getImage() + ".png").toString())));
        this.setVisible(true);
    }
}
