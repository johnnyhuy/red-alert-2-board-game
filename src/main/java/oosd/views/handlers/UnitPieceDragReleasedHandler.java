package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Piece;

import static oosd.helpers.ObjectHelper.exists;

public class UnitPieceDragReleasedHandler implements EventHandler<MouseEvent> {
    private GameEngine gameEngine;
    private GameController gameController;
    private Piece piece;

    public UnitPieceDragReleasedHandler(GameEngine gameEngine, GameController gameController, Piece piece) {
        this.gameEngine = gameEngine;
        this.gameController = gameController;
        this.piece = piece;
    }

    @Override
    public void handle(MouseEvent event) {
        Piece selectedPiece = gameEngine.getSelectedPiece();
        boolean unitExists = exists(piece.getUnit());
        boolean isValidMove = selectedPiece.getUnit().getUnitBehaviour().isValidMove(gameEngine, piece);

        if (!unitExists && isValidMove) {
            gameController.moveUnit(event, selectedPiece, piece);
        }
    }
}
