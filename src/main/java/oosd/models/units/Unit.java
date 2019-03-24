package oosd.models.units;

import oosd.models.board.Hexagon;
import oosd.models.player.Player;

import java.util.ArrayList;

public abstract class Unit {
    private Hexagon location;
    private Player player;
    private ArrayList<Class<? extends Unit>> winnables = new ArrayList<>();
    private ArrayList<Player> allies = new ArrayList<>();
    private boolean captured;

    Unit(Hexagon location, Player player) {
        this.location = location;
        this.player = player;
        this.captured = false;
    }

    public void setStatus(boolean captured) {
        this.captured = captured;
    }

    public Hexagon getLocation() {
        return this.location;
    }

    public void setAlly(Player ally) {
        this.allies.add(ally);
    }

    public ArrayList<Player> getAllies() {
        return allies;
    }

    ArrayList<Class<? extends Unit>> getWinnables() {
        return winnables;
    }

    public Player getPlayer() {
        return this.player;
    }
}
