package oosd.views.handler;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Piece;

public class PieceHandler implements EventHandler<MouseEvent> {
    private GameEngine gameEngine;
    private GameController gameController;
    private Piece piece;

    public PieceHandler(GameEngine gameEngine, GameController gameController, Piece piece) {
        this.gameEngine = gameEngine;
        this.gameController = gameController;
        this.piece = piece;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Piece selectedPiece = gameEngine.getSelectedPiece();

        if (piece.getUnit() != null) {
            if (piece.getUnit().getDefendStatus() || !piece.getUnit().getPlayer().equals(gameEngine.getTurn())) {
                return;
            }

            if (piece.equals(selectedPiece)) {
                gameController.defendUnit(mouseEvent, piece);
            } else {
                gameController.selectUnit(mouseEvent, selectedPiece, piece);
            }

            return;
        }

        if (selectedPiece != null) {
            if (!selectedPiece.getUnit().getUnitBehaviour().isValidMove(gameEngine, piece)) {
                return;
            }

            gameController.moveUnit(mouseEvent, selectedPiece, piece);
        }
    }
}
