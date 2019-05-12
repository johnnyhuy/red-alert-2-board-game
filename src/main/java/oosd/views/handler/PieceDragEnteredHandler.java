package oosd.views.handler;

import javafx.event.EventHandler;
import javafx.scene.input.MouseDragEvent;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Piece;

import static oosd.helpers.ObjectHelper.exists;

public class PieceDragEnteredHandler implements EventHandler<MouseDragEvent> {
    private GameEngine gameEngine;
    private GameController gameController;
    private Piece piece;

    public PieceDragEnteredHandler(GameEngine gameEngine, GameController gameController, Piece piece) {
        this.gameEngine = gameEngine;
        this.gameController = gameController;
        this.piece = piece;
    }

    @Override
    public void handle(MouseDragEvent mouseDragEvent) {
        Piece selectedPiece = gameEngine.getSelectedPiece();
        boolean unitExists = exists(piece.getUnit());
        boolean isEnemyUnit = unitExists && !piece.getUnit().getPlayer().equals(gameEngine.getTurn());
        boolean isDefensive = unitExists && piece.getUnit().getDefendStatus();
        boolean isValidMove = exists(selectedPiece) && selectedPiece.getUnit().getUnitBehaviour().isValidMove(gameEngine, piece);

        gameController.selectUnit(mouseDragEvent, selectedPiece, piece);
    }
}
