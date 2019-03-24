package oosd.models.units;

import oosd.models.board.Hexagon;
import oosd.models.player.Player;

import java.util.ArrayList;

public abstract class Unit {
    private Hexagon location;
    private Player player;
    private ArrayList<Unit> winnables = new ArrayList<Unit>();
    private ArrayList<Player> allies = new ArrayList<Player>();
    private boolean captured;

    Unit(Hexagon location, Player player){
        this.location = location;
        this.player = player;
        this.captured = false;
    }

    Unit() {}

    public void setStatus(boolean captured) {
    	this.captured = captured;
    }

    public void setLocation(Hexagon location) {
    	this.location = location;
    }

    public void setAlly(Player ally) {
    	this.allies.add(ally);
    }

    public ArrayList<Player> getAllies() {
    	return allies;
    }

    public ArrayList<Unit> getWinnables() {
    	return winnables;
	}

    abstract public void setWinnables();
}
