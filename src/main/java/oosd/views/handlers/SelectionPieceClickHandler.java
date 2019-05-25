package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.board.Piece;
import oosd.models.game.Engine;

public class SelectionPieceClickHandler implements EventHandler<MouseEvent> {
    private Engine engine;
    private GameController gameController;
    private Piece piece;

    public SelectionPieceClickHandler(Engine engine, GameController gameController, Piece piece) {
        this.engine = engine;
        this.gameController = gameController;
        this.piece = piece;
    }

    @Override
    public void handle(MouseEvent event) {
        Piece selectedPiece = engine.getSelectedPiece();

        if (engine.canAttackUnit(piece)) {
            gameController.attackUnit(selectedPiece, piece);
        } else if (engine.canMoveUnit(piece)) {
            gameController.moveUnit(selectedPiece, piece);
        }
    }
}
