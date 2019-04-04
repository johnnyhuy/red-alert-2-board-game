package oosd.models.board;

import oosd.models.units.Unit;

public class Hexagon {
    private int row;
    private int column;
    private Unit unit;

    public Hexagon(int column, int row) {
        this.column = column;
        this.row = row;
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
