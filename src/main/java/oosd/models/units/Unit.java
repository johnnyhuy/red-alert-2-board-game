package oosd.models.units;

import oosd.models.player.Player;
import oosd.models.units.behaviour.UnitBehaviour;

import java.util.List;

/**
 * SOLID: Open for extension and close for modification
 * Units can be extended with more sub-classes allowing different unit behaviour.
 */
public abstract class Unit {
    private Player player;
    private boolean captured;

    protected Unit(Player player) {
        this.player = player;
        this.captured = false;
        player.addUnit(this);
    }

    public boolean getCaptured() {
        return this.captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    public abstract List<Class<? extends Unit>> getWinnables();

    public Player getPlayer() {
        return this.player;
    }

    public abstract String getName();

    public abstract String getImage();

    public abstract UnitBehaviour getUnitBehaviour();
}
