package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.GameEngine;

public class UndoClickHandler implements EventHandler<MouseEvent> {
    private GameEngine gameEngine;
    private GameController gameController;

    public UndoClickHandler(GameEngine gameEngine, GameController gameController) {
        this.gameEngine = gameEngine;
        this.gameController = gameController;
    }

    @Override
    public void handle(MouseEvent event) {
        if (!gameEngine.getTurn().getUndoStatus()) {
            return;
        }

        gameController.undoMove(event);
    }
}
