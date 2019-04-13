package oosd.models.units;

import oosd.models.player.Player;
import oosd.models.units.behaviour.UnitBehaviour;

import java.util.List;

/**
 * SOLID: Open for extension and close for modification
 * Units can be extended with more sub-classes allowing different unit behaviour.
 */
// TODO: Convert this to C4J
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

    // @pre.condition captured != null
    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    // @pre.condition result.size() > 0
    public abstract List<Class<? extends Unit>> getWinnables();

    public Player getPlayer() {
        return this.player;
    }

    // @post.condition name.size() > 0
    public abstract String getName();

    public abstract String getImage();

    public abstract UnitBehaviour getUnitBehaviour();
}
