package oosd.models.board;

import oosd.models.units.Unit;

public class Hexagon {
    private int row;
    private int column;
    private Unit unit;

    public Hexagon(int column, int row) {
        this.column = column;
        this.row = row;
        
        // Invariant: column and row cannot be negative
        assert column >= 0 : "Column number cannot be negative.";
        assert row >= 0 : "Row number cannot be negative.";
    }

    /**
     * Get the row of the hexagon.
     *
     * @return location of row on the board
     */
    public int getRow() {
        return row;
    }

    /**
     * Get the column of the hexagon.
     *
     * @return location of column on the board
     */
    public int getColumn() {
        return column;
    }

    /**
     * Compare hexagons based on their location.
     *
     * @param object any given object
     * @return whether the hexagon is equal by coordinates
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Hexagon)) {
            return false;
        }

        Hexagon hexagon = (Hexagon) object;

        return hexagon.getRow() == getRow() && hexagon.getColumn() == getColumn();
    }

    /**
     * Get the unit.
     *
     * @return unit of the hexagon
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Set the unit.
     *
     * @param unit used to set to the hexagon
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
