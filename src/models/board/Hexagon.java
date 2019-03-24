package models.board;

public class Hexagon {
	private int row;
	private int column;
	
	public Hexagon (int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
}
