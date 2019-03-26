package oosd.models.board;

public class Hexagon {
    private int row;
    private int column;

    public Hexagon(int row, int column) {
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

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Hexagon)) {
            return false;
        }

        Hexagon hexagon = (Hexagon) object;

        return hexagon.getRow() == this.getRow() && hexagon.getColumn() == this.getColumn();
    }
}
