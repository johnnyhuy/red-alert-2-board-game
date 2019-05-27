package oosd.factories;

import oosd.models.board.Board;
import oosd.models.player.Player;

import java.util.List;

// TODO: merge board and players together
public interface GameSetupFactory {
    /**
     * Setup the board.
     *
     * @return a board
     */
    Board createBoard();

    /**
     * Create players to the game
     *
     * @param board to be applied to
     * @return list of players
     */
    List<Player> createPlayers(Board board);
}
