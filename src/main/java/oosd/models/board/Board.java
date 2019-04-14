package oosd.models.board;

import de.vksi.c4j.ContractReference;
import oosd.contracts.models.BoardContract;

@ContractReference(BoardContract.class)
public class Board {
    private int rows;
    private int columns;

    public Board(int columns, int rows) {
        this.rows = rows;
        this.columns = columns;
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
