package oosd.controllers;

import oosd.models.board.Piece;
import oosd.models.game.Engine;
import oosd.views.BoardView;
import oosd.views.GamePresenter;
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
    private BoardView boardView;

    public GameController(Engine engine, GamePresenter gamePresenter) {
        this.engine = engine;
        this.boardView = new BoardView(engine, gamePresenter, this);
        this.welcomeView = new WelcomeView();
    }

    public void start() {
        boardView.start();
        welcomeView.welcome();
    }

    /**
     * Used to select a unit.
     *
     * @param selectedPiece object
     * @param piece object
     */
    public void select(Piece selectedPiece, Piece piece) {
        engine.select(piece);
        boardView.selectUnit(selectedPiece, piece);
    }

    /**
     * Tasked to move the unit.
     *
     * @param selectedPiece object
     * @param piece object
     */
    public void move(Piece selectedPiece, Piece piece) {
        engine.move(selectedPiece, piece);
        boardView.moveUnit(selectedPiece, piece);
    }

    /**
     * Defend a given piece.
     *
     * @param piece object
     */
    public void defend(Piece piece) {
        engine.defend(piece);
        boardView.defendUnit(piece);
    }

    /**
     * Attack a given piece.
     *
     * @param selectedPiece object
     * @param piece         object
     */
    public void attack(Piece selectedPiece, Piece piece) {
        engine.attack(selectedPiece, piece);
        boardView.attackUnit(selectedPiece, piece);
    }

    /**
     * Undo a move in the game.
     */
    public void undo() {
        engine.undoTurn();
        boardView.undoMove();
    }

    /**
     * Forfeit the game.
     */
    public void forfeit() {
        engine.forfeitGame();
        welcomeView.welcome("Greetings commander", "It seems like someone has forfeited the game, let's start again!");
        boardView.resetGame();
    }
}
