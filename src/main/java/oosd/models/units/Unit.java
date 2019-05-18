package oosd.models.units;

import oosd.models.player.Player;
import oosd.models.units.behaviour.UnitBehaviour;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

import static oosd.helpers.ObjectHelper.isNull;

/**
 * SOLID: Open for extension and close for modification
 * Units can be extended with more sub-classes allowing different unit behaviour.
 *
 * Design pattern: template behavioural pattern is used to produce multiple types of units.
 */
public abstract class Unit {
    private Player player;
    private boolean captured;
    private int defendCount = 0;

    public Unit(Player player) {
        this.player = player;
        this.captured = false;
        player.addUnit(this);
    }

    public Unit(Player player, int defendTurns) {
        this.player = player;
        this.captured = false;
        player.addUnit(this);
        setDefendTurns(defendTurns);
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
     * Get the player that owns this unit.
     *
     * @return player object
     */
    public Player getPlayer() {
        return this.player;
    }

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
     * Clone the unit.
     *
     * @return unit behaviour object
     */
    public abstract Unit clone();

    /**
     * Get the amount of turns to defend the unit.
     *
     * @return number of turns
     */
    public int getDefaultDefendTurns() {
        return 2;
    }

    /**
     * Start defending the unit.
     */
    public void startDefending() {
        defendCount = getDefaultDefendTurns();
    }

    /**
     * Decrement the amount of turns.
     */
    public void decrementDefendTurns() {
        defendCount--;
    }

    /**
     * Get the defend status of the unit.
     *
     * @return boolean
     */
    public boolean getDefendStatus() {
        return defendCount != 0;
    }

    /**
     * Get the number of defend turns.
     *
     * @return number of defend turns
     */
    protected int getDefendTurns() {
        return this.defendCount;
    }

    /**
     * Set the number of defend turns.
     *
     * @param defendTurns number of turns to set
     */
    private void setDefendTurns(int defendTurns) {
        defendCount = defendTurns;
    }

    /**
     * Compare units based on their location.
     *
     * @param object any given object
     * @return whether the unit is equal by coordinates
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Unit))
            return false;
        if (object == this)
            return true;

        Unit unit = (Unit) object;
        return equals(unit);
    }

    /**
     * Compare units.
     *
     * @param unit object
     * @return whether the unit is truly equal
     */
    public boolean equals(Unit unit) {
        if (isNull(unit)) {
            return false;
        }

        return new EqualsBuilder()
                .append(getImage(), unit.getImage())
                .append(getName(), unit.getName())
                .isEquals();
    }

    /**
     * Produce a hash code for the object.
     *
     * @return hash code integer
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                .append(getImage())
                .append(getName())
                .toHashCode();
    }
}
