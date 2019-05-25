package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.game.Engine;
import oosd.views.components.panes.SidebarPane;

public class UndoClickHandler implements EventHandler<MouseEvent> {
    private Engine engine;
    private GameController gameController;
    private SidebarPane sidebar;

    public UndoClickHandler(Engine engine, GameController gameController, SidebarPane sidebar) {
        this.engine = engine;
        this.gameController = gameController;
        this.sidebar = sidebar;
    }

    @Override
    public void handle(MouseEvent event) {
        if (!engine.getTurn().getUndoStatus()) {
            return;
        }

        gameController.undo();

        sidebar.getTurnCountText().setText(String.format("Remaining turns: %d", engine.getRemainingTurns()));
    }
}
