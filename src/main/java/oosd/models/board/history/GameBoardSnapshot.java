package oosd.models.board.history;

import oosd.models.board.Board;
import oosd.models.board.GameBoard;

public class GameBoardSnapshot implements Snapshot<Board> {
    private final Board board;

    public GameBoardSnapshot(Board board) {
        this.board = new GameBoard(board.getColumns(), board.getRows());
        board.apply(((column, row) -> this.board.getPiece(column, row).setUnit(board.getPiece(column, row).getUnit())));
    }

    @Override
    public Board getState() {
        return this.board;
    }
}
