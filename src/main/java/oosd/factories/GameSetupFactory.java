package oosd.factories;

import oosd.models.board.Board;
import oosd.models.player.Player;

import java.util.List;

interface GameSetupFactory {
    /**
     * Create the game board.
     *
     * @return board
     */
    Board createBoard();

    /**
     * Create a list of players for the game.
     *
     * @return list of players
     */
    List<Player> createPlayers(Board board);
}
