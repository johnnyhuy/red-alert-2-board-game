package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Piece;

import static oosd.helpers.ObjectHelper.exists;

public class UnitPieceClickHandler implements EventHandler<MouseEvent> {
    private GameEngine gameEngine;
    private GameController gameController;
    private Piece piece;

    public UnitPieceClickHandler(GameEngine gameEngine, GameController gameController, Piece piece) {
        this.gameEngine = gameEngine;
        this.gameController = gameController;
        this.piece = piece;
    }

    @Override
    public void handle(MouseEvent event) {
        Piece selectedPiece = gameEngine.getSelectedPiece();
        boolean unitExists = exists(piece.getUnit());
        boolean isEnemyUnit = unitExists && !piece.getUnit().getPlayer().equals(gameEngine.getTurn());
        boolean isDefensive = unitExists && piece.getUnit().getDefendStatus();
        boolean isValidMove = exists(selectedPiece) && selectedPiece.getUnit().getUnitBehaviour().isValidMove(gameEngine, piece);

        if (isEnemyUnit && !isDefensive && isValidMove) {
            gameController.attackUnit(event, selectedPiece, piece);
        } else if (piece.equals(selectedPiece)) {
            gameController.defendUnit(event, piece);
        } else if (!isDefensive && !isEnemyUnit && unitExists) {
            gameController.selectUnit(event, selectedPiece, piece);
        }
    }
}
