package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;

public class UndoClickHandler implements EventHandler<MouseEvent> {
    private GameController gameController;

    public UndoClickHandler(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void handle(MouseEvent event) {
        gameController.undoMove(event);
    }
}
