package oosd.models.board;

import de.vksi.c4j.ContractReference;
import oosd.contracts.models.BoardContract;

@ContractReference(BoardContract.class)
public class Board {
    private Piece[][] pieces;
    private int rows;
    private int columns;

    public Board(int columns, int rows) {
        this.rows = rows;
        this.columns = columns;
        this.pieces = new Piece[columns][rows];

        for (int rowIndex = 0; rowIndex < this.getRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < this.getColumns(); columnIndex++) {
                this.pieces[columnIndex][rowIndex] = new Piece(columnIndex, rowIndex);
            }
        }
    }

    /**
     * Get the piece given the object
     *
     * @param piece object
     * @return piece object
     */
    public Piece getPiece(Piece piece) {
        return pieces[piece.getColumn()][piece.getRow()];
    }

    /**
     * Get piece by int coordinates.
     *
     * @param column x coordinate
     * @param row    y coordinate
     * @return piece object
     */
    public Piece getPiece(int column, int row) {
        return pieces[column][row];
    }

    /**
     * Get row size of the board.
     *
     * @return count of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Get column size of the board.
     *
     * @return count of columns
     */
    public int getColumns() {
        return columns;
    }
}
