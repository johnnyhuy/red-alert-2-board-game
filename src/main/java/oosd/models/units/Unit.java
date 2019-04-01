package oosd.models.units;

import oosd.models.player.Player;

import java.util.ArrayList;

public abstract class Unit {
    private Player player;
    private ArrayList<Class<? extends Unit>> winnables = new ArrayList<>();
    private boolean captured;

    Unit(Player player) {
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
}
