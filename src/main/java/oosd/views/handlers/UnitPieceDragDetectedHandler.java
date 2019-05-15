package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Piece;
import oosd.views.components.UnitPiecePolygon;

public class UnitPieceDragDetectedHandler implements EventHandler<MouseEvent> {
    private final GameController gameController;
    private final Piece piece;
    private GameEngine gameEngine;
    private UnitPiecePolygon unitPiecePolygon;

    public UnitPieceDragDetectedHandler(GameEngine gameEngine, GameController gameController, Piece piece, UnitPiecePolygon unitPiecePolygon) {
        this.gameEngine = gameEngine;
        this.gameController = gameController;
        this.piece = piece;
        this.unitPiecePolygon = unitPiecePolygon;
    }

    @Override
    public void handle(MouseEvent event) {
        if (!piece.getUnit().getPlayer().equals(gameEngine.getTurn())) {
            return;
        }

        unitPiecePolygon.startFullDrag();
        gameController.selectUnit(event, gameEngine.getSelectedPiece(), piece);
    }
}
