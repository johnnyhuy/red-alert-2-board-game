package oosd.models.board;

import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;

@Invariant({"columns > 0", "rows > 0"})
public class Board {
    private Hexagon[][] hexagons;
    private int rows;
    private int columns;

    @Requires({"columns > 0", "rows > 0"})
    @Ensures("hexagons[columns][rows] != null")
    public Board(int columns, int rows) {
        this.rows = rows;
        this.columns = columns;
        this.hexagons = new Hexagon[columns][rows];

        for (int rowIndex = 0; rowIndex < this.getRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < this.getColumns(); columnIndex++) {
                this.hexagons[columnIndex][rowIndex] = new Hexagon(columnIndex, rowIndex);
            }
        }
    }

    /**
     * Get the hexagon given the object
     *
     * @param hexagon object
     * @return hexagon object
     */
    @Ensures({"result.getColumns() == hexagon.getColumns()", "result.getRows() == hexagon.getRows()"})
    public Hexagon getHexagon(Hexagon hexagon) {
        return hexagons[hexagon.getColumn()][hexagon.getRow()];
    }

    /**
     * Get hexagon by int coordinates.
     *
     * @param column x coordinate
     * @param row    y coordinate
     * @return hexagon object
     */
    @Ensures({"result.getColumns() == column", "result.getRows() == row"})
    public Hexagon getHexagon(int column, int row) {
        return hexagons[column][row];
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
