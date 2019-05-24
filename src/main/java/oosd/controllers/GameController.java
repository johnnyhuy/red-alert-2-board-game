package oosd.controllers;

import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import oosd.models.board.Piece;
import oosd.models.game.Engine;
import oosd.views.BoardView;
import oosd.views.WelcomeView;
import oosd.views.components.panes.WelcomeWindowPane;

/**
 * GRASP: The controller
 * Used to handle requests from other objects include the view and model.
 * Acts as a middleman that delegates tasks to other objects.
 * Cleanly separates the user interface (view) from the business objects (model)
 */
public class GameController extends Controller {
    private final Engine engine;
    private Stage primaryStage;
    private BoardView boardView;
    private WelcomeWindowPane welcomeWindowPane;

    public GameController(Engine engine, Stage primaryStage) {
        this.engine = engine;
        this.primaryStage = primaryStage;
    }

    public void start() {
        boardView = new BoardView(this, engine, primaryStage);

        WelcomeView welcomeView = new WelcomeView();
        welcomeView.welcome();
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
