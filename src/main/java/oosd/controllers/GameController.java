package oosd.controllers;

import oosd.models.board.Piece;
import oosd.models.game.Engine;
import oosd.models.game.GameLogger;
import oosd.models.player.Player;
import oosd.views.BoardView;
import oosd.views.WelcomeView;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;

/**
 * GRASP: The controller
 * Used to handle requests from other objects include the view and model.
 * Acts as a middleman that delegates tasks to other objects.
 * Cleanly separates the user interface (view) from the business objects (model)
 */
@Controller
public class GameController {
    private final Engine engine;
    private WelcomeView welcomeView;
    private GameLogger gameLogger;
    private BoardView boardView;

    @Inject
    public GameController(Engine engine, GameLogger gameLogger, BoardView boardView, WelcomeView welcomeView) {
        this.engine = engine;
        this.gameLogger = gameLogger;
        this.boardView = boardView;
        this.welcomeView = welcomeView;
    }

    public void start() {
        boardView.start();
        welcomeView.welcome();
    }

    /**
     * Used to select a unit.
     *
     * @param selectedPiece object
     * @param piece         object
     */
    public void select(Piece selectedPiece, Piece piece) {
        gameLogger.log(String.format("%s selected %s unit", engine.getTurn().getPlayerName(), piece.getUnit().getName()));
        engine.select(piece);
        boardView.selectUnit(selectedPiece, piece);
    }

    /**
     * Tasked to move the unit.
     *
     * @param selectedPiece object
     * @param piece         object
     */
    public void move(Piece selectedPiece, Piece piece) {
        gameLogger.log(String.format("%s moved %s unit", engine.getTurn().getPlayerName(), selectedPiece.getUnit().getName()));
        engine.move(selectedPiece, piece);
        boardView.updateBoard();
    }

    /**
     * Defend a given piece.
     *
     * @param piece object
     */
    public void defend(Piece piece) {
        gameLogger.log(String.format("%s defended %s unit", engine.getTurn().getPlayerName(), piece.getUnit().getName()));
        engine.defend(piece);
        boardView.updateBoard();
    }

    /**
     * Attack a given piece.
     *
     * @param selectedPiece object
     * @param piece         object
     */
    public void attack(Piece selectedPiece, Piece piece) {
        gameLogger.log(String.format("%s attacked %s unit", engine.getTurn().getPlayerName(), piece.getUnit().getName()));
        engine.attack(selectedPiece, piece);
        boardView.updateBoard();
    }

    /**
     * Undo a move in the game.
     */
    public void undo() {
        Player player = engine.getTurn();
        gameLogger.log(String.format("%s undone a move %d/3 times", player.getPlayerName(), player.getUndoMoves()));
        engine.undoTurn();
        boardView.updateBoard();
    }

    /**
     * Forfeit the game.
     */
    public void forfeit() {
        gameLogger.log(String.format("%s forfeited the game", engine.getTurn().getPlayerName()));
        engine.forfeitGame();
        welcomeView.welcome("Greetings commander", "It seems like someone has forfeited the game, lets start again!");
        boardView.updateBoard();
    }

    /**
     * Restore a game.
     */
    public void restoreGame() {
        gameLogger.log(String.format("%s restored a game", engine.getTurn().getPlayerName()));
        engine.restoreGame();
        boardView.updateBoard();
    }

    /**
     * Save a game.
     */
    public void saveGame() {
        gameLogger.log(String.format("%s saved a game", engine.getTurn().getPlayerName()));
        engine.saveGame();
    }

    /**
     * End the game.
     */
    public void endGame() {
        gameLogger.log("Game finished!");
        engine.endGame();
        engine.resetGame();
        boardView.updateBoard();
        welcomeView.welcome(String.format("Player %s wins!", engine.getWinningPlayer().getPlayerName()), "End game! lets start again!");
    }
}
