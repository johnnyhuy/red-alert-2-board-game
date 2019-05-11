package oosd.models.board;

import de.vksi.c4j.ContractReference;
import oosd.contracts.models.GameBoardContract;

@ContractReference(GameBoardContract.class)
public class GameBoard implements Board {
    private Piece[][] pieces;
    private int rows;
    private int columns;

    public GameBoard(int columns, int rows) {
        this.rows = rows;
        this.columns = columns;
        this.pieces = new Piece[columns][rows];

        for (int rowIndex = 0; rowIndex < this.getRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < this.getColumns(); columnIndex++) {
                this.pieces[columnIndex][rowIndex] = new Piece(columnIndex, rowIndex);
            }
        }
    }

    @Override
    public Piece getPiece(Piece piece) {
        return pieces[piece.getColumn()][piece.getRow()];
    }

    @Override
    public Piece getPiece(int column, int row) {
        return pieces[column][row];
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public void apply(BoardActionable action) {
        for (int row = 0; row < this.getRows(); row++) {
            for (int column = 0; column < this.getColumns(); column++) {
                action.apply(column, row);
            }
        }
    }
}
