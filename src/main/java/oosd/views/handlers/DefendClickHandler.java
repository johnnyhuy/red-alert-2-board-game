package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.board.Piece;
import oosd.models.game.Engine;

import static oosd.helpers.ObjectHelper.isNull;

public class DefendClickHandler implements EventHandler<MouseEvent> {
    private Engine engine;
    private GameController gameController;

    public DefendClickHandler(Engine engine, GameController gameController) {
        this.engine = engine;
        this.gameController = gameController;
    }

    @Override
    public void handle(MouseEvent event) {
        Piece selectedPiece = engine.getSelectedPiece();

        if (isNull(selectedPiece)) {
            return;
        }

        gameController.defendUnit(event, selectedPiece);
    }
}
