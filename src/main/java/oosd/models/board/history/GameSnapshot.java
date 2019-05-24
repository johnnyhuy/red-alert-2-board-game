package oosd.models.board.history;

import oosd.models.board.Board;
import oosd.models.player.Player;

import java.util.Collection;

public class GameSnapshot implements Snapshot {
    private final Board board;
    private Collection<Player> players;

    public GameSnapshot(Board board, Collection<Player> players) {
        this.board = board;
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public Collection<Player> getPlayers() {
        return players;
    }
}
