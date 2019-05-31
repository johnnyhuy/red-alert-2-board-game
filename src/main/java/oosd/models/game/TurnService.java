package oosd.models.game;

import oosd.models.player.Player;

public interface TurnService {
    /**
     * Get the turn of the game.
     *
     * @return a player in the turn
     */
    Player getTurn();

    /**
     * Set the player turn.
     *
     * @param player in the list
     */
    void setTurn(Player player);

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

    /**
     * Get the limit of turns the game can do in total.
     *
     * @return number of turns
     */
    int getTurnLimit();

    /**
     * Get the next turn by going through the list sequentially.
     */
    void getNextTurn();

    /**
     * Reset turn to the start of the players list.
     */
    void resetTurn();
}
