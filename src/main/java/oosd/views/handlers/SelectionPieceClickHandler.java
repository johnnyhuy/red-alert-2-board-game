package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.board.Piece;
import oosd.models.game.Engine;

import static oosd.helpers.ObjectHelper.exists;

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
        boolean unitExists = exists(piece.getUnit());
        boolean isValidMove = selectedPiece.getUnit().getUnitBehaviour().isValidMove(engine, piece);
        boolean isEnemyUnit = unitExists && !piece.getUnit().getPlayer().equals(engine.getTurn());
        boolean isDefensive = unitExists && piece.getUnit().getDefendStatus();

        if (unitExists && isEnemyUnit && !isDefensive && isValidMove) {
            gameController.attackUnit(selectedPiece, piece);
        } else if (!unitExists && isValidMove) {
            gameController.moveUnit(selectedPiece, piece);
        }
    }
}
