package oosd.models.board.history;

import oosd.models.board.Board;
import oosd.models.player.Player;

import java.util.Collection;

public interface Snapshot {
    /**
     * Get board.
     *
     * @return board
     */
    Board getBoard();

    /**
     * Get the players.
     *
     * @return list of players.
     */
    Collection<Player> getPlayers();
}
