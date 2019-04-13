package oosd.models.board;

import de.vksi.c4j.ContractReference;
import oosd.contracts.models.PieceContract;
import oosd.models.units.Unit;

@ContractReference(PieceContract.class)
public class Piece {
    private int row;
    private int column;
    private Unit unit;

    public Piece(int column, int row) {
        this.column = column;
        this.row = row;
    }

    /**
     * Get the row of the piece.
     *
     * @return location of row on the board
     */
    public int getRow() {
        return row;
    }

    /**
     * Get the column of the piece.
     *
     * @return location of column on the board
     */
    public int getColumn() {
        return column;
    }

    /**
     * Compare pieces based on their location.
     *
     * @param object any given object
     * @return whether the piece is equal by coordinates
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Piece)) {
            return false;
        }

        Piece piece = (Piece) object;

        return piece.getRow() == getRow() && piece.getColumn() == getColumn();
    }

    /**
     * Get the unit.
     *
     * @return unit of the piece
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Set the unit.
     *
     * @param unit used to set to the piece
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
