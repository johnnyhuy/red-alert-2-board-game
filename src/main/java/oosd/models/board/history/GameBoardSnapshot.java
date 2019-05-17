package oosd.models.board.history;

import oosd.models.board.Board;

public class GameBoardSnapshot implements Snapshot<Board> {
    private final Board board;

    public GameBoardSnapshot(Board board) {
        this.board = board;
    }

    @Override
    public Board getState() {
        return this.board;
    }
}
