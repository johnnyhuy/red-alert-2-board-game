package oosd.models.board;

import oosd.models.board.history.Snapshot;

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
     * Get board pieces 2 dimensional array.
     *
     * @return 2 dimensional array
     */
    Piece[][] getPieces();

    /**
     * Apply logic when iterating through the board.
     *
     * @param action applied in the loops
     */
    void apply(BoardActionable action);

    /**
     * Save the board with a snapshot.
     *
     * @return board object
     */
    Snapshot<Board> save();

    /**
     * Restore the board with a given snapshot.
     *
     * @param boardSnapshot object
     */
    void restore(Snapshot boardSnapshot);
}
