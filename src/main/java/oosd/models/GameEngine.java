package oosd.models;

import oosd.models.board.Board;

public class GameEngine {
    private Board board;

    public GameEngine() {
        this.board = new Board(6, 6);
    }

    public Board getBoard() {
        return this.board;
    }
}
