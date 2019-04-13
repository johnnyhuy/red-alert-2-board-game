package oosd.views.components;

import javafx.scene.shape.Polygon;
import oosd.models.board.Piece;

public class PieceViewComponent {
    private final Polygon[][] pieces;

    public PieceViewComponent(Polygon[][] pieces) {
        this.pieces = pieces;
    }

    public Polygon getPiece(Piece piece) {
        return pieces[piece.getColumn()][piece.getRow()];
    }

    public Polygon getPiece(int column, int row) {
        return pieces[column][row];
    }
}
