package oosd.models.board;

public class Board
{
    private Hexagon[][] hexagonBoard;
    private int rows;
    private int columns;

    public Board(int rows, int columns)
    {
        super();
        this.rows = rows;
        this.columns = columns;
        this.hexagonBoard = new Hexagon[rows][columns];
    }

    public void create()
    {
        for (int rowIndex = 0; rowIndex < this.getRows(); rowIndex++)
        {
            for (int columnIndex = 0; columnIndex < this.getColumns(); columnIndex++)
            {
                this.hexagonBoard[rowIndex][columnIndex] = new Hexagon(rowIndex, columnIndex);
            }
        }
    }

    public Hexagon[][] getHexagons()
    {
        return this.hexagonBoard;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
