package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Piece;

public class UnitPiecePressedHandler implements EventHandler<MouseEvent> {
    private GameEngine gameEngine;
    private GameController gameController;
    private Piece piece;

    public UnitPiecePressedHandler(GameEngine gameEngine, GameController gameController, Piece piece) {
        this.gameEngine = gameEngine;
        this.gameController = gameController;
        this.piece = piece;
    }

    @Override
    public void handle(MouseEvent event) {
        if (!piece.getUnit().getPlayer().equals(gameEngine.getTurn())) {
            return;
        }

        gameController.selectUnit(event, gameEngine.getSelectedPiece(), piece);
        event.setDragDetect(true);
    }
}
