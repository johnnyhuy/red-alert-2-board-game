package oosd.models.board;

public interface Board {
    /**
     * Get the piece given the object
     *
     * @param piece object
     * @return piece object
     */
    Piece getPiece(Piece piece);

    /**
     * Get piece by int coordinates.
     *
     * @param column x coordinate
     * @param row    y coordinate
     * @return piece object
     */
    Piece getPiece(int column, int row);

    /**
     * Get row size of the board.
     *
     * @return count of rows
     */
    int getRows();

    /**
     * Get column size of the board.
     *
     * @return count of columns
     */
    int getColumns();

    /**
     * Apply logic when iterating through the board.
     *
     * @param action applied in the loops
     */
    void apply(BoardActionable action);
}
