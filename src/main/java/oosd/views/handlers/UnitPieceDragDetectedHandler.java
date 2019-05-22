package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.board.Piece;
import oosd.models.game.Engine;
import oosd.views.components.polygons.UnitPiecePolygon;

public class UnitPieceDragDetectedHandler implements EventHandler<MouseEvent> {
    private final GameController gameController;
    private final Piece piece;
    private Engine engine;
    private UnitPiecePolygon unitPiecePolygon;

    public UnitPieceDragDetectedHandler(Engine engine, GameController gameController, Piece piece, UnitPiecePolygon unitPiecePolygon) {
        this.engine = engine;
        this.gameController = gameController;
        this.piece = piece;
        this.unitPiecePolygon = unitPiecePolygon;
    }

    @Override
    public void handle(MouseEvent event) {
        if (!piece.getUnit().getPlayer().equals(engine.getTurn())) {
            return;
        }

        unitPiecePolygon.startFullDrag();
        gameController.selectUnit(event, engine.getSelectedPiece(), piece);
    }
}
