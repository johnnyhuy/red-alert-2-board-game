package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Piece;

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
        boolean isEnemyUnit = !piece.getUnit().getPlayer().equals(gameEngine.getTurn());
        boolean isDefensive = piece.getUnit().getDefendStatus();

        if (event.isDragDetect()) {
            return;
        }

        if (piece.equals(selectedPiece)) {
            gameController.defendUnit(event, piece);
        } else if (!isDefensive && !isEnemyUnit) {
            gameController.selectUnit(event, selectedPiece, piece);
        }
    }
}
