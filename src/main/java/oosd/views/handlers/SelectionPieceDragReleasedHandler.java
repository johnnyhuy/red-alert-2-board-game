package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.board.Piece;
import oosd.models.game.Engine;
import oosd.views.components.panes.SidebarPane;

public class SelectionPieceDragReleasedHandler implements EventHandler<MouseEvent> {
    private Engine engine;
    private GameController gameController;
    private Piece piece;
    private SidebarPane sidebar;

    public SelectionPieceDragReleasedHandler(Engine engine, GameController gameController, Piece piece, SidebarPane sidebar) {
        this.engine = engine;
        this.gameController = gameController;
        this.piece = piece;
        this.sidebar = sidebar;
    }

    @Override
    public void handle(MouseEvent event) {
        Piece selectedPiece = engine.getSelected();

        if (engine.canAttack(piece)) {
            gameController.attack(selectedPiece, piece);
        } else if (engine.canMove(piece)) {
            gameController.move(selectedPiece, piece);
        }

        sidebar.getTurnCountText().setText(String.format("Remaining turns: %d", engine.getRemainingTurns()));
    }
}
