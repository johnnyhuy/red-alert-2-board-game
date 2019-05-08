package oosd.views.components;

import oosd.models.board.Piece;

public class PieceViewComponent<T> {
    private final T[][] pieces;

    public PieceViewComponent(T[][] pieces) {
        this.pieces = pieces;
    }

    public T getPiece(Piece piece) {
        return pieces[piece.getColumn()][piece.getRow()];
    }

    public T getPiece(int column, int row) {
        return pieces[column][row];
    }

    public T[][] getComponent() {
        return this.pieces;
    }
}
