package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.game.Engine;

public class UndoClickHandler implements EventHandler<MouseEvent> {
    private Engine engine;
    private GameController gameController;

    public UndoClickHandler(Engine engine, GameController gameController) {
        this.engine = engine;
        this.gameController = gameController;
    }

    @Override
    public void handle(MouseEvent event) {
        if (!engine.getTurn().canUndo()) {
            return;
        }

        gameController.undo();
    }
}
