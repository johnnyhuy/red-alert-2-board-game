package oosd.models.board;

import static org.valid4j.Assertive.*;

public class Board {
    private Hexagon[][] hexagons;
    private int rows;
    private int columns;

    public Board(int columns, int rows) {
        this.rows = rows;
        this.columns = columns;
        this.hexagons = new Hexagon[columns][rows];

        for (int rowIndex = 0; rowIndex < this.getRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < this.getColumns(); columnIndex++) {
                this.hexagons[columnIndex][rowIndex] = new Hexagon(columnIndex, rowIndex);
            }
        }
        
    	// assert() keyword used for invariance since valid4j only handles pre and post-conditions.
    	assert columns >= 0 : "Column number cannot be negative.";
    	assert rows >= 0 : "Row number cannot be negative.";
    }

    /**
     * Get the hexagon given the object
     *
     * @param hexagon object
     * @return hexagon object
     */
    public Hexagon getHexagon(Hexagon hexagon) {
        int colResult;
        int rowResult;
        
        colResult = hexagon.getColumn();
        rowResult = hexagon.getRow();
        
        // @post-condition: returned hexagon is correct column + row
    	require(hexagons[hexagon.getColumn()][hexagon.getRow()] == hexagons[colResult][rowResult]);
    	
        return hexagons[hexagon.getColumn()][hexagon.getRow()];
    }

    /**
     * Get hexagon by int coordinates.
     *
     * @param column x coordinate
     * @param row    y coordinate
     * @return hexagon object
     */
    public Hexagon getHexagon(int column, int row) {
    	int colInt;
    	int rowInt;
    	
    	colInt = column;
    	rowInt = row;
    	
        // @post-condition: returned hexagon is correct column + row
    	require(hexagons[column][row] == hexagons[colInt][rowInt]);
    	
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
