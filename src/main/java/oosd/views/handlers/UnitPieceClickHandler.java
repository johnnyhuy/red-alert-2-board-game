package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.board.Piece;
import oosd.models.game.Engine;

public class UnitPieceClickHandler implements EventHandler<MouseEvent> {
    private Engine engine;
    private GameController gameController;
    private Piece piece;

    public UnitPieceClickHandler(Engine engine, GameController gameController, Piece piece) {
        this.engine = engine;
        this.gameController = gameController;
        this.piece = piece;
    }

    @Override
    public void handle(MouseEvent event) {
        Piece selectedPiece = engine.getSelected();

        if (engine.canDefend(piece)) {
            gameController.defend(piece);

            if (engine.getRemainingTurns() == 0) {
                gameController.endGame();
            }
        } else if (engine.canSelect(piece)) {
            gameController.select(selectedPiece, piece);
        }
    }
}
