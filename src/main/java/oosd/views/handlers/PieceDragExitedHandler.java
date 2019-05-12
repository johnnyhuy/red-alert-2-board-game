package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseDragEvent;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Piece;

public class PieceDragExitedHandler implements EventHandler<MouseDragEvent> {
    private GameEngine gameEngine;
    private GameController gameController;
    private Piece piece;

    public PieceDragExitedHandler(GameEngine gameEngine, GameController gameController, Piece piece) {
        this.gameEngine = gameEngine;
        this.gameController = gameController;
        this.piece = piece;
    }

    @Override
    public void handle(MouseDragEvent mouseDragEvent) {
        Piece selectedPiece = gameEngine.getSelectedPiece();
        gameController.selectUnit(mouseDragEvent, selectedPiece, piece);
    }
}
