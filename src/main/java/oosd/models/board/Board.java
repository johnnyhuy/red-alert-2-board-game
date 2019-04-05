package oosd.models.board;

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
    }

    public Hexagon getHexagon(Hexagon hexagon) {
        return hexagons[hexagon.getColumn()][hexagon.getRow()];
    }

    public Hexagon getHexagon(int column, int row) {
        return hexagons[column][row];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
