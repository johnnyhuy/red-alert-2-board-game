package oosd.models.units;

import de.vksi.c4j.ContractReference;
import oosd.contracts.models.UnitContract;
import oosd.models.board.Piece;
import oosd.models.units.behaviour.UnitBehaviour;

import java.util.List;

/**
 * SOLID: Open for extension and close for modification
 * Units can be extended with more sub-classes allowing different unit behaviour.
 */
@ContractReference(UnitContract.class)
public abstract class Unit {
    private Piece location;
    private boolean captured;

    protected Unit(Piece location) {
        this.location = location;
        this.captured = false;
    }

    /**
     * Get the captured status of the unit.
     *
     * @return boolean
     */
    public boolean getCaptured() {
        return this.captured;
    }

    /**
     * Set the captured status of the unit.
     *
     * @param captured boolean
     */
    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    /**
     * Get a list of units this unit can win.
     *
     * @return list of winnable units
     */
    public abstract List<Class<? extends Unit>> getWinnables();

    /**
     * Get the name of this unit.
     *
     * @return string
     */
    public abstract String getName();

    /**
     * Get the unit image name.
     *
     * @return string
     */
    public abstract String getImage();

    /**
     * Get the unit behaviour.
     *
     * @return unit behaviour object
     */
    public abstract UnitBehaviour getUnitBehaviour();

    /**
     * Get the location of a piece.
     *
     * @return piece object
     */
    public Piece getLocation() {
        return location;
    }

    /**
     * Set location to the unit.
     *
     * @param piece object
     */
    public void setLocation(Piece piece) {
        this.location = piece;
    }
}
