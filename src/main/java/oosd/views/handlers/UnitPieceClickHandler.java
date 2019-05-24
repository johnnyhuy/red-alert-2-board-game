package oosd.views.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oosd.controllers.GameController;
import oosd.models.board.Piece;
import oosd.models.game.Engine;
import oosd.views.components.panes.SidebarPane;

public class UnitPieceClickHandler implements EventHandler<MouseEvent> {
    private Engine engine;
    private GameController gameController;
    private Piece piece;
    private SidebarPane sidebar;

    public UnitPieceClickHandler(Engine engine, GameController gameController, Piece piece, SidebarPane sidebar) {
        this.engine = engine;
        this.gameController = gameController;
        this.piece = piece;
        this.sidebar = sidebar;
    }

    @Override
    public void handle(MouseEvent event) {
        Piece selectedPiece = engine.getSelectedPiece();
        boolean isEnemyUnit = !piece.getUnit().getPlayer().equals(engine.getTurn());
        boolean isDefensive = piece.getUnit().getDefendStatus();

        if (piece.equals(selectedPiece)) {
            gameController.defendUnit(piece);
        } else if (!isDefensive && !isEnemyUnit) {
            gameController.selectUnit(selectedPiece, piece);
        }

        sidebar.getTurnCountText().setText(String.format("Remaining turns: %d", engine.getRemainingTurns()));
    }
}
