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
            boolean isEnemyUnit = !piece.getUnit().getPlayer().equals(gameEngine.getTurn());
            boolean isDefensive = piece.getUnit().getDefendStatus();

            if (selectedPiece != null && isEnemyUnit && !isDefensive && selectedPiece.getUnit().getUnitBehaviour().isValidMove(gameEngine, piece)) {
                gameController.attackUnit(mouseEvent, selectedPiece, piece);
            } else if (piece.equals(selectedPiece)) {
                gameController.defendUnit(mouseEvent, piece);
            } else if (!isDefensive && !isEnemyUnit) {
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