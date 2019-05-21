package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.GameEngine;
import oosd.models.board.Piece;

import static oosd.helpers.ObjectHelper.isNull;

public class DefendClickHandler implements EventHandler<MouseEvent> {
    private GameEngine gameEngine;
    private GameController gameController;

    public DefendClickHandler(GameEngine gameEngine, GameController gameController) {
        this.gameEngine = gameEngine;
        this.gameController = gameController;
    }

    @Override
    public void handle(MouseEvent event) {
        Piece selectedPiece = gameEngine.getSelectedPiece();

        if (isNull(selectedPiece)) {
            return;
        }

        gameController.defendUnit(event, selectedPiece);
    }
}
