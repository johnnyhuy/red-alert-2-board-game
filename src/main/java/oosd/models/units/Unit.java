package oosd.models.units;

import de.vksi.c4j.ContractReference;
import oosd.contracts.models.UnitContract;
import oosd.models.player.Player;
import oosd.models.units.behaviour.UnitBehaviour;

import java.util.List;

/**
 * SOLID: Open for extension and close for modification
 * Units can be extended with more sub-classes allowing different unit behaviour.
 *
 * Design pattern: template behavioural pattern is used to produce multiple types of units.
 */
@ContractReference(UnitContract.class)
public abstract class Unit {
    private Player player;
    private boolean captured;
    private int defendCount = 0;

    protected Unit(Player player) {
        this.player = player;
        this.captured = false;
        player.addUnit(this);
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

    public int getDefaultDefendCount() {
        return 2;
    }

    public void startDefendCount() {
        defendCount = getDefaultDefendCount();
    }

    public void decrementDefendCount() {
        defendCount--;
    }

    public boolean getDefendStatus() {
        return defendCount != 0;
    }
}
