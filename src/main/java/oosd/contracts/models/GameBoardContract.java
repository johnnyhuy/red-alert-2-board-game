package oosd.contracts.models;

import de.vksi.c4j.ClassInvariant;
import de.vksi.c4j.Target;
import oosd.models.board.Board;
import oosd.models.board.GameBoard;
import oosd.models.board.Piece;

import static de.vksi.c4j.Condition.*;

public class GameBoardContract extends GameBoard {
    @Target
    private Board target;

    public GameBoardContract(int columns, int rows) {
        super(columns, rows);

        if (preCondition()) {
            assert columns > 0;
            assert rows > 0;
        }
    }

    @ClassInvariant
    public void invariant() {
        assert target.getColumns() > 0;
        assert target.getRows() > 0;
    }

    @Override
    public Piece getPiece(Piece piece) {
        if (preCondition()) {
            assert piece.getColumn() < target.getColumns();
            assert piece.getRow() < target.getRows();
        }

        if (postCondition()) {
            Piece targetPiece = target.getPiece(new Piece(piece.getColumn(), piece.getRow()));

            assert targetPiece.getColumn() == piece.getColumn();
            assert targetPiece.getRow() == piece.getRow();
        }

        return ignored();
    }

    @Override
    public Piece getPiece(int column, int row) {
        if (preCondition()) {
            assert column < target.getColumns();
            assert row < target.getRows();
        }

        if (postCondition()) {
            Piece targetPiece = target.getPiece(column, row);

            assert targetPiece.getColumn() == column;
            assert targetPiece.getRow() == row;
        }

        return ignored();
    }
}
