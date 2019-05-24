package oosd.controllers;

import javafx.stage.Stage;
import oosd.models.board.Piece;
import oosd.models.game.Engine;
import oosd.views.BoardView;
import oosd.views.WelcomeView;

/**
 * GRASP: The controller
 * Used to handle requests from other objects include the view and model.
 * Acts as a middleman that delegates tasks to other objects.
 * Cleanly separates the user interface (view) from the business objects (model)
 */
public class GameController extends Controller {
    private final Engine engine;
    private WelcomeView welcomeView;
    private Stage primaryStage;
    private BoardView boardView;

    public GameController(Engine engine, Stage primaryStage) {
        this.engine = engine;
        this.primaryStage = primaryStage;
    }

    /**
     * Startup the game.
     */
    public void start() {
        boardView = new BoardView(this, engine, primaryStage);
        welcomeView = new WelcomeView();
        welcomeView.welcome();
    }

    /**
     * Used to select a unit.
     *
     * @param selectedPiece object
     * @param piece object
     */
    public void selectUnit(Piece selectedPiece, Piece piece) {
        engine.selectUnit(piece);
        boardView.selectUnit(selectedPiece, piece);
    }

    /**
     * Tasked to move the unit.
     *
     * @param selectedPiece object
     * @param piece object
     */
    public void moveUnit(Piece selectedPiece, Piece piece) {
        engine.moveUnit(selectedPiece, piece);
        boardView.moveUnit(selectedPiece, piece);
    }

    /**
     * Defend a given piece.
     *
     * @param piece object
     */
    public void defendUnit(Piece piece) {
        engine.defendUnit(piece);
        boardView.defendUnit(piece);
    }

    /**
     * Attack a given piece.
     *
     * @param selectedPiece object
     * @param piece         object
     */
    public void attackUnit(Piece selectedPiece, Piece piece) {
        engine.attackUnit(selectedPiece, piece);
        boardView.attackUnit(selectedPiece, piece);
    }

    /**
     * Undo a move in the game.
     */
    public void undoMove() {
        engine.undoTurn();
        boardView.undoMove();
    }
}
