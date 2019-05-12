package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Piece;

public class PieceDraggedHandler implements EventHandler<MouseEvent> {
    private GameEngine gameEngine;
    private GameController gameController;
    private Piece piece;

    public PieceDraggedHandler(GameEngine gameEngine, GameController gameController, Piece piece) {
        this.gameEngine = gameEngine;
        this.gameController = gameController;
        this.piece = piece;
    }

    public void handle(MouseEvent mouseEvent) {
        Piece selectedPiece = gameEngine.getSelectedPiece();
        gameController.selectUnit(mouseEvent, selectedPiece, piece);
    }
}
