package oosd.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import oosd.models.board.Piece;
import oosd.models.game.Engine;
import oosd.views.BoardView;
import oosd.views.components.panes.BoardPane;
import oosd.views.components.panes.SidebarPane;
import oosd.views.components.panes.ToolbarPane;
import oosd.views.components.panes.WindowGridPane;

/**
 * GRASP: The controller
 * Used to handle requests from other objects include the view and model.
 * Acts as a middleman that delegates tasks to other objects.
 * Cleanly separates the user interface (view) from the business objects (model)
 */
public class GameController extends Controller {
    private final Engine engine;

    @FXML
    private WindowGridPane windowGridPane;

    @FXML
    private BoardPane boardPane;

    @FXML
    private SidebarPane sidebar;

    @FXML
    private ToolbarPane toolbar;

    private BoardView boardView;

    public GameController(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void initialize() {
        boardView = new BoardView(this, engine, boardPane, sidebar, toolbar);
        boardView.render();
    }

    /**
     * Used to select a unit.
     *
     * @param event mouse event
     * @param selectedPiece object
     * @param piece object
     */
    public void selectUnit(MouseEvent event, Piece selectedPiece, Piece piece) {
        engine.selectUnit(piece);
        boardView.selectUnit(selectedPiece, piece);
    }

    /**
     * Tasked to move the unit.
     *
     * @param event mouse event
     * @param selectedPiece object
     * @param piece object
     */
    public void moveUnit(Event event, Piece selectedPiece, Piece piece) {
        engine.moveUnit(selectedPiece, piece);
        boardView.moveUnit(selectedPiece, piece);
    }

    /**
     * Defend a given piece.
     *
     * @param event mouse event
     * @param piece object
     */
    public void defendUnit(MouseEvent event, Piece piece) {
        engine.defendUnit(piece);
        boardView.defendUnit(piece);
    }

    /**
     * Attack a given piece.
     *
     * @param mouseEvent    mouse event
     * @param selectedPiece object
     * @param piece         object
     */
    public void attackUnit(MouseEvent mouseEvent, Piece selectedPiece, Piece piece) {
        engine.attackUnit(selectedPiece, piece);
        boardView.attackUnit(selectedPiece, piece);
    }

    /**
     * Undo a move in the game.
     *
     * @param mouseEvent mouse event
     */
    public void undoMove(MouseEvent mouseEvent) {
        engine.undoTurn();
        boardView.undoMove();
    }
}
