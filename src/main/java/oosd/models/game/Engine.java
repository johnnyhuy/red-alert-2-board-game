package oosd.models.game;

import oosd.models.board.Board;
import oosd.models.board.Piece;
import oosd.models.player.Player;

public interface Engine {
    /**
     * Get the game board.
     *
     * @return board that contains the game
     */
    Board getBoard();

    /**
     * Get the selected piece user clicks.
     *
     * @return selected piece
     */
    Piece getSelectedPiece();

    /**
     * Get the turn of the game.
     *
     * @return a player in the turn
     */
    Player getTurn();

    /**
     * Undo player turns.
     */
    boolean undoTurn();

    /**
     * Move the unit in the game.
     *
     * @param selectedPiece that wants to move
     * @param targetPiece   to move to
     */
    void moveUnit(Piece selectedPiece, Piece targetPiece);

    /**
     * Defend a unit in the game.
     *
     * @param piece to be defended
     */
    void defendUnit(Piece piece);

    /**
     * Attack a given unit in the game.
     *
     * @param attackingPiece that want to attack the target piece
     * @param targetPiece    that will be attacked
     */
    void attackUnit(Piece attackingPiece, Piece targetPiece);

    /**
     * Select a unit on the board.
     *
     * @param piece to be selected
     */
    void selectUnit(Piece piece);


    /**
     * Get the total amount of turns in the game.
     *
     * @return amount of turns
     */
    int getTurns();

    /**
     * Get the remaining amount of turns.
     *
     * @return remaining turns
     */
    int getRemainingTurns();

    int getTurnLimit();

    /**
     * Reset the game to the start.
     */
    void resetGame();

    /**
     * When a player turn quits the game, all other players win and the game resets.
     */
    void forfeitGame();

    /**
     * End the game and calculate results.
     */
    void endGame();
}
