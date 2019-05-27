package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.game.Engine;
import oosd.views.GamePresenter;

public class UndoClickHandler implements EventHandler<MouseEvent> {
    private Engine engine;
    private GameController gameController;
    private GamePresenter gamePresenter;

    public UndoClickHandler(Engine engine, GameController gameController, GamePresenter gamePresenter) {
        this.engine = engine;
        this.gameController = gameController;
        this.gamePresenter = gamePresenter;
    }

    @Override
    public void handle(MouseEvent event) {
        if (!engine.getTurn().canUndo()) {
            return;
        }

        gameController.undo();
        gamePresenter.getPlayerInfoVBox().update(engine);
    }
}
