package oosd.models.board;

import de.vksi.c4j.ContractReference;
import oosd.contracts.models.PieceContract;
import oosd.models.units.Unit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import static oosd.helpers.ObjectHelper.isNull;

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
        if (!(object instanceof Piece))
            return false;
        if (object == this)
            return true;

        Piece piece = (Piece) object;
        return equals(piece);
    }

    /**
     * Compare pieces.
     *
     * @param piece object
     * @return whether the piece is truly equal
     */
    public boolean equals(Piece piece) {
        if (isNull(piece)) {
            return false;
        }

        return new EqualsBuilder()
                .append(row, piece.getRow())
                .append(column, piece.getColumn())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                .append(row)
                .append(column)
                .toHashCode();
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
