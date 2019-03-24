package oosd.models.board;

public class Board
{
    private Hexagon[][] hexagons;
    private int rows;
    private int columns;

    public Board(int rows, int columns)
    {
        this.rows = rows;
        this.columns = columns;
        this.hexagons = new Hexagon[rows][columns];

        for (int rowIndex = 0; rowIndex < this.getRows(); rowIndex++)
        {
            for (int columnIndex = 0; columnIndex < this.getColumns(); columnIndex++)
            {
                this.hexagons[rowIndex][columnIndex] = new Hexagon(rowIndex, columnIndex);
            }
        }
    }

    public Hexagon[][] getHexagons()
    {
        return this.hexagons;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
