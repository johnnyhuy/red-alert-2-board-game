package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.views.components.UnitPiecePolygon;

public class UnitPieceDragDetectedHandler implements EventHandler<MouseEvent> {
    private UnitPiecePolygon unitPiecePolygon;

    public UnitPieceDragDetectedHandler(UnitPiecePolygon unitPiecePolygon) {
        this.unitPiecePolygon = unitPiecePolygon;
    }

    @Override
    public void handle(MouseEvent event) {
        unitPiecePolygon.startFullDrag();
    }
}
