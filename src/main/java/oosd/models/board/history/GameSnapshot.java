package oosd.models.board.history;

import oosd.models.board.Board;
import oosd.models.player.Player;

import java.util.List;

public class GameSnapshot implements Snapshot {
    private final Board board;
    private List<Player> players;
    private Player turn;

    public GameSnapshot(Board board, List<Player> players, Player turn) {
        this.board = board;
        this.players = players;
        this.turn = turn;
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public Player getTurn() {
        return this.turn;
    }
}
