package oosd.models.units;

import oosd.models.GameEngine;
import oosd.models.player.Player;
import oosd.models.units.behaviour.UnitBehaviour;

import java.util.ArrayList;

/**
 * SOLID: Open for extension and close for modification
 * Units can be extended with more sub-classes allowing different unit behaviour.
 */
public abstract class Unit {
    protected GameEngine gameEngine;
    private Player player;
    private ArrayList<Class<? extends Unit>> winnables = new ArrayList<>();
    private boolean captured;

    protected Unit(Player player) {
        this.player = player;
        this.captured = false;
    }

    public boolean getCaptured() {
        return this.captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    public ArrayList<Class<? extends Unit>> getWinnables() {
        return winnables;
    }

    public Player getPlayer() {
        return this.player;
    }

    public abstract String getName();

    public abstract int getMove();

    public abstract UnitBehaviour getUnitBehaviour();
}
