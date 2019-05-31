package oosd.models.board.history;

import oosd.models.board.Board;
import oosd.models.player.Player;

import java.util.List;

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
    List<Player> getPlayers();

    Player getTurn();
}
