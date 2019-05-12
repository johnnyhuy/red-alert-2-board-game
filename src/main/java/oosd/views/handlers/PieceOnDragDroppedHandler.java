package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Piece;

public class PieceOnDragDroppedHandler implements EventHandler<DragEvent> {
    private GameEngine gameEngine;
    private GameController gameController;
    private Piece piece;

    public PieceOnDragDroppedHandler(GameEngine gameEngine, GameController gameController, Piece piece) {
        this.gameEngine = gameEngine;
        this.gameController = gameController;
        this.piece = piece;
    }

    @Override
    public void handle(DragEvent event) {
        System.out.println("Test");
        // Data transfer is not successful
        event.setDropCompleted(true);
        event.consume();
    }
}
