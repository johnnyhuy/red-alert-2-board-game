package oosd.models.board.history;

import oosd.models.board.Board;

public class BoardSnapshot implements Snapshot<Board> {
    private final Board board;

    public BoardSnapshot(Board board) {
        this.board = board;
    }

    @Override
    public Board getState() {
        return this.board;
    }
}
