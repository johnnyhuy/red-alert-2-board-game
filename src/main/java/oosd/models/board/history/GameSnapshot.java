package oosd.models.board.history;

import oosd.models.board.Board;
import oosd.models.player.Player;

import java.util.List;

public class GameSnapshot implements Snapshot {
    private final Board board;
    private List<Player> players;

    public GameSnapshot(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
