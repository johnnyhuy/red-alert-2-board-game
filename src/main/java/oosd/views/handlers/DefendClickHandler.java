package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.board.Piece;
import oosd.models.game.Engine;
import oosd.views.components.panes.SidebarPane;

import static oosd.helpers.ObjectHelper.isNull;

public class DefendClickHandler implements EventHandler<MouseEvent> {
    private Engine engine;
    private GameController gameController;
    private SidebarPane sidebar;

    public DefendClickHandler(Engine engine, GameController gameController, SidebarPane sidebar) {
        this.engine = engine;
        this.gameController = gameController;
        this.sidebar = sidebar;
    }

    @Override
    public void handle(MouseEvent event) {
        Piece selectedPiece = engine.getSelected();

        if (isNull(selectedPiece)) {
            return;
        }

        gameController.defend(selectedPiece);

        sidebar.getTurnCountText().setText(String.format("Remaining turns: %d", engine.getRemainingTurns()));
    }
}
